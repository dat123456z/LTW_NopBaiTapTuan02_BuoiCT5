package vn.iostar.dao.impl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import vn.iostar.config.DBConnection;
import vn.iostar.dao.CategoryDao;
import vn.iostar.models.Category;

public class CategoryDaoImpl extends DBConnection implements CategoryDao {

    @Override
    public void insert(Category category) {
        String sql = "INSERT INTO dbo.Category (catename, icon) VALUES (?, ?)";
        try (Connection con = super.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, category.getCatename());
            ps.setString(2, category.getIcon());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void edit(Category category) {
        String sql = "UPDATE dbo.Category SET catename = ?, icon = ? WHERE cateid = ?";
        try (Connection con = super.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, category.getCatename());
            ps.setString(2, category.getIcon());
            ps.setInt(3, category.getCateid());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM dbo.Category WHERE cateid = ?";
        try (Connection con = super.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Category get(int id) {
        String sql = "SELECT cateid, catename, icon FROM dbo.Category WHERE cateid = ?";
        try (Connection con = super.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Category c = new Category();
                    c.setCateid(rs.getInt("cateid"));
                    c.setCatename(rs.getString("catename"));
                    c.setIcon(rs.getString("icon"));
                    return c;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Category> getAll() {
        List<Category> list = new ArrayList<>();
        String sql = "SELECT cateid, catename, icon FROM dbo.Category ORDER BY cateid";
        try (Connection con = super.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Category c = new Category();
                c.setCateid(rs.getInt("cateid"));
                c.setCatename(rs.getString("catename"));
                c.setIcon(rs.getString("icon"));
                list.add(c);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public Category get(String name) {
        String sql = "SELECT cateid, catename, icon FROM dbo.Category WHERE catename = ?";
        try (Connection con = super.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, name);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Category c = new Category();
                    c.setCateid(rs.getInt("cateid"));
                    c.setCatename(rs.getString("catename"));
                    c.setIcon(rs.getString("icon"));
                    return c;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Category> search(String keyword) {
        List<Category> list = new ArrayList<>();
        String sql = "SELECT cateid, catename, icon FROM dbo.Category WHERE catename LIKE ? ORDER BY cateid";
        try (Connection con = super.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, "%" + keyword + "%");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Category c = new Category();
                    c.setCateid(rs.getInt("cateid"));
                    c.setCatename(rs.getString("catename"));
                    c.setIcon(rs.getString("icon"));
                    list.add(c);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
