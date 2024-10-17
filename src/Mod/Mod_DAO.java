/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Mod;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Ardenshley Nage
 */
public class Mod_DAO {

    private String URL = "jdbc:mysql://localhost:3306/contact_manager";
    private String USER = "root";
    private String PASSWORD = "";
    private static Connection con;
    private static PreparedStatement pst;
    private static ResultSet rs;

    public Mod_DAO(String URL, String USER, String PASSWORD) {
        this.URL = URL;
        this.USER = USER;
        this.PASSWORD = PASSWORD;

    }

    protected void connect() throws SQLException {
        if (con == null || con.isClosed()) {
            con = DriverManager.getConnection(URL, USER, PASSWORD);
        }
    }

    protected void disconnect() throws SQLException {
        if (con != null && !con.isClosed()) {
            con.close();
        }
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public boolean existsByPhoneNumber(int phone_number) {
        String query = "SELECT COUNT(*) FROM contacts WHERE phone_number = ?";
        try (@SuppressWarnings("LocalVariableHidesMemberVariable") Connection con = DriverManager.getConnection(URL, USER, PASSWORD); PreparedStatement preparedStatement = con.prepareStatement(query)) {
            preparedStatement.setInt(1, phone_number);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean TableEmpty() throws SQLException {
        String sql = "SELECT COUNT(*) AS total FROM contacts";
        connect();
        pst = con.prepareStatement(sql);
        rs = pst.executeQuery();

        boolean isEmpty = true;
        if (rs.next()) {
            int count = rs.getInt("total");
            isEmpty = (count == 0);
        }

        rs.close();
        pst.close();
        disconnect();
        return isEmpty;
    }

    public boolean AllAccessZero() throws SQLException {
        String sql = "SELECT COUNT(*) AS total FROM contacts WHERE access <> 0";
        connect();
        pst = con.prepareStatement(sql);
        rs = pst.executeQuery();

        boolean allZero = true;
        if (rs.next()) {
            int count = rs.getInt("total");
            allZero = (count == 0);
        }

        rs.close();
        pst.close();
        disconnect();
        return allZero;
    }

    @SuppressWarnings("ConvertToTryWithResources")
    public boolean updateLastNameContact(Mod_mod modln) throws SQLException {
        String sql = "UPDATE contacts SET last_name = ? WHERE id = ?";
        connect();
        int id = modln.getId();

        PreparedStatement statement = con.prepareStatement(sql);
        statement.setString(1, modln.getLast_name());
        statement.setInt(2, id);

        boolean rowUpdated = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowUpdated;
    }

    @SuppressWarnings("ConvertToTryWithResources")
    public boolean updateFirstNameContact(Mod_mod modfn) throws SQLException {
        String sql = "UPDATE contacts SET first_name = ? WHERE id = ?";
        connect();
        int id = modfn.getId();

        PreparedStatement statement = con.prepareStatement(sql);
        statement.setString(1, modfn.getFirst_name());
        statement.setInt(2, id);

        boolean rowUpdated = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowUpdated;
    }

    @SuppressWarnings("ConvertToTryWithResources")
    public boolean updatePhoneNumberContact(Mod_mod modpn) throws SQLException {
        String sql = "UPDATE contacts SET phone_number = ? WHERE id = ?";
        connect();
        int id = modpn.getId();

        PreparedStatement statement = con.prepareStatement(sql);
        statement.setInt(1, modpn.getPhone_number());
        statement.setInt(2, id);

        boolean rowUpdated = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowUpdated;
    }

    @SuppressWarnings("ConvertToTryWithResources")
    public boolean updateEmailContact(Mod_mod mode) throws SQLException {
        String sql = "UPDATE contacts SET mail_user = ? WHERE id = ?";
        connect();
        int id = mode.getId();

        PreparedStatement statement = con.prepareStatement(sql);
        statement.setString(1, mode.getMail_user());
        statement.setInt(2, id);

        boolean rowUpdated = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowUpdated;
    }
}
