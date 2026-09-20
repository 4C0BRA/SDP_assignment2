package com.assignment2.app;

import com.assignment2.gui.Button;
import com.assignment2.gui.Checkbox;
import com.assignment2.gui.GUIFactory;
import com.assignment2.logistics.Logistics;

public class DeliveryApplication {
    private final GUIFactory guiFactory;
    private final Logistics logistics;

    public DeliveryApplication(GUIFactory guiFactory, Logistics logistics) {
        this.guiFactory = guiFactory;
        this.logistics = logistics;
    }

    public void run(String cargo, String destination) {
        renderInterface();
        logistics.planDelivery(cargo, destination);
    }

    private void renderInterface() {
        Button button = guiFactory.createButton();
        Checkbox checkbox = guiFactory.createCheckbox();
        button.paint();
        checkbox.paint();
    }
}
