package component_model_loader;

import daoimpl.FeeCategoryDaoImpl;
import daoimpl.FeeDaoImpl;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import model.fee.Fee;
import model.feecategory.FeeCategory;
import model.gradelevel.GradeLevel;

public class FeeJCompModelLoader {

    private final DecimalFormat DECIMAL_FORMATTER;
    private final FeeDaoImpl feeDaoImpl;
    private final FeeCategoryDaoImpl feeCategoryDaoImpl;

    public FeeJCompModelLoader() {
        feeDaoImpl = new FeeDaoImpl();
        feeCategoryDaoImpl = new FeeCategoryDaoImpl();
        DECIMAL_FORMATTER = new DecimalFormat("#.00");
    }

    public DefaultTableModel getDownpaymentFeesAsModel(JTable jTable, int aGradeLevel) {
        DefaultTableModel jTableModel = (DefaultTableModel) jTable.getModel();
        jTableModel.setRowCount(0);

        FeeCategory feeCategory = new FeeCategory();
        feeCategory.setName("Downpayment");

        GradeLevel gradeLevel = new GradeLevel();
        gradeLevel.setLevelNo(aGradeLevel);

        Object[] data = feeDaoImpl.getFeesByGradeLevelAndCategory(gradeLevel, feeCategory).toArray();
        for (Object o : data) {
            Fee f = (Fee) o;
            Object[] rowData = {f.getName(), DECIMAL_FORMATTER.format(f.getAmount())};
            jTableModel.addRow(rowData);
        }
        return jTableModel;
    }

    public DefaultTableModel getTuitionFeesAsModel(JTable jTable, int aGradeLevel) {
        DefaultTableModel jTableModel = (DefaultTableModel) jTable.getModel();
        jTableModel.setRowCount(0);
        FeeCategory feeCategory = new FeeCategory();
        feeCategory.setName("Tuition");

        GradeLevel gradeLevel = new GradeLevel();
        gradeLevel.setLevelNo(aGradeLevel);

        Object[] data = feeDaoImpl.getFeesByGradeLevelAndCategory(gradeLevel, feeCategory).toArray();
        for (Object o : data) {
            Fee f = (Fee) o;
            Object[] rowData = {f.getName(), DECIMAL_FORMATTER.format(f.getAmount())};
            jTableModel.addRow(rowData);
        }
        return jTableModel;
    }

    public DefaultTableModel getMiscellaneousFeesAsModel(JTable jTable, int aGradeLevel) {
        DefaultTableModel jTableModel = (DefaultTableModel) jTable.getModel();
        jTableModel.setRowCount(0);
        FeeCategory feeCategory = new FeeCategory();
        feeCategory.setName("Miscellaneous");
        GradeLevel gradeLevel = new GradeLevel();
        gradeLevel.setLevelNo(aGradeLevel);

        Object[] data = feeDaoImpl.getFeesByGradeLevelAndCategory(gradeLevel, feeCategory).toArray();
        for (Object o : data) {
            Fee f = (Fee) o;
            Object[] rowData = {f.getName(), DECIMAL_FORMATTER.format(f.getAmount())};
            jTableModel.addRow(rowData);
        }
        return jTableModel;
    }

    public DefaultTableModel getOtherFeesAsModel(JTable jTable, int aGradeLevel) {
        DefaultTableModel jTableModel = (DefaultTableModel) jTable.getModel();
        jTableModel.setRowCount(0);

        FeeCategory feeCategory = new FeeCategory();
        feeCategory.setName("Others");

        GradeLevel gradeLevel = new GradeLevel();
        gradeLevel.setLevelNo(aGradeLevel);

        Object[] data = feeDaoImpl.getFeesByGradeLevelAndCategory(gradeLevel, feeCategory).toArray();
        for (Object o : data) {
            Fee f = (Fee) o;
            Object[] rowData = {f.getName(), DECIMAL_FORMATTER.format(f.getAmount())};
            jTableModel.addRow(rowData);
        }
        return jTableModel;
    }

