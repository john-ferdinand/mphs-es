package view.user;


public class Panel_Accounts extends javax.swing.JPanel {

    public Panel_Accounts() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        panel_toppanel = new javax.swing.JPanel();
        panel_control = new javax.swing.JPanel();
        btn_createnew = new javax.swing.JButton();
        btn_Edit = new javax.swing.JButton();
        btn_View = new javax.swing.JButton();
        btn_Print = new javax.swing.JButton();
        Searchbox = new javax.swing.JTextField();
        btn_Search = new javax.swing.JButton();
        lbl_show = new javax.swing.JLabel();
        combo_filter = new javax.swing.JComboBox<>();
        panel_masterrecord = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        panel_permission = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jPanel3 = new javax.swing.JPanel();
        panel_enrollment = new javax.swing.JPanel();
        jCheckBox1 = new javax.swing.JCheckBox();
        jCheckBox2 = new javax.swing.JCheckBox();
        jCheckBox3 = new javax.swing.JCheckBox();
        panel_gradingsystem = new javax.swing.JPanel();
        jCheckBox6 = new javax.swing.JCheckBox();
        jCheckBox7 = new javax.swing.JCheckBox();
        jCheckBox8 = new javax.swing.JCheckBox();
        jCheckBox9 = new javax.swing.JCheckBox();
        jCheckBox10 = new javax.swing.JCheckBox();
        panel_paymentandassessment = new javax.swing.JPanel();
        jCheckBox11 = new javax.swing.JCheckBox();
        jCheckBox12 = new javax.swing.JCheckBox();
        jCheckBox13 = new javax.swing.JCheckBox();
        jCheckBox14 = new javax.swing.JCheckBox();
        jCheckBox15 = new javax.swing.JCheckBox();
        jCheckBox16 = new javax.swing.JCheckBox();
        jCheckBox17 = new javax.swing.JCheckBox();
        jCheckBox18 = new javax.swing.JCheckBox();
        panel_settings = new javax.swing.JPanel();
        jCheckBox19 = new javax.swing.JCheckBox();
        jCheckBox20 = new javax.swing.JCheckBox();
        jCheckBox21 = new javax.swing.JCheckBox();
        jCheckBox22 = new javax.swing.JCheckBox();
        jCheckBox23 = new javax.swing.JCheckBox();
        jCheckBox24 = new javax.swing.JCheckBox();
        jCheckBox25 = new javax.swing.JCheckBox();
        jCheckBox26 = new javax.swing.JCheckBox();
        jCheckBox27 = new javax.swing.JCheckBox();
        jCheckBox28 = new javax.swing.JCheckBox();
        jCheckBox29 = new javax.swing.JCheckBox();
        jCheckBox30 = new javax.swing.JCheckBox();
        panel_useraccounts = new javax.swing.JPanel();
        jCheckBox31 = new javax.swing.JCheckBox();
        jCheckBox32 = new javax.swing.JCheckBox();
        jCheckBox33 = new javax.swing.JCheckBox();
        jCheckBox34 = new javax.swing.JCheckBox();
        jCheckBox35 = new javax.swing.JCheckBox();
        jPanel1 = new javax.swing.JPanel();
        btn_Search1 = new javax.swing.JButton();
        panel_assigned = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();

        setMinimumSize(new java.awt.Dimension(1200, 600));
        setPreferredSize(new java.awt.Dimension(1200, 600));
        setLayout(new java.awt.GridBagLayout());

        panel_toppanel.setMinimumSize(new java.awt.Dimension(1200, 600));
        panel_toppanel.setPreferredSize(new java.awt.Dimension(1200, 600));
        panel_toppanel.setLayout(new java.awt.GridBagLayout());

        panel_control.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        panel_control.setMinimumSize(new java.awt.Dimension(1200, 40));
        panel_control.setPreferredSize(new java.awt.Dimension(1200, 40));
        panel_control.setLayout(new java.awt.GridBagLayout());

        btn_createnew.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btn_createnew.setText("Add New Account");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        panel_control.add(btn_createnew, gridBagConstraints);

        btn_Edit.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btn_Edit.setText("Edit");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        panel_control.add(btn_Edit, gridBagConstraints);

        btn_View.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btn_View.setText("View");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        panel_control.add(btn_View, gridBagConstraints);

