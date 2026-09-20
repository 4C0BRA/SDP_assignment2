package com.assignment2.app;

import java.util.Optional;

public enum DeliveryMode {
    ROAD,
    SEA;

    public static Optional<DeliveryMode> parse(String text) {
        if (text == null) {
            return Optional.empty();
        }
        String normalized = text.trim().toUpperCase();
        for (DeliveryMode mode : values()) {
            if (mode.name().equals(normalized)) {
                return Optional.of(mode);
            }
        }
        return Optional.empty();
    }
}
