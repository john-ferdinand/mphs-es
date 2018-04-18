
package controller.feesetting;

import component_model_loader.FeeJCompModelLoader;
import daoimpl.FeeDaoImpl;
import daoimpl.SchoolYearDaoImpl;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import model.fee.Fee;
import model.feecategory.FeeCategory;
import model.schoolyear.SchoolYear;
import utility.string.StringUtil;
import static view.fees.Panel_FeeRecord.jtblFeeRecord;
import utility.form.FormValidator;

/**
 *
 * @author Jordan
 */
public class EditFee implements ActionListener, FormValidator{

    private int feeIdOfSelected;
    
    private JDialog jdlgFeeCrud;
    
    private final JTextField jtfFeeName;
    private final JTextField jtfAmount;
    private final JComboBox jcmbFeeCategory;
    private final JTextField jtfKindergarten;
    private final JTextField jtfGrade1;
    private final JTextField jtfGrade2;
    private final JTextField jtfGrade3;
    private final JTextField jtfGrade4;
    private final JTextField jtfGrade5;
    private final JTextField jtfGrade6;
    private final JTextField jtfGrade7;
    private final JTextField jtfGrade8;
    private final JTextField jtfGrade9;
    private final JTextField jtfGrade10;
    private final JTextArea jtaDescription;
    private final JPanel jpnlGradeLevel;
    
    private final JComboBox jcmbStatus;
    
    private final FeeDaoImpl feeDaoImpl;
    private final FeeJCompModelLoader feeJCompModelLoader;
    private final SchoolYearDaoImpl schoolYearDaoImpl;
    private final NumberFormat numberFormatter;
    
    public EditFee(
            int feeIdOfSelected,
            JTextField jtfFeeName, JTextField jtfAmount, JComboBox jcmbFeeCategory, 
            JTextField jtfKindergarten,
            JTextField jtfGrade1, JTextField jtfGrade2, JTextField jtfGrade3, JTextField jtfGrade4, JTextField jtfGrade5,
            JTextField jtfGrade6, JTextField jtfGrade7, JTextField jtfGrade8, JTextField jtfGrade9, JTextField jtfGrade10,
            JTextArea jtaDescription, JPanel jpnlGradeLevel,
            JComboBox jcmbStatus, JDialog jdlgFeeCrud
    ){
        this.feeIdOfSelected = feeIdOfSelected;
        
        this.jtfFeeName = jtfFeeName;
        this.jtfAmount = jtfAmount;
        this.jcmbFeeCategory = jcmbFeeCategory;
        this.jtfKindergarten = jtfKindergarten;
        this.jtfGrade1 = jtfGrade1;
        this.jtfGrade2 = jtfGrade2;
        this.jtfGrade3 = jtfGrade3;
        this.jtfGrade4 = jtfGrade4;
        this.jtfGrade5 = jtfGrade5;
        this.jtfGrade6 = jtfGrade6;
        this.jtfGrade7 = jtfGrade7;
        this.jtfGrade8 = jtfGrade8;
        this.jtfGrade9 = jtfGrade9;
        this.jtfGrade10 = jtfGrade10;
        this.jtaDescription = jtaDescription;
        this.jpnlGradeLevel = jpnlGradeLevel;
        
        this.jcmbStatus = jcmbStatus;
        
        this.jdlgFeeCrud = jdlgFeeCrud;
        
        feeDaoImpl = new FeeDaoImpl();
        feeJCompModelLoader = new FeeJCompModelLoader();
        schoolYearDaoImpl = new SchoolYearDaoImpl();
        numberFormatter = NumberFormat.getInstance();
    }

    @Override
    public boolean formIsValid() {
        return true;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (formIsValid()) {
            int choice = JOptionPane.showConfirmDialog(null, "Update fee?");
            if (choice == JOptionPane.YES_OPTION) {
                if (updateFee()) {
                    refreshFeeRecordTable();
                    JOptionPane.showMessageDialog(null, "Successfully updated fee.");
                    jdlgFeeCrud.dispose();
                }
            }
        }
    }
    
    private void refreshFeeRecordTable(){
        DefaultTableModel dtmFeeRecord = new DefaultTableModel();
        dtmFeeRecord = feeJCompModelLoader.getAllFeesGroupedByIdAsModel(jtblFeeRecord);
        jtblFeeRecord.setModel(dtmFeeRecord);
    }
    
    private boolean updateFee() {
        boolean isUpdated = false;
        Fee fee = new Fee();
        fee.setId(feeIdOfSelected);
        fee.setDescription(jtaDescription.getText());
        fee.setName(jtfFeeName.getText().trim());
        fee.setFeeCategory(getFeeCategory());
        fee.setAmount(BigDecimal.valueOf(Double.parseDouble(jtfAmount.getText().trim())));
        fee.setSchoolYear(getFeeSchoolYear());
        fee.setGradeLevelAmountPair(getGradeLevelAmountPair());
        fee.setIsActive(getStatus());
        
        isUpdated = feeDaoImpl.update(fee);
        return isUpdated;
    }
    
    private boolean getStatus() {
        boolean status = jcmbStatus.getSelectedItem().toString().equals("Yes");
        return status;
    }
    
