package com.bbva.pfmh.dto.jcisconnector.ffmm.commons;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum FundsNumberTypeIdOutputEnum {

    LIC("LIC");
    private String value;

    private static final Map<String, String> KEYS_TO_VALUES_MAP = Stream.of(values())
            .collect(Collectors.toMap(Enum::name, e -> e.value));

    private FundsNumberTypeIdOutputEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public String getKey() {
        return this.name().substring(1);
    }

    public static String getValueFromKey(String key) {
        return KEYS_TO_VALUES_MAP.get(String.format("_%s", key));
    }
}