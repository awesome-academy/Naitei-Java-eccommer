package app.service;

import java.util.List;

import app.model.CartItem;
import app.model.Order;
import app.model.OrderItem;
import app.request.formOrder;

public interface OrderService extends BaseService<Long, Order> {
	public void saveOrder(formOrder formOrder, List<CartItem> cartItems);

	public void updateOrder(Order order);

	public List<OrderItem> findByOrderId(Long orderId);

}