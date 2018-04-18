
package controller.grade;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.schoolyear.SchoolYear;
import model.user.User;
import view.grades.View_Dialog_InputGrade;
import view.grades.View_Panel_GradingSystem;

/**
 *
 * @author Jordan
 */
public class ActionListener_Display_Dialog_InputGrade_JButton implements ActionListener{
    
    private final View_Panel_GradingSystem panelGradingSystem;
    private final User user;
    private final SchoolYear currentSchoolYear;
    
    public ActionListener_Display_Dialog_InputGrade_JButton(User user, SchoolYear currentSchoolYear,View_Panel_GradingSystem panelGradingSystem) {
        this.user = user;
        this.currentSchoolYear = currentSchoolYear;
        this.panelGradingSystem = panelGradingSystem;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        View_Dialog_InputGrade dialogInputGrade = new View_Dialog_InputGrade(null, true, user, currentSchoolYear,panelGradingSystem);
        dialogInputGrade.pack();
        dialogInputGrade.setLocationRelativeTo(null);
        dialogInputGrade.setVisible(true);
    }

    
}
