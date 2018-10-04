/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import context.DBContext;
import entity.Employee;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Admin
 */
public class EmployeeDAO {

    public List<Employee> list() throws Exception {
        List<Employee> employees;
        try (Connection conn = new DBContext().getConnection()) {
            employees = new ArrayList<>();
            String query = "SELECT * FROM Employees";
            try (ResultSet resultSet = conn
                    .prepareStatement(query)
                    .executeQuery()) {
                while (resultSet.next()) {
                    int employeeId = resultSet.getInt("employeeId");
                    String employeeName = resultSet.getString("employeeName");
                    Date employeeDOB = resultSet.getDate("employeeDOB");
                    boolean employeeGender = resultSet.getBoolean("employeeGender");
                    String employeeAddress = resultSet.getString("employeeAddress");

                    employees.add(new Employee(employeeId, employeeName, employeeDOB, employeeGender, employeeAddress));
                }
            }
        }

        return employees;
    }
}
