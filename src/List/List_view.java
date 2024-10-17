/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package List;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.*;
import javax.swing.*;
import App.App;
import Mod.Mod_view;
import java.awt.Color;

import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Ardenshley Nage
 */
public class List_view extends javax.swing.JFrame {

    private static List_ctrl listCtrl;
    private static final String HOST = "jdbc:mysql://localhost:3306/contact_manager";
    private static final String USER = "root";
    private static final String PASSWORD = "";
    private List_mod del;

    /**
     * Creates new form List_view
     */
    public List_view() {
        initComponents();
        listCtrl = new List_ctrl(HOST, USER, PASSWORD);
        populateTable();
        addTableMouseListener();
    }

    private void addTableMouseListener() {
        jTable1.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                int selectedRow = jTable1.getSelectedRow();
                if (selectedRow >= 0 && SwingUtilities.isLeftMouseButton(e)) {
                    List<List_mod> contacts = listCtrl.getAllContacts();
                    List_mod block = contacts.get(selectedRow);
                    del = contacts.get(selectedRow);

                    if (block != null) {
                        String numero = jTable1.getValueAt(selectedRow, 0).toString();
                        String nom = jTable1.getValueAt(selectedRow, 1).toString();
                        String prenom = jTable1.getValueAt(selectedRow, 2).toString();
                        String telephone = jTable1.getValueAt(selectedRow, 3).toString();
                        String email = jTable1.getValueAt(selectedRow, 4).toString();
                        String dateAjout = jTable1.getValueAt(selectedRow, 5).toString();
                        String id = jTable1.getValueAt(selectedRow, 6).toString();

                        String message = String.format(
                                "N°: %s\nNom: %s\nPrénom: %s\nNuméro de Téléphone: %s\nE-mail: %s\nDate d'ajout: %s",
                                numero, nom, prenom, telephone, email, dateAjout
                        );

                        int option = JOptionPane.showOptionDialog(
                                List_view.this,
                                message,
                                "Détails du contact",
                                JOptionPane.DEFAULT_OPTION,
                                JOptionPane.INFORMATION_MESSAGE,
                                null,
                                new Object[]{"Modifier", "Bloquer", "Supprimer", "Annuler"},
                                "Annuler"
                        );

                        switch (option) {
                            case 0: // "Modifier"
                                Mod_view modView = new Mod_view(nom, prenom, telephone, email, id);
                                modView.setVisible(true);
                                break;
                            case 1: // "Bloquer"
                                listCtrl.BlockContacts(block);
                                populateTable();
                                JOptionPane.showMessageDialog(List_view.this, "Contact bloquer avec succès", "Bloquer", JOptionPane.INFORMATION_MESSAGE);
                                break;
                            case 2: // "Supprimer"
                                listCtrl.SureDeleteContacts(del);
                                DeleteContactsQuestion();
                                break;
                            case 3: // "Annuler"
                                break;
                        }
                    } else {
                        JOptionPane.showMessageDialog(List_view.this, "Erreur : le contact sélectionné n'a pas pu être trouvé.", "Erreur", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }

        });
    }

