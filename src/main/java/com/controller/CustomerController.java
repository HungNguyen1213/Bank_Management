package com.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.converter.AccountConverter;
import com.converter.BookSavingConverter;
import com.dto.AccountDto;
import com.dto.BookSavingDto;
import com.entity.AccountEntity;
import com.entity.BookSavingEntity;
import com.entity.CustomerEntity;
import com.repository.AccountRepository;
import com.repository.BookSavingRepository;
import com.repository.CustomerRepository;

@Controller
@RequestMapping("/customer")
public class CustomerController {
	@Autowired
	public AccountRepository accRepo;
	
	@Autowired
    public CustomerRepository cusRepo;
	
	public AccountConverter ac;
	
	@Autowired
	public BookSavingRepository bsRepo;
	
	@Autowired
	public BookSavingConverter bsc;
	
	public CustomerController() {
		super();
		ac = new AccountConverter();
	}

	@GetMapping
	public String showCustomer(Model model) {
		model.addAttribute("isSuccess", true);
		return "customer";
	}
	
	@GetMapping("/search")
	public String searchCustomer(@RequestParam("searchKey") String searchKey, Model model) {
		Map<String, Object> rs = handleSearchCustomer(searchKey, accRepo, ac);
		model.addAttribute("acc", rs.get("acc"));
		model.addAttribute("isSuccess", rs.get("isSuccess"));
		return "customer";
	}
	
	public Map<String, Object> handleSearchCustomer(String searchKey, AccountRepository repo, AccountConverter converter) {
		AccountEntity ae = repo.findByNumberAccount(searchKey);
		Map<String, Object> result = new HashMap<>();
		if(ae != null) {
			AccountDto acc = converter.toDto(ae);
			result.put("acc", acc);
			result.put("isSuccess", true);
			
		}
		else {
			result.put("acc", null);
			result.put("isSuccess", false);
		}
		return result;
	}
	
	@GetMapping("/detail/{id}")
	public String showDetail(@PathVariable("id") Long id, Model model) {
		Map<String, Object> rs = handleShowCustomerDetail(id, accRepo, ac, bsRepo, bsc);
		model.addAttribute("acc", rs.get("acc"));
		model.addAttribute("listBs", rs.get("listBs"));
		return "customer_detail";
	}

    public Map<String, Object> handleShowCustomerDetail(Long id, AccountRepository repoAcc,
    		AccountConverter accConverter, BookSavingRepository repoBs, BookSavingConverter bsConverter) {
    	Map<String, Object> result = null;
    	Optional<AccountEntity> OptAe = repoAcc.findById(id);
		if(OptAe.isPresent()) {
			result = new HashMap<>();
			AccountDto acc = accConverter.toDto(OptAe.get());
			result.put("acc", acc);		
			List<BookSavingEntity> listE = repoBs.findByAccountId(id);
			if(listE.isEmpty()) {
				result.put("listBs", null);
			}
			else {
				List<BookSavingDto> listBs = new ArrayList<>();
				for(BookSavingEntity bse : listE) {
					listBs.add(bsConverter.toDto(bse));
				}
				
				result.put("listBs", listBs);
			}
		}
		return result;
    }

    @GetMapping("/register")
    public String getAccount(Model model){
        AccountDto account = new AccountDto();
        int code = randomCode();
        account.setNumberAccount(String.valueOf(code));
        model.addAttribute("account", account);
        return "register";
    }
    
    private int randomCode() {
    	return (int) Math.floor(((Math.random() * 899999) + 100000));
    }

    @PostMapping("/register")
    public String createAccount(Model model, @ModelAttribute("account") AccountEntity account, @RequestParam("balance") String balance,
    		@RequestParam("birthday") String birthday, @RequestParam("confirm-password") String confirmPassword) throws ParseException {
    	AccountEntity accountPost = (AccountEntity) model.getAttribute("account");
        model.addAttribute("acc", handleCreateAccount(birthday, balance, accountPost, cusRepo, accRepo));
        return "register_success";
    }
    
    public AccountEntity handleCreateAccount(String birthday, String balance, AccountEntity accountPost,
    		CustomerRepository repoCus, AccountRepository repoAcc) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date birthdayFormat = sdf.parse(birthday);
        accountPost.getCustomer().setBirthday(birthdayFormat);
        CustomerEntity customer = repoCus.save(accountPost.getCustomer());
        accountPost.setCustomer(customer);
        accountPost.setBalance(Long.parseLong(balance));
        return repoAcc.save(accountPost);
    }
}
