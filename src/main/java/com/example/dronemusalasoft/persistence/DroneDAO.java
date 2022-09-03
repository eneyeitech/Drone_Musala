package com.example.dronemusalasoft.persistence;


import com.example.dronemusalasoft.business.Drone;

import java.util.List;

public interface DroneDAO {
    public Drone add(Drone drone);
    public Drone updateState(Drone drone);
    public boolean remove(String id);
    public Drone get(String id);
    public void clear();
    public List<Drone> getAll();
    public void createTable();
    public void dropTable();
}
