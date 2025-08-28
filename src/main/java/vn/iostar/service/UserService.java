package vn.iostar.service;

import vn.iostar.models.User;

public interface UserService {
	User login(String username, String password);
	User get(String username);
}
