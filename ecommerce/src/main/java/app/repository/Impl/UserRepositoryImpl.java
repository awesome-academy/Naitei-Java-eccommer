package app.repository.Impl;

import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
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
			String hql = "FROM User u WHERE u.username = :username";
			TypedQuery<User> query = session.createQuery(hql, User.class);
			query.setParameter("username", username);
			return query.getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public List<String> getUserRoles(String username) {
		try (Session session = sessionFactory.openSession()) {
			String hql = "SELECT r.name FROM User u JOIN u.roles r WHERE u.username = :username";
			return session.createQuery(hql, String.class).setParameter("username", username).getResultList();
		}
	}

}
