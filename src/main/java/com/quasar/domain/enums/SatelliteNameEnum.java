package com.quasar.domain.enums;

public enum SatelliteNameEnum {
    KENOBI("kenobi"),
    SKYWALKER("skywalker"),
    SATO("sato");

    private final String label;

    SatelliteNameEnum(String label) {
        this.label = label;
    }

    public static boolean isMember(String label) {
        for (SatelliteNameEnum e : values()) {
            if (e.label.equals(label)) {
                return true;
            }
        }
        return false;
    }
}
