/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package component_renderers;

import daoimpl.ClassTypeDaoImpl;
import java.awt.Color;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ListCellRenderer;
import model.classtype.ClassType;

/**
 *
 * @author Jordan
 */
public class Renderer_ClassType_JComboBox extends JLabel implements ListCellRenderer<Object>{
    
    public Renderer_ClassType_JComboBox(){
        this.setOpaque(true);
    }

    @Override
    public Component getListCellRendererComponent(JList<? extends Object> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        if(value instanceof Integer){
            ClassTypeDaoImpl classTypeDaoImpl = new ClassTypeDaoImpl();
            ClassType classType = classTypeDaoImpl.getClassTypeById(Integer.parseInt(value.toString()));
            this.setText(classType.getClassTypeName()+"");
        }else{
            this.setText("Select");
        }
        
        if (isSelected) {
            this.setBackground(Color.YELLOW);
//            this.setBackground(list.getSelectionBackground());
//            this.setForeground(list.getSelectionForeground());
            this.setForeground(Color.BLACK);
        } else {
            this.setBackground(list.getBackground());
            this.setForeground(list.getForeground());
        }
        return this;
    }
    
    
}
