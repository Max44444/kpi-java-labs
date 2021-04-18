package com.company.model;

import com.company.model.db.RandomDB;
import com.company.model.enity.Tour;

import java.util.Arrays;
import java.util.Calendar;

public class TourModel {
    private final RandomDB db;

    public TourModel() {
        this.db = RandomDB.getDB();
    }

    public Tour[] getAll() {
        return db.getData();
    }

    public Tour[] getToursByOperator(String name) {
        Tour[] tours = db.getData();
        Tour[] res = new Tour[tours.length];
        int totalSize = 0;
        for (Tour e : tours) {
            if (e.getTourOperator().equals(name)) {
                res[totalSize++] = e;
            }
        }
        return Arrays.copyOf(res, totalSize);
    }

    public Tour[] getToursByVisitPoint(String[] v) {
        Tour[] tours = db.getData();
        Tour[] res = new Tour[tours.length];
        int totalSize = 0;
        for (Tour e : tours) {
            if (isContainsAll(e.getVisitPoints(), v)) {
                res[totalSize++] = e;
            }
        }
        return Arrays.copyOf(res, totalSize);
    }

    public Tour[] getCurrentTours(String name, Calendar date) {
        Tour[] tours = db.getData();
        Tour[] res = new Tour[tours.length];
        int totalSize = 0;
        for (Tour e : tours) {
            if (
                e.getName().equals(name) &&
                e.getDate().before(date) &&
                e.getFreeSeats() > 0
            ) {
                res[totalSize++] = e;
            }
        }
        return Arrays.copyOf(res, totalSize);
    }

    public void add(Tour tour) {
        db.addTour(tour);
    }

    public void deleteById(int id) {
        db.deleteTourById(id);
    }

    private boolean isContainsAll(String[] arr, String[] sub) {
        boolean result = false;
        for (String i : sub) {
            for (String j : arr) {
                if (i.equals(j)) {
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
