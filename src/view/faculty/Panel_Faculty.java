package view.faculty;

import component_model_loader.FacultyJCompModelLoader;
import controller.faculty.Panel_FacultyListener;
import daoimpl.FacultyDaoImpl;
import daoimpl.SchoolYearDaoImpl;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import model.faculty.Faculty;
import utility.initializer.Initializer;


public class Panel_Faculty extends javax.swing.JPanel{
    
    private Faculty faculty = new Faculty();    
    private FacultyJCompModelLoader facultyLoader = new FacultyJCompModelLoader();
    private FacultyDaoImpl fdi = new FacultyDaoImpl();
    
    public Panel_Faculty() {
        initComponents();
        
        jtblFacultyMasterList.getColumnModel().getColumn(0).setMinWidth(0);
        jtblFacultyMasterList.getColumnModel().getColumn(0).setMaxWidth(0);
        jtblSpecialization.getColumnModel().getColumn(0).setMinWidth(0);
        jtblSpecialization.getColumnModel().getColumn(0).setMaxWidth(0);
        jtblSpecializationSubjects.getColumnModel().getColumn(0).setMinWidth(0);
        jtblSpecializationSubjects.getColumnModel().getColumn(0).setMaxWidth(0);
        
        jtblFacultyMasterList.setModel(facultyLoader.getAllFaculty(fdi.getAllFaculty(faculty)));
        jtblFacultyMasterList.getColumnModel().getColumn(0).setMinWidth(0);
        jtblFacultyMasterList.getColumnModel().getColumn(0).setMaxWidth(0);
        
        btn_addnewfaculty.addActionListener(new Panel_FacultyListener(this));
        btn_Edit.addActionListener(new Panel_FacultyListener(this));
        jbtnSearch.addActionListener(new Panel_FacultyListener(this));
        jtblFacultyMasterList.addMouseListener(new Panel_FacultyListener(this));
        jtblSpecialization.addMouseListener(new Panel_FacultyListener(this));
        jtfSearchBox.addMouseListener(new Panel_FacultyListener(this));
        jtblFacultyMasterList.addKeyListener(new Panel_FacultyListener(this));
    }

    public JButton getBtnNewFaculty(){
        return btn_addnewfaculty;
    }
    
    public JButton getBtnEditFaculty(){
        return btn_Edit;
    }
    
    public JButton getBtnSearch(){
        return jbtnSearch;
    }
    
    public JTextField getTfSearch(){
        return jtfSearchBox;
    }
    
    public JTable getJtblFacultyMasterList(){
        return jtblFacultyMasterList;
    }
    
    public JTable getJtblSpecialization(){
        return jtblSpecialization;
    }
    
