/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import context.DBContext;
import entity.Role;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class RoleDAO {
    
    
    public List<Role> list(String userName) throws Exception {
        List<Role> roles;
        try (Connection conn = new DBContext().getConnection()) {
            roles = new ArrayList<>();
            String query = "select R.* from Role_User RU\n"
                    + "inner join Roles R on R.roleid = RU.roleid\n"
                    + "where RU.username = ?";

            PreparedStatement ps = conn
                    .prepareStatement(query);
            ps.setString(1, userName);
            ResultSet resultSet = ps.executeQuery();
            
            while (resultSet.next()) {
                int id = resultSet.getInt("roleid");
                String roleName = resultSet.getString("rolename");
                roles.add(new Role(id, roleName));
            }
        }

        return roles;
    }
}
