package app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import app.authentication.MyDBAuthenticationService;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private MyDBAuthenticationService authenticationService;

	@GetMapping({ "/dashboard", "" })
	public String dashboard(Model model) {
		UserDetails userDetails = authenticationService.getCurrentUserDetails();
		if (userDetails != null) {
			model.addAttribute("username",
					userDetails.getUsername().substring(0, 1).toUpperCase() + userDetails.getUsername().substring(1));
		}
		return "views/admin/dashboard/index";
	}

}