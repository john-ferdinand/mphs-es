/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
public class UpdateSchoolYear implements ActionListener{

    private final Panel_SchoolYear panelSchoolYear;
    private final DialogSchoolYearCrud view;
    private final DateUtil dateUtil;
    private final int schoolYearIdOfSelected;
    private final SchoolYearDaoImpl schoolYearDaoImpl;
    
    public UpdateSchoolYear(DialogSchoolYearCrud view, int schoolYearIdOfSelected,Panel_SchoolYear panelSchoolYear) {
        this.view = view;
        this.dateUtil = new DateUtil();
        this.schoolYearIdOfSelected = schoolYearIdOfSelected;
        this.panelSchoolYear = panelSchoolYear;
        this.schoolYearDaoImpl = new SchoolYearDaoImpl();
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        int choice = JOptionPane.showConfirmDialog(null, "Save?", "Save Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if(choice == JOptionPane.YES_OPTION){
            if(update()){
                JOptionPane.showMessageDialog(null,"Successfully saved changes!");
                view.dispose();
                panelSchoolYear.loadSchoolYearMasterList();
            }else{
                JOptionPane.showMessageDialog(null,"Failed to save School Year\n Please contact your support.");
            }
        }
    }
    
    private boolean update(){
        boolean isSuccessful = false;
        SchoolYear schoolYear = getSchoolYear();
        schoolYear.setQuarters(getQuarters());
        isSuccessful = schoolYearDaoImpl.update(schoolYear);
        return isSuccessful;
    }
    
    private SchoolYear getSchoolYear() {
        SchoolYear schoolYear = new SchoolYear();
        try {
            schoolYear.setSchoolYearId(schoolYearIdOfSelected);
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
            schoolYear.setIsActive(view.getJcmbStatus().getSelectedItem().toString().trim().equalsIgnoreCase("Yes")?true:false);
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
