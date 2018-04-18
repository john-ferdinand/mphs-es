
package view.fees;

import component_model_loader.FeeJCompModelLoader;
import controller.feesetting.AddFee;
import controller.feesetting.ClearAllGradeLevelCheckBoxes;
import controller.feesetting.ClearFeeAmountOnUncheck;
import controller.feesetting.EditFee;
import controller.global.Controller_JButton_ExitJDialog;
import daoimpl.FeeDaoImpl;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.Map;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.JTextField;
import model.fee.Fee;
import utility.initializer.Initializer;

/**
 *
 * 
 */
public class JdlgFeeCrud extends javax.swing.JDialog implements Initializer{

    private final String action;
    private int feeIdOfSelected;
    private FeeJCompModelLoader feeJCompModelLoader;
    private DefaultComboBoxModel cmbmFeeCategories;
    private FeeDaoImpl feeDaoImpl;
    
    public JdlgFeeCrud(java.awt.Frame parent, boolean modal, String action) {
        super(parent, modal);
        this.action = action;
        initComponents();
        
        initJCompModelLoaders();
        initRenderers();
        initModels();
        initViewComponents();
        initControllers();
        initDaoImpl();
    }
    
    /**
     * Use this constructor during Edit or View operation.
     * This constructor is specifically used to supply a feeId of what's selected 
     * so to view the information of that specific Fee selected.
     * 
     * @param parent
     * @param modal
     * @param action
     * @param feeIdOfSelected 
     */
    public JdlgFeeCrud(java.awt.Frame parent, boolean modal, String action, int feeIdOfSelected) {
        super(parent, modal);
        this.action = action;
        this.feeIdOfSelected = feeIdOfSelected;
        initComponents();
        
        initJCompModelLoaders();
        initRenderers();
        initModels();
        initViewComponents();
        initControllers();
        initDaoImpl();
        
        initForm(feeIdOfSelected);
    }

    @Override
    public void initRenderers() {
    }
    
    @Override
    public final void initJCompModelLoaders() {
        feeJCompModelLoader = new FeeJCompModelLoader();
    }
    
    @Override
    public void initGridBagConstraints() {
    }

    @Override
    public final void initModels() {
        cmbmFeeCategories = feeJCompModelLoader.getAllFeeCategoriesAsModel();
    }

    @Override
    public final void initViewComponents() {
        initJButtons();
        jcmbFeeCategory.setModel(cmbmFeeCategories);
    }
    
    private void initJButtons() {
        if (action.equalsIgnoreCase("create")) {
            jbtnOk.setVisible(false);
            jlblActive.setVisible(false);
            jcmbStatus.setVisible(false);
        } else if (action.equalsIgnoreCase("edit")) {
            jbtnClear.setVisible(false);
            jbtnSaveAndNew.setVisible(false);
            jbtnOk.setVisible(false);
        } else if (action.equalsIgnoreCase("view")) {
            Component[] components = jpnlGradeLevels.getComponents();
            for(Component c : components){
                if(c instanceof JCheckBox){
                    ((JCheckBox)c).setEnabled(false);
                }else if(c instanceof JTextField){
                    ((JTextField)c).setEnabled(false);
                }
            }
            jbtnUncheckAll.setVisible(false);
            jtfFeeName.setEnabled(false);
            jtfAmount.setEnabled(false);
            jcmbFeeCategory.setEnabled(false);
            jcmbStatus.setEnabled(false);
            jtaDescription.setEnabled(false);
            jbtnApplyToAll.setEnabled(false);
            jbtnCancel.setVisible(false);
            jbtnClear.setVisible(false);
            jbtnSave.setVisible(false);
            jbtnSaveAndNew.setVisible(false);
        } else if (action.equalsIgnoreCase("print")) {

        }
    }

