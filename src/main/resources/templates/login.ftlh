<!DOCTYPE html>
<html>
<head>
    <title>AllTickets</title>
</head>
<body>
<h1>AllTickets</h1><hr>
<h4>Login</h4>
<form action="/login" method="post">
    <input type="email" name="username" placeholder="Email"><br><br>
    <input type="password" name="password" placeholder="Password"><br><br>
    <input type="hidden" name="_csrf" value="${_csrf.token}">
    <input type="submit" value="Log in"/>
</form>
<a href="/registration">Registration</a>
</body>
</html>

<style>
    body {
        font-family: Arial, sans-serif;
        background-color: #f9f9f9;
        color: #333;
        margin: 0;
        padding: 20px;
    }
    h1 {
        color: #4CAF50;
    }
    h4 {
        color: #333;
    }
    hr {
        border: 0;
        height: 1px;
        background: #ddd;
    }
    form {
        background: #fff;
        padding: 20px;
        border-radius: 5px;
        box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
    }
    input[type="email"], input[type="password"] {
        width: calc(100% - 22px);
        padding: 10px;
        margin-bottom: 10px;
        border: 1px solid #ccc;
        border-radius: 4px;
    }
    input[type="submit"] {
        background: #4CAF50;
        color: white;
        padding: 10px 20px;
        border: none;
        border-radius: 4px;
        cursor: pointer;
    }
    input[type="submit"]:hover {
        background: #45a049;
    }
    a {
        color: #4CAF50;
        text-decoration: none;
    }
    a:hover {
        text-decoration: underline;
    }
</style>