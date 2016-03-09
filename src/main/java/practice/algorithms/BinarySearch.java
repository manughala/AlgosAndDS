package practice.algorithms;

/**
 * Implements binary search.
 *
 * Worst case time complexity : O(logn)
 * Best case time complexity: O(1)
 * Average case time complexity: O(logn)
 *
 * Created by Santosh Manughala.
 */
public class BinarySearch {

    /**
     * Iterative approach
     */
    public int binarySearchIterative(int[] input, int x) {
        int low = 0;
        int high = input.length - 1;
        int mid;
        while (low <= high) {
            mid = (low + high) / 2;
            if (input[mid] < x) {
                low = mid + 1;
            } else if (input[mid] > x) {
                high = mid - 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

    /**
     * Recursive approach
     */
    public int binarySearchRecursive(int[] input, int x, int low, int high) {
        if (low > high) {
            return -1;
        }
        int mid = (low + high) / 2;
        if (input[mid] < x) {
            return binarySearchRecursive(input, x, mid + 1, high);
        } else if (input[mid] > x) {
            return binarySearchRecursive(input, x, low, mid - 1);
        } else {
            return mid;
        }
    }

    public static void main(String[] args) {
        BinarySearch binarySearch = new BinarySearch();
        int[] input = {1, 3, 5, 7, 9};
        int target = 7;

        int locationIterative = binarySearch.binarySearchIterative(input, target);
        int locationRecursive = binarySearch.binarySearchRecursive(input, target, 0, input.length - 1);


        if (locationIterative != -1) {
            System.out.println(String.format("%d is located at position %d in the array", target, locationIterative));
        } else {
            System.out.println(String.format("%d is not located in the array", target));
        }

        if (locationRecursive != -1) {
            System.out.println(String.format("%d is located at position %d in the array", target, locationRecursive));
        } else {
            System.out.println(String.format("%d is not located in the array", target));
        }
    }

}
