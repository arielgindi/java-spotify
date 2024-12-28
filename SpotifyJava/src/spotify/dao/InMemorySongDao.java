package spotify.dao;

import org.springframework.stereotype.Repository;
import spotify.Song;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Repository
public class InMemorySongDao implements SongDao, Serializable {
    private static final long serialVersionUID = 1L;
    private final List<Song> songs = new ArrayList<>();
    private static final String DEFAULT_FILE_PATH = "./songs.dat";

    @Override
    public List<Song> findAll() {
        return new ArrayList<>(songs); // ����� ���� �� ������
    }

    @Override
    public void save(Song song) {
        songs.add(song);
        Collections.sort(songs); // ���� ������ ��� ��� (�� �� ����)
    }

    @Override
    public void update(Song song) throws Exception {
        Song existingSong = getById(song.getSongID());
        if (existingSong == null) {
            throw new Exception("Song with ID " + song.getSongID() + " not found.");
        }
        existingSong.setName(song.getName());
        existingSong.setArtistName(song.getArtistName());
        existingSong.setGenre(song.getGenre());
        existingSong.setLength(song.getLength());
    }

    @Override
    public void delete(int id) throws Exception {
        Song song = getById(id);
        if (song == null) {
            throw new Exception("Song with ID " + id + " not found.");
        }
        songs.remove(song);
    }

    @Override
    public Song getById(int id) {
        return songs.stream()
                .filter(song -> song.getSongID() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public void saveToFile() throws Exception {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(DEFAULT_FILE_PATH))) {
            oos.writeObject(songs);
        }
    }

    @Override
    public void loadFromFile() throws Exception {
        File file = new File(DEFAULT_FILE_PATH);
        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(DEFAULT_FILE_PATH))) {
                List<Song> loadedSongs = (List<Song>) ois.readObject();
                songs.clear();
                songs.addAll(loadedSongs);
            }
        }
    }
}