    private void DeleteContactsQuestion() {
        // Show a dialog with Yes and No options
        int option = JOptionPane.showOptionDialog(
                null,
                "Voulez-vous réellement supprimez le contact ?",
                "Confirmation",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                new Object[]{"Oui", "Non"},
                "Non"
        );

        // Handle user selection
        if (option == JOptionPane.YES_OPTION) {
            handleDeleteAllContacts();
        } else if (option == JOptionPane.NO_OPTION) {

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

    private void handleDeleteAllContacts() {
        boolean success = listCtrl.SureDeleteContacts(del);

        if (success) {
            JOptionPane.showMessageDialog(this, "Le contacts a été supprimés.", "Information", JOptionPane.INFORMATION_MESSAGE);
            App.main(null);
        } else {
            JOptionPane.showMessageDialog(this, "Erreur lors de la suppresion du contact.", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void quitApplication3() {
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

    private void populateTable() {
        // Récupérer les contacts via le contrôleur
        List<List_mod> contacts = listCtrl.getAllContacts();

        // Obtenir le modèle de la table pour pouvoir y ajouter des lignes
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();

        // Effacer les lignes existantes dans le tableau
        model.setRowCount(0);
        int rowNumber = 1;
        // Ajouter chaque contact dans le tableau
        for (List_mod contact : contacts) {
            Object[] rowData = new Object[]{
                rowNumber++,
                contact.getLast_name(),
                contact.getFirst_name(),
                contact.getPhone_number(),
                contact.getMail_user(),
                contact.getAdd_date(),
                contact.getId()
            };
            model.addRow(rowData);
        }
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
        list_contacts_alll = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        back_menu = new javax.swing.JButton();
        exitApp = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(51, 0, 153));

        list_contacts_alll.setFont(new java.awt.Font("Arial Black", 1, 18)); // NOI18N
        list_contacts_alll.setForeground(new java.awt.Color(255, 255, 255));
        list_contacts_alll.setText("Liste des contacts (autoriser)");
        list_contacts_alll.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 3, 0, new java.awt.Color(255, 255, 255)));

        jTable1.setModel(
            new javax.swing.table.DefaultTableModel(
                new Object [][] {},
                new String [] {
                    "N°", "Nom", "Prénom", "Numéro de Téléphone", "E-mail", "Date d'ajout","Id"
                }
            ) {
                Class[] types = new Class [] {
                    java.lang.Integer.class,
                    java.lang.String.class
                    , java.lang.String.class
                    , java.lang.Integer.class
                    , java.lang.String.class
                    , java.lang.String.class,
                    java.lang.Integer.class
                };

                public Class getColumnClass(int columnIndex) {
                    return types[columnIndex];
                }
                public boolean isCellEditable(int rowIndex, int columnIndex) {
                    return false;
                }
            }
        );
        jScrollPane1.setViewportView(jTable1);
        jTable1.getColumnModel().getColumn(6).setMinWidth(0);
        jTable1.getColumnModel().getColumn(6).setMaxWidth(0);
        jTable1.getColumnModel().getColumn(6).setWidth(0);
        jTable1.getColumnModel().getColumn(6).setPreferredWidth(0);

        back_menu.setBackground(new java.awt.Color(0, 0, 0));
        back_menu.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        back_menu.setForeground(new java.awt.Color(255, 255, 255));
        back_menu.setText("Retour");
        back_menu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                back_menuActionPerformed(evt);
            }
        });
        back_menu.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                back_menu.setBackground(Color.WHITE);
                back_menu.setForeground(Color.BLACK);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                back_menu.setBackground(Color.BLACK);
                back_menu.setForeground(Color.WHITE);
            }
        });

        exitApp.setBackground(new java.awt.Color(0, 0, 0));
        exitApp.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        exitApp.setForeground(new java.awt.Color(255, 255, 255));
        exitApp.setText("Quitter");
        exitApp.setMaximumSize(new java.awt.Dimension(78, 33));
        exitApp.setMinimumSize(new java.awt.Dimension(78, 33));
        exitApp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitAppActionPerformed(evt);
            }
        });
        exitApp.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                exitApp.setBackground(Color.WHITE);
                exitApp.setForeground(Color.BLACK);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                exitApp.setBackground(Color.BLACK);
                exitApp.setForeground(Color.WHITE);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 598, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(back_menu)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(exitApp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(152, 152, 152)
                .addComponent(list_contacts_alll)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(list_contacts_alll)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(back_menu)
                    .addComponent(exitApp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(118, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
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

    private void back_menuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_back_menuActionPerformed
        // TODO add your handling code here:
        App.main(null);
    }//GEN-LAST:event_back_menuActionPerformed

    private void exitAppActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitAppActionPerformed
        // TODO add your handling code here:
        quitApplication3();
    }//GEN-LAST:event_exitAppActionPerformed

    /**
     * @param args the command line arguments
     */
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
            java.util.logging.Logger.getLogger(List_view.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(List_view.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(List_view.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(List_view.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new List_view().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton back_menu;
    private javax.swing.JButton exitApp;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel list_contacts_alll;
    // End of variables declaration//GEN-END:variables
}
