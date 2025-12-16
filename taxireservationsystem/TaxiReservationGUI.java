package com.mycompany.taxireservationsystem;



import java.awt.Graphics;

import java.awt.Graphics2D;

import java.awt.GradientPaint;

import java.awt.Color;

import java.awt.event.ActionEvent;

import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

import javax.swing.JOptionPane;

import javax.swing.JPanel;



public class TaxiReservationGUI extends javax.swing.JFrame {



    // Class variables

    private ArrayList<Reservation> reservations;

    private DefaultTableModel tableModel;

    private ReservationDAO reservationDAO;

    

    // Custom JPanel class with gradient background

    class BackgroundPanel extends JPanel {

        @Override

        protected void paintComponent(Graphics g) {

            super.paintComponent(g);

            Graphics2D g2d = (Graphics2D) g;

            

            // Create gradient from yellow-orange to light blue

            GradientPaint gradient = new GradientPaint(

                0, 0, new Color(255, 230, 150),  // Light yellow-orange

                0, getHeight(), new Color(200, 230, 255)  // Light blue

            );

            

            g2d.setPaint(gradient);

            g2d.fillRect(0, 0, getWidth(), getHeight());

        }

    }

    

    public TaxiReservationGUI() {

        initComponents();

        reservations = new ArrayList<>();

        reservationDAO = new ReservationDAO();

        setupTable();

        

        System.out.println("\n===========================================");

        System.out.println("STARTING TAXI RESERVATION SYSTEM");

        System.out.println("===========================================\n");

        

        if (DatabaseConnection.testConnection()) {

            System.out.println("✓ Database connection successful!");

            loadReservationsFromDatabase();

        } else {

            JOptionPane.showMessageDialog(this,

                "Database connection failed!\nPlease check your MySQL settings.",

                "Database Error",

                JOptionPane.ERROR_MESSAGE);

        }

    }

    

    private void loadReservationsFromDatabase() {

        System.out.println("Loading reservations from database...");

        reservations = reservationDAO.loadAllReservations();

        tableModel.setRowCount(0);

        for (Reservation r : reservations) {

            addReservationToTable(r);

        }

        updateTotalFare();

        System.out.println("✓ Loaded " + reservations.size() + " reservations from database.\n");

    }



    @SuppressWarnings("unchecked")

