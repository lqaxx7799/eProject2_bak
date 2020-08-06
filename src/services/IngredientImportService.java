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
import models.IngredientImport;

/**
 *
 * @author Admin
 */
public class IngredientImportService {

    public ArrayList<IngredientImport> getAll() {
        try {
            Connection connection = ConnectionFactory.getConnection();
            Statement stmt = connection.createStatement();
            String query = "select * from ingredient_imports";
            ResultSet rs = stmt.executeQuery(query);
            ArrayList<IngredientImport> list = new ArrayList<>();
            while (rs.next()) {
                IngredientImport ingredientImport = new IngredientImport();
                ingredientImport.setId(rs.getInt(1));
                ingredientImport.setIngredientId(rs.getInt(2));
                ingredientImport.setImportTime(rs.getTimestamp(3));
                ingredientImport.setAmount(rs.getDouble(4));
                ingredientImport.setCost(rs.getDouble(5));
                ingredientImport.setAccountId(rs.getInt(6));
                list.add(ingredientImport);
            }
            stmt.close();
            connection.close();
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public IngredientImport getById(int id) {
        try {
            Connection connection = ConnectionFactory.getConnection();
            String query = "select * from ingredient_imports where id = ?";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                IngredientImport ingredientImport = new IngredientImport();
                ingredientImport.setId(rs.getInt(1));
                ingredientImport.setIngredientId(rs.getInt(2));
                ingredientImport.setImportTime(rs.getTimestamp(3));
                ingredientImport.setAmount(rs.getDouble(4));
                ingredientImport.setCost(rs.getDouble(5));
                ingredientImport.setAccountId(rs.getInt(6));
                stmt.close();
                connection.close();
                return ingredientImport;
            }
            stmt.close();
            connection.close();
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public int insert(IngredientImport ingredientImport) {
        try {
            Connection connection = ConnectionFactory.getConnection();
            String query = "insert into ingredient_imports (ingredient_id, import_time, amount, cost, account_id) "
                    + "values (?,?,?,?,?)";
            PreparedStatement stmt = connection.prepareStatement(query);
            Timestamp importDate = ingredientImport.getImportTime() == null ? null : new Timestamp(ingredientImport.getImportTime().getTime());
            stmt.setInt(1, ingredientImport.getIngredientId());
            stmt.setTimestamp(2, importDate);
            stmt.setDouble(3, ingredientImport.getAmount());
            stmt.setDouble(4, ingredientImport.getCost());
            stmt.setInt(5, ingredientImport.getAccountId());
            int rs = stmt.executeUpdate();
            stmt.close();
            connection.close();
            return rs;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int update(IngredientImport ingredientImport) {
        try {
            Connection connection = ConnectionFactory.getConnection();
            String query = "update ingredient_imports set ingredient_id = ?, import_time = ?, amount = ?, cost = ?, account_id = ? "
                    + "where id = ?";
            PreparedStatement stmt = connection.prepareStatement(query);
            Timestamp importDate = ingredientImport.getImportTime() == null ? null : new Timestamp(ingredientImport.getImportTime().getTime());
            stmt.setInt(1, ingredientImport.getIngredientId());
            stmt.setTimestamp(2, importDate);
            stmt.setDouble(3, ingredientImport.getAmount());
            stmt.setDouble(4, ingredientImport.getCost());
            stmt.setInt(5, ingredientImport.getAccountId());
            stmt.setInt(6, ingredientImport.getId());
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
            String query = "delete from ingredient_imports where id = ?";
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
