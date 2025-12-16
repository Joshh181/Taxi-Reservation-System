package com.mycompany.taxireservationsystem;

/**
 * Reservation class - Represents a taxi reservation
 * Fixed to work with database (String reservationId)
 */
public class Reservation {
    private static int counter = 1;
    private String reservationId;  // ← CHANGED from int to String
    private String customerName;
    private String pickupLocation;
    private String destination;
    private double distance;
    private Vehicle vehicle;

    /**
     * Constructor - Creates a new reservation
     * @param customerName Customer's name
     * @param pickupLocation Pickup location
     * @param destination Destination location
     * @param distance Distance in kilometers
     * @param vehicle Vehicle assigned to this reservation
     */
    public Reservation(String customerName, String pickupLocation,
                       String destination, double distance, Vehicle vehicle) {
        this.reservationId = "R" + (counter++);  // ← CHANGED: Generates "R1", "R2", "R3", etc.
        this.customerName = customerName;
        this.pickupLocation = pickupLocation;
        this.destination = destination;
        this.distance = distance;
        this.vehicle = vehicle;
    }

    // ===== GETTER METHODS =====
    
    public String getReservationId() { return reservationId; }  // ← CHANGED return type to String
    
    public String getCustomerName() { return customerName; }
    
    public String getPickupLocation() { return pickupLocation; }
    
    public String getDestination() { return destination; }
    
    public double getDistance() { return distance; }
    
    public Vehicle getVehicle() { return vehicle; }
    
    public double getFare() { return vehicle.calculateFare(distance); }
    
    // ===== SETTER METHOD =====
    
    /**
     * Set reservation ID (used when loading from database)
     * @param reservationId Reservation ID to set
     */
    public void setReservationId(String reservationId) {
        this.reservationId = reservationId;  // ← NOW WORKS: String = String
    }
}