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
import java.sql.PreparedStatement;
import models.BillList;
public class BillListService {
    public ArrayList<BillList> getByTableId(int tableId){
        try {
            Connection connection = ConnectionFactory.getConnection();
            String query = "select menu_items.item_name,receipt_details.quantity, menu_items.price, menu_items.price * receipt_details.quantity as totalPrice " +
                           "from menu_items , receipt_details , receipts  where " +
                           "receipt_details.receipt_id = receipts.id and receipt_details.menu_item_id = menu_items.id and receipts.is_paid = 0 and receipts.table_id = ? ";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1,tableId);
            ResultSet rs = stmt.executeQuery();
            ArrayList<BillList> list = new ArrayList<>();
            while (rs.next()) {
                BillList billList = new BillList();
                billList.setItem_name(rs.getString(1));
                billList.setQuantity(rs.getInt(2));
                billList.setPrice(rs.getDouble(3));
                billList.setTotalPrice(rs.getDouble(4));
                list.add(billList);
            }
            stmt.close();
            connection.close();
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
    
    public int getReceiptIdByTableId(int tableId){
        try {
            Connection connection = ConnectionFactory.getConnection();
            String query = "select receipts.id from receipts where receipts.is_paid = 0 and receipts.table_id = ? ";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1,tableId);
            ResultSet rs = stmt.executeQuery();
            int receiptId = 0;
            if(rs.next()){
                receiptId = rs.getInt(1);
            }
            stmt.close();
            connection.close();
            return receiptId;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        } 
    }
        
    
//    public boolean checkExistBillByTableId(int tableId){
//        int count =0;
//        try {
//            Connection connection = ConnectionFactory.getConnection();
//            String query = "select * from receipts where receipts.is_paid = 0 and receipts.table_id = ? ";
//            PreparedStatement stmt = connection.prepareStatement(query);
//            stmt.setInt(1,tableId);
//            ResultSet rs = stmt.executeQuery();
//            if(rs.next()){
//                ++count;
//            }
//            stmt.close();
//            connection.close();
//            if(count>0)
//                return true;
//            else
//                return false;
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return false;
//        } 
//    }
}
