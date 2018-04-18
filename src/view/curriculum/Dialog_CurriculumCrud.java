package view.curriculum;

import component_model_loader.CurriculumJCompModelLoader;
import component_model_loader.GradeLevelJCompModelLoader;
import component_renderers.Renderer_GradeLevel_JComboBox;
import controller.curriculum.CreateCurriculum;
import controller.curriculum.CurrentSubjectsTableModelListener;
import controller.curriculum.EditCurriculum;
import controller.curriculum.LoadSubjectsToTableByGradeLevelStateChange;
import controller.curriculum.MoveSelectedSubjectsToCurrentCurriculumSubjects;
import controller.curriculum.RemoveSelectedSubjectsFromCurrent;
import controller.global.Controller_JButton_ExitJDialog;
import daoimpl.CurriculumDaoImpl;
import daoimpl.GradeLevelDaoImpl;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import model.curriculum.Curriculum;
import renderer.curriculum.Renderer_Subject_GradeLevel_TableCell;
import tablecelleditor.curriculum.SubjectHourColumnCellEditor;
import utility.initializer.Initializer;

public class Dialog_CurriculumCrud extends javax.swing.JDialog implements Initializer{

    private int curriculumIdOfSelected;
    private String action;
    private CurriculumDaoImpl curriculumDaoImpl;
    private CurriculumJCompModelLoader curriculumJCompModelLoader;
    private GradeLevelDaoImpl gradeLevelDaoImpl;
    private Renderer_GradeLevel_JComboBox gradeLevelJComboBoxRenderer;
    private GradeLevelJCompModelLoader gradeLevelJCompModelLoader;
    
