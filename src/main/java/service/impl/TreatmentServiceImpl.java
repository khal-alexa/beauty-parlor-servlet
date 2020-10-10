package service.impl;

import dao.TreatmentDao;
import dao.UserDao;
import dto.TreatmentDto;
import entity.Treatment;
import service.TreatmentService;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class TreatmentServiceImpl implements TreatmentService {
    private final TreatmentDao treatmentDao;
    private final UserDao userDao;

    public TreatmentServiceImpl(TreatmentDao treatmentDao, UserDao userDao) {
        this.treatmentDao = treatmentDao;
        this.userDao = userDao;
    }

    @Override
    public Optional<Treatment> findByName(String treatmentName) {
        return Optional.empty();
    }

    @Override
    public List<TreatmentDto> findAllWithSpecialistsAndRates() {
        Map<String, Double> rates = userDao.findAllSpecialistsWithRates();
        List<TreatmentDto> dtos = treatmentDao.findAllWithSpecialists();
        return dtos.stream()
                .map(e -> new TreatmentDto.TreatmentDtoBuilder(e)
                        .setSpecialistName(e.getSpecialistName())
                        .setRate(rates.get(e.getSpecialistName()))
                        .build())
                .collect(Collectors.toList());
    }
}
