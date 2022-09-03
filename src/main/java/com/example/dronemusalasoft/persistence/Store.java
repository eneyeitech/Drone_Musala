package com.example.dronemusalasoft.persistence;

import com.example.dronemusalasoft.business.Drone;

import java.util.HashMap;
import java.util.Map;

public class Store {
    private static Store instance;
    private Map<String, Drone> fleet = new HashMap<>(10);

    private Store(){

    }

    public static Store getInstance(){
        if(instance == null){
            instance = new Store();
        }
        return instance;
    }

    public Map<String, Drone> getFleet(){
        return fleet;
    }
}
