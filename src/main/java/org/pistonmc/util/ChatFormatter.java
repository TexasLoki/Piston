package org.pistonmc.util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.pistonmc.ChatColor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class ChatFormatter {

    public static String parse(String string) {
        try {
            JSONObject json = new JSONObject(string);
            return parse(json);
        } catch(JSONException ex) {
            return string.substring(1, string.length() - 1);
        }
    }

    public static String parse(JSONObject json) {
        StringBuilder message = new StringBuilder();
        for(ChatColor color : ChatColor.format()) {
            if(json.has(color.getName()) && json.getBoolean(color.getName())) {
                message.append(color);
            }
        }

        if(json.has("color")) {
            message.append(ChatColor.valueOf(json.getString("color").toUpperCase()));
        }

        if(json.has("translate")) {
            String text = json.getString("translate");
            if(json.has("with")) {
                JSONArray array = json.getJSONArray("with");
                String[] translationValues = new String[array.length()];
                for(int i = 0; i < translationValues.length; i++) {
                    Object object = array.get(i);

                    String value;
                    if(object instanceof JSONObject) {
                        value = parse((JSONObject) object);
                    } else {
                        value = (String) object;
                    }

                    translationValues[i] = value;
                }

                text = String.format(text, translationValues);
            }
            message.append(text);
        } else if(json.has("text")) {
            message.append(json.get("text"));
        }

        if(json.has("extra")) {
            JSONArray extra = json.getJSONArray("extra");
            for(int i = 0; i < extra.length(); i++) {
                Object object = extra.get(i);
                if(object instanceof JSONObject) {
                    message.append(parse((JSONObject) object));
                } else {
                    message.append(object);
                }
            }
        }

        return message.toString();
    }

    public static String serialize(String string) {
        String splitter = ChatColor.COLOR_CHAR + "";

        if(!string.contains(splitter)) {
            return '"' + string + '"';
        }

        JSONObject object = new JSONObject();

        String[] split = string.split(splitter);
        Map<ChatColor, String> message = new HashMap<>();
        for(String str : split) {
            ChatColor color = ChatColor.getByChar(str.charAt(0));
            str = str.substring(1);
            message.put(color, str);
        }

        JSONObject current = object;
        List<ChatColor> format = asList(ChatColor.format());
        for(Entry<ChatColor, String> entry : message.entrySet()) {
            ChatColor color = entry.getKey();
            String text = entry.getValue();

            if(format.contains(color)) {
                if(!current.has(color.getName())) {
                    current.put(color.getName(), true);
                }
            } else {
                current.put("color", color.name());
            }

            if((text != null && text.length() > 0) || !format.contains(color)) {
                text = text != null ? text : "";
                current.put("text", text);
                JSONObject extra = new JSONObject();
                current.put("extra", extra);
                current = extra;
            }
        }

        return object.toString(2);
    }

    private static <T> List<T> asList(T[] array) {
        List<T> list = new ArrayList<>();
        for(T value : array) {
            list.add(value);
        }

        return list;
    }

}