/*
 * Copyright (c) 2017, ADP Inc. All Rights Reserved. This property belongs to ADP Inc. Copying in any form is prohibited.
 */

package com.github.nwillc.fluentsee.util;

import org.junit.Before;
import org.junit.Test;

import java.io.File;

import static org.assertj.core.api.Assertions.assertThat;

public class FileReaderTest {
    private File target;

    @Before
    public void setUp() throws Exception {
        target = new File("src/test/resources/sample1.log");
    }

    @Test
    public void tailTest() throws Exception {
        assertThat(target).isNotNull();
        final FileReader tailer = new FileReader(target, System.out::println);
        tailer.read();
    }
}
