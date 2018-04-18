
package controller.reports;

import daoimpl.FeeDaoImpl;
import daoimpl.GradeLevelDaoImpl;
import daoimpl.PaymentTermDaoImpl;
import daoimpl.ReportsDaoImpl;
import daoimpl.ScheduleDaoImpl;
import daoimpl.StudentDaoImpl;
import daoimpl.SubjectDaoImpl;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import model.faculty.Faculty;
import model.fee.Fee;
import model.gradelevel.GradeLevel;
import model.paymentterm.PaymentTerm;
import model.schedule.Schedule;
import model.schoolyear.SchoolYear;
import model.student.Student;
import model.subject.Subject;
import model.user.User;
import service.tuition.TuitionPopulator;
import view.reports.Panel_Reports;

/**
 *
 * @author Jordan
 */
public class Controller_Reports_CORtab_Search_JTextField implements KeyListener{
    
    private final Panel_Reports view;
    private final User user;
    private Student student;
    private SchoolYear schoolYear;
    private List<Fee> feeList;
    private final StudentDaoImpl studentDaoImpl;
    private final SubjectDaoImpl subjectDaoImpl;
    private final GradeLevelDaoImpl gradeLevelDaoImpl;
    private final ReportsDaoImpl reportsDaoImpl;
    private final ScheduleDaoImpl scheduleDaoImpl;
    private final FeeDaoImpl feeDaoImpl;
    private final PaymentTermDaoImpl paymentTermDaoImpl;

