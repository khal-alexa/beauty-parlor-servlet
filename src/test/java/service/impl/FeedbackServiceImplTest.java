package service.impl;

import dao.FeedbackDao;
import dao.UserDao;
import dto.FeedbackDto;
import entity.Feedback;
import entity.Role;
import entity.User;
import exception.EntityNotFoundException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class FeedbackServiceImplTest {
    private static final User USER = buildUser();
    private static final Feedback FEEDBACK_ENTITY = buildFeedback();
    private static final FeedbackDto CORRECT_FEEDBACK_DTO = buildCorrectFeedbackDto();
    private static final FeedbackDto WRONG_FEEDBACK_DTO = buildWrongFeedbackDto();
    private static final String CORRECT_SPECIALIST_NAME = "Edward";
    private static final String WRONG_SPECIALIST_NAME = "Andrew";


    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Mock
    private FeedbackDao feedbackDao;

    @Mock
    private UserDao userDao;

    @InjectMocks
    private FeedbackServiceImpl feedbackService;

    @Test
    public void saveFeedbackShouldThrowExceptionWhenSpecialistNotFound() {
        exception.expect(EntityNotFoundException.class);
        exception.expectMessage(String.format("Specialist wih name %s was not found", WRONG_SPECIALIST_NAME));

        when(userDao.findByUsername(anyString())).thenReturn(Optional.empty());

        feedbackService.saveFeedback(WRONG_FEEDBACK_DTO);
    }

    @Test
    public void saveFeedbackShouldSaveWhenSpecialistFound() {
        when(userDao.findByUsername(CORRECT_SPECIALIST_NAME)).thenReturn(Optional.of(USER));
        when(feedbackDao.save(any())).thenReturn(Boolean.TRUE);

        boolean expected = feedbackService.saveFeedback(CORRECT_FEEDBACK_DTO);

        assertTrue(expected);
    }

    private static User buildUser() {
        return User.builder()
                .setId(1L)
                .setUserName("Anna1234")
                .setFirstName("Anna")
                .setLastName("Morgana")
                .setEmail("anna@qmail.com")
                .setPassword("annaPassword2@")
                .setPhoneNumber("+390832213453")
                .setRole(Role.CLIENT)
                .build();
    }

    private static Feedback buildFeedback() {
        return Feedback.builder()
                .setId(5L)
                .setRate(5)
                .setClientId(5L)
                .setDateTime(LocalDateTime.now())
                .setText("Excellent!")
                .setSpecialistId(1L)
                .build();
    }

    private static FeedbackDto buildCorrectFeedbackDto() {
        return FeedbackDto.builder()
                .setId(5L)
                .setRate(5)
                .setClientId(5L)
                .setText("Excellent!")
                .setCreatedOn(LocalDateTime.now())
                .setSpecialistName(CORRECT_SPECIALIST_NAME)
                .build();
    }

    private static FeedbackDto buildWrongFeedbackDto() {
        return FeedbackDto.builder()
                .setId(5L)
                .setRate(5)
                .setClientId(5L)
                .setText("Excellent!")
                .setCreatedOn(LocalDateTime.now())
                .setSpecialistName(WRONG_SPECIALIST_NAME)
                .build();
    }

}
