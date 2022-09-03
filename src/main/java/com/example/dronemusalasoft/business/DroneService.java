package com.example.dronemusalasoft.business;


import com.example.dronemusalasoft.persistence.DAOFactory;
import com.example.dronemusalasoft.persistence.DroneDAO;
import com.example.dronemusalasoft.persistence.MedicationDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DroneService {

    private DroneDAO droneDAO;
    private MedicationDAO medicationDAO;
    private DAOFactory mysqlFactory;

    @Autowired
    public DroneService(){
        mysqlFactory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
        droneDAO = mysqlFactory.getDroneDAO();
        medicationDAO = mysqlFactory.getMedicationDAO();
    }

    public void createDroneTable(){
        droneDAO.createTable();
    }

    public void dropDroneTable(){
        droneDAO.dropTable();
    }

    public void createMedicationTable(){
        medicationDAO.createTable();
    }

    public void dropMedicationTable(){
        medicationDAO.dropTable();
    }

    public boolean registerDrone(Drone drone) {
        droneDAO.add(drone);
        return true;
    }

    public Drone getDrone(String id) {
        return droneDAO.get(id);
    }

    public Drone removeDrone(String sn){
        Drone drone = getDrone(sn);
        System.out.println(drone);
        removeMedicationByDrone(drone);
        droneDAO.remove(String.valueOf(drone.getId()));
        return drone;
    }

    public List<Drone> allDrones(){
        return droneDAO.getAll();
    }

    public void loadADrone(String droneID, Medication item) {
        Drone drone = droneDAO.get(droneID);
        item.setDroneId(drone.getId());
        medicationDAO.add(item);
    }

    public List<Medication> medicationsByDroneID(String id){
        Drone drone = droneDAO.get(id);
        return medicationDAO.getAll(String.valueOf(drone.getId()));
    }

    public Medication deliverMedication(String code){
        Medication medication = medicationDAO.get(code);
        return medication;
    }

    public void removeMedication(Medication medication){
        medicationDAO.remove(medication.getId());
    }

    public void removeMedicationByDrone(Drone drone){
        medicationDAO.removeByDroneId(drone.getId());
    }


    public List<Medication> checkLoadedMedicationItems(String droneId) {
        return medicationsByDroneID(droneId);
    }

    public List<Drone> checkAvailableDronesForLoading() {
        List<Drone> list = allDrones();
        List<Drone> loadedListed = new ArrayList<>();
        for (Drone d: list
             ) {
            if(d.getState() == State.LOADING){
                d.setMedicationList(medicationsByDroneID(d.getSerial_number()));
                loadedListed.add(d);
            }
        }
        return loadedListed;
    }

    public int checkDroneBatteryLevel(String droneId){
        Drone drone = getDrone(droneId);
        return drone.getBattery_capacity();
    }

    public Drone updateState(Drone drone){
        droneDAO.updateState(drone);
        return drone;
    }


}
