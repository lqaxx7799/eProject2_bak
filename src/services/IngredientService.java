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
import models.Ingredient;

/**
 *
 * @author Admin
 */
public class IngredientService {

    public ArrayList<Ingredient> getAll() {
        try {
            Connection connection = ConnectionFactory.getConnection();
            Statement stmt = connection.createStatement();
            String query = "select * from ingredients";
            ResultSet rs = stmt.executeQuery(query);
            ArrayList<Ingredient> list = new ArrayList<>();
            while (rs.next()) {
                Ingredient ingredient = new Ingredient();
                ingredient.setId(rs.getInt(1));
                ingredient.setIngredientName(rs.getString(2));
                ingredient.setUnit(rs.getString(3));
                ingredient.setAvailable(rs.getBoolean(4));
                list.add(ingredient);
            }
            stmt.close();
            connection.close();
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public Ingredient getById(int id) {
        try {
            Connection connection = ConnectionFactory.getConnection();
            String query = "select * from ingredients where id = ?";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Ingredient ingredient = new Ingredient();
                ingredient.setId(rs.getInt(1));
                ingredient.setIngredientName(rs.getString(2));
                ingredient.setUnit(rs.getString(3));
                ingredient.setAvailable(rs.getBoolean(4));
                stmt.close();
                connection.close();
                return ingredient;
            }
            stmt.close();
            connection.close();
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public int insert(Ingredient ingredient) {
        try {
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Connection connection = ConnectionFactory.getConnection();
            String query = "insert into ingredients (ingredient_name, unit, is_available) values (?,?,?)";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, ingredient.getIngredientName());
            stmt.setString(2, ingredient.getUnit());
            stmt.setBoolean(3, ingredient.isAvailable());
            int rs = stmt.executeUpdate();
            stmt.close();
            connection.close();
            return rs;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int update(Ingredient ingredient) {
        try {
            Connection connection = ConnectionFactory.getConnection();
            String query = "update ingredients set ingredient_name = ?, unit = ?, is_available = ? where id = ?";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, ingredient.getIngredientName());
            stmt.setString(2, ingredient.getUnit());
            stmt.setBoolean(3, ingredient.isAvailable());
            stmt.setInt(4, ingredient.getId());
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
            String query = "delete from ingredients where id = ?";
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
