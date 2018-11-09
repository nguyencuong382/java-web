/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import context.DBContext;
import entity.Student;
import java.sql.Connection;
import java.sql.PreparedStatement;
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

    public Student save(Student newStudent) throws Exception {
        System.out.println("-------------- 1");
        Connection con = new DBContext().getConnection();
        String query = "INSERT INTO Student"
                + "           (Name"
                + "           ,ClassID"
                + "           ,Gender"
                + "           ,DOB)"
                + "     VALUES"
                + "           (?"
                + "           ,?"
                + "           ,?"
                + "           ,?)";
        
        PreparedStatement ps = con.prepareStatement(query);
        java.sql.Date sqlDate = null;
        System.out.println(newStudent.getDOB().getTime());
        try{
            sqlDate = new java.sql.Date( newStudent.getDOB().getTime());
        }catch (Exception e){
            System.out.println(e);
        }
        ps.setString(1, newStudent.getName());
        ps.setInt(2, newStudent.getClassId());
        ps.setBoolean(3, newStudent.isGender());
        ps.setDate(4, sqlDate);

        int n = ps.executeUpdate();
        
        System.out.println("Row effeced " + n);

        con.close();
        
        return newStudent;
    }
}
