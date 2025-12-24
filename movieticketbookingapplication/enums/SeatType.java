package com.mycompany.movieticketbookingapplication.enums;

public enum SeatType {
    REGULAR(1),
    PREMIUM(1.5),
    VIP(2);
    
    private final double priceMultiplier;
    
    SeatType(double priceMultiplier) {
        this.priceMultiplier = priceMultiplier;
    }
    
    public double getPriceMultiplier() {
        return this.priceMultiplier;
    }
}