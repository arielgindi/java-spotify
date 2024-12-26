package spotify;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import spotify.config.AppConfig;

public class MainApp {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        SpotifyService spotifyService = context.getBean("spotifyService", SpotifyService.class);

        // יצירת שירים
        Song song1 = new Song("Imagine", "John Lennon", "Pop", 3.15);
        Song song2 = new Song("Bohemian Rhapsody", "Queen", "Rock", 5.55);

        // הוספת שירים
        spotifyService.addSong(song1);
        spotifyService.addSong(song2);

        // שמירה לקובץ
        String filePath = "songs.dat";
        try {
            spotifyService.saveSongsToFile(filePath);
            System.out.println("Songs saved to file.");
        } catch (Exception e) {
            System.err.println("Error saving songs: " + e.getMessage());
        }

        // טעינה מקובץ
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
