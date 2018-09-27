/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Admin
 */
public class Station implements Serializable{
    private int id;
    private String name;
    private List<TrainStop> trainStops;

    public Station() {
    }

    public Station(int id, String name) {
        this.id = id;
        this.name = name;
    }
    
    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<TrainStop> getTrainStops() {
        return trainStops;
    }

    public void setTrainStops(List<TrainStop> trainStops) {
        this.trainStops = trainStops;
    }
    
    

    @Override
    public String toString() {
        return this.id + " " + this.name;
    }
    
    
}
