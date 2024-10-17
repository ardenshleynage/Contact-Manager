/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package List_Block;

import java.awt.Color;
import App.App;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import Mod.Mod_view;

/**
 *
 * @author Ardenshley Nage
 */
public class List_Block_view extends javax.swing.JFrame {

    private static List_Block_ctrl listBlockCtrl;
    private static final String HOST = "jdbc:mysql://localhost:3306/contact_manager";
    private static final String USER = "root";
    private static final String PASSWORD = "";
    private List_Block_mod del;

    /**
     * Creates new form List_Block_view
     */
    public List_Block_view() {
        initComponents();
        listBlockCtrl = new List_Block_ctrl(HOST, USER, PASSWORD);
        populateTable4();
        addTableMouseListener();
    }

    private void populateTable4() {
        // Récupérer les contacts via le contrôleur
        List<List_Block_mod> contacts = listBlockCtrl.getAllBlockContacts();

        // Obtenir le modèle de la table pour pouvoir y ajouter des lignes
        DefaultTableModel model = (DefaultTableModel) jTable5.getModel();

        // Effacer les lignes existantes dans le tableau
        model.setRowCount(0);
        int rowNumber = 1;
        // Ajouter chaque contact dans le tableau
        for (List_Block_mod contact : contacts) {
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
        jTable5.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                int selectedRow = jTable5.getSelectedRow();
                if (selectedRow >= 0 && SwingUtilities.isLeftMouseButton(e)) {
                    List<List_Block_mod> contacts = listBlockCtrl.getAllBlockContacts();
                    List_Block_mod block = contacts.get(selectedRow);
                    del = contacts.get(selectedRow);

                    if (block != null) {
                        String numero = jTable5.getValueAt(selectedRow, 0).toString();
                        String nom = jTable5.getValueAt(selectedRow, 1).toString();
                        String prenom = jTable5.getValueAt(selectedRow, 2).toString();
                        String telephone = jTable5.getValueAt(selectedRow, 3).toString();
                        String email = jTable5.getValueAt(selectedRow, 4).toString();
                        String dateAjout = jTable5.getValueAt(selectedRow, 5).toString();
                        String id = jTable5.getValueAt(selectedRow, 6).toString();

                        String message = String.format(
                                "N°: %s\nNom: %s\nPrénom: %s\nNuméro de Téléphone: %s\nE-mail: %s\nDate d'ajout: %s",
                                numero, nom, prenom, telephone, email, dateAjout
                        );

                        int option = JOptionPane.showOptionDialog(
                                List_Block_view.this,
                                message,
                                "Détails du contact",
                                JOptionPane.DEFAULT_OPTION,
                                JOptionPane.INFORMATION_MESSAGE,
                                null,
                                new Object[]{"Modifier", "Autoriser", "Supprimer", "Annuler"},
                                "Annuler"
                        );

                        switch (option) {
                            case 0: // "Modifier"
                                Mod_view modView = new Mod_view(nom, prenom, telephone, email,id);
                                modView.setVisible(true);
                                break;
                            case 1: // "Bloquer"
                                listBlockCtrl.AccessContacts(block);
                                populateTable4();
                                JOptionPane.showMessageDialog(List_Block_view.this, "Contact autoriser avec succès", "Autoriser", JOptionPane.INFORMATION_MESSAGE);
                                break;
                            case 2: // "Supprimer"
                                listBlockCtrl.SureDeleteContacts(del);
                                DeleteContactsQuestion();
                                break;
                            case 3: // "Annuler"
                                break;
                        }
                    } else {
                        JOptionPane.showMessageDialog(List_Block_view.this, "Erreur : le contact sélectionné n'a pas pu être trouvé.", "Erreur", JOptionPane.ERROR_MESSAGE);
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
        boolean success = listBlockCtrl.SureDeleteContacts(del);

        if (success) {
            JOptionPane.showMessageDialog(this, "Le contacts a été supprimés.", "Information", JOptionPane.INFORMATION_MESSAGE);
            App.main(null);
        } else {
            JOptionPane.showMessageDialog(this, "Erreur lors de la suppresion du contact.", "Erreur", JOptionPane.ERROR_MESSAGE);
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
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        block_all_list = new javax.swing.JLabel();
        back_home = new javax.swing.JButton();
        ExitApp = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable5 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(51, 0, 153));

        block_all_list.setFont(new java.awt.Font("Arial Black", 1, 18)); // NOI18N
        block_all_list.setForeground(new java.awt.Color(255, 255, 255));
        block_all_list.setText("Liste des contacts (bloquer)");
        block_all_list.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 3, 0, new java.awt.Color(255, 255, 255)));

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

        jTable5.setModel(
                new javax.swing.table.DefaultTableModel(
                        new Object[][]{},
                        new String[]{
                            "N°", "Nom", "Prénom", "Numéro de Téléphone", "E-mail", "Date d'ajout", "Id"
                        }
                ) {
            Class[] types = new Class[]{
                java.lang.Integer.class,
                java.lang.String.class,
                java.lang.String.class,
                java.lang.Integer.class,
                java.lang.String.class,
                java.lang.String.class,
                java.lang.Integer.class
            };

            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return false;
            }
        });
        jScrollPane1.setViewportView(jTable5);
        jTable5.getColumnModel().getColumn(6).setMinWidth(0);
        jTable5.getColumnModel().getColumn(6).setMaxWidth(0);
        jTable5.getColumnModel().getColumn(6).setWidth(0);
        jTable5.getColumnModel().getColumn(6).setPreferredWidth(0);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(block_all_list)
                                .addGap(140, 140, 140))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 560, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addContainerGap(30, Short.MAX_VALUE))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(back_home, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(ExitApp)
                                                .addGap(26, 26, 26))))
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(22, 22, 22)
                                .addComponent(block_all_list)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(back_home, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(ExitApp))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(114, Short.MAX_VALUE))
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
    }// </editor-fold>                        

    private void ExitAppActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        quitApplication5();
    }

    private void back_homeActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        App.main(null);
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
            java.util.logging.Logger.getLogger(List_Block_view.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(List_Block_view.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(List_Block_view.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(List_Block_view.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new List_Block_view().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify                     
    private javax.swing.JButton ExitApp;
    private javax.swing.JButton back_home;
    private javax.swing.JLabel block_all_list;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable5;
    // End of variables declaration                   
}
