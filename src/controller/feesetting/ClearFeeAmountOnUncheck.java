/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.feesetting;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JCheckBox;
import javax.swing.JTextField;

/**
 *
 * @author Jordan
 */
public class ClearFeeAmountOnUncheck implements ActionListener{

    private JCheckBox jcbGradeLevel; 
    private JTextField jtfGradeLevelFeeAmount;
    
    public ClearFeeAmountOnUncheck(JCheckBox jcbGradeLevel,JTextField jtfGradeLevelFeeAmount){
        this.jcbGradeLevel = jcbGradeLevel;
        this.jtfGradeLevelFeeAmount = jtfGradeLevelFeeAmount;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(!jcbGradeLevel.isSelected()){
            jtfGradeLevelFeeAmount.setText("");
        }
    }
    
}
