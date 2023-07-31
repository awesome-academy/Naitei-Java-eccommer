package app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	// tao create
	@GetMapping("/")
	public String index() {
		return "views/user/home/index";
	}
	
	@GetMapping("/shop-grid")
	public String shopgrid() {
		return "views/user/shopgrid/index";
	}
	@GetMapping("/shop-details")
	public String shopdetails() {
		return "views/user/shopDetails/index";
	}
	@GetMapping("/shoping-cart")
	public String shopingcart() {
		return "views/user/shopingCart/index";
	}
	@GetMapping("/checkout")
	public String checkout() {
		return "views/user/checkOut/index";
	}
	@GetMapping("/blog-details")
	public String blogdetails() {
		return "views/user/blogDetails/index";
	}
	@GetMapping("/blog")
	public String blog() {
		return "views/user/Blog/index";
	}
	@GetMapping("/contact")
	public String contact() {
		return "views/user/Contact/index";
	}
}