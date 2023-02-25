import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

public class Question5A {

    public int[][] getKeyCoordinates(int[][] height) {
        // Store the start and end points of each rectangle in a TreeMap
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int[] rect : height) {
            map.put(rect[0], Math.max(map.getOrDefault(rect[0], 0), rect[2]));
            map.put(rect[1], 0);
        }

        // Keep track of the current height while iterating through the map
        int currHeight = 0;
        int[][] res = new int[map.size()][2];
        int i = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            int x = entry.getKey();
            int h = entry.getValue();
            if (h != currHeight) {
                res[i][0] = x;
                res[i][1] = currHeight = h;
                i++;
            }
        }

        // Return the key coordinates
        return Arrays.copyOfRange(res, 0, i);
    }

    public static void main(String[] args) {
        Question5A obj = new Question5A() ;
        int[][] height = {{1,4,10},{2,5,15},{5,8,12},{9,11,1},{11,13,15}};
        int[][] res = obj.getKeyCoordinates(height);
        System.out.println(Arrays.deepToString(res));
    }
}
