package com.longbei.lwebview.gson;

import android.util.Log;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

/**
 * @author liuml
 * @explain
 * @time 2018/4/2 14:44
 */

public class IntegerTypeAdapter extends TypeAdapter {

    @Override
    public void write(JsonWriter out, Object value) throws IOException {
        try {
            if (value == null){
                value = 0;
            }
            out.value(value.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    @Override
    public Integer read(JsonReader in) {
        try {
            Integer value;
            if (in.peek() == JsonToken.NULL) {
                in.nextNull();
                Log.e("TypeAdapter", "null is not a number");
                return 0;
            }
            if (in.peek() == JsonToken.BOOLEAN) {
                boolean b = in.nextBoolean();
                Log.e("TypeAdapter", b + " is not a number");
                return 0;
            }
            if (in.peek() == JsonToken.STRING) {
                String str = in.nextString();
                if (StringUtils.isInt(str)){
                    return Integer.parseInt(str);
                } else {
                    Log.e("TypeAdapter", str + " is not a int number");
                    return 0;
                }
            } else {
                value = in.nextInt();
                return value;
            }
        } catch (Exception e) {
            Log.e("TypeAdapter", "Not a number", e);
        }
        return 0;
    }
}
