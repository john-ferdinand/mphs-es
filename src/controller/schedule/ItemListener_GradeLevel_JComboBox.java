package controller.schedule;

import component_editor.ScheduleSubjectCellEditor;
import component_model_loader.SectionJCompModelLoader;
import daoimpl.CurriculumDaoImpl;
import daoimpl.GradeLevelDaoImpl;
import daoimpl.SubjectDaoImpl;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import model.gradelevel.GradeLevel;
import model.subject.Subject;
import view.schedule.Dialog_CreateSchedule;

/**
 *
 * @author John Ferdinand Antonio
 */
public class ItemListener_GradeLevel_JComboBox implements ItemListener {

    private SubjectDaoImpl subjectDaoImpl;
    private SectionJCompModelLoader sectionJCompModelLoader;
    private GradeLevelDaoImpl gradeLevelDaoImpl;
    private final Dialog_CreateSchedule view;
    private final JComboBox jcmbGradeLevel;
    private final JComboBox jcmbSections;
    private final JTable jtblSchedule;
    private final JButton jbtnRemoveEntry;
    private final JComboBox jcmbRoom;
    private final JComboBox jcmbSchoolYear;

    public ItemListener_GradeLevel_JComboBox(Dialog_CreateSchedule view) {
        this.view = view;
        this.jcmbGradeLevel = view.getJcmbGradeLevel();
        this.jcmbSections = view.getJcmbSection();
        this.jtblSchedule = view.getJtblSchedule();
        this.jbtnRemoveEntry = view.getJbtnRemoveEntry();
        this.jcmbRoom = view.getJcmbRoom();
        this.jcmbSchoolYear = view.getJcmbSchoolYear();

        subjectDaoImpl = new SubjectDaoImpl();
        gradeLevelDaoImpl = new GradeLevelDaoImpl();
        sectionJCompModelLoader = new SectionJCompModelLoader();
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (jcmbGradeLevel.getSelectedIndex() > -1) {
            resetForm();
            loadSubjectsByGradeLevel();
            loadSubjectsToSubjectHrsSummaryTable();
            loadGradeLevelToScheduleHeader();
            enableSectionTypeCombo();
        }
    }
    
    private void loadSubjectsByGradeLevel() {
        TableColumn subjectColumn = jtblSchedule.getColumnModel().getColumn(3);
        subjectColumn.setCellEditor(new ScheduleSubjectCellEditor(jtblSchedule, view.getJcmbGradeLevel()));
    }
    
    private void loadSubjectsToSubjectHrsSummaryTable(){
        JTable table = view.getJtblSubjectHrsSummary();
        DefaultTableModel tModel = (DefaultTableModel) table.getModel();
        tModel.setRowCount(0);
        int gradeLevelId  = ((GradeLevel) jcmbGradeLevel.getSelectedItem()).getGradeLevelId();
        List<Subject> subjects = subjectDaoImpl.getAllSubjectsByGradeLevelId(gradeLevelId);
        for(Subject s : subjects){
            Object[] rowData = {s.getSubjectCode(),"",subjectDaoImpl.getSubjectMinutesPerWeekOf(s)};
            tModel.addRow(rowData);
        }
        view.getJtblSubjectHrsSummary().setModel(tModel);
    }
    
    private void loadGradeLevelToScheduleHeader() {
        GradeLevel gradeLevel = (GradeLevel) jcmbGradeLevel.getSelectedItem();
        view.getJtfGradeLevel().setText(gradeLevel.getLevelNo() == 0 ? "Kindergarten" : gradeLevel.getLevelNo() + "");
    }
    
    private void enableSectionTypeCombo(){
        view.getJcmbSectionType().setEnabled(true);
    }

    private void resetForm(){
        view.getJcmbRoom().setSelectedIndex(-1);
        view.getJbtnGrpDays().clearSelection();
        view.getJtfSectionName().setText("");
        view.getJtfAdviserName().setText("");
        view.getJtfGradeLevel().setText("");
        ((DefaultTableModel)view.getJtblSchedule().getModel()).setRowCount(0);
    }
    
    
    
}
