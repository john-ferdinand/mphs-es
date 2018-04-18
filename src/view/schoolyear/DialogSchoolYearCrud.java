package view.schoolyear;

import component_model_loader.SchoolYearJCompModelLoader;
import controller.schoolyear.Controller_SchoolDaysCalculator_DocumentListener_SyEndDatePickerImpl;
import controller.schoolyear.CreateSchoolYear;
import controller.schoolyear.UpdateSchoolYear;
import daoimpl.SchoolYearDaoImpl;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import model.quarter.Quarter;
import model.schoolyear.SchoolYear;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;
import utility.calendar.CalendarUtil;
import utility.component.JSpinnerUtil;
import utility.date.DateLabelFormatter;
import utility.initializer.Initializer;

/**
 *
 * @author Jordan
 */
public class DialogSchoolYearCrud extends JDialog implements Initializer{
    
    private final Panel_SchoolYear panelSchoolYear;
    
    private SchoolYearDaoImpl schoolYearDaoImpl;
    private SchoolYearJCompModelLoader schoolYearJCompModelLoader;
    private Insets defaultInsets;
    
    private JPanel jpnlTopPanel;
    private GridBagConstraints gbcJpnlTopPanel;
    private JPanel jpnlControls;
    private GridBagConstraints gbcJpnlControls;
    private JPanel jpnlSchoolYear;
    private GridBagConstraints gbcJpnlSchoolYear;
    
    private JPanel jpnlSummerClassSchedule;
    private JLabel jlblSummerClassStart;
    private JDatePanelImpl dpnlSummerClassStart;
    private JDatePickerImpl dpSummerClassStart;
    private JLabel jlblSummerClassEnd;
    private JDatePanelImpl dpnlSummerClassEnd;
    private JDatePickerImpl dpSummerClassEnd;
    
    private JLabel jlblSchoolYearStartDate;
    private JLabel jlblSchoolYearEndDate;
    
    private JLabel jlblSchoolDays;
    private JTextField jtfTotalSchoolDays;
    
    private JPanel jpnlEnrollmentSchedule;
    private JPanel jpnlRegularEnrollmentSchedule;
    private JPanel jpnlSummerEnrollmentSchedule;
    
    private JLabel jlblRegularEnrollmentStartDate;
    private JLabel jlblRegularEnrollmentEndDate;
    
    private JLabel jlblSummerEnrollmentStartDate;
    private JLabel jlblSummerEnrollmentEndDate;
    
    private JPanel jpnlQuarterScheduleAndGradingDueDate;
    private JPanel jpnlContainer;
    
    private JLabel jlbl1stQtrStartDate;
    private JLabel jlbl1stQtrEndDate;
    private JLabel jlbl1stQtrGradingOpenDate;
    private JLabel jlbl1stQtrGradingDueDate;
    
    private JLabel jlbl2ndQtrStartDate;
    private JLabel jlbl2ndQtrEndDate;
    private JLabel jlbl2ndQtrGradingOpenDate;
    private JLabel jlbl2ndQtrGradingDueDate;
    
    private JLabel jlbl3rdQtrStartDate;
    private JLabel jlbl3rdQtrEndDate;
    private JLabel jlbl3rdQtrGradingOpenDate;
    private JLabel jlbl3rdQtrGradingDueDate;
    
    private JLabel jlbl4thQtrStartDate;
    private JLabel jlbl4thQtrEndDate;
    private JLabel jlbl4thQtrGradingOpenDate;
    private JLabel jlbl4thQtrGradingDueDate;
    
    private JLabel jlblDash;
    
    private JDatePanelImpl dpnlSchoolYearStartDate;
    private JDatePanelImpl dpnlSchoolYearEndDate;
    
    private JDatePanelImpl dpnlRegularEnrollmentStartDate;
    private JDatePanelImpl dpnlRegularEnrollmentEndDate;
    private JDatePanelImpl dpnlSummerEnrollmentStartDate;
    private JDatePanelImpl dpnlSummerEnrollmentEndDate;
    
    private JDatePanelImpl dpnl1stQtrStartDate;
    private JDatePanelImpl dpnl2ndQtrStartDate;
    private JDatePanelImpl dpnl3rdQtrStartDate;
    private JDatePanelImpl dpnl4thQtrStartDate;
    
    private JDatePanelImpl dpnl1stQtrGradingOpenDate;
    private JDatePanelImpl dpnl2ndQtrGradingOpenDate;
    private JDatePanelImpl dpnl3rdQtrGradingOpenDate;
    private JDatePanelImpl dpnl4thQtrGradingOpenDate;
    
    private JDatePanelImpl dpnl1stQtrGradingDueDate;
    private JDatePanelImpl dpnl2ndQtrGradingDueDate;
    private JDatePanelImpl dpnl3rdQtrGradingDueDate;
    private JDatePanelImpl dpnl4thQtrGradingDueDate;
    
    private JDatePanelImpl dpnl1stQtrEndDate;
    private JDatePanelImpl dpnl2ndQtrEndDate;
    private JDatePanelImpl dpnl3rdQtrEndDate;
    private JDatePanelImpl dpnl4thQtrEndDate;
    
    private JDatePickerImpl dpSchoolYearStartDate;
    private JDatePickerImpl dpSchoolYearEndDate;
    
    private JDatePickerImpl dpRegularEnrollmentStartDate;
    private JDatePickerImpl dpRegularEnrollmentEndDate;
    private JDatePickerImpl dpSummerEnrollmentStartDate;
    private JDatePickerImpl dpSummerEnrollmentEndDate;
    
    private JDatePickerImpl dp1stQtrStartDate;
    private JDatePickerImpl dp1stQtrEndDate;
    private JDatePickerImpl dp1stQtrGradingOpenDate;
    private JDatePickerImpl dp1stQtrGradingDuedate;
    
    private JDatePickerImpl dp2ndQtrStartDate;
    private JDatePickerImpl dp2ndQtrEndDate;
    private JDatePickerImpl dp2ndQtrGradingOpenDate;
    private JDatePickerImpl dp2ndQtrGradingDueDate;
    
    private JDatePickerImpl dp3rdQtrStartDate;
    private JDatePickerImpl dp3rdQtrEndDate;
    private JDatePickerImpl dp3rdQtrGradingOpenDate;
    private JDatePickerImpl dp3rdQtrGradingDueDate;
    
