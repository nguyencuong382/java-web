/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import context.DBContext;
import entity.Student;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Admin
 */
public class StudentDAO {
    public List<Student> list(int groupId) throws Exception {
        List<Student> students;
        try (Connection conn = new DBContext().getConnection()) {
            students = new ArrayList<>();
            String query = "select * from Student where ClassID  = " + groupId;
            try (ResultSet resultSet = conn
                    .prepareStatement(query)
                    .executeQuery()) {
                while (resultSet.next()) {
                    int id = resultSet.getInt("ID");
                    String name = resultSet.getString("name");
                    Date DOB = resultSet.getDate("DOB");
                    boolean gender = resultSet.getBoolean("Gender");
                    
                    students.add(new Student(id, name, id, gender, DOB));
                }
            }
        }

        return students;
    }
}
