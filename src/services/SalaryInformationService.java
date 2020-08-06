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
import models.SalaryInformation;

/**
 *
 * @author Admin
 */
public class SalaryInformationService {

    public ArrayList<SalaryInformation> getAll() {
        try {
            Connection connection = ConnectionFactory.getConnection();
            Statement stmt = connection.createStatement();
            String query = "select * from salary_information";
            ResultSet rs = stmt.executeQuery(query);
            ArrayList<SalaryInformation> list = new ArrayList<>();
            while (rs.next()) {
                SalaryInformation salaryInformation = new SalaryInformation();
                salaryInformation.setId(rs.getInt(1));
                salaryInformation.setAccountId(rs.getInt(2));
                salaryInformation.setSalary(rs.getDouble(3));
                salaryInformation.setFromDate(rs.getDate(4));
                salaryInformation.setToDate(rs.getDate(5));
                list.add(salaryInformation);
            }
            stmt.close();
            connection.close();
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public SalaryInformation getById(int id) {
        try {
            Connection connection = ConnectionFactory.getConnection();
            String query = "select * from salary_information where id = ?";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                SalaryInformation salaryInformation = new SalaryInformation();
                salaryInformation.setId(rs.getInt(1));
                salaryInformation.setAccountId(rs.getInt(2));
                salaryInformation.setSalary(rs.getDouble(3));
                salaryInformation.setFromDate(rs.getDate(4));
                salaryInformation.setToDate(rs.getDate(5));
                stmt.close();
                connection.close();
                return salaryInformation;
            }
            stmt.close();
            connection.close();
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public ArrayList<SalaryInformation> getByAccountId(int accountId) {
        try {
            Connection connection = ConnectionFactory.getConnection();
            String query = "select * from salary_information where account_id = ?";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1, accountId);
            ResultSet rs = stmt.executeQuery();
            ArrayList<SalaryInformation> list = new ArrayList<>();
            while (rs.next()) {
                SalaryInformation salaryInformation = new SalaryInformation();
                salaryInformation.setId(rs.getInt(1));
                salaryInformation.setAccountId(rs.getInt(2));
                salaryInformation.setSalary(rs.getDouble(3));
                salaryInformation.setFromDate(rs.getDate(4));
                salaryInformation.setToDate(rs.getDate(5));
                list.add(salaryInformation);
            }
            stmt.close();
            connection.close();
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public int insert(SalaryInformation salaryInformation) {
        try {
            Connection connection = ConnectionFactory.getConnection();
            String query = "insert into salary_information "
                    + "(account_id, salary, from_date, to_date) "
                    + "values (?,?,?,?)";
            PreparedStatement stmt = connection.prepareStatement(query);
            Date fromDate = salaryInformation.getFromDate() == null ? null : new Date(salaryInformation.getFromDate().getTime());
            Date toDate = salaryInformation.getToDate() == null ? null : new Date(salaryInformation.getToDate().getTime());

            stmt.setInt(1, salaryInformation.getAccountId());
            stmt.setDouble(2, salaryInformation.getSalary());
            stmt.setDate(3, fromDate);
            stmt.setDate(4, toDate);
            int rs = stmt.executeUpdate();
            stmt.close();
            connection.close();
            return rs;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int update(SalaryInformation salaryInformation) {
        try {
            Connection connection = ConnectionFactory.getConnection();
            String query = "update salary_information set account_id = ?, salary = ?, from_date = ?, to_date = ? "
                    + "where id = ?";
            PreparedStatement stmt = connection.prepareStatement(query);
            Date fromDate = salaryInformation.getFromDate() == null ? null : new Date(salaryInformation.getFromDate().getTime());
            Date toDate = salaryInformation.getToDate() == null ? null : new Date(salaryInformation.getToDate().getTime());

            stmt.setInt(1, salaryInformation.getAccountId());
            stmt.setDouble(2, salaryInformation.getSalary());
            stmt.setDate(3, fromDate);
            stmt.setDate(4, toDate);
            stmt.setInt(5, salaryInformation.getId());
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
            String query = "delete from salary_information where id = ?";
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