    private JDatePickerImpl dp4thQtrStartDate;
    private JDatePickerImpl dp4thQtrEndDate;
    private JDatePickerImpl dp4thQtrGradingOpenDate;
    private JDatePickerImpl dp4thQtrGradingDueDate;
    
    private JLabel jlblYearFrom;
    private JLabel jlblYearTo;
    private JSpinner jsprYearFrom;
    private JSpinner jsprYearTo;
    
    private JButton jbtnSave;
    private JLabel jlblStatus;
    private JComboBox jcmbStatus;
    
    private int schoolYearIdOfSelected;
    private SchoolYear schoolYearSelected;
    private String action;
    
    public DialogSchoolYearCrud(Frame parent, boolean modal, String action, Panel_SchoolYear panelSchoolYear){
        this.action = action;
        this.panelSchoolYear = panelSchoolYear;
        initModels();
        initComponents();
        
        initGridBagConstraints();
        initJCompModelLoaders();
        initRenderers();
        initViewComponents();
        initControllers();
        initDaoImpl();
        
        createGUI();
    }
    
    public DialogSchoolYearCrud(Frame parent, boolean modal, String action, int schoolYearIdOfSelected, Panel_SchoolYear panelSchoolYear){
        initModels();
        initComponents();
        
        initDaoImpl();
        this.action = action;
        this.schoolYearIdOfSelected = schoolYearIdOfSelected;
        this.schoolYearSelected = schoolYearDaoImpl.getSchoolYearById(schoolYearIdOfSelected);
        this.panelSchoolYear = panelSchoolYear;
        initGridBagConstraints();
        initJCompModelLoaders();
        initRenderers();
        initControllers();
        createGUI();
        initViewComponents();
        initForm();
       
    }

    @Override
    public void initRenderers() {
    }
    
    @Override
    public void initJCompModelLoaders() {
        schoolYearJCompModelLoader = new SchoolYearJCompModelLoader();
    }

    @Override
    public void initViewComponents() {
        if(action.equalsIgnoreCase("create")){
            jlblStatus.setVisible(false);
            jcmbStatus.setVisible(false);
        }else if(action.equalsIgnoreCase("edit")){
            
        }else if(action.equalsIgnoreCase("view")){
            disableFields();
        }
    }

