package com.pahanabilling.controller;

import com.pahanabilling.service.BillService;
import com.pahanabilling.service.impl.BillServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.math.BigDecimal;

@WebServlet(name = "GenrateBillController", urlPatterns = {"/billing/generate"})
public class GenrateBillController extends HttpServlet {
    private final BillService billService = new BillServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        int customerId = Integer.parseInt(req.getParameter("customer_id"));
        int units = Integer.parseInt(req.getParameter("units"));
        BigDecimal rate = new BigDecimal(req.getParameter("rate_per_unit"));
        billService.generateBill(customerId, units, rate);
        resp.sendRedirect(req.getContextPath() + "/bills");
    }
}
