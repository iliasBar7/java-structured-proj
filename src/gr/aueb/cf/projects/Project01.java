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
                    System.out.println("Αριθμός εκτός ορίων: " + num);
                    return;
                }
            }

            if (pivot < NUMBERS_SIZE){
                System.out.println("Το αρχείο πρέπει να περιέχει τουλάχιστον 6 αριθμούς!");
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
            System.out.println("Η διαδικασία ολοκληρώθηκε. Οι έγκυροι συνδυασμοί αποθηκεύτηκαν στο αρχείο.");

            }catch(FileNotFoundException e) {
            System.out.println("To Αρχείο δεν βρέθηκε . Παρακαλώ ελέγξτε την διαδρομή του αρχείου ");


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

        for (int i = 0; i < arr.length - 2; i++) {
            if (arr[i] == arr[i + 1] - 1 && arr[i] == arr[i + 2] - 2) {
                return true;
            }
        }
        return false;
    }

    public static boolean isSameEnding(int[] arr) {
        int[] endingsCounts = new int[10];
        int lastDigit = 0;
        boolean hasSameEndings = false;

        if (arr == null) return false;

        for (int num : arr) {
            lastDigit = num % 10;
            endingsCounts[lastDigit]++;

        }

        for (int count : endingsCounts) {
            if (count > 3) {
                hasSameEndings = true;
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

    public static boolean isValidCombination(int[] arr) {
        return isEven(arr) && isOdd(arr) && !isContiguous(arr) && !isSameEnding(arr)
                && isSameTen(arr);
    }

}
