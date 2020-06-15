package View;

import model.Point;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.swtchart.*;
import java.util.ArrayList;

public class GraphicWindow {
    private Chart chart;

    GraphicWindow(Shell shell) {
        chart = new Chart(shell, SWT.NONE);
        chart.setBounds(200, 50, 700, 700);
    }

    public Chart getChart() {
        return chart;
    }

    public void redraw() {
        this.getChart().redraw();
    }

    public void drawWindow(ArrayList<Point> point, String string) {
        ISeriesSet seriesSet = chart.getSeriesSet();
        ISeries series = seriesSet.createSeries(ISeries.SeriesType.LINE, string);

        ILineSeries lineSeries = (ILineSeries) chart.getSeriesSet().getSeries(string);
        Display display = this.chart.getDisplay();
        if (string.equals("First")) {
            lineSeries.setLineColor(new Color(display, 150, 10, 150));
        }
        if(string.equals("Second")){
            lineSeries.setLineColor(new Color(display, 10, 250, 250));
        }
        ArrayList<Double> axis_x = new ArrayList<>();
        ArrayList<Double> axis_y = new ArrayList<>();
        for (Point p : point) {
            axis_x.add(p.getX());
            axis_y.add(p.getY());
        }
        double[] x = new double[axis_x.size()];
        double[] y = new double[axis_y.size()];
            for (int i = 0; i < axis_y.size(); ++i) {
            y[i] = axis_y.get(i);
            x[i] = axis_x.get(i);
        }
        series.setYSeries(y);
        series.setXSeries(x);
        IAxisSet axisSet = chart.getAxisSet();
        axisSet.adjustRange();
        chart.redraw();
    }

}
