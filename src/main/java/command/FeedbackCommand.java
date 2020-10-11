package command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static constant.PageConstants.CLIENT_FEEDBACK;


public class FeedbackCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        return CLIENT_FEEDBACK;
    }
}