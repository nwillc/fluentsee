

package com.github.nwillc.fluency;


import org.junit.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class EntryTest {

    private static final String TIME_STAMP = "1234567";
    private static final String CONTAINER = "DEADBEEF";
    private static final String JSON = "{}";

    @Test
    public void testSanity() throws Exception {
        final Entry entry = new Entry(TIME_STAMP, CONTAINER, JSON);

        assertThat(entry.timeStamp).isEqualTo(TIME_STAMP);
        assertThat(entry.container).isEqualTo(CONTAINER);
        assertThat(entry.json).isEqualTo(JSON);
    }
}