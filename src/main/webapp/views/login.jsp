
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Login</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <style>
        body {
            background: #f8f9fa;
            font-family: Arial, sans-serif;
        }
        .login-container {
            width: 400px;
            margin: 80px auto;
            background: #fff;
            padding: 30px;
            border: 1px solid #e0e0e0;
            box-shadow: 0px 0px 10px rgba(0,0,0,0.05);
        }
        .login-container h2 {
            text-align: center;
            margin-bottom: 25px;
            color: #555;
        }
        .form-control {
            border-radius: 0;
            height: 40px;
        }
        .btn-login {
            width: 100%;
            border-radius: 0;
            background: #009fe3;
            color: #fff;
            height: 40px;
        }
        .btn-login:hover {
            background: #007bbd;
        }
        .login-footer {
            text-align: center;
            margin-top: 20px;
            color: #666;
        }
        .login-footer a {
            color: #009fe3;
            text-decoration: none;
        }
        .login-footer a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>

<div class="login-container">
    <h2>Login in System</h2>
    <form action="login" method="post">
        <div style="margin-bottom:15px;">
            <input type="text" class="form-control" name="username" placeholder="username">
        </div>
        <div style="margin-bottom:15px;">
            <input type="password" class="form-control" name="password" placeholder="password">
        </div>
        <div class="row" style="margin-bottom:15px;">
            <div class="col-xs-6">
                <label>
                    <input type="checkbox" name="remember"> Remember me
                </label>
            </div>
            <div class="col-xs-6 text-right">
                <a href="#">Forget Password?</a>
            </div>
        </div>
        <button type="submit" class="btn btn-login">Login</button>
    </form>
    <div class="login-footer">
        If you don't have an account <a href="register.jsp">Sign up</a>
    </div>
</div>

</body>
</html>