package app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import app.model.CartItem;
import app.model.Order;
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

	@GetMapping("/admin/orders")
	public String index(Model model) {
		CheckUser(model);
		model.addAttribute("orders", orderService.findAll());
		return "views/admin/orders/index";
	}

	@GetMapping("/admin/orders/edit/{id}")
	public String edit(Model model, @PathVariable Long id) {
		CheckUser(model);
		model.addAttribute("order", orderService.findById(id));
		return "views/admin/orders/edit";
	}
	

	@PostMapping("/admin/orders/update")
	public String updateOrder(@ModelAttribute("order") Order updatedOrder, RedirectAttributes redirectAttributes) {
		try {
			orderService.updateOrder(updatedOrder);
			redirectAttributes.addFlashAttribute("successMessage", "Edit successfully.");
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("errorMessage", "Edit error.");
		}
		return "redirect:/admin/orders";
	}

	@GetMapping("/admin/orders/{id}")
	public String detail(Model model,@PathVariable Long id) {
		CheckUser(model);
		model.addAttribute("order", orderService.findById(id));
		model.addAttribute("order_items", orderService.findByOrderId(id));
		return "views/admin/orders/detail";
	}
	

}