package view.grades;

import daoimpl.QuarterDaoImpl;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import model.quarter.Quarter;
import model.schoolyear.SchoolYear;
import model.user.User;

/**
 *
 * @author Jordan
 */
public class MyInputGradeTableModel extends DefaultTableModel {

    private final JTable table;
    private final User user;
    private final SchoolYear currentSchoolYear;
    private final List<Integer> quarterColumns;

    public MyInputGradeTableModel(JTable table, List<Integer> quarterColumns, User user, SchoolYear currentSchoolYear) {
        this.table = table;
        this.user = user;
        this.currentSchoolYear = currentSchoolYear;
        this.quarterColumns = quarterColumns;
        this.setDataVector(tableData(), columnIdentifiers());
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        boolean isEditable = false;
        if (user.getRole().getRoleName().trim().equalsIgnoreCase("Administrator")) {
            isEditable = true;
        } else if (!user.getRole().getRoleName().trim().equalsIgnoreCase("Administrator")) {
            if (quarterColumns.contains(column)) {
                int quarterNo = (column == 3 ? 1 : column == 4 ? 2 : column == 5 ? 3 : column == 6 ? 4 : column);
                if (isGradingOpenFor(quarterNo)) {
                    isEditable = true;
                } else {
                    JOptionPane.showMessageDialog(null, "Grading for quarter "+quarterNo+" is closed.\nPlease contact admin if modification of grade is necessary");
                    isEditable = false;
                }
            }
        }
        return isEditable;
    }

    private Vector tableData() {
        Vector tableData = ((DefaultTableModel) table.getModel()).getDataVector();
        return tableData;
    }

    private Vector columnIdentifiers() {
        Vector columnIdentifiers = new Vector();
        TableColumnModel tableColModel = table.getColumnModel();
        for (int i = 0; i < tableColModel.getColumnCount(); i++) {
            columnIdentifiers.add(tableColModel.getColumn(i).getIdentifier());
        }
        return columnIdentifiers;
    }

    private boolean isGradingOpenFor(int quarterNo) {
        boolean isOpen;
        QuarterDaoImpl quarterDaoImpl = new QuarterDaoImpl();
        Quarter quarter = quarterDaoImpl.getQuarterBy(quarterNo, currentSchoolYear);
        Date dateToday = Calendar.getInstance().getTime();
        boolean todayEqOpen = dateToday.compareTo(quarter.getGradingOpenDate()) == 0;
        boolean todayGtOpen = dateToday.after(quarter.getGradingOpenDate());
        boolean todayEqClose = dateToday.compareTo(quarter.getGradingDueDate()) == 0;
        boolean todayLtClose = dateToday.before(quarter.getGradingDueDate());
        if ((todayGtOpen || todayEqOpen) && (todayLtClose || todayEqClose)) {
            isOpen = true;
        } else {
            isOpen = false;
        }
        return isOpen;
    }
}
