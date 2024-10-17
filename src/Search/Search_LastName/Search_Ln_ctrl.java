/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Search.Search_LastName;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ardenshley Nage
 */
public class Search_Ln_ctrl {

    private static Search_Ln_DAO Search_Ln_DAO;

    public Search_Ln_ctrl(String host, String user, String password) {

        Search_Ln_DAO = new Search_Ln_DAO(host, user, password);
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public List<Search_Ln_mod> searchByLastName(String last_name) {
        List<Search_Ln_mod> adds = new ArrayList<>();
        try {
            adds = Search_Ln_DAO.searchByLastName(last_name);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return adds;
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public boolean SureDeleteContacts(Search_Ln_mod del) {
        try {
            Search_Ln_DAO.SureDeleteContacts(del);
            return true;  // Retourne true si la suppression réussit sans exception
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Retourne false si une exception est attrapée
        }
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public boolean BlockContacts(Search_Ln_mod block) {
        try {
            Search_Ln_DAO.BlockContacts(block);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}
