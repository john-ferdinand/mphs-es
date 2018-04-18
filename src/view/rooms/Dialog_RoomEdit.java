/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moph_ui;

/**
 *
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class Dialog_RoomEdit extends javax.swing.JDialog {

    /**
     * Creates new form dialog_roomedit
     */
    public Dialog_RoomEdit(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        panel_toppanel = new javax.swing.JPanel();
        panel_roomdetails = new javax.swing.JPanel();
        lbl_roomname = new javax.swing.JLabel();
        tf_roomname = new javax.swing.JTextField();
        lbl_roombldg = new javax.swing.JLabel();
        tf_roombldg = new javax.swing.JTextField();
        lbl_roomstatus = new javax.swing.JLabel();
        combo_status = new javax.swing.JComboBox<>();
        lbl_capacity = new javax.swing.JLabel();
        tf_capacity = new javax.swing.JTextField();
        panel_roomnotes = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        panel_footer = new javax.swing.JPanel();
        btn_cancel = new javax.swing.JButton();
        btn_save = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Edit Room");
        setMinimumSize(new java.awt.Dimension(555, 380));
        setModal(true);
        setResizable(false);
        getContentPane().setLayout(new java.awt.GridBagLayout());

        panel_toppanel.setMinimumSize(new java.awt.Dimension(555, 380));
        panel_toppanel.setPreferredSize(new java.awt.Dimension(555, 380));
        panel_toppanel.setLayout(new java.awt.GridBagLayout());

        panel_roomdetails.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Room Details", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        panel_roomdetails.setMinimumSize(new java.awt.Dimension(550, 100));
        panel_roomdetails.setPreferredSize(new java.awt.Dimension(550, 100));
        panel_roomdetails.setLayout(new java.awt.GridBagLayout());

        lbl_roomname.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbl_roomname.setText("Room Name/Number :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 10, 0);
        panel_roomdetails.add(lbl_roomname, gridBagConstraints);

        tf_roomname.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tf_roomname.setMinimumSize(new java.awt.Dimension(80, 25));
        tf_roomname.setPreferredSize(new java.awt.Dimension(80, 25));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 10, 0);
        panel_roomdetails.add(tf_roomname, gridBagConstraints);

        lbl_roombldg.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbl_roombldg.setText("Bldg Name/Number :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 25, 10, 0);
        panel_roomdetails.add(lbl_roombldg, gridBagConstraints);

        tf_roombldg.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tf_roombldg.setMinimumSize(new java.awt.Dimension(80, 25));
        tf_roombldg.setPreferredSize(new java.awt.Dimension(80, 25));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 10, 30);
        panel_roomdetails.add(tf_roombldg, gridBagConstraints);

        lbl_roomstatus.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbl_roomstatus.setText("Active :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 0);
        panel_roomdetails.add(lbl_roomstatus, gridBagConstraints);

        combo_status.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        combo_status.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Yes", "No" }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 0);
        panel_roomdetails.add(combo_status, gridBagConstraints);

        lbl_capacity.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbl_capacity.setText("Capacity :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 0);
        panel_roomdetails.add(lbl_capacity, gridBagConstraints);

        tf_capacity.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tf_capacity.setMinimumSize(new java.awt.Dimension(80, 25));
        tf_capacity.setPreferredSize(new java.awt.Dimension(80, 25));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 10, 5);
        panel_roomdetails.add(tf_capacity, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 0, 3);
        panel_toppanel.add(panel_roomdetails, gridBagConstraints);

        panel_roomnotes.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Notes", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        panel_roomnotes.setMinimumSize(new java.awt.Dimension(550, 170));
        panel_roomnotes.setPreferredSize(new java.awt.Dimension(550, 170));
        panel_roomnotes.setLayout(new java.awt.GridBagLayout());

        jScrollPane1.setMinimumSize(new java.awt.Dimension(530, 140));
        jScrollPane1.setPreferredSize(new java.awt.Dimension(530, 140));

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jTextArea1.setMinimumSize(new java.awt.Dimension(530, 140));
        jTextArea1.setPreferredSize(new java.awt.Dimension(530, 140));
        jScrollPane1.setViewportView(jTextArea1);

        panel_roomnotes.add(jScrollPane1, new java.awt.GridBagConstraints());

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 3);
        panel_toppanel.add(panel_roomnotes, gridBagConstraints);

        panel_footer.setMinimumSize(new java.awt.Dimension(550, 50));
        panel_footer.setPreferredSize(new java.awt.Dimension(550, 50));
        panel_footer.setLayout(new java.awt.GridBagLayout());

        btn_cancel.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btn_cancel.setText("Cancel");
        btn_cancel.setMaximumSize(new java.awt.Dimension(80, 40));
        btn_cancel.setMinimumSize(new java.awt.Dimension(80, 40));
        btn_cancel.setPreferredSize(new java.awt.Dimension(80, 40));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        panel_footer.add(btn_cancel, gridBagConstraints);

        btn_save.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btn_save.setText("Save");
        btn_save.setMaximumSize(new java.awt.Dimension(80, 40));
        btn_save.setMinimumSize(new java.awt.Dimension(80, 40));
        btn_save.setPreferredSize(new java.awt.Dimension(80, 40));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        panel_footer.add(btn_save, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 30, 0);
        panel_toppanel.add(panel_footer, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        getContentPane().add(panel_toppanel, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(Dialog_RoomEdit.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Dialog_RoomEdit.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Dialog_RoomEdit.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Dialog_RoomEdit.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Dialog_RoomEdit dialog = new Dialog_RoomEdit(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_cancel;
    private javax.swing.JButton btn_save;
    private javax.swing.JComboBox<String> combo_status;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JLabel lbl_capacity;
    private javax.swing.JLabel lbl_roombldg;
    private javax.swing.JLabel lbl_roomname;
    private javax.swing.JLabel lbl_roomstatus;
    private javax.swing.JPanel panel_footer;
    private javax.swing.JPanel panel_roomdetails;
    private javax.swing.JPanel panel_roomnotes;
    private javax.swing.JPanel panel_toppanel;
    private javax.swing.JTextField tf_capacity;
    private javax.swing.JTextField tf_roombldg;
    private javax.swing.JTextField tf_roomname;
    // End of variables declaration//GEN-END:variables
}
