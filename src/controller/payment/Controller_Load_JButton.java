package controller.payment;

import component_model_loader.OfficialReceiptJCompModelLoader;
import component_model_loader.PaymentTermJCompModelLoader;
import component_model_loader.TuitionFeesJCompModelLoader;
import daoimpl.FeeDaoImpl;
import daoimpl.GradeLevelDaoImpl;
import daoimpl.PaymentTermDaoImpl;
import daoimpl.RegistrationDaoImpl;
import daoimpl.StudentDaoImpl;
import daoimpl.TuitionFeeDaoImpl;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableModel;
import model.admission.Admission;
import model.discount.Discount;
import model.fee.Fee;
import model.gradelevel.GradeLevel;
import model.paymentterm.PaymentTerm;
import model.registration.Registration;
import model.schoolyear.SchoolYear;
import model.student.Student;
import model.tuitionfee.Tuition;
import model.user.User;
import service.tuition.TuitionPopulator;
import view.payment.Dialog_SearchStudentByKeyword;
import view.payment.Panel_Payment;

/**
 *
 * @author Jordan
 */
public class Controller_Load_JButton implements ActionListener {

    private final Dialog_SearchStudentByKeyword searchResultDialog;
    private final Panel_Payment view;
    private final RegistrationDaoImpl registrationDaoImpl;
    private final StudentDaoImpl studentDaoImpl;
    private final GradeLevelDaoImpl gradeLevelDaoImpl;
    private final FeeDaoImpl feeDaoImpl;
    private final PaymentTermDaoImpl paymentTermDaoImpl;
    private final TuitionFeeDaoImpl tuitionFeeDaoImpl;
    private final PaymentTermJCompModelLoader paymentTermJCompModelLoader;
    private final TuitionFeesJCompModelLoader tuitionFeesJCompModelLoader;
    private Student student;
    private List<Fee> feeList;
    private PaymentTerm paymentTerm;
    private final SchoolYear currentSchoolYear;
    private final User user;

