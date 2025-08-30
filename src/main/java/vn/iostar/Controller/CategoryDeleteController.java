package vn.iostar.Controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.iostar.service.CategoryService;
import vn.iostar.service.impl.CategoryServiceImpl;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = { "/admin/category/delete" })
public class CategoryDeleteController extends HttpServlet {
CategoryService cateService = new CategoryServiceImpl();
@Override
protected void doGet(HttpServletRequest req, HttpServletResponse resp)
throws ServletException, IOException {
String id = req.getParameter("id");
cateService.delete(Integer.parseInt(id));
resp.sendRedirect(req.getContextPath() + "/admin/category/list");
}
}