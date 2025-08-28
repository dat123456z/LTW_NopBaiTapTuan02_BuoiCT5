<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Đăng nhập</title>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .login-container {
            max-width: 400px;
            margin: 50px auto;
            padding: 20px;
            border: 1px solid #ddd;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
        }
        .alert-danger {
            color: #721c24;
            background-color: #f8d7da;
            border-color: #f5c6cb;
            padding: 10px;
            border-radius: 4px;
            margin-bottom: 15px;
        }
        .login-input {
            margin-bottom: 15px;
        }
        .form-control {
            height: 45px;
        }
        .btn-login {
            width: 100%;
            height: 45px;
            font-size: 16px;
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="login-container">
           <form action="${pageContext.request.contextPath}/login" method="post">
                <h2 class="text-center">Đăng nhập tài khoản</h2>
                
                <c:if test="${alert != null}">
                    <div class="alert alert-danger">${alert}</div>
                </c:if>
                
                <div class="login-input">
                    <label class="input">
                        <div class="input-group">
                            <span class="input-group-addon">
                                <i class="fa fa-user"></i>
                            </span>
                            <input type="text" placeholder="Tài khoản" name="username" class="form-control" required>
                        </div>
                    </label>
                </div>
                
                <div class="login-input">
                    <label class="input">
                        <div class="input-group">
                            <span class="input-group-addon">
                                <i class="fa fa-lock"></i>
                            </span>
                            <input type="password" placeholder="Mật khẩu" name="password" class="form-control" required>
                        </div>
                    </label>
                </div>
                
                <div class="login-input">
                    <label class="checkbox">
                        <input type="checkbox" name="remember" value="on">
                        <span>Ghi nhớ đăng nhập</span>
                    </label>
                </div>
                
                <button type="submit" class="btn btn-primary btn-login">Đăng nhập</button>
                
                <div class="text-center" style="margin-top: 15px;">
                    <a href="${pageContext.request.contextPath}/register">Chưa có tài khoản? Đăng ký ngay</a>
                </div>
            </form>
        </div>
    </div>
</body>
</html>