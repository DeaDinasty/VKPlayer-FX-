package sample.org.jaudiotagger.audio.mp3;

import sample.org.jaudiotagger.audio.AudioFile;
import sample.org.jaudiotagger.audio.exceptions.CannotReadException;
import sample.org.jaudiotagger.audio.exceptions.CannotWriteException;
import sample.org.jaudiotagger.audio.generic.AudioFileWriter;
import sample.org.jaudiotagger.tag.Tag;

import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * Write Mp3 Info (retrofitted to entagged ,done differently to entagged which is why some methods throw RuntimeException)
 * because done elsewhere
 */
public class MP3FileWriter extends AudioFileWriter
{
    public void deleteTag(AudioFile f) throws CannotWriteException
    {
        //Because audio file is an instanceof MP3File this directs it to save
        //taking into account if the tag has been sent to null in which case it will be deleted
        f.commit();
    }

    public void writeFile(AudioFile f) throws CannotWriteException
    {
        //Because audio file is an instanceof MP3File this directs it to save
        f.commit();
    }

    /**
     * Delete the Id3v1 and ID3v2 tags from file
     *
     * @param af
     * @throws CannotReadException
     * @throws CannotWriteException
     */
    @Override
    public synchronized void delete(AudioFile af) throws CannotReadException, CannotWriteException
    {
        ((MP3File)af).setID3v1Tag(null);
        ((MP3File)af).setID3v2Tag(null);                
        af.commit();
    }

    protected void writeTag(AudioFile audioFile, Tag tag, RandomAccessFile raf, RandomAccessFile rafTemp) throws CannotWriteException, IOException
    {
        throw new RuntimeException("MP3FileReaderwriteTag should not be called");
    }

    protected void deleteTag(Tag tag, RandomAccessFile raf, RandomAccessFile tempRaf) throws CannotWriteException, IOException
    {
        throw new RuntimeException("MP3FileReader.getEncodingInfo should be called");
    }
}


