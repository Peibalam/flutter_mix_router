package com.example.myfluttermoduleapp.helper;

import android.text.TextUtils;

import com.google.gson.Gson;

public class GsonUtil {

    private static Gson gson = new Gson();

    public static Gson getGson() {
        return gson;
    }

    public static <T> T fromJson(String json, Class<T> clazz) {
        if (TextUtils.isEmpty(json)) {
            return null;
        }
        return gson.fromJson(json, clazz);
    }


    public static String toJson(Object o) {
        return gson.toJson(o);
    }

}
