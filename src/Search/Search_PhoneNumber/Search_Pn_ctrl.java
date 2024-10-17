/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Search.Search_PhoneNumber;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ardenshley Nage
 */
public class Search_Pn_ctrl {

    private static Search_Pn_DAO Search_Pn_DAO;

    public Search_Pn_ctrl(String host, String user, String password) {

        Search_Pn_DAO = new Search_Pn_DAO(host, user, password);
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public List<Search_Pn_mod> searchByPhoneNumber(String phone_number) {
        List<Search_Pn_mod> adds = new ArrayList<>();
        try {
            adds = Search_Pn_DAO.searchByPhoneNumber(phone_number);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return adds;
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public boolean SureDeleteContacts(Search_Pn_mod del) {
        try {
            Search_Pn_DAO.SureDeleteContacts(del);
            return true;  // Retourne true si la suppression réussit sans exception
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Retourne false si une exception est attrapée
        }
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public boolean BlockContacts(Search_Pn_mod block) {
        try {
            Search_Pn_DAO.BlockContacts(block);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}
