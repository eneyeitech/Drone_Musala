package com.example.dronemusalasoft.persistence;



import com.example.dronemusalasoft.business.Drone;
import com.example.dronemusalasoft.business.Model;
import com.example.dronemusalasoft.business.State;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MYSQLDroneDAO implements DroneDAO{
    @Override
    public Drone add(Drone drone) {
        String insertPaymentSQL = "INSERT INTO DRONE " +
                "(SERIAL_NUMBER, MODEL, WEIGHT_LIMIT, BATTERY_CAPACITY , STATE) VALUES (?,?,?,?,?)";

        try (Connection con = MYSQLDAOFactory.createConnection()) {

            try (PreparedStatement insertPayment = con.prepareStatement(insertPaymentSQL);) {


                // Insert a payment
                insertPayment.setString(1, drone.getSerial_number());
                insertPayment.setString(2, drone.getModel().toString());
                insertPayment.setInt(3, drone.getWeight_limit());
                insertPayment.setInt(4, drone.getBattery_capacity());
                insertPayment.setString(5, drone.getState().toString());
                int k = insertPayment.executeUpdate();

                System.out.println(drone + " saved "+ k);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return drone;
    }

    @Override
    public Drone updateState(Drone drone) {
        String updatePaymentSQL = "UPDATE DRONE " +
                "SET STATE = ? WHERE ID = ?";

        try (Connection con = MYSQLDAOFactory.createConnection()) {

            try (PreparedStatement insertPayment = con.prepareStatement(updatePaymentSQL);) {

                // Insert a payment
                insertPayment.setString(1, drone.getState().toString());
                insertPayment.setInt(2, drone.getId());
                insertPayment.executeUpdate();
                System.out.println(drone.getSerial_number() + " updated");

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return drone;
    }

    @Override
    public boolean remove(String id) {
        int drone_id = Integer.parseInt(id);
        String updatePaymentSQL = "DELETE FROM DRONE " +
                "WHERE ID = ?";

        try (Connection con = MYSQLDAOFactory.createConnection()) {

            try (PreparedStatement insertPayment = con.prepareStatement(updatePaymentSQL);) {

                // Delete a card
                insertPayment.setInt(1, drone_id);
                insertPayment.executeUpdate();
                System.out.println("Drone with id " + drone_id + " deleted");

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public Drone get(String id) {

        Drone drone = null;
        String updatePaymentSQL = "SELECT * FROM DRONE " +
                "WHERE SERIAL_NUMBER = ?";

        try (Connection con = MYSQLDAOFactory.createConnection()) {

            try (PreparedStatement insertPayment = con.prepareStatement(updatePaymentSQL);) {
                // Insert a payment
                insertPayment.setString(1, id);

                ResultSet rs = insertPayment.executeQuery();
                while (rs.next()) {
                    drone = new Drone();
                    drone.setId(rs.getInt(1));
                    drone.setSerial_number(rs.getString(2));
                    drone.setModel(Model.valueOf(rs.getString(3)));
                    drone.setWeight_limit(rs.getInt(4));
                    drone.setBattery_capacity(rs.getInt(5));
                    drone.setState(State.valueOf(rs.getString(6)));
                    break;
                }
                System.out.println(" query successful");

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return drone;

    }

    @Override
    public void clear() {

    }

    @Override
    public List<Drone> getAll() {
        List<Drone> droneList = new ArrayList<>();

        String updatePaymentSQL = "SELECT * FROM DRONE " +
                "";

        try (Connection con = MYSQLDAOFactory.createConnection()) {

            try (PreparedStatement insertPayment = con.prepareStatement(updatePaymentSQL);) {
                // Insert a payment

                ResultSet rs = insertPayment.executeQuery();
                while (rs.next()) {
                    Drone drone = new Drone();
                    drone.setId(rs.getInt(1));
                    drone.setSerial_number(rs.getString(2));
                    drone.setModel(Model.valueOf(rs.getString(3)));
                    drone.setWeight_limit(rs.getInt(4));
                    drone.setBattery_capacity(rs.getInt(5));
                    drone.setState(State.valueOf(rs.getString(6)));
                    droneList.add(drone);
                    //break;
                }
                System.out.println(" query successful");

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return droneList;

    }

    @Override
    public void createTable() {
        try (Statement statement = MYSQLDAOFactory.createConnection().createStatement();){
            statement.execute("create table if not exists DRONE(" +
                    "ID INT NOT NULL AUTO_INCREMENT," +
                    " SERIAL_NUMBER VARCHAR(255)," +
                    " MODEL VARCHAR(255)," +
                    " WEIGHT_LIMIT INT," +
                    " BATTERY_CAPACITY INT," +
                    " STATE VARCHAR(255)," +
                    " PRIMARY KEY (ID)" +
                    ")");
            System.out.println("Drone table created.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void dropTable() {
        try (Statement statement = MYSQLDAOFactory.createConnection().createStatement();){
            statement.execute("drop table DRONE if exists");
            System.out.println("Drone table dropped.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
