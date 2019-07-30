package com.practice.arrays;

/**
 * @author Santosh Manughala (SM030146).
 */
public class RemoveDuplicates {

    public static void main(String[] args) {
        int[] nums = new int[]{0,0,1,1,1,2,2,3,3,4};
        int len = removeDuplicates(nums);

        for (int i = 0; i < len; i++) {
            System.out.println(nums[i]);
        }
    }

//    public static int removeDuplicates(int[] nums) {
//
//        int temp = 1;
//
//        for(int i = 1; i < nums.length; i++) { //0 1 1 0 1 2 2 3 3 4
//               if(nums[i] != nums[i-1]) {
//                   nums[temp] = nums[i];
//                   temp++;
//               }
//
//        }
//
//        return temp;
//    }

    public static int removeDuplicates(int[] A) {
        int length=A.length;
        if(length==0 || length==1) return length;
        int i=1;
        for(int j=1; j<length; j++)
        {
            if(A[j]!=A[j-1])
            {
                A[i]=A[j];
                i++;

            }
        }
        if(i<length) A[i]='\0';
        return i;
    }



}
