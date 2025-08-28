package vn.iostar.service.impl;

import vn.iostar.dao.UserDao;
import vn.iostar.dao.impl.UserDaoImpl;
import vn.iostar.models.User;
import vn.iostar.service.UserService;

public class UserServiceImpl implements UserService {
	UserDao userDao = new UserDaoImpl();

	@Override
	public User login(String username, String password) {
	    User u = userDao.get(username.trim());
	    if (u == null) return null;

	    return u.getPassWord() != null && u.getPassWord().trim().equals(password.trim())
	           ? u : null;

	}


	@Override
	public User get(String username) {
		// TODO Auto-generated method stub
		return null;
	}
}
