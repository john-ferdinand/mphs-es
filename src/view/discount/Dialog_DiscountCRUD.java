
package view.discount;

import controller.discount.Controller_CreateSave_Discount_JButton;
import controller.global.Controller_JButton_ExitJDialog;
import daoimpl.DiscountDaoImpl;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import model.discount.Discount;
import model.schoolyear.SchoolYear;
import model.user.User;
import utility.initializer.Initializer;

/**
 *
 * @author Jordan
 */
public class Dialog_DiscountCRUD extends javax.swing.JDialog implements Initializer{

    private final Panel_Discount panelDiscount;
    private final SchoolYear currentSchoolYear;
    private final User user;
    private final String actionCommand;
    private DiscountDaoImpl discountDaoImpl;
    
    public Dialog_DiscountCRUD(java.awt.Frame parent, boolean modal, String actionCommand, Panel_Discount panelDiscount, SchoolYear currentSchoolYear, User user) {
        super(parent, modal);
        initComponents();
        this.panelDiscount = panelDiscount;
        this.currentSchoolYear = currentSchoolYear;
        this.user = user;
        this.actionCommand = actionCommand;
        
        initDaoImpl();
        initJCompModelLoaders();
        initRenderers();
        initViewComponents();
        initControllers();
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
        if(actionCommand.equals("create")){
            jlblStatus.setVisible(false);
            jcmbStatus.setVisible(false);
            jbtnSave.setVisible(false);
        }else if(actionCommand.equals("edit")){
            int rowSelected = panelDiscount.getJtblDiscountMasterList().getSelectedRow();
            int discountId = Integer.parseInt(panelDiscount.getJtblDiscountMasterList().getValueAt(rowSelected, 0).toString().trim());
            initForm(discountId);
        }
    }

    @Override
    public void initControllers() {
        int rowSelected = panelDiscount.getJtblDiscountMasterList().getSelectedRow();
        int discountId = Integer.parseInt(panelDiscount.getJtblDiscountMasterList().getValueAt(rowSelected, 0).toString().trim());
        jbtnCancel.addActionListener(new Controller_JButton_ExitJDialog(this));
        jbtnCreate.addActionListener(new Controller_CreateSave_Discount_JButton(panelDiscount,this));
        jbtnSave.addActionListener(new Controller_CreateSave_Discount_JButton(panelDiscount, this, discountId));
    }

    @Override
    public void initDaoImpl() {
        discountDaoImpl = new DiscountDaoImpl();
    }

    private void initForm(int discountId){
        Discount discount = discountDaoImpl.getDiscountBy(discountId);
        jtfDiscountName.setText(discount.getDiscountName());
        jsprPercentage.setValue(discount.getPercent());
        jtaDiscountDescription.setText(discount.getDescription());
        jtaDiscountProvision.setText(discount.getProvision());
        jcmbStatus.setSelectedItem(discount.getIsActive() == true? "Active":"Inactive");
    }
    
    public SchoolYear getCurrentSchoolYear() {
        return currentSchoolYear;
    }

    public User getUser() {
        return user;
    }

    public String getActionCommand() {
        return actionCommand;
    }

    public JSpinner getJsprPercentage() {
        return jsprPercentage;
    }

    public JTextArea getJtaDiscountDescription() {
        return jtaDiscountDescription;
    }

    public JTextArea getJtaDiscountProvision() {
        return jtaDiscountProvision;
    }
    
    public JButton getJbtnCancel() {
        return jbtnCancel;
    }

    public JButton getJbtnSave() {
        return jbtnSave;
    }

    public JComboBox<String> getJcmbStatus() {
        return jcmbStatus;
    }

    public JLabel getJlblDiscountName() {
        return jlblDiscountName;
    }

    public JLabel getJlblStatus() {
        return jlblStatus;
    }

    public JPanel getJpnlControls() {
        return jpnlControls;
    }

    public JTextArea getJtaDescription() {
        return jtaDiscountDescription;
    }

