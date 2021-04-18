package com.company.model;

import com.company.exceptions.DataBaseException;
import com.company.model.db.DataBase;
import com.company.model.db.FileSystemDB;

public class Model {

    private final TourModelFilter filter;
    private final FileTourWriter writer;
    private final ModelDataControl control;

    public Model() throws DataBaseException {
        DataBase db = FileSystemDB.getDB();
        filter = new TourModelFilter(db);
        control = new ModelDataControl(db);
        writer = new FileTourWriter();
    }

    public TourModelFilter getFilter() {
        return filter;
    }

    public FileTourWriter getWriter() {
        return writer;
    }

    public ModelDataControl getControl() {
        return control;
    }
}
