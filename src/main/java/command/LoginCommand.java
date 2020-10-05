package command;

import entities.Role;
import entities.User;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginCommand extends AbstractCommand {
    private final UserService userService;

    public LoginCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    void processGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("login.jsp");
    }

    @Override
    void processPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        User user = getLoggedUserFromDb(username, password);

        if (user == null) {
            response.sendRedirect("login.jsp");
        }

        setUserAndRoleToSession(request, user);
        Role role = user.getRole();

        if (role.equals(Role.ADMIN)) {
            response.sendRedirect("admin/adminPanel.jsp");
        }  else if (role.equals(Role.SPECIALIST)) {
            response.sendRedirect("specialist/cabinet.jsp");
        }  else {
            response.sendRedirect("client/clientProfile.jsp");
        }
    }

    private void setUserAndRoleToSession(HttpServletRequest request, User user) {
        HttpSession session = request.getSession();
        session.setAttribute("userId", user.getId());
        session.setAttribute("username", user.getUsername());
        session.setAttribute("role", user.getRole());
    }

    private User getLoggedUserFromDb(String username, String password) {
        return userService.login(username, password);
    }

}
