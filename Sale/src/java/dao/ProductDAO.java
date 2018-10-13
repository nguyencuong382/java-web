/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import context.DBContext;
import entity.Product;
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
public class ProductDAO {
    
    public List<Product> list() throws Exception {
        List<Product> products;
        try (Connection conn = new DBContext().getConnection()) {
            products = new ArrayList<>();
            String query = "select * from ProductTBL";
            try (ResultSet resultSet = conn
                    .prepareStatement(query)
                    .executeQuery()) {
                while (resultSet.next()) {
                    int productId = resultSet.getInt("ProductID");
                    String productName = resultSet.getString("ProductName");
                    double unitPrice = resultSet.getDouble("UnitPrice");
                    products.add(new Product(productId, productName, unitPrice));
                }
            }
        }

        return products;
    }

    public List<Product> list(int groupId) throws Exception {
        List<Product> products;
        try (Connection conn = new DBContext().getConnection()) {
            products = new ArrayList<>();
            String query = "select * from ProductTBL where ClassID  = " + groupId;
            try (ResultSet resultSet = conn
                    .prepareStatement(query)
                    .executeQuery()) {
                while (resultSet.next()) {
                    int id = resultSet.getInt("ID");
                    String name = resultSet.getString("name");
                    Date DOB = resultSet.getDate("DOB");
                    boolean gender = resultSet.getBoolean("Gender");

                }
            }
        }

        return products;
    }

//    public Product save(Product newProduct) throws Exception {
//        System.out.println("-------------- 1");
//        Connection con = new DBContext().getConnection();
//        String query = "INSERT INTO Product"
//                + "           (Name"
//                + "           ,ClassID"
//                + "           ,Gender"
//                + "           ,DOB)"
//                + "     VALUES"
//                + "           (?"
//                + "           ,?"
//                + "           ,?"
//                + "           ,?)";
//
//        PreparedStatement ps = con.prepareStatement(query);
//        java.sql.Date sqlDate = null;
//        try {
//            sqlDate = new java.sql.Date(newProduct.getDOB().getTime());
//        } catch (Exception e) {
//            System.out.println(e);
//        }
//        ps.setString(1, newProduct.getName());
//        ps.setInt(2, newProduct.getClassId());
//        ps.setBoolean(3, newProduct.isGender());
//        ps.setDate(4, sqlDate);
//
//        int n = ps.executeUpdate();
//
//        System.out.println("Row effeced " + n);
//
//        con.close();
//
//        return newProduct;
//    }
}
