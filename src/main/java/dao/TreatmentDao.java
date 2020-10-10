package dao;

import dto.TreatmentDto;
import entity.Treatment;

import java.util.List;
import java.util.Optional;

public interface TreatmentDao extends CrudDao<Treatment> {
    Optional<Treatment> findByName(String name);

    List<TreatmentDto> findAllWithSpecialists();

}