    public Controller_Load_JButton(Dialog_SearchStudentByKeyword searchResultDialog, Panel_Payment view, SchoolYear currentSchoolYear, User user) {
        this.searchResultDialog = searchResultDialog;
        this.view = view;
        this.currentSchoolYear = currentSchoolYear;
        this.user = user;
        this.registrationDaoImpl = new RegistrationDaoImpl();
        this.studentDaoImpl = new StudentDaoImpl();
        this.gradeLevelDaoImpl = new GradeLevelDaoImpl();
        this.feeDaoImpl = new FeeDaoImpl();
        this.paymentTermDaoImpl = new PaymentTermDaoImpl();
        this.tuitionFeeDaoImpl = new TuitionFeeDaoImpl();
        this.paymentTermJCompModelLoader = new PaymentTermJCompModelLoader();
        this.tuitionFeesJCompModelLoader = new TuitionFeesJCompModelLoader();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == searchResultDialog.getJbtnLoad()) {
            if (searchResultDialog.getJtblStudents().getSelectedRowCount() > 0) {
                JTable t = searchResultDialog.getJtblStudents();
                int registrationId = Integer.parseInt(t.getValueAt(t.getSelectedRow(), 0).toString().trim());
                int admissionId = Integer.parseInt(t.getValueAt(t.getSelectedRow(), 1).toString().trim());
                int studentNo = Integer.parseInt(t.getValueAt(t.getSelectedRow(), 3).toString().trim());
                boolean isStudent = (studentNo != 0);
                if (!isStudent) {
                    Registration registration = registrationDaoImpl.getRegistrationInfoById(registrationId);
                    Admission admission = new Admission();
                    admission.setAdmissionId(admissionId);
                    initStudentBy(registration, admission);
                    view.clearForm();
                    initForm(false);
                } else {
                    initStudentBy(studentNo);
                    view.clearForm();
                    initForm(true);
                }
            }
            searchResultDialog.dispose();
        }
    }

    private void initStudentBy(int studentNo) {
        student = studentDaoImpl.getStudentByStudentNo(studentNo);
    }

    private void initStudentBy(Registration registration, Admission admission) {
        student = new Student();
        student.setRegistration(registration);
        student.setAdmission(admission);
    }

    private void initForm(boolean isStudent) {
        view.clearForm();
        initStudentDetails(isStudent);
        initFees();
        applyBalanceBreakDownTableModelListener();

        if (isStudent) {
            if (student.hasTuitionBalance()) {
                initFormFor(student, true);
            } else {
                if (tuitionFeeDaoImpl.hasTuitionOn(student.getStudentId(), currentSchoolYear.getSchoolYearId())) {
                    initFormFor(student, false);
                } else {
                    view.getJlblHasBalanceFromPreviousSY().setVisible(false);
                    prepareFormForNewTuition(isStudent);
                }
            }
        } else {
            view.getJlblHasBalanceFromPreviousSY().setVisible(false);
            prepareFormForNewTuition(isStudent);
        }
    }

    private void initFormFor(Student s, boolean hasBalance) {
        SchoolYear schoolYear = (hasBalance == true)? (tuitionFeeDaoImpl.getSchoolYearWithTuitionBalanceFor(s.getStudentId())) : (currentSchoolYear);
        if (schoolYear.getSchoolYearId() != currentSchoolYear.getSchoolYearId()) {
            view.getJlblHasBalanceFromPreviousSY().setVisible(hasBalance);
            view.getJtfTuitionBalance().setText(""+tuitionFeeDaoImpl.getTuitionBalanceOf(s, schoolYear));
        }else{
//            JOptionPane.showMessageDialog(null,s.getStudentId()+", "+schoolYear.getSchoolYearId());
            view.getJtfTuitionBalance().setText(""+tuitionFeeDaoImpl.getTuitionBalanceOf(s, schoolYear));
        }
        
        removeModeOfPaymentItemListener();
        Tuition tuition = tuitionFeeDaoImpl.getBy(s.getStudentId(), schoolYear.getSchoolYearId());
        view.getJcmbPaymentTerm().setSelectedItem(tuition.getPaymentTerm().getPaymentTermName().trim());
        view.getJcmbModeOfPayment().setSelectedItem(tuition.getPaymentTerm().getPaymentTermName().trim().equalsIgnoreCase("Cash") ? "Cash" : "Installment");

        initDiscountOf(tuition);
        view.getJcmbPaymentTerm().setEnabled(false);
        view.setIsStudent(true);
        view.setStudent(s);
        view.setFeeList(feeList);
        initBalanceBreakDownTable(schoolYear);
        initReceiptsMasterListTable();
        initAssignSummerFeeButton();
    }
    
    private void prepareFormForNewTuition(boolean isStudent) {
        view.getJtfTuitionBalance().setText(""+basic().add(misc()).add(others()));
        view.getJcmbPaymentTerm().setEnabled(true);
        applyModeOfPaymentItemListener();
        applyPaymentTermItemListener();
        view.setIsStudent(isStudent);
        view.setStudent(student);
        view.setFeeList(feeList);
        view.getJcmbModeOfPayment().setEnabled(true);
    }
    
    private void initDiscountOf(Tuition tuition) {
        BigDecimal totalDiscount = new BigDecimal(BigInteger.ZERO).setScale(2, BigDecimal.ROUND_HALF_UP);
        for (Discount d : tuition.getDiscounts()) {
            totalDiscount = totalDiscount.add(d.getAmount());
        }
        if (tuition.getDiscounts().size() > 0) {
            view.getJcbDiscount().setSelected(true);
            view.getJcbDiscount().setEnabled(false);
        } else {
            view.getJcbDiscount().setSelected(false);
            view.getJcbDiscount().setEnabled(true);
        }
        DefaultTableModel discountTableModel = (DefaultTableModel) view.getJtblDiscounts().getModel();
        for (Discount d : tuition.getDiscounts()) {
            Object[] rowData = {
                d.getSchoolYear().getYearFrom() + "-" + d.getSchoolYear().getYearTo(), d.getDiscountName(),
                d.getPercent(), d.getAmount(), d.getProvision(), d.getDateApplied()
            };
            discountTableModel.addRow(rowData);
        }
        view.getJtblDiscounts().setModel(discountTableModel);
        view.getJtfDiscount().setText("" + totalDiscount);
    }
    
    private void applyModeOfPaymentItemListener() {
        view.getJcmbModeOfPayment().addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    String modeOfPayment = e.getItem().toString().trim();
                    DefaultComboBoxModel comboModel = (DefaultComboBoxModel) view.getJcmbPaymentTerm().getModel();
                    if (modeOfPayment.equalsIgnoreCase("Installment")) {
                        comboModel.removeAllElements();
                        comboModel.addElement("Quarterly");
                        comboModel.addElement("Semestral");
                        comboModel.addElement("Monthly");
                        loadCurrentSyTuitionForInstallment();
                    } else if (modeOfPayment.equalsIgnoreCase("Cash")) {
                        comboModel.removeAllElements();
                        comboModel.addElement("Cash");
                        loadCurrentSyTuitionForCash();
                    }
                }
            }
        });
    }
    
    private void removeModeOfPaymentItemListener() {
        ItemListener[] itemListeners = view.getJcmbModeOfPayment().getItemListeners();
        for (ItemListener il : itemListeners) {
            view.getJcmbModeOfPayment().removeItemListener(il);
        }
    }
    
    private void loadCurrentSyTuitionForCash(){
        view.getJtfDownPayment().setText("");
        view.getJtfBasicFee().setText(""+basic().setScale(2, RoundingMode.HALF_UP));
        view.getJtfMiscellaneous().setText(""+misc().setScale(2, RoundingMode.HALF_UP));
        view.getJtfOtherFees().setText(""+others().setScale(2, RoundingMode.HALF_UP));
        view.getJtfTotal().setText(""+basic().add(misc()).add(others()));
        view.getJbtnMakePayment().setEnabled(true);
    }
    
    private void loadCurrentSyTuitionForInstallment(){
        view.getJtfDownPayment().setText(""+downpayment());
        view.getJtfBasicFee().setText(""+basic().setScale(2, RoundingMode.HALF_UP));
        view.getJtfMiscellaneous().setText(""+misc().setScale(2, RoundingMode.HALF_UP));
        view.getJtfOtherFees().setText(""+others().setScale(2, RoundingMode.HALF_UP));
        view.getJtfTotal().setText(""+basic().add(misc()).add(others()));
    }
    
    private BigDecimal downpayment() {
        BigDecimal downpayment = feeDaoImpl.getDownpaymentByGradeLevel(gradeLevel());
        return downpayment;
    }

    private BigDecimal basic() {
        BigDecimal basic = feeDaoImpl.getBasicByGradeLevel(gradeLevel());
        return basic;
    }

    private BigDecimal misc() {
        BigDecimal misc = feeDaoImpl.getSumOfMiscFeesByGradeLeveLId(gradeLevel());
        return misc;
    }

    private BigDecimal others() {
        BigDecimal others = feeDaoImpl.getSumOfOthersFeeByGradeLevelId(gradeLevel());
        return others;
    }
    
    private void initBalanceBreakDownTable(SchoolYear schoolYear){
        JTable table = view.getJtblBalanceBreakDown();
        int studentId = student.getStudentId();
        table.setModel(tuitionFeesJCompModelLoader.getTuitionByStudentIdAndSchoolYearId(table, studentId, schoolYear.getSchoolYearId()));
    }
    
    private void initReceiptsMasterListTable(){
        OfficialReceiptJCompModelLoader officialReceiptJCompModelLoader = new OfficialReceiptJCompModelLoader();
        JTable t = view.getJtblReceipts();
        int studentId = student.getStudentId();
        t.setModel(officialReceiptJCompModelLoader.getAllOfficialReceiptsByStudentId(t, studentId));
    }
    
    private void initAssignSummerFeeButton() {
        view.getJbtnAssignSummerFee().addActionListener(new Controller_Display_Dialog_AssignSummerFees_JButton(view, user,student, currentSchoolYear));
        if (student.isRecommendedToTakeSummer()) {
            view.getJlblRecommendForSummerMessage().setText("Student is recommended for summer.");
            view.getJbtnAssignSummerFee().setEnabled(true);
        } else {
            view.getJbtnAssignSummerFee().setEnabled(false);
        }
    }

    private void applyPaymentTermItemListener() {
        view.getJcmbPaymentTerm().addItemListener((ItemEvent e) -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                String paymentTermName = e.getItem().toString().trim();
                int paymentTermID = paymentTermDaoImpl.getPaymentTermIDByName(paymentTermName);
                paymentTerm = paymentTermDaoImpl.getPaymentTermByPaymentTermId(paymentTermID);
                initBalanceBreakDownTable(paymentTerm);
                view.getJtfDiscount().setText("");
                view.getJcbDiscount().setSelected(false);
                view.getJcbDiscount().setEnabled(true);
                view.getJbtnSelectDiscount().setEnabled(false);
            }
        });
    }

    private void initBalanceBreakDownTable(PaymentTerm paymentTerm) {
        TuitionPopulator tuitionPopulator = new TuitionPopulator(feeList, paymentTerm,gradeLevel());
        DefaultTableModel tableModel = tuitionPopulator.getTuitionItemsTableModel(view.getJtblBalanceBreakDown(),gradeLevel());
        view.getJtblBalanceBreakDown().setModel(tableModel);
    }

    private void initStudentDetails(boolean hasStudentNo) {
        int gradeLevelNo = (hasStudentNo == true) ? student.getGradeLevelNo() : student.getRegistration().getGradeLevelNo();
        String studentNo = (hasStudentNo == true) ? student.getStudentNo() + "" : "";
        Object studentType = (hasStudentNo == true) ? student.getStudentType() : student.getRegistration().getStudentType();
        if (studentType instanceof Integer) {
            view.getJtfStudentType().setText(Integer.parseInt(studentType.toString()) == 1 ? "New" : "Old");
        } else if (studentType instanceof String) {
            view.getJtfStudentType().setText(studentType.toString().equalsIgnoreCase("N") ? "New" : studentType.toString().equalsIgnoreCase("T") ? "Transferee" : "Old");
        }
        view.getJcmbPaymentTerm().setModel(paymentTermJCompModelLoader.getPaymentTermNames());
        view.getJtfStudentNo().setText(studentNo);
        view.getJtfLastName().setText(student.getRegistration().getLastName());
        view.getJtfFirstName().setText(student.getRegistration().getFirstName());
        view.getJtfMiddleName().setText(student.getRegistration().getMiddleName());
        view.getJtfGradeLevel().setText(gradeLevelNo == 0 ? "Kindergarten" : gradeLevelNo + "");
        view.getJtfStatus().setText(student.isActive() == true ? "Active" : "Inactive");
    }

    private void initFees() {
        feeList = feeDaoImpl.getFeesByGradeLevelId(gradeLevel().getGradeLevelId());
        initFeeTableModelListenerFor(view.getJtblDownpayment(), view.getJtfDownPayment());
        initFeeTableModelListenerFor(view.getJtblBasic(), view.getJtfBasicFee());
        initFeeTableModelListenerFor(view.getJtblMiscellaneous(), view.getJtfMiscellaneous());
        initFeeTableModelListenerFor(view.getJtblOthers(), view.getJtfOtherFees());
        view.getJtfTotal().setText("" + getFeesSum());
        setFeeRecordTo("Downpayment", view.getJtblDownpayment());
        setFeeRecordTo("Others", view.getJtblOthers());
        setFeeRecordTo("Miscellaneous", view.getJtblMiscellaneous());
        setFeeRecordTo("Basic", view.getJtblBasic());
    }

    private void initFeeTableModelListenerFor(JTable table, JTextField textField) {
        table.getModel().addTableModelListener((TableModelEvent e) -> {
            BigDecimal sum = new BigDecimal(BigInteger.ZERO);
            DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
            for (int i = 0; i < tableModel.getRowCount(); i++) {
                BigDecimal amountDue = BigDecimal.valueOf(Double.parseDouble(tableModel.getValueAt(i, 1).toString().trim()));
                sum = sum.add(amountDue).setScale(2, BigDecimal.ROUND_HALF_UP);
            }
            textField.setText("" + sum);
        });
    }

    private void applyBalanceBreakDownTableModelListener() {
        view.getJtblBalanceBreakDown().getModel().addTableModelListener((TableModelEvent e) -> {
            view.getJbtnMakePayment().setEnabled(view.getJtblBalanceBreakDown().getRowCount() > 0);
        });
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

    private GradeLevel gradeLevel(){
        String gl = view.getJtfGradeLevel().getText().trim();
        int levelNo = gl.equalsIgnoreCase("Kindergarten")? 0 : Integer.parseInt(gl.trim());
        GradeLevel gradeLevel = new GradeLevel();
        gradeLevel.setGradeLevelID(gradeLevelDaoImpl.getId(levelNo));
        return gradeLevel;
    }
    
    private BigDecimal getFeesSum() {
        BigDecimal basic = feeDaoImpl.getBasicByGradeLevel(gradeLevel());
        BigDecimal misc = feeDaoImpl.getSumOfMiscFeesByGradeLeveLId(gradeLevel());
        BigDecimal others = feeDaoImpl.getSumOfOthersFeeByGradeLevelId(gradeLevel());
        BigDecimal feeSum = basic.add(misc).add(others).setScale(2, RoundingMode.HALF_UP);
        return feeSum;
    }
}
