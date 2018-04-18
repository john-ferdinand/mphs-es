package controller.payment;

import daoimpl.AdmissionDaoImpl;
import daoimpl.PaymentTermDaoImpl;
import daoimpl.StudentDaoImpl;
import daoimpl.TuitionFeeDaoImpl;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import model.admission.Admission;
import model.balancebreakdownfee.BalanceBreakDownFee;
import model.discount.Discount;
import model.enrollment.Enrollment;
import model.paymentterm.PaymentTerm;
import model.schoolyear.SchoolYear;
import model.student.Student;
import model.tuitionfee.Tuition;
import model.user.User;
import view.payment.Dialog_MakePayment;
import view.payment.Panel_Payment;

/**
 *
 * @author Jordan
 */
public class Display_Dialog_MakePayment implements ActionListener{
    
    private List<Discount> discounts;
    private final Panel_Payment view;
    private final User user;
    private final PaymentTermDaoImpl paymentTermDaoImpl;
    private final StudentDaoImpl studentDaoImpl;
    private final TuitionFeeDaoImpl tuitionFeeDaoImpl;

    public Display_Dialog_MakePayment(Panel_Payment view) {
        this.view = view;
        this.user = view.getUser();
        this.paymentTermDaoImpl = new PaymentTermDaoImpl();
        this.studentDaoImpl = new StudentDaoImpl();
        this.tuitionFeeDaoImpl = new TuitionFeeDaoImpl();
    }

    private SchoolYear getSchoolYear(Student s, boolean hasTuitionBalance){
        if(hasTuitionBalance){
            return tuitionFeeDaoImpl.getSchoolYearWithTuitionBalanceFor(s.getStudentId());
        }else{
            return view.getCurrentSchoolYear();
        }
    }
    
    private Tuition getTuitionOf(Student s, SchoolYear schoolYear){
        Tuition tuition = tuitionFeeDaoImpl.getBy(s.getStudentId(), schoolYear.getSchoolYearId());
        tuition.setStudent(s);
        return tuition;
    }
    
