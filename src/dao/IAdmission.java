
package dao;

import model.admission.Admission;

/**
 *
 * @author Acer
 */
public interface IAdmission {
    boolean completeAdmission(Admission admission);
    boolean isAdmissionCompleteFor(int registrationId);
    Admission getAdmissionByStudentId(int studentId);
}
