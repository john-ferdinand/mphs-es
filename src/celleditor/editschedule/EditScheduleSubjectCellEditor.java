package celleditor.editschedule;

import component_editor.*;
import daoimpl.SubjectDaoImpl;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.util.EventObject;
import java.util.List;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTable;
import javax.swing.ListCellRenderer;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import model.gradelevel.GradeLevel;
import model.subject.Subject;

/**
 *
 * @author John Ferdinand Antonio
 */
public class EditScheduleSubjectCellEditor extends DefaultCellEditor {

    private final JComboBox jcmbSubject;
    private final DefaultComboBoxModel subjectModel;
    private final SubjectDaoImpl subjectDaoImpl;
    private final JComboBox jcmbGradeLevel;
    private final JTable jtblSchedule;

    public EditScheduleSubjectCellEditor(JTable jtblSchedule, JComboBox jcmbGradeLevel) {
        super(new JComboBox());
        this.jtblSchedule = jtblSchedule;
        this.jcmbGradeLevel = jcmbGradeLevel;
        subjectDaoImpl = new SubjectDaoImpl();
        subjectModel = getAllSubjectNames();
        jcmbSubject = new JComboBox(subjectModel);
        jcmbSubject.setRenderer(new Renderer_Subject());
        jcmbSubject.setEditable(false);
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        return jcmbSubject;
    }

    @Override
    public Object getCellEditorValue() {
        return jcmbSubject.getSelectedItem(); //To change body of generated methods, choose Tools | Templates.
    }

    private DefaultComboBoxModel getAllSubjectNames() {
        DefaultComboBoxModel comboModel = new DefaultComboBoxModel();
        GradeLevel gradeLevel = new GradeLevel();
        gradeLevel.setGradeLevelID(((GradeLevel) jcmbGradeLevel.getSelectedItem()).getGradeLevelId());

        List<Subject> list = subjectDaoImpl.getAllSubjectsByGradeLevelId(gradeLevel.getGradeLevelId());
//        DefaultTableModel scheduleTableModel = (DefaultTableModel) jtblSchedule.getModel();
//        int tableRow = 0;
        for (Subject subject : list) {
            comboModel.addElement(subject);
            comboModel.setSelectedItem(subject);
//            scheduleTableModel.setValueAt(subject, tableRow, 3);
//            tableRow++;
        }
        comboModel.setSelectedItem(null);
        return comboModel;
    }

    @Override
    public boolean isCellEditable(EventObject anEvent) {
        boolean cellEditable = super.isCellEditable(anEvent);
        if (cellEditable && anEvent instanceof MouseEvent) {
            cellEditable = ((MouseEvent) anEvent).getClickCount() == 2;
        }
        if (cellEditable && anEvent instanceof ItemEvent) {
            cellEditable = ((ItemEvent) anEvent).getStateChange() == 1;
        }
        return cellEditable;
    }

    private class Renderer_Subject extends JLabel implements ListCellRenderer<Object> {

        public Renderer_Subject(){
            this.setOpaque(true);
        }
        
        @Override
        public Component getListCellRendererComponent(JList<? extends Object> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            if (value instanceof Subject) {
                Subject s = (Subject) value;
                this.setText(s.getSubjectCode());
            }else{
                this.setText("Select");
            }

            if (isSelected) {
                this.setBackground(Color.YELLOW);
                this.setForeground(Color.BLACK);
            } else {
                this.setBackground(list.getBackground());
                this.setForeground(list.getForeground());
            }
            return this;
        }
    }
}
