package app.repository.Impl;


import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import app.model.Blog;
import app.repository.BlogRepository;

@Repository
public class BlogRepositoryImpl implements BlogRepository{
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Blog> findAll() {
		Session session = sessionFactory.openSession();
		Criteria cr = session.createCriteria(Blog.class);
		List<Blog> blogs = cr.list();
		return blogs;		
	}

	@Override
	public Blog findById(Long id) {
		Session session = sessionFactory.openSession();
			Criteria cr = session.createCriteria(Blog.class);
			cr.add(Restrictions.eq("id",id));
			Blog blog = (Blog) cr.uniqueResult();
			return blog;
		} 
}
