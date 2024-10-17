/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Add;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ardenshley Nage
 */
public class Add_ctrl {

    private static Add_DAO Add_DAO;

    public Add_ctrl(String host, String user, String password) {

        Add_DAO = new Add_DAO(host, user, password);
    }

    public boolean existsByPhoneNumber(int phone_number) {
        return Add_DAO.existsByPhoneNumber(phone_number);
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public void updateContacts(Add_mod add) {
        try {
            Add_DAO.updateContacts(add);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public void addContacts(Add_mod add) {
        try {
            Add_DAO.addContacts(add);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    

}
