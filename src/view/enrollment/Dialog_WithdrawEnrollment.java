
public class Dialog_WithdrawEnrollment extends javax.swing.JDialog {

    public Dialog_WithdrawEnrollment(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        panel_toppanel = new javax.swing.JPanel();
        panel_subjectdetails = new javax.swing.JPanel();
        lbl_subcode = new javax.swing.JLabel();
        tf_subcode = new javax.swing.JTextField();
        lbl_subcode1 = new javax.swing.JLabel();
        tf_subcode1 = new javax.swing.JTextField();
        lbl_subcode2 = new javax.swing.JLabel();
        combo_gradelevel1 = new javax.swing.JComboBox<>();
        lbl_subcode3 = new javax.swing.JLabel();
        tf_subcode2 = new javax.swing.JTextField();
        lbl_subcode4 = new javax.swing.JLabel();
        tf_subcode3 = new javax.swing.JTextField();
        lbl_subcode5 = new javax.swing.JLabel();
        tf_subcode4 = new javax.swing.JTextField();
        lbl_subcode6 = new javax.swing.JLabel();
        tf_subcode5 = new javax.swing.JTextField();
        lbl_subcode7 = new javax.swing.JLabel();
        tf_subcode6 = new javax.swing.JTextField();
        lbl_subcode8 = new javax.swing.JLabel();
        tf_subcode7 = new javax.swing.JTextField();
        panel_footer = new javax.swing.JPanel();
        btn_cancel = new javax.swing.JButton();
        btn_save = new javax.swing.JButton();
        lbl_subcode9 = new javax.swing.JLabel();
        tf_subcode8 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Withdraw Enrollment");
        setModal(true);
        setResizable(false);
        getContentPane().setLayout(new java.awt.GridBagLayout());

        panel_toppanel.setMinimumSize(new java.awt.Dimension(555, 270));
        panel_toppanel.setPreferredSize(new java.awt.Dimension(555, 270));
        panel_toppanel.setLayout(new java.awt.GridBagLayout());

        panel_subjectdetails.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Student Details", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        panel_subjectdetails.setMinimumSize(new java.awt.Dimension(550, 150));
        panel_subjectdetails.setPreferredSize(new java.awt.Dimension(550, 150));
        panel_subjectdetails.setLayout(new java.awt.GridBagLayout());

        lbl_subcode.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbl_subcode.setText("Student ID :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 0);
        panel_subjectdetails.add(lbl_subcode, gridBagConstraints);

        tf_subcode.setEditable(false);
        tf_subcode.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tf_subcode.setMinimumSize(new java.awt.Dimension(100, 25));
        tf_subcode.setPreferredSize(new java.awt.Dimension(100, 25));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 0);
        panel_subjectdetails.add(tf_subcode, gridBagConstraints);

        lbl_subcode1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbl_subcode1.setText("Grade Level :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 5, 0);
        panel_subjectdetails.add(lbl_subcode1, gridBagConstraints);

        tf_subcode1.setEditable(false);
        tf_subcode1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tf_subcode1.setMinimumSize(new java.awt.Dimension(50, 25));
        tf_subcode1.setPreferredSize(new java.awt.Dimension(50, 25));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 0);
        panel_subjectdetails.add(tf_subcode1, gridBagConstraints);

        lbl_subcode2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbl_subcode2.setText("Status :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 0);
        panel_subjectdetails.add(lbl_subcode2, gridBagConstraints);

        combo_gradelevel1.setEditable(true);
        combo_gradelevel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        combo_gradelevel1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Active", "Inactive", "Withdrawn" }));
        combo_gradelevel1.setMinimumSize(new java.awt.Dimension(100, 25));
        combo_gradelevel1.setPreferredSize(new java.awt.Dimension(100, 25));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        panel_subjectdetails.add(combo_gradelevel1, gridBagConstraints);

        lbl_subcode3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbl_subcode3.setText("Last Name :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 0);
        panel_subjectdetails.add(lbl_subcode3, gridBagConstraints);

        tf_subcode2.setEditable(false);
        tf_subcode2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tf_subcode2.setMinimumSize(new java.awt.Dimension(100, 25));
        tf_subcode2.setPreferredSize(new java.awt.Dimension(100, 25));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 0);
        panel_subjectdetails.add(tf_subcode2, gridBagConstraints);

        lbl_subcode4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbl_subcode4.setText("First Name :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 0);
        panel_subjectdetails.add(lbl_subcode4, gridBagConstraints);

        tf_subcode3.setEditable(false);
        tf_subcode3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tf_subcode3.setMinimumSize(new java.awt.Dimension(100, 25));
        tf_subcode3.setPreferredSize(new java.awt.Dimension(100, 25));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 0);
        panel_subjectdetails.add(tf_subcode3, gridBagConstraints);

        lbl_subcode5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbl_subcode5.setText("M.I.");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 5, 0);
        panel_subjectdetails.add(lbl_subcode5, gridBagConstraints);

        tf_subcode4.setEditable(false);
        tf_subcode4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tf_subcode4.setMinimumSize(new java.awt.Dimension(50, 25));
        tf_subcode4.setPreferredSize(new java.awt.Dimension(50, 25));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 0);
        panel_subjectdetails.add(tf_subcode4, gridBagConstraints);

