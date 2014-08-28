package org.pistonmc.protocol.data;

public enum DataType {

    BYTE(0),
    SHORT(1),
    INT(2),
    FLOAT(3),
    STRING(4),
    SLOT(5),
    LOCATION(6);

    private byte type;

    DataType(int type) {
        this.type = (byte) type;
    }

    DataType(byte type) {
        this.type = type;
    }

    public byte getType() {
        return type;
    }

    public static DataType valueOf(byte id) {
        for (DataType type : values()) {
            if (type.getType() == id) {
                return type;
            }
        }

        return null;
    }

}
