package controller.editschedule;

import component_editor.ScheduleDayCellEditor;
import component_editor.ScheduleTimeCellEditor;
import component_model_loader.RoomJCompModelLoader;
import daoimpl.SchoolYearDaoImpl;
import daoimpl.SectionDaoImpl;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import model.section.Section;
import view.schedule.Dialog_CreateSchedule;
import view.schedule.Dialog_EditSchedule;

/**
 *
 * @author John Ferdinand Antonio
 */
public class Edit_ItemListener_Section_JComboBox implements ItemListener {

    private final RoomJCompModelLoader roomJCompModelLoader;
    private SectionDaoImpl sectionDaoImpl;
    private Section section;
    private final Dialog_EditSchedule view;
    private final JComboBox jcmbRoom;
    private final JComboBox jcmbSections;
    private final JTable jtblSchedule;

    public Edit_ItemListener_Section_JComboBox(Dialog_EditSchedule view) {
        sectionDaoImpl = new SectionDaoImpl();
        this.view = view;
        this.jtblSchedule = view.getJtblSchedule();
        this.jcmbSections = view.getJcmbSection();
        this.jcmbRoom = view.getJcmbRoom();
        this.roomJCompModelLoader = new RoomJCompModelLoader();
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (jcmbSections.getSelectedIndex() > -1) {
            view.getJpnlScheduleTable().repaint();
            initializeSection();//must be the first method call here
            
//            loadInitialTimeValues();
//            applyStartandEndTimeCellEditors(section);
            applyDayCellEditor();
            loadRooms();
            loadSectionInformationToScheduleHeader();
            loadSessionToTable();
            enableRoomCombo();
        }
    }

    private void enableRoomCombo(){
        view.getJcmbRoom().setEnabled(true);
    }
    
    private void loadRooms(){
        SchoolYearDaoImpl schoolYearDaoImpl = new SchoolYearDaoImpl();
        Section s = (Section) jcmbSections.getSelectedItem();
//        jcmbRoom.setModel(roomJCompModelLoader.getRoomsAvailableFor(s.getSectionSession().trim(), schoolYearDaoImpl.getCurrentSchoolYear()));
        jcmbRoom.setModel(roomJCompModelLoader.getAllActiveRooms());
    }
    
    private void initializeSection() {
        Section s = (Section) jcmbSections.getSelectedItem();
        section = sectionDaoImpl.getSectionById(s.getSectionId());
    }

    private void loadSectionInformationToScheduleHeader() {
        view.getJtfSectionName().setText(section.getSectionName());
        view.getJtfAdviserName().
                setText(section.getAdviser().getLastName() + ", "
                        + section.getAdviser().getFirstName() + " "
                        + section.getAdviser().getMiddleName());
    }

    private void applyStartandEndTimeCellEditors(Section section) {
        TableColumnModel columnModel = jtblSchedule.getColumnModel();
        TableColumn startTimeCol = columnModel.getColumn(1);
        TableColumn endTimeCol = columnModel.getColumn(2);
        startTimeCol.setCellEditor(new ScheduleTimeCellEditor(jtblSchedule, section));
        endTimeCol.setCellEditor(new ScheduleTimeCellEditor(jtblSchedule, section));
    }
    
    private void applyDayCellEditor(){
        TableColumnModel columnModel = jtblSchedule.getColumnModel();
        TableColumn dayCol = columnModel.getColumn(0);
        dayCol.setCellEditor(new ScheduleDayCellEditor());
    }
    
    private void loadInitialTimeValues() {
        int startTimeCol = 1;
        int endTimeCol = 2;
        Calendar start = Calendar.getInstance();
        Calendar end = Calendar.getInstance();
        SimpleDateFormat dateFormatter = new SimpleDateFormat("HH:mm");

        int startHour = section.getSectionSession().equalsIgnoreCase("AM") ? 7
                : section.getSectionSession().equalsIgnoreCase("PM") ? 12
                : section.getSectionSession().equalsIgnoreCase("WD") ? 7 : 7;
        for (int row = 0; row < jtblSchedule.getRowCount(); row++,startHour++) {
            start.set(Calendar.HOUR_OF_DAY, startHour);
            start.set(Calendar.MINUTE, 0);
            jtblSchedule.setValueAt(dateFormatter.format(start.getTime()), row, startTimeCol);
            end.set(Calendar.HOUR_OF_DAY, startHour+1);
            end.set(Calendar.MINUTE, 0);
            jtblSchedule.setValueAt(dateFormatter.format(end.getTime()), row, endTimeCol);
        }
    }

    private void loadSessionToTable() {
        int sessionCol = 6;
        String sectionSession = section.getSectionSession();
        for (int row = 0; row < jtblSchedule.getRowCount(); row++) {
            jtblSchedule.setValueAt(sectionSession, row, sessionCol);
        }
    }
    
    
}
