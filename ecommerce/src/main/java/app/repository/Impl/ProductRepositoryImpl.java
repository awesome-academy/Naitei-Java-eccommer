package app.repository.Impl;

import java.util.Collections;
import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.apache.log4j.Logger;
import app.model.Product;
import app.model.Review;
import app.repository.ProductRepository;

@Repository
public class ProductRepositoryImpl implements ProductRepository {
	private static final Logger logger = Logger.getLogger(ProductRepositoryImpl.class);
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Product> findAll() {
		try (Session session = sessionFactory.openSession()) {
			Criteria cr = session.createCriteria(Product.class);
			List<Product> products = cr.list();
			return products;
		} catch (Exception e) {
			return Collections.emptyList();
		}
	}

	@Override
	public Product findById(Long id) {
		try (Session session = sessionFactory.openSession()) {
			Criteria cr = session.createCriteria(Product.class);
			cr.add(Restrictions.eq("id", id));
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
			return Collections.emptyList();
		}
	}

	@Override
	public List<Product> getProductsAndReviews() {
		try (Session session = sessionFactory.openSession()) {
			String sql = "select products.* from products inner join reviews where products.id = reviews.product_id;";
			SQLQuery query = session.createSQLQuery(sql);
			query.addEntity(Product.class);
			List<Product> productList = query.getResultList();
			return productList;
		}
	}

	@Override
	public void updateProduct(Product updatedProduct) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Product existingProduct = session.get(Product.class, updatedProduct.getId());
		if (existingProduct != null) {
			existingProduct.setName(updatedProduct.getName());
			existingProduct.setPrice(updatedProduct.getPrice());
			existingProduct.setDescription(updatedProduct.getDescription());
			existingProduct.setStockQuantity(updatedProduct.getStockQuantity());
			existingProduct.setImage(updatedProduct.getImage());
			session.update(existingProduct);
			session.getTransaction().commit();
		}

	}

	@Override
	public void deleteProductById(Long id) {
	    try (Session session = sessionFactory.openSession()) {
	    	 session.beginTransaction();
	         
	         String sql = "DELETE FROM Products WHERE id = :id";
	         int deletedCount = session.createSQLQuery(sql)
	                 .setParameter("id", id)
	                 .executeUpdate();
	         
	         session.getTransaction().commit();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}

}