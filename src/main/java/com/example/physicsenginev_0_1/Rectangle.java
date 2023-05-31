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
    public double calculateDragCoefficient() {
        double area = width * height; // Calculate the area of the rectangle
        double airDensity = World.getInstance().getAirDensity(); // Get the air density from the World
        double airViscosity = World.getInstance().getAirViscosity(); // Get the air viscosity from the World
        double velocityMagnitude = velocity.getMagnitude(); // Magnitude of velocity

        // Calculate the Reynolds number
        double characteristicLength = 2 * (width + height); // Characteristic length (perimeter)
        double reynoldsNumber = (airDensity * velocityMagnitude * characteristicLength) / airViscosity;

        // Calculate the drag coefficient based on Reynolds number
        double dragCoefficient;
        if (reynoldsNumber < 0.1)
            dragCoefficient = 0.0; // laminar flow
        else if (reynoldsNumber >= 0.1 && reynoldsNumber <= 100.0)
            dragCoefficient = 0.5; // transition flow
        else
            dragCoefficient = 0.8; // turbulent flow
        return dragCoefficient;
    }
    @Override
    public  void calculateGravityForce(){
        // Apply gravity
        Vector2D gravity = new Vector2D(0, -World.g * mass);
        addForce(gravity);
    }

    @Override
    public void calculateAirResistance() {
        double airDensity = World.getInstance().getAirDensity();
        double velocityMagnitude = velocity.getMagnitude();
        double area = width * height;
        double dragCoefficient = calculateDragCoefficient();

        // Calculamos la resistencia del  aire
        double airResistanceMagnitude = 0.5 * airDensity * Math.pow(velocityMagnitude, 2) * area * dragCoefficient;

        // Calculamos la unidad del vector unitario
        Vector2D velocityUnitVector = velocity.divide(velocityMagnitude);

        // Calculate the air resistance force vector
        Vector2D airResistance = velocityUnitVector.multiply(-airResistanceMagnitude);

        // Add the air resistance force to the total force
        addForce(airResistance);
    }
    @Override
    public void calcForces() {
        calculateGravityForce();
        calculateAirResistance();
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
