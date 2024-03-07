package fr.nvh.spring.utilities.validator;

import org.springframework.lang.NonNull;

public interface RejectionExceptionMessageBuilder {

    String buildMessage(@NonNull Rejections rejections);

    static DefaultRejectionExceptionMessageBuilder defaultBuilder() {
        return new DefaultRejectionExceptionMessageBuilder();
    }
}
