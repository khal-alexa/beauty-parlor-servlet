package mapper;

import dao.TimeslotDao;
import dao.TreatmentDao;
import dao.UserDao;
import dto.AppointmentDto;
import entity.Appointment;
import exception.EntityNotFoundException;

import java.time.LocalTime;

public class AppointmentMapper implements Mapper<Appointment, AppointmentDto> {
    private final TimeslotDao timeslotRepository;
    private final TreatmentDao treatmentRepository;
    private final UserDao userRepository;

    public AppointmentMapper(TimeslotDao timeslotRepository, TreatmentDao treatmentRepository, UserDao userRepository) {
        this.timeslotRepository = timeslotRepository;
        this.treatmentRepository = treatmentRepository;
        this.userRepository = userRepository;
    }

    @Override
    public AppointmentDto mapEntityIntoDto(Appointment appointment) {
        return new AppointmentDto.AppointmentDtoBuilder()
                .setId(appointment.getId())
                .setDate(appointment.getDate())
                .setTimeslot(timeslotRepository.findById(appointment.getTimeslotId()).
                        orElseThrow(EntityNotFoundException::new).
                        getStartTime().toString())
                .setTreatmentName(treatmentRepository.findById(appointment.getTreatmentId())
                        .orElseThrow(EntityNotFoundException::new)
                        .getName())
                .setSpecialistId(appointment.getSpecialistId())
                .setSpecialistName(userRepository.findById(appointment.getSpecialistId())
                        .orElseThrow(EntityNotFoundException::new)
                        .getUsername())
                .setDone(appointment.isDone())
                .setPaid(appointment.isPaid())
                .setAvailable(false)
                .build();
    }

    @Override
    public Appointment mapDtoIntoEntity(AppointmentDto dto) {
        return new Appointment.Builder()
                .setId(dto.getId())
                .setTimeslotId(timeslotRepository.findByStartTime(LocalTime.parse(dto.getTimeslot())).getId())
                .setDate(dto.getDate())
                .setTreatmentId(treatmentRepository.findByName(dto.getTreatmentName())
                        .orElseThrow(EntityNotFoundException::new).getId())
                .setSpecialistId(userRepository.findByUsername(dto.getSpecialistName())
                        .orElseThrow(EntityNotFoundException::new).getId())
                .setPaid(dto.getPaid())
                .setDone(dto.getDone())
                .build();
    }

}
