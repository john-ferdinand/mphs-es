package dao;

import java.util.List;
import model.registration.Registration;

/**
 *
 * @author Acer
 */
public interface IRegistration {
    
    List<Registration> getAllRegistrationInfo();
    List<Registration> getAllRegistrationInfoBy(int schoolYearYearFrom, int gradeLevelNo);
    List<Registration> getAllRegistrationInfoByWildCard(String wildCardChar,int schoolYearFrom);
    List<Registration> getAllRegistrationInfoBySyYearFrom(int aSchoolYearYearFrom);
    List<Registration> getAllRegistrationInfoByAdmissionStatus(int admissionStatus, int schoolYearFrom);
    
    Registration getRegistrationInfoById(int registrationId);
    
    String getRegistrationPaymentTermByStudentId(Integer aStudentId);
    
    boolean addRegistration(Registration registration); //Register student
    boolean updateRegistration(Registration registration); // modify student registration info if necessary
    boolean deleteRegistration(Registration registration); 
    boolean isDuplicateRegistration(Registration registration);
}
