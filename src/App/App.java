/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package App;

import javax.swing.*;
import java.awt.event.ActionEvent;
import Add.Add_view;
import Access.Access_view;
import Block.Block_ctrl;
import Block.Block_view;
import Access.Access_ctrl;
import List.List_ctrl;
import List_Block.List_Block_view;
import List_Block.List_Block_ctrl;
import List.List_view;
import Search.Search_view;
import Delete.Delete_view;
import Delete.Delete_ctrl;
import List_mod.List_mod_view;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 *
 * @author Ardenshley Nage
 */
public class App extends javax.swing.JFrame {

    private static Block_ctrl blockCtrl;
    private static List_ctrl listCtrl;
    private static Access_ctrl accessCtrl;
    private static List_Block_ctrl listBlockCtrl;
    private static Delete_ctrl DeleteCtrl;
    private static final String HOST = "jdbc:mysql://localhost:3306/contact_manager";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    /**
     * Creates new form App
     */
    public App() {
        initComponents();
        blockCtrl = new Block_ctrl(HOST, USER, PASSWORD);
        listCtrl = new List_ctrl(HOST, USER, PASSWORD);
        accessCtrl = new Access_ctrl(HOST, USER, PASSWORD);
        listBlockCtrl = new List_Block_ctrl(HOST, USER, PASSWORD);
        DeleteCtrl = new Delete_ctrl(HOST, USER, PASSWORD);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        menu = new javax.swing.JLabel();
        mod_contacts = new javax.swing.JLabel();
        del_contacts = new javax.swing.JLabel();
        block_contacts = new javax.swing.JLabel();
        search_contacts = new javax.swing.JLabel();
        list_contacts = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        ExitApp = new javax.swing.JButton();
        list_contacts_block = new javax.swing.JLabel();
        allow_contacts = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Gestionnaire de contacts");
        setBackground(new java.awt.Color(204, 204, 0));
        setForeground(new java.awt.Color(0, 0, 0));
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(51, 0, 153));

