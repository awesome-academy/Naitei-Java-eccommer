package app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import app.authentication.MyDBAuthenticationService;

public class BaseController {
	@Autowired
	private MyDBAuthenticationService authenticationService;
    public void CheckUser(Model model){
	   UserDetails userDetails = authenticationService.getCurrentUserDetails();
		if (userDetails != null) {
			model.addAttribute("username",
					userDetails.getUsername().substring(0, 1).toUpperCase() + userDetails.getUsername().substring(1));
		}
   }
}
