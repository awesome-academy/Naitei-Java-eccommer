package app.repository.Impl;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import app.model.OrderItem;
import app.model.Product;
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

	@Override
	public List<OrderItem> findByOrderId(Long orderId) {
		try (Session session = sessionFactory.openSession()) {
		    CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
		    CriteriaQuery<OrderItem> criteriaQuery = criteriaBuilder.createQuery(OrderItem.class);
		    Root<OrderItem> root = criteriaQuery.from(OrderItem.class);

		    criteriaQuery.select(root);
		    criteriaQuery.where(criteriaBuilder.equal(root.get("orderId"), orderId));

		    List<OrderItem> orderItems = session.createQuery(criteriaQuery).getResultList();
		    return orderItems;
		} catch (Exception e) {
			return null;
		}
		
	}

}