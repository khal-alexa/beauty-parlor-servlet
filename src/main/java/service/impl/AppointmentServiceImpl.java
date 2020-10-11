package service.impl;

import dao.AppointmentDao;
import dao.Page;
import dao.TreatmentDao;
import dao.impl.TimeslotDaoImpl;
import dto.AppointmentDto;
import entity.Appointment;
import entity.Timeslot;
import entity.Treatment;
import exception.EntityNotFoundException;
import mapper.AppointmentMapper;
import service.AppointmentService;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class AppointmentServiceImpl implements AppointmentService {
    private final AppointmentDao appointmentDao;
    private final TimeslotDaoImpl timeslotDao;
    private final TreatmentDao treatmentDao;
    private final AppointmentMapper appointmentMapper;

    public AppointmentServiceImpl(AppointmentDao appointmentDao, TimeslotDaoImpl timeslotDao,
                                  TreatmentDao treatmentDao, AppointmentMapper appointmentMapper) {
        this.appointmentDao = appointmentDao;
        this.timeslotDao = timeslotDao;
        this.treatmentDao = treatmentDao;
        this.appointmentMapper = appointmentMapper;
    }

    @Override
    public boolean saveNewAppointment(AppointmentDto appointmentDto) {
        return appointmentDao.save(appointmentMapper.mapDtoIntoEntity(appointmentDto));
    }

    @Override
    public List<AppointmentDto> findAllByDateWithTimeslots(LocalDate date, Optional<Long> treatmentId, Page page) {
        List<Appointment> appointments = findAllSorted(date, treatmentId, page);
        List<Timeslot> timeslots = timeslotDao.findAll(page);
        List<AppointmentDto> dtos = new ArrayList<>();

        int index = 0;
        for (Timeslot timeslot : timeslots) {
            if (index < appointments.size() && timeslot.getId().equals(appointments.get(index).getTimeslotId())) {
                dtos.add(appointmentMapper.mapEntityIntoDto(appointments.get(index)));
                index++;
            } else {
                AppointmentDto dto = new AppointmentDto.AppointmentDtoBuilder()
                        .setTimeslot(timeslot.getStartTime().toString())
                        .setDate(date)
                        .setAvailable(true)
                        .build();
                dtos.add(dto);
            }
        }
        return dtos;
    }

    @Override
    public List<AppointmentDto> findAllBySpecialistIdAndDate(Long id, LocalDate date, Page page) {
        return appointmentDao.findAllByDate(date, page).stream()
                .filter(appointment -> appointment.getSpecialistId().equals(id))
                .map(appointmentMapper::mapEntityIntoDto)
                .collect(Collectors.toList());
    }

    @Override
    public void markAppointmentAsDone(Long id) {
        Optional<Appointment> appointment = appointmentDao.findById(id);
        appointment.orElseThrow(EntityNotFoundException::new).setDone(true);
        appointmentDao.save(appointment.orElseThrow(EntityNotFoundException::new));
    }

    @Override
    public AppointmentDto findById(Long appointmentId) {
        return appointmentMapper.
                mapEntityIntoDto(appointmentDao.findById(appointmentId)
                        .orElseThrow(EntityNotFoundException::new));
    }

    @Override
    public void deleteById(Long id) {
        appointmentDao.deleteById(id);
    }

    @Override
    public void update(Long id, String time) {
        Appointment appointment = appointmentDao.findById(id).orElseThrow(EntityNotFoundException::new);
        appointment.setTimeslotId(timeslotDao.findByStartTime(LocalTime.parse(time)).getId());
        appointmentDao.save(appointment);
    }

    @Override
    public void markAppointmentAsPaid(Long id) {
        Optional<Appointment> appointment = appointmentDao.findById(id);
        appointment.orElseThrow(EntityNotFoundException::new).setPaid(true);
        appointmentDao.save(appointment.orElseThrow(EntityNotFoundException::new));
    }

    @Override
    public List<AppointmentDto> findAllByDateAndTreatmentName(LocalDate date, String treatmentName, Page page) {
        Treatment treatment = treatmentDao.findByName(treatmentName).orElseThrow(EntityNotFoundException::new);
        return findAllByDateWithTimeslots(date, Optional.of(treatment.getId()), page);
    }

    private List<Appointment> findAllSorted(LocalDate date, Optional<Long> treatmentId, Page page) {
        List<Appointment> appointments;
        if(!treatmentId.isPresent()) {
            appointments = appointmentDao.findAllByDate(date, page);
        } else {
            appointments = appointmentDao.findAllByDateAndTreatmentId(date, treatmentId.get());
        }
        return appointments.stream()
                .sorted(Comparator.comparing(Appointment::getDate).thenComparing(Appointment::getTimeslotId))
                .collect(Collectors.toList());
    }

}
