/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import database.DBConnection;
import java.sql.Connection;
import java.sql.*;
import java.util.ArrayList;
import model.Product;

/**
 *
 * @author BMP 5
 */
public class ProductDAO {

    Connection conn;

    public ProductDAO() {
        conn = DBConnection.getConnection();
    }

    public void addProduct(Product product) {
        
        String sql = "INSERT INTO products(product_name, category, price, quantity )VALUES( ?, ?, ?, ?)";
try {
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, product.getProductName());
            pst.setString(2, product.getCategory());
            pst.setDouble(3, product.getPrice());
            pst.setInt(4, product.getQuantity());
            pst.executeUpdate();
            System.out.println("Product Added Successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Product> getAllProducts() {
        ArrayList<Product> productList = new ArrayList<>();
        String sql = "SELECT * FROM products";
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Product product = new Product(
                        rs.getInt("product_id"),
                        rs.getString("product_name"),
                        rs.getString("category"),
                        rs.getDouble("price"),
                        rs.getInt("quantity")
                );
                
                productList.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productList;
    }

    public void updateProduct(Product product) {
        String sql = "UPDATE products SET product_name=?, category=?,price = ?, quantity = ? WHERE product_id =  ? ";
try {
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, product.getProductName());
            pst.setString(2, product.getCategory());
            pst.setDouble(3, product.getPrice());
            pst.setInt(4, product.getQuantity());
            pst.setInt(5, product.getProductId());
            pst.executeUpdate();
            System.out.println("Product Updated Successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteProduct(int id) {
        String sql = "DELETE FROM products WHERE product_id=?";
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, id);
            pst.executeUpdate();
            System.out.println("Product Deleted Successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void recordSale(String productName, int quantitySold, double totalPrice) {
        String sql = "INSERT INTO sales(product_name, quantity_sold,"
                + "total_price) VALUES( ?,  ?,  ?)";
try {
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, productName);
            pst.setInt(2, quantitySold);
            pst.setDouble(3, totalPrice);
            pst.executeUpdate();
            System.out.println("Sale Recorded Successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
