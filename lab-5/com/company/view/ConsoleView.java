package com.company.view;

import com.company.model.enity.Tour;
import com.company.view.dictionaries.Dictionary;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class ConsoleView implements View {
    private final DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");

    public ConsoleView() {
        showMessage(Dictionary.GREETING);
        showMessage(Dictionary.HELP);
    }

    @Override
    public void showTours(Tour[] tours) {
        System.out.println("\033[0;33m");
        if (tours.length == 0) {
            System.out.println(Dictionary.NOT_FOUND);
            return;
        }

        System.out.println(Dictionary.FOUND_TOURS);
        drawLine();
        System.out.printf("|%16s |%16s |%16s |%16s |%16s |%16s |%16s |%16s |\n",
            Dictionary.ID,
            Dictionary.NAME,
            Dictionary.TOUR_OPERATOR,
            Dictionary.VISIT_POINTS,
            Dictionary.PRICE,
            Dictionary.SEATS_NUMBER,
            Dictionary.FREE_SEATS,
            Dictionary.DATE
        );
        drawLine();
        for (Tour t : tours) {
            showOneTour(t);
            drawLine();
        }
    }

    @Override
    public void showMessage(Dictionary... message) {
        System.out.println("\033[0;34m");
        for (Dictionary e : message) {
            System.out.print(e + " ");
        }
    }

    @Override
    public void showError(String message) {
        System.out.println("\033[0;31m");
        System.out.println(message);
    }

    private void showOneTour(Tour t) {
        String format = "|%16s |%16s |%16s |%16s |%16s |%16s |%16s |%16s |\n";
        System.out.printf(
            format,
            t.getId(),
            t.getName(),
            t.getTourOperator(),
            t.getVisitPoints()[0],
            t.getPrice(), t.getSeatsNumber(),
            t.getFreeSeats(),
            dateFormat.format(t.getDate().getTime())
        );
        if (t.getVisitPoints().length > 1) {
            String[] points = t.getVisitPoints();
            for (int i = 1; i < points.length; i++) {
                System.out.printf(format,"", "", "", points[i], "", "", "", "");
            }
        }
    }

    private void drawLine() {
        System.out.println("+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+");
    }
}
