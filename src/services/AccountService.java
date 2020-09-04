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
import java.sql.Date;
import models.Account;

/**
 *
 * @author Admin
 */
public class AccountService {

    public ArrayList<Account> getAll() {
        try {
            Connection connection = ConnectionFactory.getConnection();
            Statement stmt = connection.createStatement();
            String query = "select * from accounts";
            ResultSet rs = stmt.executeQuery(query);
            ArrayList<Account> list = new ArrayList<>();
            while (rs.next()) {
                Account account = new Account();
                account.setId(rs.getInt(1));
                account.setEmail(rs.getString(2));
                account.setPassword(rs.getString(3));
                account.setUserName(rs.getString(4));
                account.setAddress(rs.getString(5));
                account.setGender(rs.getString(6));
                account.setDateOfBirth(rs.getDate(7));
                account.setRoleId(rs.getInt(8));
                account.setStartWorkDate(rs.getDate(9));
                account.setWorking(rs.getBoolean(10));
                list.add(account);
            }
            stmt.close();
            connection.close();
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
    
    public ArrayList<Account> getWorkingAccounts() {
        try {
            Connection connection = ConnectionFactory.getConnection();
            Statement stmt = connection.createStatement();
            String query = "select * from accounts where is_working = 1";
            ResultSet rs = stmt.executeQuery(query);
            ArrayList<Account> list = new ArrayList<>();
            while (rs.next()) {
                Account account = new Account();
                account.setId(rs.getInt(1));
                account.setEmail(rs.getString(2));
                account.setPassword(rs.getString(3));
                account.setUserName(rs.getString(4));
                account.setAddress(rs.getString(5));
                account.setGender(rs.getString(6));
                account.setDateOfBirth(rs.getDate(7));
                account.setRoleId(rs.getInt(8));
                account.setStartWorkDate(rs.getDate(9));
                account.setWorking(rs.getBoolean(10));
                list.add(account);
            }
            stmt.close();
            connection.close();
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public Account getById(int id) {
        try {
            Connection connection = ConnectionFactory.getConnection();
            String query = "select * from accounts where id = ?";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Account account = new Account();
                account.setId(rs.getInt(1));
                account.setEmail(rs.getString(2));
                account.setPassword(rs.getString(3));
                account.setUserName(rs.getString(4));
                account.setAddress(rs.getString(5));
                account.setGender(rs.getString(6));
                account.setDateOfBirth(rs.getDate(7));
                account.setRoleId(rs.getInt(8));
                account.setStartWorkDate(rs.getDate(9));
                account.setWorking(rs.getBoolean(10));
                stmt.close();
                connection.close();
                return account;
            }
            stmt.close();
            connection.close();
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public int insert(Account account) {
        try {
            Connection connection = ConnectionFactory.getConnection();
            String query = "insert into accounts "
                    + "(email, password, user_name, address, gender, date_of_birth, role_id, start_work_date, is_working) "
                    + "values (?,?,?,?,?,?,?,?,?)";
            PreparedStatement stmt = connection.prepareStatement(query);
            Date dateOfBirth = account.getDateOfBirth() == null ? null : new Date(account.getDateOfBirth().getTime());
            Date startWorkDate = account.getStartWorkDate() == null ? null : new Date(account.getStartWorkDate().getTime());
            
            stmt.setString(1, account.getEmail());
            stmt.setString(2, account.getPassword());
            stmt.setString(3, account.getUserName());
            stmt.setString(4, account.getAddress());
            stmt.setString(5, account.getGender());
            stmt.setDate(6, dateOfBirth);
            stmt.setInt(7, account.getRoleId());
            stmt.setDate(8, startWorkDate);
            stmt.setBoolean(9, account.isWorking());

            int rs = stmt.executeUpdate();
            stmt.close();
            connection.close();
            return rs;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int update(Account account) {
        try {
            Connection connection = ConnectionFactory.getConnection();
            String query = "update accounts set email = ?, password = ?, user_name = ?, address = ?, gender = ?, "
                    + "date_of_birth = ?, role_id = ?, start_work_date = ?, is_working = ?"
                    + " where id = ?";
            PreparedStatement stmt = connection.prepareStatement(query);
            Date dateOfBirth = account.getDateOfBirth() == null ? null : new java.sql.Date(account.getDateOfBirth().getTime());
            Date startWorkDate = account.getStartWorkDate() == null ? null : new java.sql.Date(account.getStartWorkDate().getTime());
            
            stmt.setString(1, account.getEmail());
            stmt.setString(2, account.getPassword());
            stmt.setString(3, account.getUserName());
            stmt.setString(4, account.getAddress());
            stmt.setString(5, account.getGender());
            stmt.setDate(6, dateOfBirth);
            stmt.setInt(7, account.getRoleId());
            stmt.setDate(8, startWorkDate);
            stmt.setBoolean(9, account.isWorking());
            stmt.setInt(10, account.getId());
            int rs = stmt.executeUpdate();
            stmt.close();
            connection.close();
            return rs;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int updatePassword(int id, String password) {
        try {
            Connection connection = ConnectionFactory.getConnection();
            String query = "update accounts set password = ?"
                    + " where id = ?";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, password);
            stmt.setInt(2, id);
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
            String query = "delete from accounts where id = ?";
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
