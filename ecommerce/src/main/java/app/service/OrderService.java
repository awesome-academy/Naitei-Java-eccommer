package app.service;

import app.model.Order;
import app.request.formOrder;

public interface OrderService extends BaseService<Long, Order>{
	public void saveOrder(formOrder formOrder);
}