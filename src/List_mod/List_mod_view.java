/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package List_mod;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JOptionPane;
import App.App;
import java.util.*;
import Mod.Mod_option.Mod_Ln_view;
import Mod.Mod_option.Mod_Fn_view;
import Mod.Mod_option.Mod_Pn_view;
import Mod.Mod_option.Mod_Em_view;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Ardenshley Nage
 */
public class List_mod_view extends javax.swing.JFrame {

    private static List_mod_ctrl listmodCtrl;
    private static final String HOST = "jdbc:mysql://localhost:3306/contact_manager";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    /**
     * Creates new form List_mod_view
     */
    public List_mod_view() {
        initComponents();
        listmodCtrl = new List_mod_ctrl(HOST, USER, PASSWORD);
        populateTable();
        addTableMouseListener();
    }

    private void populateTable() {
        // Récupérer les contacts via le contrôleur
        List<List_mod_mod> contacts = listmodCtrl.getAllContacts();

        // Obtenir le modèle de la table pour pouvoir y ajouter des lignes
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();

        // Effacer les lignes existantes dans le tableau
        model.setRowCount(0);
        int rowNumber = 1;
        // Ajouter chaque contact dans le tableau
        for (List_mod_mod contact : contacts) {
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

    private void addTableMouseListener() {
        jTable1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int selectedRow = jTable1.getSelectedRow();
                if (selectedRow >= 0) {
                    if (SwingUtilities.isLeftMouseButton(e)) {
                        // Display contact details
                        String numero = jTable1.getValueAt(selectedRow, 0).toString();
                        String nom = jTable1.getValueAt(selectedRow, 1).toString();
                        String prenom = jTable1.getValueAt(selectedRow, 2).toString();
                        String telephone = jTable1.getValueAt(selectedRow, 3).toString();
                        String email = jTable1.getValueAt(selectedRow, 4).toString();
                        String dateAjout = jTable1.getValueAt(selectedRow, 5).toString();
                        String id = jTable1.getValueAt(selectedRow, 6).toString();

                        String message = String.format(
                                "N°: %s\nNom: %s\nPrénom: %s\nNuméro de Téléphone: %s\nE-mail: %s\nDate d'ajout: %s\n\nQuelle element désiré vous modifiez ?",
                                numero, nom, prenom, telephone, email, dateAjout
                        );

                        // Show option dialog with Modify, Block, and Delete options
                        int option = JOptionPane.showOptionDialog(
                                List_mod_view.this,
                                message,
                                "Détails du contact",
                                JOptionPane.DEFAULT_OPTION,
                                JOptionPane.INFORMATION_MESSAGE,
                                null,
                                new Object[]{"Nom", "Prénom", "Numéro de téléphone", "E-mail", "Retour"},
                                "Retour"
                        );

                        // Handle user selection
                        switch (option) {
                            case 0: // "Modifier"
                                Mod_Ln_view modLnView = new Mod_Ln_view(nom, id);
                                modLnView.setVisible(true);
                                break;
                            case 1: // "Bloquer"
                                Mod_Fn_view modFnView = new Mod_Fn_view(prenom, id);
                                modFnView.setVisible(true);
                                break;
                            case 2: // "Supprimer"
                                Mod_Pn_view modPnView = new Mod_Pn_view(telephone, id);
                                modPnView.setVisible(true);
                                break;
                            case 3: // "Annuler"
                                Mod_Em_view modEmView = new Mod_Em_view(email, id);
                                modEmView.setVisible(true);
                                // Optionally handle the cancel action
                                break;
                            case 4: // "Annuler"
                                // Optionally handle the cancel action
                                break;
                        }
                    }
                }
            }
        });
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

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        back_home = new javax.swing.JButton();
        ExitApp = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(51, 0, 153));

        jLabel1.setFont(new java.awt.Font("Arial Black", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Liste des contacts à modifier");
        jLabel1.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 3, 0, new java.awt.Color(255, 255, 255)));

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
                    java.lang.String.class
                };

                public Class getColumnClass(int columnIndex) {
                    return types[columnIndex];
                }
                public boolean isCellEditable(int rowIndex, int columnIndex) {
                    return false;
                }
            });
            jScrollPane1.setViewportView(jTable1);
            jTable1.getColumnModel().getColumn(6).setMinWidth(0);
            jTable1.getColumnModel().getColumn(6).setMaxWidth(0);
            jTable1.getColumnModel().getColumn(6).setWidth(0);
            jTable1.getColumnModel().getColumn(6).setPreferredWidth(0);

            back_home.setBackground(new java.awt.Color(0, 0, 0));
            back_home.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
            back_home.setForeground(new java.awt.Color(255, 255, 255));
            back_home.setText("Retour");
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

            javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
            jPanel1.setLayout(jPanel1Layout);
            jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1)
                    .addGap(127, 127, 127))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 610, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(back_home, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ExitApp, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap())
            );
            jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(20, 20, 20)
                    .addComponent(jLabel1)
                    .addGap(13, 13, 13)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(back_home)
                        .addComponent(ExitApp))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 51, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(62, 62, 62))
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

    private void ExitAppActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ExitAppActionPerformed
        // TODO add your handling code here:
        quitApplication3();
    }//GEN-LAST:event_ExitAppActionPerformed

    private void back_homeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_back_homeActionPerformed
        // TODO add your handling code here:
        App.main(null);
    }//GEN-LAST:event_back_homeActionPerformed

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
            java.util.logging.Logger.getLogger(List_mod_view.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(List_mod_view.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(List_mod_view.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(List_mod_view.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new List_mod_view().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ExitApp;
    private javax.swing.JButton back_home;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
