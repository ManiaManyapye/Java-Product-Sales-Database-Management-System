/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import dao.ProductDAO;
import model.Product;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;


/**
 *
 * @author BMP 5
 */
public class ProductManagementGUI extends JFrame {

    JLabel lblId, lblName, lblCategory, lblPrice, lblQuantity;
    JTextField txtId, txtName, txtCategory, txtPrice, txtQuantity;
    JButton btnAdd, btnUpdate, btnDelete, btnView, btnSell;
    JTable table;
    DefaultTableModel model;
    ProductDAO dao = new ProductDAO();

    public ProductManagementGUI() {

        setTitle("Product Sales Management System");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        JPanel topPanel = new JPanel(new GridLayout(6, 2, 10, 10));
        lblId = new JLabel("Product ID:");
        lblName = new JLabel("Product Name:");
        lblCategory = new JLabel("Category:");
        lblPrice = new JLabel("Price:");
        lblQuantity = new JLabel("Quantity:");
        txtId = new JTextField();
        txtName = new JTextField();
        txtCategory = new JTextField();
        txtPrice = new JTextField();
        txtQuantity = new JTextField();

        topPanel.add(lblId);
        topPanel.add(txtId);
        topPanel.add(lblName);
        topPanel.add(txtName);
        topPanel.add(lblCategory);
        topPanel.add(txtCategory);
        topPanel.add(lblPrice);
        topPanel.add(txtPrice);
        topPanel.add(lblQuantity);
        topPanel.add(txtQuantity);
        btnAdd = new JButton("Add Product");
        btnUpdate = new JButton("Update Product");
        btnDelete = new JButton("Delete Product");
        btnView = new JButton("View Products");
        btnSell = new JButton("Sell Product");
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(btnAdd);
        buttonPanel.add(btnUpdate);
        buttonPanel.add(btnDelete);
        buttonPanel.add(btnView);
        buttonPanel.add(btnSell);
        model = new DefaultTableModel();

        model.setColumnIdentifiers(new String[]{
            "ID",
            "Name",
            "Category",
            "Price",
            "Quantity"
        });
        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        add(topPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
        add(scrollPane, BorderLayout.SOUTH);
        btnAdd.addActionListener(e -> addProduct());
        btnView.addActionListener(e -> viewProducts());
        btnUpdate.addActionListener(e -> updateProduct());
        btnDelete.addActionListener(e -> deleteProduct());
        btnSell.addActionListener(e -> sellProduct());
        setVisible(true);
    }

    public void addProduct() {
        Product product = new Product();
        product.setProductName(txtName.getText());
        product.setCategory(txtCategory.getText());
        product.setPrice(Double.parseDouble(txtPrice.getText()));
        product.setQuantity(Integer.parseInt(txtQuantity.getText()));

        dao.addProduct(product);
        JOptionPane.showMessageDialog(this,
                "Product Added Successfully");
        clearFields();
    }

    public void viewProducts() {
        model.setRowCount(0);
        ArrayList<Product> products = dao.getAllProducts();
        for (Product product : products) {

            model.addRow(new Object[]{
                product.getProductId(),
                product.getProductName(),
                product.getCategory(),
                product.getPrice(),
                product.getQuantity()
            });
        }
    }

    public void updateProduct() {
        Product product = new Product();
        product.setProductId(Integer.parseInt(txtId.getText()));
        product.setProductName(txtName.getText());
        product.setCategory(txtCategory.getText());
        product.setPrice(Double.parseDouble(txtPrice.getText()));
        product.setQuantity(Integer.parseInt(txtQuantity.getText()));

        dao.updateProduct(product);
        JOptionPane.showMessageDialog(this,
                "Product Updated Successfully");
        clearFields();
        viewProducts();
    }

    public void deleteProduct() {
        int id = Integer.parseInt(txtId.getText());
        dao.deleteProduct(id);
        JOptionPane.showMessageDialog(this,
                "Product Deleted Successfully");
        clearFields();
        viewProducts();
    }

    public void sellProduct() {
        String productName = txtName.getText();
        int quantity = Integer.parseInt(txtQuantity.getText());
        double price = Double.parseDouble(txtPrice.getText());
        double total = quantity * price;

        dao.recordSale(productName, quantity, total);
        JOptionPane.showMessageDialog(this,
                "Sale Recorded Successfully\nTotal = R" + total);
    }

    public void clearFields() {
        txtId.setText("");
        txtName.setText("");
        txtCategory.setText("");
        txtPrice.setText("");
        txtQuantity.setText("");
    }

}
