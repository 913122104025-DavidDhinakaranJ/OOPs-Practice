package com.mycompany.movieticketbookingapplication.models;

import com.mycompany.movieticketbookingapplication.enums.SeatStatus;

public class ShowSeat {
    private final String showSeatId;
    private final Seat seat;
    private final Show show;
    private SeatStatus status;
    private double price;
    
    public ShowSeat(String showSeatId, Seat seat, Show show) {
        this.showSeatId = showSeatId;
        this.seat = seat;
        this.show = show;
        this.status = SeatStatus.AVAILABLE;
    }
    
    public String getShowSeatId() {
        return showSeatId;
    }

    public Seat getSeat() {
        return seat;
    }

    public Show getShow() {
        return show;
    }

    public boolean isAvailable() {
        return this.status == SeatStatus.AVAILABLE;
    }

    public void bookSeat() {
        this.status = SeatStatus.BOOKED;
    }
    
    public void cancelSeat() {
        this.status = SeatStatus.AVAILABLE;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}