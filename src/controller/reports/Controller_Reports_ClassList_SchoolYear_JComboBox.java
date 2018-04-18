/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.reports;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import view.reports.Panel_Reports;

/**
 *
 * @author Jordan
 */
public class Controller_Reports_ClassList_SchoolYear_JComboBox implements ItemListener{
        
    private final Panel_Reports view;

    public Controller_Reports_ClassList_SchoolYear_JComboBox(Panel_Reports view) {
        this.view = view;
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if(e.getSource() == view.getJcmbClassListSchoolYear()){
            if(view.getJcmbCorSchoolYear().getSelectedIndex() > -1){
                view.getJcmbClassListGradeLevel().setSelectedIndex(-1);
                view.getJcmbClassListSectionType().setSelectedIndex(-1);
                view.getJcmbClassListSection().setSelectedIndex(-1);
                view.clearClassList();
                view.getJcbClassListFaculty().setSelected(false);
            }
        }
    }
    
}