    private FeeCategory getFeeCategory() {
        FeeCategory feeCategory = new FeeCategory();
        String fCat = jcmbFeeCategory.getSelectedItem().toString();
        feeCategory.setName(fCat);
        return feeCategory;
    }
    
    private SchoolYear getFeeSchoolYear() {
        SchoolYear schoolYear = new SchoolYear();
        schoolYear.setSchoolYearId(schoolYearDaoImpl.getCurrentSchoolYearId());
        return schoolYear;
    }
    
    private boolean correspondingFieldEmpty(String checkBoxText) {
        boolean fieldEmpty = true;
        if (!checkBoxText.trim().isEmpty()) {
            Component[] components = jpnlGradeLevel.getComponents();
            for (Component c : components) {
                if (c instanceof JTextField) {
                    if (!((JTextField) c).getToolTipText().trim().equalsIgnoreCase("Kindergarten")) {
                        String textField = StringUtil.removeWhiteSpaces(StringUtil.removeAllNonNumeric(((JTextField) c).getToolTipText().trim()));
//                        System.out.println(s + " = " + cbText);
                        if (textField.equalsIgnoreCase(checkBoxText)) {
                            fieldEmpty = ((JTextField) c).getText().trim().isEmpty();
                             System.out.println(""+checkBoxText+" = "+textField+" : "+fieldEmpty);
                        }
                        
                    }
                }
            }
        } 
       
        return fieldEmpty;
    }
    
    
    /**
     * This method is used specifically with the UI design of Fees which is used in performing CRUD operation.
     * This method collects all the gradelevel JCheckboxes selected by user as well as all
     * the fee amount JTextFields for selected gradelevel JCheckboxes.
     * <br/><br/>
     * The selected fee gradelevel JCheckboxes and selected fee amount JTextFields are put in a HashMap
     * where gradelevel JCheckBox is "key" and fee Amount JTextField is "value"
     * @return 
     */
    private Map<Integer, BigDecimal> getGradeLevelAmountPair() {
        Map<Integer, BigDecimal> gradeLevelAmountPair = new HashMap<>();
        Integer key = null;
        BigDecimal value = null;
        try {
            Component[] components = jpnlGradeLevel.getComponents();
            for (Component c : components) {
                if (c instanceof JCheckBox) {
                    JCheckBox cb = ((JCheckBox) c);
                    boolean selected = cb.isSelected();
                    if (!cb.getText().equalsIgnoreCase("Kindergarten") && selected) {
                        String cbText = StringUtil.removeWhiteSpaces(StringUtil.removeAllNonNumeric(cb.getText()));
                        if (!correspondingFieldEmpty(cbText)) {
                            key = getKey(cbText);
                            value = getValue(key);
                        }
                    } else if (cb.getText().trim().equalsIgnoreCase("Kindergarten") && selected) {
                        if (!jtfKindergarten.getText().trim().isEmpty()) {
                            key = 0;
                            value = BigDecimal.valueOf(Double.parseDouble(jtfKindergarten.getText().trim()));
                        }
                    }
                    if(key != null && value != null){
                        System.out.println("key: "+key+", value: "+value);
                        gradeLevelAmountPair.put(key, value);
                    }
                    
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return gradeLevelAmountPair;
    }
    
    private Integer getKey(String gLevel){
        Integer key;
        key = gLevel.equals("1") ? 1
                : gLevel.equals("2") ? 2
                : gLevel.equals("3") ? 3
                : gLevel.equals("4") ? 4
                : gLevel.equals("5") ? 5
                : gLevel.equals("6") ? 6
                : gLevel.equals("7") ? 7
                : gLevel.equals("8") ? 8
                : gLevel.equals("9") ? 9
                : gLevel.equals("10") ? 10 : Integer.parseInt(gLevel);
        
        return key;
    }
    
    private BigDecimal getValue(Integer keyGLevel) {
        BigDecimal feeAmount = null;
        try {
            feeAmount = 
                      keyGLevel == 1 ? BigDecimal.valueOf(Double.parseDouble(jtfGrade1.getText().trim()))
                    : keyGLevel == 2 ? BigDecimal.valueOf(Double.parseDouble(jtfGrade2.getText().trim()))
                    : keyGLevel == 3 ? BigDecimal.valueOf(Double.parseDouble(jtfGrade3.getText().trim()))
                    : keyGLevel == 4 ? BigDecimal.valueOf(Double.parseDouble(jtfGrade4.getText().trim()))
                    : keyGLevel == 5 ? BigDecimal.valueOf(Double.parseDouble(jtfGrade5.getText().trim()))
                    : keyGLevel == 6 ? BigDecimal.valueOf(Double.parseDouble(jtfGrade6.getText().trim()))
                    : keyGLevel == 7 ? BigDecimal.valueOf(Double.parseDouble(jtfGrade7.getText().trim()))
                    : keyGLevel == 8 ? BigDecimal.valueOf(Double.parseDouble(jtfGrade8.getText().trim()))
                    : keyGLevel == 9 ? BigDecimal.valueOf(Double.parseDouble(jtfGrade9.getText().trim()))
                    : keyGLevel == 10 ? BigDecimal.valueOf(Double.parseDouble(jtfGrade10.getText().trim())) 
                    : BigDecimal.valueOf(0.00);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return feeAmount;
    }
    
}
