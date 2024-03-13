package fr.nvh.spring.utilities.validator;

import lombok.Getter;
import org.springframework.lang.NonNull;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class Rejections {

    @Getter
    private final String name;

    private final List<Rejection> rejectionList = new LinkedList<>();

    public Rejections(String name) {
        this.name = name;
    }

    public void reject(@NonNull String code, @NonNull String message) {
        rejectionList.add(new Rejection(code, message));
    }

    public void reject(@NonNull String code, @NonNull String message, Map<String, Object> additionalParams) {
        rejectionList.add(new Rejection(code, message, additionalParams));
    }

    public boolean hasRejections() {
        return !rejectionList.isEmpty();
    }

    public int size() {
        return rejectionList.size();
    }

    public Stream<Rejection> stream() {
        return rejectionList.stream();
    }

    public void finish() {
        if (hasRejections()) {
            throw new RejectionException(this);
        }
    }

    public static void validate(String name) {}
}
