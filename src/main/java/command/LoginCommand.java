package command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static constant.PageConstants.ADMIN_PANEL;
import static constant.PageConstants.CLIENT_PROFILE;
import static constant.PageConstants.LOGIN_PAGE;
import static constant.PageConstants.SPECIALIST_CABINET;

public class LoginCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String role = (String) request.getSession().getAttribute("role");
        if (role != null) {
            return getPageByRole(role);
        }
        return LOGIN_PAGE;
    }

    private String getPageByRole(String role) {
        switch (role) {
            case "ADMIN":
                return ADMIN_PANEL;
            case "SPECIALIST":
                return SPECIALIST_CABINET;
            case "CLIENT":
                return "redirect:" + CLIENT_PROFILE;
            default:
                return LOGIN_PAGE;
        }
    }

}
