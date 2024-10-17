/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Search.Search_FirstName;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ardenshley Nage
 */
public class Search_Fn_ctrl {

    private static Search_Fn_DAO Search_Fn_DAO;

    public Search_Fn_ctrl(String host, String user, String password) {

        Search_Fn_DAO = new Search_Fn_DAO(host, user, password);
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public List<Search_Fn_mod> searchByFirstName(String first_name) {
        List<Search_Fn_mod> adds = new ArrayList<>();
        try {
            adds = Search_Fn_DAO.searchByFirstName(first_name);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return adds;
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public boolean SureDeleteContacts(Search_Fn_mod del) {
        try {
            Search_Fn_DAO.SureDeleteContacts(del);
            return true;  // Retourne true si la suppression réussit sans exception
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Retourne false si une exception est attrapée
        }
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public boolean BlockContacts(Search_Fn_mod block) {
        try {
            Search_Fn_DAO.BlockContacts(block);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}
