package com.company.model;

import com.company.exceptions.DataSavingException;
import com.company.model.enity.Tour;

import java.io.FileOutputStream;
import java.io.IOException;

public class FileTourWriter {

    public static void saveDataIntoFile(String filename, Tour[] tours) throws DataSavingException {

        try (FileOutputStream fos = new FileOutputStream("./user-files/" + filename)) {
            StringBuilder data = new StringBuilder();

            for (Tour t : tours) {
                data.append('\n').append(t.toString());
            }

            fos.write(data.toString().getBytes());
        } catch (IOException e) {
            throw new DataSavingException("Cannot save data to the file");
        }

    }

}