package view.subject;

import component_model_loader.GradeLevelJCompModelLoader;
import component_model_loader.SubjectJCompModelLoader;
import component_renderers.Renderer_GradeLevel_JComboBox;
import controller.global.Controller_JButton_ExitJDialog;
import controller.subject.CreateSubject;
import controller.subject.EditSubject;
import daoimpl.GradeLevelDaoImpl;
import daoimpl.SubjectDaoImpl;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import model.subject.Subject;
import utility.initializer.Initializer;


public class DialogSubjectCrud extends javax.swing.JDialog implements Initializer{

    private final String action;
    private int subjectIdOfSelected;
    private SubjectJCompModelLoader subjectJCompoCompModelLoader;
    private Renderer_GradeLevel_JComboBox gradeLevelJComboBoxRenderer;
    private GradeLevelJCompModelLoader gradeLevelJCompModelLoader;
    private GradeLevelDaoImpl gradeLevelDaoImpl;
    private SubjectDaoImpl subjectDaoImpl;
    private DefaultComboBoxModel dcmGradeLevel;
    
    public DialogSubjectCrud(java.awt.Frame parent, boolean modal,String action) {
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
     * Use this constructor during VIEW or EDIT action. 
     * This constructor requires a subjectId of what was selected to be supplied
     * @param parent
     * @param modal
     * @param action
     * @param subjectIdOfSelected 
     */
    public DialogSubjectCrud(java.awt.Frame parent, boolean modal,String action, int subjectIdOfSelected) {
        super(parent, modal);
        this.action = action;
        this.subjectIdOfSelected = subjectIdOfSelected;
        initComponents();
        
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
        gradeLevelJComboBoxRenderer = new Renderer_GradeLevel_JComboBox();
    }
    
    @Override
    public void initGridBagConstraints() {
    }

    @Override
    public void initJCompModelLoaders() {
        subjectJCompoCompModelLoader = new SubjectJCompModelLoader();
        gradeLevelJCompModelLoader = new GradeLevelJCompModelLoader();
    }

    @Override
    public void initModels() {
        dcmGradeLevel = gradeLevelJCompModelLoader.getAllGradeLevels();
    }

    /**
     * This sets up the state and visibility of components once this JDialog is displayed on screen.
     */
    @Override
    public void initViewComponents() {
        jcmbGradeLevel.setModel(dcmGradeLevel);
        jcmbGradeLevel.setRenderer(gradeLevelJComboBoxRenderer);
        
        if(action.equalsIgnoreCase("create")){
            jlblStatus.setVisible(false);
            jcmbStatus.setVisible(false);
        }else if(action.equalsIgnoreCase("edit")){
            
        }else if(action.equalsIgnoreCase("view")){
            jtaSubjectDescription.setEnabled(false);
            jtfSubjectCode.setEnabled(false);
            jtfSubjectName.setEnabled(false);
            jcmbGradeLevel.setEnabled(false);
            jcmbStatus.setEnabled(false);
            jbtnClear.setVisible(false);
        }
    }

    @Override
    public void initControllers() {
        jbtnCancel.addActionListener(new Controller_JButton_ExitJDialog(this));
        if (action.equalsIgnoreCase("create")) {
            jbtnSave.addActionListener(new CreateSubject(this));
        } else if (action.equalsIgnoreCase("edit")) {
            jbtnSave.addActionListener(new EditSubject(
                    subjectIdOfSelected, jtfSubjectCode, jtfSubjectName,
                    jcmbStatus, jcmbGradeLevel, jtaSubjectDescription,this)
            );
        }
    }

    @Override
    public void initDaoImpl() {
        gradeLevelDaoImpl = new GradeLevelDaoImpl();
        subjectDaoImpl = new SubjectDaoImpl();
    }
    
    /**
     * Use this method to initialize form during Edit or View actions
     */
    private void initForm(){
        Subject subject = subjectDaoImpl.getSubjectInfoById(subjectIdOfSelected);
        jtfSubjectCode.setText(subject.getSubjectCode());
        jtfSubjectName.setText(subject.getSubjectTitle());
        jtaSubjectDescription.setText(subject.getSubjectDescription());
        jcmbGradeLevel.setSelectedItem(subject.getGradeLevel().getLevelNo());
        jcmbStatus.setSelectedItem(subject.isIsActive()==true?"Yes":"No");
    }

    public JScrollPane getjScrollPane1() {
        return jScrollPane1;
    }

    public JButton getJbtnCancel() {
        return jbtnCancel;
    }

    public JButton getJbtnClear() {
        return jbtnClear;
    }

    public JButton getJbtnSave() {
        return jbtnSave;
    }

    public JButton getJbtnSaveAndNew() {
        return jbtnSaveAndNew;
    }

    public JComboBox<String> getJcmbGradeLevel() {
        return jcmbGradeLevel;
    }

    public JComboBox<String> getJcmbStatus() {
        return jcmbStatus;
    }

    public JLabel getJlblStatus() {
        return jlblStatus;
    }

    public JTextArea getJtaSubjectDescription() {
        return jtaSubjectDescription;
    }

    public JTextField getJtfSubjectCode() {
        return jtfSubjectCode;
    }

    public JTextField getJtfSubjectName() {
        return jtfSubjectName;
    }

    public JLabel getLbl_subcode() {
        return lbl_subcode;
    }

    public JLabel getLbl_subcode2() {
        return lbl_subcode2;
    }

    public JLabel getLbl_subname() {
        return lbl_subname;
    }

    public JPanel getPanel_footer() {
        return panel_footer;
    }

    public JPanel getPanel_subjectdetails() {
        return panel_subjectdetails;
    }

    public JPanel getPanel_subjectdetails1() {
        return panel_subjectdetails1;
    }

    public JPanel getPanel_toppanel() {
        return panel_toppanel;
    }
    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        panel_toppanel = new javax.swing.JPanel();
        panel_subjectdetails = new javax.swing.JPanel();
        lbl_subcode = new javax.swing.JLabel();
        jtfSubjectCode = new javax.swing.JTextField();
        lbl_subname = new javax.swing.JLabel();
        jtfSubjectName = new javax.swing.JTextField();
        jlblStatus = new javax.swing.JLabel();
        jcmbStatus = new javax.swing.JComboBox<>();
        lbl_subcode2 = new javax.swing.JLabel();
        jcmbGradeLevel = new javax.swing.JComboBox<>();
        panel_subjectdetails1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtaSubjectDescription = new javax.swing.JTextArea();
        panel_footer = new javax.swing.JPanel();
        jbtnCancel = new javax.swing.JButton();
        jbtnClear = new javax.swing.JButton();
        jbtnSave = new javax.swing.JButton();
        jbtnSaveAndNew = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Create New Subject");
        setMinimumSize(new java.awt.Dimension(555, 380));
        setModal(true);
        setResizable(false);
        getContentPane().setLayout(new java.awt.GridBagLayout());

        panel_toppanel.setMinimumSize(new java.awt.Dimension(555, 380));
        panel_toppanel.setPreferredSize(new java.awt.Dimension(555, 380));
        panel_toppanel.setLayout(new java.awt.GridBagLayout());

        panel_subjectdetails.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Subject Details", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        panel_subjectdetails.setMinimumSize(new java.awt.Dimension(550, 100));
        panel_subjectdetails.setPreferredSize(new java.awt.Dimension(550, 100));
        panel_subjectdetails.setLayout(new java.awt.GridBagLayout());

        lbl_subcode.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbl_subcode.setText("Subject Code :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 10, 0);
        panel_subjectdetails.add(lbl_subcode, gridBagConstraints);

        jtfSubjectCode.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jtfSubjectCode.setMinimumSize(new java.awt.Dimension(150, 25));
        jtfSubjectCode.setPreferredSize(new java.awt.Dimension(150, 25));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 10, 0);
        panel_subjectdetails.add(jtfSubjectCode, gridBagConstraints);

        lbl_subname.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbl_subname.setText("Subject Name :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 15, 10, 0);
        panel_subjectdetails.add(lbl_subname, gridBagConstraints);

        jtfSubjectName.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jtfSubjectName.setMinimumSize(new java.awt.Dimension(150, 25));
        jtfSubjectName.setPreferredSize(new java.awt.Dimension(150, 25));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 10, 5);
        panel_subjectdetails.add(jtfSubjectName, gridBagConstraints);

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

        lbl_subcode2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbl_subcode2.setText("Grade Level :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 0);
        panel_subjectdetails.add(lbl_subcode2, gridBagConstraints);

        jcmbGradeLevel.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jcmbGradeLevel.setMinimumSize(new java.awt.Dimension(50, 25));
        jcmbGradeLevel.setPreferredSize(new java.awt.Dimension(50, 25));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 0);
        panel_subjectdetails.add(jcmbGradeLevel, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        panel_toppanel.add(panel_subjectdetails, gridBagConstraints);

        panel_subjectdetails1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Description", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        panel_subjectdetails1.setMinimumSize(new java.awt.Dimension(550, 170));
        panel_subjectdetails1.setPreferredSize(new java.awt.Dimension(550, 170));
        panel_subjectdetails1.setLayout(new java.awt.GridBagLayout());

        jScrollPane1.setMinimumSize(new java.awt.Dimension(530, 140));
        jScrollPane1.setPreferredSize(new java.awt.Dimension(530, 140));

        jtaSubjectDescription.setColumns(20);
        jtaSubjectDescription.setRows(5);
        jtaSubjectDescription.setMinimumSize(new java.awt.Dimension(530, 140));
        jtaSubjectDescription.setPreferredSize(new java.awt.Dimension(530, 140));
        jScrollPane1.setViewportView(jtaSubjectDescription);

        panel_subjectdetails1.add(jScrollPane1, new java.awt.GridBagConstraints());

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 3);
        panel_toppanel.add(panel_subjectdetails1, gridBagConstraints);

        panel_footer.setMinimumSize(new java.awt.Dimension(550, 50));
        panel_footer.setPreferredSize(new java.awt.Dimension(550, 50));
        panel_footer.setLayout(new java.awt.GridBagLayout());

        jbtnCancel.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jbtnCancel.setText("Cancel");
        jbtnCancel.setMaximumSize(new java.awt.Dimension(80, 40));
        jbtnCancel.setMinimumSize(new java.awt.Dimension(80, 40));
        jbtnCancel.setPreferredSize(new java.awt.Dimension(80, 40));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        panel_footer.add(jbtnCancel, gridBagConstraints);

        jbtnClear.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jbtnClear.setText("Clear");
        jbtnClear.setMaximumSize(new java.awt.Dimension(80, 40));
        jbtnClear.setMinimumSize(new java.awt.Dimension(80, 40));
        jbtnClear.setPreferredSize(new java.awt.Dimension(80, 40));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        panel_footer.add(jbtnClear, gridBagConstraints);

        jbtnSave.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jbtnSave.setText("Save");
        jbtnSave.setMaximumSize(new java.awt.Dimension(80, 40));
        jbtnSave.setMinimumSize(new java.awt.Dimension(80, 40));
        jbtnSave.setPreferredSize(new java.awt.Dimension(80, 40));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        panel_footer.add(jbtnSave, gridBagConstraints);

        jbtnSaveAndNew.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jbtnSaveAndNew.setText("Save & New");
        jbtnSaveAndNew.setMaximumSize(new java.awt.Dimension(120, 40));
        jbtnSaveAndNew.setMinimumSize(new java.awt.Dimension(120, 40));
        jbtnSaveAndNew.setPreferredSize(new java.awt.Dimension(120, 40));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        panel_footer.add(jbtnSaveAndNew, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 30, 0);
        panel_toppanel.add(panel_footer, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        getContentPane().add(panel_toppanel, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jbtnCancel;
    private javax.swing.JButton jbtnClear;
    private javax.swing.JButton jbtnSave;
    private javax.swing.JButton jbtnSaveAndNew;
    private javax.swing.JComboBox<String> jcmbGradeLevel;
    private javax.swing.JComboBox<String> jcmbStatus;
    private javax.swing.JLabel jlblStatus;
    private javax.swing.JTextArea jtaSubjectDescription;
    private javax.swing.JTextField jtfSubjectCode;
    private javax.swing.JTextField jtfSubjectName;
    private javax.swing.JLabel lbl_subcode;
    private javax.swing.JLabel lbl_subcode2;
    private javax.swing.JLabel lbl_subname;
    private javax.swing.JPanel panel_footer;
    private javax.swing.JPanel panel_subjectdetails;
    private javax.swing.JPanel panel_subjectdetails1;
    private javax.swing.JPanel panel_toppanel;
    // End of variables declaration//GEN-END:variables
}
