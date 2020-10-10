package command;

import constant.PageConstants;
import dao.Page;
import dto.TreatmentDto;
import service.TreatmentService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HomeCommand implements Command {
    private final TreatmentService treatmentService;

    public HomeCommand(TreatmentService treatmentService) {
        this.treatmentService = treatmentService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page = request.getParameter("page");
        Page<TreatmentDto> treatments = treatmentService.findAllWithSpecialistsAndRates(page);
        request.setAttribute("treatments", treatments);
        request.setAttribute("page", treatments.getPageNumber());
        return PageConstants.HOME_PAGE;
    }

}
