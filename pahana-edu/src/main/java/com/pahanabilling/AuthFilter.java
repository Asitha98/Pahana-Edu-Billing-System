package com.pahanabilling;

import com.pahanabilling.model.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.*;
import java.io.IOException;

/**
 * Guards authenticated areas. If no session user -> redirect to /login.
 */
@WebFilter(
    filterName = "AuthFilter",
    urlPatterns = {
        "/admin",
        "/customers", "/customer/*",
        "/items", "/item/*",
        "/bills", "/billing", "/billing/*"
    }
)
public class AuthFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest  req  = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        HttpSession session = req.getSession(false);
        User user = (session != null) ? (User) session.getAttribute("user") : null;

        if (user == null) {
            // Preserve where the user wanted to go (optional)
            String next = req.getRequestURI() + (req.getQueryString() != null ? "?" + req.getQueryString() : "");
            resp.sendRedirect(req.getContextPath() + "/login?next=" + resp.encodeRedirectURL(next));
            return;
        }
        chain.doFilter(request, response);
    }
}
