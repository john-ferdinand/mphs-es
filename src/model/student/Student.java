
package model.student;

import model.tuitionfee.Tuition;
import java.util.Date;
import model.admission.Admission;
import model.enrollment.Enrollment;
import model.paymentterm.PaymentTerm;
import model.promotionInfo.Promotion;
import model.registration.Registration;
import model.section.Section;
import model.subject.Subject;


public class Student {
    private int studentId;
    private int studentNo;
    private int studentType;
    public int gradeLevelNo; //current gradelevelNo
    private Registration registration;
    private Admission admission;
    private PaymentTerm paymentTerm;
    private Enrollment enrollment;
    private Section section;
    private boolean exists;
    private boolean isActive;
    private boolean isGraduated;
    private boolean isRecommendedToTakeSummer;
    private boolean hasTuitionBalance;
    private Date entryDate;
    private Date dateGraduated;
    private Tuition tuitionFee;
    private Promotion promotion;
    private Subject subject;

    public boolean hasTuitionBalance() {
        return hasTuitionBalance;
    }

    public void setHasTuitionBalance(boolean hasTuitionBalance) {
        this.hasTuitionBalance = hasTuitionBalance;
    }

    
    
    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public boolean isRecommendedToTakeSummer() {
        return isRecommendedToTakeSummer;
    }

    public void setIsRecommendedToTakeSummer(boolean isRecommendedToTakeSummer) {
        this.isRecommendedToTakeSummer = isRecommendedToTakeSummer;
    }
    
    public Promotion getPromotion() {
        return promotion;
    }

    public void setPromotion(Promotion promotion) {
        this.promotion = promotion;
    }
    
    public Section getSection() {
        return section;
    }

    public void setSection(Section section) {
        this.section = section;
    }
    
    public Enrollment getEnrollment() {
        return enrollment;
    }

    public void setEnrollment(Enrollment enrollment) {
        this.enrollment = enrollment;
    }
    
    public int getStudentNo() {
        return studentNo;
    }

    public void setStudentNo(int studentNo) {
        this.studentNo = studentNo;
    }
    
    public boolean isExists() {
        return exists;
    }

    public void setExists(boolean exists) {
        this.exists = exists;
    }
    
    public PaymentTerm getPaymentTerm() {
        return paymentTerm;
    }

    public void setPaymentTerm(PaymentTerm paymentTerm) {
        this.paymentTerm = paymentTerm;
    }
    
    public int getGradeLevelNo() {
        return gradeLevelNo;
    }

    public void setGradeLevelNo(int gradeLevel) {
        this.gradeLevelNo = gradeLevel;
    }
    
    public Tuition getTuitionFee() {
        return tuitionFee;
    }

    public void setTuitionFee(Tuition tuitionFee) {
        this.tuitionFee = tuitionFee;
    }
    
    public int getStudentType() {
        return studentType;
    }

    public void setStudentType(int studentType) {
        this.studentType = studentType;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public Registration getRegistration() {
        return registration;
    }

    public void setRegistration(Registration registration) {
        this.registration = registration;
    }

    public Admission getAdmission() {
        return admission;
    }

    public void setAdmission(Admission admission) {
        this.admission = admission;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public boolean getIsGraduated() {
        return isGraduated;
    }

    public void setIsGraduated(boolean isGraduated) {
        this.isGraduated = isGraduated;
    }

    public Date getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(Date entryDate) {
        this.entryDate = entryDate;
    }

    public Date getDateGraduated() {
        return dateGraduated;
    }

    public void setDateGraduated(Date dateGraduated) {
        this.dateGraduated = dateGraduated;
    }
    
        
}
