package com.example.dronemusalasoft.persistence;


import com.example.dronemusalasoft.business.Medication;

import java.util.List;

public interface MedicationDAO {
    public Medication add(Medication medication);
    public boolean remove(int id);
    public boolean removeByDroneId(int id);
    public Medication get(String code);
    public void clear();
    public List<Medication> getAll(String droneId);
    public void createTable();
    public void dropTable();
}
