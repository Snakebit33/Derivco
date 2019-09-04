package ee.mikkelsaar.bank.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ee.mikkelsaar.bank.dao.JdbcUserDAO;
import ee.mikkelsaar.bank.object.Account;
import ee.mikkelsaar.bank.object.Authorities;
import ee.mikkelsaar.bank.object.Password;

@Controller
public class AppController {
	
	@Autowired
	private JdbcUserDAO userDAO;
	
	
	@RequestMapping(value = "/change**", method = RequestMethod.GET)
	public ModelAndView newUserPage() {
		
		ModelAndView model = new ModelAndView();
		model.setViewName("change");
		
		return model;

	}
	
	@RequestMapping(value = "/changePsw**", method = RequestMethod.POST)
	public String changePsw(@ModelAttribute Password password, Model model) {
		
		UserDetails userDetails =(UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		
        userDAO.changePassword(userDetails.getUsername(), password.getPassword());
        userDAO.changeRole(userDetails.getUsername(), Authorities.ROLE_USER);
        
        SecurityContextHolder.getContext().setAuthentication(null);
        
		return "redirect:/login";
	}
	
	@RequestMapping(value = "/user**", method = RequestMethod.GET)
	public ModelAndView userPage() {
		
		UserDetails userDetails =(UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		List<Account> accounts = userDAO.getAccounts(userDetails.getUsername());
		
		ModelAndView model = new ModelAndView();
		model.addObject("accounts", accounts);
		model.setViewName("user");
		
		return model;

	}

	@RequestMapping(value = "/admin**", method = RequestMethod.GET)
	public ModelAndView adminPage() {

		ModelAndView model = new ModelAndView();
		model.addObject("title", "Spring Security Custom Login Form");
		model.addObject("message", "This is protected page!");
		model.setViewName("admin");
		
		return model;

	}

	@RequestMapping(value = { "/", "/login" }, method = RequestMethod.GET)
	public ModelAndView login(@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout) {

		ModelAndView model = new ModelAndView();
		if (error != null) {
			model.addObject("error", "Invalid username or password!");
		}

		if (logout != null) {
			model.addObject("msg", "You've been logged out successfully.");
		}
		model.setViewName("login");

		return model;

	}
	
	@RequestMapping(value = "/403", method = RequestMethod.GET)
	public ModelAndView accesssDenied() {
 
	  ModelAndView model = new ModelAndView();
	  model.setViewName("403");
	  
	  return model;
 
	}

}