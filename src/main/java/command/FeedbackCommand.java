package command;

import dto.FeedbackDto;
import entity.Role;
import exception.CommandException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import service.FeedbackService;
import service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.List;

import static constant.PageConstants.CLIENT_FEEDBACK;
import static constant.PageConstants.CLIENT_PROFILE;

public class FeedbackCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger(FeedbackCommand.class);

    private final UserService userService;
    private final FeedbackService feedbackService;

    public FeedbackCommand(UserService userService, FeedbackService feedbackService) {
        this.userService = userService;
        this.feedbackService = feedbackService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String text = request.getParameter("text");
        if (text != null) {
            FeedbackDto dto = buildFeedbackFromRequest(request);
            boolean isSavedSuccessfully = feedbackService.saveFeedback(dto);
            if (!isSavedSuccessfully) {
                String message = "Feedback was not saved";
                LOGGER.warn(message);
                throw new CommandException(message);
            }
            return CLIENT_PROFILE;
        }
        List<String> specialists = userService.findAllByRole(Role.SPECIALIST);
        request.setAttribute("specialists", specialists);
        return CLIENT_FEEDBACK;
    }

    private FeedbackDto buildFeedbackFromRequest(HttpServletRequest request) {
        String specialistName = request.getParameter("specialistName");
        String rate = request.getParameter("rate");
        String text = request.getParameter("text");
        Long clientId = (Long) request.getSession().getAttribute("userId");

        return new FeedbackDto.Builder()
                .setClientId(clientId)
                .setCreatedOn(LocalDateTime.now())
                .setRate(Integer.parseInt(rate))
                .setSpecialistName(specialistName)
                .setText(text)
                .build();
    }

}
