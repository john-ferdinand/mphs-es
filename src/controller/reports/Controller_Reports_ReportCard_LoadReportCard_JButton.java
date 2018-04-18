
package controller.reports;

import daoimpl.ReportsDaoImpl;
import daoimpl.SectionDaoImpl;
import daoimpl.StudentDaoImpl;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.gradelevel.GradeLevel;
import model.reportcard.ReportCard;
import model.schoolyear.SchoolYear;
import model.section.Section;
import model.student.Student;
import model.subject.Subject;
import utility.form.FormValidator;
import view.reports.Dialog_ReportCard_SearchStudent;
import view.reports.Panel_Reports;

/**
 *
 * @author Jordan
 */
public class Controller_Reports_ReportCard_LoadReportCard_JButton implements FormValidator, ActionListener{

    private final Panel_Reports panelReports;
    private final Dialog_ReportCard_SearchStudent dialog;
    private final StudentDaoImpl studentDaoImpl;
    private final ReportsDaoImpl reportCardDaoImpl;
    private final SectionDaoImpl sectionDaoImpl;

    public Controller_Reports_ReportCard_LoadReportCard_JButton(Panel_Reports panelReports, Dialog_ReportCard_SearchStudent dialog) {
        this.panelReports = panelReports;
        this.dialog = dialog;
        this.studentDaoImpl = new StudentDaoImpl();
        this.reportCardDaoImpl = new ReportsDaoImpl();
        this.sectionDaoImpl = new SectionDaoImpl();
    }
    
    @Override
    public boolean formIsValid() {
        boolean isValid = true;
        if(dialog.getJcmbGradeLevel().getSelectedIndex() <= -1){
            JOptionPane.showMessageDialog(null,"Please select gradelevel.");
            isValid = false;
        }else if(dialog.getJtblSearchResult().getSelectedRowCount() == 0){
            JOptionPane.showMessageDialog(null,"Please select student.");
            isValid = false;
        }else if(dialog.getJcmbReportCardFilterBySchoolYear().getSelectedIndex() <= -1){
            JOptionPane.showMessageDialog(null,"Please select schoolyear.");
            isValid = false;
        }
        return isValid;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(formIsValid()){
            panelReports.clearReportCard();
            if(loadReportCard()){
               dialog.dispose();
            }else{
                 JOptionPane.showMessageDialog(null,"No grades found for selected year and gradelevel.");
            }
        }
    }
    
    private Student student(){
        int rowSelected = dialog.getJtblSearchResult().getSelectedRow();
        int studentId = Integer.parseInt(dialog.getJtblSearchResult().getValueAt(rowSelected, 0).toString().trim());
        Student student = studentDaoImpl.getStudentByStudentId(studentId);
        return student;
    }
    
    private GradeLevel gradeLevel(){
        GradeLevel gradeLevel = (GradeLevel) dialog.getJcmbGradeLevel().getSelectedItem();
        return gradeLevel;
    }
    
    private SchoolYear schoolYear(){
         SchoolYear schoolYear = (SchoolYear) dialog.getJcmbReportCardFilterBySchoolYear().getSelectedItem();
         return schoolYear;
    }
    
    private void loadReportCardHeader(){
        Student student = student();
        GradeLevel gradeLevel = gradeLevel();
        SchoolYear schoolYear = schoolYear();
        Section section = section();
        panelReports.getJlblReportCardStudentName().setText("Student: " + student.getRegistration().getLastName() + ", " + student.getRegistration().getFirstName() + " " + student.getRegistration().getMiddleName());
        panelReports.getJlblReportCardsGradeLevelText().setText(gradeLevel.getLevelNo() == 0 ? "Kindergarten" : gradeLevel.getLevelNo() + "");
        panelReports.getJlblReportCardsSchoolYearText().setText("" + schoolYear.getYearFrom() + "-" + schoolYear.getYearTo());
        panelReports.getJlblReportCardsSectionNameText().setText(section.getSectionName());
//        panelReports.getJlblReportCardsAdviserNameText().setText(""+section.getAdviser().getLastName()+", "+section.getAdviser().getFirstName());
    }
    
    private Section section(){
        Section section = sectionDaoImpl.getSectionOf(student(), gradeLevel(), schoolYear(), "R");
        return section;
    }
    
    private boolean loadReportCard() {
        boolean hasRecordLoaded;
        loadReportCardHeader();
        DefaultTableModel tableModel = (DefaultTableModel) panelReports.getJtblReportCard().getModel();
        tableModel.setRowCount(0);
        ReportCard reportCard = reportCardDaoImpl.getReportCardOf(student(), gradeLevel(), schoolYear());
        int generalAverage = 0;
        int rowCount = 0;
        for (Subject subject : reportCard.getSubjects()) {
            int firstGp = subject.getFirstGradingPeriodAverage().getValue();
            int secondGp = subject.getSecondGradingPeriodAverage().getValue();
            int thirdGp = subject.getThirdGradingPeriodAverage().getValue();
            int fourthGp = subject.getFourthGradingPeriodAverage().getValue();
            int average = (firstGp + secondGp + thirdGp + fourthGp) / 4;
            Object[] rowData = {
                subject.getSubjectTitle(),firstGp, secondGp, thirdGp, fourthGp, average, (average > 75 ? "Passed" : "Summer")
            };
            tableModel.addRow(rowData);
            rowCount++;
            generalAverage += average;
        }
        if(generalAverage > 0 && rowCount > 0){
            panelReports.getJtfReportCardGeneralAverage().setText(""+generalAverage/rowCount);
        }
        
        hasRecordLoaded = (tableModel.getRowCount() > 0);
        return hasRecordLoaded;
    }
}
