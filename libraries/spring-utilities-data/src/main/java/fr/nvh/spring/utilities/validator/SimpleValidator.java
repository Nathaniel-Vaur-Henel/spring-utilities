package fr.nvh.spring.utilities.validator;

import org.springframework.lang.NonNull;

public abstract class SimpleValidator<T> {

    public abstract void validate(T value, @NonNull Rejections validationExceptions);
}