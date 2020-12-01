package com.conteo.api.utils.utils;


import com.google.gson.Gson;

public class ParseUtil {
    public static <T> String objectLog(T object) {
        return new Gson().toJson(object);
    }
}
