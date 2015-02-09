package nba.com.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nba.com.dao.UserDAO;
import nba.com.entities.User;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserDAO userDAO;

	@Override
	public User findUser(String name) {
		return null;
	}

	@Override
	public void store(User user) {
		userDAO.save(user);
	}

}
