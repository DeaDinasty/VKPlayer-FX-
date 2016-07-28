package sample.trackInfo;

import sample.org.jaudiotagger.audio.AudioFile;
import sample.org.jaudiotagger.audio.AudioFileIO;
import sample.org.jaudiotagger.audio.exceptions.CannotReadException;
import sample.org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import sample.org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import sample.org.jaudiotagger.audio.mp3.MP3File;
import sample.org.jaudiotagger.tag.FieldKey;
import sample.org.jaudiotagger.tag.Tag;
import sample.org.jaudiotagger.tag.TagException;
import sample.org.jaudiotagger.tag.id3.ID3v1Tag;
import sample.org.jaudiotagger.tag.id3.ID3v24Frame;
import sample.org.jaudiotagger.tag.id3.ID3v24Frames;

import java.util.List;
import java.util.Collections;
import java.io.*;


public class InfoT {

	public InfoT() {}

	private long arr[];
	private List da0files;
	private String[] trackName;
	private String[] trackArtist;
	private String[] trackAlbum;
	private String[] trackTime;

	public void goQuickSort(long array[], List da0) {
		da0files = da0;
		arr = array;
		quickSort(0, arr.length - 1);
	}

	public long[] getArray() {
		return arr;
	}

    public void goFileInfo(List filePATH) {
        fileInfo(filePATH);
    }

    public BaggageTI getTracksInfo() {
        return new BaggageTI(trackName, trackArtist, trackAlbum, trackTime);
    }

	private void fileInfo(List filePATH) {
		int i = filePATH.size();
		trackName = new String[i];
		trackArtist = new String[i];
		trackAlbum = new String[i];
		trackTime = new String[i];
        File file;
		for (int j = 0; j < i; j++) {
			file = new File(filePATH.get(j).toString());
			try {
				MP3File mp3 = (MP3File) AudioFileIO.read(file);
				Tag tag = mp3.getTag();
				int tempTime = mp3.getAudioHeader().getTrackLength();
                if (tempTime > 3600) {
                    trackTime[j] = tempTime / 3600 + ":";
                    if (tempTime % 3600 / 60 > 0) trackTime[j] += tempTime % 3600 / 60 + ":";
                    else trackTime[j].concat("00:");
                    if (tempTime % 3600 % 60 > 0) trackTime[j] += tempTime % 3600 % 60 + ":";
                    else trackTime[j].concat("00");
                }
                else if (tempTime > 60) {
                    trackTime[j] = tempTime / 60 + ":";
                    if (tempTime % 60 > 0) trackTime[j] += tempTime % 60;
                    else trackTime[j] += "00";
                }
                else trackTime[j] = "0:" + tempTime;
                trackName[j] = tag.getFirst(FieldKey.TITLE);
				trackArtist[j] = tag.getFirst(FieldKey.ARTIST);
				trackAlbum[j] = tag.getFirst(FieldKey.ALBUM);
			} catch (CannotReadException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (TagException e) {
				e.printStackTrace();
			} catch (ReadOnlyFileException e) {
				e.printStackTrace();
			} catch (InvalidAudioFrameException e) {
				e.printStackTrace();
			}
		}
	}

	private int partition(int left, int right) {
		int i = left, j = right;
		long tmp;
		long pivot = arr[(left + right) / 2];      
		while (i <= j) {
			while (arr[i] < pivot) i++;
			while (arr[j] > pivot) j--;
			if (i <= j) { 
				Collections.swap(da0files, i, j);
				tmp = arr[i];
				arr[i] = arr[j];
				arr[j] = tmp;
				i++;
				j--;
			 }
		 };
		return i;
	}
	 
	private void quickSort(int left, int right) {
		int index = partition(left, right);
		if (left < index - 1) quickSort(left, index - 1);
		if (index < right) quickSort(index, right);
	}
}
