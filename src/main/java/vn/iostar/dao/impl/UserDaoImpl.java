package vn.iostar.dao.impl;

import vn.iostar.config.DBConnection;
import vn.iostar.dao.UserDao;
import vn.iostar.models.User;

import java.sql.*;

public class UserDaoImpl implements UserDao {

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
}
