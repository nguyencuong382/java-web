/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import entity.Station;
import java.io.Serializable;
import java.util.List;
import model.StationDAO;

/**
 *
 * @author Admin
 */
public class StationBean implements Serializable{
    
    private String search;
    
    public StationBean() {
        search = "";
    }

    public StationBean(String search) {
        this.search = search;
    }
    
    public List<Station> getStations() throws Exception {
        return new StationDAO().list(search);
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }
    
    
}
