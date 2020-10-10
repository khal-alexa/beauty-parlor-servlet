package service;

import dao.Page;
import dto.TreatmentDto;
import entity.Treatment;

import java.util.List;
import java.util.Optional;

public interface TreatmentService {
    Optional<Treatment> findByName(String treatmentName);

    Page<TreatmentDto> findAllWithSpecialistsAndRates(String pageNumber);
}
