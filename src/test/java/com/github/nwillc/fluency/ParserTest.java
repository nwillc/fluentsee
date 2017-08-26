
package com.github.nwillc.fluency;

import org.junit.Before;
import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Paths;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ParserTest {
    private static final String SAMPLE1 = "/sample1.log";
    private static final String TIME_STAMP = "2017-08-25T22:27:56+00:00";
    private static final String CONTAINER = "7f419fc32a89";
    private static final String JSON = "{\"container_id\":\"7f419fc32a891b2ed7ff1088c529138ddd5729802070f8928cb504e9d68192f2\",\"container_name\":\"/hungry_shockley\",\"source\":\"stdout\",\"log\":\"Hello Fluentd\"}";
    private String sample1;

    @Before
    public void setUp() throws Exception {
        sample1 = new String(Files.readAllBytes(Paths.get(getClass().getResource(SAMPLE1).toURI())));
    }

    @Test
    public void testLineParse() throws Exception {
        assertThat(sample1).isNotEmpty();
        final Entry entry = Parser.parseEntry(sample1);
        assertThat(entry).isNotNull();
        assertThat(entry.timeStamp).isEqualTo(TIME_STAMP);
        assertThat(entry.container).isEqualTo(CONTAINER);
        assertThat(entry.json).isEqualTo(JSON);
    }
}