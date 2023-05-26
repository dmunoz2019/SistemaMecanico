package com.example.physicsenginev_0_1;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Rectangle extends Body {
    private double width;
    private double height;
    private GraphicsContext gc = null;


    public Rectangle(Vector2D position, Vector2D velocity, double mass, Color color, boolean isMovable,  double width, double height) {
        super(position, velocity, mass, color, isMovable);
        this.width = width;
        this.height = height;
    }

//    @Override
//    public void draw(GraphicsContext gc, double scale, double xMin, double yMax) {
//        double xPixel = toPixelX(position.getX(), scale, xMin);
//        double yPixel = toPixelY(position.getY(), scale, yMax);
//        double wPixel = scale * width;
//        double hPixel = scale * height;
//        gc.setFill(color);
//        gc.fillRect(xPixel, yPixel, wPixel, hPixel);
//    }

    @Override
    protected Vector2D calculateAcceleration() {
        return new Vector2D(0, 0);
    }

    @Override
    public void updatePosition(double deltaT) {
        // Calculate the displacement based on the current velocity and time step
        Vector2D displacement = velocity.multiply(deltaT);

        // Update the position by adding the displacement
        position = position.add(displacement);
    }


    private double toPixelX(double x, double scale, double xMin) {
        return scale * (x - xMin);
    }

    private double toPixelY(double y, double scale, double yMax) {
        return scale * (yMax - y);
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }
}
