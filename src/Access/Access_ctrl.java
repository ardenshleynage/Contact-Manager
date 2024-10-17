/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Access;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ardenshley Nage
 */
public class Access_ctrl {

    private static Access_DAO Access_DAO;

    public Access_ctrl(String host, String user, String password) {

        Access_DAO = new Access_DAO(host, user, password);
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public List<Access_mod> getAllBlockContacts() {
        List<Access_mod> Access = new ArrayList<>();
        try {
            Access = Access_DAO.getAllBlockContacts();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Access;
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public void AllowContacts(Access_mod ace) {
        try {
            Access_DAO.AllowContacts(ace);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public boolean TableEmpty() {
        try {
            return Access_DAO.TableEmpty();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public boolean AllAccessOne() {
        try {
            return Access_DAO.AllAccessOne();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public boolean AllowAllContacts() {
        try {
            return Access_DAO.AllowAllContacts();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public int getContactIdByRow(int rowIndex) {
        List<Access_mod> contacts = getAllBlockContacts();
        return contacts.get(rowIndex).getId();
    }

}
