<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>
</head>
<body>
<h2>Registration form</h2>
<form action="/register" method="post">
    <div class="container">
        <label ><b>Username</b></label>
        <input type="text" pattern="^[a-zA-Z][a-zA-Z0-9-_\.]{3,19}$"
               placeholder="Enter Username" name="username" required>
        <br>
        Username can contain only english letters or numbers or symbols: "_", "-", "." (min - 4, max - 20),
        should start from letter
        <br>

        <label ><b>Password </b></label>
        <input type="password" pattern="^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[A-Za-z\d]{8,}$"
               placeholder="Enter Password" name="password" required>
        <br>
        Password requirements:
        - Minimum 8 characters
        - At least one uppercase character
        - At least one lowercase character
        - At least one digit
        <br>

        <label ><b>Confirm password</span></b></label>
        <input type="password" pattern="^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[A-Za-z\d]{8,}$"
               placeholder="Confirm password" name="confirmedPassword" required>
        <br>

        <label ><b>First name</span></b></label>
        <input type="text" placeholder="First name" name="firstName" required>
        <br>

        <label ><b>Last name</span></b></label>
        <input type="text" placeholder="Last name" name="lastName" required>
        <br>

        <label ><b>Email</b></label>
        <input type="email" pattern="^[-\w.]+@([A-z0-9][-A-z0-9]+\.)+[A-z]{2,4}$"
               placeholder="Enter Email" name="email" required>
        <br>

        <label ><b>Phone Number</b></label>
        <input type="text" pattern="^(\s*)?(\+)?([- _():=+]?\d[- _():=+]?){10,14}(\s*)?$"
               placeholder="Enter Phone Number" name="phoneNumber" required>
        <br>

        <button type="submit">Register</button>
    </div>

</form>
</body>
</html>
