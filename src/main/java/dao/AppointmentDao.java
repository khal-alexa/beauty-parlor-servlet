package dao;

import entity.Appointment;

import java.time.LocalDate;
import java.util.List;

public interface AppointmentDao extends CrudDao<Appointment> {
    List<Appointment> findAllByDate(LocalDate date, Page page);

    List<Appointment> findAllByDateAndSpecialist(LocalDate date, Long specialistId);

    List<Appointment> findAllByDateAndTreatmentId(LocalDate date, Long treatmentId);

    boolean updateDone(Long id);

}
