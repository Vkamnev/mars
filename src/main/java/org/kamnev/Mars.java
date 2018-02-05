package org.kamnev;

import java.util.*;

import static java.lang.Integer.valueOf;
import static java.util.stream.Collectors.toList;

public class Mars {

    private static final String DELIMITER = " ";
    private static final int MAX_COMMAND_LENGTH = 100;
    private static int MAX_WORLD_SIZE = 50;


    public static void main(String[] args) {

        System.out.println("Confirm your input by entering empty line");


        Scanner input = new Scanner(System.in);
        List<String> lines = new ArrayList<String>();
        readInput(input, lines);

        Iterator<String> it = lines.listIterator();
        Boolean[][] world = setupWorld(it.next().split(DELIMITER));

        List<Robot> robots = new ArrayList<>();
        while (it.hasNext()) {
            robots.add(initRobot(it.next(), it.next()));
        }

        robots.stream().peek(robot -> robot.executeCommandsIn(world)).forEach(Robot::status);

    }

    private static void readInput(Scanner input, List<String> lines) {
        String lineNew;

        while (input.hasNextLine()) {
            lineNew = input.nextLine();
            if (lineNew.isEmpty()) {
                break;
            }
            lines.add(lineNew);
        }
    }

    private static Boolean[][] setupWorld(String[] worldSize) {
        Integer xDimension = valueOf(worldSize[0])+1;
        Integer yDimension = valueOf(worldSize[1])+1;
        if (xDimension > MAX_WORLD_SIZE || yDimension > MAX_WORLD_SIZE) {
            throw new IllegalArgumentException("Max wold size is 50");
        }
        return new Boolean[xDimension][yDimension];
    }

    private static Robot initRobot(String startPos, String commands) {
        String[] position = startPos.split(DELIMITER);
        if (position.length != 3) {
            throw new IllegalArgumentException("Wrong positioning of the robot!");
    }
        if (commands.length()> MAX_COMMAND_LENGTH) {
            throw new IllegalArgumentException("List of commands can't exceed "+ MAX_COMMAND_LENGTH);
        }
        return new Robot(new Coordinates(Integer.valueOf(position[0]), Integer.valueOf(position[1])),
                Direction.valueOf(position[2]),
                Arrays.stream(commands.split("")).map(Robot.Command::valueOf).collect(toList()));
    }

}
