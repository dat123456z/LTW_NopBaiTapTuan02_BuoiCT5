package vn.iostar.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;

import vn.iostar.config.DBConnection;
import vn.iostar.dao.UserDao;
import vn.iostar.dao.impl.UserDaoImpl;
import vn.iostar.models.User;
import vn.iostar.service.UserService;

public class UserServiceImpl implements UserService {
	UserDao userDao = new UserDaoImpl();

	@Override
	public User login(String username, String password) {
		User u = userDao.get(username.trim());
		if (u == null)
			return null;

		return u.getPassWord() != null && u.getPassWord().trim().equals(password.trim()) ? u : null;

	}

	@Override
	public User get(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean register(String username, String password, String email, String fullname, String phone) {
		if (userDao.checkExistUsername(username)) {
			return false;
		}
		long millis = System.currentTimeMillis();
		java.sql.Date date = new java.sql.Date(millis);
		userDao.insert(new User(0, email, username, fullname, password, null, 5, phone, date));
		return true;
	}

	public boolean checkExistEmail(String email) {
		return userDao.checkExistEmail(email);
	}

	public boolean checkExistUsername(String username) {
		return userDao.checkExistUsername(username);
	}

	@Override
	public boolean checkExistPhone(String phone) {
		return userDao.checkExistPhone(phone);
	}

	@Override
	public void insert(User user) {
		userDao.insert(user);
	}
	
	@Override
	public boolean updatePasswordByEmail(String email, String newPassword) {
	    String sql = "UPDATE Users SET passWord = ? WHERE email = ?";
	    try (Connection c = new DBConnection().getConnection();
	         PreparedStatement ps = c.prepareStatement(sql)) {

	        ps.setString(1, newPassword);
	        ps.setString(2, email);
	        return ps.executeUpdate() > 0;
	    } catch (Exception e) {
	        e.printStackTrace();
	        return false;
	    }
	}



}
