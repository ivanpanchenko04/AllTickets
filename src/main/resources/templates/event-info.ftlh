<!DOCTYPE html>
<html>
<head>
    <title>AllTickets</title>
</head>
<body>
<h1>AllTickets</h1><hr>
<#list images as img>
    <img src="/images/${img.id}" height="150px"/>
</#list>
<p>${event.title}</p>
<p>${event.description}</p>
<p>${event.price} грн</p>
<p>${event.city}</p>
<hr>
<form action="/event/delete/${event.id}" method="post">
    <input type="hidden" name="_csrf" value="${_csrf.token}">
    <input type="submit" value="Delete event"/>
</form>
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
    h1, h3, h4 {
        color: #4CAF50;
    }
    hr {
        border: 0;
        height: 1px;
        background: #ddd;
    }
    a {
        color: #4CAF50;
        text-decoration: none;
    }
    a:hover {
        text-decoration: underline;
    }
    form {
        background: #fff;
        padding: 20px;
        border-radius: 5px;
        box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
    }
    input[type="text"], input[type="email"], input[type="password"], input[type="number"], input[type="file"] {
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
    .event-container, .image-container {
        margin-bottom: 20px;
    }
    .image-container img {
        margin-bottom: 10px;
    }
</style>