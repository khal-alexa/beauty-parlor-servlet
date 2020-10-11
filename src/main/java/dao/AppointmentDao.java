package dao;

import entity.Appointment;

import java.time.LocalDate;
import java.util.List;

public interface AppointmentDao extends CrudDao<Appointment> {
    List<Appointment> findAllByDate(LocalDate date, Page page);

    List<Appointment> findAllByDateAndTreatmentId(LocalDate date, Long treatmentId);

}
