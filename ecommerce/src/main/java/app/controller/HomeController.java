package app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import app.authentication.MyDBAuthenticationService;

@Controller
public class HomeController {

	@Autowired
	private MyDBAuthenticationService authenticationService;



	@GetMapping("/")
	public String index(Model model) {
		UserDetails userDetails = authenticationService.getCurrentUserDetails();
		if (userDetails != null) {
			model.addAttribute("username",
					userDetails.getUsername().substring(0, 1).toUpperCase() + userDetails.getUsername().substring(1));
		}
		return "views/user/home/index";
	}

	@GetMapping("/admin")
	public String dashboard() {
		return "views/admin/dashboard/index";
	}
}