package com.pahanabilling.controller;

import com.pahanabilling.model.Bill;
import com.pahanabilling.service.BillService;
import com.pahanabilling.service.impl.BillServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "BillListController", urlPatterns = {"/bills"})
public class BillListController extends HttpServlet {
    private final BillService billService = new BillServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Bill> bills = billService.list();
        req.setAttribute("bills", bills);
        req.getRequestDispatcher("/bills.jsp").forward(req, resp);
    }
}
