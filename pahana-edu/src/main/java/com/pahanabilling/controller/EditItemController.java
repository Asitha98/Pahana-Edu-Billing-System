package com.pahanabilling.controller;

import com.pahanabilling.model.Item;
import com.pahanabilling.service.ItemService;
import com.pahanabilling.service.impl.ItemServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Optional;

@WebServlet(name = "EditItemController", urlPatterns = {"/item/edit"})
public class EditItemController extends HttpServlet {
    private final ItemService service = new ItemServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        Optional<Item> item = service.get(id);
        req.setAttribute("item", item.orElse(null));
        req.getRequestDispatcher("/editItem.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        Item i = new Item();
        i.setItemId(Integer.parseInt(req.getParameter("id")));
        i.setSku(req.getParameter("sku"));
        i.setName(req.getParameter("name"));
        i.setUnitPrice(new BigDecimal(req.getParameter("unit_price")));
        service.update(i);
        resp.sendRedirect(req.getContextPath() + "/items");
    }
}
