/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.subjectcategory;

import component_model_loader.SubjectCategoryJCompModelLoader;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Jordan
 */
public class DisplaySubjectCategoryAssignedSubjectsOnMouseClick implements MouseListener{
    
    private JTable jtblSubjectCategoryMasterList;
    private JTable jtblAssignedSubjects;
    private SubjectCategoryJCompModelLoader subjectCategoryJCompModelLoader;
    
    public DisplaySubjectCategoryAssignedSubjectsOnMouseClick(JTable jtblSubjectCategoryMasterList,JTable jtblAssignedSubjects){
        this.jtblSubjectCategoryMasterList = jtblSubjectCategoryMasterList;
        this.jtblAssignedSubjects = jtblAssignedSubjects;
        subjectCategoryJCompModelLoader = new SubjectCategoryJCompModelLoader();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
//        if(jtblSubjectCategoryMasterList.getSelectedRow() > -1){
            DefaultTableModel tableModel = 
                    subjectCategoryJCompModelLoader.getSubjectCategoryAssignedSubjectsById(jtblSubjectCategoryMasterList,jtblAssignedSubjects);
            jtblAssignedSubjects.setModel(tableModel);
//        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
    
    
    
}
