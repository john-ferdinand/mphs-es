/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.enrollment;

import component_model_loader.RegistrationJCompModelLoader;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import model.gradelevel.GradeLevel;
import model.schoolyear.SchoolYear;
import view.enrollment.Panel_Enrollment;

/**
 *
 * @author Jordan
 */
public class Controller_ItemListener_RegisteredMasterList_GradeLevel_JComboBox implements ItemListener{
    
    private final Panel_Enrollment view;
    private final RegistrationJCompModelLoader registrationJCompModelLoader;
    private final SchoolYear currentSchoolYear;
    
    public Controller_ItemListener_RegisteredMasterList_GradeLevel_JComboBox(Panel_Enrollment view, SchoolYear currentSchoolYear) {
        this.view = view;
        this.currentSchoolYear = currentSchoolYear;
        this.registrationJCompModelLoader = new RegistrationJCompModelLoader();
    }
    
    @Override
    public void itemStateChanged(ItemEvent e) {
        if(e.getSource() == view.getJcmbRegistrationGradeLevel()){
            JComboBox combo = view.getJcmbRegistrationGradeLevel();
            JTable table = view.getJtblRegisteredMasterList();
            if(combo.getSelectedIndex() > -1){
                GradeLevel gradeLevel = (GradeLevel)combo.getSelectedItem();
                DefaultTableModel tableModel = registrationJCompModelLoader.getAllRegisteredApplicants(currentSchoolYear.getYearFrom(), gradeLevel.getLevelNo(), table);
                table.setModel(tableModel);
            }
        }
    }
    
    
    
}
