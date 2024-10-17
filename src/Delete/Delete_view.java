/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Delete;

import java.awt.Color;
import java.awt.HeadlessException;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.sql.*;
import App.App;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Ardenshley Nage
 */
public class Delete_view extends javax.swing.JFrame {

    private static Delete_ctrl DeleteCtrl;
    private static final String HOST = "jdbc:mysql://localhost:3306/contact_manager";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    /**
     * Creates new form Delete_view
     */
    public Delete_view() {
        initComponents();
        DeleteCtrl = new Delete_ctrl(HOST, USER, PASSWORD);
        populateTable2();
        addTableMouseListener();
    }

    private void addTableMouseListener() {
        jTable1.addMouseListener(new MouseAdapter() {
            private Delete_mod del;

            @Override
            public void mouseClicked(MouseEvent e) {
                int selectedRow = jTable1.getSelectedRow();
                if (selectedRow >= 0) {
                    if (SwingUtilities.isLeftMouseButton(e)) {
                        // Utiliser le contrôleur pour récupérer l'ID du contact
                        int contactId = DeleteCtrl.getContactIdByRow(selectedRow);

                        // Récupération des détails du contact sélectionné
                        String nom = jTable1.getValueAt(selectedRow, 1).toString();
                        String prenom = jTable1.getValueAt(selectedRow, 2).toString();
                        String telephone = jTable1.getValueAt(selectedRow, 3).toString();
                        String email = jTable1.getValueAt(selectedRow, 4).toString();
                        String dateAjout = jTable1.getValueAt(selectedRow, 5).toString();

                        String message = String.format(
                                "Nom: %s\nPrénom: %s\nNuméro de Téléphone: %s\nE-mail: %s\nDate d'ajout: %s",
                                nom, prenom, telephone, email, dateAjout
                        );

                        // Demande de confirmation de blocage
                        int option = JOptionPane.showOptionDialog(
                                Delete_view.this,
                                message + "\n\nVoulez-vous réellement supprimer cet utilisateur ?", // Ajout du message ici
                                "Détails du contact",
                                JOptionPane.YES_NO_OPTION,
                                JOptionPane.QUESTION_MESSAGE,
                                null,
                                new Object[]{"Oui", "Non"},
                                "Non"
                        );

                        // Gestion de la sélection de l'utilisateur
                        if (option == JOptionPane.YES_OPTION) {
                            // Création d'un objet Block_mod avec les détails du contact
                            Delete_mod del = new Delete_mod(
                                    contactId, // Utilisation de l'ID récupéré via le contrôleur
                                    nom,
                                    prenom,
                                    Integer.parseInt(telephone),
                                    email,
                                    dateAjout
                            );

                            // Appel au contrôleur pour bloquer l'utilisateur
                            deleteContacts(del);
                            populateTable2();
                        } else if (option == JOptionPane.NO_OPTION) {
                            // Retour au menu principal
                            return;
                        } else {
                            // Gestion des choix non reconnus
                            JOptionPane.showMessageDialog(null,
                                    "Erreur : choix non reconnu.",
                                    "Erreur",
                                    JOptionPane.ERROR_MESSAGE
                            );
                        }
                    }
                }
            }
        });
    }

