/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package List_mod;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ardenshley Nage
 */
public class List_mod_ctrl {

    private static List_mod_DAO List_mod_DAO;

    public List_mod_ctrl(String host, String user, String password) {

        List_mod_DAO = new List_mod_DAO(host, user, password);
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public List<List_mod_mod> getAllContacts() {
        List<List_mod_mod> lists = new ArrayList<>();
        try {
            lists = List_mod_DAO.getAllContacts();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lists;
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public boolean TableEmpty() {
        try {
            return List_mod_DAO.TableEmpty();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public boolean AllAccessZero() {
        try {
            return List_mod_DAO.AllAccessZero();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}
