package spotify;

import java.io.Serializable;

public class Song implements Serializable {
    private static final long serialVersionUID = 1L; // מזהה גרסה לסריאליזציה

    private String name;
    private String artistName;
    private String genre;
    private double length;

    // בנאי
    public Song(String name, String artistName, String genre, double length) {
        this.name = name;
        this.artistName = artistName;
        this.genre = genre;
        this.length = length;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    // מתודות עזר
    @Override
    public String toString() {
        return "Song{name='" + name + "', artistName='" + artistName + "', genre='" + genre + "', length=" + length + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Song song = (Song) o;

        return name != null ? name.equals(song.name) : song.name == null;
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }
}
