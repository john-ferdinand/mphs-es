package view.faculty;

import component_model_loader.ClassTypeJCompModelLoader;
import component_model_loader.FacultyJCompModelLoader;
import component_model_loader.SubjectCategoryJCompModelLoader;
import component_renderers.Renderer_ClassType_JComboBox;
import controller.faculty.EditFacultyDialogListener;
import daoimpl.ClassTypeDaoImpl;
import daoimpl.FacultyDaoImpl;
import daoimpl.SchoolYearDaoImpl;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import model.faculty.Faculty;
import model.subjectcategory.SubjectCategory;
import utility.initializer.Initializer;

public class Dialog_FacultyEdit extends javax.swing.JDialog implements Initializer {

    private ClassTypeDaoImpl classTypeDaoImpl;
    private ClassTypeJCompModelLoader classTypeJCompModelLoader;
    private final Faculty faculty;
    private SubjectCategory subjectCategory;
    private FacultyDaoImpl facultyDaoImpl;
    private SubjectCategoryJCompModelLoader specializationJCompModelLoader;
    private FacultyJCompModelLoader facultyJCompLoader;

    public Dialog_FacultyEdit(java.awt.Frame parent, boolean modal, Faculty f, SubjectCategory subjectCategory) {
        super(parent, modal);
        this.faculty = f;
        this.subjectCategory = subjectCategory;
        initComponents();

        initDaoImpl();
        initJCompModelLoaders();
        initControllers();
        initRenderers();
        initViewComponents();

    }

    @Override
    public void initGridBagConstraints() {
    }

    @Override
    public void initJCompModelLoaders() {
        classTypeJCompModelLoader = new ClassTypeJCompModelLoader();
        specializationJCompModelLoader = new SubjectCategoryJCompModelLoader();
        facultyJCompLoader = new FacultyJCompModelLoader();
    }

    @Override
    public void initRenderers() {
        jcmbClassHandled.setRenderer(new Renderer_ClassType_JComboBox());
    }

    @Override
    public void initModels() {
    }

