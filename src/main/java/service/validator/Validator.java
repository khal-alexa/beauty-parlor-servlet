package service.validator;

public interface Validator<E> {
    boolean isValid(E e);
}
