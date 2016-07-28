package sample.org.jaudiotagger.audio.wav.chunk;

import sample.org.jaudiotagger.audio.generic.Utils;
import sample.org.jaudiotagger.audio.iff.IffHeaderChunk;
import sample.org.jaudiotagger.tag.FieldDataInvalidException;
import sample.org.jaudiotagger.tag.wav.WavInfoTag;
import sample.org.jaudiotagger.tag.wav.WavTag;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.logging.Logger;

/**
 * Stores basic only metadata but only exists as part of a LIST chunk, doesn't have its own size field
 * instead contains a number of name,size, value tuples. So for this reason we dont subclass the Chunk class
 */
public class WavInfoChunk
{
    public static Logger logger = Logger.getLogger("sample.org.jaudiotagger.audio.wav.WavInfoChunk");

    private WavInfoTag wavInfoTag;

    public WavInfoChunk(WavTag tag)
    {
        wavInfoTag = new WavInfoTag();
        tag.setInfoTag(wavInfoTag);
    }

    /**
     * Read Info chunk
     * @param chunkData
     */
    public  boolean readChunks(ByteBuffer chunkData)
    {
        while(chunkData.remaining()>= IffHeaderChunk.TYPE_LENGTH)
        {
            String id       = Utils.readFourBytesAsChars(chunkData);
            //Padding
            if(id.trim().isEmpty())
            {
                return true;
            }
            int    size     = chunkData.getInt();

            //TODO how do you identify what is the charset being used
            String value    = Utils.getString(chunkData, 0, size, StandardCharsets.UTF_8);
            logger.config("Result:" + id + ":" + size + ":" + value + ":");

            WavInfoIdentifier wii = WavInfoIdentifier.getByCode(id);
            if(wii!=null && wii.getFieldKey()!=null)
            {
                try
                {
                    wavInfoTag.setField(wii.getFieldKey(), value);
                }
                catch(FieldDataInvalidException fdie)
                {
                    fdie.printStackTrace();
                }
            }
            //Add unless just padding
            else if(id!=null && !id.trim().isEmpty())
            {
                wavInfoTag.addUnRecognizedField(id, value);
            }

            //Each tuple aligned on even byte boundary
            if ((size & 1) != 0 && chunkData.hasRemaining())
            {
                chunkData.get();
            }
        }
        return true;
    }
}
