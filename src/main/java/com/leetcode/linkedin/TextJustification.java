package com.leetcode.linkedin;

import java.util.ArrayList;
import java.util.List;

/**
 * Given an array of words and a width maxWidth, format the text such that each line has exactly maxWidth characters and is fully (left and right) justified.

 You should pack your words in a greedy approach; that is, pack as many words as you can in each line. Pad extra spaces ' ' when necessary so that each line has exactly maxWidth characters.

 Extra spaces between words should be distributed as evenly as possible. If the number of spaces on a line do not divide evenly between words, the empty slots on the left will be assigned more spaces than the slots on the right.

 For the last line of text, it should be left justified and no extra space is inserted between words.

 Note:

 A word is defined as a character sequence consisting of non-space characters only.
 Each word's length is guaranteed to be greater than 0 and not exceed maxWidth.
 The input array words contains at least one word.
 Example 1:

 Input:
 words = ["This", "is", "an", "example", "of", "text", "justification."]
 maxWidth = 16
 Output:
 [
 "This    is    an",
 "example  of text",
 "justification.  "
 ]
 Example 2:

 Input:
 words = ["What","must","be","acknowledgment","shall","be"]
 maxWidth = 16
 Output:
 [
 "What   must   be",
 "acknowledgment  ",
 "shall be        "
 ]
 Explanation: Note that the last line is "shall be    " instead of "shall     be",
 because the last line must be left-justified instead of fully-justified.
 Note that the second line is also left-justified becase it contains only one word.
 Example 3:

 Input:
 words = ["Science","is","what","we","understand","well","enough","to","explain",
 "to","a","computer.","Art","is","everything","else","we","do"]
 maxWidth = 20
 Output:
 [
 "Science  is  what we",
 "understand      well",
 "enough to explain to",
 "a  computer.  Art is",
 "everything  else  we",
 "do                  "
 ]

 * @author Santosh Manughala (SM030146).
 */
public class TextJustification {

    public static void main(String args[]) {
        String[] words = new String[] {"What","must","be","acknowledgment","shall","be"};
        int maxWidth = 16;

        List<String> result = fullJustify(words, maxWidth);
        for(String i : result) {
            System.out.println(i);
        }
    }

    // Time: O(n)
    private static List<String> fullJustify(String[] words, int maxWidth) {
        List<String> result = new ArrayList<String>();
        int start = 0;
        while (start < words.length) {
            /* find line ending */
            int txtlen = words[start].length();
            int end = start + 1;
            while (end < words.length && txtlen + words[end].length() + end - start <= maxWidth) {
                txtlen += words[end].length();
                end++;
            }
            /* create a line from words[start, end) */
            StringBuilder line = new StringBuilder();
            /* put first word */
            line.append(words[start++]);
            /* space length between words */
            int spaces = 1;
            int remains = 0;
			/* when more than one word and not last line */
            if (start < end && end < words.length) {
                spaces = (maxWidth - txtlen) / (end - start);
                remains = (maxWidth - txtlen) - spaces * (end - start);
            }
            /* put other words */
            while (start < end) {
                int n = spaces;
                if (remains-- > 0)
                    n++;
                while (n-- > 0)
                    line.append(' ');
                line.append(words[start++]);
            }
            /* pad ending spaces */
            while (line.length() < maxWidth)
                line.append(' ');
            result.add(line.toString());
        }
        return result;
    }
}
