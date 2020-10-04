package command;

import entities.Role;
import entities.User;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegisterCommand extends AbstractCommand {
    private final UserService userService;

    public RegisterCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    void processGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("register.jsp");
    }

    @Override
    void processPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!validatePasswords(request)) {
            response.sendRedirect("register.jsp");
        }
        User user = buildUserFromRequest(request);

        if (userService.register(user)) {
            response.sendRedirect("login.jsp");
        } else {
            response.sendRedirect("register.jsp");
        }
    }

    private User buildUserFromRequest(HttpServletRequest request) {
        String username = request.getParameter("username");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String phoneNumber = request.getParameter("phoneNumber");
        String password = request.getParameter("password");

        return new User.UserBuilder()
                .setUserName(username)
                .setPassword(password)
                .setFirstName(firstName)
                .setLastName(lastName)
                .setEmail(email)
                .setPhoneNumber(phoneNumber)
                .setRole(Role.CLIENT)
                .build();
    }

    private boolean validatePasswords(HttpServletRequest request) {
        String password = request.getParameter("password");
        String confirmedPassword = request.getParameter("confirmedPassword");
        return password.equals(confirmedPassword);
    }

}
