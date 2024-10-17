/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Block;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ardenshley Nage
 */
public class Block_ctrl {

    private static Block_DAO Block_DAO;

    public Block_ctrl(String host, String user, String password) {

        Block_DAO = new Block_DAO(host, user, password);
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public List<Block_mod> getAllContacts() {
        List<Block_mod> blocks = new ArrayList<>();
        try {
            blocks = Block_DAO.getAllContacts();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return blocks;
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public void blockContacts(Block_mod block) {
        try {
            Block_DAO.blockContacts(block);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public boolean TableEmpty() {
        try {
            return Block_DAO.TableEmpty();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public boolean AllAccessZero() {
        try {
            return Block_DAO.AllAccessZero();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    @SuppressWarnings("CallToPrintStackTrace")
     public boolean blockAllContacts() {
        try {
            return Block_DAO.blockAllContacts();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public int getContactIdByRow(int rowIndex) {
        List<Block_mod> contacts = getAllContacts();
        return contacts.get(rowIndex).getId();
    }
    
}
