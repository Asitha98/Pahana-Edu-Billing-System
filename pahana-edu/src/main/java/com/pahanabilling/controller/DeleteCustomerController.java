package com.pahanabilling.controller;

import com.pahanabilling.service.CustomerService;
import com.pahanabilling.service.impl.CustomerServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(name = "DeleteCustomerController", urlPatterns = {"/customer/delete"})
public class DeleteCustomerController extends HttpServlet {
    private final CustomerService service = new CustomerServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        service.delete(id);
        resp.sendRedirect(req.getContextPath() + "/customers");
    }
}
