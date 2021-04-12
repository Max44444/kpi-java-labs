package com.company.line;

import java.util.Objects;

public class Point {

    public final double X;
    public final double Y;

    public Point(double x, double y) {
        X = x;
        Y = y;
    }

    @Override
    public String toString() {
        return "(" + X + "; " + Y + ')';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return Double.compare(point.X, X) == 0 && Double.compare(point.Y, Y) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(X, Y);
    }
}
