
package controller.schoolyear;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import view.schoolyear.DialogSchoolYearCrud;

/**
 *
 * @author Jordan
 */
public class Controller_SchoolDaysCalculator_DocumentListener_SyEndDatePickerImpl implements DocumentListener{

    private final DialogSchoolYearCrud view;

    public Controller_SchoolDaysCalculator_DocumentListener_SyEndDatePickerImpl(DialogSchoolYearCrud view) {
        this.view = view;
    }
    
    @Override
    public void insertUpdate(DocumentEvent e) {
        getTotalSchoolDays();
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        getTotalSchoolDays();
    }
    
    private void getTotalSchoolDays(){
        Date syStartDate = (Date) view.getDpSchoolYearStartDate().getModel().getValue();
        Date syEndDate = (Date) view.getDpSchoolYearEndDate().getModel().getValue();
        if (syStartDate != null && syEndDate != null) {
            LocalDate startDate = syStartDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            LocalDate endDate = syEndDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            long totalSchoolDays = ChronoUnit.DAYS.between(startDate,endDate);
            view.getJtfTotalSchoolDays().setText(""+totalSchoolDays);
        }
    }
}
