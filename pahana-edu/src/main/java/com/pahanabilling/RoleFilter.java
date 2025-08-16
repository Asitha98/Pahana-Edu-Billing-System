package com.pahanabilling;

import com.pahanabilling.model.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.Set;

/**
 * Example role guard. Adjust patterns below for your admin-only actions.
 */
@WebFilter(
    filterName = "RoleFilter",
    urlPatterns = {
        "/admin/cashier/*",  // e.g., add cashier
        "/item/delete",      // delete item
        "/customer/delete"   // delete customer
    }
)
public class RoleFilter implements Filter {

    private static final Set<String> ADMIN_ROLES = Set.of("admin", "ADMIN");

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest  req  = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        HttpSession session = req.getSession(false);
        User user = (session != null) ? (User) session.getAttribute("user") : null;

        if (user == null || !ADMIN_ROLES.contains(String.valueOf(user.getRole()).toLowerCase())) {
            resp.sendError(HttpServletResponse.SC_FORBIDDEN, "Admin only");
            return;
        }
        chain.doFilter(request, response);
    }
}
