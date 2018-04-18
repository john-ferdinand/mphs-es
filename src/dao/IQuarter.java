
package dao;

import java.util.List;
import model.schoolyear.SchoolYear;
import model.quarter.Quarter;

/**
 *
 * @author Acer
 */
public interface IQuarter {
    Quarter getQuarterBy(int quarterNo,SchoolYear schoolYear);
    Quarter getCurrentQuarterOf(SchoolYear schoolYear);
}
