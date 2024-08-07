import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Recursion recursion = new Recursion();

        // sum test
        int n = 20;
        System.out.printf("sumN(%d) = %d\n", n, recursion.sumN(n));
        System.out.println();

        // pascal test
        ArrayList<ArrayList<Integer>> pascal = recursion.pascalsTriangle(8);
        recursion.display(pascal);
    }
}
