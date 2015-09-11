package practice.challenges;

import java.io.IOException;
import java.util.Scanner;

/**
 * Created by SANTOSH on 9/7/2015.
 */
public class ReversedBinaryNumbers {

    public static void main(String args[]) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a number between 1 and 1000000000:");
        int decimal = scanner.nextInt();

        if(decimal < 1 || decimal > 1000000000) {
            throw new IllegalArgumentException("The entered number " + decimal + " is invalid, it must be between 1 and 1000000000.");
        }

        String binary = toBinary(decimal);
        String reverseBinary = new StringBuilder(binary).reverse().toString();
        int reversedValue = Integer.parseInt(reverseBinary, 2);

        System.out.println(reversedValue);
    }

    public static String toBinary(int n) {
        String binary = "";
        while (n > 0) {
            int rem = n % 2;
            binary = rem + binary;
            n = n / 2;
        }
        return binary;
    }

}
