package app.controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import app.service.CategoryService;

import app.model.Product;
import app.service.ProductService;
import app.model.Category;

@Controller
public class ProductController extends BaseController {

	@Autowired
	private ProductService productService;

	@Autowired
	private CategoryService categoryService;


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
		model.addAttribute("products", productService.getTop6Products());
        return "views/user/products/show.html"; 
    }
	
	@GetMapping("/shop-grid")
    public String ShopPage( Model model) {
		CheckUser(model);
		List<Category> categories = categoryService.findAll();
		model.addAttribute("categories", categories);
		model.addAttribute("products", productService.findAll());
		model.addAttribute("currentPage", "shop-grid");

		return "views/user/shop-grid/index.html";
    }
	
	
}
