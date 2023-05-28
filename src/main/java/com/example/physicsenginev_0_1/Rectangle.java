package com.example.physicsenginev_0_1;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Rectangle extends Body {
    private double width;
    private double height;

    public Rectangle(Vector2D position, Vector2D velocity, double mass, Color color, boolean isMovable, double width, double height) {
        super(position, velocity, mass, color, isMovable);
        this.width = width;
        this.height = height;
    }

    @Override
    public void draw(GraphicsContext gc, double scale, double xMin, double yMax) {
        double xPixel = toPixelX(position.getX(), scale, xMin);
        double yPixel = toPixelY(position.getY(), scale, yMax);
        double wPixel = scale * width;
        double hPixel = scale * height;
        gc.setFill(color);
        gc.fillRect(xPixel, yPixel, wPixel, hPixel);
    }

    @Override
    protected Vector2D calculateAcceleration() {
        return new Vector2D(0, 0);
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
