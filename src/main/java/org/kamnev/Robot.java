package org.kamnev;

import java.util.List;
import java.util.function.BiConsumer;

public class Robot {

    private static final String LOST = "LOST";
    private List<Command> commands;
    private Coordinates coordinates;
    private Direction direction;
    private boolean lost;

    public Robot(Coordinates coordinates, Direction direction, List<Command> commands) {
        this.commands = commands;
        this.direction = direction;
        this.coordinates = coordinates;
    }


    public void executeCommandsIn(Boolean[][] world) {
        this.commands.stream().forEach(c -> c.execute(this, world));
    }

    public void status() {
        System.out.println(String.format("%d %d %s %s", coordinates.getX(), coordinates.getY(), direction.name(), (lost ? LOST : "")));
    }


    public Coordinates getCoordinates() {
        return coordinates;
    }

    public Direction getDirection() {
        return direction;
    }

    public boolean isLost() {
        return lost;
    }


    //not used currently
    public void setCommands(List<Command> commands) {
        this.commands = commands;
    }

    public enum Command {

        L,
        R,
        F;
        private BiConsumer<Robot, Boolean[][]> instruction;

        static {
            L.instruction = (robot, world) -> robot.direction = robot.direction.turnLeft();
            R.instruction = (robot, world) -> robot.direction = robot.direction.turnRight();
            F.instruction = (robot, world) -> {
                Coordinates newCoordinates = robot.coordinates.newCoordinatesAfterMoveIn(robot.direction);
                if (!newCoordinates.beyondTheWorld(world.length-1, world[0].length-1)) {
                    robot.coordinates = newCoordinates;
                } else if (world[robot.coordinates.getX()][robot.coordinates.getY()] == null) {
                    world[robot.coordinates.getX()][robot.coordinates.getY()] = true;
                    robot.lost = true;
                }

            };
        }

        public void execute(Robot robot, Boolean[][] world) {
            if (!robot.lost) {
                instruction.accept(robot, world);
            }
        }


    }
}
