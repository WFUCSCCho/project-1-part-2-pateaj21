/*****
 @file: Parser.java
 ∗ @description: This program implements a parser that reads commands from an input file
  *               and performs operations on a Binary Search Tree (BST) containing Taylor Swift's
  *               discography data. The supported commands are search, print_album, and print.
  *               The results of these operations are written to an output file.
 ∗ @author: Aashi Patel
 ∗ @date: September 26, 2024
 ****/

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class Parser {
    private Taylor_Discography taylorDiscography;

    // Clears the content of the result file at the start of the program
    public void clearFile(String filePath) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(filePath))) {
            pw.print(""); // Clear the file content
        } catch (IOException e) {
            System.err.println("Error clearing the file: " + e.getMessage());
        }
    }

    // Create a Binary Search Tree (BST) of Integer type
    private BST<Integer> mybst = new BST<>();

    // Constructor: Takes the input file name, clears the output file, and processes the commands
    public Parser(String filename) throws FileNotFoundException {
        taylorDiscography = new Taylor_Discography();
        taylorDiscography.populateBSTFromCSV("/Users/aashipatel/IdeaProjects/project-1-part-2-pateaj21/src/Taylor_discography.csv");
        clearFile("./output.txt"); // Clear the result file before processing new commands
        process(new File(filename));
    }

    // Processes the input file by reading each line, trimming unnecessary spaces,
    // and sending the commands to be executed on the BST
    public void process(File input) throws FileNotFoundException {
        try (Scanner scanner = new Scanner(input)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();  // Read the next line and remove leading/trailing whitespace
                if (!line.isEmpty()) {
                    String[] command = line.split("\\s+");
                    operate_BST(command);   //call operate_BST method to execute the command
                }
            }
        }
    }

    // Operates on the BST based on the command provided.
    // Supported commands:  "search" "print".
    // Logs the result of each operation to the result file.
    public void operate_BST(String[] command) {
        switch (command[0].toLowerCase()) {
            // Search in the BST
            case "search":
                // Check if there's at least one argument after "search"
                if (command.length > 1) {
                    // Join all arguments after "search" to form the track title
                    String trackTitle = String.join(" ", Arrays.copyOfRange(command, 1, command.length));
                    // Search for the track in the Taylor_Discography
                    Taylor_Discography_Data result = taylorDiscography.searchTrack(trackTitle);
                    if (result != null) {
                        writeToFile("Found: " + result, "./output.txt");
                    } else {
                        writeToFile("Track not found: " + trackTitle, "./output.txt");
                    }
                } else {
                    writeToFile("Invalid search command", "./output.txt");
                }
                break;
            // Check if there's at least one argument after "print_album"
            case "print_album":
                if (command.length > 1) {
                    // Join all arguments after "print_album" to form the album name
                    String albumName = String.join(" ", Arrays.copyOfRange(command, 1, command.length));
                    printAlbum(albumName);
                } else {
                    writeToFile("Invalid print_album command", "./output.txt");
                }
                break;
            case "print":
                StringBuilder sb = new StringBuilder();
                sb.append("All tracks:\n");
                // Add a header for all tracks
                for (Taylor_Discography_Data data : taylorDiscography.getBST()) {
                    sb.append(data).append("\n");
                    // Append each track's information to the StringBuilder
                }
                writeToFile(sb.toString(), "./output.txt");
                break;
            // Handle invalid commands
            default:
                writeToFile("Invalid Command", "./output.txt");
                break;
        }
    }

    private void printAlbum(String albumName) {
        // Initialize a StringBuilder to store the output
        StringBuilder sb = new StringBuilder();
        // Add a header for the album tracks
        sb.append("Tracks in album ").append(albumName).append(":\n");
        // Flag to track if any tracks are found
        boolean found = false;
        // Iterate through all tracks in the BST
        for (Taylor_Discography_Data data : taylorDiscography.getBST()) {
            if (data.getAlbum().toLowerCase().contains(albumName.toLowerCase().trim())) {
                sb.append(data).append("\n");
                found = true;
            }
        }
        if (!found) {
            sb.append("No tracks found for album: ").append(albumName);
        }
        // Write the compiled track information to the output file
        writeToFile(sb.toString(), "./output.txt");
    }

    // Writes content to the result file.
    // Each content is written on a new line.
    public void writeToFile(String content, String filePath) {
        // Use try-with-resources to ensure the BufferedWriter is properly closed
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath, true))) {
            bw.write(content); // Write the content to the file
            bw.newLine();  // Add a newline after each write to prevent content from merging
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }
}

