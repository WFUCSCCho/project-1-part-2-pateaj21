import java.util.Objects;

public class Taylor_Discography_Data implements Comparable<Taylor_Discography_Data> {
    private String album;
    private String track_title;
    private int track_number;
    private String released_year;
    private String lyrics;
    private String label;
    private String writers;

    // Default constructor
    public Taylor_Discography_Data() {
    }

    // Parameterized constructor
    public Taylor_Discography_Data(String album, String track_title, int track_number, String released_year, String lyrics, String label, String writers) {
        this.album = album;
        this.track_title = track_title;
        this.track_number = track_number;
        this.released_year = released_year;
        this.lyrics = lyrics;
        this.label = label;
        this.writers = writers;
    }

    // Copy constructor
    public Taylor_Discography_Data(Taylor_Discography_Data other) {
        this(other.album, other.track_title, other.track_number, other.released_year, other.lyrics, other.label, other.writers);
    }

    // Getters
    public String getAlbum() {
        return album;
    }

    public String getTrackTitle() {
        return track_title;
    }

    public int getTrackNumber() {
        return track_number;
    }

    public String getReleasedYear() {
        return released_year;
    }

    // toString method
    @Override
    public String toString() {
        return String.format("Album: " + album + ", Track: " + track_title + ", Track Number: " + track_number +
                ", Year: " + released_year + ", Label: " + label + ", Writers: " + writers);
    }

    // equals method
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Taylor_Discography_Data that = (Taylor_Discography_Data) obj;
        return track_number == that.track_number &&
                Objects.equals(album, that.album) &&
                Objects.equals(track_title, that.track_title) &&
                Objects.equals(released_year, that.released_year) &&
                Objects.equals(label, that.label) &&
                Objects.equals(writers, that.writers);
    }
    //search method in here
    public Taylor_Discography_Data searchTrack(String title) {
        for (Taylor_Discography_Data data : bst) {
            int comp = compareTo(data);
            Taylor_Discography_Data.compareTo(data);

            if (compareTo(other.album){

            }
        }
        return null;
    }

    // compareTo method
    public int compareTo(Taylor_Discography_Data other) {
        //compare by albums alphabetically
        int albumComparison = this.album.compareTo(other.album);
        //if  albums are same, return the result of that comparison
        if (albumComparison == 0) {
        return Integer.parseInt(other.album);
    } return 0;

    }
}