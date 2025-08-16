package com.pahanabilling.controller;

import com.pahanabilling.model.User;
import com.pahanabilling.service.UserService;
import com.pahanabilling.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(name = "AddCashierController", urlPatterns = {"/admin/cashier/add"})
public class AddCashierController extends HttpServlet {
    private final UserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/admin/addCashier.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        User u = new User(username, password, "cashier");
        userService.createCashier(u); // implement this in your UserService
        resp.sendRedirect(req.getContextPath() + "/admin");
    }
}
