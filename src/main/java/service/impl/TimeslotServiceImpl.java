package service.impl;

import dao.Page;
import dao.TimeslotDao;
import dao.TreatmentDao;
import dto.AppointmentDto;
import entity.Timeslot;
import exception.EntityNotFoundException;
import service.TimeslotService;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class TimeslotServiceImpl implements TimeslotService {
    private final TimeslotDao timeslotDao;
    private final TreatmentDao treatmentDao;

    public TimeslotServiceImpl(TimeslotDao timeslotDao, TreatmentDao treatmentDao) {
        this.timeslotDao = timeslotDao;
        this.treatmentDao = treatmentDao;
    }

    @Override
    public List<Timeslot> findAll(Page page) {
        return timeslotDao.findAll(page);
    }

    @Override
    public List<AppointmentDto> findAllWithTreatment(Long treatmentId, Page page) {
        List<Timeslot> allTimeslots = findAll(page);
        return allTimeslots.stream()
                .map(timeslot -> new AppointmentDto.AppointmentDtoBuilder()
                        .setTimeslot(timeslot.getStartTime().toString())
                        .setDate(LocalDate.now())
                        .setTreatmentName(treatmentDao.findById(treatmentId)
                                .orElseThrow(EntityNotFoundException::new).getName())
                        .build())
                .collect(Collectors.toList());
    }

}
