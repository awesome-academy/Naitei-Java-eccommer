package app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import app.validator.LoginValidator;
import app.request.formLogin;
import app.model.User;

@Controller
public class SessionController {
	@Autowired
	private LoginValidator loginValidator;
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
	    binder.addValidators(loginValidator);
	}

	@GetMapping("/login")
	public String login(Model model) {
		model.addAttribute("formLogin", new formLogin());
		return "views/session/login/index";
	}
	
	@PostMapping("/login")
	public String handleLogin(Model model, @ModelAttribute("formLogin") formLogin formLogin, BindingResult binding) {
		loginValidator.validate(formLogin, binding);
		if(binding.hasErrors())

		{
			return "views/session/login/index";
		}

		return "redirect:/";
		
	}
}