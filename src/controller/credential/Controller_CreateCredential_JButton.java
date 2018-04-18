/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.credential;

import daoimpl.CredentialDaoImpl;
import daoimpl.GradeLevelDaoImpl;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import model.credential.Credential;
import model.gradelevel.GradeLevel;
import model.schoolyear.SchoolYear;
import model.user.User;
import utility.form.FormValidator;
import utility.string.StringUtil;
import view.credential.Dialog_CredentialCRUD;

/**
 *
 * @author Jordan
 */
public class Controller_CreateCredential_JButton implements ActionListener,FormValidator{
    
    private final Dialog_CredentialCRUD view;
    private final SchoolYear currentSchoolYear;
    private final User user;
    private final CredentialDaoImpl credentialDaoImpl;
    private final GradeLevelDaoImpl gradeLevelDaoImpl;

    public Controller_CreateCredential_JButton(Dialog_CredentialCRUD view, SchoolYear currentSchoolYear, User user) {
        this.view = view;
        this.currentSchoolYear = currentSchoolYear;
        this.user = user;
        credentialDaoImpl = new CredentialDaoImpl();
        gradeLevelDaoImpl = new GradeLevelDaoImpl();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(formIsValid()){
            int choice = JOptionPane.showConfirmDialog(null, "Create Credential?", "Create Confirmation", JOptionPane.YES_NO_OPTION);
            if(choice == JOptionPane.YES_OPTION){
                if(createCredential()){
                    JOptionPane.showMessageDialog(null, "Successfully created.");
                    view.dispose();
                }
            }
        }else{
            JOptionPane.showMessageDialog(null,"Please check form for empty fields.");
        }
    }
    
    
    private boolean createCredential(){
        boolean isSuccessful = false;
        Credential credential = new Credential();
        credential.setCredentialName(view.getJtfCredentialName().getText().trim());
        credential.setCreatedBy(user);
        credential.setGradeLevelsAssigned(gradeLevelsAssigned());
        credential.setCredentialDescription(view.getJtaDescription().getText().trim());
        credential.setYearCreated(currentSchoolYear);
        
        isSuccessful = credentialDaoImpl.addCredential(credential);
        return isSuccessful;
    }
    
    private List<GradeLevel> gradeLevelsAssigned(){
        Component[] compArr = view.getJpnlGradeLevelAssignment().getComponents();
        List<GradeLevel> gradeLevels = new ArrayList<>();
        for(Component c : compArr){
            if(c instanceof JCheckBox){
                JCheckBox cb = (JCheckBox)c;
                if(!cb.getText().trim().equalsIgnoreCase("Kindergarten") && cb.isSelected()){
                    String checkBoxText = ((JCheckBox)c).getText().trim();
                    int gradeLevelNo = Integer.parseInt(StringUtil.removeWhiteSpaces(StringUtil.removeAllNonNumeric(checkBoxText)));
                    GradeLevel gradeLevel = new GradeLevel();
                    gradeLevel.setLevelNo(gradeLevelNo);
                    gradeLevel.setGradeLevelID(gradeLevelDaoImpl.getId(gradeLevelNo));
                    
                    gradeLevels.add(gradeLevel);
                }else if(cb.isSelected() && cb.getText().trim().equalsIgnoreCase("Kindergarten")){
                    GradeLevel gradeLevel = new GradeLevel();
                    gradeLevel.setLevelNo(0);
                    gradeLevel.setGradeLevelID(gradeLevelDaoImpl.getId(0));
                    
                    gradeLevels.add(gradeLevel);
                }
            }
        }
        return gradeLevels;
    }

    @Override
    public boolean formIsValid() {
        boolean isValid = true;
        if(view.getJtfCredentialName().getText().trim().isEmpty()){
            isValid = false;
        }else if(view.getJtaDescription().getText().trim().isEmpty()){
            isValid = false;
        }else if(gradeLevelsAssigned().size() == 0){
            isValid = false;
        }
        return isValid;
    }
        
    
    
}
