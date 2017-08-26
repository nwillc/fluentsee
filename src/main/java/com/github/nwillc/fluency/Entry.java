
package com.github.nwillc.fluency;

public class Entry {
    public final String timeStamp;
    public final String container;
    public final String json;

    public Entry(String timeStamp, String container, String json) {
        this.timeStamp = timeStamp;
        this.container = container;
        this.json = json;
    }
}
