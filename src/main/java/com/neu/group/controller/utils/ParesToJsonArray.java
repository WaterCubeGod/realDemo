package com.neu.group.controller.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.neu.group.domain.Option;

import java.util.List;

public class ParesToJsonArray {
    public static <T> JSONArray parseToJsonArray(List<T> items) {
        JSONArray jsonArray = new JSONArray();
        for (T item : items) {
            jsonArray.add(JSONObject.toJSONString(item));
        }
        return jsonArray;
    }
}
