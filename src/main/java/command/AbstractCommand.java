package command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public abstract class AbstractCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String method = request.getMethod();

        if (method.equals("POST")) {
            processPost(request, response);
        } else if (method.equals("GET")) {
            processGet(request, response);
        } else {
            throw new IllegalStateException();
        }
    }

    abstract void processGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException;

    abstract void processPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException;

}
