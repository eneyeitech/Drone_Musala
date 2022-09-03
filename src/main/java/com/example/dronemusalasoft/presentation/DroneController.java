package com.example.dronemusalasoft.presentation;

import com.example.dronemusalasoft.business.Drone;
import com.example.dronemusalasoft.business.DroneService;
import com.example.dronemusalasoft.business.Medication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class DroneController {

    @Autowired
    DroneService droneService;

    @PostMapping("api/drone/new")
    public Object addDrone(@RequestBody Drone drone){
        String sn =drone.getSerial_number();
        if(sn.isEmpty() || sn == null){
            return "serial number missing";
        }
        drone.init();
        droneService.registerDrone(drone);
        System.out.println(drone);
        return drone.getSerial_number() + " added";
    }

    @PostMapping("api/medication/{drone_serial_number}")
    public Object loadMedication(@RequestBody Medication medication, @PathVariable String drone_serial_number){
        Drone drone = droneService.getDrone(drone_serial_number);
        if(drone == null){
            return "drone does not exist";
        }
        drone.setMedicationList(droneService.medicationsByDroneID(drone.getSerial_number()));
        medication.setDroneId(drone.getId());
        boolean added = drone.addMedication(medication);
        if(!added){
            return "drone has reached its limit";
        }
        droneService.loadADrone(drone.getSerial_number(),medication);
        drone = droneService.getDrone(drone_serial_number);

        System.out.println(drone);
        System.out.println(medication);
        return "medication loaded";
    }

    @GetMapping("api/medications/{drone_serial_number}")
    public Object getLoadedMedications(@PathVariable String drone_serial_number) {
        Drone drone = droneService.getDrone(drone_serial_number);
        if(drone == null){
            return "drone does not exist";
        }
        return droneService.medicationsByDroneID(drone.getSerial_number());
    }

    @GetMapping("api/drones/loading")
    public Object dronesAvailableForLoading() {

        return droneService.checkAvailableDronesForLoading();
    }

    @GetMapping("api/battery/{drone_serial_number}")
    public Object batteryLevel(@PathVariable String drone_serial_number) {
        Drone drone = droneService.getDrone(drone_serial_number);
        if(drone == null){
            return "drone does not exist";
        }
        return drone.getBattery_capacity()+"%";
    }
}
