package dao;

import java.util.List;
import model.schoolyear.SchoolYear;

/**
 *
 * @author Acer
 */
public interface ISchoolYear {
    List<SchoolYear> getAllSchoolYearInfo();
    List<SchoolYear> getAllSchoolYear();
    List<SchoolYear> getAllYearFrom();
    List<SchoolYear> getAllYearTo();
    SchoolYear getSchoolYearById(int schoolYearId);
    
    int getId(int schoolYearFrom);
    int getCurrentSchoolYearId();
    
    boolean add(SchoolYear schoolyear);
    boolean update(SchoolYear schoolYear);
    boolean isCurrent(SchoolYear schoolyear);
    boolean open(SchoolYear schoolYear);
    boolean close(SchoolYear schoolYear);
}
