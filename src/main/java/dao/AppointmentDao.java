package dao;

import entity.Appointment;

import java.util.List;

public interface AppointmentDao extends CrudDao<Appointment> {
    List<Appointment> findAllByClientId(Long clientId, Page page);

    List<Appointment> findAllBySpecialistId(Long specialistId, Page page);

    List<Appointment> findAllByServiceId(Long serviceId, Page page);

}
