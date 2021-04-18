package com.company.model.db;

import com.company.model.enity.Tour;

public interface DataBase {
    Tour[] getData();

    void setData(Tour[] tours);

    void addTour(Tour tour);

    void deleteTourById(int id);
}
