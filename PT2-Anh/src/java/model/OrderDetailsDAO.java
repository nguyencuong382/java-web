/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import context.DBContext;
import entity.OrderDetails;
import entity.Orders;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class OrderDetailsDAO {

    public List<OrderDetails> list() throws Exception {
        List<OrderDetails> orders;
        try (Connection conn = new DBContext().getConnection()) {
            orders = new ArrayList<>();
            String query = "select * from [OrderDetails]";

            PreparedStatement ps = conn
                    .prepareStatement(query);
            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                int orderId = resultSet.getInt("orderId");
                int itemCode = resultSet.getInt("ItemCode");
                String page = resultSet.getString("Page");
                int quanity = resultSet.getInt("Quantity");
                float price = resultSet.getFloat("Price");

                orders.add(new OrderDetails(orderId, itemCode, page, quanity, price));
            }
        }

        return orders;
    }

    public List<OrderDetails> list(int orderID) throws Exception {
        List<OrderDetails> orders;
        try (Connection conn = new DBContext().getConnection()) {
            orders = new ArrayList<>();
            String query = "select * from OrderDetails where OrderID = " + orderID;

            PreparedStatement ps = conn
                    .prepareStatement(query);
            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                int orderId = resultSet.getInt("orderId");
                int itemCode = resultSet.getInt("ItemCode");
                String page = resultSet.getString("Page");
                int quanity = resultSet.getInt("Quantity");
                float price = resultSet.getFloat("Price");

                orders.add(new OrderDetails(orderId, itemCode, page, quanity, price));
            }
        }

        return orders;
    }

    public OrderDetails save(OrderDetails orders) throws Exception {
        Connection con = new DBContext().getConnection();
        String query = "INSERT INTO [PT2].[dbo].[OrderDetails]\n"
                + "           ([OrderID]\n"
                + "           ,[ItemCode]\n"
                + "           ,[Page]\n"
                + "           ,[Quantity]\n"
                + "           ,[Price])\n"
                + "     VALUES\n"
                + "           (?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?)\n";

        PreparedStatement ps = con.prepareStatement(query);

        ps.setInt(1, orders.getOrderId());
        ps.setInt(2, orders.getItemCode());
        ps.setString(3, orders.getPage());
        ps.setInt(4, orders.getQuantity());
        ps.setFloat(5, orders.getPrice());

        int n = ps.executeUpdate();

        con.close();
        return orders;
    }
}
