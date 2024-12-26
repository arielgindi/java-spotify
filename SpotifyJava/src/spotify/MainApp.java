package spotify;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainApp {
    public static void main(String[] args) {
        // ����� ���������
    	ClassPathXmlApplicationContext context =new ClassPathXmlApplicationContext("applicationContext.xml");


        // ����� ������ ����������
        SpotifyService spotifyService = context.getBean("spotifyService", SpotifyService.class);

        // ����� �����
        Song song1 = new Song("Imagine", "John Lennon", "Pop", 3.15);
        Song song2 = new Song("Bohemian Rhapsody","Bohemian Rhapsody", "Queen", 5.55);

        // ����� �����
        spotifyService.addSong(song1);
        spotifyService.addSong(song2);

        // ����� �����
        String filePath = "songs.dat";
        try {
            spotifyService.saveSongsToFile(filePath);
            System.out.println("Songs saved to file.");
        } catch (Exception e) {
            System.err.println("Error saving songs: " + e.getMessage());
        }

        // ����� �����
        try {
            spotifyService.loadSongsFromFile(filePath);
            System.out.println("Songs loaded from file:");
            spotifyService.getAllSongs().forEach(song ->
                    System.out.println("Song: " + song.getName() + ", Artist: " + song.getArtistName()));
        } catch (Exception e) {
            System.err.println("Error loading songs: " + e.getMessage());
        }

        context.close();
    }
}
