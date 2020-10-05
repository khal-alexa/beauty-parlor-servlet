package mapper;

public interface Mapper<E, D> {
    E mapDtoIntoEntity(D dto);

    D mapEntityIntoDto(E entity);

}
