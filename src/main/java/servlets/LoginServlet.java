package servlets;

import model.User;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = {"/"})
public class LoginServlet extends HttpServlet {

    private UserService userService = UserService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session == null) {
            getServletContext().getRequestDispatcher("/WEB-INF/loginPage.jsp").forward(req, resp);
        } else if (session.getAttribute("user") != null) {
            User user = (User) session.getAttribute("user");
            if ("admin".equals(user.getRole())) {
                resp.sendRedirect("/admin/table");
            } else if ("user".equals(user.getRole()))
                resp.sendRedirect("/user");
        } else {
            getServletContext().getRequestDispatcher("/WEB-INF/loginPage.jsp").forward(req, resp);
        }
    }

//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        HttpSession session = req.getSession(false);
//        if (session == null) {
//            getServletContext().getRequestDispatcher("/WEB-INF/loginPage.jsp").forward(req, resp);
//        } else if (session.getAttribute("user") == null) {
//            getServletContext().getRequestDispatcher("/WEB-INF/loginPage.jsp").forward(req, resp);
//        } else {
//            User user = (User) session.getAttribute("user");
//            if ("admin".equals(user.getRole())) {
//                resp.sendRedirect("/admin/table");
//            } else if ("user".equals(user.getRole()))
//                resp.sendRedirect("/user");
//        }
//    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String password = req.getParameter("password");
        if (userService.validateUser(name, password)) {
            HttpSession session = req.getSession();
            User user = userService.getUserByName(name);
            session.setAttribute("user", user);
            if ("admin".equals(user.getRole())) {
                resp.sendRedirect("/admin/table");
            } else if ("user".equals(user.getRole()))
                resp.sendRedirect("/user");
        } else {
            getServletContext().getRequestDispatcher("/WEB-INF/loginPage.jsp").forward(req, resp);
        }
    }
}
