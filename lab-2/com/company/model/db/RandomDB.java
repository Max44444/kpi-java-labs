package com.company.model.db;

import com.company.model.RandomTourGenerator;
import com.company.model.enity.Tour;

import java.util.Arrays;

public class RandomDB implements DataBase {
    private Tour[] tours = new Tour[10];
    private static DataBase db;

    private RandomDB() {
        initializeData();
    }

    public static DataBase getDB() {
        if (RandomDB.db == null) {
            RandomDB.db = new RandomDB();
        }
        return RandomDB.db;
    }


    @Override
    public Tour[] getData() {
        return Arrays.copyOf(tours, tours.length);
    }

    @Override
    public void setData(Tour[] tours) {
        this.tours = tours;
    }

    @Override
    public void addTour(Tour tour) {
        Tour[] newTours = new Tour[tours.length + 1];
        System.arraycopy(tours, 0, newTours, 0, tours.length);
        newTours[tours.length] = tour;
        tours = newTours;
    }

    @Override
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
