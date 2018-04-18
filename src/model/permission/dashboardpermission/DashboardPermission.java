/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.permission.dashboardpermission;

import java.util.Date;
import model.permission.Permission;
import model.user.User;

/**
 *
 * @author John Ferdinand Antonio
 */
public class DashboardPermission extends Permission{
    private boolean canAccessRegistration;
    private boolean canAccessGrades;
    private boolean canAccessPayment;
    private boolean canAccessEnrollment;
    private boolean canAccessSettings;
    private boolean canAccessAccounts;
    private boolean canAccessReports;
    private Date dateLastModified;
    private User modifiedBy;

    public boolean hasReportsAccess() {
        return canAccessReports;
    }

    public void setCanAccessReports(boolean canAccessReports) {
        this.canAccessReports = canAccessReports;
    }

    
    
    public boolean hasRegistrationAccess() {
        return canAccessRegistration;
    }

    public void setCanAccessRegistration(boolean canAccessRegistration) {
        this.canAccessRegistration = canAccessRegistration;
    }

    public boolean hasGradeAccess() {
        return canAccessGrades;
    }

    public void setCanAccessGrades(boolean canAccessGrades) {
        this.canAccessGrades = canAccessGrades;
    }

    public boolean hasPaymentAccess() {
        return canAccessPayment;
    }

    public void setCanAccessPayment(boolean canAccessPayment) {
        this.canAccessPayment = canAccessPayment;
    }

    public boolean hasEnrollmentAccess() {
        return canAccessEnrollment;
    }

    public void setCanAccessEnrollment(boolean canAccessEnrollment) {
        this.canAccessEnrollment = canAccessEnrollment;
    }

    public boolean hasSettingsAccess() {
        return canAccessSettings;
    }

    public void setCanAccessSettings(boolean canAccessSettings) {
        this.canAccessSettings = canAccessSettings;
    }

    public boolean hasAccountsAccess() {
        return canAccessAccounts;
    }

    public void setCanAccessAccounts(boolean canAccessAccounts) {
        this.canAccessAccounts = canAccessAccounts;
    }

    public Date getDateLastModified() {
        return dateLastModified;
    }

    public void setDateLastModified(Date dateLastModified) {
        this.dateLastModified = dateLastModified;
    }

    public User getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(User modifiedBy) {
        this.modifiedBy = modifiedBy;
    }
    
    
}
