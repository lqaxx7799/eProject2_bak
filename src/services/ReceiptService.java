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
import models.Receipt;

/**
 *
 * @author Admin
 */
public class ReceiptService {

    public ArrayList<Receipt> getAll() {
        try {
            Connection connection = ConnectionFactory.getConnection();
            Statement stmt = connection.createStatement();
            String query = "select * from receipts";
            ResultSet rs = stmt.executeQuery(query);
            ArrayList<Receipt> list = new ArrayList<>();
            while (rs.next()) {
                Receipt receipt = new Receipt();
                receipt.setId(rs.getInt(1));
                receipt.setArrivedTime(rs.getTimestamp(2));
                receipt.setPaidTime(rs.getTimestamp(3));
                receipt.setPaid(rs.getBoolean(4));
                receipt.setTableId(rs.getInt(5));
                receipt.setAccountId(rs.getInt(6));
                list.add(receipt);
            }
            stmt.close();
            connection.close();
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
    
    public Receipt getById(int id) {
        try {
            Connection connection = ConnectionFactory.getConnection();
            String query = "select * from receipts where id = ?";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Receipt receipt = new Receipt();
                receipt.setId(rs.getInt(1));
                receipt.setArrivedTime(rs.getTimestamp(2));
                receipt.setPaidTime(rs.getTimestamp(3));
                receipt.setPaid(rs.getBoolean(4));
                receipt.setTableId(rs.getInt(5));
                receipt.setAccountId(rs.getInt(6));
                stmt.close();
                connection.close();
                return receipt;
            }
            stmt.close();
            connection.close();
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public int insert(Receipt receipt) {
        try {
            Connection connection = ConnectionFactory.getConnection();
            String query = "insert into receipts "
                    + "(arrived_time, paid_time, is_paid, table_id, account_id) "
                    + "values (?,?,?,?,?)";
            PreparedStatement stmt = connection.prepareStatement(query);
            Timestamp arrivedTime = receipt.getArrivedTime() == null ? null : new Timestamp(receipt.getArrivedTime().getTime());
            Timestamp paidTime = receipt.getPaidTime() == null ? null : new Timestamp(receipt.getPaidTime().getTime());

            stmt.setTimestamp(1, arrivedTime);
            stmt.setTimestamp(2, paidTime);
            stmt.setBoolean(3, receipt.isPaid());
            stmt.setInt(4, receipt.getTableId());
            stmt.setInt(5, receipt.getAccountId());
            int rs = stmt.executeUpdate();
            stmt.close();
            connection.close();
            return rs;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int update(Receipt receipt) {
        try {
            Connection connection = ConnectionFactory.getConnection();
            String query = "update receipts set arrived_time = ?, paid_time = ?, is_paid = ?, table_id = ?, account_id = ? "
                    + " where id = ?";
            PreparedStatement stmt = connection.prepareStatement(query);
            Timestamp arrivedTime = receipt.getArrivedTime() == null ? null : new Timestamp(receipt.getArrivedTime().getTime());
            Timestamp paidTime = receipt.getPaidTime() == null ? null : new Timestamp(receipt.getPaidTime().getTime());
            
            stmt.setTimestamp(1, arrivedTime);
            stmt.setTimestamp(2, paidTime);
            stmt.setBoolean(3, receipt.isPaid());
            stmt.setInt(4, receipt.getTableId());
            stmt.setInt(5, receipt.getAccountId());
            stmt.setInt(6, receipt.getId());
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
            String query = "delete from receipts where id = ?";
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
