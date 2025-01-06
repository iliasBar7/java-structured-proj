package gr.aueb.cf.projects;

/**
 * Kadane's algorithm is to traverse over the array from left to right and for each element ,
 * find the maximum sum among all subarrays ending at that element .
 * The result will be the maximum of all these values.
 * Time complexity O(n) since we traversing the array only one time by using for loop.
 * Printing the subarray is also O(n) in the worst case,
 * but it’s a separate operation and doesn't affect the overall time complexity for Kadane’s algorithm, which remains O(n).
 */
public class Project02 {

    static int globalMax;
    static int startIndex;
    static int endIndex;


    public static void main(String[] args) {

        int[] arr = {-2, 1, -3, 4, -1, 2, 1, -5, 4};


            //calculate the maximum sum subarray
            System.out.println("The maximum sum of contiguous subarray is: " + maxSubArraySum(arr) );

            //call the new method to print the subarray
            printSubarray(arr);

    }

    /**
     *It calculates the maximum sum subarray using Kadane's algorithm and updates the startIndex and endIndex as it processes the array.
     * @param arr
     * @return updates globalmax
     */
    public static int maxSubArraySum(int[] arr) {
        int localMax = arr[0];
        globalMax = arr[0];

        if (arr == null) return -1;

        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > localMax + arr[i]) {
                localMax = arr[i];
                startIndex = i; // Update start index when starting a new subarray
            } else {
                localMax += arr[i];
            }

            if (localMax > globalMax) {
                globalMax = localMax;
                endIndex = i; // Update end index for the current max sum subarray
            }
        }

        return globalMax;
    }

    /**
     * This method prints the subarray based on the startIndex and endIndex
     * obtained from the maxSubArraySum method.
     */
    public static void printSubarray(int[] arr) {
        System.out.print("The subarray is: ");
        for (int i = startIndex; i <= endIndex; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }
}
