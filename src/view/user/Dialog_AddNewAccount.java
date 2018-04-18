package view.user;


public class Dialog_AddNewAccount extends javax.swing.JDialog {


    public Dialog_AddNewAccount(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        panel_toppanel = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        lbl_name2 = new javax.swing.JLabel();
        combo_active1 = new javax.swing.JComboBox<>();
        lbl_name3 = new javax.swing.JLabel();
        combo_active = new javax.swing.JComboBox<>();
        lbl_name4 = new javax.swing.JLabel();
        combo_active2 = new javax.swing.JComboBox<>();
        jCheckBox1 = new javax.swing.JCheckBox();
        jPanel2 = new javax.swing.JPanel();
        lbl_name5 = new javax.swing.JLabel();
        lbl_name7 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        lbl_name8 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        lbl_name9 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        lbl_name10 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        lbl_name13 = new javax.swing.JLabel();
        lbl_name14 = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JTextField();
        jTextField7 = new javax.swing.JTextField();
        lbl_name15 = new javax.swing.JLabel();
        jTextField8 = new javax.swing.JTextField();
        panel_footer = new javax.swing.JPanel();
        btn_cancel = new javax.swing.JButton();
        btn_save = new javax.swing.JButton();
        btn_save1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Add New Account");
        setMinimumSize(new java.awt.Dimension(400, 530));
        setModal(true);
        setPreferredSize(new java.awt.Dimension(400, 530));
        setResizable(false);
        getContentPane().setLayout(new java.awt.GridBagLayout());

        panel_toppanel.setMinimumSize(new java.awt.Dimension(400, 530));
        panel_toppanel.setPreferredSize(new java.awt.Dimension(400, 530));
        panel_toppanel.setLayout(new java.awt.GridBagLayout());

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Control", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        jPanel1.setMinimumSize(new java.awt.Dimension(390, 90));
        jPanel1.setPreferredSize(new java.awt.Dimension(390, 90));
        jPanel1.setLayout(new java.awt.GridBagLayout());

        lbl_name2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbl_name2.setText("Role :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 0);
        jPanel1.add(lbl_name2, gridBagConstraints);

        combo_active1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        combo_active1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select", "Administrator", "Faculty", "Cashier", " " }));
        combo_active1.setMinimumSize(new java.awt.Dimension(150, 25));
        combo_active1.setPreferredSize(new java.awt.Dimension(150, 25));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 0);
        jPanel1.add(combo_active1, gridBagConstraints);

        lbl_name3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbl_name3.setText("Active :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 30, 5, 0);
        jPanel1.add(lbl_name3, gridBagConstraints);

        combo_active.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        combo_active.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Yes", "No" }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 0);
        jPanel1.add(combo_active, gridBagConstraints);

        lbl_name4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbl_name4.setText("Enlisted :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 0);
        jPanel1.add(lbl_name4, gridBagConstraints);

        combo_active2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        combo_active2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select", " " }));
        combo_active2.setMinimumSize(new java.awt.Dimension(150, 25));
        combo_active2.setPreferredSize(new java.awt.Dimension(150, 25));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 0);
        jPanel1.add(combo_active2, gridBagConstraints);

        jCheckBox1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jCheckBox1.setText("Create New");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 5, 0);
        jPanel1.add(jCheckBox1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        panel_toppanel.add(jPanel1, gridBagConstraints);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Personal Information", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        jPanel2.setMinimumSize(new java.awt.Dimension(390, 190));
        jPanel2.setPreferredSize(new java.awt.Dimension(390, 190));
        jPanel2.setLayout(new java.awt.GridBagLayout());

        lbl_name5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbl_name5.setText("Last Name :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 0);
        jPanel2.add(lbl_name5, gridBagConstraints);

        lbl_name7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbl_name7.setText("First Name :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 0);
        jPanel2.add(lbl_name7, gridBagConstraints);

        jTextField1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jTextField1.setMinimumSize(new java.awt.Dimension(150, 25));
        jTextField1.setPreferredSize(new java.awt.Dimension(150, 25));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 0);
        jPanel2.add(jTextField1, gridBagConstraints);

        jTextField2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jTextField2.setMinimumSize(new java.awt.Dimension(150, 25));
        jTextField2.setPreferredSize(new java.awt.Dimension(150, 25));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 0);
        jPanel2.add(jTextField2, gridBagConstraints);

        lbl_name8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbl_name8.setText("Middle Name :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 0);
        jPanel2.add(lbl_name8, gridBagConstraints);

        jTextField3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jTextField3.setMinimumSize(new java.awt.Dimension(150, 25));
        jTextField3.setPreferredSize(new java.awt.Dimension(150, 25));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 0);
        jPanel2.add(jTextField3, gridBagConstraints);

        lbl_name9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbl_name9.setText("Email Address :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 0);
        jPanel2.add(lbl_name9, gridBagConstraints);

        jTextField4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jTextField4.setMinimumSize(new java.awt.Dimension(150, 25));
        jTextField4.setPreferredSize(new java.awt.Dimension(150, 25));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 0);
        jPanel2.add(jTextField4, gridBagConstraints);

        lbl_name10.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbl_name10.setText("Mobile Number :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 5, 0);
        jPanel2.add(lbl_name10, gridBagConstraints);

        jTextField5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jTextField5.setMinimumSize(new java.awt.Dimension(150, 25));
        jTextField5.setPreferredSize(new java.awt.Dimension(150, 25));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 0);
        jPanel2.add(jTextField5, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        panel_toppanel.add(jPanel2, gridBagConstraints);

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Account Details", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        jPanel4.setMinimumSize(new java.awt.Dimension(390, 130));
        jPanel4.setPreferredSize(new java.awt.Dimension(390, 130));
        jPanel4.setRequestFocusEnabled(false);
        jPanel4.setLayout(new java.awt.GridBagLayout());

        lbl_name13.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbl_name13.setText("Username :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 0);
        jPanel4.add(lbl_name13, gridBagConstraints);

        lbl_name14.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbl_name14.setText("Password :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 0);
        jPanel4.add(lbl_name14, gridBagConstraints);

        jTextField6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jTextField6.setMinimumSize(new java.awt.Dimension(150, 25));
        jTextField6.setPreferredSize(new java.awt.Dimension(150, 25));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 0);
        jPanel4.add(jTextField6, gridBagConstraints);

        jTextField7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jTextField7.setMinimumSize(new java.awt.Dimension(150, 25));
        jTextField7.setPreferredSize(new java.awt.Dimension(150, 25));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 0);
        jPanel4.add(jTextField7, gridBagConstraints);

        lbl_name15.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbl_name15.setText("Re-type Password :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 5, 0);
        jPanel4.add(lbl_name15, gridBagConstraints);

        jTextField8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jTextField8.setMinimumSize(new java.awt.Dimension(150, 25));
        jTextField8.setPreferredSize(new java.awt.Dimension(150, 25));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 0);
        jPanel4.add(jTextField8, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        panel_toppanel.add(jPanel4, gridBagConstraints);

        panel_footer.setMinimumSize(new java.awt.Dimension(300, 50));
        panel_footer.setPreferredSize(new java.awt.Dimension(300, 50));
        panel_footer.setLayout(new java.awt.GridBagLayout());

        btn_cancel.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btn_cancel.setText("Cancel");
        btn_cancel.setMaximumSize(new java.awt.Dimension(69, 40));
        btn_cancel.setMinimumSize(new java.awt.Dimension(69, 40));
        btn_cancel.setPreferredSize(new java.awt.Dimension(69, 40));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.weightx = 0.5;
        panel_footer.add(btn_cancel, gridBagConstraints);

        btn_save.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btn_save.setText("Add");
        btn_save.setMaximumSize(new java.awt.Dimension(100, 40));
        btn_save.setMinimumSize(new java.awt.Dimension(90, 40));
        btn_save.setPreferredSize(new java.awt.Dimension(90, 40));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        panel_footer.add(btn_save, gridBagConstraints);

        btn_save1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btn_save1.setText("Add & New");
        btn_save1.setMaximumSize(new java.awt.Dimension(130, 40));
        btn_save1.setMinimumSize(new java.awt.Dimension(100, 40));
        btn_save1.setPreferredSize(new java.awt.Dimension(130, 40));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 20);
        panel_footer.add(btn_save1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(20, 0, 0, 0);
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
    private javax.swing.JButton btn_save1;
    private javax.swing.JComboBox<String> combo_active;
    private javax.swing.JComboBox<String> combo_active1;
    private javax.swing.JComboBox<String> combo_active2;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JLabel lbl_name10;
    private javax.swing.JLabel lbl_name13;
    private javax.swing.JLabel lbl_name14;
    private javax.swing.JLabel lbl_name15;
    private javax.swing.JLabel lbl_name2;
    private javax.swing.JLabel lbl_name3;
    private javax.swing.JLabel lbl_name4;
    private javax.swing.JLabel lbl_name5;
    private javax.swing.JLabel lbl_name7;
    private javax.swing.JLabel lbl_name8;
    private javax.swing.JLabel lbl_name9;
    private javax.swing.JPanel panel_footer;
    private javax.swing.JPanel panel_toppanel;
    // End of variables declaration//GEN-END:variables
}
