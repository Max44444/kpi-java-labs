package com.company.line;

import java.util.Objects;

public class Line implements Comparable<Line> {

    public final Point BEGIN;
    public final Point END;

    public Line(Point BEGIN, Point END) {
        this.BEGIN = BEGIN;
        this.END = END;
    }

    @Override
    public String toString() {
        return "{" + BEGIN + ", " + END + '}';
    }


    @Override
    public int compareTo(Line line) {
        return Double.compare(Math.min(BEGIN.X, END.X) - Math.min(line.BEGIN.X, line.END.X), 0.0);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Line line = (Line) o;
        return Objects.equals(BEGIN, line.BEGIN) && Objects.equals(END, line.END);
    }

    @Override
    public int hashCode() {
        return Objects.hash(BEGIN, END);
    }

}
