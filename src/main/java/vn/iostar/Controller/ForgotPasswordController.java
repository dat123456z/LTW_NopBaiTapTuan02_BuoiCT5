package vn.iostar.Controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import vn.iostar.service.UserService;
import vn.iostar.service.impl.UserServiceImpl;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = "/forgot")
public class ForgotPasswordController extends HttpServlet {

    private final UserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // Nếu đã đăng nhập thì đưa về waiting để phân quyền
        HttpSession session = req.getSession(false);
        if (session != null && session.getAttribute("account") != null) {
            resp.sendRedirect(req.getContextPath() + "/waiting");
            return;
        }
        // Mở trang quên mật khẩu
        req.getRequestDispatcher("/views/forgot.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String repassword = req.getParameter("repassword");

        // Validate cơ bản
        if (email == null || email.isBlank()
                || password == null || password.isBlank()
                || repassword == null || repassword.isBlank()) {
            req.setAttribute("alert", "Vui lòng nhập đầy đủ Email và mật khẩu mới.");
            req.getRequestDispatcher("/views/forgot.jsp").forward(req, resp);
            return;
        }

        if (!password.equals(repassword)) {
            req.setAttribute("alert", "Mật khẩu nhập lại không khớp.");
            req.getRequestDispatcher("/views/forgot.jsp").forward(req, resp);
            return;
        }

        // Kiểm tra email tồn tại
        if (!userService.checkExistEmail(email)) {
            req.setAttribute("alert", "Email không tồn tại trong hệ thống.");
            req.getRequestDispatcher("/views/forgot.jsp").forward(req, resp);
            return;
        }

        // Cập nhật mật khẩu
        boolean ok = userService.updatePasswordByEmail(email, password);
        if (ok) {
            // Có thể set message để hiện ở trang login
            req.getSession(true).setAttribute("flash_msg", "Đổi mật khẩu thành công. Vui lòng đăng nhập.");
            resp.sendRedirect(req.getContextPath() + "/login");
        } else {
            req.setAttribute("alert", "Lỗi hệ thống. Vui lòng thử lại sau.");
            req.getRequestDispatcher("/views/forgot.jsp").forward(req, resp);
        }
    }
}
