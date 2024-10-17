/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Search.Search_Email;

import App.App;
import Mod.Mod_view;
import java.awt.BorderLayout;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import Search.Search_view;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.SwingUtilities;

/**
 *
 * @author Ardenshley Nage
 */
public class Search_Em_view extends javax.swing.JFrame {

    private static Search_Em_ctrl SearchEmCtrl;
    private static final String HOST = "jdbc:mysql://localhost:3306/contact_manager";
    private static final String USER = "root";
    private static final String PASSWORD = "";
    Search_Em_mod del;
    Search_Em_mod block;

    /**
     * Creates new form Search_Em_view
     */
    public Search_Em_view() {
        initComponents();
        SearchEmCtrl = new Search_Em_ctrl(HOST, USER, PASSWORD);
    }

    private void SearchEm() {
        String Email = usr_em.getText().trim();

        // Call the controller to perform the search
        List<Search_Em_mod> results = SearchEmCtrl.searchByEmail(Email);
        if (Email.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Veuillez remplir le formulaire de recherche", "Résultats de recherche", JOptionPane.INFORMATION_MESSAGE);
        } else if (results.isEmpty()) {
            // Show message if no results are found
            JOptionPane.showMessageDialog(this, "Aucun résultat pour l'addresse email '" + Email + "'", "Résultats de recherche", JOptionPane.INFORMATION_MESSAGE);
        } else {
            // Create table to display results
            String[] columnNames = {"Nom", "Prénom", "Numéro Téléphone", "Adresse Email", "Date d'Ajout","Id"};
            DefaultTableModel model = new DefaultTableModel(columnNames, 0);

            for (Search_Em_mod contact : results) {
                Object[] row = {
                    contact.getFirst_name(),
                    contact.getLast_name(),
                    contact.getPhone_number(),
                    contact.getMail_user(),
                    contact.getAdd_date(),
                    contact.getId()
                };
                model.addRow(row);
            }

            JTable resultTable = new JTable(model);
            JScrollPane scrollPane = new JScrollPane(resultTable);

            // Show results in a new window
            JFrame resultFrame = new JFrame("Résultats de recherche");  // Declare resultFrame here
            resultFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            resultFrame.setLayout(new BorderLayout());
            resultFrame.add(scrollPane, BorderLayout.CENTER);
            resultFrame.pack();
            resultFrame.setLocationRelativeTo(this);
            resultFrame.setVisible(true);
            resultTable.getColumnModel().getColumn(5).setMinWidth(0);
            resultTable.getColumnModel().getColumn(5).setMaxWidth(0);
            resultTable.getColumnModel().getColumn(5).setWidth(0);
            resultTable.getColumnModel().getColumn(5).setPreferredWidth(0);

            // Add mouse listener to handle row selection
            resultTable.addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    int selectedRow = resultTable.getSelectedRow();
                    if (selectedRow >= 0 && SwingUtilities.isLeftMouseButton(evt)) {
                        // Fetch all contacts from the database
                        List<Search_Em_mod> contacts = SearchEmCtrl.searchByEmail("");
                        if (contacts != null && !contacts.isEmpty()) {
                            // Retrieve the selected contact
                            Search_Em_mod selectedContact = contacts.get(selectedRow);

                            // Ensure del and block are assigned to the selected contact
                            del = selectedContact;
                            block = selectedContact;

                            // Extract contact details
                            String firstName = selectedContact.getLast_name();
                            String lastName = selectedContact.getFirst_name();
                            String phoneNumber = Integer.toString(selectedContact.getPhone_number());
                            String email = selectedContact.getMail_user();
                            String addDate = selectedContact.getAdd_date();
                            String id = Integer.toString(selectedContact.getId());

                            // Format the message for the options dialog
                            String message = String.format(
                                    "Nom: %s\nPrénom: %s\nNuméro Téléphone: %s\nAdresse Email: %s\nDate d'Ajout: %s",
                                    lastName, firstName, phoneNumber, email, addDate
                            );

                            // Display options dialog
                            int option = JOptionPane.showOptionDialog(
                                    resultFrame,
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
                                    Mod_view modView = new Mod_view(lastName, firstName, phoneNumber, email, id);
                                    modView.setVisible(true);
                                    break;
                                case 1: // "Bloquer"
                                    if (block != null) {
                                        SearchEmCtrl.BlockContacts(block);
                                        JOptionPane.showMessageDialog(Search_Em_view.this, "Contact bloqué avec succès", "Bloquer", JOptionPane.INFORMATION_MESSAGE);
                                        App.main(null);
                                    } else {
                                        JOptionPane.showMessageDialog(Search_Em_view.this, "Aucun contact sélectionné pour bloquer.", "Erreur", JOptionPane.ERROR_MESSAGE);
                                    }
                                    break;
                                case 2: // "Supprimer"
                                    if (del != null) {
                                        SearchEmCtrl.SureDeleteContacts(del);
                                        DeleteContactsQuestion();
                                    } else {
                                        JOptionPane.showMessageDialog(Search_Em_view.this, "Aucun contact sélectionné pour supprimer.", "Erreur", JOptionPane.ERROR_MESSAGE);
                                    }
                                    break;
                                case 3: // "Annuler"
                                    // Do nothing, close the dialog
                                    break;
                                default:
                                    break;
                            }
                        } else {
                            JOptionPane.showMessageDialog(resultFrame, "Aucun contact trouvé.", "Erreur", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
            });
        }
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
        boolean success = SearchEmCtrl.SureDeleteContacts(del);

        if (success) {
            JOptionPane.showMessageDialog(this, "Le contacts a été supprimés.", "Information", JOptionPane.INFORMATION_MESSAGE);
            App.main(null);
        } else {
            JOptionPane.showMessageDialog(this, "Erreur lors de la suppresion du contact.", "Erreur", JOptionPane.ERROR_MESSAGE);
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
        em = new javax.swing.JLabel();
        back_home = new javax.swing.JButton();
        ExitApp = new javax.swing.JButton();
        search_em = new javax.swing.JLabel();
        usr_em = new javax.swing.JTextField();
        btn_search_em = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(51, 0, 153));

        em.setFont(new java.awt.Font("Arial Black", 1, 18)); // NOI18N
        em.setForeground(new java.awt.Color(255, 255, 255));
        em.setText("Rechercher par E-mail");
        em.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 3, 0, new java.awt.Color(255, 255, 255)));

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

