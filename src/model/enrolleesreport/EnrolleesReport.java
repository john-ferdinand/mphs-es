/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.enrolleesreport;

import java.util.Date;

/**
 *
 * @author John Ferdinand Antonio
 */
public class EnrolleesReport {
    private int gradelevel;
    private int studentId;
    private int enrollmentId;
    private String studentLastName;
    private String studentFirstName;
    private String studentMiddleName;
    private Date dateOfEnrollment;
    private int  schoolYear;
    private boolean isWithdrawn;

    public int getGradelevel() {
        return gradelevel;
    }

    public void setGradelevel(int gradelevel) {
        this.gradelevel = gradelevel;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getEnrollmentId() {
        return enrollmentId;
    }

    public void setEnrollmentId(int enrollmentId) {
        this.enrollmentId = enrollmentId;
    }

    public String getStudentLastName() {
        return studentLastName;
    }

    public void setStudentLastName(String studentLastName) {
        this.studentLastName = studentLastName;
    }

    public String getStudentFirstName() {
        return studentFirstName;
    }

    public void setStudentFirstName(String studentFirstName) {
        this.studentFirstName = studentFirstName;
    }

    public String getStudentMiddleName() {
        return studentMiddleName;
    }

    public void setStudentMiddleName(String studentMiddleName) {
        this.studentMiddleName = studentMiddleName;
    }

    public Date getDateOfEnrollment() {
        return dateOfEnrollment;
    }

    public void setDateOfEnrollment(Date dateOfEnrollment) {
        this.dateOfEnrollment = dateOfEnrollment;
    }

    public int getSchoolYear() {
        return schoolYear;
    }

    public void setSchoolYear(int schoolYear) {
        this.schoolYear = schoolYear;
    }

    public boolean isIsWithdrawn() {
        return isWithdrawn;
    }

    public void setIsWithdrawn(boolean isWithdrawn) {
        this.isWithdrawn = isWithdrawn;
    }
    
    
}
