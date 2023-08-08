package app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController extends BaseController {

	@GetMapping({ "/dashboard", "" })
	public String dashboard(Model model) {
		CheckUser(model);
		return "views/admin/dashboard/index";
	}

}