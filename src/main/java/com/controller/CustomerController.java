package com.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
	private AccountRepository accRepo;
	
	@Autowired
	private AccountConverter ac;
	
	@Autowired
	private BookSavingRepository bsRepo;
	
	@Autowired
	private BookSavingConverter bsc;
	
	@GetMapping
	public String showCustomer(Model model) {
		model.addAttribute("isSuccess", true);
		return "customer";
	}
	
	@GetMapping("/search")
	public String searchCustomer(@RequestParam("searchKey") String searchKey, Model model) {
		AccountEntity ae = accRepo.findByNumberAccount(searchKey);
		if(ae != null) {
			AccountDto acc = ac.toDto(ae);
			model.addAttribute("acc", acc);
			model.addAttribute("isSuccess", true);
		}
		else {
			model.addAttribute("isSuccess", false);
		}
		return "customer";
	}
	
	@GetMapping("/detail/{id}")
	public String showDetail(@PathVariable("id") Long id, Model model) {
		Optional<AccountEntity> OptAe = accRepo.findById(id);
		if(OptAe.isPresent()) {
			AccountDto acc = ac.toDto(OptAe.get());
			model.addAttribute("acc", acc);
		}
		List<BookSavingEntity> listE = bsRepo.findByAccountId(id);
		if(listE.isEmpty()) {
			model.addAttribute("listBs", null);
		}
		else {
			List<BookSavingDto> listBs = new ArrayList<>();
			for(BookSavingEntity bse : listE) {
				listBs.add(bsc.toDto(bse));
			}
			model.addAttribute("listBs", listBs);
		}
		return "customer_detail";
	}

    @Autowired
    private CustomerRepository cusRepo;

    @GetMapping("/register")
    public String getAccount(Model model){
        AccountDto account = new AccountDto();
        int code = (int) Math.floor(((Math.random() * 899999) + 100000));
        account.setNumberAccount(String.valueOf(code));
        model.addAttribute("account", account);
        return "register";
    }

    @PostMapping("/register")
    public String createAccount(Model model, @ModelAttribute("account") AccountEntity account, @RequestParam("balance") String balance,
    		@RequestParam("birthday") String birthday, @RequestParam("confirm-password") String confirmPassword) throws ParseException {
        AccountEntity accountPost = (AccountEntity) model.getAttribute("account");
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date birthdayFormat = sdf.parse(birthday);
//        customer.setBirthday(birthdayFormat);
        accountPost.getCustomer().setBirthday(birthdayFormat);
        CustomerEntity customer = cusRepo.save(accountPost.getCustomer());
//        System.out.println(accountPost.getNumberAccount());
        accountPost.setCustomer(customer);
        accountPost.setBalance(Long.parseLong(balance));
        accRepo.save(accountPost);
        model.addAttribute("acc", accountPost);
        return "register_success";
    }
}
