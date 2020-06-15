package model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;


public class Point {
    private double x;
    private double y;
    private double result = 0.0;

    public ArrayList<Point> getPoints() {
        return points;
    }

    public void setPoints(ArrayList<Point> points) {
        this.points = points;
    }

    private ArrayList<Point> points = new ArrayList<>();

    Point(double x) {
        BigDecimal bd = new BigDecimal(Double.toString(x));
        bd = bd.setScale(1, RoundingMode.HALF_UP);
        this.x = bd.doubleValue();
    }

    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }

    public void FunctionA() {
        this.y = 3*this.x - 4;
    }

    public void FunctionB() {
        result += (Math.pow(-1,points.size())*Math.pow(x,2*points.size()))/factorial(2*points.size());
        BigDecimal bd = new BigDecimal(Double.toString(result));
        bd = bd.setScale(4, RoundingMode.HALF_UP);
        this.y =bd.doubleValue();

    }

    public static double factorial(double n)
    {
        if (n == 0) return 1;
        return n * factorial(n-1);
    }

    public void simple(){
        this.y = (double) ((-this.x -1));
    }


}
