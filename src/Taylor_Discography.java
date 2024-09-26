import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

import static java.lang.StringUTF16.compareTo;

public class Taylor_Discography {
    private BST<Taylor_Discography_Data> bst;

    public Taylor_Discography() {
        bst = new BST<>();
    }

    // Method to read CSV file and populate BST
    public void populateBSTFromCSV(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            br.readLine(); // Skip header
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
                String s = Arrays.toString(values);
                System.out.println(s);
                Taylor_Discography_Data data = new Taylor_Discography_Data(
                        values[0], // album
                        values[1], // track_title
                        Integer.parseInt(values[2]), // track_number
                        values[3], // released_year
                        values[4], // lyrics
                        values[5], // label
                        values[6]  // writers
                );
                bst.insert(data);
            }
        } catch (IOException e) {
            System.err.println("Error reading CSV file: " + e.getMessage());
        }
    }

    // Method to search for a track by title
    public Taylor_Discography_Data searchTrack(String title) {
        for (Taylor_Discography_Data data : bst) {
            int comp = compareTo(data);
            Taylor_Discography_Data.compareTo(data);

            if (compareTo(other.album){

            }
        }
        return null;
    }


    // Method to print all tracks in an album
    public void printAlbumTracks(String albumName) {
        for (Taylor_Discography_Data data : bst) {
            if (data.getAlbum().equalsIgnoreCase(albumName)) {
                System.out.println(data);
            }
        }
    }

    // Getter for the BST
    public BST<Taylor_Discography_Data> getBST() {
        return bst;
    }
}