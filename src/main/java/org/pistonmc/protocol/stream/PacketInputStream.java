package org.pistonmc.protocol.stream;

import org.pistonmc.protocol.data.DataObject;
import org.pistonmc.protocol.data.Metadata;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

public class PacketInputStream extends DataInputStream {

    private static final Charset UTF8 = Charset.forName("UTF-8");

    public PacketInputStream(InputStream in) {
        super(in);
    }

    public String readString() throws IOException {
        int length = readVarInt();
        byte[] data = new byte[length];
        readFully(data);
        return new String(data, UTF8);
    }

    public String[] readStringArray() throws IOException {
        short size = readShort();

        String[] array = new String[size];
        for(int i = 0; i < array.length; i++) {
            array[i] = readString();
        }

        return array;
    }

    public int readVarInt() throws IOException {
        int i = 0;
        int j = 0;
        while(true) {
            int k = read();
            if(k == -1)
                throw new IOException("End of stream");

            i |= (k & 0x7F) << j++ * 7;

            if(j > 5)
                throw new IOException("VarInt too big");

            if((k & 0x80) != 128)
                break;
        }

        return i;
    }

    public long readVarInt64() throws IOException {
        long varInt = 0;
        for(int i = 0; i < 10; i++) {
            byte b = readByte();
            varInt |= ((long) (b & (i != 9 ? 0x7F : 0x01))) << (i * 7);

            if(i == 9 && (((b & 0x80) == 0x80) || ((b & 0x7E) != 0)))
                throw new IOException("VarInt too big");
            if((b & 0x80) != 0x80)
                break;
        }

        return varInt;
    }

    public byte[] readBytes() throws IOException {
        return readBytes(readShort());
    }

    public byte[] readBytes(int length) throws IOException {
        if(length < 0)
            throw new IOException("Invalid array length");
        byte[] data = new byte[length];
        readFully(data);
        return data;
    }

    public double readFixedPointByte() throws IOException {
        return (double) readByte() / 32;
    }

    public double readFixedPointInt() throws IOException {
        return (double) readInt() / 32;
    }

    public float readRotation() throws IOException {
        return (float) (readByte() / 360) * 256F;
    }

    public Metadata readMetadata() throws IOException {
        Metadata metadata = new Metadata();

        byte item;
        while((item = readByte()) != 127) {
            int index = item & 0x1F;
            byte type = (byte) (item >> 5);

            DataObject<?> object = null;
            switch(type) {
                case 0:
                    object = new DataObject<>(index, type, readByte());
                    break;
                case 1:
                    object = new DataObject<>(index, type, readShort());
                    break;
                case 2:
                    object = new DataObject<>(index, type, readInt());
                    break;
                case 3:
                    object = new DataObject<>(index, type, readFloat());
                    break;
                case 4:
                    object = new DataObject<>(index, type, readString());
                    break;
                case 5:
                    // object = new DataObject<>(index, type, readSlot());
                    break;
                case 6:
                    // object = new DataObject<>(index, type, readLocation());
                    break;
            }

            metadata.put(index, object);
        }

        return metadata;
    }

}
