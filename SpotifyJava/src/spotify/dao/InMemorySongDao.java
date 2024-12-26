package spotify.dao;

import org.springframework.stereotype.Repository;
import spotify.Song;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class InMemorySongDao implements SongDao, Serializable {
    private static final long serialVersionUID = 1L; // גרסה לסריאליזציה
    private final List<Song> songs = new ArrayList<>();

    @Override
    public void save(Song song) {
        songs.add(song);
    }

    @Override
    public List<Song> findAll() {
        return new ArrayList<>(songs);
    }

    @Override
    public void delete(String name) {
        songs.removeIf(song -> song.getName().equalsIgnoreCase(name));
    }

    @Override
    public void loadFromFile(String filePath) throws Exception {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            List<Song> loadedSongs = (List<Song>) ois.readObject();
            songs.clear();
            songs.addAll(loadedSongs);
        }
    }

    @Override
    public void saveToFile(String filePath) throws Exception {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(songs);
        }
    }
}
