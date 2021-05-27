package com.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.converter.AccountConverter;
import com.converter.BookSavingConverter;
import com.converter.InteresConverter;
import com.dto.AccountDto;
import com.dto.BookSavingDto;
import com.dto.InteresDto;
import com.entity.AccountEntity;
import com.entity.BookSavingEntity;
import com.entity.InteresEntity;
import com.entity.InteresEntity.Type;
import com.repository.AccountRepository;
import com.repository.BookSavingRepository;
import com.repository.InteresRepository;

@Controller
@RequestMapping("/booksaving")
public class BookSavingController {
	@Autowired
	private BookSavingRepository bsRepo;
	
	@Autowired
	private BookSavingConverter bc;
	
	@Autowired
	private InteresRepository iRepo;
	
	@Autowired
	private InteresConverter ic;
	
	@Autowired
	private AccountConverter ac;
	
	@Autowired
	private AccountRepository accRepo;
	
	public BookSavingController() {
		super();
	}

	@GetMapping("/add/{idAccount}")
	public String showAddForm(@PathVariable("idAccount") Long id, Model model) {
		Map<String, Object> rs = handleShowAddForm(id, iRepo, accRepo, ic, ac);
		Type[] types = InteresEntity.Type.values();
		for(Type type : types) {
			model.addAttribute(type.toString().toLowerCase(), rs.get(type.toString().toLowerCase()));
		}
		model.addAttribute("bs", new BookSavingEntity());
		model.addAttribute("acc", rs.get("acc"));
		return "to_saving";
	}
	
	public Map<String, Object> handleShowAddForm(Long id, InteresRepository repoI, AccountRepository repoAcc,
			InteresConverter converterI, AccountConverter converterAcc) {
		Map<String, Object> result = new HashMap<>();
		Iterable<InteresEntity> listE = repoI.findAll();
		List<InteresDto> listDto = new ArrayList<>();
		for(InteresEntity ie : listE) {
			listDto.add(converterI.toDto(ie));
		}
		Type[] types = InteresEntity.Type.values();
		for(Type type : types) {
			result.put(type.toString().toLowerCase(), filterByType(listDto, type));
		}
		Optional<AccountEntity> OptAcc = repoAcc.findById(id);
		if(OptAcc.isPresent()) {
			AccountDto acc = converterAcc.toDto(OptAcc.get());			
			result.put("acc", acc);
		}
		else result.put("acc", null);
		return result;
	}
	
	public List<InteresDto> filterByType(List<InteresDto> listDto, Type type) {
		List<InteresDto> list  = new ArrayList<>();
		for (InteresDto i : listDto) {
			if(i.getType().equals(type.toString())) {
				list.add(i);
			}
		}
		return list;
	}
	
	@PostMapping("/add/{idAccount}")
	public String processAddBookSaving(@RequestParam("interes") String idInteres, @PathVariable("idAccount") String idAccount,
			@RequestParam("description") String description, @RequestParam("amountSend") String amountSend) {
		handleAddBookSaving(idAccount, amountSend, idInteres, description, iRepo, accRepo, bsRepo);
		return "redirect:/customer/detail/" + idAccount;
	}
	
	public BookSavingEntity handleAddBookSaving(String idAccount, String amountSend, String idInteres, String description,
			InteresRepository repoI, AccountRepository repoAcc, BookSavingRepository repoBs) {
		BookSavingEntity bse = new BookSavingEntity();
		Optional<AccountEntity> OptAcc = repoAcc.findById(Long.parseLong(idAccount));
		if(OptAcc.isPresent()) {
			AccountEntity ae = OptAcc.get();
			ae.setBalance(ae.getBalance() - Long.parseLong(amountSend));
			bse.setAccount(ae);
			bse.setAmountSend(Long.parseLong(amountSend));
			bse.setDescription(description);
			Optional<InteresEntity> OptI = repoI.findById(Long.parseLong(idInteres));
			if(OptI.isPresent()) {
				bse.setInteres(OptI.get());
			}
			else {
				return null;
			}
		}
		else return null;
		return repoBs.save(bse);
	}
    
    @GetMapping("/withdraw-info/{idBs}")
    public String showWithdrawInfomation(@PathVariable("idBs") Long idBs, Model model){
        Map<String, Object> rs = handleShowWithdrawInfomation(idBs, bsRepo, bc, ac);
        model.addAttribute("bs", rs.get("bs"));
        model.addAttribute("acc", rs.get("acc"));
        model.addAttribute("totalInteres", rs.get("totalInteres"));
        return "withdraw";
    }
    
    public Map<String, Object> handleShowWithdrawInfomation(Long idBs, BookSavingRepository repoBs,
    		BookSavingConverter converterBs, AccountConverter converterAcc) {
    	Map<String, Object> result = new HashMap<>();
    	Optional<BookSavingEntity> Opt = repoBs.findById(idBs);
        if(Opt.isPresent()) {
        	BookSavingEntity bse = Opt.get();
        	Date date = new Date();
            bse.setWithdrawDate(date);
            BookSavingDto bs = converterBs.toDto(bse);
            result.put("bs", bs);            
            AccountDto acc = converterAcc.toDto(bse.getAccount());
            result.put("acc", acc);            
            long totalInteres = calTotalInteres(bse, date);
            result.put("totalInteres", totalInteres);
        }
        else return null;
        return result;
    }
    
    public long calTotalInteres (BookSavingEntity bse, Date date) {
    	double totalInteres;
        if(bse.getInteres().getType().toString().equals("NOLIMIT")) {
        	Long diff = date.getTime() - bse.getStartDate().getTime();
        	Long dayDiff = TimeUnit.MILLISECONDS.toDays(diff);
        	totalInteres = bse.getAmountSend() * bse.getInteres().getRatio() * dayDiff /365;
        }
        else if (bse.getInteres().getType().toString().equals("MONTH")){
        	totalInteres = bse.getAmountSend() * bse.getInteres().getRatio() * bse.getInteres().getNumber() / 12;
        }
        else {
        	totalInteres = bse.getAmountSend() * bse.getInteres().getRatio() * bse.getInteres().getNumber();
        }
        long result = Math.round(totalInteres);
    	return result;
    }
    
    @GetMapping("/withdraw/{idBs}")
    public String updateWithdraw(@PathVariable("idBs") Long idBs){
        String accId = handleUpdateWithdraw(idBs, bsRepo);
        return "redirect:/customer/detail/" + accId;
    }
    
    public String handleUpdateWithdraw(Long idBs, BookSavingRepository repoBs) {
    	Optional<BookSavingEntity> Opt = repoBs.findById(idBs);
    	String accId = null;
        if(Opt.isPresent()) {
        	BookSavingEntity bse = Opt.get();
        	Date date = new Date();
            bse.setWithdrawDate(date);
            repoBs.save(bse);
            accId = bse.getAccount().getId().toString();
        }
        return accId;
    }
}
