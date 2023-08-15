package app.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import app.model.Product;
import app.service.CategoryService;
import app.service.ProductService;

@Controller
public class HomeController extends BaseController{
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private ProductService productService;

	@GetMapping("/")
	public String index(Model model) {
		CheckUser(model);
		model.addAttribute("categories", categoryService.findAll());
		model.addAttribute("products", productService.getTop6Products());

		model.addAttribute("currentPage", "home");
		productService.getProductsAndReviews();
		model.addAttribute("topRatedProducts", productService.getProductsAndReviews());
		List<List<Product>> productGroups = new ArrayList<>();
        int chunkSize = 3;
        for (int i = 0; i < productService.getTop6Products().size(); i += chunkSize) {
            int endIndex = Math.min(i + chunkSize, productService.getTop6Products().size());
            productGroups.add(productService.getTop6Products().subList(i, endIndex));
        }
        model.addAttribute("productGroups", productGroups);
		return "views/user/home/index";
	}
	
}