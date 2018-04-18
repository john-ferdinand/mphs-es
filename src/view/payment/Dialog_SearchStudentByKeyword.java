
package view.payment;

import controller.global.Controller_JButton_ExitJDialog;
import controller.payment.Controller_Dialog_SearchStudentByKeyword_JTable;
import controller.payment.Controller_Load_JButton;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import model.schoolyear.SchoolYear;
import model.student.Student;
import model.user.User;
import renderer.payment.Renderer_Payment_Dialog_SearchResult_JTable;
import utility.initializer.Initializer;
import utility.jtable.JTableUtil;

/**
 *
 * @author Jordan
 */
public class Dialog_SearchStudentByKeyword extends javax.swing.JDialog implements Initializer{

    private final List<Student> students;
    private final SchoolYear currentSchoolYear;
    private final Panel_Payment view;
    private final User user;
    
    public Dialog_SearchStudentByKeyword(java.awt.Frame parent, boolean modal, Panel_Payment view, List<Student> students, SchoolYear currentSchoolYear, User user) {
        super(parent, modal);
        this.view = view;
        this.students = students;
        this.currentSchoolYear = currentSchoolYear;
        this.user = user;
        initComponents();
        
        initDaoImpl();
        initJCompModelLoaders();
        initControllers();
        initRenderers();
        initViewComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtblStudents = new javax.swing.JTable();
        jpnlControl = new javax.swing.JPanel();
        jbtnCancel = new javax.swing.JButton();
        jbtnLoad = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Search Results");
        setMinimumSize(new java.awt.Dimension(1000, 450));
        setPreferredSize(new java.awt.Dimension(1000, 450));
        getContentPane().setLayout(new java.awt.GridBagLayout());

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.setLayout(new java.awt.GridBagLayout());

        jtblStudents.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jtblStudents.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Registration ID", "Admission Id", "Admission Status", "Student No", "Last Name", "First Name", "Middle Name", "Student Type"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtblStudents.setRowHeight(30);
        jtblStudents.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(jtblStudents);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel1.add(jScrollPane1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        getContentPane().add(jPanel1, gridBagConstraints);

        jpnlControl.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Control", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        jpnlControl.setLayout(new java.awt.GridBagLayout());

        jbtnCancel.setText("Cancel");
        jpnlControl.add(jbtnCancel, new java.awt.GridBagConstraints());

        jbtnLoad.setText("Load");
        jpnlControl.add(jbtnLoad, new java.awt.GridBagConstraints());

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        getContentPane().add(jpnlControl, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    @Override
    public void initGridBagConstraints() {
    }

    @Override
    public void initJCompModelLoaders() {
    }

    @Override
    public void initRenderers() {
        jtblStudents.setDefaultRenderer(Object.class, new Renderer_Payment_Dialog_SearchResult_JTable(3,1,7));
    }

    @Override
    public void initModels() {
    }

    @Override
    public void initViewComponents() {
        jbtnLoad.setEnabled(false);
        JTableUtil.applyCustomHeaderRenderer(jtblStudents);
        DefaultTableModel tableModel = (DefaultTableModel) jtblStudents.getModel();
        tableModel.setRowCount(0);
        for(Student s : students){
            Object[] rowData = {
                s.getRegistration().getRegistrationId(),s.getAdmission().getAdmissionId(),
                s.getRegistration().getIsAdmissionComplete() == true? "Complete":"Pending",
                s.getStudentNo(),s.getRegistration().getLastName(), s.getRegistration().getFirstName(),
                s.getRegistration().getMiddleName(),s.getStudentType()
            };
            tableModel.addRow(rowData);
        }
        jtblStudents.setModel(tableModel);
    }

    @Override
    public void initControllers() {
        jbtnCancel.addActionListener(new Controller_JButton_ExitJDialog(this));
        jbtnLoad.addActionListener(new Controller_Load_JButton(this, view,currentSchoolYear,user));
        jtblStudents.addMouseListener(new Controller_Dialog_SearchStudentByKeyword_JTable(this));
        jtblStudents.addKeyListener(new Controller_Dialog_SearchStudentByKeyword_JTable(this));
    }

    @Override
    public void initDaoImpl() {
    }

    public JButton getJbtnCancel() {
        return jbtnCancel;
    }

    public JButton getJbtnLoad() {
        return jbtnLoad;
    }

    public JPanel getJpnlControl() {
        return jpnlControl;
    }

    public JTable getJtblStudents() {
        return jtblStudents;
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jbtnCancel;
    private javax.swing.JButton jbtnLoad;
    private javax.swing.JPanel jpnlControl;
    private javax.swing.JTable jtblStudents;
    // End of variables declaration//GEN-END:variables
}
