package dao;

import entities.Feedback;

import java.util.List;

public interface FeedbackDao extends CrudDao<Feedback> {
    List<Feedback> findAllByClientId(Long clientId, Page page);

    List<Feedback> findAllBySpecialistId(Long specialistId, Page page);

}
