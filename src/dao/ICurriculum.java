package dao;

import java.util.List;
import model.curriculum.Curriculum;
import model.subject.Subject;

public interface ICurriculum {

    boolean addCurriculum(Curriculum curriculum);

    boolean updateCurriculum(Curriculum curriculum);

    int getCurriculumIdByName(String curriculumName);

    Curriculum getCurriculumById(int curriculumId);

    List<Curriculum> getAllCurriculum();
    
    List<Curriculum> getCurriculumByWildCard(String wildCardChar);

    List<Subject> getCurriculumSubjectsById(int curriculumId);
}
