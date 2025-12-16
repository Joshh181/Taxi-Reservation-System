package com.mycompany.taxireservationsystem;

public class PremiumTaxi extends Vehicle {

    public PremiumTaxi(String vehicleId, String driverName) {
        super(vehicleId, driverName);
    }

    @Override
    public String getVehicleType() {
        return "Premium Taxi";
    }

    @Override
    public double calculateFare(double distance) {
        return distance * 25;
    }
}
