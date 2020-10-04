<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login Form</title>
</head>
<body>
<h2>Login form</h2>
<form action="/login" method="post">
    <div class="container">
        <label><b><span text="Login"></span></b></label>
        <input type="text" placeholder="Enter username" name="username" required>
        <br>

        <label><b><span text="Password"></span> </b></label>
        <input type="password" placeholder="Enter password" name="password" required>
        <br>
        <button type="submit"><span text="Login"></span>Login</button>
    </div>
</form>
</body>
</html>
