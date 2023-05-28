package com.example.physicsenginev_0_1;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Circle extends Body {
    private double radius;

    public Circle(Vector2D position, Vector2D velocity, double mass, Color color, boolean isMovable, double radius) {
        super(position, velocity, mass, color, isMovable);
        this.radius = radius;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    @Override
    public void draw(GraphicsContext gc, double scale, double xMin, double yMax) {
        double xPixel = toPixelX(position.getX(), scale, xMin);
        double yPixel = toPixelY(position.getY(), scale, yMax);
        double rPixel = scale * radius;
        gc.setFill(color);
        gc.fillOval(xPixel - rPixel, yPixel - rPixel, rPixel * 2, rPixel * 2);
    }

    @Override
    protected Vector2D calculateAcceleration() {
        Vector2D gravity = new Vector2D(0, 9.8); // Gravitational acceleration (assuming downward direction)
        return gravity;
    }


}
