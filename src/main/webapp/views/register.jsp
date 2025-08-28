<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>
<!DOCTYPE html>
<html lang="vi">
<head>
  <meta charset="UTF-8">
  <title>Tạo tài khoản</title>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <!-- Bootstrap & Font Awesome -->
  <link rel="stylesheet"
        href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css">
  <link rel="stylesheet"
        href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

  <style>
    body { background: #f6f7fb; }
    .register-wrap {
      max-width: 520px; margin: 48px auto; background: #fff;
      border: 1px solid #e9ecef; border-radius: 8px; padding: 28px 28px 20px;
      box-shadow: 0 6px 18px rgba(0,0,0,.06);
    }
    h2 { font-size: 22px; margin-bottom: 18px; }
    .input-group-text { background: #f1f4f8; }
    .form-control::placeholder { color: #9aa4b2; }
    .btn-primary { background: #0d6efd; border-color: #0d6efd; }
    .muted-link { color: #6c757d; }
    .muted-link a { text-decoration: none; }
    .muted-link a:hover { text-decoration: underline; }
  </style>
</head>
<body>

<div class="register-wrap">
  <form action="${pageContext.request.contextPath}/register" method="post" accept-charset="UTF-8">
    <h2>Tạo tài khoản mới</h2>

    <c:if test="${not empty alert}">
      <div class="alert alert-danger py-2 mb-3">${alert}</div>
    </c:if>

    <!-- Username -->
    <div class="mb-3">
      <label class="form-label visually-hidden" for="username">Tài khoản</label>
      <div class="input-group">
        <span class="input-group-text"><i class="fa fa-user"></i></span>
        <input type="text" id="username" name="username" class="form-control"
               placeholder="Tài khoản" required>
      </div>
    </div>

    <!-- Full name -->
    <div class="mb-3">
      <label class="form-label visually-hidden" for="fullname">Họ tên</label>
      <div class="input-group">
        <span class="input-group-text"><i class="fa fa-id-card"></i></span>
        <input type="text" id="fullname" name="fullname" class="form-control"
               placeholder="Họ tên" required>
      </div>
    </div>

    <!-- Email -->
    <div class="mb-3">
      <label class="form-label visually-hidden" for="email">Email</label>
      <div class="input-group">
        <span class="input-group-text"><i class="fa fa-envelope"></i></span>
        <input type="email" id="email" name="email" class="form-control"
               placeholder="Nhập Email" required>
      </div>
    </div>

    <!-- Phone -->
    <div class="mb-3">
      <label class="form-label visually-hidden" for="phone">Số điện thoại</label>
      <div class="input-group">
        <span class="input-group-text"><i class="fa fa-phone"></i></span>
        <input type="tel" id="phone" name="phone" class="form-control"
               placeholder="Số điện thoại">
      </div>
    </div>

    <!-- Password -->
    <div class="mb-3">
      <label class="form-label visually-hidden" for="password">Mật khẩu</label>
      <div class="input-group">
        <span class="input-group-text"><i class="fa fa-lock"></i></span>
        <input type="password" id="password" name="password" class="form-control"
               placeholder="Mật khẩu" required>
      </div>
    </div>

    <!-- Confirm Password (optional, phía server tự kiểm tra nếu bạn dùng) -->
    <div class="mb-4">
      <label class="form-label visually-hidden" for="repassword">Nhập lại mật khẩu</label>
      <div class="input-group">
        <span class="input-group-text"><i class="fa fa-lock"></i></span>
        <input type="password" id="repassword" name="repassword" class="form-control"
               placeholder="Nhập lại mật khẩu">
      </div>
    </div>

    <button class="btn btn-primary w-100 py-2" type="submit">Tạo tài khoản</button>

    <p class="text-center mt-3 mb-0 muted-link">
      Nếu bạn đã có tài khoản? <a href="${pageContext.request.contextPath}/login">Đăng nhập</a>
    </p>
  </form>
</div>

</body>
</html>
