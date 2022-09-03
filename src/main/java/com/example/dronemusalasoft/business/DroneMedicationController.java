package com.example.dronemusalasoft.business;

import java.util.ArrayList;
import java.util.List;

public class DroneMedicationController implements DroneController{

    private Drone droneToLoad;

    public DroneMedicationController(Drone drone){
        droneToLoad = drone;
    }

    public boolean loadDrone(Medication medicationToAdd){
        boolean loaded = droneToLoad.addMedication(medicationToAdd);
        if(loaded){
            System.out.println("Medication added!");
        }else{
            System.out.println("Medication not added!");
        }
        return loaded;
    }



    public Medication unloadDrone(String medicationCodeToUnload){
        return droneToLoad.retrieveMedication(medicationCodeToUnload);
    }

    public void setDroneState(){
        droneToLoad.setDroneState();
    }

    public void setToDelivering(){
        droneToLoad.changeToDelivering();
    }

    public void setToLoading(){
        droneToLoad.changeLoadingState();
    }

    public boolean removeMedication(Medication medication){
        return droneToLoad.removeMedication(medication);
    }

    public boolean canLoad(){
        if(droneToLoad.getState() == State.LOADING){
            return true;
        }
        return false;
    }

    public Drone getDrone(){
        return droneToLoad;
    }
}
