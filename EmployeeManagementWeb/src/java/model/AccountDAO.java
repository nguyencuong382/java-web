/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import context.DBContext;
import entity.Account;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

/**
 *
 * @author Admin
 */
public class AccountDAO {

    public List<Account> list(int employeeId) throws Exception {
        List<Account> accounts;
        try (Connection conn = new DBContext().getConnection()) {
            accounts = new ArrayList<>();
            String query = "SELECT * FROM Account where EmployeeID = " + employeeId;
            try (ResultSet resultSet = conn
                    .prepareStatement(query)
                    .executeQuery()) {
                while (resultSet.next()) {
                    
                    String userId = resultSet.getString("userId");
                    String displayName = resultSet.getString("displayName");
                    Date joinedDate = resultSet.getDate("joinedDate");
                    boolean active = resultSet.getBoolean("active");

                    accounts.add(new Account(userId, displayName, joinedDate, active, employeeId));
                }
            }
        }

        return accounts;
    }
}