    @Override
    public void initViewComponents() {

        jcmbClassHandled.setModel(classTypeJCompModelLoader.getAllClassTypeIDsByStatus(true));
        jtblSubjectCateoryList.setModel(specializationJCompModelLoader.getAllSubjectCategoryInfo(jtblSubjectCateoryList));
        jtblCurrentSpecializations.setModel(facultyJCompLoader.loadFacultySpecialization(facultyDaoImpl.loadFacultySpecialization(faculty, subjectCategory), "update"));
        jtblSubjectCateoryList.getColumnModel().getColumn(0).setMinWidth(0);
        jtblSubjectCateoryList.getColumnModel().getColumn(0).setMaxWidth(1);
        jtblCurrentSpecializations.getColumnModel().getColumn(0).setMinWidth(0);
        jtblCurrentSpecializations.getColumnModel().getColumn(0).setMaxWidth(1);

        Faculty f2 = facultyDaoImpl.getFacultyById(faculty.getFacultyID());
        jtfLastName.setText(f2.getLastName());
        jtfLastName.setText(f2.getLastName());
        jtfFirstName.setText(f2.getFirstName());
        jtfMiddleName.setText(f2.getMiddleName());
        jtfMobile.setText(f2.getContactNo());
        jtfEmailAddress.setText(f2.getEmail());
        jcmbClassHandled.setSelectedItem(f2.getClassType().getClassTypeID());
        jcmbIsActive.setSelectedItem(f2.getStatus() == true? "Yes":"No");
        
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    @Override
    public void initControllers() {
        btn_add.addActionListener(new EditFacultyDialogListener(this, faculty));
        btn_remove.addActionListener(new EditFacultyDialogListener(this, faculty));
        btn_save.addActionListener(new EditFacultyDialogListener(this, faculty));
    }

    @Override
    public void initDaoImpl() {
        facultyDaoImpl = new FacultyDaoImpl();
        classTypeDaoImpl = new ClassTypeDaoImpl();
    }

    public JComboBox<String> getJcmbClassHandled() {
        return jcmbClassHandled;
    }

    public JTextField getTfLastname() {
        return jtfLastName;
    }

    public JTextField getTfFirstname() {
        return jtfFirstName;
    }

    public JTextField getTfMiddlename() {
        return jtfMiddleName;
    }

    public JTextField getTfEmail() {
        return jtfEmailAddress;
    }

    public JTextField getTfContact() {
        return jtfMobile;
    }

    public JComboBox getCbStatus() {
        return jcmbIsActive;
    }

    public JButton getBtnAdd() {
        return btn_add;
    }

    public JButton getBtnRemove() {
        return btn_remove;
    }

    public JButton getBtnSave() {
        return btn_save;
    }

    public JDialog getDialog() {
        return this;
    }

    public JTable getTblLoadedSpecialization() {
        return jtblSubjectCateoryList;
    }

    public JTable getTblFacultySpecialization() {
        return jtblCurrentSpecializations;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        panel_toppanel = new javax.swing.JPanel();
        panel_facultydetails = new javax.swing.JPanel();
        lbl_lastname = new javax.swing.JLabel();
        jtfLastName = new javax.swing.JTextField();
        lbl_firstname = new javax.swing.JLabel();
        jtfFirstName = new javax.swing.JTextField();
        lbl_middlename = new javax.swing.JLabel();
        jtfMiddleName = new javax.swing.JTextField();
        lbl_email = new javax.swing.JLabel();
        jtfEmailAddress = new javax.swing.JTextField();
        lbl_mobile = new javax.swing.JLabel();
        jtfMobile = new javax.swing.JTextField();
        lbl_status = new javax.swing.JLabel();
        jcmbIsActive = new javax.swing.JComboBox<>();
        jcmbClassHandled = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        panel_loadspecialization = new javax.swing.JPanel();
        panel_specializationlist = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtblSubjectCateoryList = new javax.swing.JTable();
        panel_centercontrol = new javax.swing.JPanel();
        btn_add = new javax.swing.JButton();
        btn_remove = new javax.swing.JButton();
        panel_currentspecialization = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtblCurrentSpecializations = new javax.swing.JTable();
        panel_footer = new javax.swing.JPanel();
        btn_cancel = new javax.swing.JButton();
        btn_save = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Edit Faculty");
        setMinimumSize(new java.awt.Dimension(555, 600));
        setModal(true);
        setResizable(false);
        getContentPane().setLayout(new java.awt.GridBagLayout());

        panel_toppanel.setMinimumSize(new java.awt.Dimension(650, 600));
        panel_toppanel.setPreferredSize(new java.awt.Dimension(650, 600));
        panel_toppanel.setLayout(new java.awt.GridBagLayout());

        panel_facultydetails.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Faculty Details", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        panel_facultydetails.setMinimumSize(new java.awt.Dimension(620, 170));
        panel_facultydetails.setPreferredSize(new java.awt.Dimension(620, 170));
        panel_facultydetails.setLayout(new java.awt.GridBagLayout());

        lbl_lastname.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbl_lastname.setText("Last Name :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        panel_facultydetails.add(lbl_lastname, gridBagConstraints);

        jtfLastName.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jtfLastName.setMinimumSize(new java.awt.Dimension(150, 25));
        jtfLastName.setPreferredSize(new java.awt.Dimension(150, 25));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        panel_facultydetails.add(jtfLastName, gridBagConstraints);

        lbl_firstname.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbl_firstname.setText("First Name :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        panel_facultydetails.add(lbl_firstname, gridBagConstraints);

        jtfFirstName.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jtfFirstName.setMinimumSize(new java.awt.Dimension(150, 25));
        jtfFirstName.setPreferredSize(new java.awt.Dimension(150, 25));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        panel_facultydetails.add(jtfFirstName, gridBagConstraints);

        lbl_middlename.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbl_middlename.setText("Middle Name :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        panel_facultydetails.add(lbl_middlename, gridBagConstraints);

        jtfMiddleName.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jtfMiddleName.setMinimumSize(new java.awt.Dimension(150, 25));
        jtfMiddleName.setPreferredSize(new java.awt.Dimension(150, 25));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        panel_facultydetails.add(jtfMiddleName, gridBagConstraints);

        lbl_email.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbl_email.setText("Email Address :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        panel_facultydetails.add(lbl_email, gridBagConstraints);

        jtfEmailAddress.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jtfEmailAddress.setMinimumSize(new java.awt.Dimension(150, 25));
        jtfEmailAddress.setPreferredSize(new java.awt.Dimension(150, 25));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        panel_facultydetails.add(jtfEmailAddress, gridBagConstraints);

        lbl_mobile.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbl_mobile.setText("Mobile :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        panel_facultydetails.add(lbl_mobile, gridBagConstraints);

        jtfMobile.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jtfMobile.setMinimumSize(new java.awt.Dimension(150, 25));
        jtfMobile.setPreferredSize(new java.awt.Dimension(150, 25));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        panel_facultydetails.add(jtfMobile, gridBagConstraints);

        lbl_status.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbl_status.setText("Active :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        panel_facultydetails.add(lbl_status, gridBagConstraints);

        jcmbIsActive.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jcmbIsActive.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Yes", "No" }));
        jcmbIsActive.setMinimumSize(new java.awt.Dimension(60, 25));
        jcmbIsActive.setPreferredSize(new java.awt.Dimension(60, 25));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        panel_facultydetails.add(jcmbIsActive, gridBagConstraints);

        jcmbClassHandled.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        panel_facultydetails.add(jcmbClassHandled, gridBagConstraints);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Class Handled :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        panel_facultydetails.add(jLabel1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        panel_toppanel.add(panel_facultydetails, gridBagConstraints);

        panel_loadspecialization.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Load Specialization", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        panel_loadspecialization.setMinimumSize(new java.awt.Dimension(550, 380));
        panel_loadspecialization.setPreferredSize(new java.awt.Dimension(550, 380));
        panel_loadspecialization.setLayout(new java.awt.GridBagLayout());

        panel_specializationlist.setBorder(javax.swing.BorderFactory.createTitledBorder("Subject Category List"));
        panel_specializationlist.setMinimumSize(new java.awt.Dimension(245, 350));
        panel_specializationlist.setPreferredSize(new java.awt.Dimension(245, 350));
        panel_specializationlist.setLayout(new java.awt.GridBagLayout());

        jScrollPane1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jScrollPane1.setMinimumSize(new java.awt.Dimension(235, 330));
        jScrollPane1.setPreferredSize(new java.awt.Dimension(235, 330));

        jtblSubjectCateoryList.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jtblSubjectCateoryList.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Specialization Name"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtblSubjectCateoryList.setRowHeight(20);
        jScrollPane1.setViewportView(jtblSubjectCateoryList);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.5;
        panel_specializationlist.add(jScrollPane1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        panel_loadspecialization.add(panel_specializationlist, gridBagConstraints);

        panel_centercontrol.setMinimumSize(new java.awt.Dimension(60, 300));
        panel_centercontrol.setPreferredSize(new java.awt.Dimension(60, 300));
        panel_centercontrol.setLayout(new java.awt.GridBagLayout());

        btn_add.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        btn_add.setText(">");
        btn_add.setMaximumSize(new java.awt.Dimension(38, 40));
        btn_add.setMinimumSize(new java.awt.Dimension(38, 40));
        btn_add.setPreferredSize(new java.awt.Dimension(38, 40));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        panel_centercontrol.add(btn_add, gridBagConstraints);

        btn_remove.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        btn_remove.setText("<");
        btn_remove.setMaximumSize(new java.awt.Dimension(38, 40));
        btn_remove.setMinimumSize(new java.awt.Dimension(38, 40));
        btn_remove.setPreferredSize(new java.awt.Dimension(38, 40));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        panel_centercontrol.add(btn_remove, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        panel_loadspecialization.add(panel_centercontrol, gridBagConstraints);

        panel_currentspecialization.setBorder(javax.swing.BorderFactory.createTitledBorder("Current Specializations"));
        panel_currentspecialization.setMinimumSize(new java.awt.Dimension(245, 350));
        panel_currentspecialization.setLayout(new java.awt.GridBagLayout());

        jScrollPane2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jScrollPane2.setMinimumSize(new java.awt.Dimension(235, 330));
        jScrollPane2.setPreferredSize(new java.awt.Dimension(235, 330));

        jtblCurrentSpecializations.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jtblCurrentSpecializations.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Specialization Name"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtblCurrentSpecializations.setRowHeight(20);
        jScrollPane2.setViewportView(jtblCurrentSpecializations);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        panel_currentspecialization.add(jScrollPane2, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        panel_loadspecialization.add(panel_currentspecialization, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        panel_toppanel.add(panel_loadspecialization, gridBagConstraints);

        panel_footer.setMinimumSize(new java.awt.Dimension(550, 50));
        panel_footer.setPreferredSize(new java.awt.Dimension(550, 50));
        panel_footer.setLayout(new java.awt.GridBagLayout());

        btn_cancel.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btn_cancel.setText("Cancel");
        btn_cancel.setMaximumSize(new java.awt.Dimension(80, 40));
        btn_cancel.setMinimumSize(new java.awt.Dimension(80, 40));
        btn_cancel.setPreferredSize(new java.awt.Dimension(80, 40));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        panel_footer.add(btn_cancel, gridBagConstraints);

        btn_save.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btn_save.setText("Save");
        btn_save.setMaximumSize(new java.awt.Dimension(80, 40));
        btn_save.setMinimumSize(new java.awt.Dimension(80, 40));
        btn_save.setPreferredSize(new java.awt.Dimension(80, 40));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        panel_footer.add(btn_save, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
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
    private javax.swing.JButton btn_add;
    private javax.swing.JButton btn_cancel;
    private javax.swing.JButton btn_remove;
    private javax.swing.JButton btn_save;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JComboBox<String> jcmbClassHandled;
    private javax.swing.JComboBox<String> jcmbIsActive;
    private javax.swing.JTable jtblCurrentSpecializations;
    private javax.swing.JTable jtblSubjectCateoryList;
    private javax.swing.JTextField jtfEmailAddress;
    private javax.swing.JTextField jtfFirstName;
    private javax.swing.JTextField jtfLastName;
    private javax.swing.JTextField jtfMiddleName;
    private javax.swing.JTextField jtfMobile;
    private javax.swing.JLabel lbl_email;
    private javax.swing.JLabel lbl_firstname;
    private javax.swing.JLabel lbl_lastname;
    private javax.swing.JLabel lbl_middlename;
    private javax.swing.JLabel lbl_mobile;
    private javax.swing.JLabel lbl_status;
    private javax.swing.JPanel panel_centercontrol;
    private javax.swing.JPanel panel_currentspecialization;
    private javax.swing.JPanel panel_facultydetails;
    private javax.swing.JPanel panel_footer;
    private javax.swing.JPanel panel_loadspecialization;
    private javax.swing.JPanel panel_specializationlist;
    private javax.swing.JPanel panel_toppanel;
    // End of variables declaration//GEN-END:variables
}
