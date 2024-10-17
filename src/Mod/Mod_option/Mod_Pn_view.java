/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Mod.Mod_option;

import App.App;
import List_mod.List_mod_view;
import Mod.Mod_ctrl;
import Mod.Mod_mod;
import java.awt.Color;
import java.awt.HeadlessException;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JOptionPane;

/**
 *
 * @author Ardenshley Nage
 */
public class Mod_Pn_view extends javax.swing.JFrame {

    private String currentPhoneNumber;
    private String contactId;
    private Mod_ctrl ctrlln;
    private String URL = "jdbc:mysql://localhost:3306/contact_manager";
    private String USER = "root";
    private String PASSWORD = "";

    /**
     * Creates new form Mod_Pn_view
     */
    public Mod_Pn_view(String phoneNumber, String id) {
        this.currentPhoneNumber = phoneNumber;
        this.contactId = id;
        this.ctrlln = new Mod_ctrl(URL, USER, PASSWORD);
        initComponents();
        jLabel1.setText("Numéro de téléphone actuel : " + currentPhoneNumber);
    }

    private void modifyPhoneNumber() {
        String newPhoneNumber = usr_pn_mod.getText().trim();

        if (newPhoneNumber.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Veuillez entrer un nouveau numéro de téléphone.", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!newPhoneNumber.matches("\\d{8}")) {
            javax.swing.JOptionPane.showMessageDialog(this, "Le numéro de téléphone doit contenir 8 chiffres.", "Erreur", javax.swing.JOptionPane.ERROR_MESSAGE);
            return;
        }

        Mod_mod modln = new Mod_mod();
        try {
            modln.setId(Integer.parseInt(contactId));  // Assurez-vous que contactId peut être converti en int
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "ID du contact invalide.", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            // Convertir le numéro de téléphone en entier
            int phoneNumber = Integer.parseInt(newPhoneNumber);
            modln.setPhone_number(phoneNumber);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Numéro de téléphone invalide. Veuillez entrer un numéro valide.", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            int phoneNumber = Integer.parseInt(newPhoneNumber);

            // Utiliser le numéro de téléphone converti
            if (ctrlln.existsByPhoneNumber(phoneNumber)) {
                JOptionPane.showMessageDialog(this, "Ce numéro de téléphone existe déjà.", "Erreur", JOptionPane.ERROR_MESSAGE);
                return;
            } else if (ctrlln.updatePhoneNumberContact(modln)) {
                JOptionPane.showMessageDialog(this, "Numéro de téléphone mis à jour avec succès.", "Succès", JOptionPane.INFORMATION_MESSAGE);
                App.main(null);
                currentPhoneNumber = newPhoneNumber;
                jLabel1.setText("Numéro de téléphone actuel : " + currentPhoneNumber);
            } else {
                JOptionPane.showMessageDialog(this, "Aucun contact mis à jour.", "Information", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (HeadlessException e) {
            JOptionPane.showMessageDialog(this, "Erreur lors de la mise à jour du numéro de téléphone : " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void quitApplication2() {
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
        back_mod = new javax.swing.JButton();
        ExitApp = new javax.swing.JButton();
        user_pn_mod = new javax.swing.JLabel();
        usr_pn_mod = new javax.swing.JTextField();
        btn_pn_mod = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(51, 0, 153));

        jLabel1.setFont(new java.awt.Font("Arial Black", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Numéro de téléphone actuel : " + currentPhoneNumber);
        jLabel1.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 3, 0, new java.awt.Color(255, 255, 255)));

        back_mod.setBackground(new java.awt.Color(0, 0, 0));
        back_mod.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        back_mod.setForeground(new java.awt.Color(255, 255, 255));
        back_mod.setText("Retour");
        back_mod.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                back_mod.setBackground(Color.WHITE);
                back_mod.setForeground(Color.BLACK);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                back_mod.setBackground(Color.BLACK);
                back_mod.setForeground(Color.WHITE);
            }
        });
        back_mod.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                back_modActionPerformed(evt);
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

        user_pn_mod.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        user_pn_mod.setForeground(new java.awt.Color(255, 255, 255));
        user_pn_mod.setText("Entrer le nouveau numéro : ");

        btn_pn_mod.setBackground(new java.awt.Color(0, 0, 0));
        btn_pn_mod.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        btn_pn_mod.setForeground(new java.awt.Color(255, 255, 255));
        btn_pn_mod.setText("Modifier");
        btn_pn_mod.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btn_pn_mod.setBackground(Color.WHITE);
                btn_pn_mod.setForeground(Color.BLACK);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btn_pn_mod.setBackground(Color.BLACK);
                btn_pn_mod.setForeground(Color.WHITE);
            }
        });
        btn_pn_mod.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_pn_modActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(140, 140, 140))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(back_mod, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(user_pn_mod)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(usr_pn_mod, javax.swing.GroupLayout.DEFAULT_SIZE, 264, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ExitApp, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_pn_mod, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(back_mod)
                    .addComponent(ExitApp))
                .addGap(59, 59, 59)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(user_pn_mod)
                    .addComponent(usr_pn_mod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_pn_mod))
                .addContainerGap(273, Short.MAX_VALUE))
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

    private void btn_pn_modActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_pn_modActionPerformed
        // TODO add your handling code here:
        modifyPhoneNumber();
    }//GEN-LAST:event_btn_pn_modActionPerformed

    private void ExitAppActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ExitAppActionPerformed
        // TODO add your handling code here:
        quitApplication2();
    }//GEN-LAST:event_ExitAppActionPerformed

    private void back_modActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_back_modActionPerformed
        // TODO add your handling code here:
        App.main(null);
    }//GEN-LAST:event_back_modActionPerformed

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
            java.util.logging.Logger.getLogger(Mod_Pn_view.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Mod_Pn_view.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Mod_Pn_view.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Mod_Pn_view.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Mod_Pn_view("", "").setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ExitApp;
    private javax.swing.JButton back_mod;
    private javax.swing.JButton btn_pn_mod;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel user_pn_mod;
    private javax.swing.JTextField usr_pn_mod;
    // End of variables declaration//GEN-END:variables
}
