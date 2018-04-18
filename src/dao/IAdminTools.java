/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import model.registration.Registration;
import model.testdata.SubjectTestDataModel;

/**
 *
 * @author John Ferdinand Antonio
 */
public interface IAdminTools {
    boolean generateStudent(Registration registration);
    boolean deactivateAllStudents();
    boolean deleteAllPaymentAndTuitionRecord();
    boolean deleteAllStudentRecord();
    boolean addSubjects(SubjectTestDataModel s);
    boolean addFacultyWithSpecialization();
}
