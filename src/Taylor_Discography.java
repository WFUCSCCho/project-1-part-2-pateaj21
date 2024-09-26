/*****
 * @file: Taylor_Discography.java
 * @description: This class manages a Binary Search Tree (BST) of Taylor Swift's discography data.
 *               It provides methods to populate the BST from a CSV file, search for tracks,
 *               and retrieve the entire BST. It serves as the main data structure for the
 *               discography information.
 * @author: Aashi Patel
 * @date: September 26, 2024
 ****/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Taylor_Discography {
    private BST<Taylor_Discography_Data> bst;

    public Taylor_Discography() {
        bst = new BST<>();
    }

    // Method to read CSV file and populate BST with data from CSV
    public void populateBSTFromCSV(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            br.readLine(); // Skip header
            while ((line = br.readLine()) != null) {
                // Parse the CSV line into an array of values
                String[] values = parseCsvLine(line);
                // Ensure the line has at least 7 values (minimum required fields)
                if (values.length >= 7) {
                    // Create a new Taylor_Discography_Data object from the CSV values
                    Taylor_Discography_Data data = new Taylor_Discography_Data(
                            values[6], // album
                            values[0], // track_title
                            Integer.parseInt(values[8]), // track_number
                            values[7], // released_year
                            "", // lyrics
                            values[10], // writers
                            values[1] //spotifyID
                    );
                    bst.insert(data); // Insert new data object into the BST
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading CSV file: " + e.getMessage());
        }
    }
    //Parses a single line of CSV data, handling quoted fields correctly.
    private String[] parseCsvLine(String line) {
        List<String> result = new ArrayList<>();
        boolean inQuotes = false;
        StringBuilder field = new StringBuilder();
        // Iterate through each character in the line
        for (char c : line.toCharArray()) {
            if (c == '"') {
                // Toggle the inQuotes flag when encountering a quote
                inQuotes = !inQuotes;
            } else if (c == ',' && !inQuotes) {
                result.add(field.toString().trim());
                field.setLength(0);
            } else {
                // For any other character, append to the current field
                field.append(c);
            }
        }
        result.add(field.toString().trim());
    // Convert the List to an array and return
        return result.toArray(new String[0]);
    }

    private int parseTrackNumber(String trackNumberStr) {
        try {
            return Integer.parseInt(trackNumberStr.trim());
        } catch (NumberFormatException e) {
            System.err.println("Invalid track number: " + trackNumberStr);
            return 0; // or some default value
        }
    }

    // Method to search for a track by title
    public Taylor_Discography_Data searchTrack(String title) {
        // Convert the search term to lowercase and trim whitespace for case-insensitive, flexible matching
        String searchTerm = title.toLowerCase().trim();
        for (Taylor_Discography_Data data : bst) { // Iterate through all tracks in the BST
            String trackTitle = data.getTrackTitle().toLowerCase().trim();
            if (trackTitle.contains(searchTerm)) { // Check if the track title contains the search term
                return data; // Return the first matching track found
            }
        }
        return null;
    }


    // Getter for the BST
    public BST<Taylor_Discography_Data> getBST() {
        return bst;
    }
}