package controller;

import View.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.MouseWheelListener;
import org.eclipse.swt.widgets.Button;


public class ZoomListener implements MouseListener, MouseWheelListener {

    private final View.Window window;
    private long zoomCounter = 0;

    public ZoomListener(Window window) {
        this.window = window;
        this.window.getPlus().addMouseListener(this);
        this.window.getMinus().addMouseListener(this);
        this.window.getGraphicWindow().getChart().addMouseWheelListener(this);
    }

    @Override
    public void mouseDoubleClick(MouseEvent mouseEvent) {
    }

    @Override
    public void mouseDown(MouseEvent mouseEvent) {
        Button target = (Button) mouseEvent.widget;
        if (target == this.window.getPlus())
            this.window.getGraphicWindow().getChart().getAxisSet().zoomIn();
        else if (target == this.window.getMinus())
            this.window.getGraphicWindow().getChart().getAxisSet().zoomOut();
        this.window.getGraphicWindow().redraw();
    }

    @Override
    public void mouseUp(MouseEvent mouseEvent) {
    }

    @Override
    public void mouseScrolled(MouseEvent mouseEvent) {
        long prev = this.zoomCounter;
        this.zoomCounter += mouseEvent.count;
        if (mouseEvent.stateMask != SWT.CTRL) return;
        if (zoomCounter > prev) {
            this.window.getGraphicWindow().getChart().getAxisSet().zoomOut();
        } else if (zoomCounter < prev) {
            this.window.getGraphicWindow().getChart().getAxisSet().zoomIn();
        }
        this.window.getGraphicWindow().redraw();
        this.zoomCounter = 0;
    }
}
