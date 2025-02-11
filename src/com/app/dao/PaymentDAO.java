
package com.app.dao;

import com.app.model.Payment;
import java.util.ArrayList;


public interface PaymentDAO {
    
    //Create
    public boolean addPayment(Payment payment);
    
    //Read  
    public ArrayList<Payment> fetchPayment();
    public ArrayList<Payment> fetchUserPaymentHistory(int id);
}
