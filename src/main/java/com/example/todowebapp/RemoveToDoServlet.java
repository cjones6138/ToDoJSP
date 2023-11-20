package com.example.todowebapp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.Arrays;

@WebServlet(name = "RemoveToDoServlet", value = "/RemoveToDoServlet")
public class RemoveToDoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String task = request.getParameter("taskComplete");

        try {
            Utils.removeFromDB(task);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);

    }
}