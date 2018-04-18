package component_model_loader;

import daoimpl.FacultyDaoImpl;
import daoimpl.SchoolYearDaoImpl;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;
import model.faculty.Faculty;
import model.schoolyear.SchoolYear;
import model.subjectcategory.SubjectCategory;

/**
 *
 * @author John Ferdinand Antonio
 */
public class FacultyJCompModelLoader {

    private final FacultyDaoImpl facultyDaoImpl;
    
    public FacultyJCompModelLoader(){
        facultyDaoImpl = new FacultyDaoImpl();
    }
    
    private Object[] columnNames() {
        return new Object[]{"Faculty Id", "Last Name", "First Name", "Middle Name", "Contact No", "Email Address", "Status"};
    }

    public DefaultComboBoxModel getAllFacultyNamesWithNoAdvisory() {
        DefaultComboBoxModel comboModel = new DefaultComboBoxModel();
        List<Faculty> list = facultyDaoImpl.getAllFacultyWithNoAdvisory();
        for (Faculty f : list) {
            comboModel.addElement(f.getFacultyID());
        }
        return comboModel;
    }

    public DefaultComboBoxModel getAllFacultyAsId() {
        DefaultComboBoxModel comboModel = new DefaultComboBoxModel();
        List<Faculty> list = facultyDaoImpl.getAllFaculty();
        for (Faculty f : list) {
            comboModel.addElement(f.getFacultyID());
        }
        return comboModel;
    }
    
    public DefaultComboBoxModel getAllFacultyAsModel() {
        DefaultComboBoxModel comboModel = new DefaultComboBoxModel();
        List<Faculty> list = facultyDaoImpl.getAllFaculty();
        for (Faculty faculty : list) {
            comboModel.addElement(faculty);
        }
        comboModel.setSelectedItem(null);
        return comboModel;
    }
    
    public DefaultComboBoxModel getAllFacultyIdHandlingAdvisory(SchoolYear schoolYear) {
        DefaultComboBoxModel comboModel = new DefaultComboBoxModel();
        List<Faculty> list = facultyDaoImpl.getAllFacultyHandlingAdvisory(schoolYear);
        for (Faculty f : list) {
            comboModel.addElement(f.getFacultyID());
        }
        return comboModel;
    }
    
    public DefaultComboBoxModel getAllFacultyByStatus(boolean isActive) {
        DefaultComboBoxModel comboModel = new DefaultComboBoxModel();
        List<Faculty> list = facultyDaoImpl.getAllFacultyByStatus(isActive);
        for (Faculty f : list) {
            comboModel.addElement(f);
        }
        comboModel.setSelectedItem(null);
        return comboModel;
    }

    public DefaultTableModel facultyInfo(List<Faculty> list) {
        Object[][] obj = new Object[list.size()][7];
        Object[] columnObject = list.toArray();
        List<List<Object>> new2d = new ArrayList<List<Object>>(list.size());
        List<Object> newList = new ArrayList<Object>();

        for (Object object : columnObject) {
            newList = new ArrayList<Object>();
            Faculty faculty = (Faculty) object;

            newList.add(faculty.getFacultyID());
            newList.add(faculty.getLastName());
            newList.add(faculty.getFirstName());
            newList.add(faculty.getMiddleName());
            newList.add(faculty.getContactNo());
            newList.add(faculty.getEmail());
            if (faculty.getStatus()) {
                newList.add("Active");
            } else {
                newList.add("Inactive");
            }

            new2d.add(newList);
        }

        for (int a = 0; a < new2d.size(); a++) {
            for (int b = 0; b < new2d.get(a).size(); b++) {
                obj[a][b] = new2d.get(a).get(b);
            }
        }

        DefaultTableModel model = new DefaultTableModel(obj, columnNames());

        return model;
    }

    public DefaultTableModel loadFacultySpecialization(List<SubjectCategory> list, String query) {
        Object[][] obj = new Object[list.size()][4];
        Object[] columnObject = list.toArray();
        Object[] columnSpecialization;
        List<List<Object>> new2d = new ArrayList<List<Object>>(list.size());
        List<Object> newList = new ArrayList<Object>();

        if (query.equals("update")) {
            columnSpecialization = new Object[]{"Id", "Specialization Name"};
            for (Object object : columnObject) {
                newList = new ArrayList<Object>();
                SubjectCategory subjectCategory = (SubjectCategory) object;

                newList.add(subjectCategory.getSubjectCategoryId());
                newList.add(subjectCategory.getSubjectCategoryName());

                new2d.add(newList);
            }
        } else {
            columnSpecialization = new Object[]{"Id", "Specialization Name", "Description", "Status"};
            for (Object object : columnObject) {
                newList = new ArrayList<Object>();
                SubjectCategory subjectCategory = (SubjectCategory) object;

                newList.add(subjectCategory.getSubjectCategoryId());
                newList.add(subjectCategory.getSubjectCategoryName());
                newList.add(subjectCategory.getDescription());

                if (subjectCategory.getIsActive() == true) {
                    newList.add("Active");
                } else {
                    newList.add("Inactive");
                }

                new2d.add(newList);
            }
        }

        for (int a = 0; a < new2d.size(); a++) {
            for (int b = 0; b < new2d.get(a).size(); b++) {
                obj[a][b] = new2d.get(a).get(b);
            }
        }

        DefaultTableModel model = new DefaultTableModel(obj, columnSpecialization);

        return model;
    }

    public DefaultTableModel getAllFaculty(List<Faculty> list) {
        Object[][] obj = new Object[list.size()][7];
        Object[] columnObject = list.toArray();
        List<List<Object>> new2d = new ArrayList<List<Object>>(list.size());
        List<Object> newList = new ArrayList<Object>();
        for (Object object : columnObject) {
            newList = new ArrayList<Object>();
            Faculty faculty = (Faculty) object;
            newList.add(faculty.getFacultyID());
            newList.add(faculty.getLastName());
            newList.add(faculty.getFirstName());
            newList.add(faculty.getMiddleName());
            newList.add(faculty.getContactNo());
            newList.add(faculty.getEmail());
            if (faculty.getStatus()) {
                newList.add("Active");
            } else {
                newList.add("Inactive");
            }
            new2d.add(newList);
        }
        for (int a = 0; a < new2d.size(); a++) {
            for (int b = 0; b < new2d.get(a).size(); b++) {
                obj[a][b] = new2d.get(a).get(b);
            }
        }
        DefaultTableModel model = new DefaultTableModel(obj, columnNames());
        return model;
    }
}
