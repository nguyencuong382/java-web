/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import context.DBContext;
import entity.TrainStop;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class TrainStopDAO {

    public List<TrainStop> list(int stationId) throws Exception {
        List<TrainStop> trainStops;

        try (Connection conn = new DBContext().getConnection()) {
            trainStops = new ArrayList<>();
            String query = "SELECT * FROM [TrainStops] where StationID = ?";
            PreparedStatement ps = conn
                    .prepareStatement(query);
            ps.setInt(1, stationId);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {

                String trainCode = resultSet.getString("TrainCode");
                int stationId_ = resultSet.getInt("StationID");
                String departureTime = resultSet.getString("DepartureTime");
                String arrivalTime = resultSet.getString("ArrivalTime");
                int stopOrder = resultSet.getInt("StopOrder");

                trainStops.add(new TrainStop(trainCode, stationId_, departureTime, arrivalTime, stopOrder));
            }
            resultSet.close();
            conn.close();
        }

        return trainStops;
    }
    
    public List<TrainStop> list(String trainCode) throws Exception {
        List<TrainStop> trainStops;

        try (Connection conn = new DBContext().getConnection()) {
            trainStops = new ArrayList<>();
            String query = "SELECT * FROM [TrainStops] where TrainCode = ?";
            PreparedStatement ps = conn
                    .prepareStatement(query);
            ps.setString(1, trainCode);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {

                String trainCode_ = resultSet.getString("TrainCode");
                int stationId = resultSet.getInt("StationID");
                String departureTime = resultSet.getString("DepartureTime");
                String arrivalTime = resultSet.getString("ArrivalTime");
                int stopOrder = resultSet.getInt("StopOrder");

                trainStops.add(new TrainStop(trainCode_, stationId, departureTime, arrivalTime, stopOrder));
            }
            resultSet.close();
            conn.close();
        }

        return trainStops;
    }
    
    
    public List<TrainStop> list() throws Exception {
        List<TrainStop> trainStops;

        try (Connection conn = new DBContext().getConnection()) {
            trainStops = new ArrayList<>();
            String query = "SELECT * FROM [TrainStops]";
            PreparedStatement ps = conn
                    .prepareStatement(query);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {

                String trainCode = resultSet.getString("TrainCode");
                int stationId = resultSet.getInt("StationID");
                String departureTime = resultSet.getString("DepartureTime");
                String arrivalTime = resultSet.getString("ArrivalTime");
                int stopOrder = resultSet.getInt("StopOrder");

                trainStops.add(new TrainStop(trainCode, stationId, departureTime, arrivalTime, stopOrder));
            }
            resultSet.close();
            conn.close();
        }

        return trainStops;
    }
}
