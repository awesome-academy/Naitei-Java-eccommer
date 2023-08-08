package app.repository.Impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
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

	@Override
	public Product findById(Long id) {
		try (Session session = sessionFactory.openSession()) {
			Criteria cr = session.createCriteria(Product.class);
			cr.add(Restrictions.eq("id",id));
			Product product = (Product) cr.uniqueResult();
			return product;
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public List<Product> getTop6Products() {
		try (Session session = sessionFactory.openSession()) {
			Criteria cr = session.createCriteria(Product.class);
			List<Product> products = cr.setMaxResults(6).list();
			return products;
		} catch (Exception e) {
			return null;
		}
	}

}
