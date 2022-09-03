package com.example.dronemusalasoft.business;

public class DroneBatteryController implements DroneController{

    private Drone droneToControl;

    public DroneBatteryController(Drone droneToControl){
        this.droneToControl = droneToControl;
    }

    public void dischargeBattery(int dischargeRate){
        int batteryLevel = droneToControl.getBattery_capacity();
        if((batteryLevel - dischargeRate) > 0){
            droneToControl.setBattery_capacity(batteryLevel - dischargeRate);
        }
        droneToControl.setDroneState();
    }

    public void chargeBattery(int chargeRate){
        int batteryLevel = droneToControl.getBattery_capacity();
        if((batteryLevel + chargeRate ) < 100){
            droneToControl.setBattery_capacity(batteryLevel + chargeRate);
        }
        droneToControl.setDroneState();
    }

    public int getBatteryLevel(){
        return droneToControl.getBattery_capacity();
    }
}
