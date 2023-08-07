package app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import app.service.BlogService;
import app.service.CategoryService;

@Controller
public class BlogController extends BaseController{
	@Autowired
	private BlogService blogService;
	@Autowired
	private CategoryService categoryService;

	@GetMapping("/blogs")
	public String index(Model model) {
		CheckUsser(model);
		model.addAttribute("categories", categoryService.findAll());
		model.addAttribute("blogs", blogService.findAll());
		return "views/user/blogs/index";
	}
}
