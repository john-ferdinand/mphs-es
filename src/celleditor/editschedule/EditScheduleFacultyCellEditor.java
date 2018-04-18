package celleditor.editschedule;

import component_renderers.Renderer_Schedule_Faculty_CellJComboBox;
import daoimpl.FacultyDaoImpl;
import daoimpl.SchoolYearDaoImpl;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.util.EventObject;
import java.util.List;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JTable;
import model.faculty.Faculty;
import model.subject.Subject;
import view.schedule.Dialog_CreateSchedule;
import view.schedule.Dialog_EditSchedule;

/**
 *
 * @author John Ferdinand Antonio
 */
public class EditScheduleFacultyCellEditor extends DefaultCellEditor {

    private final Dialog_EditSchedule editScheduleDialog;
    private final SchoolYearDaoImpl schoolYearDaoImpl;
    private final FacultyDaoImpl facultyDaoImpl;
    private final JComboBox jcmbFaculty;
    private final DefaultComboBoxModel facultyModel;
    private final JTable jtblSchedule;

    public EditScheduleFacultyCellEditor(JTable jtblSchedule, Dialog_EditSchedule editScheduleDialog) {
        super(new JComboBox());
        this.jtblSchedule = jtblSchedule;
        this.editScheduleDialog = editScheduleDialog;
        schoolYearDaoImpl = new SchoolYearDaoImpl();
        facultyDaoImpl = new FacultyDaoImpl();
        jcmbFaculty = new JComboBox();
        jcmbFaculty.setFont(new Font("Tahoma", 1, 14));
        facultyModel = getFacultyModel();
        jcmbFaculty.setModel(facultyModel);
        jcmbFaculty.setEditable(false);
        jcmbFaculty.setRenderer(new Renderer_Schedule_Faculty_CellJComboBox());
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        return jcmbFaculty;
    }

    @Override
    public Object getCellEditorValue() {
        return jcmbFaculty.getSelectedItem(); //To change body of generated methods, choose Tools | Templates.
    }

    private DefaultComboBoxModel getFacultyModel() {
        int rowSelected = jtblSchedule.getSelectedRow();
        int currentSchoolYearId = schoolYearDaoImpl.getCurrentSchoolYearId();
        Subject subject = (Subject) jtblSchedule.getValueAt(rowSelected, 3);
        String sectionType = editScheduleDialog.getJcmbSectionType().toString().trim();
        DefaultComboBoxModel model = new DefaultComboBoxModel();
        if (jtblSchedule.getValueAt(rowSelected, 3) != null) {
            if (!sectionType.equalsIgnoreCase("S")) {
                List<Faculty> list = facultyDaoImpl.getAllFacultyHandlingSubjectBySubjectCode(subject.getSubjectCode(), currentSchoolYearId);
                for (Faculty f : list) {
                    model.addElement(f);
                }
            } else {
                //load faculty of subject whose class type is Summer
            }
        }
        return model;
    }

    @Override
    public boolean isCellEditable(EventObject anEvent) {
        boolean cellEditable = super.isCellEditable(anEvent);
        if (cellEditable && anEvent instanceof MouseEvent) {
            cellEditable = ((MouseEvent) anEvent).getClickCount() == 2;
        }
        return cellEditable;
    }
}
