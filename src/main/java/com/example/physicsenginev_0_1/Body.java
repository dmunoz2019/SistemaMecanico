package com.example.physicsenginev_0_1;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public abstract class Body {
    protected Vector2D position;
    protected Vector2D velocity;
    protected Vector2D acceleration;

    protected double mass;
    protected Color color;
    private boolean isMovable;
    protected Vector2D totalForce;

    public Body(Vector2D position, Vector2D velocity, double mass, Color color, boolean isMovable) {
        this.position = position;
        this.velocity = velocity;
        this.mass = mass;
        this.color = color;
        this.isMovable = isMovable;
        this.acceleration = new Vector2D(0, 0);
        this.totalForce = new Vector2D(0, 0);
    }

    public abstract void draw(GraphicsContext gc, double scale, double xMin, double yMax);

    public void update(double deltaTime) {
        if (isMovable) {
            calculateAcceleration();
            calculateVelocity(deltaTime);
            calculatePosition(deltaTime);

        }
    }
    protected abstract void calcForces();

    protected void  calculateAcceleration(){
        totalForce = new Vector2D(0, 0);
        calcForces();
        acceleration = totalForce.divide(mass);
    }

    protected void calculateVelocity(double deltaTime) {
        velocity = velocity.add(acceleration.multiply(deltaTime));
    }

    protected void calculatePosition(double deltaTime) {
        position = position.add(velocity.multiply(deltaTime));
    }

    public abstract double calculateDragCoefficient();

    public Vector2D getPosition() {
        return position;
    }

    protected void addForce(Vector2D force) {
        totalForce = totalForce.add(force);
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

    public Vector2D getAcceleration() {
        return acceleration;
    }

    public void setAcceleration(Vector2D acceleration) {
        this.acceleration = acceleration;
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

    public abstract void calculateAirResistance();
    public abstract void calculateGravityForce();
}
