package controller.editschedule;

import controller.schedule.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import utility.form.FormValidator;
import view.schedule.Dialog_CreateSchedule;

/**
 *
 * @author John Ferdinand Antonio
 */
public class Edit_ActionListener_Schedule_LoadToSummary_JButton implements ActionListener , FormValidator{

    private final Dialog_CreateSchedule view;
    private final JTable jtblSchedule;
//    private final JTable jtblScheduleSummary;

    public Edit_ActionListener_Schedule_LoadToSummary_JButton(Dialog_CreateSchedule view) {
        this.view = view;
        this.jtblSchedule = view.getJtblSchedule();
//        this.jtblScheduleSummary = view.getJtblScheduleSummary();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
//        if (formIsValid()) {
//            DefaultTableModel schedSummaryTableModel = (DefaultTableModel) jtblScheduleSummary.getModel();
//            schedSummaryTableModel.setRowCount(jtblSchedule.getRowCount() * 3);
//            jtblScheduleSummary.setModel(schedSummaryTableModel);
//            loadData();
//        }else{            
//            JOptionPane.showMessageDialog(null,"Please check form for empty fields or conflict.");
//        }
    }

    @Override
    public boolean formIsValid() {
        boolean isValid = true;
        DefaultTableModel tableModel = (DefaultTableModel) jtblSchedule.getModel();
        for (int row = 0; row < tableModel.getRowCount(); row++) {
            Object startTime = tableModel.getValueAt(row, 1);
            Object endTime = tableModel.getValueAt(row, 2);
            Object subject = tableModel.getValueAt(row, 3);
            Object faculty = tableModel.getValueAt(row, 4);
            Object room = tableModel.getValueAt(row, 5);
            Object session = tableModel.getValueAt(row, 6);
            if (startTime == null || endTime == null || subject == null || faculty == null || room == null || session == null) {
                isValid = false;
                break;
            } else if (startTime != null && endTime != null && session != null) {
                int sTime = Integer.parseInt(startTime.toString().trim().replace(":", ""));
                int eTime = Integer.parseInt(endTime.toString().trim().replace(":", ""));
                if (hasDuplicateStartTime(startTime) || sTime == eTime || sTime > eTime || !startTimeMatchesSession(sTime, session)) {
                    isValid = false;
                    break;
                }
            }
        }
        return isValid;
    }
    
    private void loadData() {
        int dayCol = 0;
        int startTimeCol = 1;
        int endTimeCol = 2;
        int subjCol = 3;
        int facultyCol = 4;
        int roomCol = 5;
        
        for (int row = 0; row < jtblSchedule.getRowCount(); row++) {
            Object day = jtblSchedule.getValueAt(row, dayCol);
            Object startTime = jtblSchedule.getValueAt(row, startTimeCol);
            Object endTime = jtblSchedule.getValueAt(row, endTimeCol);
            StringBuilder timeBuilder = new StringBuilder(startTime.toString() + "-" + endTime);
            setScheduleSummaryTimeAt(timeBuilder, row, 0);
            for (int col = 0; col < jtblSchedule.getColumnCount(); col++) {
                if (col == subjCol || col == facultyCol || col == roomCol) {
                    Object value = jtblSchedule.getValueAt(row, col);
                    setScheduleSummaryValueAt(value, getColFor(day));
                }
            }
        }
    }
    
    private void setScheduleSummaryTimeAt(Object value, int row, int ssCol) {
        int rowIncrement = row == 0 ? 1 : 3;
//        for (int ssRow = 0; ssRow < jtblScheduleSummary.getRowCount(); ssRow += rowIncrement) {
//            if (jtblScheduleSummary.getValueAt(ssRow, ssCol) == null && ssRow % 3 == 0) {
//                jtblScheduleSummary.setValueAt(value, ssRow, ssCol);
//                break;
//            }
//        }
    }
    
    private void setScheduleSummaryValueAt(Object value, int ssCol){
//        for (int ssRow = 0; ssRow < jtblScheduleSummary.getRowCount(); ssRow++) {
//            if(jtblScheduleSummary.getValueAt(ssRow, ssCol) == null){
//                jtblScheduleSummary.setValueAt(value, ssRow, ssCol);
//                break;
//            }
//        }
    }

    private int getColFor(Object day){
        int col = day.toString().trim().equalsIgnoreCase("Mon") ? 1
                : day.toString().trim().equalsIgnoreCase("Tue") ? 2
                : day.toString().trim().equalsIgnoreCase("Wed") ? 3
                : day.toString().trim().equalsIgnoreCase("Thu") ? 4 : 5;
        return col;
    }
    
    private boolean startTimeMatchesSession(int startTime, Object session) {
        boolean valid;
        if (session.toString().trim().equalsIgnoreCase("PM")) {
            valid = ((startTime >= 1200) && (startTime <= 1600));
        } else if (session.toString().trim().equalsIgnoreCase("AM")) {
            valid = ((startTime >= 700) && (startTime <= 1100));
        } else {//WD
            valid = ((startTime >= 700) && (startTime <= 1600));
        }
        return valid;
    }
    
    private boolean hasDuplicateStartTime(Object value) {
        int countOfRecord = 0;
        int startTimeCol = 1;
        for (int row = 0; row < jtblSchedule.getRowCount(); row++) {
            if (value.equals(jtblSchedule.getValueAt(row, startTimeCol))) {
                countOfRecord++;
            }
        }
        return countOfRecord > 1;
    }
}
