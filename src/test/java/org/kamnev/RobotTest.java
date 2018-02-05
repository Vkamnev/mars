package org.kamnev;

import org.junit.Test;

import static java.util.Collections.singletonList;
import static org.junit.Assert.*;
import static org.kamnev.Direction.*;
import static org.kamnev.Robot.Command.*;

//TODO: replace all tests with parametrized
public class RobotTest {

    @Test
    public void checkRightRotationWorksCorrectly() {
        Coordinates startingPoint = new Coordinates(0,0);
        Boolean[][] world = new Boolean[1][1];
        Robot robot = new Robot(startingPoint, Direction.S, singletonList(R));


        robot.executeCommandsIn(world);
        assertEquals(W, robot.getDirection());
        robot.executeCommandsIn(world);
        assertEquals(N, robot.getDirection());
        robot.executeCommandsIn(world);
        assertEquals(E, robot.getDirection());
        robot.executeCommandsIn(world);
        assertEquals(S, robot.getDirection());
    }

    @Test
    public void checkLeftRotationWorksCorrectly() {
        Coordinates startingPoint = new Coordinates(0,0);
        Boolean[][] world = new Boolean[1][1];
        Robot robot = new Robot(startingPoint, Direction.S, singletonList(L));

        robot.executeCommandsIn(world);
        assertEquals(E, robot.getDirection());
        robot.executeCommandsIn(world);
        assertEquals(N, robot.getDirection());
        robot.executeCommandsIn(world);
        assertEquals(W, robot.getDirection());
        robot.executeCommandsIn(world);
        assertEquals(S, robot.getDirection());
    }
    @Test
    public void checkMovementWorksCorrectly() {
        Coordinates bottomLeft = new Coordinates(0,0);
        Coordinates upperRight = new Coordinates(1,1);
        Boolean[][] world = new Boolean[2][2];

        Robot robot = new Robot(bottomLeft, Direction.E, singletonList(F));
        robot.executeCommandsIn(world);
        assertEquals(1, robot.getCoordinates().getX());
        assertEquals(0, robot.getCoordinates().getY());

        robot = new Robot(bottomLeft, Direction.N, singletonList(F));
        robot.executeCommandsIn(world);
        assertEquals(0, robot.getCoordinates().getX());
        assertEquals(1, robot.getCoordinates().getY());

        robot = new Robot(upperRight, Direction.S, singletonList(F));
        robot.executeCommandsIn(world);
        assertEquals(1, robot.getCoordinates().getX());
        assertEquals(0, robot.getCoordinates().getY());

        robot = new Robot(upperRight, Direction.W, singletonList(F));
        robot.executeCommandsIn(world);
        assertEquals(0, robot.getCoordinates().getX());
        assertEquals(1, robot.getCoordinates().getY());
    }


    @Test
    public void checkRobotFailsFromTheEdgeCorrectly() {
        Coordinates startingPoint = new Coordinates(0,0);

        Boolean[][] world = new Boolean[1][1];
        Robot robot = new Robot(startingPoint, Direction.S, singletonList(F));
        robot.executeCommandsIn(world);
        assertLost(world[0][0], robot);

        world = new Boolean[1][1];
        robot = new Robot(startingPoint, Direction.N, singletonList(F));
        robot.executeCommandsIn(world);
        assertLost(world[0][0], robot);

        world = new Boolean[1][1];
        robot = new Robot(startingPoint, Direction.E, singletonList(F));
        robot.executeCommandsIn(world);
        assertLost(world[0][0], robot);

        world = new Boolean[1][1];
        robot = new Robot(startingPoint, Direction.W, singletonList(F));
        robot.executeCommandsIn(world);
        assertLost(world[0][0], robot);
    }

    @Test
    public void checkRobotWillNotMoveIfHeFeelsScent() {
        Coordinates startingPoint = new Coordinates(0,0);
        Boolean[][] world = new Boolean[2][2];
        world[0][0] = true;

        Robot robot = new Robot(startingPoint, Direction.S, singletonList(F));
        robot.executeCommandsIn(world);

        assertFalse(robot.isLost());
        assertTrue(world[0][0]);
        assertEquals(0, robot.getCoordinates().getX());
        assertEquals(0, robot.getCoordinates().getY());

    }

    private void assertLost(boolean isScent, Robot robot) {
        assertTrue(isScent);
        assertTrue(robot.isLost());
        assertEquals(0, robot.getCoordinates().getX());
        assertEquals(0, robot.getCoordinates().getY());
    }

}