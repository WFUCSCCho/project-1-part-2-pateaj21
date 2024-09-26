/*****
 * @file: Proj1.java
 * @description: This program serves as the entry point for the Taylor Swift discography
 *               parser application. It checks for the correct number of command-line
 *               arguments (expecting a filename) and initializes the Parser to process
 *               the input file. This class orchestrates the overall execution of the
 *               discography search and display functionalities.
 * @author: Aashi Patel
 * @date: September 26, 2024
 ****/

import java.io.FileNotFoundException;

public class Proj1 {
    // Checks if exactly one argument (the filename) is provided,
    // prints an error message and exits if not, and invokes the Parser class
    // to parse the provided file.
    public static void main(String[] args) throws FileNotFoundException {
        if (args.length != 1) {
            System.err.println("Argument count is invalid: " + args.length);
            System.exit(0);
        }
        // Create a new Parser object and pass the filename to it for parsing
        new Parser(args[0]);
    }
}


