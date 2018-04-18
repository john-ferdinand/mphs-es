package component_editor;

import java.awt.Component;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.DefaultCellEditor;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import model.section.Section;

/**
 *
 * @author John Ferdinand Antonio
 */
public class ScheduleTimeCellEditor extends DefaultCellEditor {

    private final Section section;
    private final JSpinner spinner;
    private final SpinnerDateModel spinnerDateModel;
    private SimpleDateFormat format;
    private final JSpinner.DateEditor editor;
    private final JTable jtblSchedule;
    
    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        return spinner;
    }
    
    public ScheduleTimeCellEditor(JTable jtblSchedule, Section section) {
        super(new JTextField());
        this.jtblSchedule = jtblSchedule;
        this.section = section;
        
        CustomSpinnerModelArgumentLoader customModelArgumentLoader = new CustomSpinnerModelArgumentLoader(section);
        spinnerDateModel = new SpinnerDateModel();
        spinnerDateModel.setValue(customModelArgumentLoader.getValue());
        spinnerDateModel.setCalendarField(customModelArgumentLoader.getStep());
        
        spinner = new JSpinner(spinnerDateModel);
        
        editor = new JSpinner.DateEditor(spinner, "HH:mm");
        editor.getTextField().setEditable(true);
        spinner.setEditor(editor);
       
        spinner.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                
            }
        });
    }

    @Override
    public Object getCellEditorValue() {
        Date date = (Date) spinner.getValue();
        int time = getDateAsIntTime(date);
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        return editor.getFormat().format(spinner.getValue());
//        return sdf.format(spinner.getValue());
    }
    
    private int getDateAsIntTime(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        SimpleDateFormat localDateFormat = new SimpleDateFormat("HH:mm");
        int hour = calendar.get(Calendar.HOUR_OF_DAY) * 100;
        int mins = calendar.get(Calendar.MINUTE);
        int time = (hour + mins);
        return time;
    }
    
    public class CustomSpinnerModelArgumentLoader {

        private Calendar value;
        private Calendar minimum;
        private Calendar maximum;
        private Calendar step;
        private final Section s;
        
        public CustomSpinnerModelArgumentLoader(Section s) {
            this.s = s;
            value = Calendar.getInstance();
            minimum = Calendar.getInstance();
            maximum = Calendar.getInstance();
            step = Calendar.getInstance();
        }
        
        public Date getValue() {
            int defaultClassStartsAt = 7;
            int amStartsAt = 7; //7am
            int pmStartsAt = 12; //12pm
            int wholeDayStartsAt = 7;
            int startHr = s.getSectionSession().equalsIgnoreCase("AM") ? amStartsAt
                    : s.getSectionSession().equalsIgnoreCase("PM") ? pmStartsAt
                    : s.getSectionSession().equalsIgnoreCase("WD") ? wholeDayStartsAt : defaultClassStartsAt;
            value.set(Calendar.HOUR_OF_DAY, startHr);
            value.set(Calendar.MINUTE, 0);
            return value.getTime();
        }

        public Date getMinimum() {
            int defaultClassStartsAt = 7;
            int amStartsAt = 7; //7am
            int pmStartsAt = 12; //12pm
            int wholeDayStartsAt = 7;
            
            int startsAtHour = s.getSectionSession().equalsIgnoreCase("AM") ? amStartsAt
                    : s.getSectionSession().equalsIgnoreCase("PM") ? pmStartsAt
                    : s.getSectionSession().equalsIgnoreCase("WD") ? wholeDayStartsAt : defaultClassStartsAt;
            
            minimum.set(Calendar.HOUR_OF_DAY, startsAtHour);
            minimum.set(Calendar.MINUTE, 0);
            return minimum.getTime();
        }

        public Date getMaximum() {
            int defaultEndsAt = 17;
            int amEndsAt = 12; //12pm or 12:00
            int pmEndsAt = 17; //17pm or 5:00
            int wholeDayEndsAt = 17;
            
            int endsAtHour = s.getSectionSession().equalsIgnoreCase("AM") ? amEndsAt
                    : s.getSectionSession().equalsIgnoreCase("PM") ? pmEndsAt
                    : s.getSectionSession().equalsIgnoreCase("WD") ? wholeDayEndsAt : defaultEndsAt;
            maximum.set(Calendar.HOUR_OF_DAY, endsAtHour);
            maximum.set(Calendar.MINUTE, 0);
            return maximum.getTime();
        }

        public int getStep() {
            step.set(Calendar.HOUR_OF_DAY, 00);
            return step.get(Calendar.HOUR_OF_DAY);
        }
    }

    
    private void checkConflict() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        
        int selectedRow = jtblSchedule.getSelectedRow();
        String d = jtblSchedule.getValueAt(selectedRow, 0).toString().trim();
        String sT = sdf.format(spinner.getValue());
        String eT = sdf.format(spinner.getValue());

        System.out.println(d + "\n" + sT + "\n" + eT + "\n");
    }
    
    private boolean tableHasEmptyFields(){
        boolean hasEmptyFields = true;
        int countOfEmpty = 0;
        for(int row = 0; row<jtblSchedule.getRowCount(); row++){
            for(int col = 0; col <jtblSchedule.getColumnCount(); col++){
                if(jtblSchedule.getValueAt(row, col) == null){
                    countOfEmpty++;
                }
            }
            if(countOfEmpty <= 0){
                hasEmptyFields = false;
            }
        }
        return hasEmptyFields;
    }
}
