package fr.nvh.spring.utilities.validator;

import lombok.Getter;
import org.springframework.lang.NonNull;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class Rejections<P extends Enum<P>> {

    @Getter
    private final String name;

    private final List<Rejection> rejectionList = new LinkedList<>();
    /**
     * utiliser pour l'exception handler ?
     */
    private final Map<Enum<P>, String> additionalParams = new LinkedHashMap<>();

    public Rejections(String name) {
        this.name = name;
    }

    public boolean hasAdditionalParam() {
        return additionalParams.isEmpty();
    }

    public boolean containsAdditionalParam(P key) {
        return additionalParams.containsKey(key);
    }

    public String getAdditionalParam(P key) {
        return additionalParams.get(key);
    }

    public String putAdditionalParam(P key, String value) {
        return additionalParams.put(key, value);
    }

    public void reject(@NonNull String code, @NonNull String message) {
        rejectionList.add(new Rejection(code, message));
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
}
