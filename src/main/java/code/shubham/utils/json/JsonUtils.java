package code.shubham.utils.json;

import com.google.gson.Gson;

public class JsonUtils {
    private static final Gson GSON = new Gson();

    public static <T> String convert(T object) {
        return GSON.toJson(object);
    }

    public static <R> R convert(String json, Class<R> clazz) {
        return GSON.fromJson(json, clazz);
    }

    public static <T, R> R convert(T object, Class<R> clazz) {
        return GSON.fromJson(GSON.toJson(object), clazz);
    }
}
