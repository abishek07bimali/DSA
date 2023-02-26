import java.util.ArrayList;
import java.util.List;

//Given an array of even numbers sorted in ascending order and an integer k,
//        Find the k^th missing even number from provided array
//        Input a[] ={0, 2, 6, 18, 22} k=6
//        Output: 16 examples:
//        Explanation: Missing even numbers on the list are 4, 8, 10, 12, 14, 16, 20 and so on and kth
//        missing number is on 6th place of the list i.e. 16
public class Question8B {

    public static int findKthMissingEvenNumber(int[] a, int k) {
        // create a list to hold missing even numbers
        List<Integer> missingEvens = new ArrayList<>();
        // initialize a counter variable j to 0
        int j = 0;
        // loop through even numbers between the first and last element of the input array
        for (int i = a[0]; i < a[a.length - 1]; i += 2) {
            // if the current even number is equal to an element of the input array, increment j and continue to the next iteration
            if (j < a.length && a[j] == i) {
                j++;
                continue;
            }
            // add the current even number to the list of missing even numbers
            missingEvens.add(i);
            // if the size of the list of missing even numbers is equal to k, return the current even number
            if (missingEvens.size() == k) {
                return i;
            }
        }
        // if k exceeds the number of missing even numbers in the input array, return the (n + 2*k)th even number
        return a[a.length - 1] + 2 * k;
    }

    public static void main(String[] args) {
        int[] a = {0, 2, 6, 18, 22};
        int k = 6;
        System.out.println(findKthMissingEvenNumber(a, k));
    }
}

