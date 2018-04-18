package view.section;

import component_model_loader.FacultyJCompModelLoader;
import component_model_loader.GradeLevelJCompModelLoader;
import component_model_loader.SectionJCompModelLoader;
import component_renderers.Renderer_Faculty_JComboBox;
import component_renderers.Renderer_GradeLevel_JComboBox;
import component_renderers.Renderer_SectionType_JComboBox;
import component_renderers.Renderer_Section_Session_JComboBox;
import controller.global.Controller_JButton_ExitJDialog;
import controller.section.CreateSection;
import controller.section.UpdateSection;
import daoimpl.SchoolYearDaoImpl;
import daoimpl.SectionDaoImpl;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import model.schoolyear.SchoolYear;
import model.section.Section;
import utility.initializer.Initializer;

public class DialogSectionCrud extends javax.swing.JDialog implements Initializer {

    private final Panel_Sections panelSections;
    
    private int sectionIdOfSelected;
    private final String action;
    private final SchoolYear currentSchoolYear;
    private SectionJCompModelLoader sectionJCompModelLoader;

    private Renderer_GradeLevel_JComboBox gradeLevelJComboBoxRenderer;
    private GradeLevelJCompModelLoader gradeLevelJCompModelLoader;
    private FacultyJCompModelLoader facultyJCompModelLoader;
    private Renderer_Faculty_JComboBox facultyJComboBoxRenderer;
    
    private SectionDaoImpl sectionDaoImpl;

    public DialogSectionCrud(java.awt.Frame parent, boolean modal,Panel_Sections panelSections, String action, SchoolYear currentSchoolYear) {
        super(parent, modal);
        initComponents();
        this.panelSections = panelSections;
        
        this.action = action;
        this.currentSchoolYear = currentSchoolYear;

        initDaoImpl();
        initJCompModelLoaders();
        initRenderers();
        initModels();
        initViewComponents();
        initControllers();
        
    }