    @Override
    public void initControllers() {
        if (action.equalsIgnoreCase("create")) {
            jbtnSave.addActionListener(new CreateSchoolYear(this,panelSchoolYear));
        }else if(action.equalsIgnoreCase("edit")){
            jbtnSave.addActionListener(new UpdateSchoolYear(this, schoolYearIdOfSelected, panelSchoolYear));
        }
        jsprYearFrom.getModel().addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                jsprYearTo.setValue(Integer.parseInt(jsprYearFrom.getValue().toString()) + 1);
                setAllDatePickerToYearFromSelected(Integer.parseInt(jsprYearFrom.getValue().toString()));
            }
        });
        dpSchoolYearStartDate.getJFormattedTextField().getDocument().addDocumentListener(new Controller_SchoolDaysCalculator_DocumentListener_SyEndDatePickerImpl(this));
        dpSchoolYearEndDate.getJFormattedTextField().getDocument().addDocumentListener(new Controller_SchoolDaysCalculator_DocumentListener_SyEndDatePickerImpl(this));
    }

    @Override
    public void initDaoImpl() {
        schoolYearDaoImpl = new SchoolYearDaoImpl();
    }
    
    
    @Override
    public void initGridBagConstraints(){
        this.setLayout(new GridBagLayout());
        this.setModal(true);
        defaultInsets = new Insets(3, 3, 3, 3);
    }
    
    private GridBagConstraints setPosition(int gridX, int gridY, double weightX, double weightY, int gbcFillValue) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = gridX;
        gbc.gridy = gridY;
        gbc.weightx = weightX;
        gbc.weighty = weightY;
        gbc.insets = defaultInsets;
        gbc.fill = gbcFillValue;
        return gbc;
    }
    
    @Override
    public void initModels(){
        schoolYearJCompModelLoader = new SchoolYearJCompModelLoader();
    }
    
    private Properties getDateProperties() {
        Properties dateProperties = new Properties();
        dateProperties.put("text.today", "Today");
        dateProperties.put("text.month", "Month");
        dateProperties.put("text.year", "Year");
        return dateProperties;
    }
    
    private DateLabelFormatter getDateLabelFormatter(){
        DateLabelFormatter dateLabelFormatter = new DateLabelFormatter();
        return dateLabelFormatter;
    }
    
    private void initComponents(){
        jlblSchoolDays = new JLabel("SCHOOL DAYS");
        jlblSchoolDays.setFont(new Font("Tahoma", Font.BOLD, 14));
        jtfTotalSchoolDays = new JTextField(5);
        jtfTotalSchoolDays.setEnabled(false);
        jtfTotalSchoolDays.setDisabledTextColor(Color.BLACK);
        jtfTotalSchoolDays.setFont(new Font("Tahoma", Font.BOLD, 14));
        
        jpnlTopPanel = new JPanel(new GridBagLayout());
        jpnlControls = new JPanel(new GridBagLayout());
        jpnlSchoolYear = new JPanel(new GridBagLayout());
        
        TitledBorder borderSchoolYear = BorderFactory.createTitledBorder("SCHOOL YEAR");
        borderSchoolYear.setTitleJustification(TitledBorder.CENTER);
        jpnlSchoolYear.setBorder(borderSchoolYear);
        jlblSchoolYearStartDate = new JLabel("START DATE");
        jlblSchoolYearEndDate = new JLabel("END DATE");
        
        jpnlEnrollmentSchedule = new JPanel(new GridBagLayout());
        TitledBorder borderEnrollmentSchedule = BorderFactory.createTitledBorder("ENROLLMENT SCHEDULE");
        borderEnrollmentSchedule.setTitleJustification(TitledBorder.CENTER);
        jpnlEnrollmentSchedule.setBorder(borderEnrollmentSchedule);

        jpnlRegularEnrollmentSchedule = new JPanel(new GridBagLayout());
        TitledBorder borderRegularEnrollment = BorderFactory.createTitledBorder("REGULAR ENROLLMENT");
        borderRegularEnrollment.setTitleJustification(TitledBorder.CENTER);
        jpnlRegularEnrollmentSchedule.setBorder(borderRegularEnrollment);
        
        jpnlSummerEnrollmentSchedule = new JPanel(new GridBagLayout());
        TitledBorder borderSummerEnrollment = BorderFactory.createTitledBorder("SUMMER ENROLLMENT");
        borderSummerEnrollment.setTitleJustification(TitledBorder.CENTER);
        jpnlSummerEnrollmentSchedule.setBorder(borderSummerEnrollment);
        
        jlblRegularEnrollmentStartDate = new JLabel("START DATE");
        jlblRegularEnrollmentEndDate = new JLabel("END DATE");
        jlblSummerEnrollmentStartDate = new JLabel("START DATE");
        jlblSummerEnrollmentEndDate = new JLabel("END DATE");
        
        jpnlQuarterScheduleAndGradingDueDate = new JPanel(new GridBagLayout());
        TitledBorder borderQuarterAndGradingDueDate = BorderFactory.createTitledBorder("QUARTER SCHEDULE AND GRADING DUE DATE");
        borderQuarterAndGradingDueDate.setTitleJustification(TitledBorder.CENTER);
        jpnlQuarterScheduleAndGradingDueDate.setBorder(borderQuarterAndGradingDueDate);
        
        jpnlSummerClassSchedule = new JPanel(new GridBagLayout());
        TitledBorder borderSummerClassSchedule = BorderFactory.createTitledBorder("SUMMER CLASS SCHEDULE");
        borderSummerClassSchedule.setTitleJustification(TitledBorder.CENTER);
        jpnlSummerClassSchedule.setBorder(borderSummerClassSchedule);
        
        jlblSummerClassStart = new JLabel("START");
        dpnlSummerClassStart = new JDatePanelImpl(new UtilDateModel(), getDateProperties());
        dpSummerClassStart = new JDatePickerImpl(dpnlSummerClassStart, getDateLabelFormatter());
        jlblSummerClassEnd = new JLabel("End");
        dpnlSummerClassEnd = new JDatePanelImpl(new UtilDateModel(), getDateProperties());
        dpSummerClassEnd = new JDatePickerImpl(dpnlSummerClassEnd, getDateLabelFormatter());
        
        jpnlContainer = new JPanel(new GridBagLayout());
        jpnlContainer.setBorder(BorderFactory.createTitledBorder("SCHOOL YEAR"));
        jlblDash = new JLabel("-");
        
        dpnlSchoolYearStartDate = new JDatePanelImpl(new UtilDateModel(), getDateProperties());
        dpnlSchoolYearEndDate = new JDatePanelImpl(new UtilDateModel(), getDateProperties());
        
        dpnlRegularEnrollmentStartDate = new JDatePanelImpl(new UtilDateModel(), getDateProperties());
        dpnlRegularEnrollmentEndDate = new JDatePanelImpl(new UtilDateModel(), getDateProperties());
        dpnlSummerEnrollmentStartDate = new JDatePanelImpl(new UtilDateModel(), getDateProperties());
        dpnlSummerEnrollmentEndDate = new JDatePanelImpl(new UtilDateModel(), getDateProperties());
        
        dpnl1stQtrStartDate = new JDatePanelImpl(new UtilDateModel(), getDateProperties());
        dpnl2ndQtrStartDate = new JDatePanelImpl(new UtilDateModel(), getDateProperties());
        dpnl3rdQtrStartDate = new JDatePanelImpl(new UtilDateModel(), getDateProperties());
        dpnl4thQtrStartDate = new JDatePanelImpl(new UtilDateModel(), getDateProperties());
        
        dpnl1stQtrGradingOpenDate = new JDatePanelImpl(new UtilDateModel(), getDateProperties());
        dpnl2ndQtrGradingOpenDate = new JDatePanelImpl(new UtilDateModel(), getDateProperties());
        dpnl3rdQtrGradingOpenDate = new JDatePanelImpl(new UtilDateModel(), getDateProperties());
        dpnl4thQtrGradingOpenDate = new JDatePanelImpl(new UtilDateModel(), getDateProperties());
        
        dpnl1stQtrGradingDueDate = new JDatePanelImpl(new UtilDateModel(), getDateProperties());
        dpnl2ndQtrGradingDueDate = new JDatePanelImpl(new UtilDateModel(), getDateProperties());
        dpnl3rdQtrGradingDueDate = new JDatePanelImpl(new UtilDateModel(), getDateProperties());
        dpnl4thQtrGradingDueDate = new JDatePanelImpl(new UtilDateModel(), getDateProperties());
        
        dpnl1stQtrEndDate = new JDatePanelImpl(new UtilDateModel(), getDateProperties());
        dpnl2ndQtrEndDate = new JDatePanelImpl(new UtilDateModel(), getDateProperties());
        dpnl3rdQtrEndDate = new JDatePanelImpl(new UtilDateModel(), getDateProperties());
        dpnl4thQtrEndDate = new JDatePanelImpl(new UtilDateModel(), getDateProperties());
        
        dpSchoolYearStartDate = new JDatePickerImpl(dpnlSchoolYearStartDate, getDateLabelFormatter());
        dpSchoolYearEndDate = new JDatePickerImpl(dpnlSchoolYearEndDate, getDateLabelFormatter());
        
        dpRegularEnrollmentStartDate = new JDatePickerImpl(dpnlRegularEnrollmentStartDate, getDateLabelFormatter());
        dpRegularEnrollmentEndDate = new JDatePickerImpl(dpnlRegularEnrollmentEndDate, getDateLabelFormatter());
        dpSummerEnrollmentStartDate = new JDatePickerImpl(dpnlSummerEnrollmentStartDate, getDateLabelFormatter());
        dpSummerEnrollmentEndDate = new JDatePickerImpl(dpnlSummerEnrollmentEndDate, getDateLabelFormatter());
        
        dp1stQtrStartDate = new JDatePickerImpl(dpnl1stQtrStartDate,getDateLabelFormatter());
        dp1stQtrEndDate = new JDatePickerImpl(dpnl1stQtrEndDate,getDateLabelFormatter());
        dp1stQtrGradingOpenDate = new JDatePickerImpl(dpnl1stQtrGradingOpenDate, getDateLabelFormatter());
        dp1stQtrGradingDuedate = new JDatePickerImpl(dpnl1stQtrGradingDueDate,getDateLabelFormatter());
        
        dp2ndQtrStartDate = new JDatePickerImpl(dpnl2ndQtrStartDate,getDateLabelFormatter());
        dp2ndQtrEndDate = new JDatePickerImpl(dpnl2ndQtrEndDate,getDateLabelFormatter());
        dp2ndQtrGradingOpenDate = new JDatePickerImpl(dpnl2ndQtrGradingOpenDate, getDateLabelFormatter());
        dp2ndQtrGradingDueDate = new JDatePickerImpl(dpnl2ndQtrGradingDueDate,getDateLabelFormatter());
        
        dp3rdQtrStartDate = new JDatePickerImpl(dpnl3rdQtrStartDate,getDateLabelFormatter());
        dp3rdQtrEndDate = new JDatePickerImpl(dpnl3rdQtrEndDate,getDateLabelFormatter());
        dp3rdQtrGradingOpenDate = new JDatePickerImpl(dpnl3rdQtrGradingOpenDate, getDateLabelFormatter());
        dp3rdQtrGradingDueDate = new JDatePickerImpl(dpnl3rdQtrGradingDueDate, getDateLabelFormatter());
        
        dp4thQtrStartDate = new JDatePickerImpl(dpnl4thQtrStartDate,getDateLabelFormatter());
        dp4thQtrEndDate = new JDatePickerImpl(dpnl4thQtrEndDate,getDateLabelFormatter());
        dp4thQtrGradingOpenDate = new JDatePickerImpl(dpnl4thQtrGradingOpenDate, getDateLabelFormatter());
        dp4thQtrGradingDueDate = new JDatePickerImpl(dpnl4thQtrGradingDueDate, getDateLabelFormatter());
        
        jlbl1stQtrStartDate = new JLabel("1st QTR START DATE");
        jlbl1stQtrEndDate = new JLabel("1st QTR END DATE");
        
        jlbl2ndQtrStartDate = new JLabel("2nd QTR START DATE");
        jlbl2ndQtrEndDate = new JLabel("2nd QTR END DATE");
        
        jlbl3rdQtrStartDate = new JLabel("3rd QTR START DATE");
        jlbl3rdQtrEndDate = new JLabel("3rd QTR END DATE");
        
        jlbl4thQtrStartDate = new JLabel("4th QTR START DATE");
        jlbl4thQtrEndDate = new JLabel("4th QTR END DATE");
        
        jlbl1stQtrGradingOpenDate = new JLabel("Grading Open Date");
        jlbl2ndQtrGradingOpenDate = new JLabel("Grading Open Date");
        jlbl3rdQtrGradingOpenDate = new JLabel("Grading Open Date");
        jlbl4thQtrGradingOpenDate = new JLabel("Grading Open Date");
        
        jlbl1stQtrGradingDueDate = new JLabel("Grading Due Date");
        jlbl2ndQtrGradingDueDate = new JLabel("Grading Due Date");
        jlbl3rdQtrGradingDueDate = new JLabel("Grading Due Date");
        jlbl4thQtrGradingDueDate = new JLabel("Grading Due Date");
        
        jbtnSave = new JButton("Save");
        jlblStatus = new JLabel("Active");
        jcmbStatus = new JComboBox(new Object[]{"Yes","No"});
        
        jlblYearFrom = new JLabel("Year From");
        jlblYearTo = new JLabel("Year To");
        
        int currentYear = CalendarUtil.getCurrentYear();
        int min = CalendarUtil.getCurrentYear();
        int max = (currentYear + 10);
        int stepSize = 1;
        
        SpinnerNumberModel yearFromModel = new SpinnerNumberModel(currentYear, min, max, stepSize);
        jsprYearFrom = new JSpinner();
        jsprYearFrom.setModel(yearFromModel);
        JSpinner.NumberEditor yearFromEditor = new JSpinner.NumberEditor(jsprYearFrom, "#");
        jsprYearFrom.setEditor(yearFromEditor);

        SpinnerNumberModel yearToModel = new SpinnerNumberModel(currentYear+1,min+1,max+1,stepSize);
        jsprYearTo = new JSpinner();
        jsprYearTo.setModel(yearToModel);
        JSpinner.NumberEditor yearToEditor = new JSpinner.NumberEditor(jsprYearTo, "#");
        jsprYearTo.setEditor(yearToEditor);
        JSpinnerUtil.setDisabledForegroundColorTo(jsprYearTo, Color.BLACK);
        
        jsprYearTo.setEnabled(false);
        JSpinnerUtil.setDisabledForegroundColorTo(jsprYearTo, Color.BLACK);
    }
    
    private void setAllDatePickerToYearFromSelected(int spinnerYearFromValue){
        List<Component[]> componentsArr = new ArrayList<>();
        componentsArr.add(jpnlSchoolYear.getComponents());
        componentsArr.add(jpnlRegularEnrollmentSchedule.getComponents());
        componentsArr.add(jpnlSummerEnrollmentSchedule.getComponents());
        componentsArr.add(jpnlQuarterScheduleAndGradingDueDate.getComponents());
        componentsArr.add(jpnlSummerClassSchedule.getComponents());
        for (int i = 0; i < componentsArr.size(); i++) {
            for (Component c : componentsArr.get(i)) {
                if (c instanceof JDatePickerImpl) {
                    ((JDatePickerImpl) c).getModel().setYear(spinnerYearFromValue);
                }
            }
        }
    }
    
    private void createGUI(){
        this.add(jpnlTopPanel,gbcJpnlTopPanel);
        jpnlSchoolYear.add(jlblSchoolYearStartDate,setPosition(0, 0, 0, 0, GridBagConstraints.NONE));
        jpnlSchoolYear.add(dpSchoolYearStartDate,setPosition(1, 0, 0, 0, GridBagConstraints.NONE));
        jpnlSchoolYear.add(jlblSchoolYearEndDate,setPosition(2, 0, 0, 0, GridBagConstraints.NONE));
        jpnlSchoolYear.add(dpSchoolYearEndDate,setPosition(3, 0, 0, 0, GridBagConstraints.NONE));
        jpnlSchoolYear.add(jtfTotalSchoolDays,setPosition(4, 0, 0, 0, GridBagConstraints.NONE));
        jpnlSchoolYear.add(jlblSchoolDays,setPosition(5, 0, 0, 0, GridBagConstraints.NONE));
        jpnlSchoolYear.add(jlblStatus,setPosition(6, 0, 0, 0, GridBagConstraints.NONE));
        jpnlSchoolYear.add(jcmbStatus,setPosition(7, 0, 0, 0, GridBagConstraints.NONE));
        jpnlSchoolYear.add(jlblYearFrom,setPosition(8, 0, 0, 0, GridBagConstraints.NONE));
        jpnlSchoolYear.add(jsprYearFrom,setPosition(9, 0, 0, 0, GridBagConstraints.NONE));
        jpnlSchoolYear.add(jlblYearTo,setPosition(10, 0, 0, 0, GridBagConstraints.NONE));
        jpnlSchoolYear.add(jsprYearTo,setPosition(11, 0, 0, 0, GridBagConstraints.NONE));
        
        jpnlEnrollmentSchedule.add(jpnlRegularEnrollmentSchedule,setPosition(0, 0, 0.5, 0.5, GridBagConstraints.HORIZONTAL));
        jpnlEnrollmentSchedule.add(jpnlSummerEnrollmentSchedule,setPosition(1, 0, 0.5, 0.5, GridBagConstraints.HORIZONTAL));
        
        jpnlRegularEnrollmentSchedule.add(jlblRegularEnrollmentStartDate,setPosition(0, 0, 0, 0, GridBagConstraints.NONE));
        jpnlRegularEnrollmentSchedule.add(dpRegularEnrollmentStartDate,setPosition(1, 0, 0, 0, GridBagConstraints.NONE));
        jpnlRegularEnrollmentSchedule.add(jlblRegularEnrollmentEndDate,setPosition(2, 0, 0, 0, GridBagConstraints.NONE));
        jpnlRegularEnrollmentSchedule.add(dpRegularEnrollmentEndDate,setPosition(3, 0, 0, 0, GridBagConstraints.NONE));
        
        jpnlSummerEnrollmentSchedule.add(jlblSummerEnrollmentStartDate,setPosition(0, 0, 0, 0, GridBagConstraints.NONE));
        jpnlSummerEnrollmentSchedule.add(dpSummerEnrollmentStartDate,setPosition(1, 0, 0, 0, GridBagConstraints.NONE));
        jpnlSummerEnrollmentSchedule.add(jlblSummerEnrollmentEndDate,setPosition(2, 0, 0, 0, GridBagConstraints.NONE));
        jpnlSummerEnrollmentSchedule.add(dpSummerEnrollmentEndDate,setPosition(3, 0, 0, 0, GridBagConstraints.NONE));
        
        jpnlQuarterScheduleAndGradingDueDate.add(jlbl1stQtrStartDate,setPosition(0, 0, 0, 0, GridBagConstraints.NONE));
        jpnlQuarterScheduleAndGradingDueDate.add(dp1stQtrStartDate,setPosition(1, 0, 0, 0, GridBagConstraints.NONE));
        jpnlQuarterScheduleAndGradingDueDate.add(jlbl1stQtrEndDate,setPosition(2, 0, 0, 0, GridBagConstraints.NONE));
        jpnlQuarterScheduleAndGradingDueDate.add(dp1stQtrEndDate,setPosition(3, 0, 0, 0, GridBagConstraints.NONE));
        jpnlQuarterScheduleAndGradingDueDate.add(jlbl1stQtrGradingOpenDate,setPosition(4, 0, 0, 0, GridBagConstraints.NONE));
        jpnlQuarterScheduleAndGradingDueDate.add(dp1stQtrGradingOpenDate,setPosition(5, 0, 0, 0, GridBagConstraints.NONE));
        jpnlQuarterScheduleAndGradingDueDate.add(jlbl1stQtrGradingDueDate,setPosition(6, 0, 0, 0, GridBagConstraints.NONE));
        jpnlQuarterScheduleAndGradingDueDate.add(dp1stQtrGradingDuedate,setPosition(7, 0, 0, 0, GridBagConstraints.NONE));
        
        jpnlQuarterScheduleAndGradingDueDate.add(jlbl2ndQtrStartDate,setPosition(0, 1, 0, 0, GridBagConstraints.NONE));
        jpnlQuarterScheduleAndGradingDueDate.add(dp2ndQtrStartDate,setPosition(1, 1, 0, 0, GridBagConstraints.NONE));
        jpnlQuarterScheduleAndGradingDueDate.add(jlbl2ndQtrEndDate,setPosition(2, 1, 0, 0, GridBagConstraints.NONE));
        jpnlQuarterScheduleAndGradingDueDate.add(dp2ndQtrEndDate,setPosition(3, 1, 0, 0, GridBagConstraints.NONE));
        jpnlQuarterScheduleAndGradingDueDate.add(jlbl2ndQtrGradingOpenDate,setPosition(4, 1, 0, 0, GridBagConstraints.NONE));
        jpnlQuarterScheduleAndGradingDueDate.add(dp2ndQtrGradingOpenDate,setPosition(5, 1, 0, 0, GridBagConstraints.NONE));
        jpnlQuarterScheduleAndGradingDueDate.add(jlbl2ndQtrGradingDueDate,setPosition(6, 1, 0, 0, GridBagConstraints.NONE));
        jpnlQuarterScheduleAndGradingDueDate.add(dp2ndQtrGradingDueDate,setPosition(7, 1, 0, 0, GridBagConstraints.NONE));
        
        jpnlQuarterScheduleAndGradingDueDate.add(jlbl3rdQtrStartDate,setPosition(0, 2, 0, 0, GridBagConstraints.NONE));
        jpnlQuarterScheduleAndGradingDueDate.add(dp3rdQtrStartDate,setPosition(1, 2, 0, 0, GridBagConstraints.NONE));
        jpnlQuarterScheduleAndGradingDueDate.add(jlbl3rdQtrEndDate,setPosition(2, 2, 0, 0, GridBagConstraints.NONE));
        jpnlQuarterScheduleAndGradingDueDate.add(dp3rdQtrEndDate,setPosition(3, 2, 0, 0, GridBagConstraints.NONE));
        jpnlQuarterScheduleAndGradingDueDate.add(jlbl3rdQtrGradingOpenDate,setPosition(4, 2, 0, 0, GridBagConstraints.NONE));
        jpnlQuarterScheduleAndGradingDueDate.add(dp3rdQtrGradingOpenDate,setPosition(5, 2, 0, 0, GridBagConstraints.NONE));
        jpnlQuarterScheduleAndGradingDueDate.add(jlbl3rdQtrGradingDueDate,setPosition(6, 2, 0, 0, GridBagConstraints.NONE));
        jpnlQuarterScheduleAndGradingDueDate.add(dp3rdQtrGradingDueDate, setPosition(7, 2, 0, 0, GridBagConstraints.NONE));
        
        jpnlQuarterScheduleAndGradingDueDate.add(jlbl4thQtrStartDate,setPosition(0, 3, 0, 0, GridBagConstraints.NONE));
        jpnlQuarterScheduleAndGradingDueDate.add(dp4thQtrStartDate,setPosition(1, 3, 0, 0, GridBagConstraints.NONE));
        jpnlQuarterScheduleAndGradingDueDate.add(jlbl4thQtrEndDate,setPosition(2, 3, 0, 0, GridBagConstraints.NONE));
        jpnlQuarterScheduleAndGradingDueDate.add(dp4thQtrEndDate,setPosition(3, 3, 0, 0, GridBagConstraints.NONE));
        jpnlQuarterScheduleAndGradingDueDate.add(jlbl4thQtrGradingOpenDate,setPosition(4, 3, 0, 0, GridBagConstraints.NONE));
        jpnlQuarterScheduleAndGradingDueDate.add(dp4thQtrGradingOpenDate,setPosition(5, 3, 0, 0, GridBagConstraints.NONE));
        jpnlQuarterScheduleAndGradingDueDate.add(jlbl4thQtrGradingDueDate,setPosition(6, 3, 0, 0, GridBagConstraints.NONE));
        jpnlQuarterScheduleAndGradingDueDate.add(dp4thQtrGradingDueDate,setPosition(7, 3, 0, 0, GridBagConstraints.NONE));
        
        jpnlSummerClassSchedule.add(jlblSummerClassStart,setPosition(0, 0, 0, 0, GridBagConstraints.NONE));
        jpnlSummerClassSchedule.add(dpSummerClassStart,setPosition(1, 0, 0, 0, GridBagConstraints.NONE));
        jpnlSummerClassSchedule.add(jlblSummerClassEnd,setPosition(2, 0, 0, 0, GridBagConstraints.NONE));
        jpnlSummerClassSchedule.add(dpSummerClassEnd,setPosition(3, 0, 0, 0, GridBagConstraints.NONE));
        
        jpnlContainer.add(jpnlSchoolYear,setPosition(0, 0, 0.5, 0, GridBagConstraints.HORIZONTAL));
        jpnlContainer.add(jpnlEnrollmentSchedule,setPosition(0, 1, 0.5, 0, GridBagConstraints.HORIZONTAL));
        jpnlContainer.add(jpnlQuarterScheduleAndGradingDueDate,setPosition(0, 2, 0.5, 0, GridBagConstraints.HORIZONTAL));
        jpnlContainer.add(jpnlSummerClassSchedule,setPosition(0, 3, 0.5, 0, GridBagConstraints.HORIZONTAL));
        
        jpnlControls.add(jbtnSave,setPosition(0, 0, 0, 0, GridBagConstraints.NONE));
        
        jpnlTopPanel.add(jpnlContainer,setPosition(0, 0, 0.5, 0.5, GridBagConstraints.BOTH));
        jpnlTopPanel.add(jpnlControls,setPosition(0, 4, 0.5, 0.5, GridBagConstraints.HORIZONTAL));
    } 
    
    private Date getStartDateOfQuarter(int quarterNo){
        Date startDate = null;
        List<Quarter> quarters = schoolYearSelected.getQuarters();
        for(Quarter q : quarters){
            if(q.getQuarterNo() == quarterNo){
                startDate = q.getStartDate();
                return startDate;
            }
        }
        return startDate;
    }
    
    private Date getEndDateOfQuarter(int quarterNo){
        Date endDate = null;
        List<Quarter> quarters = schoolYearSelected.getQuarters();
        for(Quarter q : quarters){
            if(q.getQuarterNo() == quarterNo){
                endDate = q.getEndDate();
                return endDate;
            }
        }
        return endDate;
    }
    
    private Date getGradingOpenDateOf(int quarterNo){
        Date gradingOpenDate = null;
        List<Quarter> quarters = schoolYearSelected.getQuarters();
        for(Quarter q : quarters){
            if(q.getQuarterNo() == quarterNo){
                gradingOpenDate = q.getGradingOpenDate();
                return gradingOpenDate;
            }
        }
        return gradingOpenDate;
    }
    
    private Date getGradingCloseDateOfQuarter(int quarterNo){
        Date gradingCloseDate = null;
        List<Quarter> quarters = schoolYearSelected.getQuarters();
        for(Quarter q : quarters){
            if(q.getQuarterNo() == quarterNo){
                gradingCloseDate = q.getGradingDueDate();
                return gradingCloseDate;
            }
        }
        return gradingCloseDate;
    }
    
    public Calendar toCalendar(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal;
    }
    
    private void initForm(){
        jtfTotalSchoolDays.setText(""+schoolYearSelected.getTotalSchoolDays());
        dpSchoolYearStartDate.getJFormattedTextField().setText(""+schoolYearSelected.getSchoolYearStartDate());
        dpSchoolYearEndDate.getJFormattedTextField().setText(""+schoolYearSelected.getSchoolYearEndDate());
        dpRegularEnrollmentStartDate.getJFormattedTextField().setText(""+schoolYearSelected.getRegularEnrollmentStartDate());
        dpRegularEnrollmentEndDate.getJFormattedTextField().setText(""+schoolYearSelected.getRegularEnrollmentEndDate());
        dpSummerEnrollmentStartDate.getJFormattedTextField().setText(""+schoolYearSelected.getSummerEnrollmentStartDate());
        dpSummerEnrollmentEndDate.getJFormattedTextField().setText(""+schoolYearSelected.getSummerEnrollmentEndDate());
        dpSummerClassStart.getJFormattedTextField().setText(""+schoolYearSelected.getSummerClassStartDate());
        dpSummerClassEnd.getJFormattedTextField().setText(""+schoolYearSelected.getSummerClassEndDate());

        
        dp1stQtrStartDate.getJFormattedTextField().setText("" + getStartDateOfQuarter(1));
        dp1stQtrEndDate.getJFormattedTextField().setText("" + getEndDateOfQuarter(1));
        dp1stQtrGradingOpenDate.getJFormattedTextField().setText("" + getGradingOpenDateOf(1));
        dp1stQtrGradingDuedate.getJFormattedTextField().setText("" + getGradingCloseDateOfQuarter(1));

        dp2ndQtrStartDate.getJFormattedTextField().setText("" + getStartDateOfQuarter(2));
        dp2ndQtrEndDate.getJFormattedTextField().setText("" + getEndDateOfQuarter(2));
        dp2ndQtrGradingOpenDate.getJFormattedTextField().setText("" + getGradingOpenDateOf(2));
        dp2ndQtrGradingDueDate.getJFormattedTextField().setText("" + getGradingCloseDateOfQuarter(2));
        
        dp3rdQtrStartDate.getJFormattedTextField().setText(""+getStartDateOfQuarter(3));
        dp3rdQtrEndDate.getJFormattedTextField().setText(""+getEndDateOfQuarter(3));
        dp3rdQtrGradingOpenDate.getJFormattedTextField().setText(""+getGradingOpenDateOf(3));
        dp3rdQtrGradingDueDate.getJFormattedTextField().setText(""+getGradingCloseDateOfQuarter(3));
        
        dp4thQtrStartDate.getJFormattedTextField().setText(""+getStartDateOfQuarter(4));
        dp4thQtrEndDate.getJFormattedTextField().setText(""+getEndDateOfQuarter(4));
        dp4thQtrGradingOpenDate.getJFormattedTextField().setText(""+getGradingOpenDateOf(4));
        dp4thQtrGradingDueDate.getJFormattedTextField().setText(""+getGradingCloseDateOfQuarter(4));
    }
    
    private void disableFields(){
        List<Component[]> componentsArr = new ArrayList<>();
        componentsArr.add(jpnlSchoolYear.getComponents());
        componentsArr.add(jpnlRegularEnrollmentSchedule.getComponents());
        componentsArr.add(jpnlSummerEnrollmentSchedule.getComponents());
        componentsArr.add(jpnlQuarterScheduleAndGradingDueDate.getComponents());
        for (int i = 0; i < componentsArr.size(); i++) {
            for (Component c : componentsArr.get(i)) {
                if (c instanceof JDatePickerImpl) {
                    ((JDatePickerImpl) c).setEnabled(false);
                }
                else if(c instanceof JDatePanelImpl){
                    ((JDatePanelImpl) c).setEnabled(false);
                }
                else if(c instanceof JComboBox){
                    ((JComboBox) c).setEnabled(false);
                }
                else if(c instanceof JSpinner){
                    ((JSpinner) c).setEnabled(false);
                }
            }
        }
    }

    public JSpinner getJsprYearTo() {
        return jsprYearTo;
    }

    public JLabel getJlblSchoolDays() {
        return jlblSchoolDays;
    }

    public JTextField getJtfTotalSchoolDays() {
        return jtfTotalSchoolDays;
    }

    public JPanel getJpnlTopPanel() {
        return jpnlTopPanel;
    }

    public JPanel getJpnlControls() {
        return jpnlControls;
    }

    public JPanel getJpnlSchoolYear() {
        return jpnlSchoolYear;
    }

    public JPanel getJpnlSummerClassSchedule() {
        return jpnlSummerClassSchedule;
    }

    public JLabel getJlblSummerClassStart() {
        return jlblSummerClassStart;
    }

    public JDatePanelImpl getDpnlSummerClassStart() {
        return dpnlSummerClassStart;
    }

    public JDatePickerImpl getDpSummerClassStart() {
        return dpSummerClassStart;
    }

    public JLabel getJlblSummerClassEnd() {
        return jlblSummerClassEnd;
    }

    public JDatePanelImpl getDpnlSummerClassEnd() {
        return dpnlSummerClassEnd;
    }

    public JDatePickerImpl getDpSummerClassEnd() {
        return dpSummerClassEnd;
    }

    public JLabel getJlblSchoolYearStartDate() {
        return jlblSchoolYearStartDate;
    }

    public JLabel getJlblSchoolYearEndDate() {
        return jlblSchoolYearEndDate;
    }

    public JPanel getJpnlEnrollmentSchedule() {
        return jpnlEnrollmentSchedule;
    }

    public JPanel getJpnlRegularEnrollmentSchedule() {
        return jpnlRegularEnrollmentSchedule;
    }

    public JPanel getJpnlSummerEnrollmentSchedule() {
        return jpnlSummerEnrollmentSchedule;
    }

    public JLabel getJlblRegularEnrollmentStartDate() {
        return jlblRegularEnrollmentStartDate;
    }

    public JLabel getJlblRegularEnrollmentEndDate() {
        return jlblRegularEnrollmentEndDate;
    }

    public JLabel getJlblSummerEnrollmentStartDate() {
        return jlblSummerEnrollmentStartDate;
    }

    public JLabel getJlblSummerEnrollmentEndDate() {
        return jlblSummerEnrollmentEndDate;
    }

    public JPanel getJpnlQuarterScheduleAndGradingDueDate() {
        return jpnlQuarterScheduleAndGradingDueDate;
    }

    public JPanel getJpnlContainer() {
        return jpnlContainer;
    }

    public JLabel getJlbl1stQtrStartDate() {
        return jlbl1stQtrStartDate;
    }

    public JLabel getJlbl1stQtrEndDate() {
        return jlbl1stQtrEndDate;
    }

    public JLabel getJlbl1stQtrGradingOpenDate() {
        return jlbl1stQtrGradingOpenDate;
    }

    public JLabel getJlbl1stQtrGradingDueDate() {
        return jlbl1stQtrGradingDueDate;
    }

    public JLabel getJlbl2ndQtrStartDate() {
        return jlbl2ndQtrStartDate;
    }

    public JLabel getJlbl2ndQtrEndDate() {
        return jlbl2ndQtrEndDate;
    }

    public JLabel getJlbl2ndQtrGradingOpenDate() {
        return jlbl2ndQtrGradingOpenDate;
    }

    public JLabel getJlbl2ndQtrGradingDueDate() {
        return jlbl2ndQtrGradingDueDate;
    }

    public JLabel getJlbl3rdQtrStartDate() {
        return jlbl3rdQtrStartDate;
    }

    public JLabel getJlbl3rdQtrEndDate() {
        return jlbl3rdQtrEndDate;
    }

    public JLabel getJlbl3rdQtrGradingOpenDate() {
        return jlbl3rdQtrGradingOpenDate;
    }

    public JLabel getJlbl3rdQtrGradingDueDate() {
        return jlbl3rdQtrGradingDueDate;
    }

    public JLabel getJlbl4thQtrStartDate() {
        return jlbl4thQtrStartDate;
    }

    public JLabel getJlbl4thQtrEndDate() {
        return jlbl4thQtrEndDate;
    }

    public JLabel getJlbl4thQtrGradingOpenDate() {
        return jlbl4thQtrGradingOpenDate;
    }

    public JLabel getJlbl4thQtrGradingDueDate() {
        return jlbl4thQtrGradingDueDate;
    }

    public JLabel getJlblDash() {
        return jlblDash;
    }

    public JDatePanelImpl getDpnlSchoolYearStartDate() {
        return dpnlSchoolYearStartDate;
    }

    public JDatePanelImpl getDpnlSchoolYearEndDate() {
        return dpnlSchoolYearEndDate;
    }

    public JDatePanelImpl getDpnlRegularEnrollmentStartDate() {
        return dpnlRegularEnrollmentStartDate;
    }

    public JDatePanelImpl getDpnlRegularEnrollmentEndDate() {
        return dpnlRegularEnrollmentEndDate;
    }

    public JDatePanelImpl getDpnlSummerEnrollmentStartDate() {
        return dpnlSummerEnrollmentStartDate;
    }

    public JDatePanelImpl getDpnlSummerEnrollmentEndDate() {
        return dpnlSummerEnrollmentEndDate;
    }

    public JDatePanelImpl getDpnl1stQtrStartDate() {
        return dpnl1stQtrStartDate;
    }

    public JDatePanelImpl getDpnl2ndQtrStartDate() {
        return dpnl2ndQtrStartDate;
    }

    public JDatePanelImpl getDpnl3rdQtrStartDate() {
        return dpnl3rdQtrStartDate;
    }

    public JDatePanelImpl getDpnl4thQtrStartDate() {
        return dpnl4thQtrStartDate;
    }

    public JDatePanelImpl getDpnl1stQtrGradingOpenDate() {
        return dpnl1stQtrGradingOpenDate;
    }

    public JDatePanelImpl getDpnl2ndQtrGradingOpenDate() {
        return dpnl2ndQtrGradingOpenDate;
    }

    public JDatePanelImpl getDpnl3rdQtrGradingOpenDate() {
        return dpnl3rdQtrGradingOpenDate;
    }

    public JDatePanelImpl getDpnl4thQtrGradingOpenDate() {
        return dpnl4thQtrGradingOpenDate;
    }

    public JDatePanelImpl getDpnl1stQtrGradingDueDate() {
        return dpnl1stQtrGradingDueDate;
    }

    public JDatePanelImpl getDpnl2ndQtrGradingDueDate() {
        return dpnl2ndQtrGradingDueDate;
    }

    public JDatePanelImpl getDpnl3rdQtrGradingDueDate() {
        return dpnl3rdQtrGradingDueDate;
    }

    public JDatePanelImpl getDpnl4thQtrGradingDueDate() {
        return dpnl4thQtrGradingDueDate;
    }

    public JDatePanelImpl getDpnl1stQtrEndDate() {
        return dpnl1stQtrEndDate;
    }

    public JDatePanelImpl getDpnl2ndQtrEndDate() {
        return dpnl2ndQtrEndDate;
    }

    public JDatePanelImpl getDpnl3rdQtrEndDate() {
        return dpnl3rdQtrEndDate;
    }

    public JDatePanelImpl getDpnl4thQtrEndDate() {
        return dpnl4thQtrEndDate;
    }

    public JDatePickerImpl getDpSchoolYearStartDate() {
        return dpSchoolYearStartDate;
    }

    public JDatePickerImpl getDpSchoolYearEndDate() {
        return dpSchoolYearEndDate;
    }

    public JDatePickerImpl getDpRegularEnrollmentStartDate() {
        return dpRegularEnrollmentStartDate;
    }

    public JDatePickerImpl getDpRegularEnrollmentEndDate() {
        return dpRegularEnrollmentEndDate;
    }

    public JDatePickerImpl getDpSummerEnrollmentStartDate() {
        return dpSummerEnrollmentStartDate;
    }

    public JDatePickerImpl getDpSummerEnrollmentEndDate() {
        return dpSummerEnrollmentEndDate;
    }

    public JDatePickerImpl getDp1stQtrStartDate() {
        return dp1stQtrStartDate;
    }

    public JDatePickerImpl getDp1stQtrEndDate() {
        return dp1stQtrEndDate;
    }

    public JDatePickerImpl getDp1stQtrGradingOpenDate() {
        return dp1stQtrGradingOpenDate;
    }

    public JDatePickerImpl getDp1stQtrGradingDuedate() {
        return dp1stQtrGradingDuedate;
    }

    public JDatePickerImpl getDp2ndQtrStartDate() {
        return dp2ndQtrStartDate;
    }

    public JDatePickerImpl getDp2ndQtrEndDate() {
        return dp2ndQtrEndDate;
    }

    public JDatePickerImpl getDp2ndQtrGradingOpenDate() {
        return dp2ndQtrGradingOpenDate;
    }

    public JDatePickerImpl getDp2ndQtrGradingDueDate() {
        return dp2ndQtrGradingDueDate;
    }

    public JDatePickerImpl getDp3rdQtrStartDate() {
        return dp3rdQtrStartDate;
    }

    public JDatePickerImpl getDp3rdQtrEndDate() {
        return dp3rdQtrEndDate;
    }

    public JDatePickerImpl getDp3rdQtrGradingOpenDate() {
        return dp3rdQtrGradingOpenDate;
    }

    public JDatePickerImpl getDp3rdQtrGradingDueDate() {
        return dp3rdQtrGradingDueDate;
    }

    public JDatePickerImpl getDp4thQtrStartDate() {
        return dp4thQtrStartDate;
    }

    public JDatePickerImpl getDp4thQtrEndDate() {
        return dp4thQtrEndDate;
    }

    public JDatePickerImpl getDp4thQtrGradingOpenDate() {
        return dp4thQtrGradingOpenDate;
    }

    public JDatePickerImpl getDp4thQtrGradingDueDate() {
        return dp4thQtrGradingDueDate;
    }

    public JLabel getJlblYearFrom() {
        return jlblYearFrom;
    }

    public JLabel getJlblYearTo() {
        return jlblYearTo;
    }

    public JSpinner getJsprYearFrom() {
        return jsprYearFrom;
    }

    public JButton getJbtnSave() {
        return jbtnSave;
    }

    public JLabel getJlblStatus() {
        return jlblStatus;
    }

    public JComboBox getJcmbStatus() {
        return jcmbStatus;
    }

    public String getAction() {
        return action;
    }
    
  
    
    
}
