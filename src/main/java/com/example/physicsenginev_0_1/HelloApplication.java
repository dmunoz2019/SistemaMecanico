package com.example.physicsenginev_0_1;

import javafx.animation.AnimationTimer;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class HelloApplication extends Application {

    private AnimationTimer timer;
    private Timeline timeline;
    private Body circle;
    private Body rectangle;
    protected Text viscosityText;


    @Override
    public void start(Stage stage) {
        stage.setTitle("Tarea de guía 14!");

        Canvas canvas = new Canvas(1000, 600);
        World.getInstance().setGraphicsContext(canvas.getGraphicsContext2D());
        World.getInstance().create();

        // Create the viscosity text
        viscosityText = new Text();
        viscosityText.setFont(new Font(25));
        viscosityText.setFill(Color.WHITE);
        viscosityText.setX(100);
        viscosityText.setY(300);


        // Create the object with initial position, velocity, and other attributes
        Vector2D initialPosition = new Vector2D(0, 0);
        Vector2D initialVelocity = new Vector2D(0, 0);

        double mass = 0.5;

        Color color = Color.RED;

        double radius = 0.20;

        circle = new Circle(initialPosition, initialVelocity, mass, color, true,radius);

        rectangle = new Rectangle(new Vector2D(0, 0), new Vector2D(0, 0), 8.5, Color.RED, true ,0.5,0.45);


        World.getInstance().addBody(circle);
        World.getInstance().addBody(rectangle);


        Group root = new Group();
        root.getChildren().addAll(canvas,viscosityText);
        stage.setScene(new Scene(root));
        stage.show();

        timeline = new Timeline(60);
        timeline.setCycleCount(Timeline.INDEFINITE);

        timer = new AnimationTimer() {
            Long last = null;
            Long start = null;
            double smoothedFrameRate = -1.0;

            @Override
            public void handle(long now) {
                if (last == null) {
                    last = now;
                    start = now;
                    return;
                }
                double dt = (now - last) * 1e-9;
                double t = (now - start) * 1e-9;
                last = now;
                // Update the position of the object within the simulation loop
                circle.update(dt);
                rectangle.update(dt);

                canvas.getGraphicsContext2D().setFill(Color.BLACK);
                canvas.getGraphicsContext2D().fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

                double currentFrameRate = 1.0 / dt;
                if (smoothedFrameRate < 0)
                    smoothedFrameRate = currentFrameRate;
                else
                    smoothedFrameRate = 0.95 * smoothedFrameRate + 0.05 * currentFrameRate;
                String frameRate = String.valueOf((int) (smoothedFrameRate + 0.5));
                canvas.getGraphicsContext2D().setStroke(Color.WHITE);
                canvas.getGraphicsContext2D().strokeText(frameRate, 10, 30);

                World.getInstance().run(t, dt);
                World.getInstance().drawBodies();
                updateViscosityText();
            }
        };

        timeline.play();
        timer.start();
    }
    private void updateViscosityText() {
        double airViscosity = World.getInstance().getAirViscosity();

        viscosityText.setText("Air Viscosity: " + airViscosity);
    }
    public static void main(String[] args) {
        launch(args);
    }
}
