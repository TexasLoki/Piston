package org.pistonmc.protocol.stream;

import com.evilco.mc.nbt.stream.NbtOutputStream;
import com.evilco.mc.nbt.tag.ITag;
import com.evilco.mc.nbt.tag.TagCompound;
import org.pistonmc.inventory.ItemStack;
import org.pistonmc.protocol.data.Metadata;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class PacketOutputStream extends DataOutputStream {

    public PacketOutputStream(OutputStream out) {
        super(out);
    }

    public void writeString(String string) throws IOException {
        writeVarInt(string.length());
        write(string.getBytes("UTF-8"));
    }

    public void writeStringArray(String[] array) throws IOException {
        writeShort(array.length);
        for(String string : array) {
            writeString(string);
        }
    }

    public void writeVarInt(int paramInt) throws IOException {
        while(true) {
            if((paramInt & 0xFFFFFF80) == 0) {
                writeByte((byte) paramInt);
                return;
            }

            writeByte((byte) (paramInt & 0x7F | 0x80));
            paramInt >>>= 7;
        }
    }

    public void writeVarInt64(long varInt) throws IOException {
        int length = 10;
        for(int i = 9; i >= 0; i--)
            if(((varInt >> (i * 7)) & (i != 9 ? 0x7F : 0x01)) == 0)
                length--;
        for(int i = 0; i < length; i++)
            writeByte((int) ((i == length - 1 ? 0x00 : 0x80) | ((varInt >> (i * 7)) & (i != 9 ? 0x7F : 0x01))));
    }

    public void writeByteArray(byte[] data) throws IOException {
        writeShort(data.length);
        write(data);
    }

    public void writeUnsignedByte(int data) throws IOException {
        write(data);
    }

    public void writeUnsignedShort(int data) throws IOException {
        writeShort(data & 0xFFFF);
    }

    public void writeFixedPointByte(double value) throws IOException {
        int i = (int) value * 32;
        writeByte(i);
    }

    public void writeFixedPointInt(double value) throws IOException {
        int i = (int) value * 32;
        writeInt(i);
    }

    public void writeRotation(float value) throws IOException {
        int i = (int) (value * 256D) / 360;
        writeByte(i);
    }

    public void writeMetadata(Metadata metadata) throws IOException {
        metadata.write(this);
    }

    public void writeItemStack(ItemStack item) throws IOException {
        if(item != null) {
            writeShort(item.getTypeId());
            writeByte(item.getAmount());
            writeShort((short) item.getData().getData());
            writeCompound(item.getCompound());
        } else {
            writeShort(-1);
        }
    }

    public void writeTag(ITag tag) throws IOException {
        NbtOutputStream stream = new NbtOutputStream(this);
        stream.write(tag);
    }

    public void writeCompound(TagCompound compound) throws IOException {
        writeTag(compound);
    }

}
