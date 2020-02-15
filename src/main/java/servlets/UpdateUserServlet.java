package servlets;

import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/admin/userUpdate"})
public class UpdateUserServlet extends HttpServlet {

    private UserService userService = UserService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long id = Long.parseLong(req.getParameter("id"));
        req.setAttribute("user", userService.getUserById(id));
        getServletContext().getRequestDispatcher("/WEB-INF/updateUser.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long id = Long.parseLong(req.getParameter("id"));
        String name = req.getParameter("name");
        String password = req.getParameter("password");
        String age = req.getParameter("age");
        String role = req.getParameter("role");
        if (name.isEmpty() | age.isEmpty() | password.isEmpty()) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        } else {
            if (userService.updateUser(id, name, password, age, role)) {
                resp.setStatus(HttpServletResponse.SC_OK);
                req.setAttribute("users", userService.allUser());
                resp.sendRedirect("/admin/table");
            } else {
                req.setAttribute("id", id);
                getServletContext().getRequestDispatcher("/WEB-INF/wrongName.jsp").forward(req, resp);
            }
        }
    }
}
