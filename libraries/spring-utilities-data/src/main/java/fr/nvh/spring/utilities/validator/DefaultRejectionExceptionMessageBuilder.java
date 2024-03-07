package fr.nvh.spring.utilities.validator;

import org.springframework.lang.NonNull;

import java.util.stream.Collectors;

public class DefaultRejectionExceptionMessageBuilder implements RejectionExceptionMessageBuilder {
    public String buildMessage(@NonNull Rejections rejections) {
        return rejections.getName() + " has been rejected with " + rejections.size() + " reason(s) :\n"
                + rejections.stream()
                        .map(rejection -> "- " + rejection.code() + ": " + rejection.message())
                        .collect(Collectors.joining("\n"));
    }
}
