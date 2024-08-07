/** Tanagorn Suksamran
 * CS 145 Lab 6
 * Recursion
 */

import java.util.ArrayList;

public class Recursion {
    /** Exercise 1
     * it works like a*b
     * you add the number a b times to its initial value
     * return itself when b == 1 as a * 1 = a
     * mystery(3, 4) = 3 + (3 + (3 + 3))
     * its significantly slower when a = 1 and b = 100 though because its still adding 100 times
     * so b should be the lower integer of the two inputs when using this
     */
    public int mystery(int a, int b) {
        // base case: if b = 1, return a
        if (b == 1) {
            return a;
        }
        
        // if not base case yet, add a to the final result and decrement b 
        else {
            return a + mystery(a, b - 1);
        }
    }
    
    /** Exercise 2 (original) */
    public int sum(int n) { 
        if (n == 0) {
            return 0;          
        }  

        else {    
            // the initial value of n we passed in never decreases
            // if n != 0 from the start it gets stuck in infinite loop
            // can be fixed by decrementing the n in the parameter every fn call
            return n + sum(n);   
        }
    }

    /** Exercise 2 (fixed) */
    public int sumN(int n) {
        if (n == 0) {
            return 0;
        }
        
        else {
            // reduce n in parameters so we actually approach base case
            return n + sumN(n - 1);
        }
    }

    /** pascals triangle function call for users */
    public ArrayList<ArrayList<Integer>> pascalsTriangle(int rows) {
        // do not allow invalid row amounts
        if (rows <= 0) {
            throw new IllegalArgumentException("rows must be at least 1.");
        }

        // create empty triangle then manipulate reference, easier to repeatedly add rows to og object
        ArrayList<ArrayList<Integer>> triangle = new ArrayList<>();
        generateRow(rows, triangle);

        return triangle;
    }

    /** generate a row */
    private ArrayList<Integer> generateRow(int row, ArrayList<ArrayList<Integer>> triangle) {
        // initialize currRow with 1 as first elem
        ArrayList<Integer> currRow = new ArrayList<>();
        currRow.add(1);

        // if first row, done
        if (row == 1) {
            triangle.add(currRow);
            return currRow;
        }

        // get prevRow for use to generate currRow
        ArrayList<Integer> prevRow;
        if (row <= triangle.size()) {
            // if already solved, look it up
            prevRow = triangle.get(row - 1);
        } else {
            // if not, recursively generate last row
            prevRow = generateRow(row - 1, triangle);
        }
        
        // start at second element and progressively move to the right
        generateValue(prevRow, currRow, 1);
        
        triangle.add(currRow);
        return currRow;
    }

    /** add value to currRow at index (keep doing until last element) */
    private void generateValue(ArrayList<Integer> prevRow, ArrayList<Integer> currRow, int index) {
        // last element
        if (index == prevRow.size()) {
            currRow.add(1);
            return;
        }

        // generate this one and call the next
        currRow.add(prevRow.get(index - 1) + prevRow.get(index));
        generateValue(prevRow, currRow, index + 1);
    }

    /** user call to display */
    public void display(ArrayList<ArrayList<Integer>> pascal) {
        display(pascal, 0);
    }

    /** real version (overloaded with index) */
    private void display(ArrayList<ArrayList<Integer>> pascal, int index) {
        // last line
        if (index == pascal.size() - 1) {
            System.out.println(pascal.get(index));
            return;
        }

        // print this line and call the next
        System.out.println(pascal.get(index));
        display(pascal, index + 1);
    }
}
