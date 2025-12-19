package com.mycompany.movieticketbookingapplication.models;

import com.mycompany.movieticketbookingapplication.enums.SeatType;

public class Seat {
    private final String SeatId;
    private final String row;
    private final int seatNumber;
    private SeatType type;
    
    public Seat(String seatId, String row, int seatNumber, SeatType type) {
        this.SeatId = seatId;
        this.row = row;
        this.seatNumber = seatNumber;
        this.type = type;
    }
    
    public String getSeatId() {
        return this.SeatId;
    }

    public String getRow() {
        return this.row;
    }
    
    public int getSeatNumber() {
        return this.seatNumber;
    }
    
   public SeatType getSeatType() {
       return this.type;
   }
   
   public void changeSeatType(SeatType type) {
       this.type = type;
   }
}