package com.practice.arrays;

import java.util.List;

/**
 * Return a list of elements where each element of the list represenst that x and y integer coordinates of the delivery destinations.
 *
 * Constraints:
 * numDeliveries <= numDestinations
 *
 * Notes
 * The plan starts from the trucks location [0,0]. The distance of the truck from a delivery destination (x,y)is the square root of x2+y2.
 * If there are lties then return any of the locationsas long as you satisfy returning X deliveries.
 *
 * Example:
 * input:
 * numDestinations: 3
 * alLocations: [[1,2],[3,4],[1,-1]]
 * numDeliveries: 2
 *
 * Output: [[1,-1],[1,2]]
 *
 * @author Santosh Manughala (SM030146).
 */
public class NumDeliveries {

    public static void main(String args[]) {
        int decimal=0,p=0;
        String n = "1010";


        System.out.println(Integer.parseInt(n,2));
        System.out.println(Integer.toBinaryString(10));
    }

    private List<List<Integer>> getNumOfDeliveries(List<List<Integer>> allLocations, int numDeliveries, int numDestinations) {

        for(List<Integer> location : allLocations) {
            findSquareRoot(location.get(0), location.get(1));
        }

        return allLocations;
    }

    private double findSquareRoot(int x, int y) {
        return Math.sqrt(Math.pow(x,2) + Math.pow(y,2));
    }
}
