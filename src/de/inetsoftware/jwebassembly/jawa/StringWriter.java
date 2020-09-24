package de.inetsoftware.jwebassembly.jawa;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class StringWriter {

    StringBuilder string;

    public StringWriter() { string = new StringBuilder(); }

    public StringWriter(String s) { string = new StringBuilder(s); }

    public void replace(int byteloc, byte b) {
        string.deleteCharAt(byteloc);
        string.insert(byteloc, (char) b);
    }

    public void replace(int byteloc, byte... bytes) throws IOException {
        for (byte b : bytes) {
            replace(byteloc++, b);
        }
    }

    public void write(byte b) throws IOException {
        string.append((char) b);
    }

    public void write(byte... bytes) throws IOException {
        for( byte b : bytes ) {
           write(b);
        }
    }

    public void write(int bytes) throws IOException {
        if (bytes > 255) throw new IOException();
        write((byte) bytes);
    }

    public void writeSig( @Nonnull String str ) throws IOException {
        byte[] bytes = str.getBytes( StandardCharsets.UTF_8 );
        write(bytes);
    }

    public void writeName( @Nonnull String str ) throws IOException {
        byte[] bytes = str.getBytes( StandardCharsets.UTF_8 );
        writeJI2( bytes.length );
        write(bytes);
    }

    public void writeString( @Nonnull String str ) throws IOException {
        byte[] bytes = str.getBytes( StandardCharsets.UTF_8 );
        writeJI4( bytes.length );
        write( bytes );
    }

    public void writeJI2(@Nonnegative int value ) throws IOException {
        byte[] bytes = {
                (byte) value,
                (byte) (value >>> 7),
        };
        write(bytes);
    }

    public void replaceJI2(@Nonnegative int value, @Nonnegative int byteloc) throws IOException {
        byte[] bytes = {
                (byte) value,
                (byte) (value >>> 7),
        };
    }

    public void replaceJI4(@Nonnegative int value, @Nonnegative int byteloc) throws IOException {
        byte[] bytes = {
                (byte) value,
                (byte) (value >>> 7),
                (byte) (value >>> 14),
                (byte) (value >>> 21),
        };
        replace(byteloc, bytes);
    }

    public void writeJI4(@Nonnegative int value ) throws IOException {

        byte[] bytes = {
                (byte) value,
                (byte) (value >>> 7),
                (byte) (value >>> 14),
                (byte) (value >>> 21)
        };
        write(bytes);
    }

    @Override
    public String toString() {
        return string.toString().replace("java", "jawa");
    }
}
