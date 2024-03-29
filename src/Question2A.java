/*Question 2
a)
You are given a 2D array containing hierarchical information about certain species,
with edge[i]=[xi,yi], where node xi is connected to xj. You are also provided an array of values associated
 with each species, such that value[i] reflects the ith nodes value. If the greatest common divisor of two
 values is 1, they are "relatively prime." Any other node on the shortest path from that node to the absolute
 parent node is an ancestor of certain species i. Return a list of nearest ancestors, where result[i] is the
 node i's nearest ancestor such that values[i] and value[result[i]] are both relative primes otherwise -1.

Input: values [3,2,6,6,4,7,12], edges= {{0,1}, {0,2}, {1,3}, {1,4}, {2,5}, {2,6}}
Output: {-1,0, -1, -1,0,2, -1}
*/

import java.util.*;

public class Question2A {
    // function to calculate greatest common divisor of two numbers
    public int gcd(int n1, int n2) {
        if (n2 == 0) {
            return n1;
        }
        return gcd(n2, n1 % n2);
    }

    // depth-first search function to traverse the tree
    public void dfs(int[] nums, LinkedList<Integer>[] tree, int depth, int node, boolean[] visited, int[] ans, Map<Integer,int[]> map, boolean[][] poss) {
        // if node has already been visited, return
        if(visited[node]) return;
        visited[node] = true;
        int ancestor = -1;
        int d = Integer.MAX_VALUE;
        // for each number from 1 to 50, check if it is relatively prime to the number at the current node
        // if it is and there exists a node with that number, update ancestor to be the nearest one
        // with that number as value
        for(int i = 1; i < 51; i++) {
            if(poss[nums[node]][i] && map.containsKey(i)) {
                if(depth - map.get(i)[0] <= d) {
                    d = depth - map.get(i)[0];
                    ancestor = map.get(i)[1];
                }
            }
        }
        // set the ancestor for the current node
        ans[node] = ancestor;
        // check if the current number already exists in the map
        int[] exist = (map.containsKey(nums[node])) ? map.get(nums[node]) :  new int[]{-1,-1};
        // add the current number and node to the map
        map.put(nums[node],new int[]{depth,node});
        // traverse the children of the current node
        for(int child : tree[node]) {
            if(visited[child]) continue;
            dfs(nums, tree, depth+1, child, visited, ans, map, poss);
        }
        // remove the current number and node from the map after visiting its children
        if(exist[0] != -1) map.put(nums[node], exist);
        else map.remove(nums[node]);

        return;
    }

    public int[] getCoprimes(int[] nums, int[][] edges) {
        // create a boolean array to store whether two numbers are relatively prime
        boolean[][] poss = new boolean[51][51];
        for(int i = 1; i < 51; i++) {
            for(int j = 1; j < 51; j++) {
                if(gcd(i,j) == 1) {
                    poss[i][j] = true;
                    poss[j][i] = true;
                }
            }
        }
        int n = nums.length;
        // create an adjacency list to represent the tree
        LinkedList<Integer>[] tree = new LinkedList[n];
        for(int i =0 ; i < tree.length; i++) tree[i] = new LinkedList<>();
        // add edges to the adjacency list
        for(int edge[] : edges) {
            tree[edge[0]].add(edge[1]);
            tree[edge[1]].add(edge[0]);
        }
        // create an array to store the nearest ancestors
        int[] ans = new int[n];
        Arrays.fill(ans, -1);
        ans[0] = -1;
        // create a map to store the node with its depth and number as value
        Map<Integer,int[]> map = new HashMap<>();
        // create a boolean array to store whether a node has been visited
        boolean[] visited = new boolean[n];
        dfs(nums, tree, 0, 0, visited, ans, map, poss);

        return ans;
    }

    public static void main(String[] args) {
        int[][] edges =  {{0,1}, {0,2}, {1,3}, {1,4}, {2,5}, {2,6}};
        int [] values = {3,2,6,6,4,7,12};
        Question2A listOfAncestors = new Question2A();
        int[] ans = listOfAncestors.getCoprimes(values,edges);
        List<Integer> answer= new ArrayList<Integer>();
        for (int i = 0;i<ans.length;i++) {
            answer.add(ans[i]);
        }
        System.out.println(answer);
    }


}
