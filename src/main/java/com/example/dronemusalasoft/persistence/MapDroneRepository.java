package com.example.dronemusalasoft.persistence;


import com.example.dronemusalasoft.business.Drone;

import java.util.*;

public class MapDroneRepository implements IDroneRepository{

    private Map<String, Drone> fleet;

    public MapDroneRepository(Store store){
        this.fleet = store.getInstance().getFleet();
    }

    @Override
    public Drone add(Drone drone) {
        if(fleet.size() >= 10){
            return null;
        }
        return fleet.put(drone.getSerial_number(), drone);
    }

    @Override
    public boolean remove(String id) {

        Drone d = fleet.remove(id);
        if(d == null){
            return false;
        }
        return true;
    }

    @Override
    public Drone get(String id) {
        return fleet.get(id);
    }

    @Override
    public void clear() {
        fleet.clear();
    }

    @Override
    public List<Drone> getAll() {
        List<Drone> list = new ArrayList<>();
        for(Map.Entry<String, Drone> m: fleet.entrySet()){
            list.add(m.getValue());
        }
        Collections.sort(list, (o1, o2) -> o1.getSerial_number().compareTo(o2.getSerial_number()));
        return list;
    }


}
