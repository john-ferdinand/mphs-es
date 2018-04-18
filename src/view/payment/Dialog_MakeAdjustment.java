package view.payment;

import utility.initializer.Initializer;

public class Dialog_MakeAdjustment extends javax.swing.JDialog implements Initializer{

    public Dialog_MakeAdjustment(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    @Override
    public void initGridBagConstraints() {
    }

    @Override
    public void initJCompModelLoaders() {
    }

    @Override
    public void initRenderers() {
    }

    @Override
    public void initModels() {
    }

    @Override
    public void initViewComponents() {
    }

    @Override
    public void initControllers() {
    }

    @Override
    public void initDaoImpl() {
    }

    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        panel_toppanel = new javax.swing.JPanel();
        panel_subjectdetails = new javax.swing.JPanel();
        lbl_subcode = new javax.swing.JLabel();
        tf_subcode = new javax.swing.JTextField();
        lbl_subname = new javax.swing.JLabel();
        lbl_subcode3 = new javax.swing.JLabel();
        tf_subcode1 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jCheckBox1 = new javax.swing.JCheckBox();
        combo_status2 = new javax.swing.JComboBox<>();
        panel_footer = new javax.swing.JPanel();
        btn_cancel = new javax.swing.JButton();
        btn_clear = new javax.swing.JButton();
        btn_save = new javax.swing.JButton();
        btn_savennew = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Make Adjustment");
        getContentPane().setLayout(new java.awt.GridBagLayout());

        panel_toppanel.setMinimumSize(new java.awt.Dimension(555, 270));
        panel_toppanel.setPreferredSize(new java.awt.Dimension(555, 270));
        panel_toppanel.setLayout(new java.awt.GridBagLayout());

        panel_subjectdetails.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Adjustment Details", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        panel_subjectdetails.setMinimumSize(new java.awt.Dimension(550, 150));
        panel_subjectdetails.setPreferredSize(new java.awt.Dimension(550, 150));
        panel_subjectdetails.setLayout(new java.awt.GridBagLayout());

        lbl_subcode.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbl_subcode.setText("Date Today :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 0);
        panel_subjectdetails.add(lbl_subcode, gridBagConstraints);

        tf_subcode.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tf_subcode.setMinimumSize(new java.awt.Dimension(150, 25));
        tf_subcode.setPreferredSize(new java.awt.Dimension(150, 25));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 0);
        panel_subjectdetails.add(tf_subcode, gridBagConstraints);

        lbl_subname.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbl_subname.setText("Descrption :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 5, 0);
        panel_subjectdetails.add(lbl_subname, gridBagConstraints);

        lbl_subcode3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbl_subcode3.setText("Amount :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 0);
        panel_subjectdetails.add(lbl_subcode3, gridBagConstraints);

        tf_subcode1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tf_subcode1.setMinimumSize(new java.awt.Dimension(80, 25));
        tf_subcode1.setPreferredSize(new java.awt.Dimension(80, 25));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 8, 0);
        panel_subjectdetails.add(tf_subcode1, gridBagConstraints);

        jScrollPane1.setMinimumSize(new java.awt.Dimension(150, 100));

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 3;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        panel_subjectdetails.add(jScrollPane1, gridBagConstraints);

        jCheckBox1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jCheckBox1.setText("Approved By :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        panel_subjectdetails.add(jCheckBox1, gridBagConstraints);

        combo_status2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        combo_status2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select ", "Antonio, John" }));
        combo_status2.setMinimumSize(new java.awt.Dimension(150, 25));
        combo_status2.setPreferredSize(new java.awt.Dimension(150, 25));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        panel_subjectdetails.add(combo_status2, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 40, 3);
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
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.weightx = 0.5;
        panel_footer.add(btn_cancel, gridBagConstraints);

        btn_clear.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btn_clear.setText("Clear");
        btn_clear.setMaximumSize(new java.awt.Dimension(60, 40));
        btn_clear.setMinimumSize(new java.awt.Dimension(60, 40));
        btn_clear.setPreferredSize(new java.awt.Dimension(60, 40));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        panel_footer.add(btn_clear, gridBagConstraints);

        btn_save.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btn_save.setText("Save");
        btn_save.setMaximumSize(new java.awt.Dimension(59, 40));
        btn_save.setMinimumSize(new java.awt.Dimension(59, 40));
        btn_save.setPreferredSize(new java.awt.Dimension(59, 40));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        panel_footer.add(btn_save, gridBagConstraints);

        btn_savennew.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btn_savennew.setText("Save & New");
        btn_savennew.setMaximumSize(new java.awt.Dimension(102, 40));
        btn_savennew.setMinimumSize(new java.awt.Dimension(102, 40));
        btn_savennew.setPreferredSize(new java.awt.Dimension(102, 40));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 30);
        panel_footer.add(btn_savennew, gridBagConstraints);

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
    private javax.swing.JButton btn_clear;
    private javax.swing.JButton btn_save;
    private javax.swing.JButton btn_savennew;
    private javax.swing.JComboBox<String> combo_status2;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JLabel lbl_subcode;
    private javax.swing.JLabel lbl_subcode3;
    private javax.swing.JLabel lbl_subname;
    private javax.swing.JPanel panel_footer;
    private javax.swing.JPanel panel_subjectdetails;
    private javax.swing.JPanel panel_toppanel;
    private javax.swing.JTextField tf_subcode;
    private javax.swing.JTextField tf_subcode1;
    // End of variables declaration//GEN-END:variables
}
