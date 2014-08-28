package org.pistonmc.util;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Lists;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class OtherUtils {

    public static <T> List<T> newList(T... array) {
        return Lists.newArrayList(array);
    }

    public static <T> List<T> asList(T[] array) {
        List<T> list = new ArrayList<>();
        for(T value : array) {
            list.add(value);
        }

        return list;
    }

    public static List<Byte> asList(byte[] array) {
        List<Byte> list = new ArrayList<>();
        for(byte value : array) {
            list.add(value);
        }

        return list;
    }

    public static <T> ArrayList<T> reverse(List<T> list) {
        ArrayList<T> reversed = new ArrayList<>();
        for(int i = list.size() - 1; i >= 0; i--) {
            reversed.add(list.get(i));
        }

        return reversed;
    }

    public static <T> T[] reverse(T[] array) {
        int i2 = 0;
        T[] reversed = create(array, array.length);
        for(int i = array.length - 1; i >= 0; i--, i2++) {
            reversed[i2] = array[i];
        }

        return reversed;
    }

    public static <K, V> HashMap<K, V> reverse(Map<K, V> map) {
        HashMap<K, V> reversed = new HashMap<>();
        List<Entry<K, V>> entries = Lists.newArrayList(map.entrySet());
        for(int i = map.size() - 1; i >= 0; i--) {
            Entry<K, V> entry = entries.get(i);
            reversed.put(entry.getKey(), entry.getValue());
        }

        return reversed;
    }

    public static <K, V> ArrayListMultimap<K, V> reverse(ArrayListMultimap<K, V> map) {
        ArrayListMultimap<K, V> reversed = ArrayListMultimap.create();
        List<Entry<K, V>> entries = Lists.newArrayList(map.entries());
        for(int i = map.size() - 1; i >= 0; i--) {
            Entry<K, V> entry = entries.get(i);
            reversed.put(entry.getKey(), entry.getValue());
        }

        return reversed;
    }

    @SuppressWarnings("unchecked")
    public static <T> T[] create(Class<T> cls, int size) {
        return (T[]) Array.newInstance(cls, size);
    }

    @SuppressWarnings("unchecked")
    public static <T> T[] create(T[] array, int size) {
        return (T[]) create(array.getClass().getComponentType(), size);
    }

}
