package command;

import dto.UserDto;
import entity.Role;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static constant.PageConstants.LOGIN_PAGE;
import static constant.PageConstants.REGISTRATION_PAGE;

public class RegisterCommand implements Command {
    private final UserService userService;

    public RegisterCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean isValidPasswords = userService.validatePasswords(request.getParameter("password"),
                request.getParameter("confirmedPassword"));

        if (!isValidPasswords || request.getAttribute("username") == null) {
            return REGISTRATION_PAGE;
        }

        UserDto user = buildUserFromRequest(request);

        if (userService.register(user)) {
            return LOGIN_PAGE;
        } else {
            return REGISTRATION_PAGE;
        }

    }

    private UserDto buildUserFromRequest(HttpServletRequest request) {
        String username = request.getParameter("username");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String phoneNumber = request.getParameter("phoneNumber");
        String password = request.getParameter("password");

        return new UserDto.UserDtoBuilder()
                .setUserName(username)
                .setPassword(password)
                .setFirstName(firstName)
                .setLastName(lastName)
                .setEmail(email)
                .setPhoneNumber(phoneNumber)
                .setRole(Role.CLIENT)
                .build();
    }

}
