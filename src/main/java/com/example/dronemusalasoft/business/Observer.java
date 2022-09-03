package com.example.dronemusalasoft.business;

public class Observer implements IObserver{

    DroneService service = new DroneService();

    @Override
    public void update(Object o) {
        service.updateState((Drone) o);
    }
}