    public JTextField getJtfDiscountName() {
        return jtfDiscountName;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtaDiscountDescription = new javax.swing.JTextArea();
        jpnlFooter = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtaDiscountProvision = new javax.swing.JTextArea();
        jlblStatus = new javax.swing.JLabel();
        jcmbStatus = new javax.swing.JComboBox<>();
        jpnlHeader = new javax.swing.JPanel();
        jlblDiscountName = new javax.swing.JLabel();
        jtfDiscountName = new javax.swing.JTextField();
        jlblPercentage = new javax.swing.JLabel();
        jsprPercentage = new javax.swing.JSpinner();
        jLabel1 = new javax.swing.JLabel();
        jpnlControls = new javax.swing.JPanel();
        jbtnCancel = new javax.swing.JButton();
        jbtnSave = new javax.swing.JButton();
        jbtnCreate = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(560, 345));
        getContentPane().setLayout(new java.awt.GridBagLayout());

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jPanel1.setLayout(new java.awt.GridBagLayout());

        jScrollPane1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Description", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        jScrollPane1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jScrollPane1.setMinimumSize(new java.awt.Dimension(150, 39));
        jScrollPane1.setPreferredSize(new java.awt.Dimension(150, 102));

        jtaDiscountDescription.setColumns(20);
        jtaDiscountDescription.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jtaDiscountDescription.setRows(5);
        jScrollPane1.setViewportView(jtaDiscountDescription);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel1.add(jScrollPane1, gridBagConstraints);

        jpnlFooter.setLayout(new java.awt.GridBagLayout());

        jScrollPane2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Provision", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N

        jtaDiscountProvision.setColumns(20);
        jtaDiscountProvision.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jtaDiscountProvision.setRows(5);
        jScrollPane2.setViewportView(jtaDiscountProvision);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jpnlFooter.add(jScrollPane2, gridBagConstraints);

        jlblStatus.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jlblStatus.setText("Status :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jpnlFooter.add(jlblStatus, gridBagConstraints);

        jcmbStatus.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jcmbStatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Active", "Inactive" }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jpnlFooter.add(jcmbStatus, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel1.add(jpnlFooter, gridBagConstraints);

        jpnlHeader.setLayout(new java.awt.GridBagLayout());

        jlblDiscountName.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jlblDiscountName.setText("Discount Name");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jpnlHeader.add(jlblDiscountName, gridBagConstraints);

        jtfDiscountName.setColumns(10);
        jtfDiscountName.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jpnlHeader.add(jtfDiscountName, gridBagConstraints);

        jlblPercentage.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jlblPercentage.setText("Percentage");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jpnlHeader.add(jlblPercentage, gridBagConstraints);

        jsprPercentage.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jsprPercentage.setModel(new javax.swing.SpinnerNumberModel(1, 1, 100, 1));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jpnlHeader.add(jsprPercentage, gridBagConstraints);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("%");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jpnlHeader.add(jLabel1, gridBagConstraints);

        jPanel1.add(jpnlHeader, new java.awt.GridBagConstraints());

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        getContentPane().add(jPanel1, gridBagConstraints);

        jpnlControls.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jpnlControls.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jpnlControls.setLayout(new java.awt.GridBagLayout());

        jbtnCancel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jbtnCancel.setText("Cancel");
        jbtnCancel.setActionCommand("cancel");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jpnlControls.add(jbtnCancel, gridBagConstraints);

        jbtnSave.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jbtnSave.setText("Save");
        jbtnSave.setActionCommand("save");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jpnlControls.add(jbtnSave, gridBagConstraints);

        jbtnCreate.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jbtnCreate.setText("Create");
        jbtnCreate.setActionCommand("create");
        jpnlControls.add(jbtnCreate, new java.awt.GridBagConstraints());

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        getContentPane().add(jpnlControls, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton jbtnCancel;
    private javax.swing.JButton jbtnCreate;
    private javax.swing.JButton jbtnSave;
    private javax.swing.JComboBox<String> jcmbStatus;
    private javax.swing.JLabel jlblDiscountName;
    private javax.swing.JLabel jlblPercentage;
    private javax.swing.JLabel jlblStatus;
    private javax.swing.JPanel jpnlControls;
    private javax.swing.JPanel jpnlFooter;
    private javax.swing.JPanel jpnlHeader;
    private javax.swing.JSpinner jsprPercentage;
    private javax.swing.JTextArea jtaDiscountDescription;
    private javax.swing.JTextArea jtaDiscountProvision;
    private javax.swing.JTextField jtfDiscountName;
    // End of variables declaration//GEN-END:variables
}
