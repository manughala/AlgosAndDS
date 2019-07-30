package com.leetcode.facebook.design;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Read N Characters Given Read4


 * Given a file and assume that you can only read the file using a given method read4, implement a method to read n characters.



 Method read4:

 The API read4 reads 4 consecutive characters from the file, then writes those characters into the buffer array buf.

 The return value is the number of actual characters read.

 Note that read4() has its own file pointer, much like FILE *fp in C.

 Definition of read4:

 Parameter:  char[] buf
 Returns:    int

 Note: buf[] is destination not source, the results from read4 will be copied to buf[]
 Below is a high level example of how read4 works:

 File file("abcdefghijk"); // File is "abcdefghijk", initially file pointer (fp) points to 'a'
 char[] buf = new char[4]; // Create buffer with enough space to store characters
 read4(buf); // read4 returns 4. Now buf = "abcd", fp points to 'e'
 read4(buf); // read4 returns 4. Now buf = "efgh", fp points to 'i'
 read4(buf); // read4 returns 3. Now buf = "ijk", fp points to end of file


 Method read:

 By using the read4 method, implement the method read that reads n characters from the file and store it in the buffer array buf. Consider that you cannot manipulate the file directly.

 The return value is the number of actual characters read.

 Definition of read:

 Parameters:	char[] buf, int n
 Returns:	int

 Note: buf[] is destination not source, you will need to write the results to buf[]


 Example 1:

 Input: file = "abc", n = 4
 Output: 3
 Explanation: After calling your read method, buf should contain "abc". We read a total of 3 characters from the file, so return 3. Note that "abc" is the file's content, not buf. buf is the destination buffer that you will have to write the results to.
 Example 2:

 Input: file = "abcde", n = 5
 Output: 5
 Explanation: After calling your read method, buf should contain "abcde". We read a total of 5 characters from the file, so return 5.
 Example 3:

 Input: file = "abcdABCD1234", n = 12
 Output: 12
 Explanation: After calling your read method, buf should contain "abcdABCD1234". We read a total of 12 characters from the file, so return 12.
 Example 4:

 Input: file = "leetcode", n = 5
 Output: 5
 Explanation: After calling your read method, buf should contain "leetc". We read a total of 5 characters from the file, so return 5.


 Note:

 Consider that you cannot manipulate the file directly, the file is only accesible for read4 but not for read.
 The read function will only be called once for each test case.
 You may assume the destination buffer array, buf, is guaranteed to have enough space for storing n characters.

 * @author Santosh Manughala (SM030146).
 */
public class ReadNCharsGivenRead4 {

    public static void main(String args[]) throws IOException {
        System.out.println(read(new char[100], 1));
    }

    static class ListNode {
        int val;
        ListNode next;
        ListNode(int val) {
            this.val = val;
        }
    }

    private static void inputLinkedListTest() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter the number of values in a linked lists: ");
        int num = Integer.parseInt(reader.readLine());

        System.out.println("Now enter values: ");
        ListNode list = new ListNode(0), head = list;

        for(int i = 0; i < num; i++) {
            int val = Integer.parseInt(reader.readLine());
            ListNode temp = new ListNode(val);
            list.next = temp;
            list = list.next;
        }

        System.out.println("EXIT");
        head = head.next;

        while(head != null) {
            System.out.println(head.val);
            head = head.next;
        }

    }



    // NOTE: this wont run locally as the read4 method is not applicable locally. we can run on leetcode.
    // TIme: O(n) - for each input n, we read the 4 chars each time, so n/4.. consider worse case 1000 /4 still n/4 -> linear
    // Space:O(1) - considering the local buff very small since its only 4 chars
    private static int read(char[] buf, int n) {
        char[] localBuffer = new char[4];
        int total = 0;

        while(true) {
            int localCount = read4(localBuffer);
            if(localCount == 0) {
                break;
            }

            for(int i = 0; i < localCount; i++) {
                if(total == n) {
                    break;
                }

                buf[total++] = localBuffer[i];
            }
        }
        return total;
    }

    private static int read4(char[] localBuffer) {
        return 1;
    }

    private static void bufferedFileReaderTest() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(new File("/Users/sm030146/Desktop/helpmar15.txt")));

        String line = "";
        while((line = reader.readLine()) != null) {
            System.out.println(line);
        }

        reader.close();

    }

    private static void scannerReadTest() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("enter a number: ");
        int num = scanner.nextInt();

        System.out.println("enter " + num + " numbers:");
        for(int i = 0; i < num; i++) {
            int input = scanner.nextInt();
            System.out.println("input " + i + " : " + input);
        }

        scanner.close();
    }

    private static void bufferReaderTest() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in), 2);
        System.out.println("enter a number: ");
        int num = Integer.parseInt(reader.readLine());

        System.out.println("enter " + num + " numbers:");
        for(int i = 0; i < num; i++) {
            String input = reader.readLine();
            System.out.println("input " + i + " : " + input);
        }

        reader.close();

    }

}
