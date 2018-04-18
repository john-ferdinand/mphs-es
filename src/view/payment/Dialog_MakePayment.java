package view.payment;

import controller.global.Controller_JButton_ExitJDialog;
import controller.payment.Dialog_MakePayment_AddItemToPay;
import controller.payment.Dialog_MakePayment_PaymentBreakDown_TableModelListener;
import controller.payment.Dialog_MakePayment_ProceedPayment;
import controller.payment.Dialog_MakePayment_RemoveItemToPay;
import daoimpl.OfficialReceiptDaoImpl;
import daoimpl.SchoolYearDaoImpl;
import daoimpl.TuitionFeeDaoImpl;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import model.balancebreakdownfee.BalanceBreakDownFee;
import model.tuitionfee.Tuition;
import model.user.User;
import utility.initializer.Initializer;

public class Dialog_MakePayment extends javax.swing.JDialog implements Initializer {

    private final boolean isStudent;
    private final boolean hasTuitionBalance;
    private final Tuition tuition;
    private final User user;
    private final TuitionFeeDaoImpl tuitionFeeDaoImpl;
    private final OfficialReceiptDaoImpl orNoDaoImpl;
    private final SchoolYearDaoImpl schoolYearDaoImpl;

    public Dialog_MakePayment(boolean isStudent, boolean hasTuitionBalance, Tuition tuition, User user) {
        this.isStudent = isStudent;
        this.hasTuitionBalance = hasTuitionBalance;
        this.tuition = tuition;
        this.user = user;
        this.tuitionFeeDaoImpl = new TuitionFeeDaoImpl();
        this.orNoDaoImpl = new OfficialReceiptDaoImpl();
        this.schoolYearDaoImpl = new SchoolYearDaoImpl();
        initComponents();

        initControllers();
        initViewComponents();
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
        if(tuition.getPaymentTerm().getPaymentTermName().trim().equalsIgnoreCase("Cash")){
            jcbDownPayment.setEnabled(false);
            jcbBalance.setEnabled(false);
            jbtnAdd.setEnabled(false);
            jbtnRemove.setEnabled(false);
            DefaultTableModel tableModel = (DefaultTableModel)jtblPaymentBreakDown.getModel();
            for(BalanceBreakDownFee b : tuition.getBalanceBreakDownFees()){
                Object[] rowData = {b.getName(),b.getAmountDue().setScale(2,RoundingMode.HALF_UP), b.getDeadline(), b.getCategory()};
                if(!b.isFullyPaid()){
                    tableModel.addRow(rowData);
                }else{
                    jcbDownPayment.setEnabled(false);
                }
            }
        }else{
            List<BalanceBreakDownFee> bbFeeList = tuition.getBalanceBreakDownFees();
            for(BalanceBreakDownFee b : bbFeeList){
                if(b.getName().trim().equalsIgnoreCase("Downpayment")){
                    if(b.isFullyPaid()){
                        jcbDownPayment.setEnabled(false);
                    }else{
                        jcbDownPayment.setEnabled(true);
                    }
                }
            }
            jcbBalance.setEnabled(true);
            jbtnAdd.setEnabled(true);
            jbtnRemove.setEnabled(true);
        }
        
        jlblOrNo.setText(orNoDaoImpl.getNextAvailableOrNoForPaymentBySchoolYearId(schoolYearDaoImpl.getCurrentSchoolYearId()));
        initBalanceJCombo();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDateTime now = LocalDateTime.now();
        jlblDateToday.setText(""+dtf.format(now));
        jlblCashierName.setText(user.getLastName()+", "+user.getFirstName()+" "+user.getMiddleName());
    }

    private void initBalanceJCombo() {
        DefaultComboBoxModel comboModel = new DefaultComboBoxModel();
        List<BalanceBreakDownFee> bbFeeList = tuition.getBalanceBreakDownFees();
        for (int i = 0; i < bbFeeList.size(); i++) {
            String category = bbFeeList.get(i).getCategory().trim();
            if (category.equalsIgnoreCase("Balance") || category.equalsIgnoreCase("B")) {
                if(!bbFeeList.get(i).isFullyPaid()){
                    comboModel.addElement(bbFeeList.get(i).getName().trim());
                }
            }
        }
        jcmbBalance.setModel(comboModel);
    }

