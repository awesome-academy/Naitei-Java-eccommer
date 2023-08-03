package app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import app.authentication.MyDBAuthenticationService;
import app.model.Category;
import app.service.CategoryService;

@Controller
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private MyDBAuthenticationService authenticationService;

	@GetMapping("/shop-grid")
	public String index(Model model) {
		UserDetails userDetails = authenticationService.getCurrentUserDetails();
		if (userDetails != null) {
			model.addAttribute("username",
					userDetails.getUsername().substring(0, 1).toUpperCase() + userDetails.getUsername().substring(1));
		}
		List<Category> categories = categoryService.findAll();
		model.addAttribute("categories", categories);
		return "views/user/shop-grid/index";
	}
}
