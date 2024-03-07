package fr.nvh.spring.utilities.validator;

import org.springframework.lang.NonNull;

public class RejectionException extends RuntimeException {

    public RejectionException(@NonNull Rejections rejections) {
        this(RejectionExceptionMessageBuilder.defaultBuilder(), rejections);
    }

    public RejectionException(RejectionExceptionMessageBuilder rejectionExceptionMessageBuilder, Rejections rejections) {
        super(rejectionExceptionMessageBuilder.buildMessage(rejections));
    }
}
