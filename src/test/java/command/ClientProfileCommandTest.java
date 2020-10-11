package command;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static constant.PageConstants.CLIENT_PROFILE;
import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class ClientProfileCommandTest {
    private static final String EXPECTED = CLIENT_PROFILE;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @InjectMocks
    private ClientProfileCommand clientProfileCommand;

    @Test
    public void executeShouldReturnClientProfilePage() {
        String actual = clientProfileCommand.execute(request, response);

        assertEquals(EXPECTED, actual);
    }

}