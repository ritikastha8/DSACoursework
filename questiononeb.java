import java.util.HashSet;
import java.util.PriorityQueue;

public class questiononeb {
    
    static class Pair {
        int product;
        int index1;
        int index2;

        Pair(int product, int index1, int index2) {
            this.product = product;
            this.index1 = index1;
            this.index2 = index2;
        }
    }

    public static int findKthLowestCombinedReturn(int[] returns1, int[] returns2, int k) {
        PriorityQueue<Pair> minHeap = new PriorityQueue<>((a, b) -> Integer.compare(a.product, b.product));
        HashSet<String> visited = new HashSet<>();

        // Start with the product of the first elements
        minHeap.offer(new Pair(returns1[0] * returns2[0], 0, 0));
        visited.add("0-0");

        // Extract the smallest k times
        for (int i = 0; i < k; i++) {
            Pair current = minHeap.poll();

            // When we pop the kth smallest product, return it
            if (i == k - 1) {
                return current.product;
            }
            // Next in the first array
            if (current.index1 + 1 < returns1.length) {
                String nextIndex = (current.index1 + 1) + "-" + current.index2;
                if (!visited.contains(nextIndex)) {
                    minHeap.offer(new Pair(returns1[current.index1 + 1] * returns2[current.index2], 
                    current.index1 + 1, current.index2));
                    visited.add(nextIndex);
                }
            }

            // Next in the second array
            if (current.index2 + 1 < returns2.length) {
                String nextIndex = current.index1 + "-" + (current.index2 + 1);
                if (!visited.contains(nextIndex)) {
                    minHeap.offer(new Pair(returns1[current.index1] * returns2[current.index2 + 1], 
                    current.index1, current.index2 + 1));
                    visited.add(nextIndex);
                }
            }
        }
        return -1; // This line should never be reached if k is valid
    }

    public static void main(String[] args) {
        int[] returns1 = {2, 5};
        int[] returns2 = {3, 4};
        int k = 2;
        System.out.println(findKthLowestCombinedReturn(returns1, returns2, k));  // Output: 8

        returns1 = new int[]{-4, -2, 0, 3};
        returns2 = new int[]{2, 4};
        k = 6;
        System.out.println(findKthLowestCombinedReturn(returns1, returns2, k));  // Output: 0
    }
}











