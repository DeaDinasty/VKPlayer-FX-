/*
 * Entagged Audio Tag library
 * Copyright (c) 2003-2005 Raphaël Slinckx <raphael@slinckx.net>
 * 
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *  
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 */
package sample.org.jaudiotagger.audio.flac;

import sample.org.jaudiotagger.audio.AudioFile;
import sample.org.jaudiotagger.audio.exceptions.CannotWriteException;
import sample.org.jaudiotagger.audio.generic.AudioFileWriter;
import sample.org.jaudiotagger.audio.generic.AudioFileWriter2;
import sample.org.jaudiotagger.tag.Tag;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Path;


/**
 * Write/delete tag info for Flac file (opensource lossless encoding)
 */
public class FlacFileWriter extends AudioFileWriter2
{

    private FlacTagWriter tw = new FlacTagWriter();

    @Override
    protected void writeTag(Tag tag, Path file) throws CannotWriteException
    {
        tw.write(tag, file);
    }

    @Override
    protected void deleteTag(Tag tag, Path file) throws CannotWriteException
    {
        tw.delete(tag, file);
    }


}

