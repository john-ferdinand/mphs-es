
package controller.faculty;

import component_model_loader.FacultyJCompModelLoader;
import component_model_loader.SubjectCategoryJCompModelLoader;
import daoimpl.FacultyDaoImpl;
import daoimpl.SchoolYearDaoImpl;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.faculty.Faculty;
import model.subjectcategory.SubjectCategory;
import view.faculty.Dialog_FacultyAdd;
import view.faculty.Dialog_FacultyEdit;
import view.faculty.Panel_Faculty;

/**
 *
 * @author franc
 */
public class Panel_FacultyListener implements ActionListener, MouseListener, KeyListener{
    
    private Panel_Faculty panelFaculty;
    private Dialog_FacultyEdit dialogFacultyEdit;
    
    private Faculty faculty = new Faculty();
    private SubjectCategory subjectCategory = new SubjectCategory();
    
    private FacultyDaoImpl fdi = new FacultyDaoImpl();
    
    private FacultyJCompModelLoader tblFacultyLoader = new FacultyJCompModelLoader();
    private SubjectCategoryJCompModelLoader subjectCategoryJCompModelLoader = new SubjectCategoryJCompModelLoader();
    
    public Panel_FacultyListener(Panel_Faculty panelFaculty){
//        JOptionPane.showMessageDialog(null,"Test");
        this.panelFaculty = panelFaculty;
    }
    
    public Panel_FacultyListener(Dialog_FacultyEdit dialogFacultyEdit){
        this.dialogFacultyEdit = dialogFacultyEdit;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(panelFaculty.getBtnNewFaculty())) {
            new Dialog_FacultyAdd(null, true);
        } else if (e.getSource().equals(panelFaculty.getBtnSearch())) {
            faculty.setLastName(panelFaculty.getTfSearch().getText());

            panelFaculty.getJtblFacultyMasterList().setModel(tblFacultyLoader.facultyInfo(fdi.getFacultyByName(faculty)));
            panelFaculty.getJtblFacultyMasterList().getColumnModel().getColumn(0).setMinWidth(0);
            panelFaculty.getJtblFacultyMasterList().getColumnModel().getColumn(0).setMaxWidth(0);
        } else if (e.getSource().equals(panelFaculty.getBtnEditFaculty())) {
            if (panelFaculty.getJtblFacultyMasterList().getSelectedRow() >= 0) {
                faculty.setFacultyID((int) panelFaculty.getJtblFacultyMasterList().getValueAt(panelFaculty.getJtblFacultyMasterList().getSelectedRow(), 0));
                dialogFacultyEdit = new Dialog_FacultyEdit(null, true, faculty, subjectCategory);
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getClickCount() == 1){
            if(e.getSource().equals(panelFaculty.getJtblFacultyMasterList())){
                 if(((DefaultTableModel)panelFaculty.getJtblSpecializationSubjects().getModel()).getRowCount() > 0){
                   ((DefaultTableModel)panelFaculty.getJtblSpecializationSubjects().getModel()).setRowCount(0); 
                }
                faculty.setFacultyID((int) panelFaculty.getJtblFacultyMasterList().getValueAt(panelFaculty.getJtblFacultyMasterList().getSelectedRow(), 0));
                panelFaculty.getJtblSpecialization().setModel(tblFacultyLoader.loadFacultySpecialization(fdi.loadFacultySpecialization(faculty, subjectCategory),"select"));
                panelFaculty.getJtblSpecialization().getColumnModel().getColumn(0).setMinWidth(0);
                panelFaculty.getJtblSpecialization().getColumnModel().getColumn(0).setMaxWidth(1);
            }
            else if(e.getSource().equals(panelFaculty.getJtblSpecialization())){
                DefaultTableModel tableModel = 
                    subjectCategoryJCompModelLoader.getSubjectCategoryAssignedSubjectsById(panelFaculty.getJtblSpecialization(),panelFaculty.getJtblSpecializationSubjects());
                panelFaculty.getJtblSpecializationSubjects().setModel(tableModel);
            }
            else if(e.getSource().equals(panelFaculty.getTfSearch())){
                panelFaculty.getTfSearch().setText("");
            }
        }
        else if(e.getClickCount() == 2){
            if(e.getSource().equals(panelFaculty.getTfSearch())){
                if(!panelFaculty.getTfSearch().equals("Search here")){
                    panelFaculty.getTfSearch().setText("");
                }
            }
        }
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

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_DOWN) {
            if (e.getSource().equals(panelFaculty.getJtblFacultyMasterList())) {
                if(((DefaultTableModel)panelFaculty.getJtblSpecializationSubjects().getModel()).getRowCount() > 0){
                   ((DefaultTableModel)panelFaculty.getJtblSpecializationSubjects().getModel()).setRowCount(0); 
                }
                faculty.setFacultyID((int) panelFaculty.getJtblFacultyMasterList().getValueAt(panelFaculty.getJtblFacultyMasterList().getSelectedRow(), 0));
                panelFaculty.getJtblSpecialization().setModel(tblFacultyLoader.loadFacultySpecialization(fdi.loadFacultySpecialization(faculty, subjectCategory), "select"));
                panelFaculty.getJtblSpecialization().getColumnModel().getColumn(0).setMinWidth(0);
                panelFaculty.getJtblSpecialization().getColumnModel().getColumn(0).setMaxWidth(1);
            }
        }

    }

    
    
    
}
