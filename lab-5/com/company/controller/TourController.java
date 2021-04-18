package com.company.controller;

import com.company.controller.listener.ConsoleListener;
import com.company.controller.listener.Listener;
import com.company.controller.procedure.ControllerProcedure;
import com.company.exceptions.*;
import com.company.model.FileTourWriter;
import com.company.model.Model;
import com.company.model.enity.Tour;
import com.company.validator.Validator;
import com.company.view.ConsoleView;
import com.company.view.View;
import com.company.view.dictionaries.Dictionary;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Calendar;

public class TourController {
    private Model model;
    private final View view = new ConsoleView();
    private final Listener listener = new ConsoleListener(view);
    private boolean isWorking = false;
    private static final Logger logger = LogManager.getLogger(TourController.class);

    public void start() {
        logger.info("Project has been started");
        isWorking = true;
        initModel();
        work();
    }

    private void work() {
        while (isWorking) {
            int command = listener.getInt(Dictionary.ENTER, Dictionary.COMMAND);
            if (command == 1) showHelp();
            else if (command == 2) catchErrors(this::stop);
            else if (command == 3) catchErrors(this::showTourList);
            else if (command == 4) catchErrors(this::showToursByOperator);
            else if (command == 5) catchErrors(this::showToursByVisitPoints);
            else if (command == 6) catchErrors(this::showCurrentTours);
            else if (command == 7) catchErrors(this::addTour);
            else if (command == 8) catchErrors(this::removeTour);
            else view.showError(Dictionary.ERROR_COMMAND.toString());
        }
    }

    public void stop() throws DataBaseException {
        isWorking = false;
        model.getControl().saveDataToDB();
        view.showMessage(Dictionary.FAREWELL);
        logger.info("Project has been stopped");
    }

    private void initModel() {
        try {
            model = new Model();
        } catch (DataBaseException e) {
            view.showError(e.getMessage());
            logger.fatal("Project has been stopped with fatal error: " + e);
            System.exit(0);
        }
    }

    private void showHelp() {
        view.showMessage(Dictionary.HELP);
    }

    private void showTourList() throws DataSavingException {
        view.showTours(model.getFilter().getAll());
        saveLastResult();
    }

    private void showToursByOperator() throws MVCWorkingException{
        String candidate = listener.getLine(Dictionary.ENTER, Dictionary.TOUR_OPERATOR);
        Validator.notEmpty(candidate);
        view.showTours(model.getFilter().getToursByOperator(candidate));
        saveLastResult();
    }

    private void showToursByVisitPoints() throws MVCWorkingException {
        String[] candidate = listener.getLine(Dictionary.ENTER, Dictionary.VISIT_POINTS).split(" ");
        Validator.notEmpty(candidate);
        view.showTours(model.getFilter().getToursByVisitPoint(candidate));
        saveLastResult();
    }

    private void showCurrentTours() throws MVCWorkingException {
        String candidateName = listener.getLine(Dictionary.ENTER, Dictionary.NAME);
        Validator.notEmpty(candidateName);
        Calendar candidateDate = listener.getDate(Dictionary.ENTER, Dictionary.DATE);
        Validator.checkLowerBound(candidateDate, Calendar.getInstance());
        view.showTours(model.getFilter().getCurrentTours(candidateName, candidateDate));
        saveLastResult();
    }

    private void addTour() throws MVCWorkingException {
        Tour candidate = listener.getTour();
        view.showTours(new Tour[]{candidate});
        if (listener.confirm(Dictionary.SAVE_QUESTION)) {
            model.getControl().addTour(candidate);
            view.showMessage(Dictionary.COMPLETE);
        }
    }

    private void removeTour() throws MVCWorkingException {
        int id = listener.getInt(Dictionary.ENTER, Dictionary.ID);
        Validator.checkLowerBound(id, 0);
        model.getControl().deleteTourByID(id);
        view.showMessage(Dictionary.COMPLETE);
    }

    private void saveLastResult() throws DataSavingException {
        if (model.getFilter().getBuffer().length != 0 && listener.confirm(Dictionary.SAVE_QUESTION)) {
            FileTourWriter.saveDataIntoFile(
                listener.getLine(Dictionary.ENTER, Dictionary.FILENAME),
                model.getFilter().getBuffer()
            );
            view.showMessage(Dictionary.SUCCESS);

        }
    }

    private void catchErrors(ControllerProcedure procedure) {
        try {
            procedure.execute();
        } catch (ValidationException e) {
            view.showError(e.getMessage());
            logger.warn("VALIDATION ERROR: " + e.getMessage());
        } catch (MVCWorkingException e) {
            view.showError(e.getMessage());
            logger.error(e);
        }
    }

}
