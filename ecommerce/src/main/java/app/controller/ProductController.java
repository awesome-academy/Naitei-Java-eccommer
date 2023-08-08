package app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import app.model.Product;
import app.service.ProductService;

@Controller
public class ProductController extends BaseController {

	@Autowired
	private ProductService productService;

	@GetMapping("/admin/product")
	public String index(Model model) {
		CheckUser(model);
		model.addAttribute("products", productService.findAll());
		return "views/admin/product/index";
	}

	@GetMapping("/products/{id}")
	public String DetailPage(@PathVariable Long id, RedirectAttributes redirectAttributes, Model model) {
		CheckUser(model);
		Product product = productService.findById(id);
		if (product == null) {
			redirectAttributes.addFlashAttribute("message", "Product not found!");
			return "redirect:/";
		}
		model.addAttribute("product", product);

		return "views/user/products/show.html";
	}
}
