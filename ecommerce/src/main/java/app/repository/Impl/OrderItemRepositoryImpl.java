package app.repository.Impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import app.model.OrderItem;
import app.repository.OrderItemRepository;

@Repository
public class OrderItemRepositoryImpl implements OrderItemRepository {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<OrderItem> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OrderItem findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveOrderItem(OrderItem item) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(item);
		session.getTransaction().commit();
	}

}