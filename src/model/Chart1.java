package model;

import View.TableWindow;
import View.GraphicWindow;

import java.util.ArrayList;

public class Chart1 {
    private GraphicWindow GraphicWindow;
    private TableWindow TableWindow;
    private double firstPoint = 0;
    private double step = 0.01;

    ArrayList<Point> points = new ArrayList<>();

    public ArrayList<Point> getPoints() {
        return points;
    }




    public Chart1(GraphicWindow GraphicWindow) {
        this.GraphicWindow = GraphicWindow;

    }

    public void update(TableWindow TableWindow){
        TableWindow.addWidget(points);
    }

    public double getFirstPoint() {
        return firstPoint;
    }

    public void setFirstPoint(double firstPoint) {
        this.firstPoint = firstPoint;
    }

    public void setPoint() {
        Point point = new Point(firstPoint);
        point.FunctionA();
        points.add(point);
        firstPoint += step;
    }


    public void upGraphicWindow() {
        GraphicWindow.drawWindow(points, "First");
    }

}
