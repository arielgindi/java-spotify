package spotify;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spotify.dao.SongDao;

import java.util.List;
import java.util.Random;

@Service
public class SpotifyService {
    private final SongDao songDao;
    private final Random random = new Random();

    @Autowired
    public SpotifyService(SongDao songDao) {
        this.songDao = songDao;
    }

    public void addSong(Song song) throws Exception {
        int newID = generateUniqueID();
        song.setSongID(newID);
        songDao.save(song);
    }

    private int generateUniqueID() throws Exception {
        int id;
        do {
            id = 100000 + random.nextInt(900000);
        } while (isDuplicateID(id));
        return id;
    }

    private boolean isDuplicateID(int songID) throws Exception {
        return songDao.findAll().stream().anyMatch(song -> song.getSongID() == songID);
    }

    public List<Song> getAllSongs() throws Exception {
        return songDao.findAll();
    }

    public void updateSong(Song song) throws Exception {
        Song existingSong = songDao.getById(song.getSongID());
        if (existingSong != null) {
            existingSong.setName(song.getName());
            existingSong.setArtistName(song.getArtistName());
            existingSong.setGenre(song.getGenre());
            existingSong.setLength(song.getLength());
            songDao.update(existingSong); // Update the song in DAO
        } else {
            throw new Exception("Song with ID " + song.getSongID() + " not found.");
        }
    }

    public void deleteSong(int id) throws Exception {
        Song song = songDao.getById(id);
        if (song != null) {
            songDao.delete(id); // Use ID for deletion
        } else {
            throw new Exception("Song with ID " + id + " not found.");
        }
    }

    public Song getSongById(int id) throws Exception {
        return songDao.getById(id);
    }

    public void saveSongsToFile() throws Exception {
        songDao.saveToFile();
    }

    public void loadSongsFromFile() throws Exception {
        songDao.loadFromFile();
    }
}