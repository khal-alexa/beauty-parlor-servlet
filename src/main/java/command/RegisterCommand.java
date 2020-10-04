package command;

import entities.Role;
import entities.User;
import service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;

public class RegisterCommand implements Command {
    private final UserService userService;

    public RegisterCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String username = request.getParameter("username");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String phoneNumber = request.getParameter("phoneNumber");
        String password = request.getParameter("password");
        String confirmedPassword = request.getParameter("confirmedPassword");

        if (!password.equals(confirmedPassword)) {
            return "registrationForm";
        }

        User user = new User.UserBuilder()
                .setUserName(username)
                .setPassword(password)
                .setFirstName(firstName)
                .setLastName(lastName)
                .setEmail(email)
                .setPhoneNumber(phoneNumber)
                .setRole(Role.CLIENT)
                .setCreatedOn(LocalDate.now())
                .build();

        if (userService.register(user)) {
            return "login.jsp";
        }
        return "register.jsp";
    }

}
