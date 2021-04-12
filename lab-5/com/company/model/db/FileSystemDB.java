package com.company.model.db;

import com.company.exceptions.DataBaseException;
import com.company.model.enity.Tour;

import java.io.*;
import java.util.Arrays;

public class FileSystemDB implements DataBase {
    private static final String filename = "src/main/resources/db.bin";
    private Tour[] tours = new Tour[0];
    private static FileSystemDB db;

    public static FileSystemDB getDB() throws DataBaseException {
        if (FileSystemDB.db == null) {
            FileSystemDB.db = new FileSystemDB();
        }
        return FileSystemDB.db;
    }

    private FileSystemDB() throws DataBaseException {
        File tmpDB = new File(filename);
        if (tmpDB.exists()) {
            refreshLocalDB();
        } else {
            throw new DataBaseException("DB connection error...");
        }
    }

    @Override
    public Tour[] getData() {
        return Arrays.copyOf(tours, tours.length);
    }

    @Override
    public void setData(Tour[] tours) throws DataBaseException {
        this.tours = tours;
    }

    @Override
    public void addTour(Tour tour) throws DataBaseException {
        Tour[] newTours = new Tour[tours.length + 1];
        System.arraycopy(tours, 0, newTours, 0, tours.length);
        newTours[tours.length] = tour;
        tours = newTours;
    }

    @Override
    public void deleteTourById(int id) throws DataBaseException {
        int index = findIndexById(id);
        if (index != -1) {
            Tour[] newTours = new Tour[tours.length - 1];
            System.arraycopy(tours, 0, newTours, 0, index);
            System.arraycopy(tours, index + 1, newTours, index, tours.length - index - 1);
            tours = newTours;
        }
    }

    @Override
    public void saveToDB() throws DataBaseException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(tours);
        } catch (IOException e) {
            throw new DataBaseException("Saving data error...");
        }
    }

    void refreshLocalDB() throws DataBaseException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            tours = ((Tour[]) ois.readObject());
        } catch (IOException | ClassNotFoundException e) {
            throw new DataBaseException("Reading data error...");
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
