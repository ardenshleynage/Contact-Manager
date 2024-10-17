/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Delete;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ardenshley Nage
 */
public class Delete_ctrl {

    private static Delete_DAO Delete_DAO;

    public Delete_ctrl(String host, String user, String password) {

        Delete_DAO = new Delete_DAO(host, user, password);
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public List<Delete_mod> getAllContacts() {
        List<Delete_mod> blocks = new ArrayList<>();
        try {
            blocks = Delete_DAO.getAllContacts();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return blocks;
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public void SureDeleteContacts(Delete_mod del) {
        try {
            Delete_DAO.SureDeleteContacts(del);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public boolean TableEmpty() {
        try {
            return Delete_DAO.TableEmpty();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public boolean AllAccessZero() {
        try {
            return Delete_DAO.AllAccessZero();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public boolean deleteAllContacts() {
        try {
            return Delete_DAO.deleteAllContacts();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public int getContactIdByRow(int rowIndex) {
        List<Delete_mod> contacts = getAllContacts();
        return contacts.get(rowIndex).getId();
    }

}
