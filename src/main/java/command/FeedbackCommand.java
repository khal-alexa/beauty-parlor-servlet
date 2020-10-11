package command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static constant.PageConstants.CLIENT_FEEDBACK;

public class FeedbackCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        return CLIENT_FEEDBACK;
    }

}
