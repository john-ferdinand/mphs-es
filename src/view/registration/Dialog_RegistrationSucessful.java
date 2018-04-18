package view.registration;


public class Dialog_RegistrationSucessful extends javax.swing.JDialog {

    /**
     * Creates new form dialog_registrationsucessful
     */
    public Dialog_RegistrationSucessful(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        panel_toppanel = new javax.swing.JPanel();
        lbl_alert = new javax.swing.JLabel();
        lbl_yourregistration = new javax.swing.JLabel();
        lbl_numberdisplay = new javax.swing.JLabel();
        lbl_numberdisplay1 = new javax.swing.JLabel();
        panel_footer = new javax.swing.JPanel();
        btn_Cancel = new javax.swing.JButton();
        btn_okay = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Registration Status");
        setMinimumSize(new java.awt.Dimension(400, 280));
        setModal(true);
        setPreferredSize(new java.awt.Dimension(400, 280));
        setResizable(false);
        getContentPane().setLayout(new java.awt.GridBagLayout());

        panel_toppanel.setMinimumSize(new java.awt.Dimension(400, 280));
        panel_toppanel.setPreferredSize(new java.awt.Dimension(400, 280));
        panel_toppanel.setLayout(new java.awt.GridBagLayout());

        lbl_alert.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        lbl_alert.setText("Registration Successful!");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        panel_toppanel.add(lbl_alert, gridBagConstraints);

        lbl_yourregistration.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lbl_yourregistration.setText("Student registration number is :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 10, 0);
        panel_toppanel.add(lbl_yourregistration, gridBagConstraints);

        lbl_numberdisplay.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        lbl_numberdisplay.setText("10020201");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 0, 0);
        panel_toppanel.add(lbl_numberdisplay, gridBagConstraints);

        lbl_numberdisplay1.setFont(new java.awt.Font("Tahoma", 2, 14)); // NOI18N
        lbl_numberdisplay1.setForeground(new java.awt.Color(0, 51, 153));
        lbl_numberdisplay1.setText("*Student can now proceed to cashier to make payments.");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.insets = new java.awt.Insets(20, 0, 20, 0);
        panel_toppanel.add(lbl_numberdisplay1, gridBagConstraints);

        panel_footer.setMinimumSize(new java.awt.Dimension(550, 50));
        panel_footer.setPreferredSize(new java.awt.Dimension(550, 50));
        panel_footer.setLayout(new java.awt.GridBagLayout());

        btn_Cancel.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btn_Cancel.setText("Cancel");
        btn_Cancel.setMaximumSize(new java.awt.Dimension(70, 40));
        btn_Cancel.setMinimumSize(new java.awt.Dimension(70, 40));
        btn_Cancel.setPreferredSize(new java.awt.Dimension(70, 40));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(0, 100, 0, 0);
        panel_footer.add(btn_Cancel, gridBagConstraints);

        btn_okay.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btn_okay.setText("Okay");
        btn_okay.setMaximumSize(new java.awt.Dimension(102, 40));
        btn_okay.setMinimumSize(new java.awt.Dimension(102, 40));
        btn_okay.setPreferredSize(new java.awt.Dimension(102, 40));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        panel_footer.add(btn_okay, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 20, 0);
        panel_toppanel.add(panel_footer, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        getContentPane().add(panel_toppanel, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_Cancel;
    private javax.swing.JButton btn_okay;
    private javax.swing.JLabel lbl_alert;
    private javax.swing.JLabel lbl_numberdisplay;
    private javax.swing.JLabel lbl_numberdisplay1;
    private javax.swing.JLabel lbl_yourregistration;
    private javax.swing.JPanel panel_footer;
    private javax.swing.JPanel panel_toppanel;
    // End of variables declaration//GEN-END:variables
}
