package model;

import View.TableWindow;
import View.GraphicWindow;
import model.Point;

import java.util.ArrayList;

public class Chart2 {
    private GraphicWindow GraphicWindow;
    private TableWindow TableWindow;
    private double firstPoint = 0.0;
    private double lastPoint = 0.0;
    private double step = 0.01;
    public double getFirstPoint() {
        return firstPoint;
    }
    public double getLastPoint() {
        return lastPoint;
    }
    public ArrayList<Point> getPoints() {
        return points;
    }
    ArrayList<Point> points = new ArrayList<>();

    public Chart2(GraphicWindow GraphicWindow) {
        this.GraphicWindow = GraphicWindow;
    }
    public void update(TableWindow TableWindow){
        TableWindow.addWidget(points);
    }

    public void setFirstPoint(double firstPoint) {
        this.lastPoint = firstPoint;
        this.firstPoint = firstPoint;
    }

    public void setPoint() {
        Point point = new Point(lastPoint);
        point.setPoints(points);
        point.FunctionB();
        points.add(point);
        lastPoint += step;
    }


    public void upGraphicWindow() {
        GraphicWindow.drawWindow(points , "Second");
    }

    public GraphicWindow getGraphicWindow() {
        return GraphicWindow;
    }

}
