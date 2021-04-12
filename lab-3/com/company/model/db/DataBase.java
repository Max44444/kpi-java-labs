package com.company.model.db;

import com.company.exceptions.DataBaseException;
import com.company.model.enity.Tour;

public interface DataBase {

    Tour[] getData();
    void setData(Tour[] tours) throws DataBaseException;
    void addTour(Tour tour) throws DataBaseException;
    void deleteTourById(int id) throws DataBaseException;
    void saveToDB() throws DataBaseException;

}
