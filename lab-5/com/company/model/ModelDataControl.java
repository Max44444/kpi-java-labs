package com.company.model;

import com.company.exceptions.DataBaseException;
import com.company.model.db.DataBase;
import com.company.model.enity.Tour;

public class ModelDataControl {

    private final DataBase db;

    public ModelDataControl(DataBase db) {
        this.db = db;
    }

    public void addTour(Tour tour) throws DataBaseException {
        db.addTour(tour);
    }

    public void deleteTourByID(int id) throws DataBaseException {
        db.deleteTourById(id);
    }

    public void saveDataToDB() throws DataBaseException {
        db.saveToDB();
    }
}
