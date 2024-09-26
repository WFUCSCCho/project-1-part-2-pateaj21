/*****
 @file: Proj1.java
 ∗ @description: This program serves as the entry point for parsing a file. It ensures that
 ∗              the user provides exactly one argument, which is the filename to be parsed.
 ∗              If the number of arguments is incorrect, an error message is displayed, and
 ∗              the program terminates. The program then creates a new instance of the
 ∗              Parser class to process the file.
 ∗ @author: Aashi Patel
 ∗ @date: September 19, 2024
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


