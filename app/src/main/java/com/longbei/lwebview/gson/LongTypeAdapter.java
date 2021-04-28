package com.longbei.lwebview.gson;

import android.util.Log;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

/**
 * @author liuml
 * @explain
 * @time 2018/4/2 14:47
 */

public class LongTypeAdapter extends TypeAdapter {
    @Override
    public void write(JsonWriter out, Object value) {
        try {
            if (value == null){
                value = 0L;
            }
            out.value(value.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Long read(JsonReader in){
        try {
            Long value;
            if (in.peek() == JsonToken.NULL) {
                in.nextNull();
                Log.e("TypeAdapter", "null is not a number");
                return 0L;
            }
            if (in.peek() == JsonToken.BOOLEAN) {
                boolean b = in.nextBoolean();
                Log.e("TypeAdapter", b + " is not a number");
                return 0L;
            }
            if (in.peek() == JsonToken.STRING) {
                String str = in.nextString();
                if (StringUtils.isInt(str)){
                    return Long.parseLong(str);
                } else {
                    Log.e("TypeAdapter", str + " is not a int number");
                    return 0L;
                }
            } else {
                value = in.nextLong();
                return value;
            }
        } catch (Exception e) {
            Log.e("TypeAdapter", "Not a number", e);
        }
        return 0L;
    }
}