        btn_Print.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btn_Print.setText("Print");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 60);
        panel_control.add(btn_Print, gridBagConstraints);

        Searchbox.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        Searchbox.setText("Search here");
        Searchbox.setMinimumSize(new java.awt.Dimension(150, 25));
        Searchbox.setPreferredSize(new java.awt.Dimension(150, 25));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(0, 300, 0, 0);
        panel_control.add(Searchbox, gridBagConstraints);

        btn_Search.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btn_Search.setText("Search");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 0;
        panel_control.add(btn_Search, gridBagConstraints);

        lbl_show.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbl_show.setText("Search By  :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(0, 30, 0, 0);
        panel_control.add(lbl_show, gridBagConstraints);

        combo_filter.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        combo_filter.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "All" }));
        combo_filter.setMinimumSize(new java.awt.Dimension(80, 25));
        combo_filter.setPreferredSize(new java.awt.Dimension(80, 25));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 100);
        panel_control.add(combo_filter, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        panel_toppanel.add(panel_control, gridBagConstraints);

        panel_masterrecord.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Sytem Users Master List", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        panel_masterrecord.setMinimumSize(new java.awt.Dimension(950, 455));
        panel_masterrecord.setPreferredSize(new java.awt.Dimension(950, 455));
        panel_masterrecord.setLayout(new java.awt.GridBagLayout());

        jScrollPane1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jScrollPane1.setMinimumSize(new java.awt.Dimension(930, 430));
        jScrollPane1.setPreferredSize(new java.awt.Dimension(930, 430));

        jTable1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Employee ID", "Name", "Email", "Mobile", "Username", "Password", "Status"
            }
        ));
        jTable1.setIntercellSpacing(new java.awt.Dimension(16, 1));
        jTable1.setRowHeight(20);
        jScrollPane1.setViewportView(jTable1);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        panel_masterrecord.add(jScrollPane1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        panel_toppanel.add(panel_masterrecord, gridBagConstraints);

        panel_permission.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Permission", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        panel_permission.setMinimumSize(new java.awt.Dimension(250, 555));
        panel_permission.setPreferredSize(new java.awt.Dimension(250, 555));
        panel_permission.setLayout(new java.awt.GridBagLayout());

        jScrollPane2.setMinimumSize(new java.awt.Dimension(240, 1000));
        jScrollPane2.setPreferredSize(new java.awt.Dimension(240, 1000));

        jPanel3.setMinimumSize(new java.awt.Dimension(230, 800));
        jPanel3.setPreferredSize(new java.awt.Dimension(230, 800));
        jPanel3.setLayout(new java.awt.GridBagLayout());

        panel_enrollment.setMinimumSize(new java.awt.Dimension(240, 80));
        panel_enrollment.setPreferredSize(new java.awt.Dimension(240, 80));
        panel_enrollment.setLayout(new java.awt.GridBagLayout());

        jCheckBox1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jCheckBox1.setText("Enrollment");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        panel_enrollment.add(jCheckBox1, gridBagConstraints);

        jCheckBox2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jCheckBox2.setText("Enrollment Withdrawal");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 0);
        panel_enrollment.add(jCheckBox2, gridBagConstraints);

        jCheckBox3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jCheckBox3.setText("Sectioning");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 0);
        panel_enrollment.add(jCheckBox3, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        jPanel3.add(panel_enrollment, gridBagConstraints);

        panel_gradingsystem.setMinimumSize(new java.awt.Dimension(280, 130));
        panel_gradingsystem.setPreferredSize(new java.awt.Dimension(280, 130));
        panel_gradingsystem.setLayout(new java.awt.GridBagLayout());

        jCheckBox6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jCheckBox6.setText("Grading System");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        panel_gradingsystem.add(jCheckBox6, gridBagConstraints);

        jCheckBox7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jCheckBox7.setText("Input Grades");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 0);
        panel_gradingsystem.add(jCheckBox7, gridBagConstraints);

        jCheckBox8.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jCheckBox8.setText("Open Grade Information");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 0);
        panel_gradingsystem.add(jCheckBox8, gridBagConstraints);

        jCheckBox9.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jCheckBox9.setText("Print Option");
        jCheckBox9.setActionCommand("Print Option");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 0);
        panel_gradingsystem.add(jCheckBox9, gridBagConstraints);

        jCheckBox10.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jCheckBox10.setText("View");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 0);
        panel_gradingsystem.add(jCheckBox10, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        jPanel3.add(panel_gradingsystem, gridBagConstraints);

        panel_paymentandassessment.setMinimumSize(new java.awt.Dimension(280, 200));
        panel_paymentandassessment.setPreferredSize(new java.awt.Dimension(280, 200));
        panel_paymentandassessment.setLayout(new java.awt.GridBagLayout());

        jCheckBox11.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jCheckBox11.setText("Payment & Assessment");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        panel_paymentandassessment.add(jCheckBox11, gridBagConstraints);

        jCheckBox12.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jCheckBox12.setText("Account Balance");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 0);
        panel_paymentandassessment.add(jCheckBox12, gridBagConstraints);

        jCheckBox13.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jCheckBox13.setText("Adjustments");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 0);
        panel_paymentandassessment.add(jCheckBox13, gridBagConstraints);

        jCheckBox14.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jCheckBox14.setText("Discounts");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 0);
        panel_paymentandassessment.add(jCheckBox14, gridBagConstraints);

        jCheckBox15.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jCheckBox15.setText("Make Payment");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 0);
        panel_paymentandassessment.add(jCheckBox15, gridBagConstraints);

        jCheckBox16.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jCheckBox16.setText("Receipts");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 0);
        panel_paymentandassessment.add(jCheckBox16, gridBagConstraints);

        jCheckBox17.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jCheckBox17.setText("Statement of Accounts");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 0);
        panel_paymentandassessment.add(jCheckBox17, gridBagConstraints);

        jCheckBox18.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jCheckBox18.setText("Transaction History");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 0);
        panel_paymentandassessment.add(jCheckBox18, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        jPanel3.add(panel_paymentandassessment, gridBagConstraints);

        panel_settings.setMinimumSize(new java.awt.Dimension(280, 300));
        panel_settings.setPreferredSize(new java.awt.Dimension(280, 300));
        panel_settings.setLayout(new java.awt.GridBagLayout());

        jCheckBox19.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jCheckBox19.setText("Settings");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        panel_settings.add(jCheckBox19, gridBagConstraints);

        jCheckBox20.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jCheckBox20.setText("Credentials");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 0);
        panel_settings.add(jCheckBox20, gridBagConstraints);

        jCheckBox21.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jCheckBox21.setText("Curriculum");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 0);
        panel_settings.add(jCheckBox21, gridBagConstraints);

        jCheckBox22.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jCheckBox22.setText("Discounts");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 0);
        panel_settings.add(jCheckBox22, gridBagConstraints);

        jCheckBox23.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jCheckBox23.setText("Faculty");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 0);
        panel_settings.add(jCheckBox23, gridBagConstraints);

        jCheckBox24.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jCheckBox24.setText("Fees");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 0);
        panel_settings.add(jCheckBox24, gridBagConstraints);

        jCheckBox25.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jCheckBox25.setText("Payment Schedule");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 0);
        panel_settings.add(jCheckBox25, gridBagConstraints);

        jCheckBox26.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jCheckBox26.setText("Room");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 0);
        panel_settings.add(jCheckBox26, gridBagConstraints);

        jCheckBox27.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jCheckBox27.setText("School Year");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 0);
        panel_settings.add(jCheckBox27, gridBagConstraints);

        jCheckBox28.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jCheckBox28.setText("Section");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 0);
        panel_settings.add(jCheckBox28, gridBagConstraints);

        jCheckBox29.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jCheckBox29.setText("Subjects");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 0);
        panel_settings.add(jCheckBox29, gridBagConstraints);

        jCheckBox30.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jCheckBox30.setText("Subjects Category");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 11;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 0);
        panel_settings.add(jCheckBox30, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        jPanel3.add(panel_settings, gridBagConstraints);

        panel_useraccounts.setMinimumSize(new java.awt.Dimension(280, 130));
        panel_useraccounts.setPreferredSize(new java.awt.Dimension(280, 130));
        panel_useraccounts.setLayout(new java.awt.GridBagLayout());

        jCheckBox31.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jCheckBox31.setText("User Accounts");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        panel_useraccounts.add(jCheckBox31, gridBagConstraints);

        jCheckBox32.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jCheckBox32.setText("Create New Accounts");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 0);
        panel_useraccounts.add(jCheckBox32, gridBagConstraints);

        jCheckBox33.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jCheckBox33.setText("Edit Permissions");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 0);
        panel_useraccounts.add(jCheckBox33, gridBagConstraints);

        jCheckBox34.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jCheckBox34.setText("Print Option");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 0);
        panel_useraccounts.add(jCheckBox34, gridBagConstraints);

        jCheckBox35.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jCheckBox35.setText("View");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 0);
        panel_useraccounts.add(jCheckBox35, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        gridBagConstraints.weighty = 0.5;
        jPanel3.add(panel_useraccounts, gridBagConstraints);

        jScrollPane2.setViewportView(jPanel3);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        gridBagConstraints.weighty = 0.5;
        panel_permission.add(jScrollPane2, gridBagConstraints);

        jPanel1.setMinimumSize(new java.awt.Dimension(240, 40));
        jPanel1.setPreferredSize(new java.awt.Dimension(240, 40));
        jPanel1.setLayout(new java.awt.GridBagLayout());

        btn_Search1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btn_Search1.setText("Edit Permission");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 0);
        jPanel1.add(btn_Search1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        panel_permission.add(jPanel1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.weighty = 0.5;
        panel_toppanel.add(panel_permission, gridBagConstraints);

        panel_assigned.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Permissions Assigned", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        panel_assigned.setMinimumSize(new java.awt.Dimension(950, 150));
        panel_assigned.setPreferredSize(new java.awt.Dimension(950, 150));
        panel_assigned.setLayout(new java.awt.GridBagLayout());

        jScrollPane3.setMinimumSize(new java.awt.Dimension(930, 20));
        jScrollPane3.setPreferredSize(new java.awt.Dimension(930, 83));

        jTextArea1.setColumns(20);
        jTextArea1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jTextArea1.setRows(5);
        jScrollPane3.setViewportView(jTextArea1);

        panel_assigned.add(jScrollPane3, new java.awt.GridBagConstraints());

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        panel_toppanel.add(panel_assigned, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        add(panel_toppanel, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField Searchbox;
    private javax.swing.JButton btn_Edit;
    private javax.swing.JButton btn_Print;
    private javax.swing.JButton btn_Search;
    private javax.swing.JButton btn_Search1;
    private javax.swing.JButton btn_View;
    private javax.swing.JButton btn_createnew;
    private javax.swing.JComboBox<String> combo_filter;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBox10;
    private javax.swing.JCheckBox jCheckBox11;
    private javax.swing.JCheckBox jCheckBox12;
    private javax.swing.JCheckBox jCheckBox13;
    private javax.swing.JCheckBox jCheckBox14;
    private javax.swing.JCheckBox jCheckBox15;
    private javax.swing.JCheckBox jCheckBox16;
    private javax.swing.JCheckBox jCheckBox17;
    private javax.swing.JCheckBox jCheckBox18;
    private javax.swing.JCheckBox jCheckBox19;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JCheckBox jCheckBox20;
    private javax.swing.JCheckBox jCheckBox21;
    private javax.swing.JCheckBox jCheckBox22;
    private javax.swing.JCheckBox jCheckBox23;
    private javax.swing.JCheckBox jCheckBox24;
    private javax.swing.JCheckBox jCheckBox25;
    private javax.swing.JCheckBox jCheckBox26;
    private javax.swing.JCheckBox jCheckBox27;
    private javax.swing.JCheckBox jCheckBox28;
    private javax.swing.JCheckBox jCheckBox29;
    private javax.swing.JCheckBox jCheckBox3;
    private javax.swing.JCheckBox jCheckBox30;
    private javax.swing.JCheckBox jCheckBox31;
    private javax.swing.JCheckBox jCheckBox32;
    private javax.swing.JCheckBox jCheckBox33;
    private javax.swing.JCheckBox jCheckBox34;
    private javax.swing.JCheckBox jCheckBox35;
    private javax.swing.JCheckBox jCheckBox6;
    private javax.swing.JCheckBox jCheckBox7;
    private javax.swing.JCheckBox jCheckBox8;
    private javax.swing.JCheckBox jCheckBox9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JLabel lbl_show;
    private javax.swing.JPanel panel_assigned;
    private javax.swing.JPanel panel_control;
    private javax.swing.JPanel panel_enrollment;
    private javax.swing.JPanel panel_gradingsystem;
    private javax.swing.JPanel panel_masterrecord;
    private javax.swing.JPanel panel_paymentandassessment;
    private javax.swing.JPanel panel_permission;
    private javax.swing.JPanel panel_settings;
    private javax.swing.JPanel panel_toppanel;
    private javax.swing.JPanel panel_useraccounts;
    // End of variables declaration//GEN-END:variables
}