    public JTable getJtblSpecializationSubjects(){
        return jtblSpecializationSubjects;
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
        panel_particularsubjects = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jtblSpecializationSubjects = new javax.swing.JTable();
        panel_control = new javax.swing.JPanel();
        btn_addnewfaculty = new javax.swing.JButton();
        btn_Edit = new javax.swing.JButton();
        btn_Print = new javax.swing.JButton();
        jtfSearchBox = new javax.swing.JTextField();
        jbtnSearch = new javax.swing.JButton();
        lbl_show = new javax.swing.JLabel();
        combo_filter = new javax.swing.JComboBox<>();
        panel_specialization = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtblSpecialization = new javax.swing.JTable();
        panel_masterrecord = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtblFacultyMasterList = new javax.swing.JTable();

        setLayout(new java.awt.GridBagLayout());

        panel_toppanel.setMinimumSize(new java.awt.Dimension(1200, 600));
        panel_toppanel.setPreferredSize(new java.awt.Dimension(1200, 600));
        panel_toppanel.setLayout(new java.awt.GridBagLayout());

        panel_particularsubjects.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Particular Subjects", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        panel_particularsubjects.setMinimumSize(new java.awt.Dimension(600, 260));
        panel_particularsubjects.setPreferredSize(new java.awt.Dimension(600, 260));
        panel_particularsubjects.setLayout(new java.awt.GridBagLayout());

        jScrollPane3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jScrollPane3.setMinimumSize(new java.awt.Dimension(590, 235));
        jScrollPane3.setPreferredSize(new java.awt.Dimension(590, 235));

        jtblSpecializationSubjects.setAutoCreateRowSorter(true);
        jtblSpecializationSubjects.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jtblSpecializationSubjects.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Title", "Subject Code", "Description", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtblSpecializationSubjects.setMinimumSize(new java.awt.Dimension(590, 10000));
        jtblSpecializationSubjects.setPreferredSize(new java.awt.Dimension(590, 10000));
        jtblSpecializationSubjects.setRowHeight(20);
        jtblSpecializationSubjects.getTableHeader().setReorderingAllowed(false);
        jScrollPane3.setViewportView(jtblSpecializationSubjects);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        panel_particularsubjects.add(jScrollPane3, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        panel_toppanel.add(panel_particularsubjects, gridBagConstraints);

        panel_control.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        panel_control.setMinimumSize(new java.awt.Dimension(1200, 40));
        panel_control.setPreferredSize(new java.awt.Dimension(1200, 40));
        panel_control.setLayout(new java.awt.GridBagLayout());

        btn_addnewfaculty.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btn_addnewfaculty.setText("Add New Faculty");
        btn_addnewfaculty.setActionCommand("add");
        btn_addnewfaculty.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_addnewfacultyActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        panel_control.add(btn_addnewfaculty, gridBagConstraints);

        btn_Edit.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btn_Edit.setText("Edit");
        btn_Edit.setActionCommand("edit");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        panel_control.add(btn_Edit, gridBagConstraints);

        btn_Print.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btn_Print.setText("Print");
        btn_Print.setActionCommand("print");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 20);
        panel_control.add(btn_Print, gridBagConstraints);

        jtfSearchBox.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jtfSearchBox.setText("Search here");
        jtfSearchBox.setMinimumSize(new java.awt.Dimension(150, 25));
        jtfSearchBox.setPreferredSize(new java.awt.Dimension(150, 25));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(0, 460, 0, 0);
        panel_control.add(jtfSearchBox, gridBagConstraints);

        jbtnSearch.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jbtnSearch.setText("Search");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 0;
        panel_control.add(jbtnSearch, gridBagConstraints);

        lbl_show.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbl_show.setText("Show :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(0, 30, 0, 0);
        panel_control.add(lbl_show, gridBagConstraints);

        combo_filter.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        combo_filter.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "All" }));
        combo_filter.setMinimumSize(new java.awt.Dimension(100, 25));
        combo_filter.setPreferredSize(new java.awt.Dimension(100, 25));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        panel_control.add(combo_filter, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        panel_toppanel.add(panel_control, gridBagConstraints);

        panel_specialization.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Specialization", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        panel_specialization.setMinimumSize(new java.awt.Dimension(600, 260));
        panel_specialization.setPreferredSize(new java.awt.Dimension(600, 260));
        panel_specialization.setLayout(new java.awt.GridBagLayout());

        jScrollPane2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jScrollPane2.setMinimumSize(new java.awt.Dimension(590, 235));
        jScrollPane2.setPreferredSize(new java.awt.Dimension(590, 235));

        jtblSpecialization.setAutoCreateRowSorter(true);
        jtblSpecialization.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jtblSpecialization.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Specialization Name", "Status", "Notes"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtblSpecialization.setMinimumSize(new java.awt.Dimension(590, 10000));
        jtblSpecialization.setPreferredSize(new java.awt.Dimension(590, 10000));
        jtblSpecialization.setRowHeight(20);
        jtblSpecialization.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(jtblSpecialization);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        panel_specialization.add(jScrollPane2, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        panel_toppanel.add(panel_specialization, gridBagConstraints);

        panel_masterrecord.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Faculty Master List", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        panel_masterrecord.setMinimumSize(new java.awt.Dimension(1200, 290));
        panel_masterrecord.setPreferredSize(new java.awt.Dimension(1200, 290));
        panel_masterrecord.setLayout(new java.awt.GridBagLayout());

        jScrollPane1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jScrollPane1.setMinimumSize(new java.awt.Dimension(1185, 265));
        jScrollPane1.setPreferredSize(new java.awt.Dimension(1185, 265));

        jtblFacultyMasterList.setAutoCreateRowSorter(true);
        jtblFacultyMasterList.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jtblFacultyMasterList.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Faculty ID", "Last Name", "First Name", "Middle Name", "Contact No", "Email Address", "Status", "Username"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtblFacultyMasterList.setFillsViewportHeight(true);
        jtblFacultyMasterList.setMaximumSize(new java.awt.Dimension(2147483647, 2147483647));
        jtblFacultyMasterList.setMinimumSize(new java.awt.Dimension(1185, 10000));
        jtblFacultyMasterList.setPreferredSize(new java.awt.Dimension(1185, 10000));
        jtblFacultyMasterList.setRowHeight(20);
        jtblFacultyMasterList.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(jtblFacultyMasterList);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        panel_masterrecord.add(jScrollPane1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        panel_toppanel.add(panel_masterrecord, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        add(panel_toppanel, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    private void btn_addnewfacultyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_addnewfacultyActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_addnewfacultyActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_Edit;
    private javax.swing.JButton btn_Print;
    private javax.swing.JButton btn_addnewfaculty;
    private javax.swing.JComboBox<String> combo_filter;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JButton jbtnSearch;
    private javax.swing.JTable jtblFacultyMasterList;
    private javax.swing.JTable jtblSpecialization;
    private javax.swing.JTable jtblSpecializationSubjects;
    private javax.swing.JTextField jtfSearchBox;
    private javax.swing.JLabel lbl_show;
    private javax.swing.JPanel panel_control;
    private javax.swing.JPanel panel_masterrecord;
    private javax.swing.JPanel panel_particularsubjects;
    private javax.swing.JPanel panel_specialization;
    private javax.swing.JPanel panel_toppanel;
    // End of variables declaration//GEN-END:variables
}
