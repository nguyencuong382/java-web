/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import context.DBContext;
import entity.Item;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class ItemDAO {

    public List<Item> list() throws Exception {
        List<Item> items;
        try (Connection conn = new DBContext().getConnection()) {
            items = new ArrayList<>();
            String query = "select * from Items";

            PreparedStatement ps = conn
                    .prepareStatement(query);

            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                int itemCode = resultSet.getInt("ItemCode");
                String name = resultSet.getString("name");
                float price = resultSet.getFloat("price");

                items.add(new Item(itemCode, name, price));
            }
        }

        return items;
    }

    public Item get(int itemCode) throws Exception {
        Item items = null;
        try (Connection conn = new DBContext().getConnection()) {
            String query = "select * from Items where ItemCode = " + itemCode;

            PreparedStatement ps = conn
                    .prepareStatement(query);

            ResultSet resultSet = ps.executeQuery();

            if (resultSet.next()) {
                String name = resultSet.getString("name");
                float price = resultSet.getFloat("price");

                items = new Item(itemCode, name, price);
            }
        }

        return items;
    }
}
