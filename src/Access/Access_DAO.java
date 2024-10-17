/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Access;

import java.sql.*;
import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author Ardenshley Nage
 */
public class Access_DAO {

    private String URL = "jdbc:mysql://localhost:3306/contact_manager";
    private String USER = "root";
    private String PASSWORD = "";
    private static Connection con;
    private static PreparedStatement pst;
    private static ResultSet rs;

    public Access_DAO(String URL, String USER, String PASSWORD) {
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

    public List<Access_mod> getAllBlockContacts() throws SQLException {
        String sql = "SELECT * FROM contacts WHERE access = 0";
        connect();
        pst = con.prepareStatement(sql);
        rs = pst.executeQuery();

        List<Access_mod> Access = new ArrayList<>();

        while (rs.next()) {
            int id = rs.getInt("id");
            String first_name = rs.getString("first_name");
            String last_name = rs.getString("last_name");
            int phone_number = rs.getInt("phone_number");
            String mail_user = rs.getString("mail_user");
            String add_date = rs.getString("add_date");

            Access_mod ace = new Access_mod(id, first_name, last_name, phone_number, mail_user, add_date);
            Access.add(ace);
        }

        rs.close();
        pst.close();

        return Access;
    }

    public boolean AllowContacts(Access_mod ace) throws SQLException {
        String sql = "UPDATE contacts SET access = ? WHERE id = ?";
        connect();

        int id = ace.getId();
        System.out.println("Connexion établie. ID à bloquer: " + id);

        PreparedStatement statement = con.prepareStatement(sql);
        statement.setInt(1, 1);
        statement.setInt(2, id);

        boolean rowDeleted = statement.executeUpdate() > 0;

        disconnect();
        return rowDeleted;
    }

    @SuppressWarnings("ConvertToTryWithResources")
    public boolean AllowAllContacts() throws SQLException {
        String sql = "UPDATE contacts SET access = 1";
        connect();

        Statement statement = con.createStatement();

        int rowsAffected = statement.executeUpdate(sql);

        statement.close();
        disconnect();

        return rowsAffected > 0;
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

    public boolean AllAccessOne() throws SQLException {
        String sql = "SELECT COUNT(*) AS total FROM contacts WHERE access <> 1";
        connect();
        pst = con.prepareStatement(sql);
        rs = pst.executeQuery();

        boolean allOne = true;
        if (rs.next()) {
            int count = rs.getInt("total");
            allOne = (count == 0);
        }

        rs.close();
        pst.close();
        disconnect();
        return allOne;
    }

}
