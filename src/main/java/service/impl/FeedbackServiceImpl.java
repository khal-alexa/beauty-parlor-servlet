package service.impl;

import dao.FeedbackDao;
import dao.Page;
import dao.UserDao;
import dto.FeedbackDto;
import entity.Feedback;
import entity.User;
import exception.EntityNotFoundException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import service.FeedbackService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class FeedbackServiceImpl implements FeedbackService {
    private static final Logger LOGGER = LogManager.getLogger(FeedbackServiceImpl.class);

    private final FeedbackDao feedbackDao;
    private final UserDao userDao;

    public FeedbackServiceImpl(FeedbackDao feedbackDao, UserDao userDao) {
        this.feedbackDao = feedbackDao;
        this.userDao = userDao;
    }

    @Override
    public boolean saveFeedback(FeedbackDto feedbackDto) {
        Optional<User> specialist = userDao.findByUsername(feedbackDto.getSpecialistName());
        if (!specialist.isPresent()) {
            String message = String.format("Specialist wih name %s was not found", feedbackDto.getSpecialistName());
            LOGGER.warn(message);
            throw new EntityNotFoundException(message);
        }
        return feedbackDao.save(new Feedback.Builder()
                .setRate(feedbackDto.getRate())
                .setClientId(feedbackDto.getClientId())
                .setSpecialistId(specialist.get().getId())
                .setText(feedbackDto.getText())
                .setDateTime(LocalDateTime.now())
                .build());
    }

    @Override
    public List<Feedback> findAll(Page page) {
        return feedbackDao.findAll(page);
    }
}
