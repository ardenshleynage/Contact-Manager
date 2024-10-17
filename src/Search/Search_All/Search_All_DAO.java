/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Search.Search_All;

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
public class Search_All_DAO {

    private String URL = "jdbc:mysql://localhost:3306/contact_manager";
    private String USER = "root";
    private String PASSWORD = "";
    private static Connection con;
    private static PreparedStatement pst;
    private static ResultSet rs;

    public Search_All_DAO(String URL, String USER, String PASSWORD) {
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
    public List<Search_All_mod> searchByAll(String afo) throws SQLException {
        List<Search_All_mod> adds = new ArrayList<>();

        // Convert 'phone_number' to CHAR for LIKE comparison
        String sql = "SELECT * FROM contacts WHERE (last_name LIKE ? OR first_name LIKE ? OR mail_user LIKE ? OR CAST(phone_number AS CHAR) LIKE ?) AND access = 1";
        connect();

        try (PreparedStatement statement = con.prepareStatement(sql)) {
            // Set all parameters for the LIKE clause
            String searchPattern = "%" + afo + "%";
            statement.setString(1, searchPattern);
            statement.setString(2, searchPattern);
            statement.setString(3, searchPattern);
            statement.setString(4, searchPattern);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    String l_name = resultSet.getString("last_name");
                    String first_name = resultSet.getString("first_name");
                    int phone_number = resultSet.getInt("phone_number");
                    int id = resultSet.getInt("id");
                    String mail_user = resultSet.getString("mail_user");
                    String add_date = resultSet.getString("add_date");

                    // Create new Search_All_mod object with retrieved data
                    Search_All_mod add = new Search_All_mod(id, l_name, first_name, phone_number, mail_user, add_date);
                    adds.add(add);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            disconnect();
        }

        return adds;
    }

    public boolean SureDeleteContacts(Search_All_mod del) throws SQLException {
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

    public boolean BlockContacts(Search_All_mod block) throws SQLException {
        String sql = "UPDATE contacts SET access = ? WHERE id = ?";
        connect();

        int id = block.getId();
        System.out.println("Connexion établie. ID à bloquer: " + id);

        PreparedStatement statement = con.prepareStatement(sql);
        statement.setInt(1, 0);
        statement.setInt(2, id);

        boolean rowDeleted = statement.executeUpdate() > 0;

        disconnect();
        return rowDeleted;
    }

}
