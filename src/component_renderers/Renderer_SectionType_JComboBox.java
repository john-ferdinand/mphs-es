/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package component_renderers;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import model.section.Section;

/**
 *
 * @author Jordan
 */
public class Renderer_SectionType_JComboBox extends JLabel implements ListCellRenderer<Object> {

    public Renderer_SectionType_JComboBox() {
        this.setOpaque(true);
    }

    @Override
    public Component getListCellRendererComponent(JList<? extends Object> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        if (value != null) {
            if (value.toString().trim().equalsIgnoreCase("R")) {
                this.setText("Regular");
            } else if (value.toString().trim().equalsIgnoreCase("S")) {
                this.setText("Summer");
            }
        } else {
            this.setText("Select");
        }
        
        if (isSelected) {
            this.setBackground(Color.YELLOW);
            this.setForeground(Color.BLACK);
        } else {
            this.setBackground(list.getBackground());
            this.setForeground(list.getForeground());
        }

        return this;
    }

}
