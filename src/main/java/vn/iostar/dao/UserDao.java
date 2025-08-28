package vn.iostar.dao;

import vn.iostar.models.User;

public interface UserDao {
	User get(String username);
}
