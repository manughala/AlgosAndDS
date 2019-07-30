package com.leetcode.facebook.stringsandarrays;

/**
 * Validate if a given string can be interpreted as a decimal number.

 Some examples:
 "0" => true
 " 0.1 " => true
 "abc" => false
 "1 a" => false
 "2e10" => true
 " -90e3   " => true
 " 1e" => false
 "e3" => false
 " 6e-1" => true
 " 99e2.5 " => false
 "53.5e93" => true
 " --6 " => false
 "-+3" => false
 "95a54e53" => false

 Note: It is intended for the problem statement to be ambiguous. You should gather all requirements up front before implementing one. However, here is a list of characters that can be in a valid decimal number:

 Numbers 0-9
 Exponent - "e"
 Positive/negative sign - "+"/"-"
 Decimal point - "."
 Of course, the context of these characters also matters in the input.

 Update (2015-02-10):
 The signature of the C++ function had been updated. If you still see your function signature accepts a const char * argument, please click the reload button to reset your code definition.

 * @author Santosh Manughala (SM030146).
 */
public class ValidNumber {
    // THis seems to be more of if else question:
    // https://www.geeksforgeeks.org/check-given-string-valid-number-integer-floating-point/

    /*
    Copied from leet code
    Explanation: We start with trimming.

If we see [0-9] we reset the number flags.
We can only see . if we didn't see e or ..
We can only see e if we didn't see e but we did see a number. We reset numberAfterE flag.
We can only see + and - in the beginning and after an e
any other character break the validation.
At the and it is only valid if there was at least 1 number and if we did see an e then a number after it as well.

So basically the number should match this regular expression:

[-+]?(([0-9]+(.[0-9]*)?)|.[0-9]+)(e[-+]?[0-9]+)?
     */
    public boolean isNumber(String s) {
        s = s.trim();

        boolean pointSeen = false;
        boolean eSeen = false;
        boolean numberSeen = false;
        boolean numberAfterE = true;
        for(int i=0; i<s.length(); i++) {
            if('0' <= s.charAt(i) && s.charAt(i) <= '9') {
                numberSeen = true;
                numberAfterE = true;
            } else if(s.charAt(i) == '.') {
                if(eSeen || pointSeen) {
                    return false;
                }
                pointSeen = true;
            } else if(s.charAt(i) == 'e') {
                if(eSeen || !numberSeen) {
                    return false;
                }
                numberAfterE = false;
                eSeen = true;
            } else if(s.charAt(i) == '-' || s.charAt(i) == '+') {
                if(i != 0 && s.charAt(i-1) != 'e') {
                    return false;
                }
            } else {
                return false;
            }
        }

        return numberSeen && numberAfterE;
    }
}

