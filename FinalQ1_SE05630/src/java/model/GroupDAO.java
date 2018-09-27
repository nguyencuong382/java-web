/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import context.DBContext;
import entity.Group;
import entity.Station;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Admin
 */
public class GroupDAO {
    public List<Group> list(int numberOfGroup) throws Exception {
        List<Group> groups;
        try (Connection conn = new DBContext().getConnection()) {
            groups = new ArrayList<>();
            String query = "SELECT TOP "+numberOfGroup+" * FROM Groups";
            try (ResultSet resultSet = conn
                    .prepareStatement(query)
                    .executeQuery()) {
                while (resultSet.next()) {
                    int id = resultSet.getInt("ID");
                    String name = resultSet.getString("name");
                    Date date = resultSet.getDate("StartedDate");
                    boolean activated = resultSet.getBoolean("Activated");
                    
                    groups.add(new Group(id, name, date, activated));
                }
            }
        }

        return groups;
    }
}