        search_em.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        search_em.setForeground(new java.awt.Color(255, 255, 255));
        search_em.setText("Entrer l'E-mail :");

        btn_search_em.setBackground(new java.awt.Color(0, 0, 0));
        btn_search_em.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        btn_search_em.setForeground(new java.awt.Color(255, 255, 255));
        btn_search_em.setText("Rechercher");
        btn_search_em.setMaximumSize(new java.awt.Dimension(79, 33));
        btn_search_em.setMinimumSize(new java.awt.Dimension(79, 33));
        btn_search_em.setPreferredSize(new java.awt.Dimension(79, 33));
        btn_search_em.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btn_search_em.setBackground(Color.WHITE);
                btn_search_em.setForeground(Color.BLACK);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btn_search_em.setBackground(Color.BLACK);
                btn_search_em.setForeground(Color.WHITE);
            }
        });
        btn_search_em.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_search_emActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(em)
                .addGap(186, 186, 186))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(back_home, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(401, 401, 401)
                        .addComponent(ExitApp, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(search_em, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(usr_em, javax.swing.GroupLayout.PREFERRED_SIZE, 327, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_search_em, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(14, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(em)
                .addGap(34, 34, 34)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(back_home, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ExitApp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(81, 81, 81)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(search_em)
                    .addComponent(usr_em, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_search_em, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(227, Short.MAX_VALUE))
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

    private void back_homeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_back_homeActionPerformed
        // TODO add your handling code here:
        Search_view.main(null);
    }//GEN-LAST:event_back_homeActionPerformed

    private void ExitAppActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ExitAppActionPerformed
        // TODO add your handling code here:
        quitApplication2();
    }//GEN-LAST:event_ExitAppActionPerformed

    private void btn_search_emActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_search_emActionPerformed
        // TODO add your handling code here:
        SearchEm();
    }//GEN-LAST:event_btn_search_emActionPerformed

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
            java.util.logging.Logger.getLogger(Search_Em_view.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Search_Em_view.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Search_Em_view.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Search_Em_view.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Search_Em_view().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ExitApp;
    private javax.swing.JButton back_home;
    private javax.swing.JButton btn_search_em;
    private javax.swing.JLabel em;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel search_em;
    private javax.swing.JTextField usr_em;
    // End of variables declaration//GEN-END:variables
}
