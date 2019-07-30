package com.practice.strings;

/**
 * @author Santosh Manughala (SM030146).
 */
public class DifferentStrings {

    // Concatenates to String
    public static void stringConcat(String string) {
        string = string + "forgeeks";
    }

    // Concatenates to StringBuilder
    public static void stringBuilderConcat(StringBuilder stringBuilder) {
        stringBuilder.append("forgeeks");
    }

    // Concatenates to StringBuffer
    public static void StringBufferConcat(StringBuffer stringBuffer) {
        stringBuffer.append("forgeeks");
    }

    public static void main(String[] args)
    {
        // If a string is going to remain constant throughout the program, then use String class object because a String object is immutable.
        String string = "Geeks";

        // "string" is not changed due to the fact that String is immutable.
        // Altering the value of String creates another object and "string" in
        // stringConcat() stores reference of new String. References "string"
        // in main() and stringConcat() refer to different strings.
        stringConcat(string);
        System.out.println("String: " + string);

        // If a string can change (example: lots of logic and operations in the construction of the string) and will only be accessed from a single thread, using a StringBuilder is good enough.
        StringBuilder stringBuilder = new StringBuilder("Geeks");
        stringBuilderConcat(stringBuilder); // s2 is changed
        System.out.println("StringBuilder: " + stringBuilder);

        // If a string can change, and will be accessed from multiple threads, use a StringBuffer because StringBuffer is synchronous so you have thread-safety.
        StringBuffer stringBuffer = new StringBuffer("Geeks");
        StringBufferConcat(stringBuffer); // s3 is changed
        System.out.println("StringBuffer: " + stringBuffer);

        //Conversion between these types
        StringBuffer sbr = new StringBuffer(string);
        sbr.reverse();
        System.out.println("Reverse String: " + sbr);

        StringBuilder sbl = new StringBuilder(string);
        sbl.append("ForGeeks");
        System.out.println("Appended String: " + sbl);

        System.out.println("String builder converted to String: " + sbl.toString());
    }
}
