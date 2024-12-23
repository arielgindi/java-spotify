package spotify;

public class Song {
	private String name;
	private String artistName;
	private String genre;
	private double length;
	public Song(String name, String artistName, String genre, double length) {
		this.name = name;
		this.artistName = artistName;
		this.genre = genre;
		this.length = length;
	}
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

	
}
