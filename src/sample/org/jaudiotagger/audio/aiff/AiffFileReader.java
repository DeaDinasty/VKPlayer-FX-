package sample.org.jaudiotagger.audio.aiff;

import sample.org.jaudiotagger.audio.exceptions.CannotReadException;
import sample.org.jaudiotagger.audio.generic.AudioFileReader;
import sample.org.jaudiotagger.audio.generic.AudioFileReader2;
import sample.org.jaudiotagger.audio.generic.GenericAudioHeader;
import sample.org.jaudiotagger.tag.Tag;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Path;

/**
 * Reads Audio and Metadata information contained in Aiff file.
 */
public class AiffFileReader extends AudioFileReader2
{
    private AiffInfoReader      ir = new AiffInfoReader();
    private AiffTagReader       im = new AiffTagReader();

    @Override
    protected GenericAudioHeader getEncodingInfo(Path path)throws CannotReadException, IOException
    {
        return ir.read(path);
    }

    @Override
    protected Tag getTag(Path path)throws CannotReadException, IOException
    {
        return im.read(path);
    }
}
