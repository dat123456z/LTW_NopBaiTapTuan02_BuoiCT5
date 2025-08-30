<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="vi">
<head>
  <meta charset="UTF-8">
  <title>Admin Home</title>
  <link rel="stylesheet"
        href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
</head>
<body>
<div class="container" style="margin-top:40px">
  <h2>Chào mừng Admin!</h2>
  <p>Xin chào, <strong>${username}</strong></p>

  <p>
    <a href="${pageContext.request.contextPath}/logout" class="btn btn-danger">
      Đăng xuất
    </a>
  </p>
</div>
</body>
</html>
