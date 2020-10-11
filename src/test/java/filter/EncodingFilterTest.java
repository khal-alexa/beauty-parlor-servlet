package filter;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class EncodingFilterTest {
    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Test
    public void testDoFilterCorrectSetCorrectEncoding() throws ServletException, IOException {
        FilterChain filterChain = mock(FilterChain.class);

        EncodingFilter filter = new EncodingFilter();
        filter.doFilter(request, response, filterChain);

        verify(request).setCharacterEncoding("UTF-8");
        verify(filterChain).doFilter(request, response);
    }

}
