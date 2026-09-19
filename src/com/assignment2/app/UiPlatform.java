package com.assignment2.app;

import java.util.Optional;

/** Supported UI platforms, as typed by the user at startup. */
public enum UiPlatform {
    WINDOWS,
    MACOS;

    /** Case-insensitive parsing; returns empty for anything unsupported. */
    public static Optional<UiPlatform> parse(String text) {
        if (text == null) {
            return Optional.empty();
        }
        String normalized = text.trim().toUpperCase();
        for (UiPlatform platform : values()) {
            if (platform.name().equals(normalized)) {
                return Optional.of(platform);
            }
        }
        return Optional.empty();
    }
}
