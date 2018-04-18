package controller.schoolyear;

import daoimpl.SchoolYearDaoImpl;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import model.schoolyear.SchoolYear;
import view.schoolyear.Panel_SchoolYear;

/**
 *
 * @author Jordan
 */
public class Controller_Open_Close_SchoolYear_JButton implements ActionListener {

    private final SchoolYearDaoImpl schoolYearDaoImpl;
    private final Panel_SchoolYear view;

    public Controller_Open_Close_SchoolYear_JButton(Panel_SchoolYear view) {
        this.schoolYearDaoImpl = new SchoolYearDaoImpl();
        this.view = view;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (aSchoolYearIsSelected()) {
            if (e.getActionCommand().trim().equalsIgnoreCase("open")) {
                int choice = JOptionPane.showConfirmDialog(null, "Open schoolYear? ", "Open SchoolYear Confirmation", JOptionPane.YES_NO_OPTION);
                if (choice == JOptionPane.YES_OPTION) {
                    if (openSchoolYear()) {
                        JOptionPane.showMessageDialog(null, "Successfully opened schoolyear.");
                        view.loadSchoolYearMasterList();
                    } else {
                        JOptionPane.showMessageDialog(null, "Encountered problems opening schoolyear. Please contact your support.");
                    }
                }
            } else if (e.getActionCommand().trim().equalsIgnoreCase("close")) {
                int choice = JOptionPane.showConfirmDialog(null, "Close schoolYear? ", "Open SchoolYear Confirmation", JOptionPane.YES_NO_OPTION);
                if (choice == JOptionPane.YES_OPTION) {
                    if (closeSchoolYear()) {
                        JOptionPane.showMessageDialog(null, "Successfully closed schoolyear.");
                        view.loadSchoolYearMasterList();
                    } else {
                        JOptionPane.showMessageDialog(null, "Encountered problems closing schoolyear. Please contact your support.");
                    }
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Please select a schoolyear from the list.");
        }
    }

    private boolean openSchoolYear() {
        SchoolYear schoolYear = getSelectedSchoolYear();
        return schoolYearDaoImpl.open(schoolYear);
    }

    private boolean closeSchoolYear() {
        SchoolYear schoolYear = getSelectedSchoolYear();
        return schoolYearDaoImpl.close(schoolYear);
    }

    private SchoolYear getSelectedSchoolYear() {
        SchoolYear schoolYear = new SchoolYear();
        int rowSelected = view.getJtblSchoolYearMasterList().getSelectedRow();
        int schoolYearId = Integer.parseInt(view.getJtblSchoolYearMasterList().getValueAt(rowSelected, 0).toString().trim());
        schoolYear.setSchoolYearId(schoolYearId);
        return schoolYear;
    }

    private boolean aSchoolYearIsSelected() {
        int countOfSelectedRows = view.getJtblSchoolYearMasterList().getSelectedRowCount();
        return countOfSelectedRows > 0;
    }

}
