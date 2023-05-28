package com.example.physicsenginev_0_1;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

public class World {
    private List<Body> bodies;

    public World() {
        bodies = new ArrayList<>();
    }

    public void addBody(Body body) {
        bodies.add(body);
    }

    // Define the visible region in the window, defined by the coordinates of the
    // top-left corner (xMin/yMax) and a scale factor
    private final double xMin = -5.0; // meters
    private final double yMax = 8.0; // meters
    private final double scale = 50.0; // pixel/meter

    public static final double g = 0;

    private static World world = null;

    public static World getInstance() {
        if (world == null)
            world = new World();
        return world;
    }

    // Add objects to the simulation world
    public void create() {
//        Vector2D position = new Vector2D(2, 3);
//        Vector2D velocity = new Vector2D(1, 0);
//        double mass = 2.5;
//        Color color = Color.GREEN;
//        double width = 60;
//        double height = 40;
//
//        Rectangle rectangle = new Rectangle(position, velocity, mass, color, width, height);
//        addBody(rectangle);
    }

    private GraphicsContext gc = null;

    public void setGraphicsContext(GraphicsContext gc) {
        this.gc = gc;
    }

    public void drawCircle(double xCenter, double yCenter, double r, Color color) {
        double xPixel = toPixelX(xCenter);
        double yPixel = toPixelY(yCenter);
        double rPixel = scale * r;
        gc.setFill(color);
        gc.fillOval(xPixel - rPixel, yPixel - rPixel, 2 * rPixel, 2 * rPixel);
    }

    private double toPixelX(double x) {
        return scale * (x - xMin);
    }

    private double toPixelY(double y) {
        return scale * (yMax - y);
    }

    // Simulate a time period, advancing time by deltaT
    public void run(double t, double deltaT) {
        for (Body body : bodies) {
            body.update(deltaT);
        }
    }
    private void drawRectangle(double x, double y, double width, double height, Color color) {
        double xPixel = toPixelX(x);
        double yPixel = toPixelY(y);
        double widthPixel = scale * width;
        double heightPixel = scale * height;
        gc.setFill(color);
        gc.fillRect(xPixel, yPixel, widthPixel, heightPixel);
    }

    public void drawBodies() {
        for (Body body : bodies) {
            if (body instanceof Circle) {
                Circle circle = (Circle) body;
                drawCircle(circle.getPosition().getX(), circle.getPosition().getY(), circle.getRadius(), circle.getColor());
            } else if (body instanceof Rectangle) {

                Rectangle rectangle = (Rectangle) body;
                drawRectangle(rectangle.getPosition().getX(), rectangle.getPosition().getY(), rectangle.getWidth(), rectangle.getHeight(), rectangle.getColor());
            }
        }
    }
}
