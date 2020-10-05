package dao;

import entity.Treatment;

import java.util.Optional;

public interface TreatmentDao extends CrudDao<Treatment> {
    Optional<Treatment> findByName(String name);

}
