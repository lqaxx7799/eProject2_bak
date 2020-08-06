/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import utils.ConnectionFactory;
import java.sql.Connection;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import models.MenuItem;

/**
 *
 * @author Admin
 */
public class MenuItemService {

    public ArrayList<MenuItem> getAll() {
        try {
            Connection connection = ConnectionFactory.getConnection();
            Statement stmt = connection.createStatement();
            String query = "select * from menu_items";
            ResultSet rs = stmt.executeQuery(query);
            ArrayList<MenuItem> list = new ArrayList<>();
            while (rs.next()) {
                MenuItem menuItem = new MenuItem();
                menuItem.setId(rs.getInt(1));
                menuItem.setItemName(rs.getString(2));
                menuItem.setCreatedTime(rs.getTimestamp(3));
                menuItem.setAvailable(rs.getBoolean(4));
                menuItem.setPrice(rs.getDouble(5));
                menuItem.setMenuCategoryId(rs.getInt(6));
                list.add(menuItem);
            }
            stmt.close();
            connection.close();
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
    
    public ArrayList<String> getNameByCategoryId(int categoryId){
        try {
            Connection connection = ConnectionFactory.getConnection();
            String query = "select item_name from menu_items where menu_category_id = ?";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1,categoryId);
            ResultSet rs = stmt.executeQuery();
            ArrayList<String> list = new ArrayList<>();
            while (rs.next()) {
                list.add(rs.getString(1));
            }
            stmt.close();
            connection.close();
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public MenuItem getById(int id) {
        try {
            Connection connection = ConnectionFactory.getConnection();
            String query = "select * from menu_items where id = ?";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                MenuItem menuItem = new MenuItem();
                menuItem.setId(rs.getInt(1));
                menuItem.setItemName(rs.getString(2));
                menuItem.setCreatedTime(rs.getTimestamp(3));
                menuItem.setAvailable(rs.getBoolean(4));
                menuItem.setPrice(rs.getDouble(5));
                menuItem.setMenuCategoryId(rs.getInt(6));
                stmt.close();
                connection.close();
                return menuItem;
            }
            stmt.close();
            connection.close();
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public int insert(MenuItem menuItem) {
        try {
            Connection connection = ConnectionFactory.getConnection();
            String query = "insert into menu_items "
                    + "(item_name, created_time, is_available, price, menu_category_id) "
                    + "values (?,?,?,?,?)";
            PreparedStatement stmt = connection.prepareStatement(query);
            Timestamp createdTime = menuItem.getCreatedTime() == null ? null : new Timestamp(menuItem.getCreatedTime().getTime());

            stmt.setString(1, menuItem.getItemName());
            stmt.setTimestamp(2, createdTime);
            stmt.setBoolean(3, menuItem.isAvailable());
            stmt.setDouble(4, menuItem.getPrice());
            stmt.setInt(5, menuItem.getMenuCategoryId());
            int rs = stmt.executeUpdate();
            stmt.close();
            connection.close();
            return rs;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int update(MenuItem menuItem) {
        try {
            Connection connection = ConnectionFactory.getConnection();
            String query = "update menu_items set item_name = ?, created_time = ?, is_available = ?, price = ?, menu_category_id = ? "
                    + "where id = ?";
            PreparedStatement stmt = connection.prepareStatement(query);
            Timestamp createdTime = menuItem.getCreatedTime() == null ? null : new Timestamp(menuItem.getCreatedTime().getTime());

            stmt.setString(1, menuItem.getItemName());
            stmt.setTimestamp(2, createdTime);
            stmt.setBoolean(3, menuItem.isAvailable());
            stmt.setDouble(4, menuItem.getPrice());
            stmt.setInt(5, menuItem.getMenuCategoryId());
            stmt.setInt(6, menuItem.getId());
            int rs = stmt.executeUpdate();
            stmt.close();
            connection.close();
            return rs;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int delete(int id) {
        try {
            Connection connection = ConnectionFactory.getConnection();
            String query = "delete from menu_items where id = ?";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1, id);
            int rs = stmt.executeUpdate();
            stmt.close();
            connection.close();
            return rs;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }
}
