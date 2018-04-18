
package dao;

import java.util.List;
import model.classtype.ClassType;

/**
 *
 * @author Jordan
 */
public interface IClassType {

    List<ClassType> getAllClassTypesByStatus(boolean isActive);
    ClassType getClassTypeById(int classTypeId);
}
