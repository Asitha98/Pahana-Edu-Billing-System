package com.pahanabilling.controller;

import com.pahanabilling.service.ItemService;
import com.pahanabilling.service.impl.ItemServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(name = "DeleteItemController", urlPatterns = {"/item/delete"})
public class DeleteItemController extends HttpServlet {
    private final ItemService service = new ItemServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        service.delete(id);
        resp.sendRedirect(req.getContextPath() + "/items");
    }
}
