package sample.org.jaudiotagger.audio.real;

import sample.org.jaudiotagger.audio.generic.GenericTag;
import sample.org.jaudiotagger.tag.FieldDataInvalidException;
import sample.org.jaudiotagger.tag.FieldKey;
import sample.org.jaudiotagger.tag.KeyNotFoundException;
import sample.org.jaudiotagger.tag.TagField;

public class RealTag extends GenericTag
{
    public String toString()
    {
        String output = "REAL " + super.toString();
        return output;
    }

    public TagField createCompilationField(boolean value) throws KeyNotFoundException, FieldDataInvalidException
    {
        return createField(FieldKey.IS_COMPILATION,String.valueOf(value));
    }
}
