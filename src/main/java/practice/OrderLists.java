package practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @author Santosh Manughala (SM030146).
 */
public class OrderLists {

    public static void main(String args[]) {
        String s1 = "br8 eat nim did";
        String s2 = "b4 xi me nu";
        String s3 = "r1 box ape bitt";

        List<String> orders = new ArrayList<>();
        orders.add(s1);
        orders.add(s2);
        orders.add(s3);

        prioritize(orders);

        for(String order : orders) {
            System.out.println(order);
        }
    }

    private static void prioritize(List<String> orders) {

        orders.sort((firstWord, secondWord) -> {
            String[] s1Split = firstWord.split(" ");
            String[] s2Split = secondWord.split(" ");

            int index = 1;

            while(index < s1Split.length && index < s2Split.length) {
                boolean s1Complete = index < s1Split.length;
                boolean s2Complete = index < s2Split.length;

                if (s1Complete && !s2Complete) {
                    return -1;
                }

                if (!s1Complete && s2Complete) {
                    return  1;
                }

                if (s1Split[index].compareTo(s2Split[index]) == 0) {
                    index++;
                } else {
                    return s1Split[index].compareTo(s2Split[index]);

                }
                index++;
            }

            return Integer.parseInt(s1Split[0]) - Integer.parseInt(s2Split[0]);
        });
    }
}
