package app.repository;

import java.util.List;

import app.model.OrderItem;

public interface OrderItemRepository extends BaseRepository<Long, OrderItem> {
	public void saveOrderItem(OrderItem item);
	public List<OrderItem> findByOrderId(Long orderId);
}
