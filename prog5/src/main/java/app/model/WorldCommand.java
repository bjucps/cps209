package app.model;

public abstract class WorldCommand {

    public abstract Critter execute();

    public abstract void undo();
}