    public Controller_Reports_CORtab_Search_JTextField(Panel_Reports view,User user) {
        this.view = view;
        this.user = user;
        reportsDaoImpl = new ReportsDaoImpl();
        studentDaoImpl = new StudentDaoImpl();
        subjectDaoImpl = new SubjectDaoImpl();
        gradeLevelDaoImpl = new GradeLevelDaoImpl();
        scheduleDaoImpl = new ScheduleDaoImpl();
        feeDaoImpl = new FeeDaoImpl();
        paymentTermDaoImpl = new PaymentTermDaoImpl();
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getSource() == view.getJtfCorSearchBox()){
            if(e.getKeyCode() == KeyEvent.VK_ENTER){
                if(!view.getJtfCorSearchBox().getText().trim().isEmpty()){
                    if(inputIsValid(view.getJtfCorSearchBox().getText())){
                        if (studentExists(view.getJtfCorSearchBox().getText())) {
                            if(studentAndSchoolYearIsInitialized()){
                                initializeFees();
                                PaymentTerm paymentTerm = paymentTermDaoImpl.getPaymentTermOf(student, schoolYear);
                                initBalanceBreakDownTable(feeList, paymentTerm);
                                loadStudentInfo();
                                loadSubjects();
                                loadFacultyPerSubject();
                                loadDayTimeRoom();
                            }else{
                                SchoolYear sySelected = (SchoolYear) view.getJcmbCorSchoolYear().getSelectedItem();
                                JOptionPane.showMessageDialog(null,"No result found for "+sySelected.getYearFrom()+"-"+sySelected.getYearTo());
                                view.clearCOR();
                            }
                        } else {
                            JOptionPane.showMessageDialog(null,"Student # doesn't exist.");
                            view.clearCOR();
                        }
                    }else{
                        JOptionPane.showMessageDialog(null,"Invalid Input.\n Please enter a valid student no.");
                    }
                }
            }
        }
    }
    
    @Override
    public void keyReleased(KeyEvent e) {
    }
    
    
    private boolean studentAndSchoolYearIsInitialized(){
        int studentNo = Integer.parseInt(view.getJtfCorSearchBox().getText().trim());
        schoolYear = (SchoolYear) view.getJcmbCorSchoolYear().getSelectedItem();
        student = reportsDaoImpl.getCOROf(studentNo, schoolYear);
        if(student.getRegistration() !=null){
            return true;
        }else{
            return false;
        }
    }
    
    private void loadStudentInfo(){
        String studentLastName = student.getRegistration().getLastName();
        String studentFirstName = student.getRegistration().getFirstName();
        String studentMiddleName = student.getRegistration().getMiddleName();
        SchoolYear schoolYear = (SchoolYear) view.getJcmbCorSchoolYear().getSelectedItem();
        String addressNo = student.getRegistration().getAddressRoomOrHouseNo();
        String addressStreet = student.getRegistration().getAddressStreet();
        String addressBrgy = student.getRegistration().getAddressBrgyOrSubd();
        String addressCity = student.getRegistration().getAddressCity();
        String motherMobileNo = student.getRegistration().getMotherMobileNo();
        String fatherMobileNo = student.getRegistration().getFatherMobileNo();
        String guardianMobileNo = student.getRegistration().getGuardianMobileNo();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date dateToday = Calendar.getInstance().getTime();
        view.getJlblCORStudentName().setText(studentLastName+", "+studentFirstName+" "+studentMiddleName);
        view.getJlblCORSchoolYear().setText(schoolYear.getYearFrom()+"-"+schoolYear.getYearTo());
        view.getJlblCORAddressText().setText(addressNo+" "+addressBrgy+" "+addressStreet+" "+addressCity);
        view.getJlblCORStudentNo().setText(""+student.getStudentNo());
        view.getJlblCORStudentType().setText(student.getStudentType()==1?"New":"Old");
        view.getJlblCORSectionName().setText(student.getSection().getSectionName());
        view.getJlblCORContact().setText(motherMobileNo);
        view.getJlblCORGradeLevelText().setText(student.getGradeLevelNo() == 0? "Kindergarten":student.getGradeLevelNo()+"");
        view.getJlblCORDateToday().setText(""+sdf.format(dateToday));
        view.getJlblUserCompleteName().setText(user.getLastName()+", "+user.getFirstName()+" "+user.getMiddleName());
        view.getJlblUserRole().setText(user.getRole().getRoleName());
        Faculty adviser = student.getSection().getAdviser();
        view.getJlblAdviserName().setText(adviser.getLastName()+", "+adviser.getFirstName()+" "+adviser.getMiddleName());
    }
    
    private void loadSubjects() {
        Integer gradeLevelNo = student.getGradeLevelNo();
        if (gradeLevelNo != null) {
            Integer gradeLevelId = gradeLevelDaoImpl.getId(gradeLevelNo);
            List<Subject> subjects = subjectDaoImpl.getAllSubjectsByGradeLevelId(gradeLevelId);
            DefaultTableModel tableModel = (DefaultTableModel) view.getJtblCORSchedule().getModel();
            tableModel.setRowCount(0);
            for (Subject subject : subjects) {
                Object[] rowData = {subject, null, null, null};
                tableModel.addRow(rowData);
            }
            view.getJtblCORSchedule().setModel(tableModel);
        }
    }
    
    private void loadFacultyPerSubject(){
        if(view.getJtblCORSchedule().getRowCount() > 0){
            for(int row = 0; row < view.getJtblCORSchedule().getRowCount(); row++){
                Subject subject = (Subject) view.getJtblCORSchedule().getValueAt(row, 0);
                Faculty f = scheduleDaoImpl.getScheduleFacultyOf(subject, schoolYear);
                StringBuilder teacher = new StringBuilder();
                teacher.append(f.getLastName()+", ");
                teacher.append(f.getFirstName()+" ");
                teacher.append(f.getMiddleName());
                view.getJtblCORSchedule().setValueAt(teacher.toString(), row, 3);
            }
        }
    }
    
    private void loadDayTimeRoom(){
        if(view.getJtblCORSchedule().getRowCount() > 0){
            for(int row = 0; row < view.getJtblCORSchedule().getRowCount(); row++){
                Subject subject = (Subject) view.getJtblCORSchedule().getValueAt(row, 0);
                String days = getDaysOf(subject,schoolYear);
                String time = getTimeOf(subject, schoolYear);
                String room = getRoomOf(subject, schoolYear);
                view.getJtblCORSchedule().setValueAt(days + " "+time, row, 1);
                view.getJtblCORSchedule().setValueAt(room, row, 2);
            }
        }
    }
    
    private String getDaysOf(Subject subject, SchoolYear sy){
        List<Schedule> schedules = scheduleDaoImpl.getScheduleDayTimeRoomOf(subject, sy);
        StringBuilder days = new StringBuilder();
        for(int i = 0; i<schedules.size(); i++){
            days.append(schedules.get(i).getDay());
            if(i != schedules.size()-1){
                days.append(",");
            }
        }
        return days.toString();
    }
    
    private String getTimeOf(Subject subject, SchoolYear sy){
        List<Schedule> schedules = scheduleDaoImpl.getScheduleDayTimeRoomOf(subject, sy);
        String time = "";
        for(int i = 0; i<1; i++){
            String startTime = intToTimeFormat(schedules.get(i).getStartTime());
            String endTime = intToTimeFormat(schedules.get(i).getEndTime());
            time = startTime + "-" + endTime;
        }
        return time;
    }
    
    private String getRoomOf(Subject subject, SchoolYear sy){
        List<Schedule> schedules = scheduleDaoImpl.getScheduleDayTimeRoomOf(subject, sy);
        String room = "";
        for(int i = 0; i<1; i++){
            room = schedules.get(i).getRoom().getRoomName();
        }
        return room;
    }
    
    
    private void initializeFees(){
        feeList = feeDaoImpl.getFeesByGradeLevelId(gradeLevelDaoImpl.getId(student.getGradeLevelNo()));
        setFeeRecordTo("Basic", view.getJtblCORTuition());
        setFeeRecordTo("Miscellaneous", view.getJtblCORMiscellaneous());
        setFeeRecordTo("Others", view.getJtblCOROthers());
        view.getJlblTotalAmount().setText(getFeesSum()+"");
    }
    
    private void initBalanceBreakDownTable(List<Fee> feeList,PaymentTerm paymentTerm) {
        GradeLevel gradeLevel = new GradeLevel();
        gradeLevel.setGradeLevelID(gradeLevelDaoImpl.getId(student.getGradeLevelNo()));
        TuitionPopulator tFeeService = new TuitionPopulator(feeList, paymentTerm,gradeLevel);
        DefaultTableModel tableModel = tFeeService.getTuitionItemsTableModel(view.getJtblCORPaymentAssessment(),gradeLevel);
        view.getJtblCORPaymentAssessment().setModel(tableModel);
    }
    
    private void setFeeRecordTo(String feeCategoryName, JTable table) {
        DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
        tableModel.setRowCount(0);
        for (Fee f : feeList) {
            if (f.getFeeCategory().getName().equalsIgnoreCase(feeCategoryName)) {
                Object[] rowData = {f.getName(), f.getAmount()};
                tableModel.addRow(rowData);
            }
            table.setModel(tableModel);
        }
    }
    
    private BigDecimal getFeesSum() {
        BigDecimal sum = new BigDecimal(BigInteger.ZERO);
        GradeLevel gradeLevel = new GradeLevel();
        gradeLevel.setGradeLevelID(gradeLevelDaoImpl.getId(student.getGradeLevelNo()));
        BigDecimal basic = feeDaoImpl.getBasicByGradeLevel(gradeLevel);
        BigDecimal other = feeDaoImpl.getSumOfOthersFeeByGradeLevelId(gradeLevel);
        BigDecimal misc = feeDaoImpl.getSumOfMiscFeesByGradeLeveLId(gradeLevel);
        sum = basic.add(other).add(misc);
        return sum;
    }
    
    private String intToTimeFormat(int time) {
        int hours = time/100;
        int minutes = (time%100 * 100)/10;
        String str = String.format("%02d%s%02d", hours,":",minutes);
        return str;
    }
    
    private boolean inputIsValid(String studentNo) {
        try {
            Integer.parseInt(studentNo.trim());
            return true;
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    private boolean studentExists(String studentNo){
        int sNo = Integer.parseInt(studentNo.trim());
        return studentDaoImpl.studentExist(sNo);
    }
 
}
