package controller.registration;

import daoimpl.CredentialDaoImpl;
import daoimpl.RegistrationDaoImpl;
import daoimpl.SchoolYearDaoImpl;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import model.credential.Credential;
import model.registration.Registration;
import org.jdatepicker.impl.JDatePickerImpl;
import utility.date.DateUtil;
import utility.form.FormValidator;
import view.registration.View_Panel_Registration;

/**
 *
 * @author Jordan
 */
public class Controller_JButton_Register implements ActionListener, FormValidator {

    private final View_Panel_Registration view;
    private final DateUtil dateUtil;
    private final RegistrationDaoImpl registrationDaoImpl;
    private final CredentialDaoImpl credentialDaoImpl;

    public Controller_JButton_Register(View_Panel_Registration view) {
        this.view = view;
        dateUtil = new DateUtil();
        registrationDaoImpl = new RegistrationDaoImpl();
        credentialDaoImpl = new CredentialDaoImpl();
    }

    private void clearForm() {
        List<Component[]> components = new ArrayList<>();
        components.add(view.getJpnlHomeAddress().getComponents());
        components.add(view.getJpnlParentInfo().getComponents());
        components.add(view.getJpnlGuardianInfo().getComponents());
        components.add(view.getJpnlStatusCont().getComponents());
        components.add(view.getJpnlCredentials().getComponents());
        components.add(view.getJpnlStudentInfo().getComponents());
        for (int i = 0; i < components.size(); i++) {
            for (Component c : components.get(i)) {
                if (c instanceof JTextField) {
                    ((JTextField) c).setText("");
                } else if (c instanceof JComboBox) {
                    ((JComboBox) c).setSelectedIndex(-1);
                } else if (c instanceof JCheckBox) {
                    ((JCheckBox) c).setSelected(false);
                } else if (c instanceof JList) {
                    ((DefaultListModel) ((JList) c).getModel()).removeAllElements();
                } else if(c instanceof JDatePickerImpl){
                    ((JDatePickerImpl)c).getJFormattedTextField().setText("");
                }
            }
        }
        removeCredentials();
    }

    private void removeCredentials(){
         view.getJpnlCredentials().removeAll();
         view.getJpnlCredentials().repaint();
         view.getJpnlCredentials().revalidate();
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        int choice = JOptionPane.showConfirmDialog(null, "Save Registration?", "Registration Confirmation", JOptionPane.YES_NO_OPTION);
        if (choice == JOptionPane.YES_OPTION) {
            if (formIsValid()) {
                if (register()) {
                    JOptionPane.showMessageDialog(null, "Successfully registered.");
                    clearForm();
                } else {
                    JOptionPane.showMessageDialog(null, "Failed to register.");
                }
            } else {
                JOptionPane.showMessageDialog(null,"Please fill out all required fields.");
            }
            
        }
    }

    private boolean register() {
        boolean isRegistered = false;
        isRegistered = registrationDaoImpl.addRegistration(getRegistration());
        return isRegistered;
    }

