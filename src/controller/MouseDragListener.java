package controller;


import model.Coordinates;
import View.Window;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.MouseMoveListener;
import org.swtchart.IAxis;

public class MouseDragListener implements MouseListener, MouseMoveListener {

    private final Window window;
    private final Coordinates<Integer> cursorCoordinates;
    private boolean isDragging;

    public MouseDragListener(Window window) {
        this.window = window;
        this.isDragging = false;
        this.cursorCoordinates = new Coordinates<>();
        this.window.getGraphicWindow().getChart().getPlotArea().addMouseListener(this);
        this.window.getGraphicWindow().getChart().getPlotArea().addMouseMoveListener(this);
    }



    @Override
    public void mouseDoubleClick(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseDown(MouseEvent mouseEvent) {
        this.cursorCoordinates.put(mouseEvent.x, mouseEvent.y);
        this.isDragging = true;
    }

    @Override
    public void mouseUp(MouseEvent mouseEvent) {
        this.cursorCoordinates.clear();
        this.isDragging = false;
    }

    @Override
    public void mouseMove(MouseEvent mouseEvent) {
        if (this.isDragging) {
            Coordinates<Coordinates<Boolean>> direction = this.getDirection(new Coordinates<>(mouseEvent.x, mouseEvent.y));
            IAxis xAsix = this.window.getGraphicWindow().getChart().getAxisSet().getXAxis(0);
            IAxis yAsix = this.window.getGraphicWindow().getChart().getAxisSet().getYAxis(0);

            boolean left = direction.getFirst().getFirst();
            boolean right = direction.getFirst().getSecond();
            boolean up = direction.getSecond().getFirst();
            boolean down = direction.getSecond().getSecond();

            if (left) xAsix.scrollUp();
            if (right) xAsix.scrollDown();
            if (up) yAsix.scrollDown();
            if (down) yAsix.scrollUp();

            this.cursorCoordinates.put(mouseEvent.x, mouseEvent.y);
            this.window.getGraphicWindow().redraw();
        }
    }

    private Coordinates<Coordinates<Boolean>> getDirection(Coordinates<Integer> currentCursorCoordinates) {


        Coordinates<Coordinates<Boolean>> direction = new Coordinates<>();
        Coordinates<Boolean> leftRight = new Coordinates<>();
        Coordinates<Boolean> upDown = new Coordinates<>();

        int prevX = this.cursorCoordinates.getFirst();
        int prevY = this.cursorCoordinates.getSecond();
        int currentX = currentCursorCoordinates.getFirst();
        int currentY = currentCursorCoordinates.getSecond();

        leftRight.setFirst(currentX < prevX + 20);    // If X coordinate has decreased - cursor is moving left
        leftRight.setSecond(currentX > prevX - 20);   // If X coordinate has increased - cursor is moving right
        upDown.setFirst(currentY < prevY + 20);       // If Y coordinate has decreased - cursor is moving up
        upDown.setSecond(currentY > prevY - 20);      // If Y coordinate has increased - cursor is moving down

        direction.put(leftRight, upDown);

        return direction;
    }
}
