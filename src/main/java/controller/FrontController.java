package controller;

import command.Command;
import command.CommandProvider;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class FrontController extends HttpServlet {
    private final CommandProvider commandProvider;

    public FrontController() {
        commandProvider = new CommandProvider();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Command command = commandProvider.getCommand(request.getRequestURI());
        String page = command.execute(request, response);

        if (page.contains("redirect:")) {
            response.sendRedirect(request.getContextPath() +page.replace("redirect:",""));
            return;
        }

        request.getRequestDispatcher(page).forward(request, response);
    }

}
