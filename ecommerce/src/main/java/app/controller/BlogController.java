package app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import app.model.Blog;
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
		CheckUser(model);
		model.addAttribute("categories", categoryService.findAll());
		model.addAttribute("blogs", blogService.findAll());
		model.addAttribute("currentPage", "blogs");
		return "views/user/blogs/index";
	}
	@GetMapping("/blogs/{id}")
    public String DetailPage(@PathVariable Long id, RedirectAttributes redirectAttributes, Model model) {
		Blog blog = blogService.findById(id);
		if(blog == null) {
			redirectAttributes.addFlashAttribute("errorMessage","Blog not found!");
			return "redirect:/";
		}
		model.addAttribute("blog", blog);

        return "views/user/blogs/show.html"; 
    }
}
