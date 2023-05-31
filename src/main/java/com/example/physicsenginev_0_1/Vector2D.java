package com.example.physicsenginev_0_1;

public class Vector2D {
    private double x;
    private double y;

    public Vector2D(double x, double y) {
        this.x = x;
        this.y = y;
    }
    public double getMagnitude() {
        return Math.sqrt(x * x + y * y);
    }
    public Vector2D add(Vector2D other) {
        return new Vector2D(this.x + other.x, this.y + other.y);
    }

    public Vector2D subtract(Vector2D other) {
        return new Vector2D(this.x - other.x, this.y - other.y);
    }

    public Vector2D multiply(double scalar) {
        return new Vector2D(this.x * scalar, this.y * scalar);
    }

    public double dotProduct(Vector2D other) {
        return this.x * other.x + this.y * other.y;
    }

    public double crossProduct(Vector2D other) {
        return this.x * other.y - this.y * other.x;
    }

    public double magnitude() {
        return Math.sqrt(x * x + y * y);
    }

    public Vector2D normalize() {
        double magnitude = magnitude();
        if (magnitude != 0) {
            return new Vector2D(x / magnitude, y / magnitude);
        }
        return new Vector2D(0, 0);
    }

    public double angle() {
        return Math.atan2(y, x);
    }

    public Vector2D rotate(double angle) {
        double cosAngle = Math.cos(angle);
        double sinAngle = Math.sin(angle);
        double newX = x * cosAngle - y * sinAngle;
        double newY = x * sinAngle + y * cosAngle;
        return new Vector2D(newX, newY);
    }

    public Vector2D divide(double scalar) {
        if (scalar != 0) {
            double reciprocal = 1.0 / scalar;
            return new Vector2D(x * reciprocal, y * reciprocal);
        }
        return new Vector2D(0, 0);
    }

    // Additional methods for convenience

    public Vector2D negate() {
        return new Vector2D(-x, -y);
    }

    public Vector2D setMagnitude(double magnitude) {
        return normalize().multiply(magnitude);
    }

    public double distance(Vector2D other) {
        double dx = this.x - other.x;
        double dy = this.y - other.y;
        return Math.sqrt(dx * dx + dy * dy);
    }

    // Getters and setters

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }
}
