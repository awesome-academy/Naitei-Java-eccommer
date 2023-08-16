package app.service.Impl;

import java.util.Collections;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.model.User;
import app.repository.UserRepository;
import app.request.signUp;
import app.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	private static final Logger logger = Logger.getLogger(UserServiceImpl.class);

	@Autowired
	UserRepository userepository;
	

	@Override
	public void save(signUp signup) {
		try {
			User user = new User();
			user.setUsername(signup.getUsername());
			user.setPassword(signup.getPassword());
			user.setEmail(signup.getEmail());
			user.setAddress(signup.getAddress());
			user.setFirstName(signup.getFirstname());
			user.setLastName(signup.getLastname());
			user.setPhone(signup.getPhone());
			userepository.save(user);
		}
		catch(Exception e)
		{
			logger.error(e);
		}
		
	}
	
}
