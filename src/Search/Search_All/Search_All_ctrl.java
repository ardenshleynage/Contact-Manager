/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Search.Search_All;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ardenshley Nage
 */
public class Search_All_ctrl {

    private static Search_All_DAO Search_All_DAO;

    public Search_All_ctrl(String host, String user, String password) {

        Search_All_DAO = new Search_All_DAO(host, user, password);
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public List<Search_All_mod> searchByAll(String afo) {
        List<Search_All_mod> adds = new ArrayList<>();
        try {
            adds = Search_All_DAO.searchByAll(afo);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return adds;
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public boolean SureDeleteContacts(Search_All_mod del) {
        try {
            Search_All_DAO.SureDeleteContacts(del);
            return true;  // Retourne true si la suppression réussit sans exception
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Retourne false si une exception est attrapée
        }
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public boolean BlockContacts(Search_All_mod block) {
        try {
            Search_All_DAO.BlockContacts(block);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}
