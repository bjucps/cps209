/*
 * This class contains the main method of the application
 */


import model.Critter;
import model.CritterType;
import model.World;

import java.util.Scanner;

public class App {

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Welcome to World Builder!");

        boolean done = false;
        while (!done) {
            String[] cmd = getCommand();
            try {
                switch (cmd[0]) {
                    case "create":
                        CritterType type = (cmd[1].equals("tracker")) ? CritterType.TRACKER : CritterType.WANDERER;
                        var newCritter =  World.instance().create(type);
                        System.out.println("Created critter: " + newCritter);
                        break;
                    case "move":
                        // move id toX toY
                        int id = Integer.parseInt(cmd[1]);
                        int toX = Integer.parseInt(cmd[2]);
                        int toY = Integer.parseInt(cmd[3]);
                        
                        Critter toMove = World.instance().find(id);
                        if (toMove != null) {
                            toMove.setX(toX);
                            toMove.setY(toY);
                            System.out.println("Moved critter: " + toMove);
                        }
                        
                        break;
                    case "animate":
                        System.out.println("\nMoving all critters a bit...");
                        for (var critter : World.instance().getCritters()) {
                            critter.updatePosition();
                        }
                    case "print":
                        System.out.println("\nCritters in the world:");
                        System.out.println("----------------------");
                        for (var critter : World.instance().getCritters()) {
                            System.out.println(critter);
                        }
                        break;
                    case "quit":
                        done = true;
                        break;
                    case "undo":
                        break;
                    default:
                        System.out.println("Unknown command " + cmd[0]);
                        break;
                }

            } catch (Exception e) {
                System.out.println("Problem executing command: " + e);

            }
        }
    }

    static String[] getCommand() {
        System.out.print("\nCommand: ");
        String cmdLine = scanner.nextLine();
        return cmdLine.split(" ");
    }
}
