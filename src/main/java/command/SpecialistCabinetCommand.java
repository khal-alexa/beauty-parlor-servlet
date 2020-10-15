package command;

import constant.PageConstants;
import dto.AppointmentDto;
import service.AppointmentService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.util.List;

public class SpecialistCabinetCommand implements Command {
    private final AppointmentService appointmentService;

    public SpecialistCabinetCommand(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String requestedDate = (String) request.getAttribute("startDate");
        String appointmentId = request.getParameter("id");
        if (appointmentId != null) {
            appointmentService.markAppointmentAsDone(Long.parseLong(appointmentId));
        }
        LocalDate date = requestedDate == null ? LocalDate.now() : LocalDate.parse(requestedDate);
        Long specialistId = (Long) request.getSession().getAttribute("userId");
        List<AppointmentDto> appointments = appointmentService.findAllBySpecialistIdAndDate(specialistId, date);
        request.setAttribute("appointments", appointments);

        return PageConstants.SPECIALIST_CABINET;
    }

}
