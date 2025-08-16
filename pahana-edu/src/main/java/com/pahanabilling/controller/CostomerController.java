package com.pahanabilling.controller;

import com.pahanabilling.model.Customer;
import com.pahanabilling.service.CustomerService;
import com.pahanabilling.service.impl.CustomerServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "CostomerController", urlPatterns = {"/customers"})
public class CostomerController extends HttpServlet {
    private final CustomerService service = new CustomerServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Customer> list = service.list();
        req.setAttribute("customers", list);
        req.getRequestDispatcher("/customer.jsp").forward(req, resp);
    }
}
