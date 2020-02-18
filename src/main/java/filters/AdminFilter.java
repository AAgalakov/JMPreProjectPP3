package filters;

import model.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/admin/*")
public class AdminFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession(false);
        if (session == null) {
            response.sendRedirect("/");
        } else if (session.getAttribute("user") != null) {
            User user = (User) session.getAttribute("user");
            if (!"admin".equals(user.getRole())) {
                response.sendRedirect("/user");
            } else {
                filterChain.doFilter(request, response);
            }
        } else {
            response.sendRedirect("/");
        }
    }

    @Override
    public void destroy() {
    }
}
