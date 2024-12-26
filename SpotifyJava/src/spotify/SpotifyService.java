package spotify;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spotify.dao.SongDao;
import spotify.Song;

import java.util.List;

@Service
public class SpotifyService {
    private final SongDao songDao;

    @Autowired
    public SpotifyService(SongDao songDao) {
        this.songDao = songDao;
    }

    public void addSong(Song song) {
        songDao.save(song);
    }

    public List<Song> getAllSongs() {
        return songDao.findAll();
    }

    public void removeSong(String name) {
        songDao.delete(name);
    }

    public void saveSongsToFile(String filePath) throws Exception {
        songDao.saveToFile(filePath);
    }

    public void loadSongsFromFile(String filePath) throws Exception {
        songDao.loadFromFile(filePath);
    }
}
