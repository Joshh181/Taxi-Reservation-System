package com.mycompany.taxireservationsystem;

public class StandardTaxi extends Vehicle {

    public StandardTaxi(String vehicleId, String driverName) {
        super(vehicleId, driverName);
    }

    @Override
    public String getVehicleType() {
        return "Standard Taxi";
    }

    @Override
    public double calculateFare(double distance) {
        return distance * 15;
    }
}
