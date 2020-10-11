package service;

import dao.Page;
import dto.AppointmentDto;
import entity.Timeslot;

import java.util.List;

public interface TimeslotService {
    List<Timeslot> findAll(Page page);

    List<AppointmentDto> findAllWithTreatment(Long treatmentId,Page page);

}
