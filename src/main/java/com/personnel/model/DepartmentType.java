package com.personnel.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum DepartmentType {
    @JsonProperty("Personal")
    HR("Personal"),
    @JsonProperty("Verkauf")
    SALES("Verkauf"),
    @JsonProperty("Entwicklung")
    IT("Entwicklung");

    private final String value;

    public String getValue() {

        return value;
    }

    public static DepartmentType getFromValue(String value) {

        return Arrays.stream(DepartmentType.values()).filter(dep -> dep.getValue().equals(value)).findFirst().get();
    }

    private DepartmentType(String value) {

        this.value = value;
    }

    public static List<String> getEnumOptions() {

        return Stream.of(DepartmentType.class.getEnumConstants())
                .map(DepartmentType::getValue)
                .collect(Collectors.toList());
    }
}
