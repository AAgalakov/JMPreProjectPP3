package servlets;

import model.User;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/admin/table"})
public class TableServlet extends HttpServlet {

    private UserService userService = UserService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("users", userService.allUser());
        getServletContext().getRequestDispatcher("/WEB-INF/table.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String password = req.getParameter("password");
        String age = req.getParameter("age");
        String role = req.getParameter("role");
        if (!(name.isEmpty() | age.isEmpty() | password.isEmpty()) &&
                userService.addUser(new User(name, password, Integer.parseInt(age), role))) {
            req.setAttribute("users", userService.allUser());
            getServletContext().getRequestDispatcher("/WEB-INF/table.jsp").forward(req, resp);
        } else {
            req.setAttribute("URL", req.getRequestURL().toString());
            getServletContext().getRequestDispatcher("/WEB-INF/wrongName.jsp").forward(req, resp);
        }
    }
}
