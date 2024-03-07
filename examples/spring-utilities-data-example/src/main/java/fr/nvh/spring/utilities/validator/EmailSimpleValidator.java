package fr.nvh.spring.utilities.validator;

import org.springframework.lang.NonNull;

import java.util.regex.Pattern;

public class EmailSimpleValidator extends SimpleValidator<String> {
    private static final Pattern PATTERN = Pattern.compile("^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$");

    @Override
    public void validate(String value, @NonNull Rejections validationExceptions) {
        if (value == null) {
            validationExceptions.reject("email.not.null", "The email cannot be null.");
            return;
        }
        if (value.isBlank()) {
            validationExceptions.reject("email.not.blank", "The email cannot be empty.");
            return;
        }

        if (!PATTERN.matcher(value).matches()) {
            validationExceptions.reject("email.invalid", "The email [" + value + "] is invalid.");
        }
    }
}
