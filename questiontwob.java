public class questiontwob {
    public static int[] findClosestPair(int[] x_coords, int[] y_coords) {
        int n = x_coords.length; // Number of points
        int minDistance = Integer.MAX_VALUE; // Initialize minimum distance to max value
        int[] closestPair = {-1, -1}; // To store the indices of the closest pair

        // Iterate over all unique pairs of points (i, j) where i < j
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                // Calculate the Manhattan distance
                int distance = Math.abs(x_coords[i] - x_coords[j]) + Math.abs(y_coords[i] - y_coords[j]);

                // Update minimum distance and closest pair
                if (distance < minDistance || 
                    (distance == minDistance && (i < closestPair[0] || (i == closestPair[0] && j < closestPair[1])))) {
                    minDistance = distance;
                    closestPair[0] = i;
                    closestPair[1] = j;
                }
            }
        }

        return closestPair;
    }

    public static void main(String[] args) {
        int[] x_coords = {1, 2, 3, 2, 4};
        int[] y_coords = {2, 3, 1, 2, 3};

        int[] result = findClosestPair(x_coords, y_coords);
        System.out.println("Closest pair of indices: [" + result[0] + ", " + result[1] + "]"); // Output: [0, 3]
    }
}

