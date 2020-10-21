<%@page import="ru.appline.logic.Model" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Title</title>
  </head>
  <body>
  <h1>Home Page</h1>
    Enter user ID: (0 - get all users)
  <br/>
  Available: <% Model model = Model.getInstance();
  out.print(model.getFromList().size());
  %>

  <form method="get" action="get">
    <label for="user-id">
      <input type="text" name="user-id" id="user-id">
    </label>
    <button type="submit">SEARCH</button>
  </form>
  <a href="addUser.html">Create New User</a>

  </body>
</html>
