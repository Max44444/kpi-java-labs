package com.company.validator;

import com.company.exceptions.*;
import com.company.model.enity.Tour;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Validator {
    public static void notEmpty(String candidate) throws EmptyStringException {
        if(candidate.isEmpty()) {
            throw new EmptyStringException("The name should not be empty");
        }
    }

    public static void notEmpty(String[] candidate) throws NoVisitPointsException {
        if (candidate.length == 0) {
            throw new NoVisitPointsException("The list of visit points should not be empty");
        }
    }

    public static void checkPrice(BigDecimal candidate) throws IncorrectPriceException {
        if (candidate.compareTo(BigDecimal.valueOf(0)) <= 0) {
            throw new IncorrectPriceException("The price should not be less then or equals 0");
        }
    }

    public static void checkUpperBound(long candidate, long bound) throws IncorrectNumberException {
        if (candidate > bound) {
            throw new IncorrectNumberException("The number should not be grates then " + bound);
        }
    }

    public static void checkUpperBound(Calendar candidate, Calendar bound) throws IncorrectDateException {
        if (candidate.after(bound)) {
            throw new IncorrectDateException("The date should not be after "
                + (new SimpleDateFormat("dd.MM.yyyy").format(bound.getTime())));
        }
    }

    public static void checkLowerBound(long candidate, long bound) throws IncorrectNumberException {
        if (candidate < bound) {
            throw new IncorrectNumberException("The number should not be less then " + bound);
        }
    }

    public static void checkLowerBound(Calendar candidate, Calendar bound) throws IncorrectDateException {
        if (candidate.before(bound)) {
            throw new IncorrectDateException("The date should not be before "
                + (new SimpleDateFormat("dd.MM.yyyy").format(bound.getTime())));
        }
    }

    public static void checkTour(Tour candidate) throws IncorrectTourException {
        try {
            notEmpty(candidate.getName());
            notEmpty(candidate.getTourOperator());
            notEmpty(candidate.getVisitPoints());
            checkPrice(candidate.getPrice());
            checkLowerBound(candidate.getSeatsNumber(), 0);
            checkLowerBound(candidate.getFreeSeats(), 0);
            checkUpperBound(candidate.getFreeSeats(), candidate.getSeatsNumber());
            checkLowerBound(candidate.getDate(), Calendar.getInstance());
        } catch (Exception e) {
            throw new IncorrectTourException("Incorrect tour data\n" + e.getMessage());
        }
    }
}
