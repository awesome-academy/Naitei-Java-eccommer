package app.service.Impl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import app.authentication.MyDBAuthenticationService;
import app.model.CartItem;
import app.model.Order;
import app.model.OrderItem;
import app.model.Product;
import app.repository.OrderItemRepository;
import app.repository.OrderRepository;
import app.repository.ProductRepository;
import app.repository.UserRepository;
import app.request.formOrder;
import app.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private OrderItemRepository itemRepository;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private MyDBAuthenticationService authenticationService;

	@Override
	public List<Order> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Order findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveOrder(formOrder formOrder, List<CartItem> cartItems) {
		Order order = new Order();
		UserDetails userDetails = authenticationService.getCurrentUserDetails();
		order.setDeliveryStatus("Pending");
		order.setOrderDate(LocalDate.now());
		order.setRecipientName(formOrder.getRecipientName());
		order.setRecipientPhone(formOrder.getRecipientPhone());
		order.setRecipientAddress(formOrder.getRecipientAddress());
		order.setTotalAmount(formOrder.getTotalAmount());
		order.setUserId(userRepository.findUser(userDetails.getUsername()).getId());
		Long orderId = orderRepository.saveOrder(order);

		for (CartItem cartItem : cartItems) {
			OrderItem item = new OrderItem();
			item.setProductId(cartItem.getProduct().getId());
			item.setQuantity(cartItem.getQuantity());
			item.setPrice(cartItem.getProduct().getPrice() * cartItem.getQuantity());
			item.setOrderId(orderId);
			itemRepository.saveOrderItem(item);

			Product product = productRepository.findById(cartItem.getProduct().getId());
			if (product != null) {
				product.setStockQuantity(product.getStockQuantity() - cartItem.getQuantity());
				productRepository.updateProduct(product);
			}
		}
	}
}