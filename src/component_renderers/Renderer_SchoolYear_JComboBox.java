package component_renderers;

import daoimpl.SchoolYearDaoImpl;
import java.awt.Color;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import model.schoolyear.SchoolYear;

/**
 *
 * @author Jordan
 * When this JComboBox Renderer is applied to a JComboBox containing schoolyearId, it 
 * renders the schoolyear id to a "yearFrom - yearTo" format.
 * E.g.
 * schoolyearId : 432
 * Result: 2018 - 2019
 * 
 * Keep in mind that this will only render values of type integer which is a schoolyearid existing in the database table
 * enrollmentdb.schoolyear_mt.schoolyear_id
 * 
 * WHERE:
 * enrollmentdb - is the name of the database
 * schoolyear_mt - is the name table
 * schoolyear_id - is the name of column containing the int value of schoolyear.
 */
public class Renderer_SchoolYear_JComboBox extends JLabel implements ListCellRenderer<Object> {

    private final SchoolYearDaoImpl schoolYearDaoImpl;
    
    public Renderer_SchoolYear_JComboBox() {
        this.setOpaque(true);
        schoolYearDaoImpl = new SchoolYearDaoImpl();
    }

    @Override
    public Component getListCellRendererComponent(JList<? extends Object> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        if (value instanceof Integer) {
            Object valueToDisplay = getValueToDisplay(Integer.parseInt(value.toString().trim()));
            this.setText(String.valueOf(valueToDisplay));
        }
        else if(value instanceof SchoolYear){
            SchoolYear schoolYear = (SchoolYear) value;
            this.setText(schoolYear.getYearFrom()+"-"+schoolYear.getYearTo());
        }
            else{
            this.setText("Select");
        }

        if (isSelected) {
            //if user points or selects an item on the list 
            this.setBackground(Color.BLACK);
            this.setForeground(Color.YELLOW);
        } else {
            this.setBackground(list.getBackground()); //default bg color
            this.setForeground(list.getForeground()); //default fg color
        }
        return this;
    }
    
    private Object getValueToDisplay(int schoolYearId) {
        SchoolYear schoolYear = schoolYearDaoImpl.getSchoolYearById(schoolYearId);
        Object valueToDisplay = (schoolYear.getYearFrom() + " - " + schoolYear.getYearTo());
        return valueToDisplay;
    }

}
