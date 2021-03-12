package com.company.model.db;

import com.company.model.RandomTourGenerator;
import com.company.model.enity.Tour;

import java.util.Arrays;

public class RandomDB {
    private Tour[] tours = new Tour[10];
    private static RandomDB db;

    public static RandomDB getDB() {
        if (db == null) {
            db = new RandomDB();
        }
        return db;
    }

    private RandomDB() {
        initializeData();
    }

    public Tour[] getData() {
        return Arrays.copyOf(tours, tours.length);
    }

    public void setData(Tour[] tours) {
        this.tours = tours;
    }

    public void addTour(Tour tour) {
        Tour[] newTours = new Tour[tours.length + 1];
        System.arraycopy(tours, 0, newTours, 0, tours.length);
        newTours[tours.length] = tour;
        tours = newTours;
    }

    public void deleteTourById(int id) {
        int index = findIndexById(id);
        if (index != -1) {
            Tour[] newTours = new Tour[tours.length - 1];
            System.arraycopy(tours, 0, newTours, 0, index);
            System.arraycopy(tours, index + 1, newTours, index, tours.length - index - 1);
            tours = newTours;
        }
    }

    private void initializeData() {
        for (int i = 0; i < tours.length; i++) {
            tours[i] = RandomTourGenerator.generateTour() ;
        }
    }

    private int findIndexById(long id) {
        for (int i = 0; i < tours.length; i++) {
            if (tours[i].getId() == id) {
                return i;
            }
        }
        return -1;
    }
}
