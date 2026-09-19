package com.assignment2.logistics;

/** Concrete creator: produces trucks. */
public class RoadLogistics extends Logistics {

    @Override
    protected Transport createTransport() {
        return new Truck();
    }
}
