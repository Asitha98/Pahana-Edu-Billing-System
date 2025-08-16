package com.pahanabilling.controller;

import com.pahanabilling.model.Customer;
import com.pahanabilling.service.CustomerService;
import com.pahanabilling.service.impl.CustomerServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(name = "AddCustomerController", urlPatterns = {"/customer/add"})
public class AddCustomerController extends HttpServlet {
    private final CustomerService service = new CustomerServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/addCustomer.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        Customer c = new Customer();
        c.setAccountNo(req.getParameter("account_no"));
        c.setName(req.getParameter("name"));
        c.setAddress(req.getParameter("address"));
        c.setPhone(req.getParameter("phone"));
        c.setUnits(Integer.parseInt(req.getParameter("units")));
        service.create(c);
        resp.sendRedirect(req.getContextPath() + "/customers");
    }
}
