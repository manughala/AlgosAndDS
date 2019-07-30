package com.leetcode.amazon.codingassessment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * In Amazon’s sort center, a computer system decides what packages are to be loaded on what trucks. All rooms and spaces are abstracted into space units which is represented as an integer. For each type of truck, they have different space units. For each package, they will be occupying different space units. As a software development engineer in sort centers, you will need to write a method:

 Given truck space units and a list of product space units, find out exactly TWO products that fit into the truck. You will also implement an internal rule that the truck has to reserve exactly 30 space units for safety purposes. Each package is assigned a unique ID, numbered from 0 to N-1.

 Assumptions:
 You will pick up exactly 2 packages.
 You cannot pick up one package twice.
 If you have multiple pairs, select the pair with the largest package.

 Input:
 The input to the function/method consists of two arguments :
 truckSpace , an integer representing the truck space.
 packagesSpace , a list of integers representing the space units occupying by packages.

 Output:
 Return a list of integers representing the IDs of two packages whose combined space will leave exactly 30 space units on the truck.

 Example
 Input :
 truckSpace = 90
 packagesSpace = [1, 10, 25, 35, 60]
 Output :
 [2, 3]
 Explanation : Given a truck of 90 space units, a list of packages space units [1, 10, 25, 35, 60], Your method should select the third(ID-2) and fourth(ID-3) package since you have to reserve exactly 30 space units.

 * @author Santosh Manughala (SM030146).
 */
public class AmazonsSortCenter {

    public static void main(String args[]) {
        List<Integer> packagesSpace = new ArrayList<>();

        packagesSpace.add(1);
        packagesSpace.add(10);
        packagesSpace.add(25);
        packagesSpace.add(35);
        packagesSpace.add(60);

        System.out.println("Expected: [2, 3], Actual: " + getIds(packagesSpace, 90).toString());

        packagesSpace = new ArrayList<>();
        packagesSpace.add(45);
        packagesSpace.add(30);
        packagesSpace.add(45);
        packagesSpace.add(60);
        packagesSpace.add(90);

        System.out.println("Expected: [1, 3], Actual: " + getIds(packagesSpace, 120));

    }

    private static List<Integer> getIds(List<Integer> packagesSpace, int truckSpace) {
        List<Integer> result = new ArrayList<>();
        if(packagesSpace == null || packagesSpace.size() == 0) {
            return result;
        }

        Map<Integer, Integer> map = new HashMap<>();
        int target = truckSpace - 30;

        for(int i = 0; i < packagesSpace.size(); i++) {
            int complement = target - packagesSpace.get(i);

            if(map.containsKey(complement)) {
                int firstIdx = map.get(complement), secondIdx = i;

                if(result.isEmpty()) {
                    result.add(firstIdx);
                    result.add(secondIdx);
                    continue;
                }

                int prevFirstVal = packagesSpace.get(result.get(0)), prevSecondVal = packagesSpace.get(result.get(1));
                int currentFirstVal = packagesSpace.get(firstIdx), currentSecondVal = packagesSpace.get(secondIdx);

                if(Math.max(currentFirstVal, currentSecondVal) > Math.max(prevFirstVal, prevSecondVal)) {
                    result.add(0, firstIdx);
                    result.add(1, secondIdx);
                }
            }

            map.put(packagesSpace.get(i), i);
        }

        return result.subList(0, 2);
    }
}
