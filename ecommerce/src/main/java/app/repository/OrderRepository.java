package app.repository;

import app.model.Order;

public interface OrderRepository extends BaseRepository<Long, Order>{
	public Long saveOrder(Order order);
	
	public void updateOrder(Order updatedOrder);
}