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
public class OrdersDAO {

    public List<Orders> list() throws Exception {
        List<Orders> orders;
        try (Connection conn = new DBContext().getConnection()) {
            orders = new ArrayList<>();
            String query = "select * from Orders";

            PreparedStatement ps = conn
                    .prepareStatement(query);
            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                int orderId = resultSet.getInt("orderId");
                boolean isCash = resultSet.getBoolean("CashOrCard");
                String currency = resultSet.getString("Currency");
                String seatNo = resultSet.getString("SeatNo");
                float total = resultSet.getFloat("TotalAmount");
                
                orders.add(new Orders(orderId, isCash, currency, seatNo, total));
            }
        }

        return orders;
    }

    public Orders save(Orders orders) throws Exception {
        System.out.println("-------------- 1");
        Connection con = new DBContext().getConnection();
        String query = "INSERT INTO [Orders]"
                + "           ("
                + "           [CashOrCard]"
                + "           ,[Currency]"
                + "           ,[SeatNo]"
                + "           ,[TotalAmount])"
                + "     VALUES"
                + "           (?"
                + "           ,?"
                + "           ,?"
                + "           ,?)";

        PreparedStatement ps = con.prepareStatement(query);

        ps.setBoolean(1, orders.isCashOrCard());
        ps.setString(2, orders.getCurrency());
        ps.setString(3, orders.getSeatNo());
        ps.setFloat(4, orders.getTotalAmount());

        int n = ps.executeUpdate();

        query = "SELECT TOP 1 * FROM Orders ORDER BY OrderID DESC";
        ps = con.prepareStatement(query);

        ResultSet resultSet = ps.executeQuery();

        if (resultSet.next()) {
            int id = resultSet.getInt("OrderID");
            orders.setOrderId(id);
        }
        
        con.close();
        return orders;
    }
}
