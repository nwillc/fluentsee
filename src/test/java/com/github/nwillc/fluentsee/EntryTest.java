package com.github.nwillc.fluentsee;


import org.junit.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class EntryTest {

    public static final String TIME_STAMP = "1234567";
    public static final String CONTAINER = "DEADBEEF";
    public static final String JSON = "{ \"foo\": \"bar\" }";

    @Test
    public void testSanity() throws Exception {
        final Entry entry = new Entry(TIME_STAMP, CONTAINER, JSON);

        assertThat(entry.timeStamp).isEqualTo(TIME_STAMP);
        assertThat(entry.container).isEqualTo(CONTAINER);
        assertThat(entry.json.get("foo")).isEqualTo("bar");
    }

    public static Entry getSample() {
        return  new Entry(TIME_STAMP, CONTAINER, JSON);
    }
}