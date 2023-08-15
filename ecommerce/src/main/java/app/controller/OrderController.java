package app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import app.model.CartItem;
import app.request.formOrder;
import app.service.OrderService;
import app.service.ShoppingCartService;

@Controller
public class OrderController extends BaseController {
	@Autowired
	private ShoppingCartService shoppingCartService;

	@Autowired
	private OrderService orderService;

	@GetMapping("/orders/new")
	public String checkout(Model model) {
		CheckUser(model);
		List<CartItem> cartItems = shoppingCartService.getCartItems();
		model.addAttribute("cartItems", cartItems);
		double subtotal = 0, total = 0;

		for (CartItem cartItem : cartItems) {
			subtotal += cartItem.getProduct().getPrice() * cartItem.getQuantity();
			total += cartItem.getProduct().getPrice() * cartItem.getQuantity();
		}

		model.addAttribute("subtotal", subtotal);
		model.addAttribute("total", total);
		model.addAttribute("formOrder", new formOrder());
		return "views/user/orders/new";
	}

	@PostMapping("/orders/save")
	public String order(RedirectAttributes redirectAttributes, @ModelAttribute("formOrder") formOrder formOrder) {
		try {
			List<CartItem> cartItems = shoppingCartService.getCartItems();
			orderService.saveOrder(formOrder, cartItems);
			redirectAttributes.addFlashAttribute("successMessage", "Order placed successfully.");
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("errorMessage", "Error occurred while placing the order.");
		}
		return "redirect:/";
	}
}