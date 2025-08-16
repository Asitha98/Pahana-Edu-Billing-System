package com.pahanabilling.controller;

import com.pahanabilling.model.Item;
import com.pahanabilling.service.ItemService;
import com.pahanabilling.service.impl.ItemServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "IteamController", urlPatterns = {"/items"})
public class IteamController extends HttpServlet {
    private final ItemService service = new ItemServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Item> list = service.list();
        req.setAttribute("items", list);
        req.getRequestDispatcher("/item.jsp").forward(req, resp);
    }
}
