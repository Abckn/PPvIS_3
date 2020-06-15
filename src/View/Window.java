package View;

import controller.MouseDragListener;
import controller.ZoomListener;
import model.Chart1;
import model.Chart2;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class Window {
    private int time = 1000;
    private Runnable pointTimer;
    private Runnable chartTimer;
    private boolean Select2 = false;
    private boolean ifRunnable = false;
    private Button plus;
    private Button minus;
    private GraphicWindow GraphicWindow;
    public GraphicWindow getGraphicWindow() {
        return GraphicWindow;
    }
    public Button getPlus() {
        return plus;
    }
    public Button getMinus() {
        return minus;
    }

    public void start() {
        Display display = new Display();
        Shell shell = new Shell(display);
        shell.setText("Graphic");
        shell.setSize(900, 900);

        GraphicWindow = new GraphicWindow(shell);
        Chart1 chart1 = new Chart1(GraphicWindow);
        Chart2 Chart2 = new Chart2(GraphicWindow);
        TableWindow TableWindow = new TableWindow(shell);

        Button start = new Button(shell, SWT.PUSH);
        start.setBounds(50, 50, 150, 50);
        start.setText("Start");

        Button stop = new Button(shell, SWT.PUSH);
        stop.setBounds(50, 100, 150, 50);
        stop.setText("Stop");

        plus = new Button(shell, SWT.PUSH);
        plus.setBounds(0, 50, 50, 50);
        plus.setText("+");

        minus = new Button(shell, SWT.PUSH);
        minus.setBounds(0, 100, 50, 50);
        minus.setText("-");

        MouseDragListener MouseDragListener = new MouseDragListener(this);
        ZoomListener ZoomListener = new ZoomListener(this);

        start.addSelectionListener(new SelectionListener() {
            @Override
            public void widgetSelected(SelectionEvent selectionEvent) {

                if (!ifRunnable) {
                    ifRunnable = true;
                    Select2 = false;
                        chart1.setFirstPoint(-100);
                        Chart2.setFirstPoint(0);
                   // }

                    pointTimer = new Runnable() {
                        @Override
                        public void run() {

                            chart1.setPoint();
                            Chart2.setPoint();
                            display.timerExec(time, this);
                        }
                    };

                    display.timerExec(time, pointTimer);

                    chartTimer = new Runnable() {
                        @Override
                        public void run() {
                            chart1.upGraphicWindow();
                            Chart2.upGraphicWindow();
                            Chart2.update(TableWindow);
                            display.timerExec(time, this);

                        }
                    };
                    display.timerExec(time, chartTimer);

                }
            }

            @Override
            public void widgetDefaultSelected(SelectionEvent selectionEvent) {

            }
        });
        stop.addSelectionListener(new SelectionListener() {
            @Override
            public void widgetSelected(SelectionEvent selectionEvent) {
                if(Select2){
                    chart1.getPoints().clear();
                    Chart2.getPoints().clear();
                    Chart2.setFirstPoint(0.0);
                    chart1.setFirstPoint(-100.0);
                    chart1.upGraphicWindow();
                    Chart2.upGraphicWindow();
                    chart1.update(TableWindow);
                }
                if (ifRunnable){
                    ifRunnable = false;
                    Select2 = true;
                    display.timerExec(-1, pointTimer);
                    display.timerExec(-1, chartTimer);
                }
            }

            @Override
            public void widgetDefaultSelected(SelectionEvent selectionEvent) {

            }
        });

        plus.addSelectionListener(new SelectionListener() {
            @Override
            public void widgetSelected(SelectionEvent selectionEvent) { }

            @Override
            public void widgetDefaultSelected(SelectionEvent selectionEvent) {

            }
        });

        minus.addSelectionListener(new SelectionListener() {
            @Override
            public void widgetSelected(SelectionEvent selectionEvent) {
              }

            @Override
            public void widgetDefaultSelected(SelectionEvent selectionEvent) {

            }
        });

        shell.open();

        while (!shell.isDisposed()) {
            if (!display.readAndDispatch()) {
                display.sleep();
            }
        }
        display.dispose();
    }
}
