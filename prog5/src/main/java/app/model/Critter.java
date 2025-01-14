package app.model;

import java.util.Random;

public abstract class Critter{

    protected int x;
    protected int y;
    protected int speed;
    protected int direction; // 0-359

    private static Random rand = new Random();

    public Critter() {
        this.x = rand.nextInt(World.instance().getWidth() - 1) + 1;
        this.y = rand.nextInt(World.instance().getHeight() - 1) + 1;
        this.speed = rand.nextInt(10) + 1;
        setRandomDirection();
    }

    void setRandomDirection() {
        this.direction = rand.nextInt(360);
    }

    public void updatePosition() {
        x += Math.round(speed * Math.cos(direction * Math.PI / 180.0));
        y += Math.round(speed * Math.sin(direction * Math.PI / 180.0));
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getDirection() {
        return direction;
    }

    public int getSpeed() {
        return speed;
    }

    @Override
    public String toString() {
        return "{x=" + x +
                ", y=" + y +
                ", speed=" + speed +
                ", direction=" + direction +
                '}';
    }
}
