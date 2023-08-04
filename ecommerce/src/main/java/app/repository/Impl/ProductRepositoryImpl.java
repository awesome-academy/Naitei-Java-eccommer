package app.repository.Impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import app.model.Product;
import app.repository.ProductRepository;

@Repository
public class ProductRepositoryImpl implements ProductRepository {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Product> findAll() {
		try (Session session = sessionFactory.openSession()) {
			Criteria cr = session.createCriteria(Product.class);
			List<Product> products = cr.list();
			return products;
		} catch (Exception e) {
			return null;
		}
	}

}
