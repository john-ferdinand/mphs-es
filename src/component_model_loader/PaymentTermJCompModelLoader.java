package component_model_loader;

import daoimpl.PaymentTermDaoImpl;
import daoimpl.SchoolYearDaoImpl;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import model.paymentterm.PaymentTerm;
import model.schoolyear.SchoolYear;

/**
 *
 * @author John Ferdinand Antonio
 */
public class PaymentTermJCompModelLoader {
    private final PaymentTermDaoImpl paymentTermDaoImpl;
    private final SchoolYearDaoImpl schoolYearDaoImpl;
    
    public PaymentTermJCompModelLoader(){
        paymentTermDaoImpl = new PaymentTermDaoImpl();
        schoolYearDaoImpl = new SchoolYearDaoImpl();
    }
    
    public DefaultComboBoxModel getPaymentTermNames(){
        DefaultComboBoxModel comboBoxModel = new DefaultComboBoxModel();
        List<PaymentTerm> paymentTermList = paymentTermDaoImpl.getAll();
        for(PaymentTerm pt : paymentTermList){
            comboBoxModel.addElement(pt.getPaymentTermName());
        }
        comboBoxModel.setSelectedItem(null);
        return comboBoxModel;
    }
}
