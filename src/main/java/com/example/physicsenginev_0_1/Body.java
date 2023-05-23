package com.example.physicsenginev_0_1;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * Clase abstracta que representa un cuerpo de dos dimensiones en un entorno 2D.
 */
public abstract class Body {
    protected Vector2D position;
    protected Vector2D velocity;
    protected double mass;
    protected Color color;

    /**
     * Constructor de la clase Body.
     *
     * @param position La posición inicial del cuerpo.
     * @param velocity La velocidad inicial del cuerpo.
     * @param mass     La masa del cuerpo.
     * @param color    El color del cuerpo.
     */
    public Body(Vector2D position, Vector2D velocity, double mass, Color color) {
        this.position = position;
        this.velocity = velocity;
        this.mass = mass;
        this.color = color;
    }

    /**
     * Dibuja el cuerpo en el contexto gráfico dado, utilizando el factor de escala y las coordenadas de la ventana.
     *
     * @param gc    El contexto gráfico en el que se dibuja el cuerpo.
     * @param scale El factor de escala para convertir unidades de metros a píxeles.
     * @param xMin  La coordenada x mínima de la ventana visible.
     * @param yMax  La coordenada y máxima de la ventana visible.
     */
    public abstract void draw(GraphicsContext gc, double scale, double xMin, double yMax);

    /**
     * Actualiza el estado del cuerpo en función del delta de tiempo dado.
     *
     * @param deltaT El delta de tiempo para avanzar en la simulación.
     */
    public void update(double deltaT) {
        Vector2D acceleration = calculateAcceleration();
        velocity = velocity.add(acceleration.multiply(deltaT));
        position = position.add(velocity.multiply(deltaT));
    }

    /**
     * Calcula la aceleración del cuerpo en función de su estado actual.
     *
     * @return El vector de aceleración del cuerpo.
     */
    protected abstract Vector2D calculateAcceleration();

    // Getters and setters

    /**
     * Obtiene la posición actual del cuerpo.
     *
     * @return El vector de posición del cuerpo.
     */
    public Vector2D getPosition() {
        return position;
    }

    /**
     * Establece la posición del cuerpo.
     *
     * @param position El nuevo vector de posición del cuerpo.
     */
    public void setPosition(Vector2D position) {
        this.position = position;
    }

    /**
     * Obtiene la velocidad actual del cuerpo.
     *
     * @return El vector de velocidad del cuerpo.
     */
    public Vector2D getVelocity() {
        return velocity;
    }

    /**
     * Establece la velocidad del cuerpo.
     *
     * @param velocity El nuevo vector de velocidad del cuerpo.
     */
    public void setVelocity(Vector2D velocity) {
        this.velocity = velocity;
    }

    /**
     * Obtiene la masa del cuerpo.
     *
     * @return La masa del cuerpo.
     */
    public double getMass() {
        return mass;
    }

    /**
     * Establece la masa del cuerpo.
     *
     * @param mass La nueva masa del cuerpo.
     */
    public void setMass(double mass) {
        this.mass = mass;
    }

    /**
     * Obtiene el color del cuerpo.
     *
     * @return El color del cuerpo.
     */
    public Color getColor() {
        return color;
    }
    public void setColor(Color color) {
        this.color = color;
    }

    public abstract void updatePosition(double deltaT);
}