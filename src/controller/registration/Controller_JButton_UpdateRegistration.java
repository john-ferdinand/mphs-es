package controller.registration;

import daoimpl.AdmissionDaoImpl;
import daoimpl.CredentialDaoImpl;
import daoimpl.FeeDaoImpl;
import daoimpl.GradeLevelDaoImpl;
import daoimpl.RegistrationDaoImpl;
import daoimpl.SchoolYearDaoImpl;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import model.admission.Admission;
import model.credential.Credential;
import model.fee.Fee;
import model.registration.Registration;
import utility.date.DateUtil;
import view.enrollment.Panel_Enrollment;
import view.registration.View_Panel_Registration;

/**
 *
 * @author Jordan
 */
public class Controller_JButton_UpdateRegistration implements ActionListener {

    private Panel_Enrollment panelEnrollment;
    private View_Panel_Registration view;

    private final DateUtil dateUtil;
    private final RegistrationDaoImpl registrationDaoImpl;
    private final FeeDaoImpl feeDaoImpl;
    private final GradeLevelDaoImpl gradeLevelDaoImpl;
    private final AdmissionDaoImpl admissionDaoImpl;
    private final CredentialDaoImpl credentialDaoImpl;

    private final int registrationId;

    public Controller_JButton_UpdateRegistration(Panel_Enrollment panelEnrollment,View_Panel_Registration view, int registrationId) {
        this.panelEnrollment = panelEnrollment;
        this.registrationId = registrationId;
        this.view = view;
        dateUtil = new DateUtil();
        registrationDaoImpl = new RegistrationDaoImpl();
        feeDaoImpl = new FeeDaoImpl();
        gradeLevelDaoImpl = new GradeLevelDaoImpl();
        admissionDaoImpl = new AdmissionDaoImpl();
        credentialDaoImpl = new CredentialDaoImpl();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int choice = JOptionPane.showConfirmDialog(null, "Save Edit?", "Save Edit Confirmation", JOptionPane.YES_NO_OPTION);
        if (choice == JOptionPane.YES_OPTION) {
            if (update()) {
                JOptionPane.showMessageDialog(null, "Successfully saved changes.");
                panelEnrollment.loadRegistrationRecord();
            } else {
                JOptionPane.showMessageDialog(null, "Failed to save changes.");
            }
        }
    }

    private boolean update() {
        boolean isUpdated = false;
        Registration registration = getRegistration();
        if (view.getJcmbAdmissionStatus().getSelectedItem().toString().trim().equalsIgnoreCase("Pending")) {
            isUpdated = updateRegistrationInfo(registration);
        } else if (view.getJcmbAdmissionStatus().getSelectedItem().toString().trim().equalsIgnoreCase("Complete")) {
            if (admissionDaoImpl.isAdmissionCompleteFor(registrationId)) {
                isUpdated = updateRegistrationInfo(registration);
            } else {
                isUpdated = (updateRegistrationInfo(registration)) && (completeAdmission(registration));
                if(isUpdated){
                    JOptionPane.showMessageDialog(null,"Successfully completed admission process.");
                }
            }

        }
        return isUpdated;
    }

    private boolean updateRegistrationInfo(Registration registration) {
        boolean isUpdated = false;
        isUpdated = registrationDaoImpl.updateRegistration(registration);
        return isUpdated;
    }

    private boolean completeAdmission(Registration registration) {
        boolean isCompleted = false;
        List<Fee> feeList = new ArrayList<>();
        int gradeLevelId = gradeLevelDaoImpl.getId(registration.getGradeLevelNo());
        feeList = feeDaoImpl.getFeesByGradeLevelId(gradeLevelId);

        Admission admission = new Admission();
        admission.setRegistration(registration);
        admission.setFeeList(feeList);
        isCompleted = admissionDaoImpl.completeAdmission(admission);
        return isCompleted;
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
            registration.setFatherMiddleName(view.getJtfFatherMiddleName().getText().trim());
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
            List<Credential> credentials = new ArrayList<>();
            for (Component component : view.getJpnlCredentials().getComponents()) {
                if (component instanceof JCheckBox) {
                    JCheckBox checkBox = (JCheckBox) component;
                    if (checkBox.isSelected()) {
                        String credentialName = checkBox.getText().trim();
                        int credentialId = credentialDaoImpl.getCredentialIdByName(credentialName);
                        Credential credential = new Credential();
                        credential.setCredentialId(credentialId);
                        credentials.add(credential);
                    }
                }
            }
            registration.setIsRegistrationActive(view.getJcmbRegistrationStatus().getSelectedItem().toString().trim().equalsIgnoreCase("Active") ? true : false);
            registration.setCredentials(credentials);
            registration.setRegistrationId(registrationId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return registration;
    }
}