    private void showMakePaymentDialog(boolean isStudent, boolean hasTuitionBalance, Tuition tuition) {
        if (!balanceIsSettled()) {
            initDialog(isStudent, hasTuitionBalance, tuition);
        } else {
            JOptionPane.showMessageDialog(null, "Balance is settled.");
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        Student student = view.getStudent();
        boolean isStudent = view.getIsStudent();
        if (isStudent) {
            if (student.hasTuitionBalance()) {
                SchoolYear schoolYear = getSchoolYear(student, true);
                Tuition tuition = getTuitionOf(student, schoolYear);
                showMakePaymentDialog(true, true, tuition);
            } else if(!student.hasTuitionBalance()){
                SchoolYear schoolYear = getSchoolYear(student, false);
                Tuition tuition = getTuitionOf(student, schoolYear);
                if (tuitionFeeDaoImpl.hasTuitionOn(student.getStudentId(), schoolYear.getSchoolYearId())) {
                    showMakePaymentDialog(true, false, tuition);
                } else {
                    discounts = view.getDiscounts();
                    Tuition newTuition = createTuitionForExistingStudent(view.getStudent());
                    showMakePaymentDialog(true, false, newTuition);
                }
            }
        } else {
            discounts = view.getDiscounts();
            Tuition tuition = createTuitionFor(student);
            showMakePaymentDialog(false, false, tuition);
        }
    }

    private Admission admissionOf(int studentId){
        AdmissionDaoImpl admissionDaoImpl = new AdmissionDaoImpl();
        Admission admission = admissionDaoImpl.getAdmissionByStudentId(studentId);
        return admission;
    }
    
    private Tuition createTuitionFor(Student s) {
        s.setEnrollment(enrollment());
        Tuition tuition = new Tuition();
        tuition.setStudent(s);
        tuition.setPaymentTerm(paymentTerm());
        tuition.setBalanceBreakDownFees(balanceBreakDownFees());
        tuition.setSchoolyearId(view.getCurrentSchoolYear().getSchoolYearId());
        tuition.setDiscounts(discounts);
        return tuition;
    }
    
    private Tuition createTuitionForExistingStudent(Student s) {
        s.setEnrollment(enrollment());
        s.setAdmission(admissionOf(s.getStudentId()));
        Tuition tuition = new Tuition();
        tuition.setStudent(s);
        tuition.setPaymentTerm(paymentTerm());
        tuition.setBalanceBreakDownFees(balanceBreakDownFees());
        tuition.setSchoolyearId(view.getCurrentSchoolYear().getSchoolYearId());
        tuition.setDiscounts(discounts);
        return tuition;
    }
    
    private boolean balanceIsSettled(){
        double sumOfBalance = 0.00;
        for(int row = 0; row <view.getJtblBalanceBreakDown().getRowCount(); row++){
            sumOfBalance += Double.parseDouble(view.getJtblBalanceBreakDown().getValueAt(row, 2).toString().trim());
        }
        return sumOfBalance <= 0.00;
    }
    
    
    private Enrollment enrollment() {
        Enrollment enrollment = new Enrollment();
        String enrollmentType = paymentTerm().getPaymentTermName().trim().equalsIgnoreCase("Summer") == true ? "S" : "R";
        enrollment.setEnrollmentType(enrollmentType.trim());
        enrollment.setSchoolYearId(view.getCurrentSchoolYear().getSchoolYearId());
        return enrollment;
    }
    
    
    private PaymentTerm paymentTerm() {
        String paymentTermName = view.getJcmbPaymentTerm().getSelectedItem().toString().trim();
        int paymentTermID = paymentTermDaoImpl.getPaymentTermIDByName(paymentTermName);
        PaymentTerm paymentTerm = paymentTermDaoImpl.getPaymentTermByPaymentTermId(paymentTermID);
        return paymentTerm;
    }
    
    private List<BalanceBreakDownFee> balanceBreakDownFees() {
        List<BalanceBreakDownFee> bbFeeList = new ArrayList<>();
        JTable t = view.getJtblBalanceBreakDown();
        for (int i = 0; i < t.getRowCount(); i++) {
            try {
                if(!paymentTerm().getPaymentTermName().trim().equalsIgnoreCase("Cash")){
                    BalanceBreakDownFee bbFee = new BalanceBreakDownFee();
                    String name = t.getValueAt(i, 0).toString().trim();
                    BigDecimal amountDue = BigDecimal.valueOf(Double.parseDouble(t.getValueAt(i, 1).toString().trim())).setScale(2, BigDecimal.ROUND_HALF_UP);
                    BigDecimal balance = BigDecimal.valueOf(Double.parseDouble(t.getValueAt(i, 2).toString().trim())).setScale(2,RoundingMode.HALF_UP);
                    String date = (t.getValueAt(i, 3).toString().trim());
                    boolean isPaid = (t.getValueAt(i, 4).toString().trim().equalsIgnoreCase("Yes"));
                    String category = (t.getValueAt(i, 5).toString().trim());
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                    Date deadline = format.parse(date);
                    bbFee.setDeadline(deadline);
                    bbFee.setIsPaid(isPaid);
                    bbFee.setAmountDue(amountDue);
                    bbFee.setBalance(balance);
                    bbFee.setName(name);
                    bbFee.setCategory(category);
                    bbFeeList.add(bbFee);
                }else{//if cash
                    BalanceBreakDownFee bbFee = new BalanceBreakDownFee();
                    String name = t.getValueAt(i, 0).toString().trim();
                    BigDecimal amountDue = BigDecimal.valueOf(Double.parseDouble(t.getValueAt(i, 1).toString().trim())).setScale(2, BigDecimal.ROUND_HALF_UP);
                    BigDecimal balance = BigDecimal.valueOf(Double.parseDouble(t.getValueAt(i, 2).toString().trim())).setScale(2,RoundingMode.HALF_UP);
                    String date = (t.getValueAt(i, 3).toString().trim());
                    boolean isPaid = (t.getValueAt(i, 4).toString().trim().equalsIgnoreCase("Yes"));
                    String category = (t.getValueAt(i, 5).toString().trim());
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                    Date deadline = format.parse(date);
                    bbFee.setDeadline(deadline);
                    bbFee.setIsPaid(isPaid);
                    bbFee.setAmountDue(amountDue);
                    bbFee.setBalance(balance);
                    bbFee.setName(name);
                    bbFee.setCategory(category);
                    bbFeeList.add(bbFee);
                }
            } catch (ParseException ex) {
                ex.printStackTrace();
            }
        }
        return bbFeeList;
    }
    
    
    private void initDialog(boolean isStudent, boolean hasTuitionBalance, Tuition tuition) {
        Dialog_MakePayment dialog = new Dialog_MakePayment(isStudent, hasTuitionBalance, tuition, user);
        if (dialog.isShowing()) {
            dialog.dispose();
        } else {
            dialog.setModal(true);
            dialog.pack();
            dialog.setLocationRelativeTo(null);
            dialog.setVisible(true);
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        }
    }
}
