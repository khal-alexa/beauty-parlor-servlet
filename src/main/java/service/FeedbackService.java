package service;

import dao.Page;
import dto.FeedbackDto;
import entity.Feedback;

import java.util.List;

public interface FeedbackService {
    boolean saveFeedback(FeedbackDto feedbackDto);

    List<Feedback> findAll(Page page);

}
