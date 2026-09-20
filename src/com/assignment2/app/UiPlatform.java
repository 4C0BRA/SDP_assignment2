package com.assignment2.app;

import java.util.Optional;

public enum UiPlatform {
    WINDOWS,
    MACOS;

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
