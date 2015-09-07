package practice.challenges;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


/**
 * Created by SANTOSH on 9/6/2015.
 */
public class TomographyOperation {

    public static void main(String[] args) throws IOException {
        Vector<Integer> v1 = new Vector<>(3);
        v1.add(0);
        v1.add(0);
        v1.add(3);
//        v1.add(2);
//        v1.add(3);
//        v1.add(2);
//        Vector<Integer> v2 = new Vector<>(4);
        Vector<Integer> v2 = new Vector<>(3);
        v2.add(0);
        v2.add(0);
        v2.add(3);
//        v2.add(1);
//        v2.add(1);
//        v2.add(3);
//        v2.add(2);
        System.out.println("HERE1");
        if(statisfiable(v1, v2)) {
            System.out.println("YEs");
            return;
        }

        System.out.println("NO");
        return;
//        System.out.println("Enter to values: x y");
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//
//        String values = br.readLine();
//        String[] split = values.split(" ");
//        int m;
//        int n;
//        if(split.length == 2)
//        {
//            m = Integer.parseInt(split[0]);
//            if(m < 1) {
//                throw new IllegalArgumentException("The value of m (number of rows) must not be less than 1");
//            }
//
//            n = Integer.parseInt(split[1]);
//            if(n < 1 || n > 1000) {
//                throw new IllegalArgumentException("The value of n (number of columns) must be greater than 1 and less than 1000");
//            }
//        }else{
//            throw new IllegalArgumentException("Expecting number of arguments to be 2, each separated with a space, but were: " + values);
//        }
//
//        System.out.println("m = " + m);
//        System.out.println("n = " + n);
//
//
//        String rowsInput;
//        System.out.println("Enter m numbers between 0 and " + n + " :");
//        rowsInput = br.readLine();
//        String[] rowsSplit = rowsInput.split(" ");
//        List<Integer> rows = new ArrayList<Integer>();
//
//        if(rowsSplit.length == m)
//        {
//            for(int i = 0; i < m-1; i++) {
//                int temp = Integer.parseInt(rowsSplit[i]);
//                if( temp < 0 || temp > n) {
//                    throw new IllegalArgumentException("The sum of each row in the matrix must be between 0 and " + n + ", but was " + temp);
//                }
//                rows.add(temp);
//            }
//        }else{
//            throw new IllegalArgumentException("Expecting number of arguments to be " + m + ", each separated with a space, but were: " + values);
//        }
//
//        String columnsInput;
//        System.out.println("Enter n numbers between 0 and " + m + " :");
//        columnsInput = br.readLine();
//        String[] columnsSplit = columnsInput.split(" ");
//        List<Integer> columns = new ArrayList<Integer>();
//
//        if(columnsSplit.length == n)
//        {
//            for(int i = 0; i < n-1; i++) {
//                int temp = Integer.parseInt(columnsSplit[i]);
//                if( temp < 0 || temp > n) {
//                    throw new IllegalArgumentException("The sum of each row in the matrix must be between 0 and " + m + ", but was " + temp);
//                }
//                columns.add(Integer.parseInt(columnsSplit[i]));
//            }
//        }else{
//            throw new IllegalArgumentException("Expecting number of arguments to be " + n + ", each separated with a space, but were: " + values);
//        }
//
//        if(tomographyPossible(rows, columns)) {
//            return "Yes";
//        }

    }

    private static boolean statisfiable(Vector<Integer> a, Vector<Integer> b) {
        System.out.println("HERE2");
        while(!a.isEmpty()) {
            b.sort(Comparator.<Integer>reverseOrder());
            int k = a.lastElement();
            a.remove(a.indexOf(a.lastElement()));
            if(k > b.size()) return false;
            if(k == 0) return false;
            if(b.get(k - 1) == 0) return false;
            for(int i=0; i < k; i++) {
                int j = b.get(i);
                b.remove(i);
                j--;
                b.add(i, j);
            }
        }

        Iterator<Integer> i = b.iterator();
        while(i.hasNext())
            if(i.next() != 0)
                return false;

        return true;
    }

    private static boolean tomographyPossible(List<Integer> rows, List<Integer> columns) {

        for(int i = 1; i < rows.size() - 1; i++){

            for(int j = 1; j < columns.size() - 1; j++) {

            }
        }


        return false;
    }
}
