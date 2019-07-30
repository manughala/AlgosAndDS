package com.practice.strings;

/**
 * @author Santosh Manughala (SM030146).
 */
public class StringReversal {


    private static void reverseStringUsingByteArray(final String input) {
        // String input = "Hello World";

        // getBytes() method to convert string into bytes[].
        byte [] stringBytes = input.getBytes();
        byte [] result = new byte [stringBytes.length];

        // Store result in reverse order into the result byte[]
        for (int i = 0; i < stringBytes.length; i++) {
            result[i] = stringBytes[stringBytes.length - i - 1];
        }

        System.out.println("reversed string: " + new String(result));
    }

    private static void builtInReverseString(final String input) {
        System.out.println(new StringBuilder(input).reverse());
    }

    public static void main(String args[]) {
//        reverseStringUsingByteArray();


        String input = "Hello World";

        System.out.println("reverse words: " + reverseWords(input));
//        String[] strarray = input.split(" ");
//        String output = "";




//        for(String str: strarray) {
//            // getBytes() method to convert string into bytes[].
//            byte [] stringBytes = str.getBytes();
//            byte [] result = new byte [stringBytes.length];
//
//            // Store result in reverse order into the result byte[]
//            for (int i = 0; i < stringBytes.length; i++) {
//                result[i] = stringBytes[stringBytes.length - i - 1];
//
////                output = output + new String(result);
//            }
//            output = output + new String(result) + " ";
//        }
//        System.out.println(output);
    }


    private static String reverseStringUsingCharArray(final String input) {
        if (input.isEmpty() || input == null) {
            throw new IllegalStateException("invalid input");
        }

        if(input.length() < 2) {
            return input;
        }

        char[] stringCharArray = input.toCharArray();
        char[] outputCharArray = new char[stringCharArray.length];
        for(int i= 0; i<stringCharArray.length; i++) {
            outputCharArray[i] = stringCharArray[stringCharArray.length -1 -i];
        }

        return new String(outputCharArray);
    }

    public static String reverseWords(String str)
    {
        char[] chars = str.toCharArray();
        int wstart=0;
        for (int pos=0;;pos++)
        {
            if (pos < chars.length && chars[pos]!=' ')
            {
                continue;
            }
            for (int wend=pos-1; wend>wstart; ++wstart,--wend)
            {
                char t=chars[wstart];
                chars[wstart]=chars[wend];
                chars[wend]=t;
            }
            if (pos>=chars.length)
            {
                break;
            }
            wstart=pos+1;
        }
        return String.valueOf(chars);
    }


    // Write program for the following case Reverse string :
    // Input:- "Hello World" Output:-"olleH dlroW"
}
