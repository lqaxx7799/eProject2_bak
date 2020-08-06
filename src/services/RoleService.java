/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import utils.ConnectionFactory;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.Connection;
import models.Role;

/**
 *
 * @author Admin
 */
public class RoleService {

    public ArrayList<Role> getAll() {
        try {
            Connection connection = ConnectionFactory.getConnection();
            Statement stmt = connection.createStatement();
            String query = "select * from roles";
            ResultSet rs = stmt.executeQuery(query);
            ArrayList<Role> list = new ArrayList<>();
            while (rs.next()) {
                Role role = new Role();
                role.setId(rs.getInt(1));
                role.setRoleName(rs.getString(2));
                list.add(role);
            }
            stmt.close();
            connection.close();
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public Role getById(int id) {
        try {
            Connection connection = ConnectionFactory.getConnection();
            String query = "select * from roles where id = ?";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Role role = new Role();
                role.setId(rs.getInt(1));
                role.setRoleName(rs.getString(2));
                stmt.close();
                connection.close();
                return role;
            }
            stmt.close();
            connection.close();
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public int insert(Role role) {
        try {
            Connection connection = ConnectionFactory.getConnection();
            String query = "insert into roles(role_name) values (?)";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, role.getRoleName());
            int rs = stmt.executeUpdate();
            stmt.close();
            connection.close();
            return rs;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int update(Role role) {
        try {
            Connection connection = ConnectionFactory.getConnection();
            String query = "update roles set role_name = ? where id = ?";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, role.getRoleName());
            stmt.setInt(2, role.getId());
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
            String query = "delete from roles where id = ?";
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
