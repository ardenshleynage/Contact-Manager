/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Delete;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ardenshley Nage
 */
public class Delete_DAO {

    private String URL = "jdbc:mysql://localhost:3306/contact_manager";
    private String USER = "root";
    private String PASSWORD = "";
    private static Connection con;
    private static PreparedStatement pst;
    private static ResultSet rs;

    public Delete_DAO(String URL, String USER, String PASSWORD) {
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
    public boolean deleteAllContacts() throws SQLException {
        String sql = "DELETE FROM contacts WHERE access = 1";
        connect();

        Statement statement = con.createStatement();

        int rowsAffected = statement.executeUpdate(sql);

        statement.close();
        disconnect();

        return rowsAffected > 0;
    }

    public List<Delete_mod> getAllContacts() throws SQLException {
        String sql = "SELECT * FROM contacts WHERE access = 1";
        connect();
        pst = con.prepareStatement(sql);
        rs = pst.executeQuery();

        List<Delete_mod> blocks = new ArrayList<>();

        while (rs.next()) {
            int id = rs.getInt("id");
            String first_name = rs.getString("first_name");
            String last_name = rs.getString("last_name");
            int phone_number = rs.getInt("phone_number");
            String mail_user = rs.getString("mail_user");
            String add_date = rs.getString("add_date");

            Delete_mod block = new Delete_mod(id, first_name, last_name, phone_number, mail_user, add_date);
            blocks.add(block);
        }

        rs.close();
        pst.close();

        return blocks;
    }

    public boolean SureDeleteContacts(Delete_mod del) throws SQLException {
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


}
