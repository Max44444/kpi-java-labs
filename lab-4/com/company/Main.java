package com.company;

import com.company.line.Line;
import com.company.line.Point;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        Set<Line> lines = getLines();
        printSet(lines);
        System.out.println();

        Line l1 = Collections.min(lines);
        Line l2 = getNextMin(lines, l1);

        System.out.println("Line 1:" + l1);
        System.out.println("Line 2:" + l2);

        System.out.println("The cross point is: " + getCrossPointWith(l1, l2));
    }

    static void printSet(Set<Line> lines) {
        for(Line e : lines) {
            System.out.println(e);
        }
    }

    private static Line getNextMin(Set<Line> lines, Line l1) {
        Iterator<Line> iterator = lines.iterator();
        Line l2 = iterator.next();

        if (l2.equals(l1)) {
            l2 = iterator.next();
        }

        while (iterator.hasNext()) {
            Line tmp = iterator.next();
            if (tmp.compareTo(l2) < 0 && !tmp.equals(l1)) {
                l2 = tmp;
            }
        }
        return l2;
    }

    static Set<Line> getLines() {
        Random random = new Random();
        Set<Line> lines = new HashSet<>();

        for (int i = 0; i < 10; i++) {
            lines.add(new Line(
                new Point(random.nextDouble() * 200 - 100, random.nextDouble() * 200 - 100),
                new Point(random.nextDouble() * 200 - 100, random.nextDouble() * 200 - 100)
            ));
        }

        return lines;
    }

    public static Point getCrossPointWith(Line first, Line second) {
        double a1 = first.BEGIN.Y - first.END.Y;
        double b1 = first.END.X - first.BEGIN.X;
        double a2 = second.BEGIN.Y - second.END.Y;
        double b2 = second.END.X - second.BEGIN.X;

        double d = a1 * b2 - a2 * b1;
        if (d != 0) {
            double c1 = first.END.Y * first.BEGIN.X - first.END.X * first.BEGIN.Y;
            double c2 = second.END.Y * second.BEGIN.X - second.END.X * second.BEGIN.Y;

            return new Point(
                (b1 * c2 - b2 * c1) / d,
                (a2 * c1 - a1 * c2) / d
            );
        } else {
            return null;
        }
    }

}
