/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import context.DBContext;
import dao.ProductDAO;
import entity.Product;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class ProductBean {

    int page, pageSize;

    public ProductBean() {
        page = 1;
        pageSize = 5;
    }

    public int getPages() throws Exception {
        int m = new ProductDAO().list().size();

        return m / pageSize;
    }

    public List<Product> getProducts() throws Exception {
        int from = (page - 1) * pageSize + 1;
        int to = page * pageSize;

        String store = "{call GetProducts (?, ?)}";

        Connection conn = new DBContext().getConnection();
        CallableStatement cs = conn.prepareCall(store);
        cs.setInt(1, from);
        cs.setInt(2, to);

        ResultSet resultSet = cs.executeQuery();
        List<Product> products;
        products = new ArrayList<>();
        while (resultSet.next()) {
            int productId = resultSet.getInt("ProductID");
            String productName = resultSet.getString("ProductName");
            double unitPrice = resultSet.getDouble("UnitPrice");
            products.add(new Product(productId, productName, unitPrice));
        }
        
        return products;
    }

    public int getPage() {
        return page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

}
