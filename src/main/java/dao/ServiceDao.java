package dao;

import entities.Service;

import java.util.Optional;

public interface ServiceDao extends CrudDao<Service> {
    Optional<Service> findByName(String name);

}
