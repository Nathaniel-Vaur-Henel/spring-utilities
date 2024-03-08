package fr.nvh.spring.utilities.validator;

import java.util.Map;

public record Rejection(String code, String message, Map<String, Object> additionalParams) {}
