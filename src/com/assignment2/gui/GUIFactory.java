package com.assignment2.gui;

/**
 * Abstract Factory: creates a complete, matching family of UI components.
 * Each method returns the abstract product type, so clients never see concrete classes.
 */
public interface GUIFactory {

    Button createButton();

    Checkbox createCheckbox();
}
