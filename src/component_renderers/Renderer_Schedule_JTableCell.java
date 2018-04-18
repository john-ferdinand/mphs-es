
package component_renderers;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Acer
 */
public class Renderer_Schedule_JTableCell extends DefaultTableCellRenderer {
    
    private final JLabel jlblConflictMessage;
    
    public Renderer_Schedule_JTableCell(JLabel jlblConflictMessage){
        this.jlblConflictMessage = jlblConflictMessage;
    }
    
    @Override
    public Component getTableCellRendererComponent(
            JTable table, Object value,
            boolean isSelected, boolean hasFocus,
            int row, int col) {
        
        Component cellComponent = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);
        
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        boolean hasStartTime = (model.getValueAt(row, 1) != null);
        boolean hasEndTime = (model.getValueAt(row, 2) != null);
        
        if (hasStartTime && hasEndTime) {
            int startTime = Integer.parseInt(model.getValueAt(row, 1).toString().replace(":", ""));
            int endTime = Integer.parseInt(model.getValueAt(row, 2).toString().replace(":", ""));
            if (endTime < startTime) {
                colorConflict(cellComponent, row);
            } else if (startTime == endTime) {
                colorConflict(cellComponent, row);
            }
        }else{
            colorWithOriginal(cellComponent, row);
        }
        
        if(isSelected){
            cellComponent.setBackground(Color.WHITE);
            ((JLabel)cellComponent).setForeground(Color.BLACK);
        }
        
        if(!hasNullFields(table)){
            compare(table, row, cellComponent);
        }
        
        return cellComponent;
    }
    
    private void colorWithOriginal(Component c, int row) {
        if (row % 2 == 0) {
            c.setBackground(Color.WHITE);
            ((JLabel) c).setForeground(Color.BLACK);
        } else {
            c.setBackground(Color.WHITE);
            ((JLabel) c).setForeground(Color.BLACK);
        }
    }
    
    private void colorConflict(Component c, int row) {
        c.setBackground(Color.RED);
        ((JLabel) c).setForeground(Color.WHITE);
    }
    
    private void compare(JTable table, int rowIndex, Component c) {
        String day1 = table.getValueAt(rowIndex, 0).toString();
        String startTime1 = table.getValueAt(rowIndex, 1).toString();
        String endTime1 = table.getValueAt(rowIndex, 2).toString();
        String subject1 = table.getValueAt(rowIndex, 3).toString();
        String faculty1 = table.getValueAt(rowIndex, 4).toString();
        String room1 = table.getValueAt(rowIndex, 5).toString();

        for (int row = 0; row < table.getRowCount(); row++) {
            if (row != rowIndex) {
                String day = table.getValueAt(row, 0).toString().trim();
                String startTime = table.getValueAt(row, 1).toString().trim();
                String endTime = table.getValueAt(row, 2).toString().trim();
                String subject = table.getValueAt(row, 3).toString().trim();
                String faculty = table.getValueAt(row, 4).toString().trim();
                String room = table.getValueAt(row, 5).toString().trim();

                if (hasSameRoom(room1, room)
                        && hasSameFaculty(faculty1, faculty)
                        && hasSameDay(day1, day)
                        && hasSameTime(startTime1, endTime1, startTime, endTime)) {
                    colorConflict(c, row);
                } else if (hasSameRoom(room1, room) && hasSameFaculty(faculty1, faculty)
                        && hasSameDay(day1, day) && !hasSameTime(startTime1, endTime1, startTime, endTime)
                        && hasSameSubject(subject1, subject)) {
                    colorConflict(c, row);
                } else if(hasSameDay(day1, day) && hasTimeConflict(startTime1, endTime1, startTime, endTime)){
                    colorConflict(c, row);
                } else if(hasSameDay(day1, day) && hasSameSubject(subject1, subject)){
                    colorConflict(c, row);
                }
            }
        }
    }
    
    private boolean hasTimeConflict(String st1, String et1, String st, String et) {
        boolean hasTimeConflict = false;
        int startTime1 = Integer.parseInt(st1.replace(":", ""));
        int endTime1 = Integer.parseInt(et1.replace(":", ""));
        int startTime = Integer.parseInt(st.replace(":", ""));
        int endTime = Integer.parseInt(et.replace(":", ""));
        
        System.out.println("Start Time1 : " + startTime1);
        System.out.println("End Time1 : " + endTime1);
        System.out.println("Start Time : "+startTime);
        System.out.println("End Time : "+endTime);
        
        boolean timeConflictA = ((startTime >= startTime1) && (endTime <= endTime1));
        boolean timeConflictB = ((startTime < startTime1) && (endTime > startTime1 && endTime <= endTime1));
        boolean timeConflictC = (startTime == endTime);
        boolean timeConflictD = (startTime == startTime1) && (endTime > endTime1);
        boolean timeConflictE = (endTime1 < startTime1);

        if (timeConflictA == true) {
            hasTimeConflict = true;
        } else if (timeConflictB == true) {
            hasTimeConflict = true;
        } else if (timeConflictC == true) {
            hasTimeConflict = true;
        } else if (timeConflictD) {
            hasTimeConflict = true;
        } else if(timeConflictE){
            hasTimeConflict = true;
        }

        if (hasTimeConflict) {
            System.out.println("Time conflict.");
        }

        return hasTimeConflict;
    }
    
    private boolean hasNullFields(JTable table){
        boolean hasNull = true;
        int countOfNull = 0;
        for(int r = 0; r < table.getRowCount(); r++){
            for(int c = 0; c < table.getColumnCount(); c++){
                if(table.getValueAt(r, c)==null){
                    countOfNull++;
                }
            }
        }
        if(countOfNull == 0){
            hasNull = false;
        }
        return hasNull;
    }
    
    private boolean hasSameSubject(String subject1, String subject2) {
        boolean hasSameSubject = false;
        if (subject1.equals(subject2)) {
            hasSameSubject = true;
        }
        return hasSameSubject;
    }

    private boolean hasSameFaculty(String faculty1, String faculty2) {
        boolean hasSameFaculty = false;
        if (faculty1.equals(faculty2)) {
            hasSameFaculty = true;
        }
        return hasSameFaculty;
    }
    
    private boolean hasSameDay(String day1,String day2){
        boolean hasSameDay = false;
        if(day1.equals(day2)){
            hasSameDay = true;
        }
        return hasSameDay;
    }
    
    private boolean hasSameTime(String startTime1, String endTime1, String startTime2, String endTime2) {
        boolean hasSameTime = false;
        if (startTime1.equals(startTime2) && endTime1.equals(endTime2)) {
            hasSameTime = true;
        }
        return hasSameTime;
    }
    
    private boolean hasSameRoom(String room1, String room2){
        boolean hasSameRoom = false;
        if(room1.equals(room2)){
            hasSameRoom = true;
        }
        return hasSameRoom;
    }
}
