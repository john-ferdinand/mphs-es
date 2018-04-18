
package view.paymentsetting;

import component_model_loader.SchoolYearJCompModelLoader;
import component_renderers.Renderer_SchoolYear_JComboBox;
import controller.paymentsetting.DisplayNewPaymentScheduleController;
import daoimpl.PaymentTermDaoImpl;
import daoimpl.SchoolYearDaoImpl;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import model.paymentterm.PaymentTerm;
import model.period.Period;
import model.schoolyear.SchoolYear;
import utility.initializer.Initializer;

/**
 *
 * @author John Ferdinand Antonio
 */
public class PaymentScheduleSettings extends javax.swing.JPanel implements Initializer{
    
    private DisplayNewPaymentScheduleController displayDialogController;
    private SchoolYearDaoImpl schoolYearDaoImpl;
    private PaymentTermDaoImpl paymentTermDaoImpl;
    private SchoolYearJCompModelLoader schoolYearJCompModelLoader;
    
    public PaymentScheduleSettings() {
        initComponents();
        
        initDaoImpl();
        initJCompModelLoaders();
        initControllers();
        initRenderers();
        initViewComponents();
    }

    @Override
    public void initGridBagConstraints() {
        
    }

    @Override
    public void initJCompModelLoaders() {
        schoolYearJCompModelLoader = new SchoolYearJCompModelLoader();
    }

    @Override
    public void initRenderers() {
        jcmbSY.setRenderer(new Renderer_SchoolYear_JComboBox());
    }

    @Override
    public void initModels() {
        
    }

    @Override
    public void initViewComponents() {
        jcmbSY.setModel(schoolYearJCompModelLoader.getAllSchoolYear());
        SchoolYear csy = schoolYearDaoImpl.getCurrentSchoolYear();
        DefaultComboBoxModel comboModel = (DefaultComboBoxModel) jcmbSY.getModel();
        for(int i = 0; i < comboModel.getSize(); i++){
            Object element = comboModel.getElementAt(i);
            if(element instanceof SchoolYear){
                SchoolYear sy = (SchoolYear) element;
                if(sy.getSchoolYearId() == csy.getSchoolYearId()){
                    jcmbSY.setSelectedItem(element);
                }
            }
        }
        loadSemestralSchedule();
        loadQuarterlySchedule();
        loadMonthlySchedule();
    }

