package app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import app.validator.LoginValidator;
import app.validator.SignupValidator;
import app.request.formLogin;
import app.request.signUp;
import app.service.UserService;
import app.authentication.MyDBAuthenticationService;

@Controller
public class SessionController {
	@Autowired
	UserService userService;
	@Autowired
	private LoginValidator loginValidator;

	@InitBinder("formLogin")
	protected void initBinder(WebDataBinder binder) {
		binder.addValidators(loginValidator);
	}

	@Autowired
	private SignupValidator signupValidator;

	@InitBinder("signUp")
	protected void initBinders(WebDataBinder binder) {
		binder.addValidators(signupValidator);
	}

	@Autowired
	private MyDBAuthenticationService authenticationService;

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

	@GetMapping("/logoutSuccessful")
	public String logout(Model model, RedirectAttributes redirectAttributes) {
		redirectAttributes.addFlashAttribute("successMessage", "Logout successed!");
		return "redirect:/";
	}

	@GetMapping("/signup")
	public String signup(Model model) {
		model.addAttribute("signUp", new signUp());
		return "views/session/signup/index";
	}

	@PostMapping("/signup")
	public String signup(Model model, @ModelAttribute("signUp") signUp signUp, BindingResult binding,
			RedirectAttributes redirectAttributes) {
		signupValidator.validate(signUp, binding);
		if (binding.hasErrors()) {
			return "views/session/signup/index";
		}
		try {
			redirectAttributes.addFlashAttribute("message", "Signup successed!");
			userService.save(signUp);
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("message", "Signup not successed!");
		} finally {
			return "redirect:/login";
		}

	}
}