package view.subjectcategory;

import component_model_loader.SubjectCategoryJCompModelLoader;
import component_model_loader.SubjectJCompModelLoader;
import controller.global.Controller_JButton_ExitJDialog;
import controller.subjectcategory.CreateSubjectCategory;
import controller.subjectcategory.EditSubjectCategory;
import controller.subjectcategory.MoveSelectedSubjectsToAssigned;
import controller.subjectcategory.RemoveSelectedSubjectsFromAssigned;
import daoimpl.SubjectCategoryDaoImpl;
import daoimpl.SubjectDaoImpl;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.subjectcategory.SubjectCategory;
import utility.initializer.Initializer;

public class DialogSubjectCategoryCrud extends javax.swing.JDialog implements Initializer{

    private String action;
    private int subjectCtgyIdOfSelected;
    private SubjectDaoImpl subjectDaoImpl;
    private SubjectCategoryDaoImpl subjectCategoryDaoImpl;
    private SubjectCategoryJCompModelLoader subjectCategoryJCompModelLoader;
    private SubjectJCompModelLoader subjectJCompModelLoader;
    private DefaultTableModel dtmSubjectMasterList;
    
    public DialogSubjectCategoryCrud(java.awt.Frame parent, boolean modal, String action) {
        super(parent, modal);
        initComponents();
        
        this.action = action;
        
        initJCompModelLoaders();
        initRenderers();
        initModels();
        initViewComponents();
        initControllers();
        initDaoImpl();
    }
    
    
    /**
     * Use this constructor during VIEW and EDIT operations
     * @param parent
     * @param modal
     * @param action
     * @param subjectCtgyIdOfSelected 
     */
    public DialogSubjectCategoryCrud(java.awt.Frame parent, boolean modal, String action, int subjectCtgyIdOfSelected) {
        super(parent, modal);
        initComponents();
        
        this.action = action;
        this.subjectCtgyIdOfSelected = subjectCtgyIdOfSelected;
        
        initJCompModelLoaders();
        initRenderers();
        initModels();
        initViewComponents();
        initControllers();
        initDaoImpl();
        
        initForm();
    }

    @Override
    public void initRenderers() {
        
    }
    
    @Override
    public void initGridBagConstraints() {

    }

    @Override
    public void initJCompModelLoaders() {
        subjectJCompModelLoader = new SubjectJCompModelLoader();
        subjectCategoryJCompModelLoader = new SubjectCategoryJCompModelLoader();
    }

    @Override
    public void initModels() {
        dtmSubjectMasterList = subjectJCompModelLoader.getAllSubjectsInfo(jtblSubjectMasterList);
    }

    @Override
    public void initViewComponents() {
        jtblSubjectMasterList.setModel(dtmSubjectMasterList);
        
        if(action.equalsIgnoreCase("create")){
            jlblStatus.setVisible(false);
            jcmbStatus.setVisible(false);
        }else if(action.equalsIgnoreCase("edit")){
            
        }else if(action.equalsIgnoreCase("view")){
            jbtnAddSubject.setEnabled(false);
            jbtnClear.setVisible(false);
            jbtnRemove.setEnabled(false);
            jbtnSave.setVisible(false);
            jbtnSaveAndNew.setVisible(false);
            jtfCategoryName.setEnabled(false);
            jcmbStatus.setEnabled(false);
            jtaDescription.setEnabled(false);
            jtblAssignedSubjects.setEnabled(false);
            jtblSubjectMasterList.setEnabled(false);
        }else if(action.equalsIgnoreCase("print")){
            
        }
    }

    @Override
    public void initControllers() {
        jbtnCancel.addActionListener(new Controller_JButton_ExitJDialog(this));
        jbtnAddSubject.addActionListener(new MoveSelectedSubjectsToAssigned(jtblSubjectMasterList, jtblAssignedSubjects));
        jbtnRemove.addActionListener(new RemoveSelectedSubjectsFromAssigned(jtblAssignedSubjects));
        if (action.equalsIgnoreCase("create")) {
            jbtnSave.addActionListener(new CreateSubjectCategory(jtfCategoryName, jtaDescription, jtblAssignedSubjects));
        }
        if(action.equalsIgnoreCase("edit")){
            jbtnSave.addActionListener(new EditSubjectCategory(
                    subjectCtgyIdOfSelected,
                    jtfCategoryName, jtaDescription,
                    jcmbStatus, jtblAssignedSubjects,
                    this)
            );
        }
    }

    @Override
    public void initDaoImpl() {
        subjectDaoImpl = new SubjectDaoImpl();
        subjectCategoryDaoImpl = new SubjectCategoryDaoImpl();
    }
    
