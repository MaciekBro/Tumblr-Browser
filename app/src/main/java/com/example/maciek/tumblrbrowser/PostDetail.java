package com.example.maciek.tumblrbrowser;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Maciek on 2017-03-13.
 */
public class PostDetail {

    String id;
    String url;
    String date;
    String type;

    public String getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }

    public String getDate() {
        return date;
    }

    public String getType() {
        return type;
    }
}
