/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package List;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ardenshley Nage
 */
public class List_ctrl {

    private static List_DAO List_DAO;

    public List_ctrl(String host, String user, String password) {

        List_DAO = new List_DAO(host, user, password);
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public List<List_mod> getAllContacts() {
        List<List_mod> lists = new ArrayList<>();
        try {
            lists = List_DAO.getAllContacts();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lists;
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public boolean TableEmpty() {
        try {
            return List_DAO.IsTableEmpty();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public boolean AllAccessZero() {
        try {
            return List_DAO.IsAllAccessZero();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public boolean SureDeleteContacts(List_mod del) {
        try {
            List_DAO.SureDeleteContacts(del);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public void BlockContacts(List_mod block) {
        try {
            List_DAO.BlockContacts(block);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
