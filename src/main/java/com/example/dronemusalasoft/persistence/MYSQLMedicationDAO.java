package com.example.dronemusalasoft.persistence;


import com.example.dronemusalasoft.business.Medication;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MYSQLMedicationDAO implements MedicationDAO{
    @Override
    public Medication add(Medication medication) {
        String insertPaymentSQL = "INSERT INTO MEDICATION " +
                "(NAME, WEIGHT, CODE, IMAGE , DRONE_ID) VALUES (?,?,?,?,?)";

        try (Connection con = MYSQLDAOFactory.createConnection()) {

            try (PreparedStatement insertPayment = con.prepareStatement(insertPaymentSQL);) {


                // Insert a payment
                insertPayment.setString(1, medication.getName());
                insertPayment.setInt(2, medication.getWeight());
                insertPayment.setString(3, medication.getCode());
                insertPayment.setString(4, medication.getImage());
                insertPayment.setInt(5, medication.getDroneId());
                int k = insertPayment.executeUpdate();

                System.out.println(medication + " saved "+ k);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return medication;
    }

    @Override
    public boolean remove(int id) {

        String updatePaymentSQL = "DELETE FROM MEDICATION " +
                "WHERE ID = ?";

        try (Connection con = MYSQLDAOFactory.createConnection()) {

            try (PreparedStatement insertPayment = con.prepareStatement(updatePaymentSQL);) {

                // Delete a card
                insertPayment.setInt(1, id);
                insertPayment.executeUpdate();
                System.out.println("Medication with id " + id + " deleted");

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public boolean removeByDroneId(int id) {
        String updatePaymentSQL = "DELETE FROM MEDICATION " +
                "WHERE DRONE_ID = ?";

        try (Connection con = MYSQLDAOFactory.createConnection()) {

            try (PreparedStatement insertPayment = con.prepareStatement(updatePaymentSQL);) {

                // Delete a card
                insertPayment.setInt(1, id);
                insertPayment.executeUpdate();
                System.out.println("Medications with drone id " + id + " deleted");

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public Medication get(String code) {
        Medication medication = null;
        String updatePaymentSQL = "SELECT * FROM MEDICATION " +
                "WHERE CODE = ?";

        try (Connection con = MYSQLDAOFactory.createConnection()) {

            try (PreparedStatement insertPayment = con.prepareStatement(updatePaymentSQL);) {
                // Insert a payment
                insertPayment.setString(1, code);

                ResultSet rs = insertPayment.executeQuery();
                while (rs.next()) {
                    medication = new Medication();
                    medication.setId(rs.getInt(1));
                    medication.setName(rs.getString(2));
                    medication.setWeight(rs.getInt(3));
                    medication.setCode(rs.getString(4));
                    medication.setImage(rs.getString(5));
                    medication.setDroneId(rs.getInt(6));
                    break;
                }
                System.out.println(" query successful");

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return medication;
    }

    @Override
    public void clear() {

    }

    @Override
    public List<Medication> getAll(String droneId) {
        List<Medication> medicationList = new ArrayList<>();

        String updatePaymentSQL = "SELECT * FROM MEDICATION " +
                "WHERE DRONE_ID = ?";

        try (Connection con = MYSQLDAOFactory.createConnection()) {

            try (PreparedStatement insertPayment = con.prepareStatement(updatePaymentSQL);) {
                // Insert a payment
                insertPayment.setInt(1, Integer.parseInt(droneId));

                ResultSet rs = insertPayment.executeQuery();
                while (rs.next()) {
                    Medication medication = new Medication();
                    medication.setId(rs.getInt(1));
                    medication.setName(rs.getString(2));
                    medication.setWeight(rs.getInt(3));
                    medication.setCode(rs.getString(4));
                    medication.setImage(rs.getString(5));
                    medication.setDroneId(rs.getInt(6));
                    medicationList.add(medication);
                    //break;
                }
                System.out.println(" query successful");

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return medicationList;
    }

    @Override
    public void createTable() {
        try (Statement statement = MYSQLDAOFactory.createConnection().createStatement();){
            statement.execute("create table if not exists MEDICATION(" +
                    "ID INT NOT NULL AUTO_INCREMENT," +
                    " NAME VARCHAR(255)," +
                    " WEIGHT INT," +
                    " CODE VARCHAR(255) NOT NULL UNIQUE," +
                    " IMAGE VARCHAR(255)," +
                    " DRONE_ID INT," +
                    " PRIMARY KEY (ID)," +
                    " FOREIGN KEY (DRONE_ID) REFERENCES DRONE(ID)" +
                    ")");
            System.out.println("Medication table created.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void dropTable() {
        try (Statement statement = MYSQLDAOFactory.createConnection().createStatement();){
            statement.execute("drop table MEDICATION if exists");
            System.out.println("Medication table dropped.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
