package com.practice.arrays;

/**
 * Given an array, rotate the array to the right by k steps, where k is non-negative.

 Input: [1,2,3,4,5,6,7] and k = 3



 Output: [5,6,7,1,2,3,4]
 Explanation:
 rotate 1 steps to the right: [7,1,2,3,4,5,6]
 rotate 2 steps to the right: [6,7,1,2,3,4,5]
 rotate 3 steps to the right: [5,6,7,1,2,3,4]

 Input: [-1,-100,3,99] and k = 2
 Output: [3,99,-1,-100]
 Explanation:
 rotate 1 steps to the right: [99,-1,-100,3]
 rotate 2 steps to the right: [3,99,-1,-100]
 * @author Santosh Manughala (SM030146).
 */
public class RotateArray {

    public static void main(String args[]) {
        int[] input = {1,2};
        int k = 3;

        System.out.println("gcd " + gcd(7, 3));

        leftRotate(input, k, input.length);
        System.out.println("left rotate: ");
        printArray(input, input.length);

//        int[] input1 = {1,2,3,4,5,6,7};
//        rightRotate(input1, k, input1.length);
//        System.out.println("right rotate: ");
//        printArray(input1, input1.length);
    }

    private static void rotateArray(int[] input, int k) {
        int len = input.length;

        for(int i = 0; i < k; i++) {
            int temp = input[0];
            for(int j= 0; j<len-1; j++) {
                input[j] = input[j+1];
            }
            input[len-1] = temp;
        }
    }


    /*Function to left rotate arr[] of siz n by d*/
    static void leftRotate(int arr[], int d, int n) {
        System.out.println(gcd(4, 2));
        if(n <= d) {
            d = d - n;
        }

            int k, j, temp;
        for(int i = 0; i < gcd(n, d); i++) {
            j = i;
            temp = arr[j];

            while(true) {
                k = j+d;
                if(k >= n) {
                    k = k-n;
                }

                if(k==i) {
                    break;
                }

                arr[j] = arr[k];
                j = k;
            }

             arr[j] = temp;


        }
    }

    /*Function to right rotate arr[] of siz n by d*/
    static void rightRotate(int arr[], int d, int n) {
                int i,j,k,temp; // 1234 -> 3234  -> 3214 -> 3414 -> 3412
        for(i = 0; i<gcd(n,d); i++) { // 1234567

            j = n-d+i; // j = 5
            temp = arr[j]; // temp = 6
            while(true) {
                k = j-d; // j = 2 k = 0

                if(k<0) {
                    k = n+k;
                }
                arr[j] = arr[k];
                j = k;

                if(k == i){
                    break;
                }
            }

            arr[j] = temp;
        }
    }

    /*UTILITY FUNCTIONS*/

    /* function to print an array */
    static void printArray(int arr[], int size)
    {
        for (int i = 0; i < size; i++)
            System.out.print(arr[i] + " ");
    }

    static int gcd(int a, int b) {
        if (b == 0) {
            return a;
        } else {
            return gcd(b, a%b);
        }

    }


    //BEST SOLUTION
    private static void rotateArrayRec(int[] array, int d) {

        //right rotate
//        rotate(array, 0, array.length-1);
//        rotate(array, 0, d-1);
//        rotate(array, d, array.length-1);

        //left rotate
        rotate(array, 0, array.length-1);
        rotate(array, 0, array.length-d-1);
        rotate(array, array.length-d, array.length-1);

        for(int num : array) {
            System.out.println(num);
        }
    }

    private static void rotate(int[] array, int start, int end) {
        int l = array.length;
        while(start < end) {
            int temp = array[start];
            array[start] = array[end];
            array[end] = temp;
            start++;
            end--;
        }
    }


    /*
    COPY

    private static void addBinary(String a, String b) {

        // Initialize result
        String result = "";

        // Initialize digit sum
        int s = 0;

        // Travers both strings starting
        // from last characters
        int i = a.length() - 1, j = b.length() - 1;
        while (i >= 0 || j >= 0 || s == 1)
        {

            // Comput sum of last
            // digits and carry // - '0'
            s += ((i >= 0)? a.charAt(i) - '0' : 0);
            s += ((j >= 0)? b.charAt(j) - '0' : 0);

            // If current digit sum is
            // 1 or 3, add 1 to result
            result = (char)(s % 2 + '0') + result;

            // Compute carry
            s /= 2;

            // Move to next digits
            i--; j--;
        }
        System.out.println("result = " + result);

    }

    private static void addBinary(long binary1, long binary2) {

        // 1011, 101  -> 10000
        int i = 0, remainder = 0;
        int[] sum = new int[20];

        while (binary1 != 0 || binary2 != 0)
        {
            sum[i++] = (int)((binary1 % 10 + binary2 % 10 + remainder) % 2);// 1 + 1+ 0 -> 0 ; 1+ 0 + 1 -> 0; 0 + 1 + 1 -> 0; 1 + 0 + 1 -> 1
            remainder = (int)((binary1 % 10 + binary2 % 10 + remainder) / 2); // 1 + 1 + 0 -> 1 ; 1 + 0 + 1 -> 1; 0 + 1 + 1 -> 1; 1 + 0 + 1 -> 1
            binary1 = binary1 / 10;
            binary2 = binary2 / 10;
        }
        if (remainder != 0) {
            sum[i++] = remainder;
        }
        --i;
        System.out.print("Sum of two binary numbers: ");
        String result="";
        while (i >= 0) {
            result = result + sum[i--];
        }
        System.out.print("\n");
        System.out.print(result);
    }
     */

}