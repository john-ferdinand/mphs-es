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

/**
 *
 * @author John Ferdinand Antonio
 */
public class Renderer_Day_JComboBox extends JLabel implements ListCellRenderer<Object> {

    public Renderer_Day_JComboBox() {
        setOpaque(true);
    }

    @Override
    public Component getListCellRendererComponent(JList<? extends Object> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {

        if (value instanceof Object) {
            int val = Integer.parseInt(value.toString());
            switch (val) {
                case 0:
                    this.setText("Mon");
                    break;
                case 1:
                    this.setText("Tue");
                    break;
                case 2:
                    this.setText("Wed");
                    break;
                case 3:
                    this.setText("Thu");
                    break;
                case 4:
                    this.setText("Fri");
                    break;
                case 5:
                    this.setText("Sat");
                    break;
                case 6:
                    this.setText("Sun");
                    break;
            }
        }

        if (isSelected) {
            this.setBackground(Color.YELLOW);
//            this.setBackground(list.getSelectionBackground());
            this.setForeground(list.getSelectionForeground());
        } else {
            this.setBackground(list.getBackground());
            this.setForeground(list.getForeground());
        }
        return this;
    }
}
