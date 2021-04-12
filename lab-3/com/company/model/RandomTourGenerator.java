package com.company.model;

import com.company.model.enity.Tour;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.GregorianCalendar;
import java.util.Random;

public class RandomTourGenerator {
    private static final String[] tourNames = {"grandtour", "supertour", "studenttour"};
    private static final String[] operatorNames = {"airlines", "globe", "worldtravel"};
    private static final String[] pointsNames = {"poland", "germany", "france", "italy", "spain", "switzerland"};

    public static Tour generateTour() {
        shuffleArray();
        Random random = new Random();
        return new Tour(
            tourNames[random.nextInt(3)],
            operatorNames[random.nextInt(3)],
            Arrays.copyOfRange(pointsNames, 0, random.nextInt(pointsNames.length - 1) + 1),
            new BigDecimal(random.nextInt(10000) + 5000),
            random.nextInt(20) + 10,
            random.nextInt(10),
            new GregorianCalendar(2021, random.nextInt(8) + 4, random.nextInt(20))
        );
    }

    private static void shuffleArray() {
        Random random = new Random();
        for (int i = 0; i < pointsNames.length; i++) {
            int randomIdx = random.nextInt(pointsNames.length);
            String temp = pointsNames[randomIdx];
            pointsNames[randomIdx] = pointsNames[i];
            pointsNames[i] = temp;
        }
    }
}
