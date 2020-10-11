package dao;

import java.util.List;
import java.util.Optional;

public interface CrudDao<E> {
    boolean save(E entity);

    Optional<E> findById(Long id);

    List<E> findAll(Page page);

    void update(E entity);

    void deleteById(Long id);

}
