package controller.settings;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import model.schoolyear.SchoolYear;
import model.user.User;
import view.container.SettingsPanel;
import view.credential.Panel_Credential;
import view.curriculum.Panel_Curriculum;
import view.discount.Panel_Discount;
import view.faculty.Panel_Faculty;
import view.fees.Panel_FeeRecord;
import view.paymentsetting.PaymentScheduleSettings;
import view.rooms.Panel_Rooms;
import view.schedule.Panel_ClassSchedules;
import view.schoolyear.Panel_SchoolYear;
import view.section.Panel_Sections;
import view.subject.Panel_Subjects;
import view.subjectcategory.Panel_SubjectCategory;

/**
 *
 * @author Jordan
 */
public class AddSettingsPanelToTabbedPaneOnMouseClick implements MouseListener {

    private final SettingsPanel view;
    private final SchoolYear currentSchoolYear;
    private final User user;

    public AddSettingsPanelToTabbedPaneOnMouseClick(SettingsPanel view, SchoolYear currentSchoolYear, User user) {
        this.view = view;
        this.currentSchoolYear = currentSchoolYear;
        this.user = user;
    }

    private void addPanelToTabbedPane(JPanel panelContainer, JPanel panelToAdd) {
        panelContainer.removeAll();
        panelContainer.add(panelToAdd);
        view.getJtpManagementTabbedPane().repaint();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getClickCount() == 1) {
            int selectedTab = view.getJtpManagementTabbedPane().getSelectedIndex();
            switch (selectedTab) {
                case 0:
                    addPanelToTabbedPane(view.getJpnlSubjectMgmt(), new Panel_Subjects());
                    break;
                case 1:
                    addPanelToTabbedPane(view.getJpnlSubjectCatMgmt(), new Panel_SubjectCategory());
                    break;
                case 2:
                    addPanelToTabbedPane(view.getJpnlCurriculumMgmt(), new Panel_Curriculum());
                    break;
                case 3:
                    addPanelToTabbedPane(view.getJpnlFacultyMgmt(), new Panel_Faculty());
                    break;
                case 4:
                    addPanelToTabbedPane(view.getJpnlSectionMgmt(), new Panel_Sections(currentSchoolYear,user));
                    break;
                case 5:
                    addPanelToTabbedPane(view.getJpnlRoomMgmt(), new Panel_Rooms());
                    break;
                case 6:
                    addPanelToTabbedPane(view.getJpnlScheduleMgmt(), new Panel_ClassSchedules());
                    break;
                case 7:
                    addPanelToTabbedPane(view.getJpnlFeesMgmt(), new Panel_FeeRecord());
                    break;
                case 8:
                    addPanelToTabbedPane(view.getJpnlCredentialsMgmt(), new Panel_Credential(currentSchoolYear,user)); //replace null with the correct panel used as UI
                    break;
                case 9:
                    addPanelToTabbedPane(view.getJpnlPaymentScheduleMgmt(), new PaymentScheduleSettings());
                    break;
                case 10:
                    addPanelToTabbedPane(view.getJpnlDiscountsMgmt(), new Panel_Discount(currentSchoolYear, user));
                    break;
                case 11:
//                    addPanelToTabbedPane(jpnlLanMgmt, null);
                    JOptionPane.showMessageDialog(null, "Settings for LAN Management is still under development.");
                    break;
                case 12:
                    addPanelToTabbedPane(view.getJpnlSchoolYearMgmt(), new Panel_SchoolYear());
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

}
