/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import context.DBContext;
import entity.User_;
import java.sql.Connection;
import java.sql.ResultSet;

/**
 *
 * @author Admin
 */
public class UserDAO {

    public User_ list(String userName, String passWord) throws Exception {
        User_ user = null;
        
        try (Connection conn = new DBContext().getConnection()) {
            String query = "SELECT * FROM Users";
            System.out.println("bbbb----------");
            try (ResultSet resultSet = conn
                    .prepareStatement(query)
                    .executeQuery()) {
                while (resultSet.next()) {
                   String userName_ = resultSet.getString("username");
                   String passWord_ = resultSet.getString("password");
                    System.out.println(userName_);
                   if(userName.equalsIgnoreCase(userName_) && passWord.equals(passWord_)){
                       user = new User_(userName, passWord);
                       return user;
                   }
                }
            }
        }

        return user;
    }
    
}
