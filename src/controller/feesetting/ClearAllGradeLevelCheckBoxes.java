/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.feesetting;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Jordan
 */
public class ClearAllGradeLevelCheckBoxes implements ActionListener{

    private JPanel jpnlGradeLevels;
    
    public ClearAllGradeLevelCheckBoxes(JPanel jpnlGradeLevels){
        this.jpnlGradeLevels = jpnlGradeLevels;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        Component[] components = jpnlGradeLevels.getComponents();
        for(Component c : components){
            if(c instanceof JCheckBox){
                ((JCheckBox)c).setSelected(false);
            }else if(c instanceof JTextField){
                ((JTextField)c).setText("");
            }
        }
    }
    
}
