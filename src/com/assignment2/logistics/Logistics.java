package com.assignment2.logistics;

/**
 * Creator of the Factory Method pattern.
 * planDelivery() is the shared workflow; the subclass decides which Transport is used.
 */
public abstract class Logistics {

    /** The factory method: each concrete creator returns its own Transport. */
    protected abstract Transport createTransport();

    public void planDelivery(String cargo, String destination) {
        Transport transport = createTransport();
        transport.deliver(cargo, destination);
    }
}
