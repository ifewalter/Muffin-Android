package com.utiset.muffin.models;

import com.google.gson.Gson;

/**
 * Created by ife on 04/06/16.
 */
public class BaseModel {

    public String toJson() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }


    public Object fromJson(String jsonString) {
        Gson gson = new Gson();
        return gson.fromJson(jsonString, this.getClass());

    }
}