    private void DeleteAllContactsQuestion() {
        // Show a dialog with Yes and No options
        int option = JOptionPane.showOptionDialog(
                null,
                "Voulez-vous réellement supprimez tous les contacts ?",
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
        boolean success = DeleteCtrl.deleteAllContacts();

        if (success) {
            JOptionPane.showMessageDialog(this, "Tous les contacts ont été supprimés.", "Information", JOptionPane.INFORMATION_MESSAGE);
            App.main(null);
        } else {
            JOptionPane.showMessageDialog(this, "Erreur lors de la suppresion des contacts.", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void quitApplication4() {
        // Show a dialog with Yes and No options
        int option = JOptionPane.showOptionDialog(
                null,
                "Voulez-vous réellement quitter l'application ?",
                "Confirmation",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                new Object[]{"Oui", "Non"},
                "Non"
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

    @SuppressWarnings("CallToPrintStackTrace")
    public void deleteContacts(Delete_mod del) {
        try {
            // Instancier le DAO avec les informations de connexion appropriées
            String dbUrl = "jdbc:mysql://localhost:3306/contact_manager";
            String dbUser = "root";
            String dbPassword = "";

            Delete_DAO deleteDAO = new Delete_DAO(dbUrl, dbUser, dbPassword);

            // Appel de la méthode non statique via l'instance
            boolean success = deleteDAO.SureDeleteContacts(del);

            if (success) {
                JOptionPane.showMessageDialog(
                        Delete_view.this,
                        "L'utilisateur a été supprimé avec succès.",
                        "Succès",
                        JOptionPane.INFORMATION_MESSAGE
                );
            } else {
                JOptionPane.showMessageDialog(
                        Delete_view.this,
                        "Échec de la suppresion de l'utilisateur.",
                        "Erreur",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(
                    Delete_view.this,
                    "Une erreur est survenue lors de la suppresion de l'utilisateur.",
                    "Erreur",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    @SuppressWarnings("CallToPrintStackTrace")
    private void populateTable2() {
        try {
            // Vérifier si la table est vide

            // Récupérer les contacts via le contrôleur
            List<Delete_mod> contacts = DeleteCtrl.getAllContacts();

            // Obtenir le modèle de la table pour pouvoir y ajouter des lignes
            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();

            // Effacer les lignes existantes dans le tableau
            model.setRowCount(0);
            int rowNumber = 1;

            // Ajouter chaque contact dans le tableau
            for (Delete_mod contact : contacts) {
                Object[] rowData = new Object[]{
                    rowNumber++,
                    contact.getLast_name(),
                    contact.getFirst_name(),
                    contact.getPhone_number(),
                    contact.getMail_user(),
                    contact.getAdd_date()
                };
                model.addRow(rowData);
            }
        } catch (HeadlessException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erreur lors de la récupération des contacts.", "Erreur", JOptionPane.ERROR_MESSAGE);
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
        del_user = new javax.swing.JLabel();
        back_home = new javax.swing.JButton();
        ExitApp = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        delete_all_ctc = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(51, 0, 153));

        del_user.setFont(new java.awt.Font("Arial Black", 1, 18)); // NOI18N
        del_user.setForeground(new java.awt.Color(255, 255, 255));
        del_user.setText("Supprimer un contact");
        del_user.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 3, 0, new java.awt.Color(255, 255, 255)));

        back_home.setBackground(new java.awt.Color(0, 0, 0));
        back_home.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        back_home.setForeground(new java.awt.Color(255, 255, 255));
        back_home.setText("Retour");
        back_home.setMaximumSize(new java.awt.Dimension(79, 33));
        back_home.setMinimumSize(new java.awt.Dimension(79, 33));
        back_home.setPreferredSize(new java.awt.Dimension(79, 33));
        back_home.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                back_home.setBackground(Color.WHITE);
                back_home.setForeground(Color.BLACK);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                back_home.setBackground(Color.BLACK);
                back_home.setForeground(Color.WHITE);
            }
        });
        back_home.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                back_homeActionPerformed(evt);
            }
        });

        ExitApp.setBackground(new java.awt.Color(0, 0, 0));
        ExitApp.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        ExitApp.setForeground(new java.awt.Color(255, 255, 255));
        ExitApp.setText("Quitter");
        ExitApp.setMaximumSize(new java.awt.Dimension(79, 33));
        ExitApp.setMinimumSize(new java.awt.Dimension(79, 33));
        ExitApp.setPreferredSize(new java.awt.Dimension(79, 33));
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
        ExitApp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ExitAppActionPerformed(evt);
            }
        });

        jTable1.setModel(
            new javax.swing.table.DefaultTableModel(
                new Object [][] {},
                new String [] {
                    "N°", "Nom", "Prénom", "Numéro de Téléphone", "E-mail", "Date d'ajout"
                }
            ) {
                Class[] types = new Class [] {
                    java.lang.Integer.class,
                    java.lang.String.class
                    , java.lang.String.class
                    , java.lang.Integer.class
                    , java.lang.String.class
                    , java.lang.String.class
                };

                public Class getColumnClass(int columnIndex) {
                    return types[columnIndex];
                }
                public boolean isCellEditable(int rowIndex, int columnIndex) {
                    return false;
                }
            });
            jScrollPane1.setViewportView(jTable1);

            delete_all_ctc.setBackground(new java.awt.Color(0, 0, 0));
            delete_all_ctc.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
            delete_all_ctc.setForeground(new java.awt.Color(255, 255, 255));
            delete_all_ctc.setText("Supprimer tous les contacts");
            delete_all_ctc.setMaximumSize(new java.awt.Dimension(79, 33));
            delete_all_ctc.setMinimumSize(new java.awt.Dimension(79, 33));
            delete_all_ctc.setPreferredSize(new java.awt.Dimension(79, 33));
            delete_all_ctc.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    delete_all_ctc.setBackground(Color.WHITE);
                    delete_all_ctc.setForeground(Color.BLACK);
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    delete_all_ctc.setBackground(Color.BLACK);
                    delete_all_ctc.setForeground(Color.WHITE);
                }
            });
            delete_all_ctc.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    delete_all_ctcActionPerformed(evt);
                }
            });

            javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
            jPanel1.setLayout(jPanel1Layout);
            jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(24, 24, 24)
                    .addComponent(back_home, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ExitApp, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(31, 31, 31))
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 610, Short.MAX_VALUE)
                    .addContainerGap())
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                            .addComponent(del_user)
                            .addGap(174, 174, 174))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                            .addComponent(delete_all_ctc, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(163, 163, 163))))
            );
            jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(30, 30, 30)
                    .addComponent(del_user)
                    .addGap(18, 18, 18)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(back_home, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(ExitApp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(31, 31, 31)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addComponent(delete_all_ctc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(29, Short.MAX_VALUE))
            );

            javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
            getContentPane().setLayout(layout);
            layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

    private void back_homeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_back_homeActionPerformed
        // TODO add your handling code here:
        App.main(null);
    }//GEN-LAST:event_back_homeActionPerformed

    private void ExitAppActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ExitAppActionPerformed
        // TODO add your handling code here:
        quitApplication4();
    }//GEN-LAST:event_ExitAppActionPerformed

    private void delete_all_ctcActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_delete_all_ctcActionPerformed
        // TODO add your handling code here:
        DeleteAllContactsQuestion();
    }//GEN-LAST:event_delete_all_ctcActionPerformed

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
            java.util.logging.Logger.getLogger(Delete_view.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Delete_view.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Delete_view.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Delete_view.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Delete_view().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ExitApp;
    private javax.swing.JButton back_home;
    private javax.swing.JLabel del_user;
    private javax.swing.JButton delete_all_ctc;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
