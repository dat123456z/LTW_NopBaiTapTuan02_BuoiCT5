package vn.iostar.Controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.iostar.models.User;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = "/admin/home")
public class AdminHomeController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("account") == null) {
            // chưa login thì đá về trang login
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        User u = (User) session.getAttribute("account");
        req.setAttribute("username", u.getUserName());

        // forward sang JSP
        req.getRequestDispatcher("/views/admin/home.jsp").forward(req, resp);
    }
}
