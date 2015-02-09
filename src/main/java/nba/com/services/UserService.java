package nba.com.services;

import nba.com.entities.User;

public interface UserService {
	User findUser(String name);
	void store(User user);
}
