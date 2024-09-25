/*****
 @file: Parser.java
 ∗ @description: This program implements a parser that reads commands from an input file
  *               and performs operations on a Binary Search Tree (BST). The supported
  *               commands are insert, search, remove, and print. The results of these
  *               operations are written to an output file.
 ∗ @author: Aashi Patel
 ∗ @date: September 19, 2024
 ****/

import java.io.*;
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
        taylorDiscography.populateBSTFromCSV("Taylor_discography.csv");
        //clearFile("./result.txt"); // Clear the result file before processing new commands
        process(new File(filename));
    }

    // Processes the input file by reading each line, trimming unnecessary spaces,
    // and sending the commands to be executed on the BST
    public void process(File input) throws FileNotFoundException {
        try (Scanner scanner = new Scanner(input)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (!line.isEmpty()) {
                    String[] command = line.split("\\s+");
                    operate_BST(command);   //call operate_BST method;
                }
            }
        }
    }

    // Operates on the BST based on the command provided.
    // Supported commands: "insert", "search", "remove", and "print".
    // Logs the result of each operation to the result file.
    public void operate_BST(String[] command) {
        switch (command[0]) {
            // Search in the BST
            case "search":
                if (command.length > 1) {
                    String trackTitle = String.join(" ", java.util.Arrays.copyOfRange(command, 1, command.length));
                    Taylor_Discography_Data result = taylorDiscography.searchTrack(trackTitle);
                    if (result != null) {
                        writeToFile("Found:  " + result, "./output.txt");
                    } else {
                        writeToFile("Track not found: " + trackTitle, "./output.txt");
                    }
                } else {
                    writeToFile("Invalid Command", "./output.txt");
                }
                break;
            // Remove an integer from the BST
            case "print_album":
                if (command.length > 1) {
                    String albumName = String.join(" ", java.util.Arrays.copyOfRange(command, 1, command.length));
                    StringBuilder sb = new StringBuilder();
                    sb.append("Tracks in album ").append(albumName).append(":\n");
                    for (Taylor_Discography_Data data : taylorDiscography.getBST()) {
                        if (data.getAlbum().equalsIgnoreCase(albumName)) {
                            sb.append(data).append("\n");
                        }
                    }
                    writeToFile(sb.toString(), "./output.txt");
                } else {
                    writeToFile("Invalid Command", "./output.txt");
                }
                break;
            // Print all integers in the BST in sorted order
            case "print":
                StringBuilder sb = new StringBuilder();
                sb.append("All tracks:\n");
                for (Taylor_Discography_Data data : taylorDiscography.getBST()) {
                    sb.append(data).append("\n");
                }
                writeToFile(sb.toString(), "./output.txt");
                break;
            // Handle invalid commands
            default:
                writeToFile("Invalid Command", "./output.txt");
                break;
        }
    }


    // Writes content to the result file.
    // Each content is written on a new line.
    public void writeToFile(String content, String filePath) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath, true))) {
            bw.write(content);
            bw.newLine();  // Add a newline after each write to prevent content from merging
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }
}

