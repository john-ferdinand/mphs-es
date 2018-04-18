package controller.faculty;

import daoimpl.ClassTypeDaoImpl;
import daoimpl.FacultyDaoImpl;
import daoimpl.SchoolYearDaoImpl;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import model.classtype.ClassType;
import model.faculty.Faculty;
import model.subjectcategory.SubjectCategory;
import view.faculty.Dialog_FacultyEdit;

/**
 *
 * @author franc
 */
public class EditFacultyDialogListener implements ActionListener {

    private Dialog_FacultyEdit view;
    private ArrayList<String> rejectedData;
    private Faculty faculty;
    private SubjectCategory subjectCategory = new SubjectCategory();
    private final SchoolYearDaoImpl schoolYearDaoImpl;
    private FacultyDaoImpl facultyDaoImpl;
    private ClassTypeDaoImpl classTypeDaoImpl;

    public EditFacultyDialogListener(Dialog_FacultyEdit view, Faculty faculty) {
        this.view = view;
        this.schoolYearDaoImpl = new SchoolYearDaoImpl();
        facultyDaoImpl = new FacultyDaoImpl();
        classTypeDaoImpl = new ClassTypeDaoImpl();
        rejectedData = new ArrayList<>();
        this.faculty = faculty;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(view.getBtnAdd())) {
            add();
            if (rejectedData.size() > 0) {
                displayRejectedData();
                rejectedData.clear();
            }
        } else if (e.getSource().equals(view.getBtnRemove())) {
            removeRowsSelected();
        } else if (e.getSource().equals(view.getBtnSave())) {
            if (view.getTfLastname().getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Lastname field cannot be left blank!");
            } else if (view.getTfFirstname().getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Firstname field cannot be left blank!");
            } else if (view.getTfMiddlename().getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Middlename field cannot be left blank!");
            } else {
                faculty.setFacultyID(faculty.getFacultyID());
                faculty.setLastName(view.getTfLastname().getText().trim());
                faculty.setFirstName(view.getTfFirstname().getText().trim());
                faculty.setMiddleName(view.getTfMiddlename().getText().trim());
                faculty.setContactNo(view.getTfContact().getText().trim());
                faculty.setEmail(view.getTfEmail().getText().trim());
                
                int classTypeId = Integer.parseInt(view.getJcmbClassHandled().getSelectedItem().toString().trim());
                ClassType classType = classTypeDaoImpl.getClassTypeById(classTypeId);
                faculty.setClassType(classType);
                if (view.getCbStatus().getSelectedIndex() == 0) {
                    faculty.setStatus(true);
                } else {
                    faculty.setStatus(false);
                }

                facultyDaoImpl.updateFaculty(faculty);

                if (facultyDaoImpl.countFacultySpecialization(faculty) == 0) {
                    for (int i = 0; i < view.getTblFacultySpecialization().getRowCount(); i++) {
                        subjectCategory.setSubjectCategoryId(Integer.parseInt((String) view.getTblFacultySpecialization().getValueAt(i, 0)));
                        facultyDaoImpl.createFacultySpecialization(faculty, subjectCategory);
                        if (i == view.getTblFacultySpecialization().getRowCount() - 1) {
                            JOptionPane.showMessageDialog(null, "Successful!");
                            view.getDialog().dispose();
                        }
                    }
                } else {

                    facultyDaoImpl.deleteFacultySpecialization(faculty);
                    for (int i = 0; i < view.getTblFacultySpecialization().getRowCount(); i++) {
                        subjectCategory.setSubjectCategoryId(Integer.parseInt(view.getTblFacultySpecialization().getValueAt(i, 0).toString()));
                        facultyDaoImpl.createFacultySpecialization(faculty, subjectCategory);
                        if (i == view.getTblFacultySpecialization().getRowCount() - 1) {
                            JOptionPane.showMessageDialog(null, "Successful!");
                            view.getDialog().dispose();
                        }
                    }
                }
            }
        }
    }

    private boolean exists(String id) {
        boolean exists = false;

        for (int i = 0; i < view.getTblFacultySpecialization().getRowCount(); i++) {
            if (id.equalsIgnoreCase(view.getTblFacultySpecialization().getValueAt(i, 0).toString().trim())) {
                exists = true;
            }
        }
        return exists;
    }

    private boolean add() {
        boolean isAdded = false;
        DefaultTableModel dtmFacultySpecToAssign = (DefaultTableModel) view.getTblFacultySpecialization().getModel();
        int[] selectedRows = view.getTblLoadedSpecialization().getSelectedRows();
        for (int i = 0; i < selectedRows.length; i++) {
            String id = view.getTblLoadedSpecialization().getValueAt(selectedRows[i], 0).toString();
            String name = view.getTblLoadedSpecialization().getValueAt(selectedRows[i], 1).toString();
            if (!exists(id.trim())) {
                Object[] rowData = {id, name};
                dtmFacultySpecToAssign.addRow(rowData);
            } else {
                rejectedData.add(id + " " + name);
            }
        }
        removeRowsSelected(selectedRows, view.getTblLoadedSpecialization());

        return isAdded;
    }

    private void removeRowsSelected(int[] selectedRows, JTable jTable) {
        for (int i = selectedRows.length - 1; i >= 0; i--) {
            DefaultTableModel tableModel = (DefaultTableModel) jTable.getModel();
            tableModel.removeRow(selectedRows[i]);
            ;
        }
    }

    private void displayRejectedData() {
        String output = "";
        for (int i = 0; i < rejectedData.size(); i++) {
            output = output + rejectedData.get(i) + ",\n";
        }
        JOptionPane.showMessageDialog(null, "The following are already on the list of assigned\n" + output);
    }

    private void removeRowsSelected() {
        int[] indices = view.getTblFacultySpecialization().getSelectedRows();
        for (int i = indices.length - 1; i >= 0; i--) {
            DefaultTableModel tableModel = (DefaultTableModel) view.getTblFacultySpecialization().getModel();
            tableModel.removeRow(indices[i]);
            ;
        }
    }

}
