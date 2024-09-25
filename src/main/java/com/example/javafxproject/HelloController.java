package com.example.javafxproject;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.ScrollEvent;
import javafx.scene.paint.Color;

public class HelloController {

    private double scale = 1.0;
    private double translateX = 0;
    private double translateY = 0;
    private double lastMouseX;
    private double lastMouseY;

    @FXML
    private Canvas canvas;
    GraphicsContext gc;

    @FXML
    protected void zaporedno() {
        start(0);
    }

    @FXML
    protected void vzporedno() {
        start(1);
    }

    @FXML
    protected void porazdeljeno() {
        start(2);
    }

    private void start(int mode) {
        // Reset translation and scaling variables
        translateX = 0;
        translateY = 0;
        scale = 1.0;

        canvas.setOnMousePressed(this::onMousePressed);
        canvas.setOnMouseDragged(this::onMouseDragged);
        canvas.setOnScroll(this::handleScroll);

        gc = canvas.getGraphicsContext2D();
        clearCanvas();

        Run run = new Run(gc, mode);
        run.start();
    }

    private void clearCanvas(){
        gc.setFill(Color.TRANSPARENT);
        gc.clearRect(0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());
    }

    private void onMousePressed(javafx.scene.input.MouseEvent event) {
        lastMouseX = event.getX();
        lastMouseY = event.getY();
    }

    private void onMouseDragged(javafx.scene.input.MouseEvent event) {
        double deltaX = event.getX() - lastMouseX;
        double deltaY = event.getY() - lastMouseY;

        // Increase the panning scale by multiplying the deltaX and deltaY
        double panScale = 2.0; // Adjust this value as needed
        deltaX *= panScale;
        deltaY *= panScale;

        translateX += deltaX;
        translateY += deltaY;

        canvas.setTranslateX(translateX);
        canvas.setTranslateY(translateY);

        lastMouseX = event.getX();
        lastMouseY = event.getY();
    }


    private void handleScroll(ScrollEvent event) {
        double delta = event.getDeltaY();
        if (delta > 0) {
            scale *= 1.1;
        } else {
            scale /= 1.1;
        }

        Canvas canvas = (Canvas) event.getSource();
        canvas.setScaleX(scale);
        canvas.setScaleY(scale);

        event.consume();
    }

}
