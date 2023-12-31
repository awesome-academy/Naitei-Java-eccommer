package app.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import app.model.Cart;
import app.model.CartItem;
import app.model.Product;
import app.service.ProductService;
import app.service.ShoppingCartService;

@Controller
@RequestMapping("/cart")
public class CartController extends BaseController {
	@Autowired
	private ProductService productService;

	@Autowired
	private ShoppingCartService shoppingCartService;

	@GetMapping()
	public String viewCart(Model model) {
		CheckUser(model);
		List<CartItem> cartItems = shoppingCartService.getCartItems();
		model.addAttribute("cartItems", cartItems);
		return "views/user/carts/index";
	}

	@PostMapping("/add")
	public String addToCart(@RequestParam Long productId, @RequestParam int quantity, HttpSession session) {
		// Lấy cart hiện tại từ session, hoặc tạo mới nếu chưa có
		Cart cart = (Cart) session.getAttribute("cart");
		if (cart == null) {
			cart = new Cart();
			session.setAttribute("cart", cart);
		}

		Product product = productService.findById(productId);
		CartItem cartItem = new CartItem();
		cartItem.setId(productId);
		cartItem.setProduct(product);
		cartItem.setQuantity(quantity);
		cartItem.setCart(cart);

		shoppingCartService.addItem(cartItem);
		return "redirect:/cart";
	}

	@GetMapping("/remove")
	public String removeCartItem(@RequestParam("cartItemId") Long cartItemId, RedirectAttributes redirectAttributes) {
		CartItem cartItem = shoppingCartService.findById(cartItemId);
		if (cartItem == null) {
			redirectAttributes.addFlashAttribute("message", "CartItem not found!");
			return "redirect:/cart";
		}
		shoppingCartService.removeItem(cartItem);
		return "redirect:/cart";
	}

	@PostMapping("/updateCartItemQuantity")
	public String updateCartItemQuantity(@RequestParam("cartItemId") Long cartItemId,
			@RequestParam("quantity") int quantity) {
		CartItem cartItem = shoppingCartService.findById(cartItemId);

		if (cartItem != null) {
			cartItem.setQuantity(quantity);
		}
		return "redirect:/cart";
	}

}
