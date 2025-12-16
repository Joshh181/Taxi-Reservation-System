-- ============================================
-- Taxi Reservation System - Database Setup
-- ============================================

-- Step 1: Create the database
CREATE DATABASE IF NOT EXISTS taxi_reservation_db;

-- Step 2: Use the database
USE taxi_reservation_db;

-- Step 3: Create reservations table
CREATE TABLE IF NOT EXISTS reservations (
    id INT PRIMARY KEY AUTO_INCREMENT,
    reservation_id VARCHAR(50) UNIQUE NOT NULL,
    customer_name VARCHAR(100) NOT NULL,
    pickup_location VARCHAR(200) NOT NULL,
    destination VARCHAR(200) NOT NULL,
    distance DOUBLE NOT NULL,
    vehicle_type VARCHAR(50) NOT NULL,
    vehicle_id VARCHAR(50) NOT NULL,
    driver_name VARCHAR(100) NOT NULL,
    fare DOUBLE NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    INDEX idx_reservation_id (reservation_id),
    INDEX idx_customer_name (customer_name),
    INDEX idx_created_at (created_at)
);

-- Step 4: Verify the table was created
DESCRIBE reservations;

-- Step 5: Check if database is empty (should return 0 rows initially)
SELECT COUNT(*) as total_reservations FROM reservations;

-- Optional: Insert a test record to verify everything works
INSERT INTO reservations 
(reservation_id, customer_name, pickup_location, destination, distance, vehicle_type, vehicle_id, driver_name, fare)
VALUES 
('TEST001', 'Test Customer', 'Test Pickup', 'Test Destination', 10.5, 'Standard Taxi', 'V1234', 'Juan', 150.00);

-- Verify the test record was inserted
SELECT * FROM reservations;

-- Optional: Delete the test record after verification
-- DELETE FROM reservations WHERE reservation_id = 'TEST001';
reservations
-- Success message
SELECT 'Database setup completed successfully!' as Status;
USE taxi_reservation_db;
SELECT * FROM reservations;