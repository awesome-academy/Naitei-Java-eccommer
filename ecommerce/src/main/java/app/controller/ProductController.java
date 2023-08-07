package app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import app.authentication.MyDBAuthenticationService;
import app.service.ProductService;

@Controller
public class ProductController {

	@Autowired
	private MyDBAuthenticationService authenticationService;

	@Autowired
	private ProductService productService;

	@GetMapping("/admin/product")
	public String index(Model model) {
		UserDetails userDetails = authenticationService.getCurrentUserDetails();
		if (userDetails != null) {
			model.addAttribute("username",
					userDetails.getUsername().substring(0, 1).toUpperCase() + userDetails.getUsername().substring(1));
		}
		model.addAttribute("products", productService.findAll());
		return "views/admin/product/index";
	}
}
