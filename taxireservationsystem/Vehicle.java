package com.mycompany.taxireservationsystem;

public abstract class Vehicle {
    protected String vehicleId;
    protected String driverName;

    public Vehicle(String vehicleId, String driverName) {
        this.vehicleId = vehicleId;
        this.driverName = driverName;
    }

    public String getDriverName() { return driverName; }
    public String getVehicleId() { return vehicleId; }

    public abstract String getVehicleType();
    public abstract double calculateFare(double distance);
}
