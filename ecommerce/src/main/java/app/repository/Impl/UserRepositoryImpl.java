package app.repository.Impl;

import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import app.model.User;
import app.repository.UserRepository;

@Repository
public class UserRepositoryImpl implements UserRepository {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public User findUser(String username) {
		try (Session session = sessionFactory.openSession()) {
			Criteria criteria = session.createCriteria(User.class);
			criteria.add(Restrictions.eq("username", username));
			return (User) criteria.uniqueResult();
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public List<String> getUserRoles(String username) {
		try (Session session = sessionFactory.openSession()) {
			Criteria criteria = session.createCriteria(User.class);
			criteria.add(Restrictions.eq("username", username));
			criteria.createAlias("roles", "r");
			criteria.setProjection(Projections.property("r.name"));
			return criteria.list();
		}
	}

	@Override
	public void save(User user) {
		Session session = sessionFactory.openSession();
		session.save(user);
	}

}
