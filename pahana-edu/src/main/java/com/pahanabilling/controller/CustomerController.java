package com.pahanabilling.controller;

import com.pahanabilling.dao.CustomerDAO;
import com.pahanabilling.model.Customer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/customer")
public class CustomerController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accountNo = request.getParameter("accountNo");
        String name = request.getParameter("name");
        String address = request.getParameter("address");
        String phone = request.getParameter("phone");
        int units = Integer.parseInt(request.getParameter("units"));

        Customer c = new Customer();
        c.setAccountNo(accountNo);
        c.setName(name);
        c.setAddress(address);
        c.setPhone(phone);
        c.setUnits(units);

        try {
            CustomerDAO.insertCustomer(c);
        } catch (Exception e) {
            e.printStackTrace();
        }

        response.sendRedirect("viewCustomers.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            List<Customer> customerList = CustomerDAO.getAllCustomers();
            request.setAttribute("customers", customerList);
        } catch (Exception e) {
            e.printStackTrace();
        }

        request.getRequestDispatcher("viewCustomers.jsp").forward(request, response);
    }
}
