/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Search.Search_Email;

import java.sql.*;
import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author Ardenshley Nage
 */
public class Search_Em_DAO {

    private String URL = "jdbc:mysql://localhost:3306/contact_manager";
    private String USER = "root";
    private String PASSWORD = "";
    private static Connection con;
    private static PreparedStatement pst;
    private static ResultSet rs;

    public Search_Em_DAO(String URL, String USER, String PASSWORD) {
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
    public List<Search_Em_mod> searchByEmail(String mail) throws SQLException {
        List<Search_Em_mod> adds = new ArrayList<>();
        String sql = "SELECT * FROM contacts WHERE mail_user LIKE ? AND access = 1";
        connect();

        try (PreparedStatement statement = con.prepareStatement(sql)) {
            statement.setString(1, "%" + mail + "%");

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    String l_name = resultSet.getString("last_name");
                    String first_name = resultSet.getString("first_name");
                    int phone_number = resultSet.getInt("phone_number");
                    int id = resultSet.getInt("id");
                    String mail_user = resultSet.getString("mail_user");
                    String add_date = resultSet.getString("add_date");

                    Search_Em_mod add = new Search_Em_mod(id, l_name, first_name, phone_number, mail_user, add_date);
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
    
    public boolean SureDeleteContacts(Search_Em_mod del) throws SQLException {
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

    public boolean BlockContacts(Search_Em_mod block) throws SQLException {
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
