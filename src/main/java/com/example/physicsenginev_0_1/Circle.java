package com.example.physicsenginev_0_1;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Circle extends Body {
    private double radius;
    private boolean isMovable;

    public Circle(Vector2D position, Vector2D velocity, double mass, Color color, boolean isMovable, double radius) {
        super(position, velocity, mass, color,isMovable );
        this.radius = radius;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public void draw(GraphicsContext gc, double scale, double xMin, double yMax) {

    }

    @Override
    protected Vector2D calculateAcceleration() {

        return null;
    }

    @Override
    public void updatePosition(double deltaT) {
        // Calculate the displacement based on the current velocity and time step
        Vector2D displacement = velocity.multiply(deltaT);

        // Update the position by adding the displacement
        position = position.add(displacement);
    }
}
