<!DOCTYPE html>
<html>
<head>
    <title>AllTickets</title>
</head>
<body>
<h1>AllTickets | <a href="/login">Log in</a></h1><hr>
<#list events as event>
    <div>
        <p><b>${event.title}</b> ${event.city} | <a href="/event/${event.id}">Details</a></p>
    </div>
<#else>
    <h3>There`s no events</h3>
</#list>
<hr>
<h3>Add new event</h3>
<form action="/event/create" method="post" enctype="multipart/form-data">
    <input type="text" name="title" placeholder="Title"/><br><br>
    <input type="text" name="description" placeholder="Description"/><br><br>
    <input type="number" name="price" placeholder="Price"/><br><br>
    <input type="text" name="city" placeholder="City"/><br><br>
    <input type="file" name="file1" placeholder="Image 1"/><br><br>
    <input type="file" name="file2" placeholder="Image 2"/><br><br>
    <input type="file" name="file3" placeholder="Image 3"/><br><br>
    <input type="hidden" name="_csrf" value="${_csrf.token}">
    <input type="submit" value="Add event"/>
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
    h1 {
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
    input[type="text"], input[type="number"], input[type="file"] {
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
    .event-container {
        margin-bottom: 20px;
    }
</style>