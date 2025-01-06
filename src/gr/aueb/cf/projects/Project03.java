package gr.aueb.cf.projects;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Project03 {

    public static void main(String[] args) {
                // 128x2 array to store characters and their frequencies
                int[][] table = new int[128][2];

                // Filename of the text file to be read
                String filename = "c:/tmp/input.txt";

                // Read the file and count the occurrences of each character
                readFileAndCount(filename, table);

                // Display the statistics
                displayStatistics(table);
            }

            // Function to read the file and count character occurrences
            public static void readFileAndCount(String filename, int[][] table) {
                try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
                    int ch;
                    // Read the file character by character
                    while ((ch = br.read()) != -1) {
                        // Ignore whitespaces and newline characters
                        if (Character.isWhitespace(ch)) {
                            continue;
                        }

                        // If the character is within the ASCII range (0-127)
                        if (ch >= 0 && ch < 128) {
                            table[ch][0] = ch;       // Store the character (its ASCII value)
                            table[ch][1]++;          // Increment the frequency count
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            // Function to display the character statistics
            public static void displayStatistics(int[][] table) {
                System.out.println("Statistics:");

                // Iterate over the table and display the characters with their frequencies
                for (int i = 0; i < 128; i++) {
                    if (table[i][1] > 0) {  // If the character appeared at least once
                        // Convert the ASCII value back to a character and print it with its frequency
                        System.out.println((char) table[i][0] + ": " + table[i][1]);
                    }
                }
            }

        }

