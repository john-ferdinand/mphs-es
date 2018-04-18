package component_model_loader;

import daoimpl.PromotionDaoImpl;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import model.schoolyear.SchoolYear;
import model.student.Student;
import model.summerstudent.SummerStudent;

/**
 *
 * @author Jordan
 */
public class PromotionJCompModelLoader {

    private final PromotionDaoImpl promotionDaoImpl;

    public PromotionJCompModelLoader() {
        this.promotionDaoImpl = new PromotionDaoImpl();
    }

    public DefaultTableModel getAllPromotedStudentOf(JTable table, SchoolYear schoolYear) {
        DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
        tableModel.setRowCount(0);
        List<Student> studentsPromoted = promotionDaoImpl.getAllPromotedStudentsOf(schoolYear);
        for (Student s : studentsPromoted) {
            Object[] rowData = {
                s.getStudentId(), s.getStudentNo(),
                s.getRegistration().getLastName() + ", " + s.getRegistration().getFirstName() + ", " + s.getRegistration().getMiddleName(),
                s.getPromotion().getGradeLevelFromPromoted().getLevelNo(), s.getPromotion().getGradeLevelToPromoted().getLevelNo(),
                s.getPromotion().getDatePromoted(),
                s.getPromotion().getPromotedBy().getLastName() + ", " + s.getPromotion().getPromotedBy().getFirstName() + " "
                + s.getPromotion().getPromotedBy().getMiddleName()
            };
            tableModel.addRow(rowData);
        }
        return tableModel;
    }

    
    public DefaultTableModel getAllSummerStudentsOf(JTable table, SchoolYear schoolYear) {
        DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
        tableModel.setRowCount(0);
        List<SummerStudent> summerStudents = promotionDaoImpl.getAllSummerStudentsOf(schoolYear);
        for (SummerStudent ss : summerStudents) {
            Object[] rowData = {
                ss.getStudentId(), ss.getStudentNo(), ss.getRegistration().getLastName() + ", " + ss.getRegistration().getFirstName()
                + " "+ss.getRegistration().getMiddleName(), ss.getSummerGradeLevel().getLevelNo(), ss.getDateRecommendedForSummer(),
                ss.getRecommendedBy().getLastName()+", "+ss.getRecommendedBy().getFirstName()+" "+ss.getRecommendedBy().getLastName(),
                ss.getIsEnrolledInSummer()==true? "Yes":"No",ss.getSectionName(),ss.getIsAlreadyPromoted()==true?"Yes":"No"
            };
            tableModel.addRow(rowData);
        }
        return tableModel;
    }
}
