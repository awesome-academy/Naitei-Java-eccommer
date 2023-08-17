package app.repository;

import java.util.List;

import app.model.Role;
import app.model.User;

public interface UserRepository {
	public User findUser(String username);
	
	public List<String> getUserRoles(String username);
	
	public void save(User user);
	
	public Role findRoleByName(String name);
}
