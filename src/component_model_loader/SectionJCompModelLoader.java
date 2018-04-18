package component_model_loader;

import daoimpl.SchoolYearDaoImpl;
import daoimpl.SectionDaoImpl;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import model.faculty.Faculty;
import model.gradelevel.GradeLevel;
import model.schoolyear.SchoolYear;
import model.section.Section;
import model.student.Student;

public class SectionJCompModelLoader {

    private final SectionDaoImpl sectionDaoImpl;

    public SectionJCompModelLoader() {
        sectionDaoImpl = new SectionDaoImpl();
    }

    public DefaultComboBoxModel getAllSections(){
        DefaultComboBoxModel comboModel = new DefaultComboBoxModel();
        List<Section> sectionList = sectionDaoImpl.getAllSections();
        for (Section section : sectionList) {
            comboModel.addElement(section);
        }
        comboModel.setSelectedItem(null);
        return comboModel;
    }
    
    public DefaultTableModel getAllSections(JTable jTable) {
        DefaultTableModel tableModel = (DefaultTableModel) jTable.getModel();
        tableModel.setRowCount(0);
        Object[] sectionList = sectionDaoImpl.getAllSections().toArray();
        for (Object o : sectionList) {
            Section s = (Section) o;
            Object[] rowData = {
                s.getSectionId(), s.getSectionName(), s.getGradeLevel().getLevelNo(),
                s.getAdviser().getLastName() + ", " + s.getAdviser().getFirstName() + " " + s.getAdviser().getMiddleName(),
                s.getSectionSession(), s.getSchoolYear().getYearFrom(),
                s.getIsActive() == true ? "Active" : "Inactive",s.getSectionType()
            };
            tableModel.addRow(rowData);
        }
        return tableModel;
    }

    public DefaultTableModel getSectionsByWildCard(JTextField jtfSearchBox, JTable jTable) {
        DefaultTableModel tableModel = (DefaultTableModel) jTable.getModel();
        tableModel.setRowCount(0);
        String wildCardChar = jtfSearchBox.getText().trim();
        Object[] sectionList = sectionDaoImpl.getSectionsBy(wildCardChar).toArray();
        for (Object o : sectionList) {
            Section s = (Section) o;
            Object[] rowData = {
                s.getSectionId(), s.getSectionName(), s.getGradeLevel().getLevelNo(),
                s.getAdviser().getLastName() + ", " + s.getAdviser().getFirstName() + " " + s.getAdviser().getMiddleName(),
                s.getSectionSession(), s.getSchoolYear().getYearFrom(),
                s.getIsActive() == true ? "Yes" : "No",s.getSectionType()
            };
            tableModel.addRow(rowData);
        }
        return tableModel;
    }

    public DefaultComboBoxModel getSectionsByGradeLevelNo(JComboBox jcmbGradeLevel){
        DefaultComboBoxModel comboModel = new DefaultComboBoxModel();
        GradeLevel gradeLevel = (GradeLevel)jcmbGradeLevel.getSelectedItem();
        List<Section> sectionList = sectionDaoImpl.getSectionsBy(gradeLevel.getLevelNo());
        for (Section s : sectionList) {
            comboModel.addElement(s);
        }
        comboModel.setSelectedItem(null);
        return comboModel;
    }
    
    public DefaultTableModel getSectionsByGradeLevelNo(JTable table, JComboBox jcmbGradeLevel){
        DefaultTableModel tableModel = (DefaultTableModel)table.getModel();
        tableModel.setRowCount(0);
        int gradeLevelNo = Integer.parseInt(jcmbGradeLevel.getSelectedItem().toString().trim());
        List<Section> sectionList = sectionDaoImpl.getSectionsBy(gradeLevelNo);
        for (Section s : sectionList) {
            Object[] rowData = {
                s.getSectionId(), s.getSectionName(), s.getGradeLevel().getLevelNo(),
                s.getAdviser().getLastName() + ", " + s.getAdviser().getFirstName() + " " + s.getAdviser().getMiddleName(),
                s.getSectionSession(), s.getSchoolYear().getYearFrom(),
                s.getIsActive() == true ? "Yes" : "No",s.getSectionType()
            };
            tableModel.addRow(rowData);
        }
        return tableModel;
    }
    
