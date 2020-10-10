package command;

import dto.TreatmentDto;
import service.TreatmentService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class HomeCommand extends AbstractCommand {
    private final TreatmentService treatmentService;

    public HomeCommand(TreatmentService treatmentService) {
        this.treatmentService = treatmentService;
    }

    @Override
    void processGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<TreatmentDto> treatments = treatmentService.findAllWithSpecialistsAndRates();
        request.setAttribute("treatments", treatments);
        request.getRequestDispatcher("home.jsp").forward(request, response);
    }

    @Override
    void processPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

}
