/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Search;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JOptionPane;
import App.App;
import Search.Search_LastName.Search_Ln_view;
import Search.Search_FirstName.Search_Fn_view;
import Search.Search_PhoneNumber.Search_Pn_view;
import Search.Search_Email.Search_Em_view;
import Search.Search_All.Search_All_view;

/**
 *
 * @author Ardenshley Nage
 */
public class Search_view extends javax.swing.JFrame {

    /**
     * Creates new form Search_view
     */
    public Search_view() {
        initComponents();
    }

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

    private void handleChoice(int choice) {
        switch (choice) {
            case 1 ->
                Search_Ln_view.main(null);
            case 2 ->
                Search_Fn_view.main(null);
            case 3 ->
                Search_Pn_view.main(null);
            case 4 ->
                Search_Em_view.main(null);
            case 5 ->
                Search_All_view.main(null);
            default ->
                JOptionPane.showMessageDialog(this, "Choix invalide.");
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
        type_search = new javax.swing.JLabel();
        search_by_ln = new javax.swing.JLabel();
        search_by_fn = new javax.swing.JLabel();
        search_by_tlf = new javax.swing.JLabel();
        search_by_mail = new javax.swing.JLabel();
        search_all = new javax.swing.JLabel();
        back_home = new javax.swing.JButton();
        ExitApp = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(51, 0, 153));

        type_search.setFont(new java.awt.Font("Arial Black", 1, 18)); // NOI18N
        type_search.setForeground(new java.awt.Color(255, 255, 255));
        type_search.setText("Type de recherche");
        type_search.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 3, 0, new java.awt.Color(255, 255, 255)));

        search_by_ln.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        search_by_ln.setForeground(new java.awt.Color(255, 255, 255));
        search_by_ln.setText("1- Rechercher par nom");
        search_by_ln.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        search_by_ln.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                handleChoice(1);
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                search_by_ln.setForeground(Color.BLACK);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                search_by_ln.setForeground(Color.WHITE);
            }
        });

        search_by_fn.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        search_by_fn.setForeground(new java.awt.Color(255, 255, 255));
        search_by_fn.setText("2- Rechercher par prénom");
        search_by_fn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        search_by_fn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                handleChoice(2);
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                search_by_fn.setForeground(Color.BLACK);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                search_by_fn.setForeground(Color.WHITE);
            }
        });

        search_by_tlf.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        search_by_tlf.setForeground(new java.awt.Color(255, 255, 255));
        search_by_tlf.setText("3- Rechercher par numéro de téléphone");
        search_by_tlf.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        search_by_tlf.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                handleChoice(3);
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                search_by_tlf.setForeground(Color.BLACK);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                search_by_tlf.setForeground(Color.WHITE);
            }
        });

        search_by_mail.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        search_by_mail.setForeground(new java.awt.Color(255, 255, 255));
        search_by_mail.setText("4- Rechercher par adresse e-mail");
        search_by_mail.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        search_by_mail.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                handleChoice(4);
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                search_by_mail.setForeground(Color.BLACK);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                search_by_mail.setForeground(Color.WHITE);
            }
        });

        search_all.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        search_all.setForeground(new java.awt.Color(255, 255, 255));
        search_all.setText("5- Tous rechercher");
        search_all.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        search_all.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                handleChoice(5);
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                search_all.setForeground(Color.BLACK);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                search_all.setForeground(Color.WHITE);
            }
        });

        back_home.setBackground(new java.awt.Color(0, 0, 0));
        back_home.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(type_search)
                .addGap(195, 195, 195))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(157, 157, 157)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(search_all)
                    .addComponent(search_by_mail)
                    .addComponent(search_by_tlf)
                    .addComponent(search_by_fn)
                    .addComponent(search_by_ln))
                .addContainerGap(156, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(back_home, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(ExitApp)
                .addGap(28, 28, 28))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(type_search)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(back_home, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ExitApp))
                .addGap(43, 43, 43)
                .addComponent(search_by_ln, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(search_by_fn, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(search_by_tlf, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(search_by_mail, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(search_all, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(133, Short.MAX_VALUE))
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

    private void ExitAppActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ExitAppActionPerformed
        // TODO add your handling code here:
        quitApplication();
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
            java.util.logging.Logger.getLogger(Search_view.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Search_view.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Search_view.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Search_view.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Search_view().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ExitApp;
    private javax.swing.JButton back_home;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel search_all;
    private javax.swing.JLabel search_by_fn;
    private javax.swing.JLabel search_by_ln;
    private javax.swing.JLabel search_by_mail;
    private javax.swing.JLabel search_by_tlf;
    private javax.swing.JLabel type_search;
    // End of variables declaration//GEN-END:variables
}
