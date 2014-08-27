package org.pistonmc.protocol.data;

import org.pistonmc.protocol.stream.PacketOutputStream;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Metadata {

    private Map<Integer, DataObject> objects;

    public Metadata() {
        this.objects = new HashMap<>();
    }

    public DataObject getObject(int index) {
        return objects.get(index);
    }

    public void put(int index, DataObject object) {
        if(object == null) {
            return;
        }

        objects.putIfAbsent(index, object);
    }

    public void write(PacketOutputStream stream) throws IOException {
        for(DataObject object : objects.values()) {
            int header = (object.getType().getType() << 5 | object.getType().getType() & 0x1f) & 0xff;
            stream.writeByte(header);
            object.write(stream);
        }

        stream.writeByte(127);
    }

    public boolean isEmpty() {
        return objects.isEmpty();
    }

}
