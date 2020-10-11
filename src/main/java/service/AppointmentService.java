package service;

import dao.Page;
import dto.AppointmentDto;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface AppointmentService {
    boolean saveNewAppointment(AppointmentDto appointmentDto);

    List<AppointmentDto> findAllByDateWithTimeslots(LocalDate date, Optional<Long> treatmentId, Page page);

    List<AppointmentDto> findAllBySpecialistIdAndDate(Long id, LocalDate date, Page page);

    void markAppointmentAsDone(Long id);

    AppointmentDto findById(Long appointmentId);

    void deleteById(Long id);

    void update(Long id, String time);

    void markAppointmentAsPaid(Long id);

    List<AppointmentDto> findAllByDateAndTreatmentName(LocalDate date, String treatmentName, Page page);

}
