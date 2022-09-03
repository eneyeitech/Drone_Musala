package com.example.dronemusalasoft.persistence;


import com.example.dronemusalasoft.business.Drone;

import java.util.List;

public interface IDroneRepository {
    public Drone add(Drone drone);
    public boolean remove(String id);

    public Drone get(String id);
    public void clear();
    public List<Drone> getAll();
}
