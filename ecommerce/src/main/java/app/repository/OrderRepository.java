package app.repository;

import app.model.Order;

public interface OrderRepository extends BaseRepository<Long, Order>{
	public void saveOrder(Order order);
}