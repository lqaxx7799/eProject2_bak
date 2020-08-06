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
import models.ReceiptDetail;

/**
 *
 * @author Admin
 */
public class ReceiptDetailService {

    public ArrayList<ReceiptDetail> getAll() {
        try {
            Connection connection = ConnectionFactory.getConnection();
            Statement stmt = connection.createStatement();
            String query = "select * from receipt_details";
            ResultSet rs = stmt.executeQuery(query);
            ArrayList<ReceiptDetail> list = new ArrayList<>();
            while (rs.next()) {
                ReceiptDetail receiptDetail = new ReceiptDetail();
                receiptDetail.setId(rs.getInt(1));
                receiptDetail.setReceiptId(rs.getInt(2));
                receiptDetail.setMenuItemId(rs.getInt(3));
                receiptDetail.setQuantity(rs.getInt(4));
                receiptDetail.setUnitPrice(rs.getDouble(5));
                receiptDetail.setMade(rs.getBoolean(6));    
                receiptDetail.setServed(rs.getBoolean(7));
                list.add(receiptDetail);
            }
            stmt.close();
            connection.close();
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public ReceiptDetail getById(int id) {
        try {
            Connection connection = ConnectionFactory.getConnection();
            String query = "select * from receipt_details where id = ?";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                ReceiptDetail receiptDetail = new ReceiptDetail();
                receiptDetail.setId(rs.getInt(1));
                receiptDetail.setReceiptId(rs.getInt(2));
                receiptDetail.setMenuItemId(rs.getInt(3));
                receiptDetail.setQuantity(rs.getInt(4));
                receiptDetail.setUnitPrice(rs.getDouble(5));
                receiptDetail.setMade(rs.getBoolean(6));    
                receiptDetail.setServed(rs.getBoolean(7));
                stmt.close();
                connection.close();
                return receiptDetail;
            }
            stmt.close();
            connection.close();
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public int insert(ReceiptDetail receiptDetail) {
        try {
            Connection connection = ConnectionFactory.getConnection();
            String query = "insert into receipt_details "
                    + "(receipt_id, menu_item_id, quantity, unit_price, is_made, is_served) "
                    + "values (?,?,?,?,?,?)";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1, receiptDetail.getReceiptId());
            stmt.setInt(2, receiptDetail.getMenuItemId());
            stmt.setInt(3, receiptDetail.getQuantity());
            stmt.setDouble(4, receiptDetail.getUnitPrice());
            stmt.setBoolean(5, receiptDetail.isMade());
            stmt.setBoolean(6, receiptDetail.isServed());
            int rs = stmt.executeUpdate();
            stmt.close();
            connection.close();
            return rs;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int update(ReceiptDetail receiptDetail) {
        try {
            Connection connection = ConnectionFactory.getConnection();
            String query = "update receipt_details set receipt_id = ?, menu_item_id = ?, quantity = ?, "
                    + "unit_price = ?, is_made = ?, is_served = ? "
                    + "where id = ?";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1, receiptDetail.getReceiptId());
            stmt.setInt(2, receiptDetail.getMenuItemId());
            stmt.setInt(3, receiptDetail.getQuantity());
            stmt.setDouble(4, receiptDetail.getUnitPrice());
            stmt.setBoolean(5, receiptDetail.isMade());
            stmt.setBoolean(6, receiptDetail.isServed());
            stmt.setInt(7, receiptDetail.getId());
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
            String query = "delete from receipt_details where id = ?";
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