    /**
     * Returns a DefaultTableModel containing sectionId, sectionName,
     * sectionGradeLevel level, sectionAdviser lastname, firstname, middlename,
     * session, schoolyear yearfrom, isactive properties of section. Record
     * returned are <b>active</b> sections of whatever the current schoolyear
     * is.
     *
     * @param jcmbGradeLevel is any JComboBox containing gradelevel level nos
     * @param jTable is any JTable to put the record in the DefaultTableModel
     * @return
     */
    public DefaultTableModel getSectionsOfCurrentSchoolYearByGradeLevelNo(JComboBox jcmbGradeLevel, JTable jTable) {
        DefaultTableModel tableModel = (DefaultTableModel) jTable.getModel();
        tableModel.setRowCount(0);
        SchoolYearDaoImpl schoolYearDaoImpl = new SchoolYearDaoImpl();
        int gradeLevelNo = Integer.parseInt(jcmbGradeLevel.getSelectedItem().toString().trim());
        int schoolYearId = schoolYearDaoImpl.getCurrentSchoolYearId();
        Object[] sectionList = sectionDaoImpl.getSectionsBy(gradeLevelNo, schoolYearId).toArray();
        for (Object o : sectionList) {
            Section s = (Section) o;
            Object[] rowData = {
                s.getSectionId(), s.getSectionName(), s.getGradeLevel().getLevelNo(),
                s.getAdviser().getLastName() + ", " + s.getAdviser().getFirstName() + " " + s.getAdviser().getMiddleName(),
                s.getSectionSession(), s.getSchoolYear().getYearFrom(),
                s.getIsActive() == true ? "Yes" : "No"
            };
            tableModel.addRow(rowData);
        }
        return tableModel;
    }

    public DefaultComboBoxModel getSectionIDsOfCurrentSchoolYearByGradeLevelNo(JComboBox jcmbGradeLevel) {
        DefaultComboBoxModel comboModel = new DefaultComboBoxModel();
        SchoolYearDaoImpl schoolYearDaoImpl = new SchoolYearDaoImpl();
        int gradeLevelNo = Integer.parseInt(jcmbGradeLevel.getSelectedItem().toString().trim());
        int schoolYearId = schoolYearDaoImpl.getCurrentSchoolYearId();
        List<Section> sectionList = sectionDaoImpl.getSectionsBy(gradeLevelNo, schoolYearId);
        for (Section s : sectionList) {
            comboModel.addElement(s.getSectionId());
        }
        return comboModel;
    }
    
    public DefaultComboBoxModel getSectionOfCurrentSchoolYearByGradeLevelNo(JComboBox jcmbGradeLevel) {
        DefaultComboBoxModel comboModel = new DefaultComboBoxModel();
        SchoolYearDaoImpl schoolYearDaoImpl = new SchoolYearDaoImpl();
        int gradeLevelNo = Integer.parseInt(jcmbGradeLevel.getSelectedItem().toString().trim());
        int schoolYearId = schoolYearDaoImpl.getCurrentSchoolYearId();
        List<Section> sectionList = sectionDaoImpl.getSectionsBy(gradeLevelNo, schoolYearId);
        for (Section s : sectionList) {
            comboModel.addElement(s);
        }
        return comboModel;
    }

    public DefaultComboBoxModel getAllSectionIDByStatusAndSchoolYearId(boolean status, int schoolYearId) {
        DefaultComboBoxModel comboModel = new DefaultComboBoxModel();
        List<Section> sectionList = sectionDaoImpl.getSectionsBy(status, schoolYearId);
        for (Section s : sectionList) {
            comboModel.addElement(s.getSectionId());
        }
        return comboModel;
    }
    
    public DefaultComboBoxModel getAllSectionByStatusAndSchoolYearId(boolean status, int schoolYearId) {
        DefaultComboBoxModel comboModel = new DefaultComboBoxModel();
        List<Section> sectionList = sectionDaoImpl.getSectionsBy(status, schoolYearId);
        for (Section s : sectionList) {
            comboModel.addElement(s);
        }
        comboModel.setSelectedItem(null);
        return comboModel;
    }

