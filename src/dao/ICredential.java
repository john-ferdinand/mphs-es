
package dao;

import java.util.List;
import model.credential.Credential;
import model.gradelevel.GradeLevel;

/**
 *
 * @author Jordan
 */
public interface ICredential {
    boolean addCredential(Credential aCredential);
    int getCredentialIdByName(String credentialName);
    List<Credential> getAllCredentials();
    List<Credential> getCredentialsBy(GradeLevel gradeLevel);
}
