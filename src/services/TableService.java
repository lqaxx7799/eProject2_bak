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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import models.Table;

/**
 *
 * @author Admin
 */
public class TableService {

    public ArrayList<Table> getAll() {
        try {
            Connection connection = ConnectionFactory.getConnection();
            Statement stmt = connection.createStatement();
            String query = "select * from tables";
            ResultSet rs = stmt.executeQuery(query);
            ArrayList<Table> list = new ArrayList<>();
            while (rs.next()) {
                Table table = new Table();
                table.setId(rs.getInt(1));
                table.setTableName(rs.getString(2));
                table.setOccupied(rs.getBoolean(3));
                table.setAvailable(rs.getBoolean(4));
                list.add(table);
            }
            stmt.close();
            connection.close();
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public Table getById(int id) {
        try {
            Connection connection = ConnectionFactory.getConnection();
            String query = "select * from tables where id = ?";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Table table = new Table();
                table.setId(rs.getInt(1));
                table.setTableName(rs.getString(2));
                table.setOccupied(rs.getBoolean(3));
                table.setAvailable(rs.getBoolean(4));
                stmt.close();
                connection.close();
                return table;
            }
            stmt.close();
            connection.close();
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public int insert(Table table) {
        try {
            Connection connection = ConnectionFactory.getConnection();
            String query = "insert into tables (table_name, is_occupied, is_available) values (?,?,?)";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, table.getTableName());
            stmt.setBoolean(2, table.isOccupied());
            stmt.setBoolean(3, table.isAvailable());
            int rs = stmt.executeUpdate();
            stmt.close();
            connection.close();
            return rs;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int update(Table table) {
        try {
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Connection connection = ConnectionFactory.getConnection();
            String query = "update tables set table_name = ?, is_occupied = ?, is_available = ? "
                    + "where id = ?";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, table.getTableName());
            stmt.setBoolean(2, table.isOccupied());
            stmt.setBoolean(3, table.isAvailable());
            stmt.setInt(4, table.getId());
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
            String query = "delete from tables where id = ?";
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
