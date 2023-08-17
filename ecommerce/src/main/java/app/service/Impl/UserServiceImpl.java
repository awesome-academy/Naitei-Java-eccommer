package app.service.Impl;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import app.model.Role;
import app.model.User;
import app.repository.UserRepository;
import app.request.signUp;
import app.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	private static final Logger logger = Logger.getLogger(UserServiceImpl.class);

	@Autowired
	UserRepository userepository;
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public void save(signUp signup) {
		try {
			User user = new User();
			user.setUsername(signup.getUsername());
			user.setPassword(passwordEncoder.encode(signup.getPassword()));
			user.setEmail(signup.getEmail());
			user.setAddress(signup.getAddress());
			user.setFirstName(signup.getFirstname());
			user.setLastName(signup.getLastname());
			user.setPhone(signup.getPhone());
			System.out.println("User: " + user);
			Role userRole = userepository.findRoleByName("USER");
			Set<Role> roles = new HashSet<>();
			roles.add(userRole);
			user.setRoles(roles);
			System.out.println("User: " + user);
			userepository.save(user);
		} catch (Exception e) {
			logger.error(e);
		}

	}

}
