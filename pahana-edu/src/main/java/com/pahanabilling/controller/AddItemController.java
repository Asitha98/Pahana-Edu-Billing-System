package com.pahanabilling.controller;

import com.pahanabilling.model.Item;
import com.pahanabilling.service.ItemService;
import com.pahanabilling.service.impl.ItemServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.math.BigDecimal;

@WebServlet(name = "AddItemController", urlPatterns = {"/item/add"})
public class AddItemController extends HttpServlet {
    private final ItemService service = new ItemServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/addItem.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        Item i = new Item();
        i.setSku(req.getParameter("sku"));
        i.setName(req.getParameter("name"));
        i.setUnitPrice(new BigDecimal(req.getParameter("unit_price")));
        service.create(i);
        resp.sendRedirect(req.getContextPath() + "/items");
    }
}
