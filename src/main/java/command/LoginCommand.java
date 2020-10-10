package command;

import entity.Role;
import entity.User;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;

import static constant.PageConstants.*;

public class LoginCommand implements Command {
    private final UserService userService;

    public LoginCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (username == null) {
            return LOGIN_PAGE;
        }

        Optional<User> user = getLoggedUserFromDb(username, password);

        if (!user.isPresent()) {
            response.sendRedirect(LOGIN_PAGE);
        }

        User verifiedUser = user.get();
        setUserAndRoleToSession(request, verifiedUser);
        Role role = verifiedUser.getRole();

        if (role.equals(Role.ADMIN)) {
            return ADMIN_PANEL;
        }  else if (role.equals(Role.SPECIALIST)) {
            return SPECIALIST_CABINET;
        }  else {
            return CLIENT_PROFILE;
        }
    }

    private void setUserAndRoleToSession(HttpServletRequest request, User user) {
        HttpSession session = request.getSession();
        session.setAttribute("userId", user.getId());
        session.setAttribute("username", user.getUsername());
        session.setAttribute("role", user.getRole());
    }

    private Optional<User> getLoggedUserFromDb(String username, String password) {
        return userService.login(username, password);
    }

}
