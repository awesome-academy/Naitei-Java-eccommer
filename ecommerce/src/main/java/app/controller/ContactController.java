package app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ContactController extends BaseController{
	@GetMapping("/contacts")
	public String contact(Model model) {
		CheckUser(model);
		model.addAttribute("currentPage", "contact");
		return "views/user/contacts/index";
	}
}
