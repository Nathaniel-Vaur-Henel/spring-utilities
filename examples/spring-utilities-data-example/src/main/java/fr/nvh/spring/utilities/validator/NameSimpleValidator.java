package fr.nvh.spring.utilities.validator;

import org.springframework.lang.NonNull;

public class NameSimpleValidator extends SimpleValidator<String> {

    @Override
    public void validate(String value, @NonNull Rejections validationExceptions) {
        if (value == null) {
            validationExceptions.reject("name.not.null", "The name cannot be null.");
            return;
        }
        if (value.isBlank()) {
            validationExceptions.reject("name.not.blank", "The name cannot be empty.");
        }
        if (value.length() > 2) {
            validationExceptions.reject("name.too.big.2", "The name [" + value + "]is too big (2 max).");
        }
        if (value.length() > 3) {
            validationExceptions.reject("name.too.big.3", "The name [" + value + "]is too big (3 max).");
        }
    }
}
