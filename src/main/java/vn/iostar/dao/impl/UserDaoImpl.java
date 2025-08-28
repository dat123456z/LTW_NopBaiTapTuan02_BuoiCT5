package vn.iostar.dao.impl;

import vn.iostar.config.DBConnection;
import vn.iostar.dao.UserDao;
import vn.iostar.models.User;

import java.sql.*;

public class UserDaoImpl implements UserDao {
	
	public Connection conn = null;
	public PreparedStatement ps = null;
	public ResultSet rs = null;


    @Override
    public User get(String username) {
        final String sql =
            "SELECT TOP 1 id, email, userName, fullName, passWord, avatar, roleid, phone, createdDate " +
            "FROM [LTW].[dbo].[Users] WHERE userName = ?";

        try (Connection conn = new DBConnection().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, username.trim());

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    User u = new User();
                    u.setId(rs.getInt("id"));
                    u.setEmail(rs.getString("email"));
                    u.setUserName(rs.getString("userName"));
                    u.setFullName(rs.getString("fullName"));
                    u.setPassWord(rs.getString("passWord")); 
                    u.setAvatar(rs.getString("avatar"));
                    u.setRoleid(rs.getInt("roleid"));
                    u.setPhone(rs.getString("phone"));
                    u.setCreatedDate(rs.getDate("createdDate"));
                    return u;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        return null;
    }
    
    @Override
    public void insert(User user) {
    String sql = "INSERT INTO [Users](email, username, fullname, password, avatar, roleid,phone,createddate) VALUES (?,?,?,?,?,?,?,?)";
    try {
    conn = new DBConnection().getConnection();
    ps = conn.prepareStatement(sql);
    ps.setString(1, user.getEmail());
    ps.setString(2, user.getUserName());
    ps.setString(3, user.getFullName());
    ps.setString(4, user.getPassWord());
    ps.setString(5, user.getAvatar());
    ps.setInt(6,user.getRoleid());
    ps.setString(7,user.getPhone());
    ps.setDate(8, user.getCreatedDate());
    ps.executeUpdate();
    } catch (Exception e) {e.printStackTrace();}
    }
    
    @Override
    public boolean checkExistEmail(String email) {
    boolean duplicate = false;
    String query = "select * from [Users] where email = ?";
    try {
    conn = new DBConnection().getConnection();
    ps = conn.prepareStatement(query);
    ps.setString(1, email);
    rs = ps.executeQuery();
    if (rs.next()) {
    duplicate = true;
    }
    ps.close();
    conn.close();
    } catch (Exception ex) {}
    return duplicate;
    }
    
    @Override
    public boolean checkExistUsername(String username) {
    boolean duplicate = false;
    String query = "select * from [Users] where username = ?";
    try {
    conn = new DBConnection().getConnection();
    ps = conn.prepareStatement(query);
    ps.setString(1, username);
    rs = ps.executeQuery();
    if (rs.next()) {
    duplicate = true;
    }
    ps.close();
    conn.close();
    } catch (Exception ex) {}
    return duplicate;
    }

	@Override
	public boolean checkExistPhone(String phone) {
		// TODO Auto-generated method stub
		return false;
	}
}
