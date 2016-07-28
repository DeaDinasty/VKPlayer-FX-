package sample.trackInfo;

public class BaggageTI {
    static public String[] trackName;
    static public String[] trackArtist;
    static public String[] trackAlbum;
    static public String[] trackTime;
    public BaggageTI(){}
    public BaggageTI(String[] trackName, String[] trackArtist, String[] trackAlbum, String[] trackTime) {
        this.trackName = trackName;
        this.trackArtist = trackArtist;
        this.trackAlbum = trackAlbum;
        this.trackTime = trackTime;
    }
}
