import java.util.*;

public class questionthreea {
    public static int minCostToConnectDevices(int n, int[] modules, int[][] connections) {
        // Priority Queue to process the cheapest available connection/module cost first
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        
        // Track devices included in MST
        boolean[] inMST = new boolean[n];
        int totalCost = 0;
        int devicesConnected = 0;

        // Consider each module as an edge from a virtual node (Super source)
        for (int i = 0; i < n; i++) {
            pq.offer(new int[]{modules[i], i});
        }

        // Add connections to the priority queue
        Map<Integer, List<int[]>> graph = new HashMap<>();
        for (int[] conn : connections) {
            int u = conn[0] - 1;  // Convert to 0-based index
            int v = conn[1] - 1;
            int cost = conn[2];

            graph.computeIfAbsent(u, k -> new ArrayList<>()).add(new int[]{v, cost});
            graph.computeIfAbsent(v, k -> new ArrayList<>()).add(new int[]{u, cost});
        }

        // Process the priority queue (Prim’s Algorithm)
        while (!pq.isEmpty() && devicesConnected < n) {
            int[] current = pq.poll();
            int cost = current[0];
            int device = current[1];

            // If the device is already included in MST, skip it
            if (inMST[device]) continue;

            // Include this device in MST
            inMST[device] = true;
            totalCost += cost;
            devicesConnected++;

            // Add all connections of this device to the priority queue
            if (graph.containsKey(device)) {
                for (int[] neighbor : graph.get(device)) {
                    int nextDevice = neighbor[0];
                    int connectionCost = neighbor[1];

                    if (!inMST[nextDevice]) {
                        pq.offer(new int[]{connectionCost, nextDevice});
                    }
                }
            }
        }

        return totalCost;
    }

    public static void main(String[] args) {
        int n = 3;
        int[] modules = {1, 2, 2};
        int[][] connections = {{1, 2, 1}, {2, 3, 1}};
        System.out.println(minCostToConnectDevices(n, modules, connections)); // Output: 3
    }
}

