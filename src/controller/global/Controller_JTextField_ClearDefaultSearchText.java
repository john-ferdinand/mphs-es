/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.global;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JTextField;

/**
 *
 * @author Jordan
 */
public class Controller_JTextField_ClearDefaultSearchText implements MouseListener{

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if(e.getSource() instanceof JTextField){
            String text = ((JTextField)e.getSource()).getText();
            if(text.trim().toLowerCase().contains("search")){
                ((JTextField)e.getSource()).setText("");
            }
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        
    }

    
    
}
