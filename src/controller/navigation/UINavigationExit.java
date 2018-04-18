
package controller.navigation;

import constants.DashboardMenuItem;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import utility.layout.CardLayoutUtil;
import view.container.Dashboard;

/**
 *
 * @author John Ferdinand Antonio
 */
public class UINavigationExit implements ActionListener{
    private final Container container;
    private final DashboardMenuItem dashboardMenuItem;
    
    public UINavigationExit(Container container,DashboardMenuItem dashboardMenuPanel){
        this.container = container;
        this.dashboardMenuItem = dashboardMenuPanel;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        removeContainer();
    }
    
    private void removeContainer(){
        int choice = JOptionPane.showConfirmDialog(null, "Exit?", "Exit Confirmation", JOptionPane.WARNING_MESSAGE);
        if (choice == JOptionPane.YES_OPTION) {
            Dashboard.jtpTopTabbedPane.remove(container);
            returnToDashboard();
            switch (dashboardMenuItem) {
                case ENROLLMENT:
                    Dashboard.setEnrollmentInstanceCount(0);
                    break;
                case SETTINGS:
                    Dashboard.setSettingsInstanceCount(0);
                    break;
                case GRADINGSYSTEM:
                    Dashboard.setGradingSystemInstanceCount(0);
                    break;
                case PAYMENTS:
                    Dashboard.setPaymentsAndAssessmentInstanceCount(0);
                    break;
                case REGISTRATION:
                    Dashboard.setRegistrationInstanceCount(0);
                    break;
                case ACCOUNTS:
                    Dashboard.setUserAccountsInstanceCount(0);
                    break;
                default:
                    break;
            }
            
        }
    }
    
    private void returnToDashboard() {
        CardLayoutUtil.flipCardTo(Dashboard.jpnlTopCardContainer, Dashboard.jpnlTopCard);
    }
    
}
