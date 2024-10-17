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
public class Search_Em_ctrl {

    private static Search_Em_DAO Search_Em_DAO;

    public Search_Em_ctrl(String host, String user, String password) {

        Search_Em_DAO = new Search_Em_DAO(host, user, password);
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public List<Search_Em_mod> searchByEmail(String mail) {
        List<Search_Em_mod> adds = new ArrayList<>();
        try {
            adds = Search_Em_DAO.searchByEmail(mail);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return adds;
    }
    @SuppressWarnings("CallToPrintStackTrace")
    public boolean SureDeleteContacts(Search_Em_mod del) {
        try {
            Search_Em_DAO.SureDeleteContacts(del);
            return true;  // Retourne true si la suppression réussit sans exception
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Retourne false si une exception est attrapée
        }
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public boolean BlockContacts(Search_Em_mod block) {
        try {
            Search_Em_DAO.BlockContacts(block);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}
