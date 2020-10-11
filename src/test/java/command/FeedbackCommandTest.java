package command;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static constant.PageConstants.CLIENT_FEEDBACK;
import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class FeedbackCommandTest {
    private static final String EXPECTED = CLIENT_FEEDBACK;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @InjectMocks
    private FeedbackCommand feedbackCommand;

    @Test
    public void executeShouldReturnFeedbackPage() {
        String actual = feedbackCommand.execute(request, response);

        assertEquals(EXPECTED, actual);
    }

}
