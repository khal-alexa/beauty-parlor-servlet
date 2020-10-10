package service;

import dto.TreatmentDto;
import entity.Treatment;

import java.util.List;
import java.util.Optional;

public interface TreatmentService {
    Optional<Treatment> findByName(String treatmentName);

    List<TreatmentDto> findAllWithSpecialistsAndRates();
}
