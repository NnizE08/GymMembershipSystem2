
package com.app.controller;

import com.app.dao.PaymentDAO;
import com.app.model.Payment;
import com.app.view.PaymentView;
import java.util.ArrayList;
import java.util.List;


public class PaymentController {
    
    private PaymentDAO paymentDAO;
    private PaymentView paymentView;

    public PaymentController(PaymentDAO paymentDAO, PaymentView paymentView) {
        this.paymentDAO = paymentDAO;
        this.paymentView = paymentView;
    }
    
    /* 
        -------------------------------------------------------------------------
                                        CREATE
        -------------------------------------------------------------------------
    */
    
     public void addPayment(){
        
        Payment newPayment = paymentView.createPayments(); 
     
        if(paymentDAO.addPayment(newPayment)){
            System.out.println("Registration Successful!");
        }
        else{
            System.out.println("Registration Failed!");
        }
    }
     
      /* 
        -------------------------------------------------------------------------
                                        READ
        -------------------------------------------------------------------------
    */
    
    public void displayPayment(){
        ArrayList<Payment> payments = paymentDAO.fetchPayment(); 
        paymentView.displayPayments(payments); 
    }
    
    public void displayUserPayment(int id){       
        List<Payment> paymentList = paymentDAO.fetchUserPaymentHistory(id);              
        paymentView.displayUserPayments(paymentList);
    } 
}
