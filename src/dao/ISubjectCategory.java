
package dao;

import java.util.List;
import model.subject.Subject;
import model.subjectcategory.SubjectCategory;

/**
 *
 * @author Jordan
 */
public interface ISubjectCategory {
    boolean subjectCategoryNameExists(String subjectCategoryName);
    boolean addSubjectCategory(SubjectCategory subjectCategory);
    boolean updateSubjectCategory(SubjectCategory subjectCategory);
    List<Subject> getSubjectCategoryAssignedSubjectsById(int subjectCategoryId);
    List<SubjectCategory> getAllSubjectCategoryInfo();
    List<SubjectCategory> getAllSubjectCategoryInfoByWildCard(String wildCardChar);
    SubjectCategory getSubjectCategoryInfoById(int subjectCategoryId);
}