    public Dialog_CurriculumCrud(java.awt.Frame parent, boolean modal, String action) {
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
     * Use this constructor when doing VIEW or EDIT operation
     * The curriculumId of what was selected in the master record JTable of curriculum
     * must be supplied
     * @param parent
     * @param modal
     * @param action
     * @param curriculumIdOfSelected 
     */
    public Dialog_CurriculumCrud(java.awt.Frame parent, boolean modal, String action, int curriculumIdOfSelected) {
        super(parent, modal);
        initComponents();
        
        this.curriculumIdOfSelected = curriculumIdOfSelected;
        this.action = action;
        
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
        jtblSubjectList.setDefaultRenderer(Object.class, new Renderer_Subject_GradeLevel_TableCell(3));
        jtblCurrentSubjects.setDefaultRenderer(Object.class, new Renderer_Subject_GradeLevel_TableCell(3));
    }
    
    @Override
    public void initGridBagConstraints() {
    }

    @Override
    public void initJCompModelLoaders() {
        curriculumJCompModelLoader = new CurriculumJCompModelLoader();
        gradeLevelJCompModelLoader = new GradeLevelJCompModelLoader();
    }

    @Override
    public void initModels() {
    }

    @Override
    public void initViewComponents() {
//        TableColumnModel tableColModel = jtblCurrentSubjects.getColumnModel();
//        TableColumn subjectHoursColumn = tableColModel.getColumn(4);
//        subjectHoursColumn.setCellEditor(new SubjectHourColumnCellEditor());
        
        jcmbGradeLevel.setModel(gradeLevelJCompModelLoader.getAllGradeLevels());
        jcmbGradeLevel.setRenderer(gradeLevelJComboBoxRenderer);
        
        if(action.equalsIgnoreCase("create")){
            jlblStatus.setVisible(false);
            jcmbStatus.setVisible(false);
            jlblTotalMinutes.setText("");
        }else if(action.equalsIgnoreCase("edit")){
            
        }else if(action.equalsIgnoreCase("view")){
            jbtnClear.setVisible(false);
            jbtnSave.setVisible(false);
            jbtnSaveAndNew.setVisible(false);
            jtblCurrentSubjects.setEnabled(false);
            jtblSubjectList.setEnabled(false);
            jtfCurriculumName.setEnabled(false);
            jtaCurriculumDescription.setEnabled(false);
            jcmbStatus.setEnabled(false);
        }else if(action.equalsIgnoreCase("print")){
            
        }
    }

    @Override
    public void initControllers() {

        jbtnRemoveSubjects.addActionListener(new RemoveSelectedSubjectsFromCurrent(jtblCurrentSubjects, jtblSubjectList));
        jbtnMoveSubjects.addActionListener(new MoveSelectedSubjectsToCurrentCurriculumSubjects(jtblSubjectList, jtblCurrentSubjects));
        jcmbGradeLevel.addItemListener(new LoadSubjectsToTableByGradeLevelStateChange(jcmbGradeLevel, jtblSubjectList, jtblCurrentSubjects));
        jbtnCancel.addActionListener(new Controller_JButton_ExitJDialog(this));
        if (action.equalsIgnoreCase("create")) {
            jbtnSave.addActionListener(new CreateCurriculum(jtfCurriculumName, jtaCurriculumDescription, jtblCurrentSubjects));
        }
        if (action.equalsIgnoreCase("edit")) {
            jbtnSave.addActionListener(new EditCurriculum(
                    curriculumIdOfSelected, jtfCurriculumName,
                    jtaCurriculumDescription, jcmbStatus,
                    jtblCurrentSubjects)
            );
        }

        jtblCurrentSubjects.getModel()
                .addTableModelListener(new CurrentSubjectsTableModelListener(jlblTotalMinutes,jlblTotalHrs, jtblCurrentSubjects));
    }

    @Override
    public void initDaoImpl() {
        curriculumDaoImpl = new CurriculumDaoImpl();
        gradeLevelDaoImpl = new GradeLevelDaoImpl();
    }
    
    private void initForm(){
        Curriculum c = curriculumDaoImpl.getCurriculumById(curriculumIdOfSelected);
        jcmbStatus.setSelectedItem(c.getIsActive()==true?"Yes":"No");
        jtfCurriculumName.setText(c.getTitle().trim());
        jtaCurriculumDescription.setText(c.getDescription());
        jtblCurrentSubjects.setModel(curriculumJCompModelLoader.getCurriculumSubjectsById(curriculumIdOfSelected, jtblCurrentSubjects));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        panel_toopanel = new javax.swing.JPanel();
        panel_details = new javax.swing.JPanel();
        lbl_name = new javax.swing.JLabel();
        jtfCurriculumName = new javax.swing.JTextField();
        jlblStatus = new javax.swing.JLabel();
        jcmbStatus = new javax.swing.JComboBox<>();
        lbl_name3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtaCurriculumDescription = new javax.swing.JTextArea();
        panel_subjectlist = new javax.swing.JPanel();
        panel_filter = new javax.swing.JPanel();
        jlblGradeLevel = new javax.swing.JLabel();
        jcmbGradeLevel = new javax.swing.JComboBox<>();
        panel_subjtable = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtblSubjectList = new javax.swing.JTable();
        panel_centercontrol = new javax.swing.JPanel();
        jbtnMoveSubjects = new javax.swing.JButton();
        jbtnRemoveSubjects = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        panel_currentsubjects = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jtblCurrentSubjects = new javax.swing.JTable();
        panel_footer = new javax.swing.JPanel();
        jbtnCancel = new javax.swing.JButton();
        jbtnClear = new javax.swing.JButton();
        jbtnSave = new javax.swing.JButton();
        jbtnSaveAndNew = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jlblTotalMinutes = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jlblTotalHrs = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Create New Curriculum");
        setMinimumSize(new java.awt.Dimension(1100, 700));
        setModal(true);
        setPreferredSize(new java.awt.Dimension(1100, 700));
        setResizable(false);
        getContentPane().setLayout(new java.awt.GridBagLayout());

        panel_toopanel.setMinimumSize(new java.awt.Dimension(800, 600));
        panel_toopanel.setPreferredSize(new java.awt.Dimension(800, 600));
        panel_toopanel.setLayout(new java.awt.GridBagLayout());

        panel_details.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Curriculum Details", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        panel_details.setMinimumSize(new java.awt.Dimension(798, 120));
        panel_details.setPreferredSize(new java.awt.Dimension(798, 120));
        panel_details.setLayout(new java.awt.GridBagLayout());

        lbl_name.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbl_name.setText("Curriculum Name :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 0, 5);
        panel_details.add(lbl_name, gridBagConstraints);

        jtfCurriculumName.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jtfCurriculumName.setMinimumSize(new java.awt.Dimension(200, 25));
        jtfCurriculumName.setPreferredSize(new java.awt.Dimension(200, 25));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 0, 0);
        panel_details.add(jtfCurriculumName, gridBagConstraints);

        jlblStatus.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jlblStatus.setText("Active :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(3, 0, 0, 5);
        panel_details.add(jlblStatus, gridBagConstraints);

        jcmbStatus.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jcmbStatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Yes", "No" }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 0, 0, 0);
        panel_details.add(jcmbStatus, gridBagConstraints);

        lbl_name3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbl_name3.setText("Description :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(0, 50, 0, 5);
        panel_details.add(lbl_name3, gridBagConstraints);

        jScrollPane1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jScrollPane1.setMinimumSize(new java.awt.Dimension(300, 80));
        jScrollPane1.setPreferredSize(new java.awt.Dimension(300, 80));

        jtaCurriculumDescription.setColumns(20);
        jtaCurriculumDescription.setRows(5);
        jtaCurriculumDescription.setMinimumSize(new java.awt.Dimension(100, 100));
        jtaCurriculumDescription.setPreferredSize(new java.awt.Dimension(100, 100));
        jScrollPane1.setViewportView(jtaCurriculumDescription);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 3;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 10, 0);
        panel_details.add(jScrollPane1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        panel_toopanel.add(panel_details, gridBagConstraints);

        panel_subjectlist.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Subject List", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        panel_subjectlist.setMinimumSize(new java.awt.Dimension(375, 400));
        panel_subjectlist.setPreferredSize(new java.awt.Dimension(375, 400));
        panel_subjectlist.setLayout(new java.awt.GridBagLayout());

        panel_filter.setMinimumSize(new java.awt.Dimension(360, 35));
        panel_filter.setPreferredSize(new java.awt.Dimension(360, 35));
        panel_filter.setLayout(new java.awt.GridBagLayout());

        jlblGradeLevel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jlblGradeLevel.setText("Show Grade Level :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(10, 5, 10, 0);
        panel_filter.add(jlblGradeLevel, gridBagConstraints);

        jcmbGradeLevel.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jcmbGradeLevel.setMinimumSize(new java.awt.Dimension(100, 25));
        jcmbGradeLevel.setPreferredSize(new java.awt.Dimension(100, 25));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 7.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 10, 0);
        panel_filter.add(jcmbGradeLevel, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        panel_subjectlist.add(panel_filter, gridBagConstraints);

        panel_subjtable.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        panel_subjtable.setMinimumSize(new java.awt.Dimension(360, 340));
        panel_subjtable.setPreferredSize(new java.awt.Dimension(360, 340));
        panel_subjtable.setLayout(new java.awt.GridBagLayout());

        jScrollPane2.setMinimumSize(new java.awt.Dimension(350, 330));
        jScrollPane2.setPreferredSize(new java.awt.Dimension(350, 330));

        jtblSubjectList.setAutoCreateRowSorter(true);
        jtblSubjectList.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jtblSubjectList.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Subject Id", "Name", "Code", "Grade Level"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtblSubjectList.setMinimumSize(new java.awt.Dimension(350, 330));
        jtblSubjectList.setPreferredSize(new java.awt.Dimension(350, 330));
        jtblSubjectList.setRowHeight(20);
        jtblSubjectList.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(jtblSubjectList);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        panel_subjtable.add(jScrollPane2, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        panel_subjectlist.add(panel_subjtable, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        panel_toopanel.add(panel_subjectlist, gridBagConstraints);

        panel_centercontrol.setMinimumSize(new java.awt.Dimension(56, 400));
        panel_centercontrol.setLayout(new java.awt.GridBagLayout());

        jbtnMoveSubjects.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jbtnMoveSubjects.setText(">");
        jbtnMoveSubjects.setMaximumSize(new java.awt.Dimension(50, 40));
        jbtnMoveSubjects.setMinimumSize(new java.awt.Dimension(50, 40));
        jbtnMoveSubjects.setPreferredSize(new java.awt.Dimension(50, 40));
        jbtnMoveSubjects.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnMoveSubjectsActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        panel_centercontrol.add(jbtnMoveSubjects, gridBagConstraints);

        jbtnRemoveSubjects.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jbtnRemoveSubjects.setText("<");
        jbtnRemoveSubjects.setMaximumSize(new java.awt.Dimension(50, 40));
        jbtnRemoveSubjects.setMinimumSize(new java.awt.Dimension(50, 40));
        jbtnRemoveSubjects.setPreferredSize(new java.awt.Dimension(50, 40));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        panel_centercontrol.add(jbtnRemoveSubjects, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        panel_toopanel.add(panel_centercontrol, gridBagConstraints);

        jScrollPane4.setMinimumSize(new java.awt.Dimension(375, 400));
        jScrollPane4.setPreferredSize(new java.awt.Dimension(375, 400));

        panel_currentsubjects.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Current Subjects", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        panel_currentsubjects.setMinimumSize(new java.awt.Dimension(375, 400));
        panel_currentsubjects.setPreferredSize(new java.awt.Dimension(375, 400));
        panel_currentsubjects.setLayout(new java.awt.GridBagLayout());

        jScrollPane3.setMinimumSize(new java.awt.Dimension(360, 375));
        jScrollPane3.setPreferredSize(new java.awt.Dimension(360, 375));

        jtblCurrentSubjects.setAutoCreateRowSorter(true);
        jtblCurrentSubjects.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jtblCurrentSubjects.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Subject Id", "Name", "Code", "Grade Level", "mins / week"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtblCurrentSubjects.setRowHeight(20);
        jtblCurrentSubjects.getTableHeader().setReorderingAllowed(false);
        jScrollPane3.setViewportView(jtblCurrentSubjects);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        panel_currentsubjects.add(jScrollPane3, gridBagConstraints);

        jScrollPane4.setViewportView(panel_currentsubjects);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        panel_toopanel.add(jScrollPane4, gridBagConstraints);

        panel_footer.setMinimumSize(new java.awt.Dimension(798, 50));
        panel_footer.setPreferredSize(new java.awt.Dimension(798, 50));
        panel_footer.setLayout(new java.awt.GridBagLayout());

        jbtnCancel.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jbtnCancel.setText("Cancel");
        jbtnCancel.setMaximumSize(new java.awt.Dimension(120, 40));
        jbtnCancel.setMinimumSize(new java.awt.Dimension(120, 40));
        jbtnCancel.setPreferredSize(new java.awt.Dimension(120, 30));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        panel_footer.add(jbtnCancel, gridBagConstraints);

        jbtnClear.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jbtnClear.setText("Clear");
        jbtnClear.setMaximumSize(new java.awt.Dimension(120, 40));
        jbtnClear.setMinimumSize(new java.awt.Dimension(120, 40));
        jbtnClear.setPreferredSize(new java.awt.Dimension(120, 30));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        panel_footer.add(jbtnClear, gridBagConstraints);

        jbtnSave.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jbtnSave.setText("Save");
        jbtnSave.setMaximumSize(new java.awt.Dimension(120, 40));
        jbtnSave.setMinimumSize(new java.awt.Dimension(120, 40));
        jbtnSave.setPreferredSize(new java.awt.Dimension(120, 30));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        panel_footer.add(jbtnSave, gridBagConstraints);

        jbtnSaveAndNew.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jbtnSaveAndNew.setText("Save & New");
        jbtnSaveAndNew.setMaximumSize(new java.awt.Dimension(120, 40));
        jbtnSaveAndNew.setMinimumSize(new java.awt.Dimension(120, 40));
        jbtnSaveAndNew.setPreferredSize(new java.awt.Dimension(120, 30));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        panel_footer.add(jbtnSaveAndNew, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        panel_toopanel.add(panel_footer, gridBagConstraints);

        jPanel1.setLayout(new java.awt.GridBagLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setText("Total (mins):");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel1.add(jLabel1, gridBagConstraints);

        jlblTotalMinutes.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jlblTotalMinutes.setText("totalInMins");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel1.add(jlblTotalMinutes, gridBagConstraints);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setText("Total (hrs) :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel1.add(jLabel2, gridBagConstraints);

        jlblTotalHrs.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jlblTotalHrs.setText("totalInHrs");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel1.add(jlblTotalHrs, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        panel_toopanel.add(jPanel1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        getContentPane().add(panel_toopanel, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbtnMoveSubjectsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnMoveSubjectsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jbtnMoveSubjectsActionPerformed

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JButton jbtnCancel;
    private javax.swing.JButton jbtnClear;
    private javax.swing.JButton jbtnMoveSubjects;
    private javax.swing.JButton jbtnRemoveSubjects;
    private javax.swing.JButton jbtnSave;
    private javax.swing.JButton jbtnSaveAndNew;
    private javax.swing.JComboBox<String> jcmbGradeLevel;
    private javax.swing.JComboBox<String> jcmbStatus;
    private javax.swing.JLabel jlblGradeLevel;
    private javax.swing.JLabel jlblStatus;
    private javax.swing.JLabel jlblTotalHrs;
    private javax.swing.JLabel jlblTotalMinutes;
    private javax.swing.JTextArea jtaCurriculumDescription;
    private javax.swing.JTable jtblCurrentSubjects;
    private javax.swing.JTable jtblSubjectList;
    private javax.swing.JTextField jtfCurriculumName;
    private javax.swing.JLabel lbl_name;
    private javax.swing.JLabel lbl_name3;
    private javax.swing.JPanel panel_centercontrol;
    private javax.swing.JPanel panel_currentsubjects;
    private javax.swing.JPanel panel_details;
    private javax.swing.JPanel panel_filter;
    private javax.swing.JPanel panel_footer;
    private javax.swing.JPanel panel_subjectlist;
    private javax.swing.JPanel panel_subjtable;
    private javax.swing.JPanel panel_toopanel;
    // End of variables declaration//GEN-END:variables
}
