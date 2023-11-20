<%@ page import="com.example.todowebapp.Utils" %>
<%@page import="java.sql.*"%>
<%@ page import="static com.example.todowebapp.Utils.*" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    try {
        Utils.createTable();
    } catch (Exception e) {
        throw new RuntimeException(e);
    }
%>

<!DOCTYPE html>
<html>
<head>
    <title>JSP - ToDo List</title>
</head>
<body>

<h1>To Do List</h1>
<hr>

<form action="/AddToDoServlet" method="post">

    <label for="task">Task: </label>
    <input type="text" name="task" id="task" value="">

    <button type="submit" name="taskAdd" id="taskAdd">+Add</button>

</form>
<hr>

<form action="/RemoveToDoServlet" method="post">
    <ul>
        <%
            try{
                ResultSet result = Utils.displayDB();

                if(result.next()==false){ %>

        <p>All tasks complete!</p>

        <%
        }else{
            do{%>

        <li>
            <button type="submit" name="taskComplete" value="<%=result.getString(1)%>">
                &#x2713;
            </button>
            <%= result.getString(2) %>
        </li>
        <hr><%
                }while (result.next());

            }
        }catch (Exception e){
            e.getStackTrace();
        }%>
    </ul>
</form>
</body>
</html>