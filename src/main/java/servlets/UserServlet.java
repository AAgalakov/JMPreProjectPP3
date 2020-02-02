package servlets;

import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/userDeleteAll/"})
public class UserServlet extends HttpServlet {

    private UserService userService = UserService.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        userService.deleteAllUser();//
        resp.setStatus(HttpServletResponse.SC_OK);
        getServletContext().getRequestDispatcher("/WEB-INF/main.jsp").forward(req, resp);
    }
}
