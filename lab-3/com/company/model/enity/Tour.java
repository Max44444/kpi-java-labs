package com.company.model.enity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Calendar;

public class Tour implements Serializable {
    private static long tourIds = 0;

    private transient Long id = null;
    private final String name;
    private final String tourOperator;
    private final String[] visitPoints;
    private final BigDecimal price;
    private final int seatsNumber;
    private final int freeSeats;
    private final Calendar date;

    public Tour(
            String name,
            String tourOperator,
            String[] visitPoints,
            BigDecimal price,
            int seatsNumber,
            int freeSeats,
            Calendar date
    ) {
        this.name = name;
        this.tourOperator = tourOperator;
        this.visitPoints = visitPoints;
        this.price = price;
        this.seatsNumber = seatsNumber;
        this.freeSeats = freeSeats;
        this.date = date;
    }

    public long getId() {
        if (id == null) {
            id = ++tourIds;
        }
        return id;
    }

    public String getName() {
        return name;
    }

    public String getTourOperator() {
        return tourOperator;
    }

    public String[] getVisitPoints() {
        return visitPoints;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public int getSeatsNumber() {
        return seatsNumber;
    }

    public int getFreeSeats() {
        return freeSeats;
    }

    public Calendar getDate() {
        return date;
    }

    @Override
    public String toString() {
        return  "\n name: " + name +
                "\n tourOperator: " + tourOperator +
                "\n visitPoints: " + Arrays.toString(visitPoints) +
                "\n price: " + price +
                "\n seatsNumber: " + seatsNumber +
                "\n freeSeats: " + freeSeats +
                "\n date: " + date.getTime();
    }
}
