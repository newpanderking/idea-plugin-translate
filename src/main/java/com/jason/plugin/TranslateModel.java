
package com.jason.plugin;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

/**
 * @author <a href="mailto:jason.wzr@alibaba-inc.com">玄翰</a>
 * @version v 0, TranslateModel.java
 * @since 2019年11月28日 2:52 下午
 */
public class TranslateModel {

    public static String transfer(String json) {
        Gson gson = new Gson();
        JsonObject object = gson.fromJson(json, JsonObject.class);
        StringBuilder sb = new StringBuilder();
        sb.append("<ol>");
        sb.append("<li>");

        sb.append("释义：").append(getTranslateStr(object));
        sb.append("</li>");
        if (object.keySet().contains("web")) {
            sb.append("<li>");
            sb.append("网络释义：\n").append(getWebTranslateStr(object));
            sb.append("</li>");
        }
        sb.append("</ol>");
        return sb.toString();
    }

    private static String getTranslateStr(JsonObject object) {
        JsonArray array = object.getAsJsonArray("translation");
        StringBuilder sb = new StringBuilder();
        array.forEach(item -> sb.append(item.toString()).append(","));
        return sb.toString();
    }

    private static String getWebTranslateStr(JsonObject object){
        JsonArray array = object.getAsJsonArray("web");
        StringBuilder sb = new StringBuilder();
        array.forEach(item -> {
            sb.append(item.getAsJsonObject().get("key").getAsString()).append(":");
            JsonArray values = item.getAsJsonObject().getAsJsonArray("value");
            values.forEach(value->sb.append(value).append(","));
            sb.append("\n");
        });
        return sb.toString();
    }

}