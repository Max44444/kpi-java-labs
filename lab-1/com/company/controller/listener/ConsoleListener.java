package com.company.controller.listener;

import com.company.model.enity.Tour;
import com.company.view.ConsoleView;
import com.company.view.Dictionary;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;

public class ConsoleListener {
    private final Scanner scanner = new Scanner(System.in);
    private final ConsoleView view;

    public ConsoleListener(ConsoleView view) {
        this.view = view;
    }

    public int getInt(Dictionary ...pre) {
        while (true) {
            view.showMessage(pre);
            if (scanner.hasNextInt()) {
                return scanner.nextInt();
            } else {
                scanner.nextLine();
                view.showMessage(Dictionary.ERROR_COMMAND);
            }
        }
    }

    public String getLine(Dictionary ...pre) {
        String candidate = "";
        view.showMessage(pre);
        while (candidate.isEmpty()) {
            candidate = scanner.nextLine();
        }
        return candidate;
    }

    public Calendar getDate(Dictionary ...pre) {
        view.showMessage(pre);
        view.showMessage(Dictionary.NEW_LINE);
        int day = getInt(Dictionary.ENTER, Dictionary.DAY);
        int month = getInt(Dictionary.ENTER, Dictionary.MONTH);
        int year = getInt(Dictionary.ENTER, Dictionary.YEAR);
        return new GregorianCalendar(year, month, day);
    }

    public boolean confirm(Dictionary ...pre) {
        return getInt(pre) == 1;
    }

    public Tour getTour() {
        String name = getLine(Dictionary.ENTER, Dictionary.NAME);
        String tourOperator = getLine(Dictionary.ENTER, Dictionary.TOUR_OPERATOR);
        String[] visitPoints = getLine(Dictionary.ENTER, Dictionary.VISIT_POINTS).split(" ");
        BigDecimal price = new BigDecimal(getInt(Dictionary.ENTER, Dictionary.PRICE));
        int seats = getInt(Dictionary.ENTER, Dictionary.SEATS_NUMBER);
        int freeSeats = getInt(Dictionary.ENTER, Dictionary.FREE_SEATS);
        Calendar calendar = getDate(Dictionary.ENTER, Dictionary.DATE);
        return new Tour (name, tourOperator, visitPoints, price, seats, freeSeats, calendar);
    }
}
