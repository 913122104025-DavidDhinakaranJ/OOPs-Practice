package com.mycompany.movieticketbookingapplication.models;

import com.mycompany.movieticketbookingapplication.enums.PaymentStatus;
import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicLong;

public class Payment {
    private static final AtomicLong idCounter = new AtomicLong(1000);
    
    private final String paymentId;
    private double amount;
    private PaymentStatus status;
    private LocalDateTime paymentDate;

    public Payment() {
        this.paymentId = "PID" + idCounter.incrementAndGet();
        this.amount = 0.0;
        this.status = PaymentStatus.INITIATED;
    }
    
    public String getPaymentId() {
        return paymentId;
    }
    
    public void pay(double amount) {
        this.amount = amount;
    }
    
    public void updateStatusToSuccess() {
        this.status = PaymentStatus.SUCCESS;
        paymentDate = LocalDateTime.now();
    }
    
    public void updateStatusToFailed() {
        this.status = PaymentStatus.FAILED;
    }
    
    public void updateStatusToRefunded() {
        this.status = PaymentStatus.REFUNDED;
    }

    public double getAmount() {
        return amount;
    }

    public PaymentStatus getStatus() {
        return status;
    }

    public LocalDateTime getPaymentDate() {
        return paymentDate;
    }
}