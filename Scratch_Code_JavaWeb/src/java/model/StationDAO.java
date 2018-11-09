/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import context.DBContext;
import entity.Station;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class StationDAO {

    public List<Station> list(String stationName) throws Exception {
        List<Station> stations;
        try (Connection conn = new DBContext().getConnection()) {
            stations = new ArrayList<>();
            String query = "SELECT * FROM RailwayStations where StationName like N'%"+stationName+"%'";
            try (ResultSet resultSet = conn
                    .prepareStatement(query)
                    .executeQuery()) {
                while (resultSet.next()) {
                    int id = resultSet.getInt("StationID");
                    String name = resultSet.getString("StationName");
                    
                    stations.add(new Station(id, name));

                }
            }
        }

        return stations;
    }
    
    public void setTrainStops(List<Station> stations) throws Exception {
        TrainStopDAO trainStopDAO = new TrainStopDAO();
        
        for (Station station : stations) {
            station.setTrainStops(trainStopDAO.list(station.getId()));
        }
    }
        
}
