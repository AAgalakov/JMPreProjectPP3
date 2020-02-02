package servlets;

import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/userUpdatedToBD"})
public class UpdatedUserToBDServlet extends HttpServlet {

    private UserService userService = UserService.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long id = Long.parseLong(req.getParameter("id"));
        String name = req.getParameter("name");
        String age = req.getParameter("age");
        if (name.isEmpty() && age.isEmpty()) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        } else {
            if (userService.updateUser(id, name, age)) {
                resp.setStatus(HttpServletResponse.SC_OK);
                req.setAttribute("users", userService.allUser());
                resp.sendRedirect("/");
            }
        }
    }
}
