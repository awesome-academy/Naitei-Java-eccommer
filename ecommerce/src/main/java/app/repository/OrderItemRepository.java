package app.repository;

import app.model.OrderItem;

public interface OrderItemRepository extends BaseRepository<Long, OrderItem> {
	public void saveOrderItem(OrderItem item);
}
