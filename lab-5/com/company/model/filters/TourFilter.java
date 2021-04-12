package com.company.model.filters;

import com.company.model.enity.Tour;

import java.util.Calendar;

public class TourFilter {

    public static boolean compareByTourOperator(Tour tour, String name) {
        return tour.getTourOperator().equalsIgnoreCase(name);
    }

    public static boolean compareByVisitPoints(Tour tour, String[] points) {
        return isContainsAll(tour.getVisitPoints(), points);
    }

    public static boolean isCurrent(Tour tour, String name, Calendar date) {
        return  tour.getName().equalsIgnoreCase(name) &&
                tour.getDate().before(date) &&
                tour.getFreeSeats() > 0;
    }

    private static boolean isContainsAll(String[] arr, String[] sub) {
        boolean result = false;
        for (String i : sub) {
            for (String j : arr) {
                if (i.equalsIgnoreCase(j)) {
                    result = true;
                    break;
                }
            }
            if (!result) {
                return false;
            }
            result = false;
        }
        return true;
    }
}
