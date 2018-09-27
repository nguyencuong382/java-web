/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;

/**
 *
 * @author Admin
 */
public class TrainStop implements Serializable{
    private String trainCode;
    private int stationId;
    private String departureTime;
    private String arrivalTime;
    private int stopOrder;

    public TrainStop() {
    }

    public TrainStop(String trainCode, int stationId, String departureTime, String arrivalTime, int stopOrder) {
        this.trainCode = trainCode;
        this.stationId = stationId;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.stopOrder = stopOrder;
    }

    public String getTrainCode() {
        return trainCode;
    }

    public void setTrainCode(String trainCode) {
        this.trainCode = trainCode;
    }

    public int getStationId() {
        return stationId;
    }

    public void setStationId(int stationId) {
        this.stationId = stationId;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public int getStopOrder() {
        return stopOrder;
    }

    public void setStopOrder(int stopOrder) {
        this.stopOrder = stopOrder;
    }
    
    
}
