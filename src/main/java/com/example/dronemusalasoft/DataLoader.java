package com.example.dronemusalasoft;

import com.example.dronemusalasoft.business.DroneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DataLoader {

    private DroneService droneService;

    @Autowired
    public DataLoader(DroneService droneService) {
        this.droneService = droneService;
        createTables();
    }

    private void createTables() {
        try {
            System.out.println("Creating tables... ...");
            droneService.createDroneTable();
            droneService.createMedicationTable();
        } catch (Exception e) {
            System.out.println("Error occurred creating tables.");
        }
    }
}
