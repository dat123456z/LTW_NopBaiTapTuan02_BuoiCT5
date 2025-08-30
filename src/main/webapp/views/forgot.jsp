<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="vi">
<head>
  <meta charset="UTF-8">
  <title>Quên mật khẩu</title>
  <link rel="stylesheet"
        href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <link rel="stylesheet"
        href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
  <style>
    .wrap{max-width:420px;margin:60px auto;background:#fff;border:1px solid #e9ecef;
          border-radius:8px;padding:24px;box-shadow:0 6px 18px rgba(0,0,0,.06)}
    .input-group-addon{background:#f1f4f8}
  </style>
</head>
<body>
<div class="container">
  <div class="wrap">
    <form action="${pageContext.request.contextPath}/forgot" method="post">
      <h3 class="text-center">Đặt lại mật khẩu</h3>

      <c:if test="${not empty alert}">
        <div class="alert alert-danger">${alert}</div>
      </c:if>

      <div class="form-group">
        <label class="control-label" for="email">Email</label>
        <div class="input-group">
          <span class="input-group-addon"><i class="fa fa-envelope"></i></span>
          <input type="email" id="email" name="email" class="form-control" placeholder="Nhập email đã đăng ký" required>
        </div>
      </div>

      <div class="form-group">
        <label class="control-label" for="password">Mật khẩu mới</label>
        <div class="input-group">
          <span class="input-group-addon"><i class="fa fa-lock"></i></span>
          <input type="password" id="password" name="password" class="form-control" placeholder="Mật khẩu mới" required>
        </div>
      </div>

      <div class="form-group">
        <label class="control-label" for="repassword">Nhập lại mật khẩu</label>
        <div class="input-group">
          <span class="input-group-addon"><i class="fa fa-lock"></i></span>
          <input type="password" id="repassword" name="repassword" class="form-control" placeholder="Nhập lại mật khẩu" required>
        </div>
      </div>

      <button type="submit" class="btn btn-primary btn-block">Xác nhận đổi mật khẩu</button>

      <p class="text-center" style="margin-top:12px">
        <a href="${pageContext.request.contextPath}/login">Quay về đăng nhập</a>
      </p>
    </form>
  </div>
</div>
</body>
</html>
