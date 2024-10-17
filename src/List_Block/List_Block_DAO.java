/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package List_Block;

import java.sql.*;
import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author Ardenshley Nage
 */
public class List_Block_DAO {

    private String URL = "jdbc:mysql://localhost:3306/contact_manager";
    private String USER = "root";
    private String PASSWORD = "";
    private static Connection con;
    private static PreparedStatement pst;
    private static ResultSet rs;

    public List_Block_DAO(String URL, String USER, String PASSWORD) {
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

    public boolean AccessContacts(List_Block_mod block) throws SQLException {
        String sql = "UPDATE contacts SET access = ? WHERE id = ?";
        connect();

        int id = block.getId();
        System.out.println("Connexion établie. ID à bloquer: " + id);

        PreparedStatement statement = con.prepareStatement(sql);
        statement.setInt(1, 1);
        statement.setInt(2, id);

        boolean rowDeleted = statement.executeUpdate() > 0;

        disconnect();
        return rowDeleted;
    }

    public boolean SureDeleteContacts(List_Block_mod del) throws SQLException {
        if (del == null) {
            throw new IllegalArgumentException("L'objet Delete_mod ne peut pas être null");
        }

        String sql = "DELETE FROM contacts WHERE id = ?";
        connect();

        int id = del.getId();

        PreparedStatement statement = con.prepareStatement(sql);
        statement.setInt(1, id);

        boolean rowDeleted = statement.executeUpdate() > 0;

        disconnect();
        return rowDeleted;
    }

    public List<List_Block_mod> getAllBlockContacts() throws SQLException {
        String sql = "SELECT * FROM contacts WHERE access = 0";
        connect();
        pst = con.prepareStatement(sql);
        rs = pst.executeQuery();

        List<List_Block_mod> lists = new ArrayList<>();

        while (rs.next()) {
            int id = rs.getInt("id");
            String first_name = rs.getString("first_name");
            String last_name = rs.getString("last_name");
            int phone_number = rs.getInt("phone_number");
            String mail_user = rs.getString("mail_user");
            String add_date = rs.getString("add_date");

            List_Block_mod list = new List_Block_mod(id, first_name, last_name, phone_number, mail_user, add_date);
            lists.add(list);
        }

        rs.close();
        pst.close();

        return lists;
    }

    public boolean IsAllAccessOne() throws SQLException {
        String sql = "SELECT COUNT(*) AS total FROM contacts WHERE access <> 1";
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

    public boolean IsTableEmpty() throws SQLException {
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