    @Override
    public void initControllers() {
        displayDialogController = new DisplayNewPaymentScheduleController(this,jbtnCreateNew,jbtnEdit);
        displayDialogController.displayForCreate();
        displayDialogController.displayForEdit();
        
        jcmbSY.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                loadSemestralSchedule();
                loadQuarterlySchedule();
                loadMonthlySchedule();
            }
        });
    }

    @Override
    public void initDaoImpl() {
        schoolYearDaoImpl = new SchoolYearDaoImpl();
        paymentTermDaoImpl = new PaymentTermDaoImpl();
    }

    private void loadMonthlySchedule(){
        SchoolYear schoolYear = (SchoolYear) jcmbSY.getSelectedItem();
        List<PaymentTerm> paymentTerms = paymentTermDaoImpl.getAllPaymentTermsSchedulesAndPenaltyOf(schoolYear);
        for(PaymentTerm pt : paymentTerms){
            if(pt.getPaymentTermName().trim().equalsIgnoreCase("Monthly")){
                for(Period period : pt.getPeriods()){
                    if(period.getPeriodName().trim().equalsIgnoreCase("1st Month")){
                        jlblFirstMonth.setText(""+period.getPaymentDeadline());
                    }else if(period.getPeriodName().trim().equalsIgnoreCase("2nd Month")){
                        jlblSecondMonth.setText(""+period.getPaymentDeadline());
                    }else if(period.getPeriodName().trim().equalsIgnoreCase("3rd Month")){
                        jlblThirdMonth.setText(""+period.getPaymentDeadline());
                    }else if(period.getPeriodName().trim().equalsIgnoreCase("4th Month")){
                        jlblFourthMonth.setText(""+period.getPaymentDeadline());
                    }else if(period.getPeriodName().trim().equalsIgnoreCase("5th Month")){
                        jlblFifthMonth.setText(""+period.getPaymentDeadline());
                    }else if(period.getPeriodName().trim().equalsIgnoreCase("6th Month")){
                        jlblSixthMonth.setText(""+period.getPaymentDeadline());
                    }else if(period.getPeriodName().trim().equalsIgnoreCase("7th Month")){
                        jlblSeventhMonth.setText(""+period.getPaymentDeadline());
                    }else if(period.getPeriodName().trim().equalsIgnoreCase("8th Month")){
                        jlblEighthMonth.setText(""+period.getPaymentDeadline());
                    }else if(period.getPeriodName().trim().equalsIgnoreCase("9th Month")){
                        jlblNinthMonth.setText(""+period.getPaymentDeadline());
                    }else if(period.getPeriodName().trim().equalsIgnoreCase("10th Month")){
                        jlblTenthMonth.setText(""+period.getPaymentDeadline());
                    }
                }
                jlblMonthlyPenalty.setText(""+pt.getPenaltyAmount());
            }
        }
    }
    
    private void loadQuarterlySchedule(){
        SchoolYear schoolYear = (SchoolYear) jcmbSY.getSelectedItem();
        List<PaymentTerm> paymentTerms = paymentTermDaoImpl.getAllPaymentTermsSchedulesAndPenaltyOf(schoolYear);
        for(PaymentTerm pt : paymentTerms){
            if(pt.getPaymentTermName().trim().equalsIgnoreCase("Quarterly")){
                for(Period period : pt.getPeriods()){
                    if(period.getPeriodName().trim().equalsIgnoreCase("1st Quarter")){
                        jlblFirstQuarter.setText(""+period.getPaymentDeadline());
                    }else if(period.getPeriodName().trim().equalsIgnoreCase("2nd Quarter")){
                        jlblSecondQuarter.setText(""+period.getPaymentDeadline());
                    }else if(period.getPeriodName().trim().equalsIgnoreCase("3rd Quarter")){
                        jlblThirdQuarter.setText(""+period.getPaymentDeadline());
                    }else if(period.getPeriodName().trim().equalsIgnoreCase("4th Quarter")){
                        jlblFourthQuarter.setText(""+period.getPaymentDeadline());
                    }
                }
                jlblQuarterlyPenalty.setText(""+pt.getPenaltyAmount());
            }
        }
    }
    
    private void loadSemestralSchedule(){
        SchoolYear schoolYear = (SchoolYear) jcmbSY.getSelectedItem();
        List<PaymentTerm> paymentTerms = paymentTermDaoImpl.getAllPaymentTermsSchedulesAndPenaltyOf(schoolYear);
        for(PaymentTerm pt : paymentTerms){
            if(pt.getPaymentTermName().trim().equalsIgnoreCase("Semestral")){
                for(Period period : pt.getPeriods()){
                    if(period.getPeriodName().trim().equalsIgnoreCase("1st Semester")){
                        jlblFirstSem.setText(""+period.getPaymentDeadline());
                    }else if(period.getPeriodName().trim().equalsIgnoreCase("2nd Semester")){
                        jlblSecondSem.setText(""+period.getPaymentDeadline());
                    }
                }
                jlblSemestralPenalty.setText(""+pt.getPenaltyAmount());
            }
        }
    }

    public JButton getJbtnCreateNew() {
        return jbtnCreateNew;
    }

    public JButton getJbtnRefresh() {
        return jbtnRefresh;
    }

    public JComboBox<String> getJcmbSY() {
        return jcmbSY;
    }

    public JLabel getJlblEighthMonth() {
        return jlblEighthMonth;
    }

    public JLabel getJlblFifthMonth() {
        return jlblFifthMonth;
    }

    public JLabel getJlblFirstMonth() {
        return jlblFirstMonth;
    }

    public JLabel getJlblFirstQuarter() {
        return jlblFirstQuarter;
    }

    public JLabel getJlblFirstSem() {
        return jlblFirstSem;
    }

    public JLabel getJlblFourthMonth() {
        return jlblFourthMonth;
    }

    public JLabel getJlblFourthQuarter() {
        return jlblFourthQuarter;
    }

    public JLabel getJlblMonthlyPenalty() {
        return jlblMonthlyPenalty;
    }

    public JLabel getJlblNinthMonth() {
        return jlblNinthMonth;
    }

    public JLabel getJlblQuarterlyPenalty() {
        return jlblQuarterlyPenalty;
    }

    public JLabel getJlblSecondMonth() {
        return jlblSecondMonth;
    }

    public JLabel getJlblSecondQuarter() {
        return jlblSecondQuarter;
    }

    public JLabel getJlblSecondSem() {
        return jlblSecondSem;
    }

    public JLabel getJlblSemestralPenalty() {
        return jlblSemestralPenalty;
    }

    public JLabel getJlblSeventhMonth() {
        return jlblSeventhMonth;
    }

    public JLabel getJlblSixthMonth() {
        return jlblSixthMonth;
    }

    public JLabel getJlblTenthMonth() {
        return jlblTenthMonth;
    }

    public JLabel getJlblThirdMonth() {
        return jlblThirdMonth;
    }

    public JLabel getJlblThirdQuarter() {
        return jlblThirdQuarter;
    }

    public JPanel getPanel_control() {
        return panel_control;
    }

    public JPanel getPanel_monthly() {
        return panel_monthly;
    }

    public JPanel getPanel_quarterly() {
        return panel_quarterly;
    }

    public JPanel getPanel_semstral() {
        return panel_semstral;
    }

    public JButton getJbtnEdit() {
        return jbtnEdit;
    }
    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        panel_control = new javax.swing.JPanel();
        jbtnCreateNew = new javax.swing.JButton();
        jbtnRefresh = new javax.swing.JButton();
        javax.swing.JLabel lbl_Sy = new javax.swing.JLabel();
        jcmbSY = new javax.swing.JComboBox<>();
        jbtnEdit = new javax.swing.JButton();
        panel_semstral = new javax.swing.JPanel();
        javax.swing.JLabel lbl_FirstSem = new javax.swing.JLabel();
        jlblFirstSem = new javax.swing.JLabel();
        javax.swing.JLabel lbl_FirstSem1 = new javax.swing.JLabel();
        jlblSecondSem = new javax.swing.JLabel();
        javax.swing.JLabel lbl_FirstSem2 = new javax.swing.JLabel();
        jlblSemestralPenalty = new javax.swing.JLabel();
        panel_quarterly = new javax.swing.JPanel();
        javax.swing.JLabel lbl_FirstSem3 = new javax.swing.JLabel();
        jlblFirstQuarter = new javax.swing.JLabel();
        javax.swing.JLabel lbl_FirstSem4 = new javax.swing.JLabel();
        jlblThirdQuarter = new javax.swing.JLabel();
        javax.swing.JLabel lbl_FirstSem5 = new javax.swing.JLabel();
        jlblQuarterlyPenalty = new javax.swing.JLabel();
        javax.swing.JLabel lbl_FirstSem6 = new javax.swing.JLabel();
        jlblSecondQuarter = new javax.swing.JLabel();
        javax.swing.JLabel lbl_FirstSem7 = new javax.swing.JLabel();
        jlblFourthQuarter = new javax.swing.JLabel();
        panel_monthly = new javax.swing.JPanel();
        javax.swing.JLabel lbl_FirstSem8 = new javax.swing.JLabel();
        javax.swing.JLabel lbl_FirstSem9 = new javax.swing.JLabel();
        jlblFirstMonth = new javax.swing.JLabel();
        jlblSecondMonth = new javax.swing.JLabel();
        javax.swing.JLabel lbl_FirstSem10 = new javax.swing.JLabel();
        javax.swing.JLabel lbl_FirstSem11 = new javax.swing.JLabel();
        jlblThirdMonth = new javax.swing.JLabel();
        jlblFourthMonth = new javax.swing.JLabel();
        javax.swing.JLabel lbl_FirstSem12 = new javax.swing.JLabel();
        jlblSixthMonth = new javax.swing.JLabel();
        javax.swing.JLabel lbl_FirstSem13 = new javax.swing.JLabel();
        jlblFifthMonth = new javax.swing.JLabel();
        javax.swing.JLabel lbl_FirstSem14 = new javax.swing.JLabel();
        jlblSeventhMonth = new javax.swing.JLabel();
        javax.swing.JLabel lbl_FirstSem15 = new javax.swing.JLabel();
        jlblEighthMonth = new javax.swing.JLabel();
        javax.swing.JLabel lbl_FirstSem16 = new javax.swing.JLabel();
        jlblNinthMonth = new javax.swing.JLabel();
        javax.swing.JLabel lbl_FirstSem17 = new javax.swing.JLabel();
        jlblTenthMonth = new javax.swing.JLabel();
        javax.swing.JLabel lbl_FirstSem18 = new javax.swing.JLabel();
        jlblMonthlyPenalty = new javax.swing.JLabel();

        setLayout(new java.awt.GridBagLayout());

        panel_control.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Control", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        panel_control.setMinimumSize(new java.awt.Dimension(20, 80));
        panel_control.setPreferredSize(new java.awt.Dimension(20, 80));
        panel_control.setLayout(new java.awt.GridBagLayout());

        jbtnCreateNew.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jbtnCreateNew.setText("Create New Schedule");
        jbtnCreateNew.setActionCommand("create");
        jbtnCreateNew.setMaximumSize(new java.awt.Dimension(170, 40));
        jbtnCreateNew.setMinimumSize(new java.awt.Dimension(170, 40));
        jbtnCreateNew.setPreferredSize(new java.awt.Dimension(170, 40));
        jbtnCreateNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnCreateNewActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(3, 20, 5, 3);
        panel_control.add(jbtnCreateNew, gridBagConstraints);

        jbtnRefresh.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jbtnRefresh.setText("Refresh");
        jbtnRefresh.setMaximumSize(new java.awt.Dimension(159, 40));
        jbtnRefresh.setMinimumSize(new java.awt.Dimension(100, 40));
        jbtnRefresh.setPreferredSize(new java.awt.Dimension(100, 40));
        jbtnRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnRefreshActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 5, 3);
        panel_control.add(jbtnRefresh, gridBagConstraints);

        lbl_Sy.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbl_Sy.setText("Show School Year :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 0);
        panel_control.add(lbl_Sy, gridBagConstraints);

        jcmbSY.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jcmbSY.setMinimumSize(new java.awt.Dimension(150, 25));
        jcmbSY.setPreferredSize(new java.awt.Dimension(150, 25));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 20);
        panel_control.add(jcmbSY, gridBagConstraints);

        jbtnEdit.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jbtnEdit.setText("Edit");
        jbtnEdit.setActionCommand("edit");
        jbtnEdit.setMaximumSize(new java.awt.Dimension(70, 40));
        jbtnEdit.setMinimumSize(new java.awt.Dimension(70, 40));
        jbtnEdit.setPreferredSize(new java.awt.Dimension(70, 40));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        panel_control.add(jbtnEdit, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.5;
        add(panel_control, gridBagConstraints);

        panel_semstral.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Semestral", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        panel_semstral.setMinimumSize(new java.awt.Dimension(20, 80));
        panel_semstral.setPreferredSize(new java.awt.Dimension(20, 80));
        panel_semstral.setLayout(new java.awt.GridBagLayout());

        lbl_FirstSem.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        lbl_FirstSem.setText("First Semester :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 5, 0);
        panel_semstral.add(lbl_FirstSem, gridBagConstraints);

        jlblFirstSem.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jlblFirstSem.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        jlblFirstSem.setMaximumSize(new java.awt.Dimension(150, 25));
        jlblFirstSem.setMinimumSize(new java.awt.Dimension(150, 25));
        jlblFirstSem.setPreferredSize(new java.awt.Dimension(150, 25));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 0);
        panel_semstral.add(jlblFirstSem, gridBagConstraints);

        lbl_FirstSem1.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        lbl_FirstSem1.setText("Second Semester :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(0, 30, 5, 0);
        panel_semstral.add(lbl_FirstSem1, gridBagConstraints);

        jlblSecondSem.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jlblSecondSem.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        jlblSecondSem.setMaximumSize(new java.awt.Dimension(150, 25));
        jlblSecondSem.setMinimumSize(new java.awt.Dimension(150, 25));
        jlblSecondSem.setPreferredSize(new java.awt.Dimension(150, 25));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 0);
        panel_semstral.add(jlblSecondSem, gridBagConstraints);

        lbl_FirstSem2.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        lbl_FirstSem2.setText("Penalty :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(0, 30, 5, 0);
        panel_semstral.add(lbl_FirstSem2, gridBagConstraints);

        jlblSemestralPenalty.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jlblSemestralPenalty.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        jlblSemestralPenalty.setMaximumSize(new java.awt.Dimension(150, 25));
        jlblSemestralPenalty.setMinimumSize(new java.awt.Dimension(150, 25));
        jlblSemestralPenalty.setPreferredSize(new java.awt.Dimension(150, 25));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 0);
        panel_semstral.add(jlblSemestralPenalty, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.5;
        add(panel_semstral, gridBagConstraints);

        panel_quarterly.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Quarterly", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        panel_quarterly.setMinimumSize(new java.awt.Dimension(20, 130));
        panel_quarterly.setPreferredSize(new java.awt.Dimension(20, 130));
        panel_quarterly.setLayout(new java.awt.GridBagLayout());

        lbl_FirstSem3.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        lbl_FirstSem3.setText("First Quarter :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 15, 0);
        panel_quarterly.add(lbl_FirstSem3, gridBagConstraints);

        jlblFirstQuarter.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jlblFirstQuarter.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        jlblFirstQuarter.setMaximumSize(new java.awt.Dimension(150, 25));
        jlblFirstQuarter.setMinimumSize(new java.awt.Dimension(150, 25));
        jlblFirstQuarter.setPreferredSize(new java.awt.Dimension(150, 25));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 15, 0);
        panel_quarterly.add(jlblFirstQuarter, gridBagConstraints);

        lbl_FirstSem4.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        lbl_FirstSem4.setText("Third Quarter :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 30, 15, 0);
        panel_quarterly.add(lbl_FirstSem4, gridBagConstraints);

        jlblThirdQuarter.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jlblThirdQuarter.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        jlblThirdQuarter.setMaximumSize(new java.awt.Dimension(150, 25));
        jlblThirdQuarter.setMinimumSize(new java.awt.Dimension(150, 25));
        jlblThirdQuarter.setPreferredSize(new java.awt.Dimension(150, 25));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 15, 0);
        panel_quarterly.add(jlblThirdQuarter, gridBagConstraints);

        lbl_FirstSem5.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        lbl_FirstSem5.setText("Penalty :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(0, 30, 15, 0);
        panel_quarterly.add(lbl_FirstSem5, gridBagConstraints);

        jlblQuarterlyPenalty.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jlblQuarterlyPenalty.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        jlblQuarterlyPenalty.setMaximumSize(new java.awt.Dimension(150, 25));
        jlblQuarterlyPenalty.setMinimumSize(new java.awt.Dimension(150, 25));
        jlblQuarterlyPenalty.setPreferredSize(new java.awt.Dimension(150, 25));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 15, 0);
        panel_quarterly.add(jlblQuarterlyPenalty, gridBagConstraints);

        lbl_FirstSem6.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        lbl_FirstSem6.setText("Second Quarter :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 5, 0);
        panel_quarterly.add(lbl_FirstSem6, gridBagConstraints);

        jlblSecondQuarter.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jlblSecondQuarter.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        jlblSecondQuarter.setMaximumSize(new java.awt.Dimension(150, 25));
        jlblSecondQuarter.setMinimumSize(new java.awt.Dimension(150, 25));
        jlblSecondQuarter.setPreferredSize(new java.awt.Dimension(150, 25));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 0);
        panel_quarterly.add(jlblSecondQuarter, gridBagConstraints);

        lbl_FirstSem7.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        lbl_FirstSem7.setText("Fourth Quarter :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 30, 5, 0);
        panel_quarterly.add(lbl_FirstSem7, gridBagConstraints);

        jlblFourthQuarter.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jlblFourthQuarter.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        jlblFourthQuarter.setMaximumSize(new java.awt.Dimension(150, 25));
        jlblFourthQuarter.setMinimumSize(new java.awt.Dimension(150, 25));
        jlblFourthQuarter.setPreferredSize(new java.awt.Dimension(150, 25));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 0);
        panel_quarterly.add(jlblFourthQuarter, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.5;
        add(panel_quarterly, gridBagConstraints);

        panel_monthly.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Monthly", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        panel_monthly.setMinimumSize(new java.awt.Dimension(20, 80));
        panel_monthly.setPreferredSize(new java.awt.Dimension(20, 80));
        panel_monthly.setLayout(new java.awt.GridBagLayout());

        lbl_FirstSem8.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        lbl_FirstSem8.setText("1st Month :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 10, 0);
        panel_monthly.add(lbl_FirstSem8, gridBagConstraints);

        lbl_FirstSem9.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        lbl_FirstSem9.setText("2nd Month :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 10, 0);
        panel_monthly.add(lbl_FirstSem9, gridBagConstraints);

        jlblFirstMonth.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jlblFirstMonth.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        jlblFirstMonth.setMaximumSize(new java.awt.Dimension(150, 25));
        jlblFirstMonth.setMinimumSize(new java.awt.Dimension(150, 25));
        jlblFirstMonth.setPreferredSize(new java.awt.Dimension(150, 25));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 10, 0);
        panel_monthly.add(jlblFirstMonth, gridBagConstraints);

        jlblSecondMonth.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jlblSecondMonth.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        jlblSecondMonth.setMaximumSize(new java.awt.Dimension(150, 25));
        jlblSecondMonth.setMinimumSize(new java.awt.Dimension(150, 25));
        jlblSecondMonth.setPreferredSize(new java.awt.Dimension(150, 25));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 10, 0);
        panel_monthly.add(jlblSecondMonth, gridBagConstraints);

        lbl_FirstSem10.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        lbl_FirstSem10.setText("3rd Month :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 10, 0);
        panel_monthly.add(lbl_FirstSem10, gridBagConstraints);

        lbl_FirstSem11.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        lbl_FirstSem11.setText("4th Month :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 10, 0);
        panel_monthly.add(lbl_FirstSem11, gridBagConstraints);

        jlblThirdMonth.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jlblThirdMonth.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        jlblThirdMonth.setMaximumSize(new java.awt.Dimension(150, 25));
        jlblThirdMonth.setMinimumSize(new java.awt.Dimension(150, 25));
        jlblThirdMonth.setPreferredSize(new java.awt.Dimension(150, 25));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 10, 0);
        panel_monthly.add(jlblThirdMonth, gridBagConstraints);

        jlblFourthMonth.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jlblFourthMonth.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        jlblFourthMonth.setMaximumSize(new java.awt.Dimension(150, 25));
        jlblFourthMonth.setMinimumSize(new java.awt.Dimension(150, 25));
        jlblFourthMonth.setPreferredSize(new java.awt.Dimension(150, 25));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 10, 0);
        panel_monthly.add(jlblFourthMonth, gridBagConstraints);

        lbl_FirstSem12.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        lbl_FirstSem12.setText("6th Month :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 50, 10, 0);
        panel_monthly.add(lbl_FirstSem12, gridBagConstraints);

        jlblSixthMonth.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jlblSixthMonth.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        jlblSixthMonth.setMaximumSize(new java.awt.Dimension(150, 25));
        jlblSixthMonth.setMinimumSize(new java.awt.Dimension(150, 25));
        jlblSixthMonth.setPreferredSize(new java.awt.Dimension(150, 25));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 10, 0);
        panel_monthly.add(jlblSixthMonth, gridBagConstraints);

        lbl_FirstSem13.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        lbl_FirstSem13.setText("5th Month :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 10, 0);
        panel_monthly.add(lbl_FirstSem13, gridBagConstraints);

        jlblFifthMonth.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jlblFifthMonth.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        jlblFifthMonth.setMaximumSize(new java.awt.Dimension(150, 25));
        jlblFifthMonth.setMinimumSize(new java.awt.Dimension(150, 25));
        jlblFifthMonth.setPreferredSize(new java.awt.Dimension(150, 25));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 10, 0);
        panel_monthly.add(jlblFifthMonth, gridBagConstraints);

        lbl_FirstSem14.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        lbl_FirstSem14.setText("7th Month :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 50, 10, 0);
        panel_monthly.add(lbl_FirstSem14, gridBagConstraints);

        jlblSeventhMonth.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jlblSeventhMonth.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        jlblSeventhMonth.setMaximumSize(new java.awt.Dimension(150, 25));
        jlblSeventhMonth.setMinimumSize(new java.awt.Dimension(150, 25));
        jlblSeventhMonth.setPreferredSize(new java.awt.Dimension(150, 25));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 10, 0);
        panel_monthly.add(jlblSeventhMonth, gridBagConstraints);

        lbl_FirstSem15.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        lbl_FirstSem15.setText("8th Month :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 50, 10, 0);
        panel_monthly.add(lbl_FirstSem15, gridBagConstraints);

        jlblEighthMonth.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jlblEighthMonth.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        jlblEighthMonth.setMaximumSize(new java.awt.Dimension(150, 25));
        jlblEighthMonth.setMinimumSize(new java.awt.Dimension(150, 25));
        jlblEighthMonth.setPreferredSize(new java.awt.Dimension(150, 25));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 10, 0);
        panel_monthly.add(jlblEighthMonth, gridBagConstraints);

        lbl_FirstSem16.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        lbl_FirstSem16.setText("9th Month :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 50, 10, 0);
        panel_monthly.add(lbl_FirstSem16, gridBagConstraints);

        jlblNinthMonth.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jlblNinthMonth.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        jlblNinthMonth.setMaximumSize(new java.awt.Dimension(150, 25));
        jlblNinthMonth.setMinimumSize(new java.awt.Dimension(150, 25));
        jlblNinthMonth.setPreferredSize(new java.awt.Dimension(150, 25));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 10, 0);
        panel_monthly.add(jlblNinthMonth, gridBagConstraints);

        lbl_FirstSem17.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        lbl_FirstSem17.setText("10th Month :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 50, 10, 0);
        panel_monthly.add(lbl_FirstSem17, gridBagConstraints);

        jlblTenthMonth.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jlblTenthMonth.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        jlblTenthMonth.setMaximumSize(new java.awt.Dimension(150, 25));
        jlblTenthMonth.setMinimumSize(new java.awt.Dimension(150, 25));
        jlblTenthMonth.setPreferredSize(new java.awt.Dimension(150, 25));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 10, 0);
        panel_monthly.add(jlblTenthMonth, gridBagConstraints);

        lbl_FirstSem18.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        lbl_FirstSem18.setText("Penalty :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(0, 50, 10, 0);
        panel_monthly.add(lbl_FirstSem18, gridBagConstraints);

        jlblMonthlyPenalty.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jlblMonthlyPenalty.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        jlblMonthlyPenalty.setMaximumSize(new java.awt.Dimension(150, 25));
        jlblMonthlyPenalty.setMinimumSize(new java.awt.Dimension(150, 25));
        jlblMonthlyPenalty.setPreferredSize(new java.awt.Dimension(150, 25));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 10, 0);
        panel_monthly.add(jlblMonthlyPenalty, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.5;
        add(panel_monthly, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    private void jbtnCreateNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnCreateNewActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jbtnCreateNewActionPerformed

    private void jbtnRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnRefreshActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jbtnRefreshActionPerformed
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jbtnCreateNew;
    private javax.swing.JButton jbtnEdit;
    private javax.swing.JButton jbtnRefresh;
    private javax.swing.JComboBox<String> jcmbSY;
    private javax.swing.JLabel jlblEighthMonth;
    private javax.swing.JLabel jlblFifthMonth;
    private javax.swing.JLabel jlblFirstMonth;
    private javax.swing.JLabel jlblFirstQuarter;
    private javax.swing.JLabel jlblFirstSem;
    private javax.swing.JLabel jlblFourthMonth;
    private javax.swing.JLabel jlblFourthQuarter;
    private javax.swing.JLabel jlblMonthlyPenalty;
    private javax.swing.JLabel jlblNinthMonth;
    private javax.swing.JLabel jlblQuarterlyPenalty;
    private javax.swing.JLabel jlblSecondMonth;
    private javax.swing.JLabel jlblSecondQuarter;
    private javax.swing.JLabel jlblSecondSem;
    private javax.swing.JLabel jlblSemestralPenalty;
    private javax.swing.JLabel jlblSeventhMonth;
    private javax.swing.JLabel jlblSixthMonth;
    private javax.swing.JLabel jlblTenthMonth;
    private javax.swing.JLabel jlblThirdMonth;
    private javax.swing.JLabel jlblThirdQuarter;
    private javax.swing.JPanel panel_control;
    private javax.swing.JPanel panel_monthly;
    private javax.swing.JPanel panel_quarterly;
    private javax.swing.JPanel panel_semstral;
    // End of variables declaration//GEN-END:variables
}