    /**
     * Use this constructor during VIEW or EDIT operations. This requires a
     * sectionId of what was selected in record to be supplied.
     *
     * @param parent
     * @param model
     * @param action
     * @param sectionIdOfSelected
     */
    public DialogSectionCrud(java.awt.Frame parent, boolean model,Panel_Sections panelSections, String action, int sectionIdOfSelected, SchoolYear currentSchoolYear) {
        super(parent, model);
        initComponents();
        this.panelSections = panelSections;
        
        this.action = action;
        this.sectionIdOfSelected = sectionIdOfSelected;
        this.currentSchoolYear = currentSchoolYear;

        initDaoImpl();
        initJCompModelLoaders();
        initRenderers();
        initModels();
        initViewComponents();
        initControllers();
        
        initForm();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        panel_toppanel = new javax.swing.JPanel();
        jpnlSectionDetails = new javax.swing.JPanel();
        jlblSectionName = new javax.swing.JLabel();
        jtfSectionName = new javax.swing.JTextField();
        jlblGradeLevel = new javax.swing.JLabel();
        jcmbGradeLevel = new javax.swing.JComboBox<>();
        jlblSession = new javax.swing.JLabel();
        jcmbSession = new javax.swing.JComboBox<>();
        jlblAdviser = new javax.swing.JLabel();
        jcmbAdviser = new javax.swing.JComboBox<>();
        jlblStatus = new javax.swing.JLabel();
        jcmbStatus = new javax.swing.JComboBox<>();
        jlblCapacity = new javax.swing.JLabel();
        jtfCapacity = new javax.swing.JTextField();
        jlblSectionType = new javax.swing.JLabel();
        jcmbSectionType = new javax.swing.JComboBox<>();
        jpnlFooter = new javax.swing.JPanel();
        jbtnCancel = new javax.swing.JButton();
        jbtnClear = new javax.swing.JButton();
        jbtnSave = new javax.swing.JButton();
        jbtnSaveAndNew = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Create New Section");
        setMinimumSize(new java.awt.Dimension(620, 260));
        setModal(true);
        setResizable(false);
        getContentPane().setLayout(new java.awt.GridBagLayout());

        panel_toppanel.setMinimumSize(new java.awt.Dimension(555, 270));
        panel_toppanel.setPreferredSize(new java.awt.Dimension(555, 270));
        panel_toppanel.setLayout(new java.awt.GridBagLayout());

        jpnlSectionDetails.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Section Details", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        jpnlSectionDetails.setMinimumSize(new java.awt.Dimension(550, 150));
        jpnlSectionDetails.setPreferredSize(new java.awt.Dimension(550, 150));
        jpnlSectionDetails.setLayout(new java.awt.GridBagLayout());

        jlblSectionName.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jlblSectionName.setText("Section Name :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jpnlSectionDetails.add(jlblSectionName, gridBagConstraints);

        jtfSectionName.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jtfSectionName.setMinimumSize(new java.awt.Dimension(150, 25));
        jtfSectionName.setPreferredSize(new java.awt.Dimension(150, 25));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jpnlSectionDetails.add(jtfSectionName, gridBagConstraints);

        jlblGradeLevel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jlblGradeLevel.setText("Grade Level :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jpnlSectionDetails.add(jlblGradeLevel, gridBagConstraints);

        jcmbGradeLevel.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jcmbGradeLevel.setMinimumSize(new java.awt.Dimension(100, 25));
        jcmbGradeLevel.setPreferredSize(new java.awt.Dimension(100, 25));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jpnlSectionDetails.add(jcmbGradeLevel, gridBagConstraints);

        jlblSession.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jlblSession.setText("Session :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jpnlSectionDetails.add(jlblSession, gridBagConstraints);

        jcmbSession.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jcmbSession.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "AM", "PM", "WD" }));
        jcmbSession.setSelectedIndex(-1);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jpnlSectionDetails.add(jcmbSession, gridBagConstraints);

        jlblAdviser.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jlblAdviser.setText("Adviser :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jpnlSectionDetails.add(jlblAdviser, gridBagConstraints);

        jcmbAdviser.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jcmbAdviser.setMinimumSize(new java.awt.Dimension(180, 25));
        jcmbAdviser.setPreferredSize(new java.awt.Dimension(180, 25));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jpnlSectionDetails.add(jcmbAdviser, gridBagConstraints);

        jlblStatus.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jlblStatus.setText("Active :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jpnlSectionDetails.add(jlblStatus, gridBagConstraints);

        jcmbStatus.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jcmbStatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Yes", "No" }));
        jcmbStatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcmbStatusActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jpnlSectionDetails.add(jcmbStatus, gridBagConstraints);

        jlblCapacity.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jlblCapacity.setText("Capacity :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jpnlSectionDetails.add(jlblCapacity, gridBagConstraints);

        jtfCapacity.setColumns(4);
        jtfCapacity.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jpnlSectionDetails.add(jtfCapacity, gridBagConstraints);

        jlblSectionType.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jlblSectionType.setText("Section Type :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jpnlSectionDetails.add(jlblSectionType, gridBagConstraints);

        jcmbSectionType.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jcmbSectionType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "R", "S" }));
        jcmbSectionType.setSelectedIndex(-1);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jpnlSectionDetails.add(jcmbSectionType, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        panel_toppanel.add(jpnlSectionDetails, gridBagConstraints);

        jpnlFooter.setMinimumSize(new java.awt.Dimension(550, 50));
        jpnlFooter.setPreferredSize(new java.awt.Dimension(550, 50));
        jpnlFooter.setLayout(new java.awt.GridBagLayout());

        jbtnCancel.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jbtnCancel.setText("Cancel");
        jbtnCancel.setActionCommand("cancel");
        jbtnCancel.setMaximumSize(new java.awt.Dimension(80, 40));
        jbtnCancel.setMinimumSize(new java.awt.Dimension(80, 40));
        jbtnCancel.setPreferredSize(new java.awt.Dimension(80, 40));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.weightx = 0.5;
        jpnlFooter.add(jbtnCancel, gridBagConstraints);

        jbtnClear.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jbtnClear.setText("Clear");
        jbtnClear.setActionCommand("clear");
        jbtnClear.setMaximumSize(new java.awt.Dimension(80, 40));
        jbtnClear.setMinimumSize(new java.awt.Dimension(80, 40));
        jbtnClear.setPreferredSize(new java.awt.Dimension(80, 40));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        jpnlFooter.add(jbtnClear, gridBagConstraints);

        jbtnSave.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jbtnSave.setText("Save");
        jbtnSave.setActionCommand("save");
        jbtnSave.setMaximumSize(new java.awt.Dimension(80, 40));
        jbtnSave.setMinimumSize(new java.awt.Dimension(80, 40));
        jbtnSave.setPreferredSize(new java.awt.Dimension(80, 40));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        jpnlFooter.add(jbtnSave, gridBagConstraints);

        jbtnSaveAndNew.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jbtnSaveAndNew.setText("Save & New");
        jbtnSaveAndNew.setActionCommand("save_and_new");
        jbtnSaveAndNew.setMaximumSize(new java.awt.Dimension(120, 40));
        jbtnSaveAndNew.setMinimumSize(new java.awt.Dimension(120, 40));
        jbtnSaveAndNew.setPreferredSize(new java.awt.Dimension(120, 40));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 30);
        jpnlFooter.add(jbtnSaveAndNew, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        panel_toppanel.add(jpnlFooter, gridBagConstraints);

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

    private void jcmbStatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcmbStatusActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jcmbStatusActionPerformed

    @Override
    public void initRenderers() {
        jcmbSectionType.setRenderer(new Renderer_SectionType_JComboBox());
//        jcmbSession.setRenderer(new Renderer_Section_Session_JComboBox());
        gradeLevelJComboBoxRenderer = new Renderer_GradeLevel_JComboBox();
        facultyJComboBoxRenderer = new Renderer_Faculty_JComboBox();

        jcmbGradeLevel.setRenderer(gradeLevelJComboBoxRenderer);
        jcmbAdviser.setRenderer(facultyJComboBoxRenderer);
    }

    @Override
    public void initGridBagConstraints() {
    }

    @Override
    public void initJCompModelLoaders() {
        sectionJCompModelLoader = new SectionJCompModelLoader();
        gradeLevelJCompModelLoader = new GradeLevelJCompModelLoader();
        facultyJCompModelLoader = new FacultyJCompModelLoader();
    }

    @Override
    public void initModels() {
    }

    @Override
    public void initViewComponents() {
        jcmbGradeLevel.setModel(gradeLevelJCompModelLoader.getAllGradeLevels());
        jcmbAdviser.setModel(facultyJCompModelLoader.getAllFacultyIdHandlingAdvisory(currentSchoolYear));

        if (action.equalsIgnoreCase("create")) {
            jcmbStatus.setVisible(false);
            jlblStatus.setVisible(false);
        } else if (action.equalsIgnoreCase("edit")) {

        } else if (action.equalsIgnoreCase("view")) {
            jtfSectionName.setEnabled(false);
            jcmbGradeLevel.setEnabled(false);
            jcmbAdviser.setEnabled(false);
            jcmbSession.setEnabled(false);
            jcmbStatus.setEnabled(false);
        }
    }

    @Override
    public void initControllers() {
        jbtnCancel.addActionListener(new Controller_JButton_ExitJDialog(this));
        if (action.equalsIgnoreCase("create")) {
            jbtnSave.addActionListener(new CreateSection(panelSections,this,currentSchoolYear));
        } else if (action.equalsIgnoreCase("edit")) {
            jbtnSave.addActionListener(new UpdateSection(sectionIdOfSelected,panelSections, this,currentSchoolYear));
        }
    }

    @Override
    public void initDaoImpl() {
        sectionDaoImpl = new SectionDaoImpl();
    }

    private void initForm() {
        Section section = sectionDaoImpl.getSectionById(sectionIdOfSelected);
        jtfSectionName.setText(section.getSectionName());
        jcmbGradeLevel.setSelectedItem(section.getGradeLevel().getLevelNo());
        jcmbSession.setSelectedItem(section.getSectionSession());
        jcmbStatus.setSelectedItem(section.getIsActive() == true ? "Yes" : "No");
        jtfCapacity.setText(""+section.getCapacity());
        jcmbAdviser.setSelectedItem(section.getAdviser().getFacultyID());
        jcmbSectionType.setSelectedItem(section.getSectionType().trim());
    }

    public JComboBox<String> getJcmbSectionType() {
        return jcmbSectionType;
    }

    public JLabel getJlblSectionType() {
        return jlblSectionType;
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

    public JComboBox<String> getJcmbAdviser() {
        return jcmbAdviser;
    }

    public JComboBox<String> getJcmbGradeLevel() {
        return jcmbGradeLevel;
    }

    public JComboBox<String> getJcmbSession() {
        return jcmbSession;
    }

    public JComboBox<String> getJcmbStatus() {
        return jcmbStatus;
    }

    public JLabel getJlblAdviser() {
        return jlblAdviser;
    }

    public JLabel getJlblGradeLevel() {
        return jlblGradeLevel;
    }

    public JLabel getJlblSectionName() {
        return jlblSectionName;
    }

    public JLabel getJlblSession() {
        return jlblSession;
    }

    public JLabel getJlblStatus() {
        return jlblStatus;
    }

    public JPanel getJpnlFooter() {
        return jpnlFooter;
    }

    public JPanel getJpnlSectionDetails() {
        return jpnlSectionDetails;
    }

    public JTextField getJtfSectionName() {
        return jtfSectionName;
    }

    public JPanel getPanel_toppanel() {
        return panel_toppanel;
    }

    public JLabel getJlblCapacity() {
        return jlblCapacity;
    }

    public JTextField getJtfCapacity() {
        return jtfCapacity;
    }

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jbtnCancel;
    private javax.swing.JButton jbtnClear;
    private javax.swing.JButton jbtnSave;
    private javax.swing.JButton jbtnSaveAndNew;
    private javax.swing.JComboBox<String> jcmbAdviser;
    private javax.swing.JComboBox<String> jcmbGradeLevel;
    private javax.swing.JComboBox<String> jcmbSectionType;
    private javax.swing.JComboBox<String> jcmbSession;
    private javax.swing.JComboBox<String> jcmbStatus;
    private javax.swing.JLabel jlblAdviser;
    private javax.swing.JLabel jlblCapacity;
    private javax.swing.JLabel jlblGradeLevel;
    private javax.swing.JLabel jlblSectionName;
    private javax.swing.JLabel jlblSectionType;
    private javax.swing.JLabel jlblSession;
    private javax.swing.JLabel jlblStatus;
    private javax.swing.JPanel jpnlFooter;
    private javax.swing.JPanel jpnlSectionDetails;
    private javax.swing.JTextField jtfCapacity;
    private javax.swing.JTextField jtfSectionName;
    private javax.swing.JPanel panel_toppanel;
    // End of variables declaration//GEN-END:variables
}