    @Override
    public void initControllers() {
        jbtnAdd.addActionListener(new Dialog_MakePayment_AddItemToPay(isStudent,this, tuition));
        jcbBalance.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                jcmbBalance.setEnabled(jcbBalance.isSelected());
            }
        });
        jbtnCancel.addActionListener(new Controller_JButton_ExitJDialog(this));
        jbtnRemove.addActionListener(new Dialog_MakePayment_RemoveItemToPay(this));
        jtblPaymentBreakDown.getModel().addTableModelListener(new Dialog_MakePayment_PaymentBreakDown_TableModelListener(this));
        
        jbtnProceedPayment.addActionListener(new Dialog_MakePayment_ProceedPayment(isStudent,hasTuitionBalance,this,tuition, tuitionFeeDaoImpl,user));
        
        jtfSubtotal.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                if(!jtfPenalty.getText().trim().isEmpty() && jtfPenalty.getText() != null){
                    BigDecimal penalty = BigDecimal.valueOf(Double.parseDouble(jtfPenalty.getText().trim()));
                    BigDecimal subTotal = BigDecimal.valueOf(Double.parseDouble(jtfSubtotal.getText().trim()));
                    BigDecimal total = penalty.add(subTotal);
                    jtfTotal.setText(""+total.setScale(2,RoundingMode.HALF_UP));
                }else{
                    BigDecimal subTotal = BigDecimal.valueOf(Double.parseDouble(jtfSubtotal.getText().trim()));
                    jtfTotal.setText(""+subTotal.setScale(2,RoundingMode.HALF_UP));
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
            }
        });
        
        jtfTendered.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                jtfAmountCharged.setText(jtfTendered.getText());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
               jtfAmountCharged.setText(jtfTendered.getText());
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
            }
        });
    }

    @Override
    public void initDaoImpl() {
    }

    public JLabel getJlblChargedPhp() {
        return jlblChargedPhp;
    }

    public JLabel getJlblPenaltyPhp() {
        return jlblPenaltyPhp;
    }

    public JLabel getJlblReceivedPhp() {
        return jlblReceivedPhp;
    }

    public JLabel getJlblTotalPhp() {
        return jlblTotalPhp;
    }

    public JPanel getJpnlFooter() {
        return jpnlFooter;
    }

    public JPanel getJpnlPaymentBreakDown() {
        return jpnlPaymentBreakDown;
    }

    public JPanel getJpnlTopPanel() {
        return jpnlTopPanel;
    }

    public JScrollPane getJspPaymentBreakDown() {
        return jspPaymentBreakDown;
    }

    public JTextField getJtfAmountCharged() {
        return jtfAmountCharged;
    }
    
    public JLabel getDisplay_lastname10() {
        return jlblTotalPhp;
    }

    public JLabel getDisplay_lastname12() {
        return jlblReceivedPhp;
    }

    public JLabel getDisplay_lastname3() {
        return display_lastname3;
    }

    public JLabel getDisplay_lastname7() {
        return jlblPenaltyPhp;
    }

    public JPanel getjPanel1() {
        return jPanel1;
    }

    public JPanel getjPanel2() {
        return jPanel2;
    }

    public JScrollPane getjScrollPane1() {
        return jspPaymentBreakDown;
    }

    public JButton getJbtnAdd() {
        return jbtnAdd;
    }

    public JButton getJbtnCancel() {
        return jbtnCancel;
    }

    public JButton getJbtnProceedPayment() {
        return jbtnProceedPayment;
    }

    public JButton getJbtnRemove() {
        return jbtnRemove;
    }

    public JCheckBox getJcbBalance() {
        return jcbBalance;
    }

    public JCheckBox getJcbDownPayment() {
        return jcbDownPayment;
    }

    public JComboBox<String> getJcmbBalance() {
        return jcmbBalance;
    }

    public JLabel getJlblCashierName() {
        return jlblCashierName;
    }

    public JLabel getJlblDateToday() {
        return jlblDateToday;
    }

    public JLabel getJlblOrNo() {
        return jlblOrNo;
    }

    public JTable getJtblPaymentBreakDown() {
        return jtblPaymentBreakDown;
    }

    public JTextField getJtfPenalty() {
        return jtfPenalty;
    }

    public JTextField getJtfSubtotal() {
        return jtfSubtotal;
    }

    public JTextField getJtfTendered() {
        return jtfTendered;
    }

    public JTextField getJtfTotal() {
        return jtfTotal;
    }

    public JPanel getPanel_datetodaycontainer() {
        return panel_datetodaycontainer;
    }

    public JPanel getPanel_datetodaycontainer1() {
        return panel_datetodaycontainer1;
    }

    public JPanel getPanel_datetodaycontainer2() {
        return jpnlPaymentBreakDown;
    }

    public JPanel getPanel_footer() {
        return jpnlFooter;
    }

    public JPanel getPanel_toppanel() {
        return jpnlTopPanel;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jpnlTopPanel = new javax.swing.JPanel();
        panel_datetodaycontainer = new javax.swing.JPanel();
        javax.swing.JLabel lbl_datetoday = new javax.swing.JLabel();
        jlblDateToday = new javax.swing.JLabel();
        javax.swing.JLabel lbl_datetoday1 = new javax.swing.JLabel();
        jlblOrNo = new javax.swing.JLabel();
        javax.swing.JLabel lbl_datetoday3 = new javax.swing.JLabel();
        jlblCashierName = new javax.swing.JLabel();
        panel_datetodaycontainer1 = new javax.swing.JPanel();
        jcbDownPayment = new javax.swing.JCheckBox();
        jcbBalance = new javax.swing.JCheckBox();
        jcmbBalance = new javax.swing.JComboBox<>();
        jPanel2 = new javax.swing.JPanel();
        jbtnAdd = new javax.swing.JButton();
        jbtnRemove = new javax.swing.JButton();
        jpnlPaymentBreakDown = new javax.swing.JPanel();
        jspPaymentBreakDown = new javax.swing.JScrollPane();
        jtblPaymentBreakDown = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        javax.swing.JLabel lbl_datetoday4 = new javax.swing.JLabel();
        display_lastname3 = new javax.swing.JLabel();
        javax.swing.JLabel jlblPenalty = new javax.swing.JLabel();
        jlblPenaltyPhp = new javax.swing.JLabel();
        javax.swing.JLabel jlblTotal = new javax.swing.JLabel();
        jlblTotalPhp = new javax.swing.JLabel();
        javax.swing.JLabel jlblReceived = new javax.swing.JLabel();
        jlblReceivedPhp = new javax.swing.JLabel();
        jtfTendered = new javax.swing.JTextField();
        jtfSubtotal = new javax.swing.JTextField();
        jtfPenalty = new javax.swing.JTextField();
        jtfTotal = new javax.swing.JTextField();
        jlblChargedPhp = new javax.swing.JLabel();
        jtfAmountCharged = new javax.swing.JTextField();
        jpnlFooter = new javax.swing.JPanel();
        jbtnCancel = new javax.swing.JButton();
        jbtnProceedPayment = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Make Payment");
        setMinimumSize(new java.awt.Dimension(500, 600));
        setModal(true);
        setResizable(false);
        getContentPane().setLayout(new java.awt.GridBagLayout());

        jpnlTopPanel.setMinimumSize(new java.awt.Dimension(500, 600));
        jpnlTopPanel.setPreferredSize(new java.awt.Dimension(500, 600));
        jpnlTopPanel.setLayout(new java.awt.GridBagLayout());

        panel_datetodaycontainer.setMinimumSize(new java.awt.Dimension(490, 70));
        panel_datetodaycontainer.setPreferredSize(new java.awt.Dimension(490, 70));
        panel_datetodaycontainer.setLayout(new java.awt.GridBagLayout());

        lbl_datetoday.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbl_datetoday.setText("Date Today :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 0);
        panel_datetodaycontainer.add(lbl_datetoday, gridBagConstraints);

        jlblDateToday.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jlblDateToday.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jlblDateToday.setMaximumSize(new java.awt.Dimension(150, 20));
        jlblDateToday.setMinimumSize(new java.awt.Dimension(150, 20));
        jlblDateToday.setPreferredSize(new java.awt.Dimension(150, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 0);
        panel_datetodaycontainer.add(jlblDateToday, gridBagConstraints);

        lbl_datetoday1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbl_datetoday1.setText("OR # :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 0);
        panel_datetodaycontainer.add(lbl_datetoday1, gridBagConstraints);

        jlblOrNo.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jlblOrNo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jlblOrNo.setMaximumSize(new java.awt.Dimension(150, 20));
        jlblOrNo.setMinimumSize(new java.awt.Dimension(150, 20));
        jlblOrNo.setPreferredSize(new java.awt.Dimension(150, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 0);
        panel_datetodaycontainer.add(jlblOrNo, gridBagConstraints);

        lbl_datetoday3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbl_datetoday3.setText("Cashier :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        panel_datetodaycontainer.add(lbl_datetoday3, gridBagConstraints);

        jlblCashierName.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jlblCashierName.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jlblCashierName.setMaximumSize(new java.awt.Dimension(150, 20));
        jlblCashierName.setMinimumSize(new java.awt.Dimension(150, 20));
        jlblCashierName.setPreferredSize(new java.awt.Dimension(150, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        panel_datetodaycontainer.add(jlblCashierName, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        jpnlTopPanel.add(panel_datetodaycontainer, gridBagConstraints);

        panel_datetodaycontainer1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Fees", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        panel_datetodaycontainer1.setMinimumSize(new java.awt.Dimension(490, 100));
        panel_datetodaycontainer1.setPreferredSize(new java.awt.Dimension(490, 100));
        panel_datetodaycontainer1.setLayout(new java.awt.GridBagLayout());

        jcbDownPayment.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jcbDownPayment.setText("Downpayment");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        panel_datetodaycontainer1.add(jcbDownPayment, gridBagConstraints);

        jcbBalance.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jcbBalance.setText("Balance");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        panel_datetodaycontainer1.add(jcbBalance, gridBagConstraints);

        jcmbBalance.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jcmbBalance.setEnabled(false);
        jcmbBalance.setMinimumSize(new java.awt.Dimension(100, 25));
        jcmbBalance.setPreferredSize(new java.awt.Dimension(100, 25));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        panel_datetodaycontainer1.add(jcmbBalance, gridBagConstraints);

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel2.setLayout(new java.awt.GridBagLayout());

        jbtnAdd.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jbtnAdd.setText("Add");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel2.add(jbtnAdd, gridBagConstraints);

        jbtnRemove.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jbtnRemove.setText("Remove");
        jbtnRemove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnRemoveActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel2.add(jbtnRemove, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        panel_datetodaycontainer1.add(jPanel2, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        jpnlTopPanel.add(panel_datetodaycontainer1, gridBagConstraints);

        jpnlPaymentBreakDown.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Payment Breakdown", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        jpnlPaymentBreakDown.setMinimumSize(new java.awt.Dimension(490, 400));
        jpnlPaymentBreakDown.setPreferredSize(new java.awt.Dimension(490, 400));
        jpnlPaymentBreakDown.setLayout(new java.awt.GridBagLayout());

        jspPaymentBreakDown.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jspPaymentBreakDown.setMinimumSize(new java.awt.Dimension(480, 150));
        jspPaymentBreakDown.setPreferredSize(new java.awt.Dimension(480, 150));

        jtblPaymentBreakDown.setAutoCreateRowSorter(true);
        jtblPaymentBreakDown.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jtblPaymentBreakDown.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Particular", "Amount", ""
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtblPaymentBreakDown.setRowHeight(20);
        jtblPaymentBreakDown.getTableHeader().setReorderingAllowed(false);
        jspPaymentBreakDown.setViewportView(jtblPaymentBreakDown);
        if (jtblPaymentBreakDown.getColumnModel().getColumnCount() > 0) {
            jtblPaymentBreakDown.getColumnModel().getColumn(2).setMinWidth(1);
            jtblPaymentBreakDown.getColumnModel().getColumn(2).setPreferredWidth(1);
            jtblPaymentBreakDown.getColumnModel().getColumn(2).setMaxWidth(1);
        }

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        jpnlPaymentBreakDown.add(jspPaymentBreakDown, gridBagConstraints);

        jPanel1.setMinimumSize(new java.awt.Dimension(250, 200));
        jPanel1.setPreferredSize(new java.awt.Dimension(250, 200));
        jPanel1.setLayout(new java.awt.GridBagLayout());

        lbl_datetoday4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lbl_datetoday4.setText("Subtotal :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 0);
        jPanel1.add(lbl_datetoday4, gridBagConstraints);

        display_lastname3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        display_lastname3.setText("Php");
        display_lastname3.setMaximumSize(new java.awt.Dimension(30, 20));
        display_lastname3.setMinimumSize(new java.awt.Dimension(30, 20));
        display_lastname3.setPreferredSize(new java.awt.Dimension(30, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 0);
        jPanel1.add(display_lastname3, gridBagConstraints);

        jlblPenalty.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jlblPenalty.setText("Penalty :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 0);
        jPanel1.add(jlblPenalty, gridBagConstraints);

        jlblPenaltyPhp.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jlblPenaltyPhp.setText("Php");
        jlblPenaltyPhp.setMaximumSize(new java.awt.Dimension(30, 20));
        jlblPenaltyPhp.setMinimumSize(new java.awt.Dimension(30, 20));
        jlblPenaltyPhp.setPreferredSize(new java.awt.Dimension(30, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 0);
        jPanel1.add(jlblPenaltyPhp, gridBagConstraints);

        jlblTotal.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jlblTotal.setForeground(new java.awt.Color(0, 51, 102));
        jlblTotal.setText("Total :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 0);
        jPanel1.add(jlblTotal, gridBagConstraints);

        jlblTotalPhp.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jlblTotalPhp.setForeground(new java.awt.Color(0, 51, 102));
        jlblTotalPhp.setText("Php");
        jlblTotalPhp.setMaximumSize(new java.awt.Dimension(30, 20));
        jlblTotalPhp.setMinimumSize(new java.awt.Dimension(30, 20));
        jlblTotalPhp.setPreferredSize(new java.awt.Dimension(30, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 0);
        jPanel1.add(jlblTotalPhp, gridBagConstraints);

        jlblReceived.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jlblReceived.setForeground(new java.awt.Color(0, 153, 102));
        jlblReceived.setText("Tendered");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 0);
        jPanel1.add(jlblReceived, gridBagConstraints);

        jlblReceivedPhp.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jlblReceivedPhp.setForeground(new java.awt.Color(0, 153, 102));
        jlblReceivedPhp.setText("Php");
        jlblReceivedPhp.setMaximumSize(new java.awt.Dimension(30, 20));
        jlblReceivedPhp.setMinimumSize(new java.awt.Dimension(30, 20));
        jlblReceivedPhp.setPreferredSize(new java.awt.Dimension(30, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 0);
        jPanel1.add(jlblReceivedPhp, gridBagConstraints);

        jtfTendered.setBackground(new java.awt.Color(0, 153, 102));
        jtfTendered.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jtfTendered.setMinimumSize(new java.awt.Dimension(100, 25));
        jtfTendered.setPreferredSize(new java.awt.Dimension(100, 25));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 5;
        jPanel1.add(jtfTendered, gridBagConstraints);

        jtfSubtotal.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jtfSubtotal.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        jtfSubtotal.setEnabled(false);
        jtfSubtotal.setMinimumSize(new java.awt.Dimension(100, 25));
        jtfSubtotal.setPreferredSize(new java.awt.Dimension(100, 25));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        jPanel1.add(jtfSubtotal, gridBagConstraints);

        jtfPenalty.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jtfPenalty.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        jtfPenalty.setEnabled(false);
        jtfPenalty.setMinimumSize(new java.awt.Dimension(100, 25));
        jtfPenalty.setPreferredSize(new java.awt.Dimension(100, 25));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        jPanel1.add(jtfPenalty, gridBagConstraints);

        jtfTotal.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jtfTotal.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        jtfTotal.setEnabled(false);
        jtfTotal.setMinimumSize(new java.awt.Dimension(100, 25));
        jtfTotal.setPreferredSize(new java.awt.Dimension(100, 25));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        jPanel1.add(jtfTotal, gridBagConstraints);

        jlblChargedPhp.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jlblChargedPhp.setForeground(new java.awt.Color(0, 153, 102));
        jlblChargedPhp.setText("Php");
        jlblChargedPhp.setMaximumSize(new java.awt.Dimension(30, 20));
        jlblChargedPhp.setMinimumSize(new java.awt.Dimension(30, 20));
        jlblChargedPhp.setPreferredSize(new java.awt.Dimension(30, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 0);
        jPanel1.add(jlblChargedPhp, gridBagConstraints);

        jtfAmountCharged.setBackground(new java.awt.Color(0, 153, 102));
        jtfAmountCharged.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jtfAmountCharged.setMinimumSize(new java.awt.Dimension(100, 25));
        jtfAmountCharged.setPreferredSize(new java.awt.Dimension(100, 25));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 6;
        jPanel1.add(jtfAmountCharged, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 0, 0);
        jpnlPaymentBreakDown.add(jPanel1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        jpnlTopPanel.add(jpnlPaymentBreakDown, gridBagConstraints);

        jpnlFooter.setMinimumSize(new java.awt.Dimension(490, 50));
        jpnlFooter.setPreferredSize(new java.awt.Dimension(490, 50));
        jpnlFooter.setLayout(new java.awt.GridBagLayout());

        jbtnCancel.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jbtnCancel.setText("Cancel");
        jbtnCancel.setMaximumSize(new java.awt.Dimension(90, 40));
        jbtnCancel.setMinimumSize(new java.awt.Dimension(90, 40));
        jbtnCancel.setPreferredSize(new java.awt.Dimension(90, 40));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.weightx = 0.5;
        jpnlFooter.add(jbtnCancel, gridBagConstraints);

        jbtnProceedPayment.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jbtnProceedPayment.setText("Proceed Payment");
        jbtnProceedPayment.setActionCommand("proceedpayment");
        jbtnProceedPayment.setMaximumSize(new java.awt.Dimension(200, 40));
        jbtnProceedPayment.setMinimumSize(new java.awt.Dimension(200, 40));
        jbtnProceedPayment.setPreferredSize(new java.awt.Dimension(200, 40));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 30);
        jpnlFooter.add(jbtnProceedPayment, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 20, 0);
        jpnlTopPanel.add(jpnlFooter, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        getContentPane().add(jpnlTopPanel, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbtnRemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnRemoveActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jbtnRemoveActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel display_lastname3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JButton jbtnAdd;
    private javax.swing.JButton jbtnCancel;
    private javax.swing.JButton jbtnProceedPayment;
    private javax.swing.JButton jbtnRemove;
    private javax.swing.JCheckBox jcbBalance;
    private javax.swing.JCheckBox jcbDownPayment;
    private javax.swing.JComboBox<String> jcmbBalance;
    private javax.swing.JLabel jlblCashierName;
    private javax.swing.JLabel jlblChargedPhp;
    private javax.swing.JLabel jlblDateToday;
    private javax.swing.JLabel jlblOrNo;
    private javax.swing.JLabel jlblPenaltyPhp;
    private javax.swing.JLabel jlblReceivedPhp;
    private javax.swing.JLabel jlblTotalPhp;
    private javax.swing.JPanel jpnlFooter;
    private javax.swing.JPanel jpnlPaymentBreakDown;
    private javax.swing.JPanel jpnlTopPanel;
    private javax.swing.JScrollPane jspPaymentBreakDown;
    private javax.swing.JTable jtblPaymentBreakDown;
    private javax.swing.JTextField jtfAmountCharged;
    private javax.swing.JTextField jtfPenalty;
    private javax.swing.JTextField jtfSubtotal;
    private javax.swing.JTextField jtfTendered;
    private javax.swing.JTextField jtfTotal;
    private javax.swing.JPanel panel_datetodaycontainer;
    private javax.swing.JPanel panel_datetodaycontainer1;
    // End of variables declaration//GEN-END:variables
}
