/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Add;

import java.sql.*;
import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author Ardenshley Nage
 */
public class Add_DAO {

    private String URL = "jdbc:mysql://localhost:3306/contact_manager";
    private String USER = "root";
    private String PASSWORD = "";
    private static Connection con;
    private static PreparedStatement pst;
    private static ResultSet rs;

    public Add_DAO(String URL, String USER, String PASSWORD) {
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

    public void addContacts(Add_mod add) throws SQLException {
        String sql = "INSERT INTO contacts (last_name, first_name, phone_number, mail_user, access, add_date) VALUES (?, ?, ?, ?, ?, ?)";
        connect();

        PreparedStatement statement = con.prepareStatement(sql);
        statement.setString(1, add.getLast_name());
        statement.setString(2, add.getFirst_name());
        statement.setInt(3, add.getPhone_number());
        statement.setString(4, add.getMail_user());
        statement.setInt(5, 1);
        statement.setString(6, add.getAdd_date());

        int rowsAffected = statement.executeUpdate();
        if (rowsAffected > 0) {
            System.out.println("Contact ajouté avec succès.");
        } else {
            System.out.println("Échec de l'ajout du contact.");
        }
    }

    @SuppressWarnings("ConvertToTryWithResources")
    public boolean updateContacts(Add_mod add) throws SQLException {
        String sql = "UPDATE contacts SET last_name = ?, first_name = ?, phone_number = ?, mail_user = ? WHERE id = ?";
        connect();
        int id = add.getId();

        PreparedStatement statement = con.prepareStatement(sql);
        statement.setString(1, add.getLast_name());
        statement.setString(2, add.getFirst_name());
        statement.setDouble(3, add.getPhone_number());
        statement.setString(4, add.getMail_user());
        statement.setInt(5, id);

        boolean rowUpdated = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowUpdated;
    }

    

}
