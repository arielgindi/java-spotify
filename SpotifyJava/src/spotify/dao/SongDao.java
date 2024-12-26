package spotify.dao;

import spotify.Song;
import java.util.List;

public interface SongDao {
    void save(Song song);
    List<Song> findAll();
    void delete(String name);
    void loadFromFile(String filePath) throws Exception; // ����� �����
    void saveToFile(String filePath) throws Exception;  // ����� �����
}
