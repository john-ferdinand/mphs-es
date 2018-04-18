package controller.registration;

import daoimpl.CredentialDaoImpl;
import daoimpl.GradeLevelDaoImpl;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import model.credential.Credential;
import model.gradelevel.GradeLevel;
import org.jdatepicker.impl.JDatePickerImpl;
import view.registration.View_Panel_Registration;

/**
 *
 * @author Jordan
 */
public class Controller_JComboBox_GradeLevel implements ItemListener {

    private final View_Panel_Registration view;
    private final GradeLevelDaoImpl gradeLevelDaoImpl;
    private final CredentialDaoImpl credentialDaoImpl;

    public Controller_JComboBox_GradeLevel(View_Panel_Registration view) {
        this.view = view;
        gradeLevelDaoImpl = new GradeLevelDaoImpl();
        credentialDaoImpl = new CredentialDaoImpl();
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getSource() == view.getJcmbGradeLevel()) {
            JComboBox combo = view.getJcmbGradeLevel();
            if (view.getJcmbGradeLevel().getSelectedIndex() > -1) {
                if (combo.getSelectedItem() instanceof Integer) {
                    int gradeLevelNo = Integer.parseInt(combo.getSelectedItem().toString());
                    int gradeLevelId = gradeLevelDaoImpl.getId(gradeLevelNo);
                    GradeLevel gradeLevel = gradeLevelDaoImpl.getById(gradeLevelId);
                    loadCredentialCheckBoxBy(gradeLevel);
                    validateAgeBy(gradeLevel);
                    validateIfTransfereeBy(gradeLevelNo);
                }
            }
        }
    }

    private void loadCredentialCheckBoxBy(GradeLevel gradeLevel) {
        List<Credential> credentialList = credentialDaoImpl.getCredentialsBy(gradeLevel);
        GridBagConstraints gbc = new GridBagConstraints(0, 0, 1, 1, 0, 0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(3, 3, 3, 3), 0, 0);
        for (Credential c : credentialList) {
            JCheckBox checkBox = new JCheckBox(c.getCredentialName().trim());
            checkBox.setText(c.getCredentialName());
            view.getJpnlCredentials().add(checkBox, gbc);
            view.getJpnlCredentials().revalidate();
            gbc.gridy++;
        }
    }

    private void validateIfTransfereeBy(int gradeLevelNo) {
        if (gradeLevelNo == 0) {
            view.getJcbTransferee().setEnabled(false);
        } else {
            view.getJcbTransferee().setEnabled(true);
        }
    }
    
    private void validateAgeBy(GradeLevel gradeLevel) {
        if (!view.getDpBirthday().getJFormattedTextField().getText().trim().isEmpty()) {
            int levelNo = gradeLevel.getLevelNo();
            int age = getAgeBy(view.getDpBirthday());
            if (!(age >= gradeLevel.getAgeFrom() && age <= gradeLevel.getAgeTo())) {
//                view.getJtaWarnings().append("Please check birthdate input.");
//                view.getJtaWarnings().append("\nRecommended age for "+(levelNo == 0? "Kindergarten is " : "Grade "+levelNo+"is "));
//                view.getJtaWarnings().append(""+gradeLevel.getAgeFrom()+ " to "+gradeLevel.getAgeTo());
            } else {
                view.getJtaWarnings().setText("");
            }
        }
    }

    private int getAgeBy(JDatePickerImpl birthday) {
        Date selectedDate = (Date) birthday.getModel().getValue();
        LocalDate birthDate = selectedDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate now = LocalDate.now();
        int age = Period.between(birthDate, now).getYears();
        return age;
    }
}
