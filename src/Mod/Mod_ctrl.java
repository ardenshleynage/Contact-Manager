/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Mod;

import java.sql.SQLException;

/**
 *
 * @author Ardenshley Nage
 */
public class Mod_ctrl {

    private static Mod_DAO Mod_DAO;

    public Mod_ctrl(String host, String user, String password) {

        Mod_DAO = new Mod_DAO(host, user, password);
    }

    public boolean existsByPhoneNumber(int phone_number) {
        return Mod_DAO.existsByPhoneNumber(phone_number);
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public boolean TableEmpty() {
        try {
            return Mod_DAO.TableEmpty();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public boolean AllAccessZero() {
        try {
            return Mod_DAO.AllAccessZero();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public void updateLastNameContact(Mod_mod modln) {
        try {
            Mod_DAO.updateLastNameContact(modln);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public void updateFirstNameContact(Mod_mod modfn) {
        try {
            Mod_DAO.updateFirstNameContact(modfn);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public void updateEmailContact(Mod_mod mode) {
        try {
            Mod_DAO.updateEmailContact(mode);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public boolean updatePhoneNumberContact(Mod_mod modpn) {
        try {
            return Mod_DAO.updatePhoneNumberContact(modpn);  // Retourne le résultat de la mise à jour
        } catch (SQLException e) {
            e.printStackTrace();
            return false;  // Retourne false en cas d'exception
        }
    }

}
