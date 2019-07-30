package com.practice.strings;

public class Example
{
    public void reverseWordInMyString(String str)
    {
        String input = "tact ase";

        char[] inputChars = input.toCharArray();
        for(int i = 0; i<inputChars.length; i++) {
            for(int j =0; j<inputChars.length; j++) {
                if(i != j || inputChars[i] != inputChars[j]) {
                    swap(inputChars, i, j);
                }
//                System.out.println("i= " + i + "j= :" +j + "chars:" + new String(inputChars));
                System.out.println(inputChars);
            }
        }
    }

    public void swap(char[] charArray, int pos1, int pos2) {
        char temp = charArray[pos1];
        charArray[pos1] = charArray[pos2];
        charArray[pos2] = temp;
    }

    public static void main(String[] args)
    {
        Example obj = new Example();
        obj.reverseWordInMyString("Welcome to BeginnersBook");
//        obj.reverseWordInMyString("This is an easy Java Program");
    }
}