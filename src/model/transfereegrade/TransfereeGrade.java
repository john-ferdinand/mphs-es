/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.transfereegrade;

/**
 *
 * @author John Ferdinand Antonio
 */
public class TransfereeGrade {
    private int admissionId;
    private int registrationId;
    private int studentId;
    private double firstQuarterGrade;
    private double secondQuarterGrade;
    private double thirdQuarterGrade;
    private double fourthQuarterGrade;
    private double gwa;

    public int getAdmissionId() {
        return admissionId;
    }

    public void setAdmissionId(int admissionId) {
        this.admissionId = admissionId;
    }
    
    public int getRegistrationId() {
        return registrationId;
    }

    public void setRegistrationId(int registrationId) {
        this.registrationId = registrationId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public double getFirstQuarterGrade() {
        return firstQuarterGrade;
    }

    public void setFirstQuarterGrade(double firstQuarterGrade) {
        this.firstQuarterGrade = firstQuarterGrade;
    }

    public double getSecondQuarterGrade() {
        return secondQuarterGrade;
    }

    public void setSecondQuarterGrade(double secondQuarterGrade) {
        this.secondQuarterGrade = secondQuarterGrade;
    }

    public double getThirdQuarterGrade() {
        return thirdQuarterGrade;
    }

    public void setThirdQuarterGrade(double thirdQuarterGrade) {
        this.thirdQuarterGrade = thirdQuarterGrade;
    }

    public double getFourthQuarterGrade() {
        return fourthQuarterGrade;
    }

    public void setFourthQuarterGrade(double fourthQuarterGrade) {
        this.fourthQuarterGrade = fourthQuarterGrade;
    }

    public double getGwa() {
        return gwa;
    }

    public void setGwa(double gwa) {
        this.gwa = gwa;
    }
    
    
}
