package com.assignment2.logistics;

/** Concrete creator: produces ships. */
public class SeaLogistics extends Logistics {

    @Override
    protected Transport createTransport() {
        return new Ship();
    }
}