        menu.setFont(new java.awt.Font("Arial Black", 1, 18)); // NOI18N
        menu.setForeground(new java.awt.Color(255, 255, 255));
        menu.setText("Menu");
        menu.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 3, 0, new java.awt.Color(255, 255, 255)));

        add_contacts.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        add_contacts.setForeground(new java.awt.Color(255, 255, 255));
        add_contacts.setText("1- Ajoutez un nouveau contact");
        add_contacts.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        add_contacts.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                handleChoice(1);
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                add_contacts.setForeground(Color.BLACK);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                add_contacts.setForeground(Color.WHITE);
            }
        });

        mod_contacts.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        mod_contacts.setForeground(new java.awt.Color(255, 255, 255));
        mod_contacts.setText("2- Modifier un contact");
        mod_contacts.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        mod_contacts.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                handleChoice(2);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                mod_contacts.setForeground(Color.BLACK);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                mod_contacts.setForeground(Color.WHITE);
            }
        });

        del_contacts.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        del_contacts.setForeground(new java.awt.Color(255, 255, 255));
        del_contacts.setText("3- Supprimer un contact");
        del_contacts.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        del_contacts.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                handleChoice(3);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                del_contacts.setForeground(Color.BLACK);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                del_contacts.setForeground(Color.WHITE);
            }
        });

        block_contacts.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        block_contacts.setForeground(new java.awt.Color(255, 255, 255));
        block_contacts.setText("5- Bloquer un contact");
        block_contacts.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        block_contacts.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                handleChoice(5);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                block_contacts.setForeground(Color.BLACK);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                block_contacts.setForeground(Color.WHITE);
            }
        });

        search_contacts.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        search_contacts.setForeground(new java.awt.Color(255, 255, 255));
        search_contacts.setText("6- Rechercher un contact");
        search_contacts.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        search_contacts.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                handleChoice(6);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                search_contacts.setForeground(Color.BLACK);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                search_contacts.setForeground(Color.WHITE);
            }
        });

        list_contacts.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        list_contacts.setForeground(new java.awt.Color(255, 255, 255));
        list_contacts.setText("7- Liste des contacts (autoriser) ");
        list_contacts.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        list_contacts.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                handleChoice(7);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                list_contacts.setForeground(Color.BLACK);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                list_contacts.setForeground(Color.WHITE);
            }
        });

        jLabel9.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N

        ExitApp.setBackground(new java.awt.Color(0, 0, 0));
        ExitApp.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        ExitApp.setForeground(new java.awt.Color(255, 255, 255));
        ExitApp.setText("Quittez");
        ExitApp.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        ExitApp.setMaximumSize(new java.awt.Dimension(78, 33));
        ExitApp.setMinimumSize(new java.awt.Dimension(78, 33));
        ExitApp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ExitAppActionPerformed(evt);
            }
        });
        ExitApp.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                ExitApp.setBackground(Color.WHITE);
                ExitApp.setForeground(Color.BLACK);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                ExitApp.setBackground(Color.BLACK);
                ExitApp.setForeground(Color.WHITE);
            }
        });

        list_contacts_block.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        list_contacts_block.setForeground(new java.awt.Color(255, 255, 255));
        list_contacts_block.setText("8- Liste des contacts (bloquer) ");
        list_contacts_block.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        list_contacts_block.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                handleChoice(8);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                list_contacts_block.setForeground(Color.BLACK);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                list_contacts_block.setForeground(Color.WHITE);
            }
        });

        allow_contacts.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        allow_contacts.setForeground(new java.awt.Color(255, 255, 255));
        allow_contacts.setText("4- Autoriser un contact");
        allow_contacts.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        allow_contacts.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                handleChoice(4);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                allow_contacts.setForeground(Color.BLACK);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                allow_contacts.setForeground(Color.WHITE);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(jLabel9)
                .addGap(223, 223, 223)
                .addComponent(ExitApp, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(218, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(menu)
                        .addGap(269, 269, 269))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(list_contacts_block)
                            .addComponent(mod_contacts)
                            .addComponent(add_contacts)
                            .addComponent(del_contacts)
                            .addComponent(block_contacts)
                            .addComponent(list_contacts)
                            .addComponent(search_contacts)
                            .addComponent(allow_contacts))
                        .addGap(144, 144, 144))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(menu)
                .addGap(35, 35, 35)
                .addComponent(add_contacts, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(mod_contacts, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(del_contacts, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(allow_contacts, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(block_contacts)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(search_contacts)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(list_contacts)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(list_contacts_block)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(52, 52, 52)
                        .addComponent(jLabel9)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                        .addComponent(ExitApp, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(23, 23, 23))))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {add_contacts, block_contacts, del_contacts, list_contacts, list_contacts_block, mod_contacts, search_contacts});

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ExitAppActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ExitAppActionPerformed
        // TODO add your handling code here:
        quitApplication();
    }//GEN-LAST:event_ExitAppActionPerformed

    // Method to quit the application
    private void quitApplication() {
        // Show a dialog with Yes and No options
        int option = JOptionPane.showOptionDialog(
                null,
                "Voulez-vous réellement quitter l'application ?",
                "Confirmation",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                new Object[]{"Oui", "Non"},
                "Non" // Default button option
        );

        // Handle user selection
        if (option == JOptionPane.YES_OPTION) {
            System.exit(0);
        } else if (option == JOptionPane.NO_OPTION) {
            // Return to the main menu
            return;
        } else {
            // Handle any unexpected option, though in this case it's unlikely
            JOptionPane.showMessageDialog(null,
                    "Erreur : choix non reconnu.",
                    "Erreur",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private boolean isTableEmptyAccess() {
        return accessCtrl.TableEmpty();
    }

    private boolean areAllAccessOne() {
        // Vérifie si tous les éléments ont access = 0
        return accessCtrl.AllAccessOne();
    }

    private boolean isTableEmpty() {
        // Vérifie si la table est vide
        return blockCtrl.TableEmpty();
    }

// Méthode pour vérifier si tous les contacts ont le champ access à 0
    private boolean areAllAccessZero() {
        // Vérifie si tous les éléments ont access = 0
        return blockCtrl.AllAccessZero();
    }

    private boolean isTableEmpty_List() {
        // Vérifie si la table est vide
        return listCtrl.TableEmpty();
    }

// Méthode pour vérifier si tous les contacts ont le champ access à 0
    private boolean areAllAccessZero_List() {
        // Vérifie si tous les éléments ont access = 0
        return listCtrl.AllAccessZero();
    }

    private boolean isTableEmpty_List_Block() {
        // Vérifie si la table est vide
        return listBlockCtrl.TableEmpty();
    }

// Méthode pour vérifier si tous les contacts ont le champ access à 0
    private boolean areAllAccessOne_List() {
        // Vérifie si tous les éléments ont access = 0
        return listBlockCtrl.AllAccessOne();
    }

    private boolean isTableEmpty_Delete() {
        // Vérifie si la table est vide
        return DeleteCtrl.TableEmpty();
    }

// Méthode pour vérifier si tous les contacts ont le champ access à 0
    private boolean areAllAccessZero_Delete() {
        // Vérifie si tous les éléments ont access = 0
        return DeleteCtrl.AllAccessZero();
    }

    private void handleChoice(int choice) {
        switch (choice) {
            case 1 ->
                Add_view.main(null);
            case 2 ->
                List_mod_view.main(null);
            case 3 -> {
                if (isTableEmpty_Delete()) {
                    JOptionPane.showMessageDialog(this, "Aucun contacts.");
                } else if (areAllAccessZero_Delete()) {
                    JOptionPane.showMessageDialog(this, "Tous les contacts sont bloqués. pour supprimer les contacts bloqué`s aller dans liste contacts(bloquer)");
                } else {
                    Delete_view.main(null);
                }
            }
            case 4 -> {
                if (isTableEmptyAccess()) {
                    JOptionPane.showMessageDialog(this, "Aucun contacts.");
                } else if (areAllAccessOne()) {
                    JOptionPane.showMessageDialog(this, "Tous les contacts sont autorisés.");
                } else {
                    Access_view.main(null);
                }
            }
            case 5 -> {
                if (isTableEmpty()) {
                    JOptionPane.showMessageDialog(this, "Aucun contacts.");
                } else if (areAllAccessZero()) {
                    JOptionPane.showMessageDialog(this, "Tous les contacts sont bloqués.");
                } else {
                    Block_view.main(null);
                }
            }
            case 6 ->
                Search_view.main(null);
            case 7 -> {
                if (isTableEmpty_List()) {
                    JOptionPane.showMessageDialog(this, "Aucun contacts.");
                } else if (areAllAccessZero_List()) {
                    JOptionPane.showMessageDialog(this, "Tous les contacts sont bloqués.");
                } else {
                    List_view.main(null);
                }
            }
            case 8 -> {
                if (isTableEmpty_List_Block()) {
                    JOptionPane.showMessageDialog(this, "Aucun contacts.");
                } else if (areAllAccessOne_List()) {
                    JOptionPane.showMessageDialog(this, "Tous les contacts sont autorisés.");
                } else {
                    List_Block_view.main(null);
                }
            }
            default ->
                JOptionPane.showMessageDialog(this, "Choix invalide.");
        }
    }

    /**
     * @param args the command line arguments
     */
    @SuppressWarnings("Convert2Lambda")
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(App.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(App.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(App.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(App.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new App().setVisible(true);
                new App().setResizable(false);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ExitApp;
    private final javax.swing.JLabel add_contacts = new javax.swing.JLabel();
    private javax.swing.JLabel allow_contacts;
    private javax.swing.JLabel block_contacts;
    private javax.swing.JLabel del_contacts;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel list_contacts;
    private javax.swing.JLabel list_contacts_block;
    private javax.swing.JLabel menu;
    private javax.swing.JLabel mod_contacts;
    private javax.swing.JLabel search_contacts;
    // End of variables declaration//GEN-END:variables
}
