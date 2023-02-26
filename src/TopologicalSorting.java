import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

 public class TopologicalSorting {
    public ArrayList<String> findSchedule(String[] dependencies, String[][] pairs) {
        int depNo = dependencies.length;
        int pairNo = pairs.length;
        int[] index = new int[depNo];
        int[] indegree = new int[depNo];
        ArrayList<String> values = new ArrayList<String>(); // initialize ArrayList to store the sorted order

        // assign indexes to dependencies
        for (int k = 0; k < depNo; k++) {
            index[k] = k;
        }

        // calculate the indegree of each dependency
        for (int i = 0; i < depNo; i++) {
            for (int j = 0; j < pairNo; j++) {
                if (pairs[j][1].equals(dependencies[i])) {
                    indegree[i]++;
                }
            }
        }

        // create a queue to store dependencies with zero indegree
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < depNo; i++) {
            if (indegree[i] == 0) {
                queue.add(index[i]);
            }
        }

        boolean visited[] = new boolean[depNo];
        while (!queue.isEmpty()) {
            int val = queue.remove();
            visited[val] = true;
            values.add(dependencies[val]);
            for (int j = 0; j < pairNo; j++) {
                if (pairs[j][0].equals(dependencies[val])) {
                    int childIndex = Arrays.asList(dependencies).indexOf(pairs[j][1]);
                    indegree[childIndex]--;
                    if (indegree[childIndex] == 0 && !visited[childIndex]) {
                        queue.add(childIndex);
                    }
                }
            }
        }

        return values;
    }

    public static void main(String[] args) {
        String[] dependencies = {"A", "B", "C", "D", "E", "F"};
        String[][] pairs = {{"A", "D"}, {"F", "B"}, {"B", "D"}, {"F", "A"}, {"D", "C"}, {"E", "C"}};
        TopologicalSorting ts = new TopologicalSorting();
        ArrayList<String> schedule = ts.findSchedule(dependencies, pairs);
        System.out.println(schedule);
    }
}
