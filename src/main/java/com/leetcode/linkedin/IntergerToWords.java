package com.leetcode.linkedin;

/**
 * @author Santosh Manughala (SM030146).
 */
public class IntergerToWords {
    public String numberToWords(int num) {
        if(num < 0) {
            return "Zero";
        }

        if(num == 0) {
            return numToWord(num);
        }

        StringBuilder builder = new StringBuilder();

        if(num >= 1000000000) {
            int temp = num / 1000000000;
            builder.append(translate(temp) + " Billion");

            num = num % 1000000000;
        }

        if(num >= 1000000) {
            int temp = num / 1000000;
            builder.append(translate(temp) + " Million");

            num = num % 1000000;
        }

        if(num >= 1000) {
            int temp = num / 1000;
            builder.append(translate(temp) + " Thousand");

            num = num % 1000;
        }

        if(num > 0) {
            builder.append(translate(num));
        }
        return builder.toString().trim();
    }

    private String translate(int num) {
        StringBuilder builder = new StringBuilder();

        if(num >= 100) {
            int temp = num / 100;
            builder.append(" " + numToWord(temp) + " Hundred");

            num = num % 100;
        }

        if(num > 0) {
            if(num > 0 && num <= 20) {
                builder.append(" " + numToWord(num));
            } else {
                int temp = num / 10;
                builder.append(" " + numToWord(temp * 10));

                num = num % 10;
                if(num > 0) {
                    builder.append(" " + numToWord(num));
                }
            }
        }

        return builder.toString();
    }

    private String numToWord(int num) {
        switch (num) {
            case 0: return "Zero";
            case 1: return "One";
            case 2: return "Two";
            case 3: return "Three";
            case 4: return "Four";
            case 5: return "Five";
            case 6: return "Six";
            case 7: return "Seven";
            case 8: return "Eight";
            case 9: return "Nine";
            case 10: return "Ten";
            case 11: return "Eleven";
            case 12: return "Twelve";
            case 13: return "Thirteen";
            case 14: return "Fourteen";
            case 15: return "Fifteen";
            case 16: return "Sixteen";
            case 17: return "Seventeen";
            case 18: return "Eighteen";
            case 19: return "Nineteen";
            case 20: return "Twenty";
            case 30: return "Thirty";
            case 40: return "Forty";
            case 50: return "Fifty";
            case 60: return "Sixty";
            case 70: return "Seventy";
            case 80: return "Eighty";
            case 90: return "Ninety";
            default: return "";
        }
    }
}
