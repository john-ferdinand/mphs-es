/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package component_model_loader;

import utility.layout.CardLayoutUtil;
import view.container.Dashboard;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class NavigationImpl implements Navigation {

    private void returnHome() {
        CardLayoutUtil.flipCardTo(Dashboard.jpnlTopCardContainer, Dashboard.jpnlTopCard);
    }

    @Override
    public void exitRegistration(JPanel jPanelRegistration) {
        int choice = JOptionPane.showConfirmDialog(null, "Exit Registration?", "Exit Confirmation", JOptionPane.WARNING_MESSAGE);
        if (choice == JOptionPane.YES_OPTION) {
            Dashboard.jtpTopTabbedPane.remove(jPanelRegistration);
            returnHome();
            Dashboard.setRegistrationInstanceCount(0);
        }
    }

    @Override
    public void exitEnrollment(JPanel jPanelEnrollment) {
        int choice = JOptionPane.showConfirmDialog(null, "Exit Enrollment?", "Exit Confirmation", JOptionPane.WARNING_MESSAGE);
        if (choice == JOptionPane.YES_OPTION) {
            Dashboard.jtpTopTabbedPane.remove(jPanelEnrollment);
            returnHome();
            Dashboard.setEnrollmentInstanceCount(0);
        }
    }

    @Override
    public void exitPayment(JPanel jPanelPayment) {
        int choice = JOptionPane.showConfirmDialog(null, "Exit Payment?", "Exit Confirmation", JOptionPane.WARNING_MESSAGE);
        if (choice == JOptionPane.YES_OPTION) {
            Dashboard.jtpTopTabbedPane.remove(jPanelPayment);
            returnHome();
            Dashboard.setPaymentsAndAssessmentInstanceCount(0);
        }
    }

    @Override
    public void exitReports(JPanel jPanelReports) {
        int choice = JOptionPane.showConfirmDialog(null, "Exit Reports", "Exit Confirmation", JOptionPane.WARNING_MESSAGE);
        if (choice == JOptionPane.YES_OPTION) {
            Dashboard.jtpTopTabbedPane.remove(jPanelReports);
            returnHome();
            Dashboard.setReportsInstanceCount(0);
        }
    }

    @Override
    public void exitManagement(JPanel jPanelManagement) {
        int choice = JOptionPane.showConfirmDialog(null, "Exit Management?", "Exit Confirmation", JOptionPane.WARNING_MESSAGE);
        if (choice == JOptionPane.YES_OPTION) {
            Dashboard.jtpTopTabbedPane.remove(jPanelManagement);
            returnHome();
            Dashboard.setSettingsInstanceCount(0);
        }
    }

    @Override
    public void exitGrades(JPanel jPanelGrades) {
        int choice = JOptionPane.showConfirmDialog(null, "Exit Grades?", "Exit Confirmation", JOptionPane.WARNING_MESSAGE);
        if (choice == JOptionPane.YES_OPTION) {
             Dashboard.jtpTopTabbedPane.remove(jPanelGrades);
            returnHome();
            Dashboard.setGradingSystemInstanceCount(0);
        }
    }

    @Override
    public void exitAccounts(JPanel jPanelAccounts) {
        int choice = JOptionPane.showConfirmDialog(null, "Exit Accounts?", "Exit Confirmation", JOptionPane.WARNING_MESSAGE);
        if (choice == JOptionPane.YES_OPTION) {
             Dashboard.jtpTopTabbedPane.remove(jPanelAccounts);
            returnHome();
            Dashboard.setUserAccountsInstanceCount(0);
        }
    }

}
