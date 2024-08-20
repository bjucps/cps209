package model;

import java.util.ArrayList;
import java.util.List;


public class World {

    private List<Critter> critters = new ArrayList<>(); 
    private int width, height;

    /**
     * Factory
     * @param type - the type of critter to create
     * @return the created critter
     */
    public Critter create(CritterType type) {
        return null;
    }

    public boolean remove(Critter c) {
        return critters.remove(c);
    }

    // setters and getters

    public List<Critter> getCritters() {
        return critters;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    private World() {
        setWidth(100);
        setHeight(100);
    }

    private static World instance = new World();

    public static World instance() {
        return instance;
    }

    public static void reset() {
        instance = new World();
    }
}
