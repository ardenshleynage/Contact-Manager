/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package List_mod;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ardenshley Nage
 */
public class List_mod_DAO {

    private String URL = "jdbc:mysql://localhost:3306/contact_manager";
    private String USER = "root";
    private String PASSWORD = "";
    private static Connection con;
    private static PreparedStatement pst;
    private static ResultSet rs;

    public List_mod_DAO(String URL, String USER, String PASSWORD) {
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

    public List<List_mod_mod> getAllContacts() throws SQLException {
        String sql = "SELECT * FROM contacts WHERE access = 1";
        connect();
        pst = con.prepareStatement(sql);
        rs = pst.executeQuery();

        List<List_mod_mod> lists = new ArrayList<>();

        while (rs.next()) {
            int id = rs.getInt("id");
            String first_name = rs.getString("first_name");
            String last_name = rs.getString("last_name");
            int phone_number = rs.getInt("phone_number");
            String mail_user = rs.getString("mail_user");
            String add_date = rs.getString("add_date");

            List_mod_mod list = new List_mod_mod(id, first_name, last_name, phone_number, mail_user, add_date);
            lists.add(list);
        }

        rs.close();
        pst.close();

        return lists;
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

}
