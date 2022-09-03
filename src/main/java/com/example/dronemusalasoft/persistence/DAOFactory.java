package com.example.dronemusalasoft.persistence;

public abstract class DAOFactory {
    // List of DAO types supported by the factory
    public static final int SQLITE3 = 1;
    public static final int MYSQL = 2;

    // There will be a method for each DAO that can be
    // created. The concrete factories will have to
    // implement these methods.
    public abstract DroneDAO getDroneDAO();

    public abstract MedicationDAO getMedicationDAO();

    public static DAOFactory getDAOFactory(int whichFactory) {
        switch (whichFactory) {
            case MYSQL:
                return new MYSQLDAOFactory();
            default:
                return null;
        }
    }
}
