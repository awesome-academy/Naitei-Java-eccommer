package app.repository.Impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import app.model.Category;
import app.repository.CategoryRepository;

@Repository
public class CategoryRepositpryImpl implements CategoryRepository {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Category> findAll() {
		try (Session session = sessionFactory.openSession()) {
//			List<Category> categories = session.createQuery("FROM Categories", Category.class).list();
			Criteria cr = session.createCriteria(Category.class);
			List<Category> categories = cr.list();
			return categories;
		} 
	}

	@Override
	public Category findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
