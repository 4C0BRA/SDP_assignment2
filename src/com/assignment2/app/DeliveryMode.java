package com.assignment2.app;

import java.util.Optional;

/** Supported delivery modes, as typed by the user at startup. */
public enum DeliveryMode {
    ROAD,
    SEA;

    /** Case-insensitive parsing; returns empty for anything unsupported. */
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
