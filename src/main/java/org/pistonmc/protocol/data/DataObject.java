package org.pistonmc.protocol.data;

import org.pistonmc.inventory.ItemStack;
import org.pistonmc.protocol.stream.PacketOutputStream;

import java.io.IOException;

public class DataObject<T> {

    private int index;
    private DataType type;
    private T value;

    public DataObject(int index, byte type, T value) {
        this.index = index;
        this.type = DataType.valueOf(type);
        this.value = value;
    }

    public int getIndex() {
        return index;
    }

    public DataType getType() {
        return type;
    }

    public T getValue() {
        return value;
    }

    public void write(PacketOutputStream stream) throws IOException {
        switch(type.getType()) {
            case 0:
                stream.writeByte((Byte) value);
                break;
            case 1:
                stream.writeShort((Short) value);
                break;
            case 2:
                stream.writeInt((Integer) value);
                break;
            case 3:
                stream.writeFloat((Float) value);
                break;
            case 4:
                stream.writeString((String) value);
                break;
            case 5:
                stream.writeItemStack((ItemStack) value);
                break;
            case 6:
                // stream.writeLocation((Location) value);
                break;
        }
    }

}
