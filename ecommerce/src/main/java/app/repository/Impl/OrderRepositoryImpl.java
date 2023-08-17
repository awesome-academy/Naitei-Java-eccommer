package app.repository.Impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import app.model.Order;
import app.repository.OrderRepository;

@Repository
public class OrderRepositoryImpl implements OrderRepository {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Order> findAll() {
		Session session = sessionFactory.openSession();
		Criteria cr = session.createCriteria(Order.class);
		cr.addOrder(org.hibernate.criterion.Order.asc("orderDate")); 
		List<Order> orders = cr.list();
		return orders;
	}

	@Override
	public Order findById(Long id) {
		Session session = sessionFactory.openSession();
		Criteria cr = session.createCriteria(Order.class);
		cr.add(Restrictions.eq("id", id));
		Order order = (Order) cr.uniqueResult();
		return order;
	}

	@Override
	public Long saveOrder(Order order) {
		Long orderId = null;
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		orderId = (Long) session.save(order);
		session.getTransaction().commit();
		return orderId;
	}

}
