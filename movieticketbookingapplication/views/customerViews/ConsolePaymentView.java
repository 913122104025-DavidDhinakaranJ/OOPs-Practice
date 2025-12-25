package com.mycompany.movieticketbookingapplication.views.customerViews;

import com.mycompany.movieticketbookingapplication.models.Payment;
import com.mycompany.movieticketbookingapplication.utils.ConsoleInputUtil;

public class ConsolePaymentView {
    private final ConsoleInputUtil inputReader;
    private final Payment payment;
        
    public ConsolePaymentView(Payment payment) {
        inputReader = new ConsoleInputUtil();
        this.payment = payment;
    }
    
    public boolean handlePayment(double amount) {
        displayAmountToPay(amount);
        return processpayment(amount);
    }
    
    private void displayAmountToPay(double amount) {
        System.out.println("Amount to pay: " + amount);
    }

    private boolean processpayment(double amount) {
        if(!inputReader.readBoolean("Do you want to continue to payment?")) {
            System.out.println("Payment Cancelled.");
            payment.updateStatusToFailed();
            return false;
        }
        
        System.out.println("Payment Successful");
        payment.updateStatusToSuccess();
        return true;
    }
}