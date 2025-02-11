package com.app.dao.impl;

import com.app.dao.PaymentDAO;
import com.app.model.Payment;
import com.app.util.DbConnection;
import java.sql.SQLException;
import java.util.ArrayList;

public class PaymentDAOImpl extends DbConnection implements PaymentDAO {

    /* 
        -------------------------------------------------------------------------
                                        CREATE
        -------------------------------------------------------------------------
     */
    @Override
    public boolean addPayment(Payment payment) {
        
        String query = "INSERT INTO tblpayments (payment_amount, "
                + "payment_payment_date, payment_mode, payment_package_id, payment_user_id)"
                + "VALUES (?, ?, ?, ?, ?)";
        boolean isSuccessful = false;

        try {
            connect();
            prep = con.prepareStatement(query);
            prep.setInt(1,payment.getPaymentAmount());
            prep.setDate(2, payment.getPaymentPaymentDate());
            prep.setString(3, payment.getPaymentMode());
            prep.setInt(4, payment.getPaymentPackageId());
            prep.setInt(5, payment.getPaymentUserId());
            prep.executeUpdate();
            isSuccessful = true;
            
        } catch (SQLException e) {
            System.out.println("Error from adding additional payments" + e.getMessage());
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                System.out.println("Failed to close resources: " + ex.getMessage());
            }
        }

        return isSuccessful;

    }


    @Override
    public ArrayList<Payment> fetchPayment() {

        String query = "SELECT tblpayments.payment_id, tblpayments.payment_amount, " 
                + "tblpayments.payment_payment_date, tblpayments.payment_mode, "
                + "tblpayments.payment_package_id, tblpayments.payment_user_id, "
                + "tblusers.user_lastname, tblusers.user_firstname, tblusers.user_middlename, "
                + "tblpackages.package_name "
                + "FROM tblpayments "
                + "JOIN tblusers ON tblpayments.payment_user_id = tblusers.user_id "
                + "JOIN tblpackages ON tblpayments.payment_package_id = tblpackages.package_id";
        

        ArrayList<Payment> payments = new ArrayList<>();

        try {
            connect();
            state = con.createStatement();
            result = state.executeQuery(query);

            while (result.next()) {
                payments.add(
                        new Payment (
                                result.getInt("payment_id"),
                                result.getInt("payment_amount"),
                                result.getDate("payment_payment_date"),
                                result.getString("payment_mode"),
                                result.getInt("payment_package_id"),
                                result.getInt("payment_user_id"), 
                                result.getString("user_lastname") + ", " + result.getString("user_firstname")
                                + " " + result.getString("user_middlename"), 
                                result.getString("package_name")
                ));

               
            }

        } catch (SQLException e) {
            System.out.println("PaymentDAOImpl: fetchAuthor() " + e.getMessage());
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }

        return payments;
    }
    
    
    @Override
    public ArrayList<Payment> fetchUserPaymentHistory(int id) {

        String query = "SELECT tblpayments.payment_id, tblpayments.payment_amount, " 
                + "tblpayments.payment_payment_date, tblpayments.payment_mode, "
                + "tblpayments.payment_package_id, tblpayments.payment_user_id, "
                + "tblusers.user_lastname, tblusers.user_firstname, tblusers.user_middlename, "
                + "tblpackages.package_name "
                + "FROM tblpayments "
                + "JOIN tblusers ON tblpayments.payment_user_id = tblusers.user_id "
                + "JOIN tblpackages ON tblpayments.payment_package_id = tblpackages.package_id "
                + "WHERE payment_user_id = " + id;
        ArrayList<Payment> payments = new ArrayList<>();

        try {
            connect();
            state = con.createStatement();
            result = state.executeQuery(query);

            while (result.next()) {
                payments.add(
                        new Payment (
                                result.getInt("payment_id"),
                                result.getInt("payment_amount"),
                                result.getDate("payment_payment_date"),
                                result.getString("payment_mode"),
                                result.getInt("payment_package_id"),
                                result.getInt("payment_user_id"),
                                result.getString("user_lastname") + ", " + result.getString("user_firstname") + " "
                                + result.getString("user_middlename"),
                                result.getString("package_name") 
                        
                ));
            }

        } catch (SQLException e) {
            System.out.println("PaymentDAOImpl: fetchUserPaymentHistory() " + e.getMessage());
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }

        return payments;
    }
  
}
