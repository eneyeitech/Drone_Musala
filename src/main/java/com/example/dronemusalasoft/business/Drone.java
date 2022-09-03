package com.example.dronemusalasoft.business;

import java.util.ArrayList;
import java.util.List;

public class Drone {

    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private String serial_number;
    private Model model;
    private int weight_limit;
    private int battery_capacity;
    private State state;
    private List<Medication> medicationList;
    private IObserver observer;

    {
        medicationList = new ArrayList<>();
        observer = new Observer();
    }

    public Drone(){

    }

    public Drone(String sn){
        serial_number = sn;
        //medicationList = new ArrayList<>();
    }

    public Drone(String sn, int weight){
        serial_number = sn;
        weight_limit = weight;
        setModelType(weight);
        setBattery_capacity(100);
        setDroneState();
        //medicationList = new ArrayList<>();
    }

    public void init(){
        setModelType(getWeight_limit());
        setBattery_capacity(100);
        setDroneState();
    }
    
    private void setModelType(int weight){
        if (weight > 1000){
            setModel(Model.Heavyweight);

        } else if (weight > 500) {
            setModel(Model.Cruiserweight);
        } else if (weight > 200) {
            setModel(Model.Middleweight);
        } else {
            setModel(Model.Lightweight);
        }
    }

    public void setDroneState(){
        int currentLoadedWeight = getCurrentLoadedWeight();
        int batteryLevel = getBattery_capacity();
        if(getState() != State.LOADED) {
            if (batteryLevel >= 25 && currentLoadedWeight < weight_limit) {
                setState(State.LOADING);
            } else {
                setState(State.IDLE);
            }
        }
    }

    public void changeToDelivering(){
        setState(State.DELIVERING);
        changeLoadingState();
    }

    public void changeLoadingState(){
        if(battery_capacity < 25){
            setState(State.IDLE);
        } else if(isLoaded()){
            setState(State.LOADED);
        } else if(deliveryCompleted()){
            setState(State.LOADING);
        } else {

        }
    }

    public boolean isLoaded(){
        int currentLoadedWeight = getCurrentLoadedWeight();
        if(currentLoadedWeight == weight_limit){
            return true;
        }
        return false;
    }

    public boolean deliveryCompleted(){
        int currentLoadedWeight = getCurrentLoadedWeight();
        if(currentLoadedWeight == 0){
            return true;
        }
        return false;
    }

    public String getSerial_number() {
        return serial_number;
    }

    public void setSerial_number(String serial_number) {
        this.serial_number = serial_number;
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public int getWeight_limit() {
        return weight_limit;
    }

    public void setWeight_limit(int weight_limit) {
        this.weight_limit = weight_limit;
        setModelType(weight_limit);
    }

    public int getBattery_capacity() {
        return battery_capacity;
    }

    public void setBattery_capacity(int battery_capacity) {
        this.battery_capacity = battery_capacity;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
        persist();
    }

    public boolean addMedication(Medication medication){
        if((getCurrentLoadedWeight() + medication.getWeight()) > weight_limit){
            return false;
        }

        medicationList.add(medication);
        return true;
    }

    public Medication retrieveMedication(String code){
        Medication medication = null;

        for(Medication m: medicationList){
            if(m.getCode().equalsIgnoreCase(code)){
                medication = m;
                break;
            }
        }
        return medication;
    }

    public boolean removeMedication(Medication medication){
        return medicationList.remove(medication);
    }

    private int getCurrentLoadedWeight(){
        int weight = 0;
        for(Medication m: medicationList){
            weight += m.getWeight();
        }
        return weight;
    }

    public int getRemainingWeight(){
        return (weight_limit - getCurrentLoadedWeight());
    }

    public void setMedicationList(List<Medication> medicationList) {
        this.medicationList = new ArrayList<>(medicationList);
    }

    public List<Medication> getMedicationList(){
        List<Medication> newList = new ArrayList<>(medicationList);
        return newList;
    }

    @Override
    public String toString() {
        return "Drone{" +
                "id=" + id +
                ", serial_number='" + serial_number + '\'' +
                ", model=" + model +
                ", weight_limit=" + weight_limit +
                ", battery_capacity=" + battery_capacity +
                ", state=" + state +
                ", medicationList=" + medicationList +
                '}';
    }


    public void persist(){
        observer.update(this);
    }
}
