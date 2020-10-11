package command;

import constant.PageConstants;
import dao.Page;
import dto.TreatmentDto;
import service.TreatmentService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static constant.PageConstants.HOME_PAGE;

public class HomeCommand implements Command {
    private final TreatmentService treatmentService;

    public HomeCommand(TreatmentService treatmentService) {
        this.treatmentService = treatmentService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String page = request.getParameter("page");
        Page<TreatmentDto> treatments = treatmentService.findAllWithSpecialistsAndRates(page);
        request.setAttribute("treatments", treatments);
        request.setAttribute("page", treatments.getPageNumber());
        return HOME_PAGE;
    }

}
