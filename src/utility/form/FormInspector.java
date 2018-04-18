/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utility.form;

import java.awt.Component;
import java.awt.Container;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Jordan
 */
public class FormInspector {

    /**
     *
     * @param container - is any of the JComponents considered as a container.
     * Most common containers are JPanel, JScrollPane etc. This method checks if
     * there is any JComponent which is null or has no value. a JTextField with
     * no text is not valid form. Same goes if JComboBox has no selected item.
     * This method will only return true if all JComponents within a container
     * such as a panel is not null or has something selected or has something
     * inputted.
     * @return
     */
    public boolean hasEmptyFields(Container container) {
        boolean hasEmptyFields = true;
        Component[] components = container.getComponents();
        for (Component c : components) {
            if (c instanceof JPanel) {
                System.out.println("Panel");
                hasEmptyFields((Container) c);
            } else if (c instanceof JTextField) {
                boolean isEmpty = ((JTextField) c).getText().trim().isEmpty();
                hasEmptyFields = hasEmptyFields && isEmpty;
            } else if (c instanceof JComboBox) {
                boolean isEmpty = ((JComboBox) c).getSelectedIndex() == -1 ? true : false;
                hasEmptyFields = hasEmptyFields && isEmpty;
            }
        }

        return hasEmptyFields;
    }

    /**
     * 
     * @param s
     * - represents the string value. This method accepts any valid String value
     * and tests or checks whether the string value is a number or not.
     * @return 
     */
    public static boolean isInteger(String s) {
        boolean isInt = true;
        try {
            Integer.parseInt(s);
        } catch (Exception e) {
            isInt = false;
            e.printStackTrace();
        }
        return isInt;
    }//end of isInteger block

    /**
     * 
     * @param s
     * - represents a String value. This method accepts any valid String value
     * then it tests or checks whether the value entered is a Double value (numbers with decimals) or not.
     * @return 
     */
    public static boolean isDouble(String s) {
        boolean isDouble = true;
        try {
            Double.parseDouble(s);
        } catch (Exception e) {
            isDouble = false;
            e.printStackTrace();
        }
        return isDouble;
    }

    public static boolean hasSpecialCharacters(String s) {
        Boolean result;

        Pattern pattern = Pattern.compile("[^a-z0-9]", Pattern.CASE_INSENSITIVE); //patter for letters and numbers only
        Matcher matcher = pattern.matcher(s);
        result = matcher.find();

        if (result) {
        } else if (result == false) {
        }
        return result;
    }
}
