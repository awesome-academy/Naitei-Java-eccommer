package app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
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
import app.authentication.MyDBAuthenticationService;
import app.model.User;

@Controller
public class SessionController {
	@Autowired
	private LoginValidator loginValidator;

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.addValidators(loginValidator);
	}

	@Autowired
	private MyDBAuthenticationService authenticationService;

	private AuthenticationManager authenticationManager;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@GetMapping("/login")
	public String login(Model model) {
		model.addAttribute("formLogin", new formLogin());
		return "views/session/login/index";
	}

	@PostMapping("/login")
	public String handleLogin(Model model, @ModelAttribute("formLogin") formLogin formLogin, BindingResult binding) {
		loginValidator.validate(formLogin, binding);
		if (binding.hasErrors()) {
			return "views/session/login/index";
		}

		UserDetails userDetails = authenticationService.loadUserByUsername(formLogin.getUsername());

		if (userDetails != null && passwordEncoder.matches(formLogin.getPassword(), userDetails.getPassword())) {
			Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, null,
					userDetails.getAuthorities());
			SecurityContextHolder.getContext().setAuthentication(authentication);
			return "redirect:/";
		} else {
			model.addAttribute("error", "Invalid username or password");
			return "views/session/login/index";
		}

	}
}