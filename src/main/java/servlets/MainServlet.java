package servlets;

import model.User;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/"})
public class MainServlet extends HttpServlet {

    private UserService userService = UserService.getInstance();

    private User user = new User();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("users", userService.allUser());
        getServletContext().getRequestDispatcher("/WEB-INF/main.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String age = req.getParameter("age");
//        String role = req.getParameter("role");
        if (!(name.isEmpty() | age.isEmpty()) ){//| role.isEmpty()) {
            user.setName(name);
            user.setAge(Integer.parseInt(age));
//            user.setRole(role);
            userService.addUser(user);
        }
        req.setAttribute("users", userService.allUser());
        getServletContext().getRequestDispatcher("/WEB-INF/main.jsp").forward(req, resp);
    }
}
