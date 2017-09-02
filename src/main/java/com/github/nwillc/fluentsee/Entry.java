package com.github.nwillc.fluentsee;

import java.util.Map;

public class Entry {
    public final String timeStamp;
    public final String container;
    public final Map json;

    public Entry(String timeStamp, String container, String json) {
        this.timeStamp = timeStamp;
        this.container = container;
        this.json = JsonUtil.toMap(json);
    }

    @Override
    public String toString() {
        return json.get("log").toString();
    }

    public String toVerboseString() {
        return json.get("container_name") + " " + toString();
    }
}
