package com.leetcode.linkedin;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * On a single threaded CPU, we execute some functions.  Each function has a unique id between 0 and N-1.

 We store logs in timestamp order that describe when a function is entered or exited.

 Each log is a string with this format: "{function_id}:{"start" | "end"}:{timestamp}".  For example, "0:start:3" means the function with id 0 started at the beginning of timestamp 3.  "1:end:2" means the function with id 1 ended at the end of timestamp 2.

 A function's exclusive time is the number of units of time spent in this function.  Note that this does not include any recursive calls to child functions.

 Return the exclusive time of each function, sorted by their function id.



 Example 1:



 Input:
 n = 2
 logs = ["0:start:0","1:start:2","1:end:5","0:end:6"]
 Output: [3, 4]
 Explanation:
 Function 0 starts at the beginning of time 0, then it executes 2 units of time and reaches the end of time 1.
 Now function 1 starts at the beginning of time 2, executes 4 units of time and ends at time 5.
 Function 0 is running again at the beginning of time 6, and also ends at the end of time 6, thus executing for 1 unit of time.
 So function 0 spends 2 + 1 = 3 units of total time executing, and function 1 spends 4 units of total time executing.


 Note:

 1 <= n <= 100
 Two functions won't start or end at the same time.
 Functions will always log when they exit.

 * @author Santosh Manughala (SM030146).
 */
public class ExclusiveTimeOfFunctions {

    public static void main(String args[]) {
        List<String> logs = new ArrayList<>();
        logs.add("0:start:0");
        logs.add("1:start:2");
        logs.add("1:end:5");
        logs.add("0:end:6");

        int[] resultBruteForce = getExclusiveTimeBruteForce(2, logs);

        for(int result: resultBruteForce) {
            System.out.println(result);
        }

        int[] resultBestCase = getExclusiveTimeBestCase(2, logs);

        for(int result: resultBestCase) {
            System.out.println(result);
        }
    }

    private static int[] getExclusiveTimeBestCase(int n, List<String> logs) {
        if(n < 1 || n > 100 || logs == null || logs.isEmpty()) {
            return new int[]{};
        }

        int[] result = new int[n];
        String[] log = logs.get(0).split(":");

        Stack<Integer> stack = new Stack<>();
        stack.push(Integer.parseInt(log[0]));
        int prevTime = Integer.parseInt(log[2]), i = 1;

        while(i < logs.size()) {
            log = logs.get(i).split(":");
            if(log[1].equals("start")) {
                if(!stack.isEmpty()) {
                    result[stack.peek()] += Integer.parseInt(log[2]) - prevTime;
                }
                stack.push(Integer.parseInt(log[0]));
                prevTime = Integer.parseInt(log[2]);
            } else {
                result[stack.peek()] += Integer.parseInt(log[2]) - prevTime + 1;
                stack.pop();
                prevTime = Integer.parseInt(log[2]) + 1;
            }
            i++;
        }
        return result;
    }

    // Time: O(t)
    // Space: O(n)
    private static int[] getExclusiveTimeBruteForce(int n, List<String> logs) {
        if(n < 1 || n > 100 || logs == null || logs.isEmpty()) {
            return new int[]{};
        }

        int[] result = new int[n];
        String[] log = logs.get(0).split(":");

        Stack<Integer> stack = new Stack<>();
        stack.push(Integer.parseInt(log[0]));
        int time = Integer.parseInt(log[2]), i = 1;

        while(i < logs.size()) {
            log = logs.get(i).split(":");
            while(time < Integer.parseInt(log[2])) {
                result[stack.peek()]++;
                time++;
            }

            if(log[1].equals("start")) {
                stack.push(Integer.parseInt(log[0]));
            } else {
                result[stack.peek()]++;
                time++;
                stack.pop();
            }

            i++;
        }

        return result;
    }
}
