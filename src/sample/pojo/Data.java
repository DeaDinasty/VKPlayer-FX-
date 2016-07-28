package sample.pojo;

public class Data {

    private int id;
    private String name;
    private String artist;
    private String album;
    private String time;

    public Data(int id, String name, String artist, String album, String time) {
        this.id = id;
        this.name = name;
        this.artist = artist;
        this.album = album;
        this.time = time;
    }

    public void DataClean() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getAlbum() { return album; }

    public void setAlbum(String album) { this.album = album; }

    public String getTime() {
        return time;
    }
}
