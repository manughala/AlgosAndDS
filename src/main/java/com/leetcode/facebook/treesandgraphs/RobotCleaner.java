package com.leetcode.facebook.treesandgraphs;

import java.util.HashSet;
import java.util.Set;

/**
 * Given a robot cleaner in a room modeled as a grid.

 Each cell in the grid can be empty or blocked.

 The robot cleaner with 4 given APIs can move forward, turn left or turn right. Each turn it made is 90 degrees.

 When it tries to move into a blocked cell, its bumper sensor detects the obstacle and it stays on the current cell.

 Design an algorithm to clean the entire room using only the 4 given APIs shown below.

 interface Robot {
 // returns true if next cell is open and robot moves into the cell.
 // returns false if next cell is obstacle and robot stays on the current cell.
 boolean move();

 // Robot will stay on the same cell after calling turnLeft/turnRight.
 // Each turn will be 90 degrees.
 void turnLeft();
 void turnRight();

 // Clean the current cell.
 void clean();
 }
 Example:

 Input:
 room = [
 [1,1,1,1,1,0,1,1],
 [1,1,1,1,1,0,1,1],
 [1,0,1,1,1,1,1,1],
 [0,0,0,1,0,0,0,0],
 [1,1,1,1,1,1,1,1]
 ],
 row = 1,
 col = 3

 Explanation:
 All grids in the room are marked by either 0 or 1.
 0 means the cell is blocked, while 1 means the cell is accessible.
 The robot initially starts at the position of row=1, col=3.
 From the top left corner, its position is one row below and three columns right.
 Notes:

 The input is only given to initialize the room and the robot's position internally. You must solve this problem "blindfolded". In other words, you must control the robot using only the mentioned 4 APIs, without knowing the room layout and the initial robot's position.
 The robot's initial position will always be in an accessible cell.
 The initial direction of the robot will be facing up.
 All accessible cells are connected, which means the all cells marked as 1 will be accessible by the robot.
 Assume all four edges of the grid are all surrounded by wall.

 * @author Santosh Manughala (SM030146).
 */
public class RobotCleaner {


      // This is the robot's control interface.
      // You should not implement it, or speculate about its implementation
      interface Robot {
          // Returns true if the cell in front is open and robot moves into the cell.
          // Returns false if the cell in front is blocked and robot stays in the current cell.
          public boolean move();

          // Robot will stay in the same cell after calling turnLeft/turnRight.
          // Each turn will be 90 degrees.
          public void turnLeft();
          public void turnRight();

          // Clean the current cell.
          public void clean();
      }

      public static void main(String args[]) {
          cleanRoom(new Robot() {
              @Override
              public boolean move() {
                  return false;
              }

              @Override
              public void turnLeft() {

              }

              @Override
              public void turnRight() {

              }

              @Override
              public void clean() {

              }
          });
      }

      /*
      EXPLANATION COPIED FROM http://www.cnblogs.com/grandyang/p/9988250.html:

      The example given has information about the room and the starting position, but there is no code in it, and it is not intended to distract us.
      Think about it too. Do we have to know the user's room information when programming the sweeping robot? Of course not, the title also said that
      we blindly Blindfolded some, so just write it blindly. Since it is sweeping the floor, you must record which positions have been swept, so you
       must record the location information. Since you don't know the global location, you can only use the relative location information. The initial
        time is (0, 0), then add 1 minus 1 to the top, bottom, left, and right. The location information is placed in a HashSet, and for convenience,
        the 2D coordinates can be encoded into a string. We use recursive DFS, the initialization position is (0, 0), and then create an array of up,
        down, left and right directions, using a variable dir to fetch from it. In the recursive function, we first call the clean function on the
        starting position, because the title says that the starting position is reachable, that is, it is 1. Then you need to add the starting position
        to visited. Then we loop four times, because there are four directions, because the dir passed in by the recursive function is the direction of
        the last turn, then we add dir to i, in order to prevent the crossover, the balance of 4 is our new direction. Then, calculate the new position
        coordinates newX and newY. At this point, we must first judge that the visited does not contain this new location, that is, the new location has
        not been accessed. We also need to call the move function to determine whether the new location can be reached. If both conditions are met, we
        will call the recursive function on the new location. Note that after the recursive function call is completed, we have to go back to the state
        before the call, because the robot here is a reference number, it is globally common, so we have to go back to the previous state. Going back to
        the previous state is very simple, because the way the robot works is to go to the direction of advancement before moving forward. Then the way
         we step back is to rotate 180 degrees, go further, and then go back to the original direction. In the same way, we try it in order -> right ->
         down -> On the left, each time the robot has to turn to the right, because the move function can only detect whether the front can be reached,
         so we must let the robot go in the right direction in order to correctly call the move function. If the children's shoes used by the sweeping
         robot should have an impact, when there are obstacles on the current side, the robot disc will turn in the direction first, and then move on.
         The mechanism to be implemented here is similar. See the code below:
       */
    public static void cleanRoom(Robot robot) {
        // use 'x@y' mark visited nodes, where x,y are integers tracking the coordinates
        dfs(robot, new HashSet<>(), 0, 0, 0); // 0: up, 90: right, 180: down, 270: left
    }

    public static void dfs(Robot robot, Set<String> visited, int i, int j, int curDir) {
        String key = i + "@" + j;
        if (visited.contains(key)) return;
        visited.add(key);
        robot.clean();

        for (int n = 0; n < 4; n++) { // 4 directions
            if(robot.move()) { // can go directly. Find the (x, y) for the next cell based on current direction
                int x = i, y = j;
                switch(curDir) {
                    case 0: // go up, reduce i
                        x = i-1;
                        break;
                    case 90: // go right
                        y = j+1;
                        break;
                    case 180: // go down
                        x = i+1;
                        break;
                    case 270: // go left
                        y = j-1;
                        break;
                    default:
                        break;
                }

                dfs(robot, visited, x, y, curDir);
                backtrack(robot);
            }

            // turn to next direction
            robot.turnRight();
            curDir += 90;
            curDir %= 360;
        }
    }

    // go back to the starting position
    private static void backtrack(Robot robot) {
        robot.turnLeft();
        robot.turnLeft();
        robot.move();
        robot.turnRight();
        robot.turnRight();
    }
}
