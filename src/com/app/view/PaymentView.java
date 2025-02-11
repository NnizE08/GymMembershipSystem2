package com.app.view;

import com.app.model.Payment;
import com.app.util.Helper;
import com.app.util.TextHelper;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;

public class PaymentView {
    private Scanner scanner = new Scanner(System.in);
    TextHelper th = new TextHelper();
    
    /* 
        -------------------------------------------------------------------------
                                        CREATE
        -------------------------------------------------------------------------
    */
    
    public Payment createPayments(){
        Scanner sc = new Scanner(System.in); 
        String paymentMode;
        int paymentId, paymentAmount, paymentPackageId, paymentUserId;
        java.sql.Date payment_payment_date ; 
        
        
        System.out.println(th.DIVIDER);
        System.out.println(th.COLOR_BLUE + "\t\t\tPROCESS PAYMENT" + th.COLOR_RESET);
        System.out.println(th.DIVIDER);
        
        System.out.print("Payment amount: ");
        paymentAmount = sc.nextInt();
       
        Calendar cal = Calendar.getInstance();
        payment_payment_date = new java.sql.Date(cal.getTime().getTime());
        sc.nextLine();
        System.out.print("Payment mode : ");
        paymentMode = sc.nextLine(); 
        System.out.print("Package Id : ");
        paymentPackageId = sc.nextInt(); 
        System.out.print("User Id: ");
        paymentUserId = sc.nextInt();
        
        return new Payment (paymentAmount, payment_payment_date , paymentMode, paymentPackageId, paymentUserId); 
       
    }
        
    
    /* 
        -------------------------------------------------------------------------
                                        READ
        -------------------------------------------------------------------------
     */
    
    
    public void displayPayments(ArrayList<Payment> payments) {
        System.out.println("\n--------------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("%-4s| %-10s| %-10s| %-10s| %-40s| %-40s| %n", "ID", "AMOUNT", "PAYMENT DATE", "PAYMENT_MODE", "PACKAGE", "MEMBER");
        System.out.println("--------------------------------------------------------------------------------------------------------------------------------");

        payments.forEach((payment) -> {
            System.out.printf("%-4s|%-12s| %-10s | %-11s | %-40s| %-40s|%n", payment.getPaymentId(), payment.getPaymentAmount(),
                    payment.getPaymentPaymentDate(), payment.getPaymentMode(), payment.getPackageName(), payment.getMemberName());
        });
        System.out.println("--------------------------------------------------------------------------------------------------------------------------------");
    }
    
    public void displayUserPayments(List<Payment> payments) {
       // Check if the list is empty
        if (payments.isEmpty()) {
            System.out.println("\n╔════════════════════════════════════════════════════════════════════════════╗");
            System.out.println("║                          No record payment history found.                   ║");
            System.out.println("╚════════════════════════════════════════════════════════════════════════════╝");
        } else {
            // Print the table headers
            System.out.println("\n╔════════╦════════════╦══════════════╦═══════════════╦════════════════════╦══════════════════════╗");
            System.out.printf("║ %-6s ║ %-10s ║ %-12s ║ %-13s ║ %-18s ║ %-20s ║%n", 
                              "ID", "AMOUNT", "PAYMENT DATE", "PAYMENT MODE", "PACKAGE", "USER");
            System.out.println("╠════════╬════════════╬══════════════╬═══════════════╬════════════════════╬══════════════════════╣");

            // Print the payment records
            payments.forEach((payment) -> {
                System.out.printf("║ %-6s ║ %-10s ║ %-12s ║ %-13s ║ %-18s ║ %-15s ║%n", 
                                  payment.getPaymentId(), payment.getPaymentAmount(),
                                  payment.getPaymentPaymentDate(), payment.getPaymentMode(), 
                                  payment.getPackageName(), payment.getMemberName());
            });

            // Close the table
            System.out.println("╚════════╩════════════╩══════════════╩═══════════════╩════════════════════╩══════════════════════╝");
        }
    }
    
    public int getUserId() {
        
        int userId = -1;
        boolean isValidUser = false; // Flag to track validity

        try{
            while (!isValidUser) {
                System.out.println(th.DIVIDER);
                System.out.println(th.COLOR_BLUE + "\t\t\tVIEW PAYMENT HISTORY" + th.COLOR_RESET);
                System.out.println(th.DIVIDER);

                System.out.print("Enter User ID: ");
                String input = scanner.nextLine();

                if (Helper.isNumeric(input)) { // Check if input is a valid number
                    userId = Integer.parseInt(input);
                    isValidUser = true;
                } else {
                    System.out.println("Invalid input. Please enter a valid numeric User ID.");
                }
            }
        }catch(Exception e){
            System.out.println("A NullPointerException occurred: " + e.getMessage());
            StackTraceElement[] stackTrace = e.getStackTrace();
            for (StackTraceElement element : stackTrace) {
                // Print method name and line number
                System.out.println("Exception in method: " + element.getMethodName() + " at line: " + element.getLineNumber());
            }
        }
       

        return userId;
    }
}
