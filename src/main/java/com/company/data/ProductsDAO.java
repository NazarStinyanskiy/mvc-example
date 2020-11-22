package com.company.data;

import com.company.dao.DAO;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class ProductsDAO implements DAO {

    private final String DB_DRIVER = "org.postgresql.Driver";
    private final String DB_URL = "jdbc:postgresql://localhost/ProdaMarket";
    private final String USER = "postgres";
    private final String PASSWORD = "postgres";


    public void save(Product product){
        try {
            Class.forName(DB_DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Connection connection = null;
        PreparedStatement statement = null;

        String sql = "INSERT INTO products (name, amount, cost) VALUES (?, ?, ?)";

        try {
            connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            statement = connection.prepareStatement(sql);
            statement.setString(1, product.getName());
            statement.setInt(2, product.getAmount());
            statement.setDouble(3, product.getCost());

            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        try {

            if(connection != null) connection.close();
            if(statement != null) statement.close();

        } catch (SQLException throwables) {
                throwables.printStackTrace();
        }

    }

    public List<Product> getProducts() {
        List<Product> list = new ArrayList<>();

        try {
            Class.forName(DB_DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        String sql = "SELECT * FROM products";

        try{
            connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);

            while(resultSet.next()){
                list.add(new Product(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getInt("amount"),
                        resultSet.getDouble("cost")
                ));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {

            try{

                if(connection != null) connection.close();
                if(statement != null) statement.close();
                if(resultSet != null) resultSet.close();

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }


        }

        return list;
    }

    public Product getProduct(int id){
        Product product = null;

        try {
            Class.forName(DB_DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        String sql = "SELECT * FROM products WHERE id=" + id;

        try{
            connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);

            resultSet.next();

            product = new Product(
                    resultSet.getInt("id"),
                    resultSet.getString("name"),
                    resultSet.getInt("amount"),
                    resultSet.getDouble("cost")
            );

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try{

                if(connection != null) connection.close();
                if(statement != null) statement.close();
                if(resultSet != null) resultSet.close();

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

        return product;
    }

    public void edit(int id, Product editedProduct){
        try {
            Class.forName(DB_DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Connection connection = null;
        PreparedStatement statement = null;

        String sql = "UPDATE products SET name = ?, amount = ?, cost = ? WHERE id = " + editedProduct.getId();

        try{

            connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            statement = connection.prepareStatement(sql);
            statement.setString(1, editedProduct.getName());
            statement.setInt(2, editedProduct.getAmount());
            statement.setDouble(3, editedProduct.getCost());
            System.out.println(statement.executeUpdate());

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            try{

                if(connection != null) connection.close();
                if(statement != null) statement.close();

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    public void delete(int id){
        try {
            Class.forName(DB_DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Connection connection = null;
        Statement statement = null;

        String sql = "DELETE FROM products WHERE id=" + id;

        try{
            connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            try{

                if(connection != null) connection.close();
                if(statement != null) statement.close();

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
}