    /**
     * Call this method from the constructor during VIEW and EDIT operations.
     * This method requires that a subjectcategory has been supplied within the constructor where 
     * this method is called from
     */
    private void initForm(){
        SubjectCategory subjectCtgy = subjectCategoryDaoImpl.getSubjectCategoryInfoById(subjectCtgyIdOfSelected);
        jtfCategoryName.setText(subjectCtgy.getSubjectCategoryName());
        jtaDescription.setText(subjectCtgy.getDescription());
        jcmbStatus.setSelectedItem(subjectCtgy.getIsActive()==true?"Yes":"No");
        jtblAssignedSubjects.setModel(subjectCategoryJCompModelLoader.getSubjectCategoryAssignedSubjectsById(subjectCtgyIdOfSelected, jtblAssignedSubjects));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        panel_toppanel = new javax.swing.JPanel();
        panel_subjectdetails = new javax.swing.JPanel();
        lbl_subcode = new javax.swing.JLabel();
        jtfCategoryName = new javax.swing.JTextField();
        lbl_subname = new javax.swing.JLabel();
        jlblStatus = new javax.swing.JLabel();
        jcmbStatus = new javax.swing.JComboBox<>();
        jScrollPane3 = new javax.swing.JScrollPane();
        jtaDescription = new javax.swing.JTextArea();
        panel_subjectdetails1 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtblSubjectMasterList = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtblAssignedSubjects = new javax.swing.JTable();
        panel_centercontrol = new javax.swing.JPanel();
        jbtnAddSubject = new javax.swing.JButton();
        jbtnRemove = new javax.swing.JButton();
        panel_footer = new javax.swing.JPanel();
        jbtnCancel = new javax.swing.JButton();
        jbtnClear = new javax.swing.JButton();
        jbtnSave = new javax.swing.JButton();
        jbtnSaveAndNew = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Create New Subject Category");
        setMinimumSize(new java.awt.Dimension(700, 650));
        setModal(true);
        setResizable(false);
        getContentPane().setLayout(new java.awt.GridBagLayout());

        panel_toppanel.setMinimumSize(new java.awt.Dimension(700, 650));
        panel_toppanel.setPreferredSize(new java.awt.Dimension(700, 650));
        panel_toppanel.setLayout(new java.awt.GridBagLayout());

        panel_subjectdetails.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Category Details", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        panel_subjectdetails.setMinimumSize(new java.awt.Dimension(700, 100));
        panel_subjectdetails.setPreferredSize(new java.awt.Dimension(700, 100));
        panel_subjectdetails.setLayout(new java.awt.GridBagLayout());

        lbl_subcode.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbl_subcode.setText("Category Name :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 10, 0);
        panel_subjectdetails.add(lbl_subcode, gridBagConstraints);

        jtfCategoryName.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jtfCategoryName.setMinimumSize(new java.awt.Dimension(150, 25));
        jtfCategoryName.setPreferredSize(new java.awt.Dimension(150, 25));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 10, 0);
        panel_subjectdetails.add(jtfCategoryName, gridBagConstraints);

        lbl_subname.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbl_subname.setText("Description :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 15, 10, 0);
        panel_subjectdetails.add(lbl_subname, gridBagConstraints);

        jlblStatus.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jlblStatus.setText("Active :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 0);
        panel_subjectdetails.add(jlblStatus, gridBagConstraints);

        jcmbStatus.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jcmbStatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Yes", "No" }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 0);
        panel_subjectdetails.add(jcmbStatus, gridBagConstraints);

        jtaDescription.setColumns(20);
        jtaDescription.setRows(5);
        jScrollPane3.setViewportView(jtaDescription);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        panel_subjectdetails.add(jScrollPane3, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 0, 3);
        panel_toppanel.add(panel_subjectdetails, gridBagConstraints);

        panel_subjectdetails1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Assign Subjects To This Category", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        panel_subjectdetails1.setMinimumSize(new java.awt.Dimension(700, 450));
        panel_subjectdetails1.setPreferredSize(new java.awt.Dimension(700, 450));
        panel_subjectdetails1.setLayout(new java.awt.GridBagLayout());

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Subjects Master List", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11))); // NOI18N
        jPanel1.setMinimumSize(new java.awt.Dimension(325, 425));
        jPanel1.setPreferredSize(new java.awt.Dimension(325, 425));
        jPanel1.setLayout(new java.awt.GridBagLayout());

        jScrollPane1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jScrollPane1.setMinimumSize(new java.awt.Dimension(310, 400));
        jScrollPane1.setName(""); // NOI18N
        jScrollPane1.setPreferredSize(new java.awt.Dimension(310, 400));

        jtblSubjectMasterList.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jtblSubjectMasterList.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Subject Name", "Subject Code"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtblSubjectMasterList.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(jtblSubjectMasterList);

        jPanel1.add(jScrollPane1, new java.awt.GridBagConstraints());

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        panel_subjectdetails1.add(jPanel1, gridBagConstraints);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Assigned Subjects", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11))); // NOI18N
        jPanel2.setMinimumSize(new java.awt.Dimension(325, 425));
        jPanel2.setPreferredSize(new java.awt.Dimension(325, 425));
        jPanel2.setLayout(new java.awt.GridBagLayout());

        jScrollPane2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jScrollPane2.setMinimumSize(new java.awt.Dimension(310, 400));
        jScrollPane2.setName(""); // NOI18N
        jScrollPane2.setPreferredSize(new java.awt.Dimension(310, 400));

        jtblAssignedSubjects.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jtblAssignedSubjects.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Subject Name", "Subject Code"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtblAssignedSubjects.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(jtblAssignedSubjects);

        jPanel2.add(jScrollPane2, new java.awt.GridBagConstraints());

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        panel_subjectdetails1.add(jPanel2, gridBagConstraints);

        panel_centercontrol.setMinimumSize(new java.awt.Dimension(50, 400));
        panel_centercontrol.setPreferredSize(new java.awt.Dimension(50, 400));
        panel_centercontrol.setLayout(new java.awt.GridBagLayout());

        jbtnAddSubject.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jbtnAddSubject.setText(">");
        jbtnAddSubject.setMaximumSize(new java.awt.Dimension(50, 40));
        jbtnAddSubject.setMinimumSize(new java.awt.Dimension(50, 40));
        jbtnAddSubject.setPreferredSize(new java.awt.Dimension(50, 40));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
        panel_centercontrol.add(jbtnAddSubject, gridBagConstraints);

        jbtnRemove.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jbtnRemove.setText("<");
        jbtnRemove.setAlignmentY(0.0F);
        jbtnRemove.setMaximumSize(new java.awt.Dimension(50, 40));
        jbtnRemove.setMinimumSize(new java.awt.Dimension(50, 40));
        jbtnRemove.setPreferredSize(new java.awt.Dimension(50, 40));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
        panel_centercontrol.add(jbtnRemove, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        panel_subjectdetails1.add(panel_centercontrol, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 3);
        panel_toppanel.add(panel_subjectdetails1, gridBagConstraints);

        panel_footer.setMinimumSize(new java.awt.Dimension(700, 50));
        panel_footer.setPreferredSize(new java.awt.Dimension(700, 50));
        panel_footer.setLayout(new java.awt.GridBagLayout());

        jbtnCancel.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jbtnCancel.setText("Cancel");
        jbtnCancel.setActionCommand("cancel");
        jbtnCancel.setMaximumSize(new java.awt.Dimension(100, 40));
        jbtnCancel.setMinimumSize(new java.awt.Dimension(100, 40));
        jbtnCancel.setPreferredSize(new java.awt.Dimension(100, 40));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.weightx = 0.5;
        panel_footer.add(jbtnCancel, gridBagConstraints);

        jbtnClear.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jbtnClear.setText("Clear");
        jbtnClear.setActionCommand("clear");
        jbtnClear.setMaximumSize(new java.awt.Dimension(100, 40));
        jbtnClear.setMinimumSize(new java.awt.Dimension(100, 40));
        jbtnClear.setPreferredSize(new java.awt.Dimension(100, 40));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        panel_footer.add(jbtnClear, gridBagConstraints);

        jbtnSave.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jbtnSave.setText("Save");
        jbtnSave.setActionCommand("save");
        jbtnSave.setMaximumSize(new java.awt.Dimension(100, 40));
        jbtnSave.setMinimumSize(new java.awt.Dimension(100, 40));
        jbtnSave.setPreferredSize(new java.awt.Dimension(100, 40));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        panel_footer.add(jbtnSave, gridBagConstraints);

        jbtnSaveAndNew.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jbtnSaveAndNew.setText("Save & New");
        jbtnSaveAndNew.setActionCommand("save_and_new");
        jbtnSaveAndNew.setMaximumSize(new java.awt.Dimension(140, 40));
        jbtnSaveAndNew.setMinimumSize(new java.awt.Dimension(140, 40));
        jbtnSaveAndNew.setPreferredSize(new java.awt.Dimension(140, 40));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 30);
        panel_footer.add(jbtnSaveAndNew, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 50, 0);
        panel_toppanel.add(panel_footer, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        getContentPane().add(panel_toppanel, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JButton jbtnAddSubject;
    private javax.swing.JButton jbtnCancel;
    private javax.swing.JButton jbtnClear;
    private javax.swing.JButton jbtnRemove;
    private javax.swing.JButton jbtnSave;
    private javax.swing.JButton jbtnSaveAndNew;
    private javax.swing.JComboBox<String> jcmbStatus;
    private javax.swing.JLabel jlblStatus;
    private javax.swing.JTextArea jtaDescription;
    private javax.swing.JTable jtblAssignedSubjects;
    private javax.swing.JTable jtblSubjectMasterList;
    private javax.swing.JTextField jtfCategoryName;
    private javax.swing.JLabel lbl_subcode;
    private javax.swing.JLabel lbl_subname;
    private javax.swing.JPanel panel_centercontrol;
    private javax.swing.JPanel panel_footer;
    private javax.swing.JPanel panel_subjectdetails;
    private javax.swing.JPanel panel_subjectdetails1;
    private javax.swing.JPanel panel_toppanel;
    // End of variables declaration//GEN-END:variables
}
