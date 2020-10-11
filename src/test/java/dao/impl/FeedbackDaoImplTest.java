package dao.impl;

import dao.CrudDao;
import dao.impl.util.TestDBConnector;
import dao.impl.util.TestDBInitializer;
import entity.Feedback;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class FeedbackDaoImplTest {
    private static final Feedback FEEDBACK = createTestFeedback();
    private static final long LAST_ID_IN_POPULATION = 11;
    private static final long FEEDBACK_ID = LAST_ID_IN_POPULATION + 1;

    private static CrudDao<Feedback> feedbackDao;

    private static TestDBConnector dbConnector;
    private static TestDBInitializer dbInitializer;

    @BeforeClass
    public static void init() {
        dbConnector = new TestDBConnector();
        feedbackDao = new FeedbackDaoImpl(dbConnector);
        dbInitializer = new TestDBInitializer(dbConnector);
    }

    @Before
    public void dataBaseInit() {
        dbInitializer.initDatabase();
        dbInitializer.populateDatabase();
    }

    @Test
    public void saveShouldCreateValidEntityInDb() {
        boolean isSaved = feedbackDao.save(FEEDBACK);
        Feedback actual = feedbackDao.findById(FEEDBACK_ID).get();
        assertTrue(isSaved);
        assertEquals(FEEDBACK, actual);
    }

    @Test
    public void deleteShouldRemoveEntityInDb() {
        feedbackDao.save(FEEDBACK);
        feedbackDao.deleteById(FEEDBACK_ID);
        Optional<Feedback> actual = feedbackDao.findById(FEEDBACK_ID);
        assertEquals(Optional.empty(), actual);
    }

    private static Feedback createTestFeedback() {
        return Feedback.builder()
                .setId(FEEDBACK_ID)
                .setRate(5)
                .setClientId(13L)
                .setDateTime(LocalDateTime.now())
                .setText("Good")
                .setSpecialistId(21L)
                .build();
    }

}
