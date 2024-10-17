/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package List_Block;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ardenshley Nage
 */
public class List_Block_ctrl {

    private static List_Block_DAO List_Block_DAO;

    public List_Block_ctrl(String host, String user, String password) {

        List_Block_DAO = new List_Block_DAO(host, user, password);
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public List<List_Block_mod> getAllBlockContacts() {
        List<List_Block_mod> listbs = new ArrayList<>();
        try {
            listbs = List_Block_DAO.getAllBlockContacts();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listbs;
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public boolean TableEmpty() {
        try {
            return List_Block_DAO.IsTableEmpty();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public void AccessContacts(List_Block_mod block) {
        try {
            List_Block_DAO.AccessContacts(block);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public boolean SureDeleteContacts(List_Block_mod del) {
        try {
            List_Block_DAO.SureDeleteContacts(del);
            return true;  // Retourne true si la suppression réussit sans exception
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Retourne false si une exception est attrapée
        }
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public boolean AllAccessOne() {
        try {
            return List_Block_DAO.IsAllAccessOne();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}
