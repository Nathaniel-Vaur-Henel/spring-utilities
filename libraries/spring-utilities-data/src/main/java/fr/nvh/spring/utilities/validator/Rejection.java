package fr.nvh.spring.utilities.validator;

import java.util.Collections;
import java.util.Map;

public record Rejection(String code, String message, Map<String, Object> additionalParams) {
    public Rejection(String code, String message) {
        this(code, message, Collections.emptyMap());
    }
}