    private void initComponents() {

        jSeparator2 = new javax.swing.JSeparator();

        jPanel1 = new javax.swing.JPanel();

        jLabel6 = new javax.swing.JLabel();

        jLabel1 = new javax.swing.JLabel();

        txtCustomerName = new javax.swing.JTextField();

        jLabel2 = new javax.swing.JLabel();

        txtPickup = new javax.swing.JTextField();

        txtCustomerName2 = new javax.swing.JTextField();

        txtDistance = new javax.swing.JTextField();

        jLabel3 = new javax.swing.JLabel();

        jLabel4 = new javax.swing.JLabel();

        jLabel5 = new javax.swing.JLabel();

        cmbVehicleType = new javax.swing.JComboBox<>();

        btnBook = new javax.swing.JButton();

        btnClear = new javax.swing.JButton();

        btnDelete = new javax.swing.JButton();

        jPanel3 = new javax.swing.JPanel();

        jSeparator1 = new javax.swing.JSeparator();

        jLabel7 = new javax.swing.JLabel();

        jScrollPane1 = new javax.swing.JScrollPane();

        tblReservations = new javax.swing.JTable();

        jLabel8 = new javax.swing.JLabel();

        jPanel2 = new javax.swing.JPanel();

        jPanel4 = new javax.swing.JPanel();

        jLabel9 = new javax.swing.JLabel();

        jSeparator3 = new javax.swing.JSeparator();

        lblTotalFare = new javax.swing.JLabel();

        backgroundPanel = new BackgroundPanel();



        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        setTitle("Taxi Reservation System");

        setPreferredSize(new java.awt.Dimension(900, 650));



        jPanel1.setBackground(new java.awt.Color(255, 193, 7));

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 152, 0), 2));



        jLabel6.setFont(new java.awt.Font("Verdana", 1, 18));

        jLabel6.setForeground(new java.awt.Color(51, 51, 51));

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jLabel6.setText("TAXI RESERVATION SYSTEM");



        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);

        jPanel1.setLayout(jPanel1Layout);

        jPanel1Layout.setHorizontalGroup(

            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)

            .addGroup(jPanel1Layout.createSequentialGroup()

                .addContainerGap()

                .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)

                .addContainerGap())

        );

        jPanel1Layout.setVerticalGroup(

            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)

            .addGroup(jPanel1Layout.createSequentialGroup()

                .addContainerGap()

                .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)

                .addContainerGap())

        );



        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12));

        jLabel1.setText("Customer Name:");



        txtCustomerName.setColumns(20);



        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12));

        jLabel2.setText("Pickup Location:");



        txtPickup.setColumns(20);



        txtCustomerName2.setColumns(20);



        txtDistance.setColumns(20);



        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12));

        jLabel3.setText("Destination:");



        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12));

        jLabel4.setText("Distance (km):");



        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12));

        jLabel5.setText("Vehicle Type:");



        cmbVehicleType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Standard Taxi", "Premium Taxi", "Shared Taxi" }));



        btnBook.setBackground(new java.awt.Color(76, 175, 80));

        btnBook.setFont(new java.awt.Font("Segoe UI", 1, 14));

        btnBook.setForeground(new java.awt.Color(255, 255, 255));

        btnBook.setText(" Book Now");

        btnBook.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        btnBook.addActionListener(this::btnBookActionPerformed);



        btnClear.setBackground(new java.awt.Color(158, 158, 158));

        btnClear.setFont(new java.awt.Font("Segoe UI", 1, 14));

        btnClear.setForeground(new java.awt.Color(255, 255, 255));

        btnClear.setText("Clear");

        btnClear.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        btnClear.addActionListener(this::btnClearActionPerformed);



        btnDelete.setBackground(new java.awt.Color(244, 67, 54));

        btnDelete.setFont(new java.awt.Font("Segoe UI", 1, 14));

        btnDelete.setForeground(new java.awt.Color(255, 255, 255));

        btnDelete.setText("Delete Selected");

        btnDelete.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        btnDelete.addActionListener(this::btnDeleteActionPerformed);



        jPanel3.setBackground(new Color(255, 255, 255, 230));

        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 152, 0), 2));



        jLabel7.setFont(new java.awt.Font("Verdana", 1, 14));

        jLabel7.setForeground(new java.awt.Color(51, 51, 51));

        jLabel7.setText("Reservations");



        tblReservations.setAutoCreateRowSorter(true);

        tblReservations.setModel(new javax.swing.table.DefaultTableModel(

            new Object [][] {},

            new String [] {

                "ID", "Customer", "Pickup", "Destination", "Distance (km)", "Vehicle Type", "Driver", "Fare (₱)"

            }

        ));

        tblReservations.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        jScrollPane1.setViewportView(tblReservations);



        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);

        jPanel3.setLayout(jPanel3Layout);

        jPanel3Layout.setHorizontalGroup(

            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)

            .addGroup(jPanel3Layout.createSequentialGroup()

                .addContainerGap()

                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)

                    .addComponent(jScrollPane1)

                    .addGroup(jPanel3Layout.createSequentialGroup()

                        .addComponent(jLabel7)

                        .addGap(0, 0, Short.MAX_VALUE)))

                .addContainerGap())

        );

        jPanel3Layout.setVerticalGroup(

            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)

            .addGroup(jPanel3Layout.createSequentialGroup()

                .addContainerGap()

                .addComponent(jLabel7)

                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)

                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)

                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))

        );



        jLabel8.setFont(new java.awt.Font("Verdana", 1, 14));

        jLabel8.setForeground(new java.awt.Color(51, 51, 51));

        jLabel8.setText("New Reservation");



        jPanel4.setBackground(new Color(255, 255, 255, 230));

        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(33, 150, 243), 2));



        jLabel9.setFont(new java.awt.Font("Verdana", 1, 14));

        jLabel9.setForeground(new java.awt.Color(51, 51, 51));

        jLabel9.setText("Summary");



        lblTotalFare.setFont(new java.awt.Font("Verdana", 1, 18));

        lblTotalFare.setForeground(new java.awt.Color(33, 150, 243));

        lblTotalFare.setText("Total Revenue: ₱0.00");



        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);

        jPanel4.setLayout(jPanel4Layout);

        jPanel4Layout.setHorizontalGroup(

            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)

            .addGroup(jPanel4Layout.createSequentialGroup()

                .addContainerGap()

                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)

                    .addComponent(jLabel9)

                    .addComponent(lblTotalFare))

                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))

        );

        jPanel4Layout.setVerticalGroup(

            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)

            .addGroup(jPanel4Layout.createSequentialGroup()

                .addContainerGap()

                .addComponent(jLabel9)

                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)

                .addComponent(lblTotalFare)

                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))

        );



        javax.swing.GroupLayout backgroundPanelLayout = new javax.swing.GroupLayout(backgroundPanel);

        backgroundPanel.setLayout(backgroundPanelLayout);

        backgroundPanelLayout.setHorizontalGroup(

            backgroundPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)

            .addGroup(backgroundPanelLayout.createSequentialGroup()

                .addContainerGap()

                .addGroup(backgroundPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)

                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)

                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)

                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)

                    .addGroup(backgroundPanelLayout.createSequentialGroup()

                        .addGroup(backgroundPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)

                            .addComponent(jLabel8)

                            .addGroup(backgroundPanelLayout.createSequentialGroup()

                                .addGap(50, 50, 50)

                                .addGroup(backgroundPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)

                                    .addGroup(backgroundPanelLayout.createSequentialGroup()

                                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)

                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)

                                        .addComponent(txtCustomerName))

                                    .addGroup(backgroundPanelLayout.createSequentialGroup()

                                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)

                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)

                                        .addComponent(txtPickup))

                                    .addGroup(backgroundPanelLayout.createSequentialGroup()

                                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)

                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)

                                        .addComponent(txtCustomerName2))

                                    .addGroup(backgroundPanelLayout.createSequentialGroup()

                                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)

                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)

                                        .addComponent(txtDistance))

                                    .addGroup(backgroundPanelLayout.createSequentialGroup()

                                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)

                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)

                                        .addComponent(cmbVehicleType, 0, 650, Short.MAX_VALUE))

                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, backgroundPanelLayout.createSequentialGroup()

                                        .addGap(120, 120, 120)

                                        .addComponent(btnBook, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)

                                        .addGap(30, 30, 30)

                                        .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)

                                        .addGap(30, 30, 30)

                                        .addComponent(btnDelete)

                                        .addGap(70, 70, 70)))))

                        .addGap(0, 50, Short.MAX_VALUE)))

                .addContainerGap())

        );

        backgroundPanelLayout.setVerticalGroup(

            backgroundPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)

            .addGroup(backgroundPanelLayout.createSequentialGroup()

                .addContainerGap()

                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)

                .addGap(18, 18, 18)

                .addComponent(jLabel8)

                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)

                .addGroup(backgroundPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)

                    .addComponent(jLabel1)

                    .addComponent(txtCustomerName, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))

                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)

                .addGroup(backgroundPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)

                    .addComponent(jLabel2)

                    .addComponent(txtPickup, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))

                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)

                .addGroup(backgroundPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)

                    .addComponent(jLabel3)

                    .addComponent(txtCustomerName2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))

                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)

                .addGroup(backgroundPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)

                    .addComponent(jLabel4)

                    .addComponent(txtDistance, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))

                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)

                .addGroup(backgroundPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)

                    .addComponent(jLabel5)

                    .addComponent(cmbVehicleType, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))

                .addGap(18, 18, 18)

                .addGroup(backgroundPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)

                    .addComponent(btnBook, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)

                    .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)

                    .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))

                .addGap(18, 18, 18)

                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)

                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)

                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)

                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))

        );



        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());

        getContentPane().setLayout(layout);

        layout.setHorizontalGroup(

            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)

            .addComponent(backgroundPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)

        );

        layout.setVerticalGroup(

            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)

            .addComponent(backgroundPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)

        );



        pack();

        setLocationRelativeTo(null);

    }



    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {

        clearFields();

    }



    private void btnBookActionPerformed(java.awt.event.ActionEvent evt) {

        System.out.println("\n--- BOOK NOW BUTTON CLICKED ---");

        try {

            String customerName = txtCustomerName.getText().trim();

            String pickup = txtPickup.getText().trim();

            String destination = txtCustomerName2.getText().trim();

            String distanceStr = txtDistance.getText().trim();

            

            if (customerName.isEmpty() || pickup.isEmpty() || destination.isEmpty() || distanceStr.isEmpty()) {

                JOptionPane.showMessageDialog(this, "Please fill in all fields!", "Validation Error", JOptionPane.ERROR_MESSAGE);

                return;

            }

            

            double distance = Double.parseDouble(distanceStr);

            if (distance <= 0) {

                JOptionPane.showMessageDialog(this, "Distance must be greater than 0!", "Validation Error", JOptionPane.ERROR_MESSAGE);

                return;

            }

            

            Vehicle vehicle = createVehicle((String) cmbVehicleType.getSelectedItem());

            Reservation reservation = new Reservation(customerName, pickup, destination, distance, vehicle);

            

            if (reservationDAO.saveReservation(reservation)) {

                reservations.add(reservation);

                addReservationToTable(reservation);

                updateTotalFare();

                clearFields();

                JOptionPane.showMessageDialog(this, 

                    String.format("Reservation successful!\nReservation ID: %s\nFare: ₱%.2f\n✓ Saved to database!", 

                    reservation.getReservationId(), reservation.getFare()),

                    "Success", JOptionPane.INFORMATION_MESSAGE);

            } else {

                JOptionPane.showMessageDialog(this, "Failed to save reservation to database!", "Database Error", JOptionPane.ERROR_MESSAGE);

            }

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(this, "Please enter a valid distance!", "Input Error", JOptionPane.ERROR_MESSAGE);

        }

    }



    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {

        int selectedRow = tblReservations.getSelectedRow();

        if (selectedRow == -1) {

            JOptionPane.showMessageDialog(this, "Please select a reservation to delete!", "Selection Error", JOptionPane.WARNING_MESSAGE);

            return;

        }

        

        int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this reservation?", "Confirm Delete", JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {

            Reservation reservation = reservations.get(selectedRow);

            if (reservationDAO.deleteReservation(reservation.getReservationId())) {

                reservations.remove(selectedRow);

                tableModel.removeRow(selectedRow);

                updateTotalFare();

                JOptionPane.showMessageDialog(this, "Reservation deleted successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);

            } else {

                JOptionPane.showMessageDialog(this, "Failed to delete reservation from database!", "Database Error", JOptionPane.ERROR_MESSAGE);

            }

        }

    }

    

    private void setupTable() {

        String[] columns = {"ID", "Customer", "Pickup", "Destination", "Distance (km)", "Vehicle Type", "Driver", "Fare (₱)"};

        tableModel = new DefaultTableModel(columns, 0) {

            @Override

            public boolean isCellEditable(int row, int column) {

                return false;

            }

        };

        tblReservations.setModel(tableModel);

    }

    

    private Vehicle createVehicle(String type) {

        String[] driverNames = {"Juan", "Maria", "Pedro", "Rosa", "Carlos"};

        String driver = driverNames[(int)(Math.random() * driverNames.length)];

        String vehicleId = "V" + (int)(Math.random() * 9000 + 1000);

        

        switch (type) {

            case "Premium Taxi": return new PremiumTaxi(vehicleId, driver);

            case "Shared Taxi": return new SharedTaxi(vehicleId, driver);

            default: return new StandardTaxi(vehicleId, driver);

        }

    }

    

    private void addReservationToTable(Reservation r) {

        Object[] row = {

            r.getReservationId(), r.getCustomerName(), r.getPickupLocation(), r.getDestination(),

            r.getDistance(), r.getVehicle().getVehicleType(), r.getVehicle().getDriverName(),

            String.format("%.2f", r.getFare())

        };

        tableModel.addRow(row);

    }

    

    private void updateTotalFare() {

        double total = reservations.stream().mapToDouble(Reservation::getFare).sum();

        lblTotalFare.setText(String.format("Total Revenue: ₱%.2f", total));

    }

    

    private void clearFields() {

        txtCustomerName.setText("");

        txtPickup.setText("");

        txtCustomerName2.setText("");

        txtDistance.setText("");

        cmbVehicleType.setSelectedIndex(0);

        txtCustomerName.requestFocus();

    }



    public static void main(String args[]) {

        try {

            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {

                if ("Nimbus".equals(info.getName())) {

                    javax.swing.UIManager.setLookAndFeel(info.getClassName());

                    break;

                }

            }

        } catch (Exception ex) {

            java.util.logging.Logger.getLogger(TaxiReservationGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);

        }

        

        java.awt.EventQueue.invokeLater(() -> new TaxiReservationGUI().setVisible(true));

    }



    // Variables declaration

    private BackgroundPanel backgroundPanel;

    private javax.swing.JButton btnBook;

    private javax.swing.JButton btnClear;

    private javax.swing.JButton btnDelete;

    private javax.swing.JComboBox<String> cmbVehicleType;

    private javax.swing.JLabel jLabel1, jLabel2, jLabel3, jLabel4, jLabel5, jLabel6, jLabel7, jLabel8, jLabel9;

    private javax.swing.JPanel jPanel1, jPanel2, jPanel3, jPanel4;

    private javax.swing.JScrollPane jScrollPane1;

    private javax.swing.JSeparator jSeparator1, jSeparator2, jSeparator3;

    private javax.swing.JLabel lblTotalFare;

    private javax.swing.JTable tblReservations;

    private javax.swing.JTextField txtCustomerName, txtCustomerName2, txtDistance, txtPickup;

}