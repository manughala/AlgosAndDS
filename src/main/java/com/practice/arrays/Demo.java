package com.practice.arrays;

import java.util.ArrayList;
import java.util.List;

/**
 * There is a colony of 8 cells arranged in a straight line where each day every cell competes with its adjacent cells(neighbour). Each day, for each cell, if its neighbours are both active or both inactive, the cell becomes inactive the next day,. otherwise itbecomes active the next day.

 Assumptions: The two cells on the ends have single adjacent cell, so the other adjacent cell can be assumsed to be always inactive. Even after updating the cell state. consider its pervious state for updating the state of other cells. Update the cell informationof allcells simultaneously.

 Write a fuction cellCompete which takes takes one 8 element array of integers cells representing the current state of 8 cells and one integer days representing te number of days to simulate. An integer value of 1 represents an active cell and value of 0 represents an inactive cell.

 Program:

 int* cellCompete(int* cells,int days)
 {
 //write your code here
 }
 //function signature ends

 Test Case 1:
 INPUT:
 [1,0,0,0,0,1,0,0],1
 EXPECTED RETURN VALUE:
 [0,1,0,0,1,0,1,0]

 Test Case 2:
 INPUT:
 [1,1,1,0,1,1,1,1,],2
 EXPECTED RETURN VALUE:
 [0,0,0,0,0,1,1,0]

 * @author Santosh Manughala (SM030146).
 */
public class Demo {

        // METHOD SIGNATURE BEGINS, THIS METHOD IS REQUIRED
        public static List<Integer> cellCompete(int[] states, int days)
        {
            if(states.length < 8) {
                System.out.println("Need 8 cells to perform the algorithm");
                throw new IllegalArgumentException("Need 8 cells to perform the algorithm");
            }

            int[] statesOutput = new int[states.length];
            int j = 0;

            for(int d = 0; d < days; d++) {
                for(int i=0; i< states.length; i++) {
                    if(i ==0) {
                        if(states[i+1] == 0) {
                            statesOutput[i] = 0;
                        } else {
                            statesOutput[i] = 1;
                        }
                    } else if(i == states.length-1) {
                        if(states[i-1] == 0) {
                            statesOutput[i] = 0;
                        } else {
                            statesOutput[i] = 1;
                        }
                    } else {
                        if(states[i+1] == states[i-1]) {
                            statesOutput[i] = 0;
                        } else {
                            statesOutput[i] = 1;
                        }
                    }
                }
                states = statesOutput;
            }

            List<Integer> statesOutputList = new ArrayList<>();
            for(int state: statesOutput) {
                statesOutputList.add(state);
            }

            return statesOutputList;
        }
        // METHOD SIGNATURE ENDS

    public static void main(String[] args) {
        List<Integer> statesOutput = cellCompete(new int[] {1,1,1,0,1,1,1,1}, 2);
        System.out.println(statesOutput);
    }

}
