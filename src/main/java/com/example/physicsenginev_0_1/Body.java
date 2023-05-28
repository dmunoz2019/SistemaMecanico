package com.example.physicsenginev_0_1;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public abstract class Body {

    protected Vector2D position;
    protected Vector2D velocity;
    protected double mass;
    protected Color color;

    private boolean isMovable;

    public Body(Vector2D position, Vector2D velocity, double mass, Color color, boolean isMovable) {
        this.position = position;
        this.velocity = velocity;
        this.mass = mass;
        this.color = color;
        this.isMovable = isMovable;
    }

    public abstract void draw(GraphicsContext gc, double scale, double xMin, double yMax);

    public void update(double deltaT) {
        Vector2D acceleration = calculateAcceleration();
        Vector2D gravity = new Vector2D(0, -World.g); // Assuming gravity is directed downward with a magnitude of 9.8 m/s^2
        Vector2D gravitationalForce = gravity.multiply(mass);
        acceleration = gravitationalForce.divide(mass);
        Vector2D deltaVelocity = acceleration.multiply(deltaT);
        velocity = velocity.add(deltaVelocity);

        // Update position
        Vector2D deltaPosition = velocity.multiply(deltaT);
        position = position.add(deltaPosition);
    }

    protected abstract Vector2D calculateAcceleration();

    public Vector2D getPosition() {
        return position;
    }

    public void setPosition(Vector2D position) {
        this.position = position;
    }

    public Vector2D getVelocity() {
        return velocity;
    }

    public void setVelocity(Vector2D velocity) {
        this.velocity = velocity;
    }

    public double getMass() {
        return mass;
    }

    public void setMass(double mass) {
        this.mass = mass;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public boolean isMovable() {
        return isMovable;
    }

    public void setMovable(boolean movable) {
        isMovable = movable;
    }

    protected double toPixelX(double x, double scale, double xMin) {
        return scale * (x - xMin);
    }

    protected double toPixelY(double y, double scale, double yMax) {
        return scale * (yMax - y);
    }
}
