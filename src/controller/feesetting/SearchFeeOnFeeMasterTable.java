/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.feesetting;

import component_model_loader.FeeJCompModelLoader;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import utility.form.FormInspector;
import utility.form.FormValidator;

/**
 *
 * @author Jordan
 */
public class SearchFeeOnFeeMasterTable extends FormInspector implements ActionListener, FormValidator{

    private final JTextField jtfSearchBox;
    private final JTable jtblFeeRecord;
    
    private FeeJCompModelLoader feeJCompModelLoader;
    
    public SearchFeeOnFeeMasterTable(JTextField jtfSearchBox,JTable jtblFeeRecord){
        this.jtfSearchBox = jtfSearchBox;
        this.jtblFeeRecord = jtblFeeRecord;
        
        feeJCompModelLoader = new FeeJCompModelLoader();
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(formIsValid()){
            DefaultTableModel tableModel = feeJCompModelLoader.getAllFeesByWildCardAsModel(jtfSearchBox, jtblFeeRecord);
            jtblFeeRecord.setModel(tableModel);
        }else{
           DefaultTableModel tableModel = feeJCompModelLoader.getAllFeesGroupedByIdAsModel(jtblFeeRecord);
           jtblFeeRecord.setModel(tableModel);
        }
    }

    @Override
    public boolean formIsValid() {
       return !jtfSearchBox.getText().isEmpty();
    }
    
}
