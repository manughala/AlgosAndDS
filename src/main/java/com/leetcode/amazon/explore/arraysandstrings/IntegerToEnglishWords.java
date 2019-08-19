package com.leetcode.amazon.explore.arraysandstrings;

/**
 * Convert a non-negative integer to its english words representation. Given input is guaranteed to be less than 231 - 1.

 Example 1:

 Input: 123
 Output: "One Hundred Twenty Three"
 Example 2:

 Input: 12345
 Output: "Twelve Thousand Three Hundred Forty Five"
 Example 3:

 Input: 1234567
 Output: "One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven"
 Example 4:

 Input: 1234567891
 Output: "One Billion Two Hundred Thirty Four Million Five Hundred Sixty Seven Thousand Eight Hundred Ninety One"

 * @author Santosh Manughala (SM030146).
 */
public class IntegerToEnglishWords {

    public static void main(String args[]) {
        System.out.println(integerToEnglishWords(123));
        System.out.println(integerToEnglishWords(12345));
        System.out.println(integerToEnglishWords(1234567));
        System.out.println(integerToEnglishWords(1234567891));
    }

    private static String integerToEnglishWords(int num) {
        if(num < 0 | num == 0) {
            return numToWord(0);
        }
        StringBuilder builder = new StringBuilder();

        if (num >= 1000000000) {
            int temp = num/1000000000;
            builder.append(translate(temp)).append(" Billion");

            num = num % 1000000000;
        }

        if (num >= 1000000) {
            int temp = num/1000000;
            builder.append(translate(temp)).append(" Million");

            num = num % 1000000;
        }

        if (num >= 1000) {
            int temp = num/1000;
            builder.append(translate(temp)).append(" Thousand");

            num = num % 1000;
        }

        if(num > 0) {
            builder.append(translate(num));
        }

        return builder.toString();
    }

    private static String translate(int num) {
        StringBuilder builder = new StringBuilder();

        if(num >= 100) {
            int temp = num/100;
            builder.append(numToWord(temp)).append(" Hundred");
            num = num % 100;
        }

        if(num > 0) {
            if(num > 0 && num <= 20) {
                builder.append(numToWord(num));
            } else {
                int temp = num / 10;
                builder.append(numToWord(temp * 10));

                num = num % 10;
                if(num > 0) {
                    builder.append(numToWord(num));
                }
            }
        }

        return builder.toString();
    }


    private static String numToWord(int num) {
        switch (num) {
            case 0: return " Zero";
            case 1: return " One";
            case 2: return " Two";
            case 3: return " Three";
            case 4: return " Four";
            case 5: return " Five";
            case 6: return " Six";
            case 7: return " Seven";
            case 8: return " Eight";
            case 9: return " Nine";
            case 10: return " Ten";
            case 11: return " Eleven";
            case 12: return " Tweleve";
            case 13: return " Thirteen";
            case 14: return " Fourteen";
            case 15: return " Fifteen";
            case 16: return " Sixteen";
            case 17: return " Seventeen";
            case 18: return " Eighteen";
            case 19: return " Nighteen";
            case 20: return " Twenty";
            case 30: return " Thirty";
            case 40: return " Forty";
            case 50: return " Fifty";
            case 60: return " Sixty";
            case 70: return " Seventy";
            case 80: return " Eighty";
            case 90: return " Ninety";
            default: return "";
        }
    }
}
