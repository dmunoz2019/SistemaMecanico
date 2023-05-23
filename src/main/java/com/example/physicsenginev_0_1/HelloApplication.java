package com.example.physicsenginev_0_1;

import javafx.animation.AnimationTimer;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class HelloApplication extends Application {

    private AnimationTimer timer;
    private Timeline timeline;

    @Override
    public void start(Stage stage) {
        stage.setTitle("Tarea de guía 14!");

        Canvas canvas = new Canvas(800, 600);
        World.getInstance().setGraphicsContext(canvas.getGraphicsContext2D());
        World.getInstance().create();

        Rectangle rectangle = new Rectangle(new Vector2D(1, 0), new Vector2D(1, 0), 2.5, Color.GREEN, 0.6, 0.4);
        Circle circle =new Circle(new Vector2D(2,2), new Vector2D(0,0), 500.0,Color.BROWN,0.8);
        World.getInstance().addBody(rectangle);
        World.getInstance().addBody(circle);

        Group root = new Group();
        root.getChildren().add(canvas);
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

                canvas.getGraphicsContext2D().setFill(Color.DARKBLUE);
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
            }
        };

        timeline.play();
        timer.start();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
