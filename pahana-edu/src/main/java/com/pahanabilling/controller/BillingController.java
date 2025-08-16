package com.pahanabilling.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(name = "BillingController", urlPatterns = {"/billing"})
public class BillingController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // shows quick generate form and maybe filters
        req.getRequestDispatcher("/bills.jsp").forward(req, resp);
    }
}
