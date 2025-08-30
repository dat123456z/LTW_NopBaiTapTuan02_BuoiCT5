package vn.iostar.Controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = "/logout")
public class LogoutController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        // Hủy session
        HttpSession session = req.getSession(false);
        if (session != null) {
            session.invalidate();
        }

        // Xóa cookie "username" (nếu bạn dùng để remember login)
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie ck : cookies) {
                if ("username".equals(ck.getName())) {
                    ck.setValue(null);
                    ck.setMaxAge(0); // hết hạn ngay lập tức
                    ck.setPath(req.getContextPath()); // đảm bảo trùng path đã set trước đó
                    resp.addCookie(ck);
                }
            }
        }

        // Quay về trang login
        resp.sendRedirect(req.getContextPath() + "/login");
    }
}