        lbl_subcode6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbl_subcode6.setText("Type :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 0);
        panel_subjectdetails.add(lbl_subcode6, gridBagConstraints);

        tf_subcode5.setEditable(false);
        tf_subcode5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tf_subcode5.setMinimumSize(new java.awt.Dimension(100, 25));
        tf_subcode5.setPreferredSize(new java.awt.Dimension(100, 25));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 0);
        panel_subjectdetails.add(tf_subcode5, gridBagConstraints);

        lbl_subcode7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbl_subcode7.setText("Adviser :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 0);
        panel_subjectdetails.add(lbl_subcode7, gridBagConstraints);

        tf_subcode6.setEditable(false);
        tf_subcode6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tf_subcode6.setMinimumSize(new java.awt.Dimension(100, 25));
        tf_subcode6.setPreferredSize(new java.awt.Dimension(100, 25));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 0);
        panel_subjectdetails.add(tf_subcode6, gridBagConstraints);

        lbl_subcode8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbl_subcode8.setText("Section :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 0);
        panel_subjectdetails.add(lbl_subcode8, gridBagConstraints);

        tf_subcode7.setEditable(false);
        tf_subcode7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tf_subcode7.setMinimumSize(new java.awt.Dimension(100, 25));
        tf_subcode7.setPreferredSize(new java.awt.Dimension(100, 25));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 0);
        panel_subjectdetails.add(tf_subcode7, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        gridBagConstraints.weighty = 0.5;
        panel_toppanel.add(panel_subjectdetails, gridBagConstraints);

        panel_footer.setMinimumSize(new java.awt.Dimension(550, 50));
        panel_footer.setPreferredSize(new java.awt.Dimension(550, 50));
        panel_footer.setLayout(new java.awt.GridBagLayout());

        btn_cancel.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btn_cancel.setText("Cancel");
        btn_cancel.setMaximumSize(new java.awt.Dimension(69, 40));
        btn_cancel.setMinimumSize(new java.awt.Dimension(69, 40));
        btn_cancel.setPreferredSize(new java.awt.Dimension(69, 40));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.weightx = 0.5;
        panel_footer.add(btn_cancel, gridBagConstraints);

        btn_save.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btn_save.setText("Save");
        btn_save.setMaximumSize(new java.awt.Dimension(59, 40));
        btn_save.setMinimumSize(new java.awt.Dimension(59, 40));
        btn_save.setPreferredSize(new java.awt.Dimension(59, 40));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 30);
        panel_footer.add(btn_save, gridBagConstraints);

        lbl_subcode9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lbl_subcode9.setText("Withdrawal Reference Number :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 5, 0);
        panel_footer.add(lbl_subcode9, gridBagConstraints);

        tf_subcode8.setEditable(false);
        tf_subcode8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        tf_subcode8.setMinimumSize(new java.awt.Dimension(100, 25));
        tf_subcode8.setPreferredSize(new java.awt.Dimension(100, 25));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 0);
        panel_footer.add(tf_subcode8, gridBagConstraints);

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

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_cancel;
    private javax.swing.JButton btn_save;
    private javax.swing.JComboBox<String> combo_gradelevel1;
    private javax.swing.JLabel lbl_subcode;
    private javax.swing.JLabel lbl_subcode1;
    private javax.swing.JLabel lbl_subcode2;
    private javax.swing.JLabel lbl_subcode3;
    private javax.swing.JLabel lbl_subcode4;
    private javax.swing.JLabel lbl_subcode5;
    private javax.swing.JLabel lbl_subcode6;
    private javax.swing.JLabel lbl_subcode7;
    private javax.swing.JLabel lbl_subcode8;
    private javax.swing.JLabel lbl_subcode9;
    private javax.swing.JPanel panel_footer;
    private javax.swing.JPanel panel_subjectdetails;
    private javax.swing.JPanel panel_toppanel;
    private javax.swing.JTextField tf_subcode;
    private javax.swing.JTextField tf_subcode1;
    private javax.swing.JTextField tf_subcode2;
    private javax.swing.JTextField tf_subcode3;
    private javax.swing.JTextField tf_subcode4;
    private javax.swing.JTextField tf_subcode5;
    private javax.swing.JTextField tf_subcode6;
    private javax.swing.JTextField tf_subcode7;
    private javax.swing.JTextField tf_subcode8;
    // End of variables declaration//GEN-END:variables
}