    public DefaultTableModel getSectionStudentsBySectionIdAndSchoolYearId(JTable table, JComboBox jcmbSection, int schoolYearId) {
        DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
        tableModel.setRowCount(0);
        Section section = (Section)jcmbSection.getSelectedItem();
        SchoolYear schoolYear = new SchoolYear();
        schoolYear.setSchoolYearId(schoolYearId);
        section.setSectionId(section.getSectionId());
        section.setSchoolYear(schoolYear);
        List<Student> studentList = sectionDaoImpl.getSectionStudentsOf(section);
        for (Student s : studentList) {
            Object[] rowData = {
                s.getStudentId(), s.getStudentNo(), s.getRegistration().getLastName(),
                s.getRegistration().getFirstName(), s.getRegistration().getMiddleName(),
                s.getStudentType() == 1 ? "New" : "Old", s.getGradeLevelNo()
            };
            tableModel.addRow(rowData);
        }
        return tableModel;
    }

    public DefaultComboBoxModel getSectionIdsOfSectionsWithNoSchedules(boolean status, int schoolyearId) {
        DefaultComboBoxModel comboModel = new DefaultComboBoxModel();
        List<Section> sectionList = sectionDaoImpl.getSectionsWithNoAssignedScheduleByStatusAndSchoolYearId(status, schoolyearId);
        for (Section s : sectionList) {
            comboModel.addElement(s.getSectionId());
        }
        comboModel.setSelectedItem(null);
        return comboModel;
    }

    public DefaultComboBoxModel getSectionIdsWithoutSchedule(boolean isActive, int schoolyearId, int gradeLevelId) {
        DefaultComboBoxModel comboModel = new DefaultComboBoxModel();
        List<Section> sectionList = sectionDaoImpl.getSectionsWithNoAssignedScheduleBy_Status_SchoolYearId_GradeLevelId(isActive, schoolyearId, gradeLevelId);
        for (Section s : sectionList) {
            comboModel.addElement(s.getSectionId());
        }
        comboModel.setSelectedItem(null);
        return comboModel;
    }
    
    public DefaultComboBoxModel getSectionsWithoutSchedule(boolean isActive, int schoolyearId, int gradeLevelId) {
        DefaultComboBoxModel comboModel = new DefaultComboBoxModel();
        List<Section> sectionList = sectionDaoImpl.getSectionsWithNoAssignedScheduleBy_Status_SchoolYearId_GradeLevelId(isActive, schoolyearId, gradeLevelId);
        for (Section section : sectionList) {
            comboModel.addElement(section);
        }
        comboModel.setSelectedItem(null);
        return comboModel;
    }
    
    public DefaultComboBoxModel getSectionsWithoutScheduleBySectionType(SchoolYear sy, GradeLevel g, String sectionType){
        DefaultComboBoxModel comboModel = new DefaultComboBoxModel();
        List<Section> sectionList = sectionDaoImpl.getSectionsWithNoAssignedSchedBySchoolYearGradeLevelAndSectionType(sy, g, sectionType);
        for(Section section : sectionList){
            comboModel.addElement(section);
        }
        comboModel.setSelectedItem(null);
        return comboModel;
    }
    
    public DefaultComboBoxModel getNonAdvisorySectionsOfFaculty(Faculty faculty, SchoolYear schoolYear){
        DefaultComboBoxModel comboModel = new DefaultComboBoxModel();
        List<Section> sectionList = sectionDaoImpl.getNonAdvisorySectionsOf(faculty,schoolYear);
        for (Section section : sectionList) {
            comboModel.addElement(section);
        }
        comboModel.setSelectedItem(null);
        return comboModel;
    }
    
    public DefaultComboBoxModel getAdvisorySectionsOfFaculty(Faculty faculty, SchoolYear schoolYear){
        DefaultComboBoxModel comboModel = new DefaultComboBoxModel();
        List<Section> sectionList = sectionDaoImpl.getAdvisorySectionsOf(faculty,schoolYear);
        for (Section section : sectionList) {
            comboModel.addElement(section);
        }
        comboModel.setSelectedItem(null);
        return comboModel;
    }
    
    public DefaultComboBoxModel getSectionsBy(SchoolYear schoolYear, GradeLevel gradeLevel, String sectionType){
        DefaultComboBoxModel comboModel = new DefaultComboBoxModel();
        List<Section> sectionList = sectionDaoImpl.getSectionsBy(gradeLevel, sectionType, schoolYear);
        for(Section section : sectionList){
            comboModel.addElement(section);
        }
        comboModel.setSelectedItem(null);
        return comboModel;
    }
}
