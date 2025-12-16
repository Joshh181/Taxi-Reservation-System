package com.mycompany.taxireservationsystem;

import java.sql.*;
import java.util.ArrayList;

/**
 * Data Access Object for Reservation operations
 * Handles all database CRUD operations for reservations
 */
public class ReservationDAO {
    
    /**
     * Save a reservation to database
     * @param reservation Reservation object to save
     * @return true if successful, false otherwise
     */
    public boolean saveReservation(Reservation reservation) {
        String sql = "INSERT INTO reservations (reservation_id, customer_name, " +
                     "pickup_location, destination, distance, vehicle_type, " +
                     "vehicle_id, driver_name, fare) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            System.out.println("\n📝 Saving reservation to database...");
            
            pstmt.setString(1, reservation.getReservationId());
            pstmt.setString(2, reservation.getCustomerName());
            pstmt.setString(3, reservation.getPickupLocation());
            pstmt.setString(4, reservation.getDestination());
            pstmt.setDouble(5, reservation.getDistance());
            pstmt.setString(6, reservation.getVehicle().getVehicleType());
            pstmt.setString(7, reservation.getVehicle().getVehicleId());
            pstmt.setString(8, reservation.getVehicle().getDriverName());
            pstmt.setDouble(9, reservation.getFare());
            
            int rowsAffected = pstmt.executeUpdate();
            
            if (rowsAffected > 0) {
                System.out.println("✓ Reservation saved successfully!");
                System.out.println("  ID: " + reservation.getReservationId());
                System.out.println("  Customer: " + reservation.getCustomerName());
                System.out.println("  Fare: ₱" + reservation.getFare());
                return true;
            }
            
            return false;
            
        } catch (SQLException e) {
            System.err.println("❌ Error saving reservation: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
    
    /**
     * Load all reservations from database
     * @return ArrayList of all reservations
     */
    public ArrayList<Reservation> loadAllReservations() {
        ArrayList<Reservation> reservations = new ArrayList<>();
        String sql = "SELECT * FROM reservations ORDER BY created_at DESC";
        
        System.out.println("\n📂 Loading reservations from database...");
        
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            int count = 0;
            while (rs.next()) {
                // Recreate vehicle object
                String vehicleType = rs.getString("vehicle_type");
                String vehicleId = rs.getString("vehicle_id");
                String driverName = rs.getString("driver_name");
                
                Vehicle vehicle = createVehicleFromType(vehicleType, vehicleId, driverName);
                
                // Create reservation with saved data
                Reservation reservation = new Reservation(
                    rs.getString("customer_name"),
                    rs.getString("pickup_location"),
                    rs.getString("destination"),
                    rs.getDouble("distance"),
                    vehicle
                );
                
                // Set the saved reservation ID
                reservation.setReservationId(rs.getString("reservation_id"));
                
                reservations.add(reservation);
                count++;
            }
            
            System.out.println("✓ Loaded " + count + " reservation(s) from database");
            
        } catch (SQLException e) {
            System.err.println("❌ Error loading reservations: " + e.getMessage());
            e.printStackTrace();
        }
        
        return reservations;
    }
    
    /**
     * Delete a reservation from database
     * @param reservationId ID of reservation to delete
     * @return true if successful, false otherwise
     */
    public boolean deleteReservation(String reservationId) {
        String sql = "DELETE FROM reservations WHERE reservation_id = ?";
        
        System.out.println("\n🗑️ Deleting reservation: " + reservationId);
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, reservationId);
            int rowsAffected = pstmt.executeUpdate();
            
            if (rowsAffected > 0) {
                System.out.println("✓ Reservation deleted successfully!");
                return true;
            } else {
                System.out.println("⚠️ No reservation found with ID: " + reservationId);
                return false;
            }
            
        } catch (SQLException e) {
            System.err.println("❌ Error deleting reservation: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
    
    /**
     * Delete all reservations (clear database)
     * @return true if successful, false otherwise
     */
    public boolean deleteAllReservations() {
        String sql = "DELETE FROM reservations";
        
        System.out.println("\n🗑️ Clearing all reservations...");
        
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement()) {
            
            int rowsAffected = stmt.executeUpdate(sql);
            System.out.println("✓ Deleted " + rowsAffected + " reservation(s)");
            return true;
            
        } catch (SQLException e) {
            System.err.println("❌ Error clearing reservations: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
    
    /**
     * Get total count of reservations
     * @return number of reservations in database
     */
    public int getReservationCount() {
        String sql = "SELECT COUNT(*) as count FROM reservations";
        
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            if (rs.next()) {
                int count = rs.getInt("count");
                System.out.println("📊 Total reservations in database: " + count);
                return count;
            }
            
        } catch (SQLException e) {
            System.err.println("❌ Error getting count: " + e.getMessage());
            e.printStackTrace();
        }
        
        return 0;
    }
    
    /**
     * Get total revenue from all reservations
     * @return total fare amount
     */
    public double getTotalRevenue() {
        String sql = "SELECT SUM(fare) as total FROM reservations";
        
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            if (rs.next()) {
                double total = rs.getDouble("total");
                System.out.println("💰 Total revenue: ₱" + String.format("%.2f", total));
                return total;
            }
            
        } catch (SQLException e) {
            System.err.println("❌ Error getting revenue: " + e.getMessage());
            e.printStackTrace();
        }
        
        return 0.0;
    }
    
    /**
     * Helper method to recreate vehicle from database data
     */
    private Vehicle createVehicleFromType(String type, String vehicleId, String driverName) {
        switch (type) {
            case "Premium Taxi":
                return new PremiumTaxi(vehicleId, driverName);
            case "Shared Taxi":
                return new SharedTaxi(vehicleId, driverName);
            default:
                return new StandardTaxi(vehicleId, driverName);
        }
    }
    
    /**
     * Test method - Run this to test database operations
     */
    public static void main(String[] args) {
        System.out.println("===========================================");
        System.out.println("RESERVATION DAO TEST");
        System.out.println("===========================================\n");
        
        // Test connection first
        if (!DatabaseConnection.testConnection()) {
            System.out.println("❌ Database connection failed! Cannot proceed with tests.");
            return;
        }
        
        ReservationDAO dao = new ReservationDAO();
        
        // Test 1: Check current count
        System.out.println("\n--- TEST 1: Current Database Status ---");
        dao.getReservationCount();
        dao.getTotalRevenue();
        
        // Test 2: Create and save a test reservation
        System.out.println("\n--- TEST 2: Save Test Reservation ---");
        Vehicle testVehicle = new StandardTaxi("TEST123", "Test Driver");
        Reservation testReservation = new Reservation(
            "Test Customer",
            "Test Pickup Location",
            "Test Destination",
            15.5,
            testVehicle
        );
        
        boolean saved = dao.saveReservation(testReservation);
        System.out.println("Save result: " + (saved ? "✓ SUCCESS" : "❌ FAILED"));
        
        // Test 3: Load all reservations
        System.out.println("\n--- TEST 3: Load All Reservations ---");
        ArrayList<Reservation> reservations = dao.loadAllReservations();
        System.out.println("Found " + reservations.size() + " reservation(s)");
        
        // Display each reservation
        for (Reservation r : reservations) {
            System.out.println("\n  Reservation: " + r.getReservationId());
            System.out.println("  Customer: " + r.getCustomerName());
            System.out.println("  Route: " + r.getPickupLocation() + " → " + r.getDestination());
            System.out.println("  Distance: " + r.getDistance() + " km");
            System.out.println("  Fare: ₱" + r.getFare());
        }
        
        // Test 4: Get statistics
        System.out.println("\n--- TEST 4: Database Statistics ---");
        dao.getReservationCount();
        dao.getTotalRevenue();
        
        // Optional: Clean up test data
        System.out.println("\n--- Optional: Delete Test Reservation ---");
        System.out.println("(Uncomment the line below to delete the test reservation)");
        // dao.deleteReservation(testReservation.getReservationId());
        
        System.out.println("\n===========================================");
        System.out.println("✅ ALL TESTS COMPLETED!");
        System.out.println("===========================================");
        
        DatabaseConnection.closeConnection();
    }
}