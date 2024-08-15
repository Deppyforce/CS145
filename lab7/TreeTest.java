/** Tanagorn Suksmran
 * CS 145 Lab 7
 * Binary Tree
 */

import java.security.SecureRandom;
import java.util.ArrayList;

public class TreeTest {
    public static void main(String[] args) {
        // initialize random number generator and array to log numbers
        SecureRandom rng = new SecureRandom();
        ArrayList<Integer> numbers = new ArrayList<>();

        // create new int tree instance
        Tree<Integer> tree = new Tree<>();

        // add a random number between 0 - 99 to tree 10 times
        for (int i = 0; i < 10; i++) {
            int number = rng.nextInt(100);
            tree.insertNode(number);
            numbers.add(number);
        }

        // print the inserted values in the order that they are generated
        System.out.println("Inserted the following values:");
        for (int i : numbers) {
            System.out.printf("%d ", i);
        }
        System.out.print("\n\n");

        // run three different traversal types
        System.out.println("Preorder traversal");
        tree.preorderTraversal();
        System.out.print("\n\n");

        System.out.println("Inorder traversal");
        tree.inorderTraversal();
        System.out.print("\n\n");

        System.out.println("Postorder traversal");
        tree.postorderTraversal();
        System.out.print("\n\n");

        // print a depiction of the tree
        tree.outputTree(0);
    }
}
