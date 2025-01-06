package gr.aueb.cf.projects;

import org.w3c.dom.ls.LSOutput;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Scanner;

public class Project01 {

    public static void main(String[] args) throws IOException {



        try (Scanner in = new Scanner(new File("c:/tmp/numbers.txt"));
             PrintStream ps = new PrintStream(new FileOutputStream("c:/tmp/numbers out.txt"),true,StandardCharsets.UTF_8)) {
            final int NUMBERS_SIZE = 6;
            int[] inputNumbers = new int[49];
            int pivot = 0;
            int [] result = new int[NUMBERS_SIZE];
            int num;




            while ((in.hasNext() && pivot < 49)) {
                num = in.nextInt();
                if (num >= 1 && num <= 49) { // Έλεγχος ορίων
                    inputNumbers[pivot++] = num;
                } else {
                    System.out.println("Number out of bounds: " + num);
                    return;
                }
            }

            if (pivot < NUMBERS_SIZE){
                System.out.println("The file must contain at least 6 numbers!");
                return;
            }



            int [] numbers = Arrays.copyOfRange(inputNumbers, 0 , pivot);
            Arrays.sort(numbers);



            for (int i = 0; i < numbers.length - 5; i++) {
                for (int j = i+1; j < numbers.length - 4; j++) {
                    for (int k = j + 1; k < numbers.length - 3; k++) {
                        for (int l = k + 1; l < numbers.length - 2; l++) {
                            for (int m = l + 1; m < numbers .length -1; m++) {
                                for (int n = m + 1; n < numbers.length; n++ ) {
                                    result[0] = numbers[i];
                                    result[1] = numbers[j];
                                    result[2] = numbers[k];
                                    result[3] = numbers[l];
                                    result[4] = numbers[m];
                                    result[5] = numbers[n];

                                    if (isValidCombination(result)) {
                                        for (int res : result) {
                                            ps.print(res + " ");
                                        }
                                        ps.println();
                                    }
                                }
                            }
                        }
                    }
                }

            }
            System.out.println("Process completed. Valid combinations are saved to the file.");

            }catch(FileNotFoundException e) {
            System.out.println("The file was not found. Please check the file path.");


        }
    }

    public static boolean isEven(int[] arr) {
        int even = 0;
        if (arr == null) return false;

        for (int num : arr) {
            if (num % 2 == 0) even++;


        }
        return even <= 4;
    }

    public static boolean isOdd(int[] arr) {
        int odds = 0;
        if (arr == null) return false;

        for (int num : arr) {
            if (num % 2 != 0) odds++;

        }
        return odds <= 4;
    }

    public static boolean isContiguous(int[] arr) {
        if (arr == null) return false;

        int consecutiveCount = 0;

        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] == arr[i + 1] - 1) {
                consecutiveCount++;
                if (consecutiveCount > 2) return false;
            } else {
                consecutiveCount = 0;
            }
        }

        return true; // If the loop finishes without finding more than 2 consecutive numbers, return true
    }


    public static boolean isSameEnding(int[] arr) {
        int[] endingsCounts = new int[10];
        int lastDigit = 0;
        boolean hasSameEndings = true;

        if (arr == null) return false;

        for (int num : arr) {
            lastDigit = num % 10;
            endingsCounts[lastDigit]++;

        }

        for (int count : endingsCounts) {
            if (count > 3) {
                hasSameEndings = false;
                break;


            }


        }
        return hasSameEndings;

    }
    public static boolean isSameTen (int[] arr) {
        if (arr == null) return false;

        int[] tensCount = new int[5];
        int ten = 0;
        boolean hasSameTen = true;

        for (int num : arr) {
            ten = (num - 1) / 10; //
            tensCount[ten]++;
        }

        for (int count : tensCount) {
            if (count > 3) {
                hasSameTen = false;
                break;
            }
        }
        return hasSameTen;
    }

    /**
     * The combination must satisfy all the following conditions:
     * No more than 4 even numbers
     * No more than 4 odd numbers
     * No three contiguous numbers
     * No more than 3 numbers with the same last digit
     * No more than 3 numbers from the same tens group
     * @param arr
     * @return the validation to meet the predicates.
     */
    // Check if the combination is valid based on all the criteria
    public static boolean isValidCombination(int[] arr) {
        return isEven(arr) && isOdd(arr) && isContiguous(arr) && isSameEnding(arr)
                && isSameTen(arr);
    }

}
