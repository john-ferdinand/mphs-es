
package controller.schoolyear;

import daoimpl.SchoolYearDaoImpl;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.quarter.Quarter;
import model.schoolyear.SchoolYear;
import org.jdatepicker.impl.JDatePickerImpl;
import utility.date.DateUtil;
import view.schoolyear.DialogSchoolYearCrud;
import view.schoolyear.Panel_SchoolYear;

/**
 *
 * @author Jordan
 */
public class CreateSchoolYear implements ActionListener{
    
    private final Panel_SchoolYear panelSchoolYear;
    private final DialogSchoolYearCrud view;
    private final SchoolYearDaoImpl schoolYearDaoImpl;
    private final DateUtil dateUtil;

    public CreateSchoolYear(DialogSchoolYearCrud view,Panel_SchoolYear panelSchoolYear) {
        this.view = view;
        this.panelSchoolYear = panelSchoolYear;
        schoolYearDaoImpl = new SchoolYearDaoImpl();
        dateUtil = new DateUtil();
    }

    
    @Override
    public void actionPerformed(ActionEvent e) {
        int choice = JOptionPane.showConfirmDialog(null, "Save School Year?", "Save Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if(choice == JOptionPane.YES_OPTION){
            if(add()){
                JOptionPane.showMessageDialog(null,"Successfully saved School Year!");
                view.dispose();
                panelSchoolYear.loadSchoolYearMasterList();
            }else{
                JOptionPane.showMessageDialog(null,"Failed to save School Year");
            }
        }
    }
    
    private boolean add() {
        boolean isAdded = false;
        SchoolYear schoolYear = new SchoolYear();
        schoolYear = getSchoolYear();
        isAdded = schoolYearDaoImpl.add(schoolYear);
        return isAdded;
    }
    
    private SchoolYear getSchoolYear() {
        SchoolYear schoolYear = new SchoolYear();
        try {
            schoolYear.setYearFrom(Integer.parseInt(view.getJsprYearFrom().getValue().toString().trim()));
            schoolYear.setYearTo(Integer.parseInt(view.getJsprYearTo().getValue().toString().trim()));
            schoolYear.setSchoolYearStartDate(dateUtil.toUtilDate(stringDateOf(view.getDpSchoolYearStartDate())));
            schoolYear.setSchoolYearEndDate(dateUtil.toUtilDate(stringDateOf(view.getDpSchoolYearEndDate())));
            schoolYear.setRegularEnrollmentStartDate(dateUtil.toUtilDate(stringDateOf(view.getDpRegularEnrollmentStartDate())));
            schoolYear.setRegularEnrollmentEndDate(dateUtil.toUtilDate(stringDateOf(view.getDpRegularEnrollmentEndDate())));
            schoolYear.setSummerEnrollmentStartDate(dateUtil.toUtilDate(stringDateOf(view.getDpSummerEnrollmentStartDate())));
            schoolYear.setSummerEnrollmentEndDate(dateUtil.toUtilDate(stringDateOf(view.getDpSummerEnrollmentEndDate())));
            schoolYear.setQuarters(getQuarters());
            schoolYear.setSummerClassStartDate(dateUtil.toUtilDate(stringDateOf(view.getDpSummerClassStart())));
            schoolYear.setSummerClassEndDate(dateUtil.toUtilDate(stringDateOf(view.getDpSummerClassEnd())));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return schoolYear;
    }
    
    private List<Quarter> getQuarters() {
        List<Quarter> quarters = new ArrayList<>();
        try {
            Quarter firstQuarter = new Quarter();
            firstQuarter.setQuarterNo(1);
            firstQuarter.setStartDate(dateUtil.toUtilDate(stringDateOf(view.getDp1stQtrStartDate())));
            firstQuarter.setEndDate(dateUtil.toUtilDate(stringDateOf(view.getDp1stQtrEndDate())));
            firstQuarter.setGradingOpenDate(dateUtil.toUtilDate(stringDateOf(view.getDp1stQtrGradingOpenDate())));
            firstQuarter.setGradingDueDate(dateUtil.toUtilDate(stringDateOf(view.getDp1stQtrGradingDuedate())));
            quarters.add(firstQuarter);
            
            Quarter secondQuarter = new Quarter();
            secondQuarter.setQuarterNo(2);
            secondQuarter.setStartDate(dateUtil.toUtilDate(stringDateOf(view.getDp2ndQtrStartDate())));
            secondQuarter.setEndDate(dateUtil.toUtilDate(stringDateOf(view.getDp2ndQtrEndDate())));
            secondQuarter.setGradingOpenDate(dateUtil.toUtilDate(stringDateOf(view.getDp2ndQtrGradingOpenDate())));
            secondQuarter.setGradingDueDate(dateUtil.toUtilDate(stringDateOf(view.getDp2ndQtrGradingDueDate())));
            quarters.add(secondQuarter);
            
            Quarter thirdQuarter = new Quarter();
            thirdQuarter.setQuarterNo(3);
            thirdQuarter.setStartDate(dateUtil.toUtilDate(stringDateOf(view.getDp3rdQtrStartDate())));
            thirdQuarter.setEndDate(dateUtil.toUtilDate(stringDateOf(view.getDp3rdQtrEndDate())));
            thirdQuarter.setGradingOpenDate(dateUtil.toUtilDate(stringDateOf(view.getDp3rdQtrGradingOpenDate())));
            thirdQuarter.setGradingDueDate(dateUtil.toUtilDate(stringDateOf(view.getDp3rdQtrGradingDueDate())));
            quarters.add(thirdQuarter);
            
            Quarter fourthQuarter = new Quarter();
            fourthQuarter.setQuarterNo(4);
            fourthQuarter.setStartDate(dateUtil.toUtilDate(stringDateOf(view.getDp4thQtrStartDate())));
            fourthQuarter.setEndDate(dateUtil.toUtilDate(stringDateOf(view.getDp4thQtrEndDate())));
            fourthQuarter.setGradingOpenDate(dateUtil.toUtilDate(stringDateOf(view.getDp4thQtrGradingOpenDate())));
            fourthQuarter.setGradingDueDate(dateUtil.toUtilDate(stringDateOf(view.getDp4thQtrGradingDueDate())));
            quarters.add(fourthQuarter);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return quarters;
    }
    
    private String stringDateOf(JDatePickerImpl datePicker){
        String strDate = datePicker.getJFormattedTextField().getText().trim();
        return strDate;
    }
    
}
