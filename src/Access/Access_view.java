/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Access;

import App.App;
import java.awt.Color;
import java.awt.HeadlessException;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Ardenshley Nage
 */
public class Access_view extends javax.swing.JFrame {

    private static Access_ctrl accessCtrl;
    private static final String HOST = "jdbc:mysql://localhost:3306/contact_manager";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    /**
     * Creates new form Access_view
     */
    public Access_view() {
        initComponents();
        accessCtrl = new Access_ctrl(HOST, USER, PASSWORD);
        populateTable3();
        addTableMouseListener();
    }

    @SuppressWarnings("CallToPrintStackTrace")
    private void populateTable3() {
        try {
            List<Access_mod> contacts = accessCtrl.getAllBlockContacts();

            // Obtenir le modèle de la table pour pouvoir y ajouter des lignes
            DefaultTableModel model = (DefaultTableModel) jTable3.getModel();

            // Effacer les lignes existantes dans le tableau
            model.setRowCount(0);
            int rowNumber = 1;

            // Ajouter chaque contact dans le tableau
            for (Access_mod contact : contacts) {
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

    private void addTableMouseListener() {
        jTable3.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int selectedRow = jTable3.getSelectedRow();
                if (selectedRow >= 0) {
                    if (SwingUtilities.isLeftMouseButton(e)) {
                        // Utiliser le contrôleur pour récupérer l'ID du contact
                        int contactId = accessCtrl.getContactIdByRow(selectedRow);

                        // Récupération des détails du contact sélectionné
                        String nom = jTable3.getValueAt(selectedRow, 1).toString();
                        String prenom = jTable3.getValueAt(selectedRow, 2).toString();
                        String telephone = jTable3.getValueAt(selectedRow, 3).toString();
                        String email = jTable3.getValueAt(selectedRow, 4).toString();
                        String dateAjout = jTable3.getValueAt(selectedRow, 5).toString();

                        String message = String.format(
                                "Nom: %s\nPrénom: %s\nNuméro de Téléphone: %s\nE-mail: %s\nDate d'ajout: %s",
                                nom, prenom, telephone, email, dateAjout
                        );

                        // Demande de confirmation de blocage
                        int option = JOptionPane.showOptionDialog(
                                Access_view.this,
                                message + "\n\nVoulez-vous réellement autoriser cet utilisateur ?", // Ajout du message ici
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
                            Access_mod ace = new Access_mod(
                                    contactId,
                                    nom,
                                    prenom,
                                    Integer.parseInt(telephone),
                                    email,
                                    dateAjout
                            );

                            // Appel au contrôleur pour bloquer l'utilisateur
                            AllowContacts(ace);
                            populateTable3();
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

    @SuppressWarnings("CallToPrintStackTrace")
    public void AllowContacts(Access_mod ace) {
        try {
            // Instancier le DAO avec les informations de connexion appropriées
            String dbUrl = "jdbc:mysql://localhost:3306/contact_manager";
            String dbUser = "root";
            String dbPassword = "";

            Access_DAO accessDAO = new Access_DAO(dbUrl, dbUser, dbPassword);

            // Appel de la méthode non statique via l'instance
            boolean success = accessDAO.AllowContacts(ace);

            if (success) {
                JOptionPane.showMessageDialog(
                        Access_view.this,
                        "L'utilisateur a été autorisé avec succès.",
                        "Succès",
                        JOptionPane.INFORMATION_MESSAGE
                );
            } else {
                JOptionPane.showMessageDialog(
                        Access_view.this,
                        "Échec de l'autorisation de l'utilisateur.",
                        "Erreur",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(
                    Access_view.this,
                    "Une erreur est survenue lors de l'autorisation de l'utilisateur.",
                    "Erreur",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void quitApplication5() {
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

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        back_button = new javax.swing.JButton();
        Exit_button = new javax.swing.JButton();
        allow_all_button = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();

        jLabel1.setText("jLabel1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(51, 0, 153));

        jLabel2.setFont(new java.awt.Font("Arial Black", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Autorisser un contact");
        jLabel2.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 3, 0, new java.awt.Color(255, 255, 255)));

        back_button.setBackground(new java.awt.Color(0, 0, 0));
        back_button.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        back_button.setForeground(new java.awt.Color(255, 255, 255));
        back_button.setText("Retour");
        back_button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                back_button.setBackground(Color.WHITE);
                back_button.setForeground(Color.BLACK);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                back_button.setBackground(Color.BLACK);
                back_button.setForeground(Color.WHITE);
            }
        });
        back_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                back_buttonActionPerformed(evt);
            }
        });

        Exit_button.setBackground(new java.awt.Color(0, 0, 0));
        Exit_button.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        Exit_button.setForeground(new java.awt.Color(255, 255, 255));
        Exit_button.setText("Quitter");
        Exit_button.setMaximumSize(new java.awt.Dimension(78, 33));
        Exit_button.setMinimumSize(new java.awt.Dimension(78, 33));
        Exit_button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                Exit_button.setBackground(Color.WHITE);
                Exit_button.setForeground(Color.BLACK);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                Exit_button.setBackground(Color.BLACK);
                Exit_button.setForeground(Color.WHITE);
            }
        });
        Exit_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Exit_buttonActionPerformed(evt);
            }
        });

        allow_all_button.setBackground(new java.awt.Color(0, 0, 0));
        allow_all_button.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        allow_all_button.setForeground(new java.awt.Color(255, 255, 255));
        allow_all_button.setText("Autorisez tous les contacts");
        allow_all_button.setMaximumSize(new java.awt.Dimension(78, 33));
        allow_all_button.setMinimumSize(new java.awt.Dimension(78, 33));
        allow_all_button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                allow_all_button.setBackground(Color.WHITE);
                allow_all_button.setForeground(Color.BLACK);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                allow_all_button.setBackground(Color.BLACK);
                allow_all_button.setForeground(Color.WHITE);
            }
        });
        allow_all_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                allow_all_buttonActionPerformed(evt);
            }
        });

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {},
            new String [] {
                "N°", "Nom", "Prénom", "Numéro de Téléphone", "E-mail", "Date d'ajout"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class
                ,
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
        jScrollPane1.setViewportView(jTable3);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(back_button, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Exit_button, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(191, 191, 191))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 660, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(allow_all_button, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(200, 200, 200))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(back_button)
                    .addComponent(Exit_button, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(allow_all_button, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(16, Short.MAX_VALUE))
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

    private void back_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_back_buttonActionPerformed
        // TODO add your handling code here:
        App.main(null);
    }//GEN-LAST:event_back_buttonActionPerformed

    private void Exit_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Exit_buttonActionPerformed
        // TODO add your handling code here:
        quitApplication5();
    }//GEN-LAST:event_Exit_buttonActionPerformed

    private void allow_all_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_allow_all_buttonActionPerformed
        // TODO add your handling code here:
        AllowAllContactsQuestion();
    }//GEN-LAST:event_allow_all_buttonActionPerformed

    private void AllowAllContactsQuestion() {
        // Show a dialog with Yes and No options
        int option = JOptionPane.showOptionDialog(
                null,
                "Voulez-vous réellement autorisez tous les contacts ?",
                "Confirmation",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                new Object[]{"Oui", "Non"},
                "Non"
        );

        // Handle user selection
        if (option == JOptionPane.YES_OPTION) {
            handleBlockAllContacts();
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

    private void handleBlockAllContacts() {
        boolean success = accessCtrl.AllowAllContacts();

        if (success) {
            JOptionPane.showMessageDialog(this, "Tous les contacts ont été autorisés.", "Information", JOptionPane.INFORMATION_MESSAGE);
            App.main(null);
        } else {
            JOptionPane.showMessageDialog(this, "Erreur lors de l'autorisation des contacts.", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

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
            java.util.logging.Logger.getLogger(Access_view.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Access_view.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Access_view.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Access_view.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Access_view().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Exit_button;
    private javax.swing.JButton allow_all_button;
    private javax.swing.JButton back_button;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable3;
    // End of variables declaration//GEN-END:variables
}
