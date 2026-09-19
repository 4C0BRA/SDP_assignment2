package com.assignment2.app;

import com.assignment2.gui.GUIFactory;
import com.assignment2.gui.MacOSFactory;
import com.assignment2.gui.WindowsFactory;
import com.assignment2.logistics.Logistics;
import com.assignment2.logistics.RoadLogistics;
import com.assignment2.logistics.SeaLogistics;

import java.util.Optional;
import java.util.Scanner;
import java.util.function.Function;

/**
 * Startup: validates the two independent choices, picks the concrete creator and
 * factory, and hands them to DeliveryApplication.
 *
 * Usage:   java com.assignment2.app.Main [ROAD|SEA] [WINDOWS|MACOS]
 * Without arguments the choices are asked on the console (asked again when invalid).
 * With a wrong number of arguments or an unsupported value the program prints a
 * message and stops with exit code 1. There is no silent default.
 */
public class Main {

    private static final String SAMPLE_CARGO = "laboratory equipment";
    private static final String SAMPLE_DESTINATION = "Aktau warehouse";

    public static void main(String[] args) {
        Optional<DeliveryMode> mode;
        Optional<UiPlatform> platform;

        if (args.length == 0) {
            Scanner console = new Scanner(System.in);
            mode = askUntilValid(console, "Delivery mode (ROAD or SEA): ", DeliveryMode::parse);
            platform = mode.flatMap(m -> askUntilValid(console, "UI platform (WINDOWS or MACOS): ", UiPlatform::parse));
        } else if (args.length == 2) {
            mode = DeliveryMode.parse(args[0]);
            platform = UiPlatform.parse(args[1]);
            reportInvalidArguments(args, mode, platform);
        } else {
            System.out.println("Expected two arguments: <ROAD|SEA> <WINDOWS|MACOS>, but got " + args.length + ".");
            System.exit(1);
            return;
        }

        if (mode.isEmpty() || platform.isEmpty()) {
            System.out.println("Input ended or was invalid. Stopping without running anything.");
            System.exit(1);
            return;
        }

        start(mode.get(), platform.get());
    }

    private static void start(DeliveryMode mode, UiPlatform platform) {
        Logistics logistics = createLogistics(mode);
        GUIFactory guiFactory = createGuiFactory(platform);

        System.out.println("Delivery mode: " + mode);
        System.out.println("UI platform: " + platform);

        new DeliveryApplication(guiFactory, logistics).run(SAMPLE_CARGO, SAMPLE_DESTINATION);
    }

    private static Logistics createLogistics(DeliveryMode mode) {
        return switch (mode) {
            case ROAD -> new RoadLogistics();
            case SEA -> new SeaLogistics();
        };
    }

    private static GUIFactory createGuiFactory(UiPlatform platform) {
        return switch (platform) {
            case WINDOWS -> new WindowsFactory();
            case MACOS -> new MacOSFactory();
        };
    }

    private static void reportInvalidArguments(String[] args, Optional<DeliveryMode> mode, Optional<UiPlatform> platform) {
        if (mode.isEmpty()) {
            System.out.println("Unsupported delivery mode: '" + args[0] + "'. Supported: ROAD, SEA.");
        }
        if (platform.isEmpty()) {
            System.out.println("Unsupported UI platform: '" + args[1] + "'. Supported: WINDOWS, MACOS.");
        }
    }

    /** Asks again until the answer is valid; returns empty when the input stream ends. */
    private static <T> Optional<T> askUntilValid(Scanner console, String question, Function<String, Optional<T>> parser) {
        while (true) {
            System.out.print(question);
            if (!console.hasNextLine()) {
                System.out.println();
                System.out.println("No input received.");
                return Optional.empty();
            }
            String answer = console.nextLine();
            Optional<T> parsed = parser.apply(answer);
            if (parsed.isPresent()) {
                return parsed;
            }
            System.out.println("Unsupported value: '" + answer.trim() + "'. Try again.");
        }
    }
}
