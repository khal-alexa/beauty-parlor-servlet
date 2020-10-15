package command;

import entity.Role;
import entity.User;
import exception.CommandException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;

import static constant.PageConstants.ADMIN_PANEL;
import static constant.PageConstants.CLIENT_PROFILE;
import static constant.PageConstants.LOGIN_PAGE;
import static constant.PageConstants.SPECIALIST_CABINET;

public class AuthCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger(AuthCommand.class);

    private final UserService userService;

    public AuthCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (username == null) {
            return LOGIN_PAGE;
        }

        Optional<User> user = getLoggedUserFromDb(username, password);

        if (!user.isPresent()) {
            try {
                String message = String.format("User %s tried to perform login but was not found in DB", username);
                LOGGER.warn(message);
                response.sendRedirect(LOGIN_PAGE);
            } catch (IOException e) {
                String message = "Cannot perform redirect to " + LOGIN_PAGE;
                LOGGER.warn(message);
                throw new CommandException(message, e);
            }
        }

        User verifiedUser = user.get();
        setUserAndRoleToSession(request, verifiedUser);
        Role role = verifiedUser.getRole();

        if (role.equals(Role.ADMIN)) {
            return ADMIN_PANEL;
        } else if (role.equals(Role.SPECIALIST)) {
            return SPECIALIST_CABINET;
        }

        return "redirect:" + CLIENT_PROFILE;
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
