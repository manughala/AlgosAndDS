package practice.challenges;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by SANTOSH on 9/7/2015.
 */
public class PizzaDelivery {

    public static void main(String[] args) throws IOException {
        System.out.println("Enter the number of test cases: ");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        if(n < 1 || n > 20) {
            throw new IllegalArgumentException("The number of test cases (n) must be greater than 1 and less than 20, but was " + n);
        }

        StringBuilder output = new StringBuilder();

        for(int i = 1; i <= n; i++) {
            System.out.println("Enter 2 integers x and y separated by space:");

            String values = br.readLine();
            String[] split = values.split(" ");
            int x;
            int y;

            if(split.length != 2) {
                throw new IllegalArgumentException("Expecting number of arguments to be 2, each separated with a space, but were: " + split.length + " arguments and the values being :" + values);
            }

            x = Integer.parseInt(split[0]);
            if(x < 1 || x > 100) {
                throw new IllegalArgumentException("The value of x  must be greater than 1 and less than 100, but was " + x);
            }

            y = Integer.parseInt(split[1]);
            if(y < 1 || y > 1000) {
                throw new IllegalArgumentException("The value of y must be greater than 1 and less than 100, but was " + y);
            }


            System.out.println("Enter " + x + " integers each line separated by a space in " + y + " lines:");

            int[][] costMatrix = new int[x][y];

            for(int p = 0; p <= y-1; p++) {
                String rowValues = br.readLine();
                String[] rowsSplit = rowValues.split(" ");
                if(rowsSplit.length != x) {
                    throw new IllegalArgumentException("Expecting number of arguments to be " + x + ", each separated with a space, but were: " + rowsSplit.length);
                }

                for(int q = 0; q <= x-1; q++) {
                    costMatrix[q][p] = Integer.parseInt(rowsSplit[q]);
                    if(costMatrix[q][p] < 0 || costMatrix[q][p] > 1000) {
                        throw new IllegalArgumentException("The number of deliveries made to each street crossing must be greater than 0 and less than 1000, but was " +  costMatrix[q][p]);
                    }
                }
            }

            output.append(getOptimalDeliveryCost(costMatrix))
                  .append(" blocks")
                  .append("\n");
        }

        System.out.println(output);
    }

    public static int getOptimalDeliveryCost( int costMatrix[][]) {
        int optimalDeliveryCost = 1000;

        for(int p = 0; p < costMatrix.length; p++) {
            for(int q = 0; q < costMatrix[0].length; q++) {

                int totalDeliveryCost = 0;
                for(int i = 0; i < costMatrix.length; i++) {
                    for(int j = 0; j < costMatrix[0].length; j++) {
                        totalDeliveryCost = totalDeliveryCost + (Math.abs(p- i) + Math.abs(q-j)) * costMatrix[i][j];
                    }
                }

                if(optimalDeliveryCost > totalDeliveryCost) {
                    optimalDeliveryCost = totalDeliveryCost;
                }
            }
        }

        return optimalDeliveryCost;
    }
}
