package practice.challenges;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Vector;


/**
 * Created by SANTOSH on 9/7/2015.
 */
public class TomographyOperation {

    public static void main(String[] args) throws IOException {
        System.out.println("Enter 2 numbers separated by space:");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String values = br.readLine();
        String[] split = values.split(" ");
        int m;
        int n;

        if(split.length != 2){
            throw new IllegalArgumentException("Expecting number of arguments to be 2, each separated with a space, but were: " + split.length + " arguments and the values being: " + values);
        }

        m = Integer.parseInt(split[0]);
        if(m < 1) {
            throw new IllegalArgumentException("The value of m (number of rows) must not be less than 1, but was " + m);
        }

        n = Integer.parseInt(split[1]);
        if(n < 1 || n > 1000) {
            throw new IllegalArgumentException("The value of n (number of columns) must be greater than 1 and less than 1000, but was " + n);
        }


        String rowsInput;
        System.out.println("Enter " + m + " numbers between 0 and " + n + " :");
        rowsInput = br.readLine();

        String[] rowsSplit = rowsInput.split(" ");
        Vector<Integer> rows = new Vector<>(m);

        if(rowsSplit.length != m) {
            throw new IllegalArgumentException("Expecting number of arguments to be " + m + ", each separated with a space, but were: " + values);
        }

        for(int i = 0; i < m; i++) {
            int temp = Integer.parseInt(rowsSplit[i]);
            if( temp < 0 || temp > n) {
                throw new IllegalArgumentException("The sum of each row in the matrix must be between 0 and " + n + ", but was " + temp);
            }
            rows.add(temp);
        }


        String columnsInput;
        System.out.println("Enter " + n + " numbers between 0 and " + m + " :");
        columnsInput = br.readLine();

        String[] columnsSplit = columnsInput.split(" ");
        Vector<Integer> columns = new Vector<>(n);

        if(columnsSplit.length != n) {
            throw new IllegalArgumentException("Expecting number of arguments to be " + n + ", each separated with a space, but were: " + values);
        }

        for(int i = 0; i < n; i++) {
            int temp = Integer.parseInt(columnsSplit[i]);
            if( temp < 0 || temp > n) {
                throw new IllegalArgumentException("The sum of each row in the matrix must be between 0 and " + m + ", but was " + temp);
            }
            columns.add(Integer.parseInt(columnsSplit[i]));
        }

        if(isExistsTomography(rows, columns)) {
            System.out.println("Yes");
            return;
        }

        System.out.println("No");
        return;
    }

    private static boolean isExistsTomography(Vector<Integer> rows, Vector<Integer> columns) {
        while(!rows.isEmpty()) {
            columns.sort(Comparator.<Integer>reverseOrder());
            int k = rows.lastElement();
            rows.remove(rows.indexOf(rows.lastElement()));
            if(k > columns.size()) return false;
            if(k == 0) return false;
            if(columns.get(k - 1) == 0) return false;
            for(int i=0; i < k; i++) {
                int j = columns.get(i);
                columns.remove(i);
                columns.add(i, --j);
            }
        }

        Iterator<Integer> iterator = columns.iterator();
        while(iterator.hasNext())
            if(iterator.next() != 0)
                return false;

        return true;
    }
}