    /**
     * Returns all FeeCategories from master table as DefaultComboBoxModel.
     * Mainly, "Basic","Miscellaneous","Others","Downpayment"
     * @return 
     */
    public DefaultComboBoxModel getAllFeeCategoriesAsModel() {
        DefaultComboBoxModel comboBoxModel = new DefaultComboBoxModel();
        List<FeeCategory> feeCategoryList = feeCategoryDaoImpl.getAllFeeCategory();
        Object[] data = feeCategoryList.toArray();
        for (Object o : data) {
            FeeCategory feeCategory = (FeeCategory) o;
            comboBoxModel.addElement(feeCategory.getName());
        }
        comboBoxModel.setSelectedItem(null);
        return comboBoxModel;
    }

    public DefaultListModel getAllFeeNamesAsModel() {
        DefaultListModel listModel = new DefaultListModel();
        Object[] feeNames = feeDaoImpl.getNames().toArray();
        for (Object o : feeNames) {
            listModel.addElement(o);
        }
        return listModel;
    }
    
    /**
     * 
     * @param jtfSearchBox
     * @param jTable
     * @return 
     */
    public DefaultTableModel getAllFeesByWildCardAsModel(JTextField jtfSearchBox, JTable jTable) {
        Object[] feesList = feeDaoImpl.getFeesByWildcard(jtfSearchBox.getText().trim()).toArray();
        DefaultTableModel tableModel = (DefaultTableModel) jTable.getModel();
        tableModel.setRowCount(0);
        for (Object o : feesList) {
            Fee f = (Fee) o;
            Object[] tableRowData = {
                f.getId(), f.getName(), f.getFeeCategory().getName(),
                f.getSchoolYear().getYearFrom(), f.isActive() == true ? "Active" : "Inactive",
                f.getDescription()
            };
            tableModel.addRow(tableRowData);
        }
        return tableModel;
    }
    
     public DefaultTableModel getAllFeesGroupedByIdAsModel(JTable jTable) {
        Object[] feesList = feeDaoImpl.getAllGroupedById().toArray();
        DefaultTableModel tableModel = (DefaultTableModel) jTable.getModel();
        tableModel.setRowCount(0);
        for (Object o : feesList) {
            Fee f = (Fee) o;
            Object[] tableRowData = {
                f.getId(),f.getName(), f.getFeeCategory().getName(),
                f.getSchoolYear().getYearFrom(), f.isActive() == true ? "Active" : "Inactive", 
                f.getDescription()
            };
            tableModel.addRow(tableRowData);
        }
        return tableModel;
    }
     
    public DefaultTableModel getFeeGradeLevelAssignmentAndAmountById(JTable jtblFeeRecord, JTable jtblFeeGradeLevelAssignment){
        DefaultTableModel tableModel = (DefaultTableModel)jtblFeeGradeLevelAssignment.getModel();
        tableModel.setRowCount(0);
        int feeIdColumnIndex = 0;
        int rowSelected = jtblFeeRecord.getSelectedRow();
        int feeId = Integer.parseInt(jtblFeeRecord.getValueAt(rowSelected,feeIdColumnIndex).toString());
        Fee fee = feeDaoImpl.getFeeGradeLevelAssignmentAndAmountById(feeId);
        Map<Integer,BigDecimal> map = fee.getGradeLevelAmountPair();
        for(Map.Entry<Integer,BigDecimal> entry : map.entrySet()){
            Object[] tableRowData = {
                entry.getKey() == 0? "Kindergarten":entry.getKey(),
                entry.getValue().setScale(2,RoundingMode.HALF_UP)
            };
            tableModel.addRow(tableRowData);
        }
        return tableModel;
    } 
    
    public DefaultTableModel getAllFeesAsModel(JTable jTable) {
        Object[] feesList = feeDaoImpl.getAll().toArray();
        DefaultTableModel tableModel = (DefaultTableModel) jTable.getModel();
        tableModel.setRowCount(0);
        for (Object o : feesList) {
            Fee f = (Fee) o;
            Object[] tableRowData = {
                f.getId(),f.getName(), f.getFeeCategory().getName(),
                f.getSchoolYear().getYearFrom(), f.isActive() == true ? "Active" : "Inactive", 
                f.getDescription()
            };
            tableModel.addRow(tableRowData);
        }
        return tableModel;
    }
    
    
}
