package com.mycompany.taxireservationsystem;

public class SharedTaxi extends Vehicle {

    public SharedTaxi(String vehicleId, String driverName) {
        super(vehicleId, driverName);
    }

    @Override
    public String getVehicleType() {
        return "Shared Taxi";
    }

    @Override
    public double calculateFare(double distance) {
        return distance * 10; // example rate
    }
}
