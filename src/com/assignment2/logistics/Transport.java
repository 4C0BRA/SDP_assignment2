package com.assignment2.logistics;

/**
 * Product of the Factory Method pattern: the delivery contract.
 * Callers depend only on this interface, never on Truck or Ship.
 */
public interface Transport {

    void deliver(String cargo, String destination);
}