    private Registration getRegistration() {
        Registration registration = new Registration();
        try {
            registration.setStudentType(view.getJcbTransferee().isSelected() == true ? "T" : "N");
            registration.setGradeLevelNo(Integer.parseInt(view.getJcmbGradeLevel().getSelectedItem().toString().trim()));
            registration.setLastName(view.getJtfLastName().getText().trim());
            registration.setFirstName(view.getJtfFirstName().getText().trim());
            registration.setMiddleName(view.getJtfMiddleName().getText().trim());
            registration.setGender(view.getJcmbGender().getSelectedItem().toString().trim());
            registration.setReligion(view.getJtfReligion().getText().trim());
            registration.setNationality(view.getJtfNationality().getText().trim());
            registration.setBirthday(dateUtil.toUtilDate(view.getDpBirthday().getJFormattedTextField().getText().trim()));
            registration.setPlaceOfBirth(view.getJtfPlaceOfBirth().getText().trim());
            registration.setAddressRoomOrHouseNo(view.getJtfRoomNo().getText().trim());
            registration.setAddressStreet(view.getJtfStreet().getText().trim());
            registration.setAddressBrgyOrSubd(view.getJtfBrgySubd().getText().trim());
            registration.setAddressCity(view.getJtfCity().getText().trim());
            registration.setRegion(view.getJtfRegion().getText().trim());
            registration.setSchoolYearYearFrom(SchoolYearDaoImpl.getCurrentSchoolYearFrom());
            registration.setFatherFirstName(view.getJtfFatherFirstName().getText().trim());
            registration.setFatherMiddleName(view.getJtfFatherFirstName().getText().trim());
            registration.setFatherLastName(view.getJtfFatherLastName().getText().trim());
            registration.setFatherOccupation(view.getJtfFatherOccupation().getText().trim());
            registration.setFatherOfficePhoneNo(view.getJtfFatherOfficePhoneNo().getText().trim());
            registration.setFatherMobileNo(view.getJtfFatherMobile().getText().trim());
            registration.setIsFatherContactInCaseEmergency(view.getJcbFatherContactEmergency().isSelected());
            registration.setMotherFirstName(view.getJtfMotherFirstName().getText().trim());
            registration.setMotherMiddleName(view.getJtfMotherMiddleName().getText().trim());
            registration.setMotherLastName(view.getJtfMotherLastName().getText().trim());
            registration.setMotherOccupation(view.getJtfMotherOccupation().getText().trim());
            registration.setMotherOfficePhoneNo(view.getJtfMotherOfficePhoneNo().getText().trim());
            registration.setMotherMobileNo(view.getJtfMotherMobile().getText().trim());
            registration.setIsMotherContactInCaseEmergency(view.getJcbMotherContactEmergency().isSelected());
            registration.setGuardianFirstName(view.getJtfGuardianFirstName().getText().trim());
            registration.setGuardianMiddleName(view.getJtfGuardianMiddleName().getText().trim());
            registration.setGuardianLastName(view.getJtfGuardianLastName().getText().trim());
            registration.setGuardianOccupation(view.getJtfGuardianOccupation().getText().trim());
            registration.setGuardianMobileNo(view.getJtfGuardianMobile().getText().trim());
            registration.setGuardianRelationToStudent(view.getJtfGuardianRelationship().getText().trim());
            registration.setIsGuardianContactInCaseEmergency(view.getJcbGuardianContactEmergency().isSelected());
            registration.setSchoolLastAttended(view.getJtfSchoolLastAttended().getText().trim());
            registration.setSchoolLastAttendedAddress(view.getJtfSchoolLastAttendedAddress().getText().trim());
            
            List<Credential> credentialsSubmitted = new ArrayList<>();
            for(Component c : view.getJpnlCredentials().getComponents()){
                if(c instanceof JCheckBox){
                    JCheckBox cb = (JCheckBox)c;
                    if(cb.isSelected()){
                        String credentialName = cb.getText().trim();
                        int credentialId = credentialDaoImpl.getCredentialIdByName(credentialName);
                        Credential credential = new Credential();
                        credential.setCredentialId(credentialId);
                        credentialsSubmitted.add(credential);
                    }
                }
            }
            
            registration.setCredentials(credentialsSubmitted);
            
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return registration;
    }

    @Override
    public boolean formIsValid() {
        boolean isValid = true;
        List<Component[]> compArr = new ArrayList<>();
        compArr.add(view.getJpnlStudentInfo().getComponents());
        compArr.add(view.getJpnlHomeAddress().getComponents());
        for (int i = 0; i < compArr.size(); i++) {
            for (Component c : compArr.get(i)) {
                if (c instanceof JTextField) {
                    if (((JTextField)c) != view.getJtfFatherOccupation() && ((JTextField)c) != view.getJtfFatherOfficePhoneNo()
                            && ((JTextField)c) != view.getJtfMotherOccupation() && ((JTextField)c) != view.getJtfMotherOfficePhoneNo()) {
                        isValid = isValid && !((JTextField) c).getText().trim().isEmpty();
                    }
                }
                if (c instanceof JComboBox) {
                    isValid = isValid && ((JComboBox) c).getSelectedIndex() > -1;
                }
                if(c instanceof JDatePickerImpl){
                    isValid = isValid && !((JDatePickerImpl) c).getJFormattedTextField().getText().trim().isEmpty();
                }
                
            }
        }
        isValid = isValid && view.getJcmbGradeLevel().getSelectedIndex() > -1;
        return isValid;
    }
}
