/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Block;

import App.App;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import java.sql.*;
import java.util.InputMismatchException;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Ardenshley Nage
 */
public class Block_view extends javax.swing.JFrame {

    private static Block_ctrl blockCtrl;
    private static final String HOST = "jdbc:mysql://localhost:3306/contact_manager";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    /**
     * Creates new form Block_view
     */
    public Block_view() {
        initComponents();
        blockCtrl = new Block_ctrl(HOST, USER, PASSWORD);
        populateTable2();
        addTableMouseListener();
    }

    private void addTableMouseListener() {
        jTable2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int selectedRow = jTable2.getSelectedRow();
                if (selectedRow >= 0) {
                    if (SwingUtilities.isLeftMouseButton(e)) {
                        // Utiliser le contrôleur pour récupérer l'ID du contact
                        int contactId = blockCtrl.getContactIdByRow(selectedRow);

                        // Récupération des détails du contact sélectionné
                        String nom = jTable2.getValueAt(selectedRow, 1).toString();
                        String prenom = jTable2.getValueAt(selectedRow, 2).toString();
                        String telephone = jTable2.getValueAt(selectedRow, 3).toString();
                        String email = jTable2.getValueAt(selectedRow, 4).toString();
                        String dateAjout = jTable2.getValueAt(selectedRow, 5).toString();

                        String message = String.format(
                                "Nom: %s\nPrénom: %s\nNuméro de Téléphone: %s\nE-mail: %s\nDate d'ajout: %s",
                                nom, prenom, telephone, email, dateAjout
                        );

                        // Demande de confirmation de blocage
                        int option = JOptionPane.showOptionDialog(
                                Block_view.this,
                                message + "\n\nVoulez-vous réellement bloquer cet utilisateur ?", // Ajout du message ici
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
                            Block_mod block = new Block_mod(
                                    contactId, // Utilisation de l'ID récupéré via le contrôleur
                                    nom,
                                    prenom,
                                    Integer.parseInt(telephone),
                                    email,
                                    dateAjout
                            );

                            // Appel au contrôleur pour bloquer l'utilisateur
                            blockContacts(block);
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

// Méthode pour bloquer un contact via le contrôleur
    @SuppressWarnings("CallToPrintStackTrace")
    public void blockContacts(Block_mod block) {
        try {
            // Instancier le DAO avec les informations de connexion appropriées
            String dbUrl = "jdbc:mysql://localhost:3306/contact_manager";
            String dbUser = "root";
            String dbPassword = "";

            Block_DAO blockDAO = new Block_DAO(dbUrl, dbUser, dbPassword);

            // Appel de la méthode non statique via l'instance
            boolean success = blockDAO.blockContacts(block);

            if (success) {
                JOptionPane.showMessageDialog(
                        Block_view.this,
                        "L'utilisateur a été bloqué avec succès.",
                        "Succès",
                        JOptionPane.INFORMATION_MESSAGE
                );
            } else {
                JOptionPane.showMessageDialog(
                        Block_view.this,
                        "Échec du blocage de l'utilisateur.",
                        "Erreur",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(
                    Block_view.this,
                    "Une erreur est survenue lors du blocage de l'utilisateur.",
                    "Erreur",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void BlockAllContactsQuestion() {
        // Show a dialog with Yes and No options
        int option = JOptionPane.showOptionDialog(
                null,
                "Voulez-vous réellement bloquez tous les contacts ?",
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
        boolean success = blockCtrl.blockAllContacts();

        if (success) {
            JOptionPane.showMessageDialog(this, "Tous les contacts ont été bloqués.", "Information", JOptionPane.INFORMATION_MESSAGE);
            App.main(null);
        } else {
            JOptionPane.showMessageDialog(this, "Erreur lors du blocage des contacts.", "Erreur", JOptionPane.ERROR_MESSAGE);
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
    private void populateTable2() {
        try {
            // Vérifier si la table est vide
          

            // Récupérer les contacts via le contrôleur
            List<Block_mod> contacts = blockCtrl.getAllContacts();

            // Obtenir le modèle de la table pour pouvoir y ajouter des lignes
            DefaultTableModel model = (DefaultTableModel) jTable2.getModel();

            // Effacer les lignes existantes dans le tableau
            model.setRowCount(0);
            int rowNumber = 1;

            // Ajouter chaque contact dans le tableau
            for (Block_mod contact : contacts) {
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
        back_home = new javax.swing.JButton();
        ExitApp = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        blockAll = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Bloquer un contact");
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(51, 0, 153));

        back_home.setBackground(new java.awt.Color(0, 0, 0));
        back_home.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        back_home.setForeground(new java.awt.Color(255, 255, 255));
        back_home.setText("Retour");
        back_home.setMaximumSize(new java.awt.Dimension(78, 33));
        back_home.setMinimumSize(new java.awt.Dimension(78, 33));
        back_home.setPreferredSize(new java.awt.Dimension(78, 33));
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
        ExitApp.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        ExitApp.setForeground(new java.awt.Color(255, 255, 255));
        ExitApp.setLabel("Quitter");
        ExitApp.setMaximumSize(new java.awt.Dimension(78, 33));
        ExitApp.setMinimumSize(new java.awt.Dimension(78, 33));
        ExitApp.setPreferredSize(new java.awt.Dimension(78, 33));
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

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(jTable2);

        jLabel1.setBackground(new java.awt.Color(0, 0, 0));
        jLabel1.setFont(new java.awt.Font("Arial Black", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Bloquer un contact");
        jLabel1.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 3, 0, new java.awt.Color(255, 255, 255)));

        blockAll.setBackground(new java.awt.Color(0, 0, 0));
        blockAll.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        blockAll.setForeground(new java.awt.Color(255, 255, 255));
        blockAll.setText("Bloquez tous les contacts");
        blockAll.setMaximumSize(new java.awt.Dimension(78, 33));
        blockAll.setMinimumSize(new java.awt.Dimension(78, 33));
        blockAll.setPreferredSize(new java.awt.Dimension(78, 33));
        blockAll.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                blockAll.setBackground(Color.WHITE);
                blockAll.setForeground(Color.BLACK);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                blockAll.setBackground(Color.BLACK);
                blockAll.setForeground(Color.WHITE);
            }
        });
        blockAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                blockAllActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(back_home, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(ExitApp, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 598, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(192, 192, 192))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(185, 185, 185)
                .addComponent(blockAll, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(back_home, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ExitApp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(42, 42, 42)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(blockAll, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

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

    private void back_homeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_back_homeActionPerformed
        // TODO add your handling code here:
        App.main(null);
    }//GEN-LAST:event_back_homeActionPerformed

    private void ExitAppActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ExitAppActionPerformed
        // TODO add your handling code here:
        quitApplication4();
    }//GEN-LAST:event_ExitAppActionPerformed

    private void blockAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_blockAllActionPerformed
        // TODO add your handling code here:
        BlockAllContactsQuestion();
    }//GEN-LAST:event_blockAllActionPerformed

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
            java.util.logging.Logger.getLogger(Block_view.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Block_view.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Block_view.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Block_view.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Block_view().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ExitApp;
    private javax.swing.JButton back_home;
    private javax.swing.JButton blockAll;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable2;
    // End of variables declaration//GEN-END:variables
}
