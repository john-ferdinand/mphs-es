/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.permission.paymentpermission;

/**
 *
 * @author John Ferdinand Antonio
 */
public class PaymentPermission {
    private boolean hasProcessPayment;
    private boolean hasApplyDiscount;

    public boolean isHasProcessPayment() {
        return hasProcessPayment;
    }

    public void setHasProcessPayment(boolean hasProcessPayment) {
        this.hasProcessPayment = hasProcessPayment;
    }

    public boolean isHasApplyDiscount() {
        return hasApplyDiscount;
    }

    public void setHasApplyDiscount(boolean hasApplyDiscount) {
        this.hasApplyDiscount = hasApplyDiscount;
    }
    
    
}