    @Override
    public final void initControllers() {
        check_kinder.addActionListener(new ClearFeeAmountOnUncheck(check_kinder, jtfKinderGarten));
        check_Grade1.addActionListener(new ClearFeeAmountOnUncheck(check_Grade1, jtfGrade1));
        check_Grade2.addActionListener(new ClearFeeAmountOnUncheck(check_Grade2, jtfGrade2));
        check_Grade3.addActionListener(new ClearFeeAmountOnUncheck(check_Grade3, jtfGrade3));
        check_Grade4.addActionListener(new ClearFeeAmountOnUncheck(check_Grade4, jtfGrade4));
        check_Grade5.addActionListener(new ClearFeeAmountOnUncheck(check_Grade5, jtfGrade5));
        check_Grade6.addActionListener(new ClearFeeAmountOnUncheck(check_Grade6, jtfGrade6));
        check_Grade7.addActionListener(new ClearFeeAmountOnUncheck(check_Grade7, jtfGrade7));
        check_Grade8.addActionListener(new ClearFeeAmountOnUncheck(check_Grade8, jtfGrade8));
        check_Grade9.addActionListener(new ClearFeeAmountOnUncheck(check_Grade9, jtfGrade9));
        check_Grade10.addActionListener(new ClearFeeAmountOnUncheck(check_Grade10, jtfGrade10));
        
        jbtnUncheckAll.addActionListener(new ClearAllGradeLevelCheckBoxes(jpnlGradeLevels));
        
        if (action.equals("create")) {
            jbtnSave.addActionListener(new AddFee(
                    jtfFeeName, jtfAmount, jcmbFeeCategory, jtfKinderGarten, jtfGrade1, jtfGrade2,
                    jtfGrade3, jtfGrade4, jtfGrade5, jtfGrade6, jtfGrade7, jtfGrade8, jtfGrade9, jtfGrade10, jtaDescription,
                    jpnlGradeLevels, this
                )
            );
        }
        
        if (action.equals("edit")) {
            jbtnSave.addActionListener(new EditFee(
                    feeIdOfSelected,
                    jtfFeeName, jtfAmount, jcmbFeeCategory, jtfKinderGarten, jtfGrade1, jtfGrade2,
                    jtfGrade3, jtfGrade4, jtfGrade5, jtfGrade6, jtfGrade7, jtfGrade8, jtfGrade9,
                    jtfGrade10, jtaDescription,
                    jpnlGradeLevels,
                    jcmbStatus, this));
        }
        
        jbtnCancel.addActionListener(new Controller_JButton_ExitJDialog(this));
        jbtnOk.addActionListener(new Controller_JButton_ExitJDialog(this));
        
        jbtnApplyToAll.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Component[] components = jpnlGradeLevels.getComponents();
                for (Component c : components) {
                    if (c instanceof JCheckBox) {
                        if (!jtfAmount.getText().trim().isEmpty()) {
                            ((JCheckBox) c).setSelected(true);
                        }
                    } else if (c instanceof JTextField) {
                        if (!jtfAmount.getText().trim().isEmpty()) {
                            ((JTextField) c).setText(jtfAmount.getText());
                        }
                    }
                }
            }
        });
    }

    @Override
    public void initDaoImpl() {
        feeDaoImpl = new FeeDaoImpl();
    }
    
    private void initForm(int feeIdOfSelected){
        if(action.equals("view") || action.equals("edit")){
            Fee fee = feeDaoImpl.getFeeInfoById(feeIdOfSelected);
            Map<Integer,BigDecimal> gradeLevelAmountPair = 
                    feeDaoImpl.getFeeGradeLevelAssignmentAndAmountById(feeIdOfSelected).getGradeLevelAmountPair();
            fillGradeLevelAmountPair(gradeLevelAmountPair);
            jcmbStatus.setSelectedItem(fee.isActive()==true?"Yes":"No");
            jtfFeeName.setText(fee.getName());
            jtaDescription.setText(fee.getDescription());
            jtfAmount.setText(fee.getAmount()+"");
            jcmbFeeCategory.setSelectedItem(fee.getFeeCategory().getName().trim());
        }
    }
    
    private void fillGradeLevelAmountPair(Map<Integer,BigDecimal> map){
        for(Map.Entry<Integer,BigDecimal> entry : map.entrySet()){
           Integer gradeLevel = entry.getKey();
           BigDecimal amount = entry.getValue();
            switch (gradeLevel) {
                case 0:
                    check_kinder.setSelected(true);
                    jtfKinderGarten.setText(amount + "");
                    break;
                case 1:
                    check_Grade1.setSelected(true);
                    jtfGrade1.setText(amount + "");
                    break;
                case 2:
                    check_Grade2.setSelected(true);
                    jtfGrade2.setText(amount + "");
                    break;
                case 3:
                    check_Grade3.setSelected(true);
                    jtfGrade3.setText(amount + "");
                    break;
                case 4:
                    check_Grade4.setSelected(true);
                    jtfGrade4.setText(amount + "");
                    break;
                case 5:
                    check_Grade5.setSelected(true);
                    jtfGrade5.setText(amount + "");
                    break;
                case 6:
                    check_Grade6.setSelected(true);
                    jtfGrade6.setText(amount + "");
                    break;
                case 7:
                    check_Grade7.setSelected(true);
                    jtfGrade7.setText(amount + "");
                    break;
                case 8:
                    check_Grade8.setSelected(true);
                    jtfGrade8.setText(amount + "");
                    break;
                case 9:
                    check_Grade9.setSelected(true);
                    jtfGrade9.setText(amount + "");
                    break;
                case 10:
                    check_Grade10.setSelected(true);
                    jtfGrade10.setText(amount + "");
                    break;
            }
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        panel_toppanel = new javax.swing.JPanel();
        panel_roomdetails = new javax.swing.JPanel();
        lbl_roomname = new javax.swing.JLabel();
        jtfFeeName = new javax.swing.JTextField();
        lbl_roombldg = new javax.swing.JLabel();
        jlblActive = new javax.swing.JLabel();
        jcmbStatus = new javax.swing.JComboBox<>();
        lbl_capacity = new javax.swing.JLabel();
        jtfAmount = new javax.swing.JTextField();
        jcmbFeeCategory = new javax.swing.JComboBox<>();
        jbtnApplyToAll = new javax.swing.JButton();
        jpnlGradeLevels = new javax.swing.JPanel();
        check_kinder = new javax.swing.JCheckBox();
        check_Grade1 = new javax.swing.JCheckBox();
        jtfKinderGarten = new javax.swing.JTextField();
        jtfGrade1 = new javax.swing.JTextField();
        check_Grade4 = new javax.swing.JCheckBox();
        jtfGrade4 = new javax.swing.JTextField();
        check_Grade6 = new javax.swing.JCheckBox();
        jtfGrade6 = new javax.swing.JTextField();
        check_Grade8 = new javax.swing.JCheckBox();
        jtfGrade8 = new javax.swing.JTextField();
        check_Grade2 = new javax.swing.JCheckBox();
        jtfGrade2 = new javax.swing.JTextField();
        check_Grade5 = new javax.swing.JCheckBox();
        jtfGrade5 = new javax.swing.JTextField();
        check_Grade7 = new javax.swing.JCheckBox();
        jtfGrade7 = new javax.swing.JTextField();
        check_Grade9 = new javax.swing.JCheckBox();
        jtfGrade9 = new javax.swing.JTextField();
        check_Grade3 = new javax.swing.JCheckBox();
        jtfGrade3 = new javax.swing.JTextField();
        check_Grade10 = new javax.swing.JCheckBox();
        jtfGrade10 = new javax.swing.JTextField();
        jbtnUncheckAll = new javax.swing.JButton();
        panel_footer = new javax.swing.JPanel();
        jbtnCancel = new javax.swing.JButton();
        jbtnClear = new javax.swing.JButton();
        jbtnSave = new javax.swing.JButton();
        jbtnSaveAndNew = new javax.swing.JButton();
        jbtnOk = new javax.swing.JButton();
        panel_roomnotes1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtaDescription = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Create New Fee");
        setMinimumSize(new java.awt.Dimension(555, 520));
        setModal(true);
        setResizable(false);
        getContentPane().setLayout(new java.awt.GridBagLayout());

        panel_toppanel.setMinimumSize(new java.awt.Dimension(555, 520));
        panel_toppanel.setPreferredSize(new java.awt.Dimension(555, 520));
        panel_toppanel.setLayout(new java.awt.GridBagLayout());

        panel_roomdetails.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Fee Details", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        panel_roomdetails.setMinimumSize(new java.awt.Dimension(600, 100));
        panel_roomdetails.setPreferredSize(new java.awt.Dimension(600, 100));
        panel_roomdetails.setLayout(new java.awt.GridBagLayout());

        lbl_roomname.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbl_roomname.setText("Fee Name :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 10, 0);
        panel_roomdetails.add(lbl_roomname, gridBagConstraints);

        jtfFeeName.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jtfFeeName.setMinimumSize(new java.awt.Dimension(150, 25));
        jtfFeeName.setPreferredSize(new java.awt.Dimension(150, 25));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 10, 0);
        panel_roomdetails.add(jtfFeeName, gridBagConstraints);

        lbl_roombldg.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbl_roombldg.setText("Category :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(5, 25, 10, 0);
        panel_roomdetails.add(lbl_roombldg, gridBagConstraints);

        jlblActive.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jlblActive.setText("Active :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 0);
        panel_roomdetails.add(jlblActive, gridBagConstraints);

        jcmbStatus.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jcmbStatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Yes", "No" }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 0);
        panel_roomdetails.add(jcmbStatus, gridBagConstraints);

        lbl_capacity.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbl_capacity.setText("Amount :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 0);
        panel_roomdetails.add(lbl_capacity, gridBagConstraints);

        jtfAmount.setColumns(8);
        jtfAmount.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jtfAmount.setMinimumSize(new java.awt.Dimension(80, 25));
        jtfAmount.setPreferredSize(new java.awt.Dimension(80, 25));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 10, 5);
        panel_roomdetails.add(jtfAmount, gridBagConstraints);

        jcmbFeeCategory.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jcmbFeeCategory.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Basic" }));
        jcmbFeeCategory.setMinimumSize(new java.awt.Dimension(150, 25));
        jcmbFeeCategory.setPreferredSize(new java.awt.Dimension(150, 25));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 0);
        panel_roomdetails.add(jcmbFeeCategory, gridBagConstraints);

        jbtnApplyToAll.setText("Apply to All");
        jbtnApplyToAll.setMaximumSize(new java.awt.Dimension(100, 32));
        jbtnApplyToAll.setMinimumSize(new java.awt.Dimension(100, 32));
        jbtnApplyToAll.setPreferredSize(new java.awt.Dimension(100, 32));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 10, 0);
        panel_roomdetails.add(jbtnApplyToAll, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 0, 3);
        panel_toppanel.add(panel_roomdetails, gridBagConstraints);

        jpnlGradeLevels.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Grade Levels", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        jpnlGradeLevels.setMinimumSize(new java.awt.Dimension(600, 170));
        jpnlGradeLevels.setPreferredSize(new java.awt.Dimension(600, 170));
        jpnlGradeLevels.setLayout(new java.awt.GridBagLayout());

        check_kinder.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        check_kinder.setText("Kindergarten");
        check_kinder.setMaximumSize(new java.awt.Dimension(98, 25));
        check_kinder.setMinimumSize(new java.awt.Dimension(98, 25));
        check_kinder.setPreferredSize(new java.awt.Dimension(98, 25));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 0);
        jpnlGradeLevels.add(check_kinder, gridBagConstraints);

        check_Grade1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        check_Grade1.setText("Grade 1");
        check_Grade1.setMaximumSize(new java.awt.Dimension(71, 25));
        check_Grade1.setMinimumSize(new java.awt.Dimension(71, 25));
        check_Grade1.setPreferredSize(new java.awt.Dimension(71, 25));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 0);
        jpnlGradeLevels.add(check_Grade1, gridBagConstraints);

        jtfKinderGarten.setColumns(8);
        jtfKinderGarten.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jtfKinderGarten.setToolTipText("Kindergarten");
        jtfKinderGarten.setMinimumSize(new java.awt.Dimension(80, 25));
        jtfKinderGarten.setPreferredSize(new java.awt.Dimension(80, 25));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 0);
        jpnlGradeLevels.add(jtfKinderGarten, gridBagConstraints);

        jtfGrade1.setColumns(8);
        jtfGrade1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jtfGrade1.setToolTipText("Grade 1");
        jtfGrade1.setMinimumSize(new java.awt.Dimension(80, 25));
        jtfGrade1.setPreferredSize(new java.awt.Dimension(80, 25));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 0);
        jpnlGradeLevels.add(jtfGrade1, gridBagConstraints);

        check_Grade4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        check_Grade4.setText("Grade 4");
        check_Grade4.setMaximumSize(new java.awt.Dimension(71, 25));
        check_Grade4.setMinimumSize(new java.awt.Dimension(71, 25));
        check_Grade4.setPreferredSize(new java.awt.Dimension(71, 25));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 5, 0);
        jpnlGradeLevels.add(check_Grade4, gridBagConstraints);

        jtfGrade4.setColumns(8);
        jtfGrade4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jtfGrade4.setToolTipText("Grade 4");
        jtfGrade4.setMinimumSize(new java.awt.Dimension(80, 25));
        jtfGrade4.setPreferredSize(new java.awt.Dimension(80, 25));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 0);
        jpnlGradeLevels.add(jtfGrade4, gridBagConstraints);

        check_Grade6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        check_Grade6.setText("Grade 6");
        check_Grade6.setMaximumSize(new java.awt.Dimension(71, 25));
        check_Grade6.setMinimumSize(new java.awt.Dimension(71, 25));
        check_Grade6.setPreferredSize(new java.awt.Dimension(71, 25));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 5, 0);
        jpnlGradeLevels.add(check_Grade6, gridBagConstraints);

        jtfGrade6.setColumns(8);
        jtfGrade6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jtfGrade6.setToolTipText("Grade 6");
        jtfGrade6.setMinimumSize(new java.awt.Dimension(80, 25));
        jtfGrade6.setPreferredSize(new java.awt.Dimension(80, 25));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 0);
        jpnlGradeLevels.add(jtfGrade6, gridBagConstraints);

        check_Grade8.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        check_Grade8.setText("Grade 8");
        check_Grade8.setMaximumSize(new java.awt.Dimension(71, 25));
        check_Grade8.setMinimumSize(new java.awt.Dimension(71, 25));
        check_Grade8.setPreferredSize(new java.awt.Dimension(71, 25));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 5, 0);
        jpnlGradeLevels.add(check_Grade8, gridBagConstraints);

        jtfGrade8.setColumns(8);
        jtfGrade8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jtfGrade8.setToolTipText("Grade 8");
        jtfGrade8.setMinimumSize(new java.awt.Dimension(80, 25));
        jtfGrade8.setPreferredSize(new java.awt.Dimension(80, 25));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 20);
        jpnlGradeLevels.add(jtfGrade8, gridBagConstraints);

        check_Grade2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        check_Grade2.setText("Grade 2");
        check_Grade2.setToolTipText("");
        check_Grade2.setMaximumSize(new java.awt.Dimension(71, 25));
        check_Grade2.setMinimumSize(new java.awt.Dimension(71, 25));
        check_Grade2.setPreferredSize(new java.awt.Dimension(71, 25));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 0);
        jpnlGradeLevels.add(check_Grade2, gridBagConstraints);

        jtfGrade2.setColumns(8);
        jtfGrade2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jtfGrade2.setToolTipText("Grade 2");
        jtfGrade2.setMinimumSize(new java.awt.Dimension(80, 25));
        jtfGrade2.setPreferredSize(new java.awt.Dimension(80, 25));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 0);
        jpnlGradeLevels.add(jtfGrade2, gridBagConstraints);

        check_Grade5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        check_Grade5.setText("Grade 5");
        check_Grade5.setMaximumSize(new java.awt.Dimension(71, 25));
        check_Grade5.setMinimumSize(new java.awt.Dimension(71, 25));
        check_Grade5.setPreferredSize(new java.awt.Dimension(71, 25));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 5, 0);
        jpnlGradeLevels.add(check_Grade5, gridBagConstraints);

        jtfGrade5.setColumns(8);
        jtfGrade5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jtfGrade5.setToolTipText("Grade 5");
        jtfGrade5.setMinimumSize(new java.awt.Dimension(80, 25));
        jtfGrade5.setPreferredSize(new java.awt.Dimension(80, 25));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 0);
        jpnlGradeLevels.add(jtfGrade5, gridBagConstraints);

        check_Grade7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        check_Grade7.setText("Grade 7");
        check_Grade7.setMaximumSize(new java.awt.Dimension(71, 25));
        check_Grade7.setMinimumSize(new java.awt.Dimension(71, 25));
        check_Grade7.setPreferredSize(new java.awt.Dimension(71, 25));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 5, 0);
        jpnlGradeLevels.add(check_Grade7, gridBagConstraints);

        jtfGrade7.setColumns(8);
        jtfGrade7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jtfGrade7.setToolTipText("Grade 7");
        jtfGrade7.setMinimumSize(new java.awt.Dimension(80, 25));
        jtfGrade7.setPreferredSize(new java.awt.Dimension(80, 25));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 0);
        jpnlGradeLevels.add(jtfGrade7, gridBagConstraints);

        check_Grade9.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        check_Grade9.setText("Grade 9");
        check_Grade9.setMaximumSize(new java.awt.Dimension(71, 25));
        check_Grade9.setMinimumSize(new java.awt.Dimension(71, 25));
        check_Grade9.setPreferredSize(new java.awt.Dimension(71, 25));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 5, 0);
        jpnlGradeLevels.add(check_Grade9, gridBagConstraints);

        jtfGrade9.setColumns(8);
        jtfGrade9.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jtfGrade9.setToolTipText("Grade 9");
        jtfGrade9.setMinimumSize(new java.awt.Dimension(80, 25));
        jtfGrade9.setPreferredSize(new java.awt.Dimension(80, 25));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 20);
        jpnlGradeLevels.add(jtfGrade9, gridBagConstraints);

        check_Grade3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        check_Grade3.setText("Grade 3");
        check_Grade3.setActionCommand("Grade 9");
        check_Grade3.setMaximumSize(new java.awt.Dimension(71, 25));
        check_Grade3.setMinimumSize(new java.awt.Dimension(71, 25));
        check_Grade3.setPreferredSize(new java.awt.Dimension(71, 25));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 0);
        jpnlGradeLevels.add(check_Grade3, gridBagConstraints);

        jtfGrade3.setColumns(8);
        jtfGrade3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jtfGrade3.setToolTipText("Grade 3");
        jtfGrade3.setMinimumSize(new java.awt.Dimension(80, 25));
        jtfGrade3.setPreferredSize(new java.awt.Dimension(80, 25));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 0);
        jpnlGradeLevels.add(jtfGrade3, gridBagConstraints);

        check_Grade10.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        check_Grade10.setText("Grade 10");
        check_Grade10.setMaximumSize(new java.awt.Dimension(80, 25));
        check_Grade10.setMinimumSize(new java.awt.Dimension(80, 25));
        check_Grade10.setPreferredSize(new java.awt.Dimension(80, 25));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 5, 0);
        jpnlGradeLevels.add(check_Grade10, gridBagConstraints);

        jtfGrade10.setColumns(8);
        jtfGrade10.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jtfGrade10.setToolTipText("Grade 10");
        jtfGrade10.setMinimumSize(new java.awt.Dimension(80, 25));
        jtfGrade10.setPreferredSize(new java.awt.Dimension(80, 25));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 20);
        jpnlGradeLevels.add(jtfGrade10, gridBagConstraints);

        jbtnUncheckAll.setText("Uncheck All");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jpnlGradeLevels.add(jbtnUncheckAll, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 3);
        panel_toppanel.add(jpnlGradeLevels, gridBagConstraints);

        panel_footer.setMinimumSize(new java.awt.Dimension(550, 50));
        panel_footer.setPreferredSize(new java.awt.Dimension(550, 50));
        panel_footer.setLayout(new java.awt.GridBagLayout());

        jbtnCancel.setFont(new java.awt.Font("Microsoft JhengHei", 1, 12)); // NOI18N
        jbtnCancel.setText("Cancel");
        jbtnCancel.setMaximumSize(new java.awt.Dimension(80, 40));
        jbtnCancel.setMinimumSize(new java.awt.Dimension(80, 40));
        jbtnCancel.setPreferredSize(new java.awt.Dimension(80, 40));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        panel_footer.add(jbtnCancel, gridBagConstraints);

        jbtnClear.setFont(new java.awt.Font("Microsoft JhengHei", 1, 12)); // NOI18N
        jbtnClear.setText("Clear");
        jbtnClear.setMaximumSize(new java.awt.Dimension(80, 40));
        jbtnClear.setMinimumSize(new java.awt.Dimension(80, 40));
        jbtnClear.setPreferredSize(new java.awt.Dimension(80, 40));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        panel_footer.add(jbtnClear, gridBagConstraints);

        jbtnSave.setFont(new java.awt.Font("Microsoft JhengHei", 1, 12)); // NOI18N
        jbtnSave.setText("Save");
        jbtnSave.setMaximumSize(new java.awt.Dimension(80, 40));
        jbtnSave.setMinimumSize(new java.awt.Dimension(80, 40));
        jbtnSave.setPreferredSize(new java.awt.Dimension(80, 40));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        panel_footer.add(jbtnSave, gridBagConstraints);

        jbtnSaveAndNew.setFont(new java.awt.Font("Microsoft JhengHei", 1, 12)); // NOI18N
        jbtnSaveAndNew.setText("Save & New");
        jbtnSaveAndNew.setMaximumSize(new java.awt.Dimension(120, 40));
        jbtnSaveAndNew.setMinimumSize(new java.awt.Dimension(120, 40));
        jbtnSaveAndNew.setPreferredSize(new java.awt.Dimension(120, 40));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        panel_footer.add(jbtnSaveAndNew, gridBagConstraints);

        jbtnOk.setFont(new java.awt.Font("Microsoft JhengHei", 1, 12)); // NOI18N
        jbtnOk.setText("OK");
        jbtnOk.setMaximumSize(new java.awt.Dimension(80, 40));
        jbtnOk.setMinimumSize(new java.awt.Dimension(80, 40));
        jbtnOk.setPreferredSize(new java.awt.Dimension(80, 40));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        panel_footer.add(jbtnOk, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 40, 0);
        panel_toppanel.add(panel_footer, gridBagConstraints);

        panel_roomnotes1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Fee Description", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        panel_roomnotes1.setMinimumSize(new java.awt.Dimension(600, 150));
        panel_roomnotes1.setPreferredSize(new java.awt.Dimension(600, 150));
        panel_roomnotes1.setLayout(new java.awt.GridBagLayout());

        jScrollPane1.setMinimumSize(new java.awt.Dimension(535, 120));
        jScrollPane1.setPreferredSize(new java.awt.Dimension(535, 120));

        jtaDescription.setColumns(20);
        jtaDescription.setRows(5);
        jtaDescription.setMinimumSize(new java.awt.Dimension(545, 145));
        jtaDescription.setPreferredSize(new java.awt.Dimension(545, 145));
        jScrollPane1.setViewportView(jtaDescription);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        panel_roomnotes1.add(jScrollPane1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 3);
        panel_toppanel.add(panel_roomnotes1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.5;
        getContentPane().add(panel_toppanel, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox check_Grade1;
    private javax.swing.JCheckBox check_Grade10;
    private javax.swing.JCheckBox check_Grade2;
    private javax.swing.JCheckBox check_Grade3;
    private javax.swing.JCheckBox check_Grade4;
    private javax.swing.JCheckBox check_Grade5;
    private javax.swing.JCheckBox check_Grade6;
    private javax.swing.JCheckBox check_Grade7;
    private javax.swing.JCheckBox check_Grade8;
    private javax.swing.JCheckBox check_Grade9;
    private javax.swing.JCheckBox check_kinder;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jbtnApplyToAll;
    private javax.swing.JButton jbtnCancel;
    private javax.swing.JButton jbtnClear;
    private javax.swing.JButton jbtnOk;
    private javax.swing.JButton jbtnSave;
    private javax.swing.JButton jbtnSaveAndNew;
    private javax.swing.JButton jbtnUncheckAll;
    private javax.swing.JComboBox<String> jcmbFeeCategory;
    private javax.swing.JComboBox<String> jcmbStatus;
    private javax.swing.JLabel jlblActive;
    private javax.swing.JPanel jpnlGradeLevels;
    private javax.swing.JTextArea jtaDescription;
    private javax.swing.JTextField jtfAmount;
    private javax.swing.JTextField jtfFeeName;
    private javax.swing.JTextField jtfGrade1;
    private javax.swing.JTextField jtfGrade10;
    private javax.swing.JTextField jtfGrade2;
    private javax.swing.JTextField jtfGrade3;
    private javax.swing.JTextField jtfGrade4;
    private javax.swing.JTextField jtfGrade5;
    private javax.swing.JTextField jtfGrade6;
    private javax.swing.JTextField jtfGrade7;
    private javax.swing.JTextField jtfGrade8;
    private javax.swing.JTextField jtfGrade9;
    private javax.swing.JTextField jtfKinderGarten;
    private javax.swing.JLabel lbl_capacity;
    private javax.swing.JLabel lbl_roombldg;
    private javax.swing.JLabel lbl_roomname;
    private javax.swing.JPanel panel_footer;
    private javax.swing.JPanel panel_roomdetails;
    private javax.swing.JPanel panel_roomnotes1;
    private javax.swing.JPanel panel_toppanel;
    // End of variables declaration//GEN-END:variables
}
