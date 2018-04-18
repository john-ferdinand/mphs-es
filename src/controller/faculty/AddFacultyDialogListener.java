package controller.faculty;

import daoimpl.FacultyDaoImpl;
import daoimpl.SchoolYearDaoImpl;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import model.faculty.Faculty;
import utility.form.FormValidator;
import view.faculty.Dialog_FacultyAdd;

/**
 *
 * @author franc
 */
public class AddFacultyDialogListener implements ActionListener, FormValidator {

    private Dialog_FacultyAdd view;
    private Faculty faculty = new Faculty();
    private FacultyDaoImpl facultyDaoImpl;
    private SchoolYearDaoImpl schoolYearDaoImpl;
    
    public AddFacultyDialogListener(Dialog_FacultyAdd view) {
        this.view = view;
        this.schoolYearDaoImpl = new SchoolYearDaoImpl();
        this.facultyDaoImpl = new FacultyDaoImpl();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(view.getBtnSaveNew())) {
            if (formIsValid()) {
                boolean isSuccessful = createFaculty();
                if (isSuccessful) {
                    JOptionPane.showMessageDialog(null, "Successfully added faculty.");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Please fill out empty fields.");
            }
        }
    }

    private boolean createFaculty() {
        boolean isCreated = false;
        faculty.setLastName(view.getTfLastname().getText().trim());
        faculty.setFirstName(view.getTfFirstname().getText().trim());
        faculty.setMiddleName(view.getTfMiddlename().getText().trim());
        faculty.setEmail(view.getTfEmail().getText().trim());
        faculty.setContactNo(view.getTfContact().getText().trim());
        isCreated = facultyDaoImpl.createFaculty(faculty);
        return isCreated;
    }

    @Override
    public boolean formIsValid() {
        boolean isValid = true;
        Component[] compArr = view.getPanel_facultydetails().getComponents();
        for (Component c : compArr) {
            if (c instanceof JTextField) {
                isValid = isValid && !((JTextField) c).getText().trim().isEmpty();
            }
        }
        return isValid;
    }

}
