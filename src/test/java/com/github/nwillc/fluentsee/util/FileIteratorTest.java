/*
 * Copyright 2017 nwillc@gmail.com
 *
 * Permission to use, copy, modify, and/or distribute this software for any purpose with or without fee is
 * hereby granted, provided that the above copyright notice and this permission notice appear in all copies.
 *
 * THE SOFTWARE IS PROVIDED "AS IS" AND THE AUTHOR DISCLAIMS ALL WARRANTIES WITH REGARD TO THIS SOFTWARE
 * INCLUDING ALL IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS. IN NO EVENT SHALL THE AUTHOR BE LIABLE
 * FOR ANY SPECIAL, DIRECT, INDIRECT, OR CONSEQUENTIAL DAMAGES OR ANY DAMAGES WHATSOEVER RESULTING FROM
 * LOSS OF USE, DATA OR PROFITS, WHETHER IN AN ACTION OF CONTRACT, NEGLIGENCE OR OTHER TORTIOUS ACTION,
 * ARISING OUT OF OR IN CONNECTION WITH THE USE OR PERFORMANCE OF THIS SOFTWARE.
 */

package com.github.nwillc.fluentsee.util;

import com.github.nwillc.contracts.IteratorContract;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.UncheckedIOException;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class FileIteratorTest extends IteratorContract {

    public static final int EXPECTED_SIZE = 3521;

    @Override
    protected Iterator getNonEmptyIterator() {
        try {
            return new FileIterator("src/test/resources/sample1.log");
        } catch (FileNotFoundException e) {
            throw new UncheckedIOException(e);
        }
    }

    @Test
    public void testBadFile() throws Exception {
        assertThatThrownBy(() -> {
            new FileIterator("foo");
        }).isInstanceOf(FileNotFoundException.class);
    }

    @Test
    public void testGoodFile() throws Exception {
        new FileIterator("src/test/resources/sample1.log");
    }

    @Test
    public void testIterateFile() throws Exception {
        final FileIterator fileIterator = new FileIterator("src/test/resources/sample2.log");

        assertThat(fileIterator.hasNext()).isTrue();
        int lines = 0;

        while (fileIterator.hasNext()) {
            fileIterator.next();
            lines++;
        }

        assertThat(lines).isEqualTo(EXPECTED_SIZE);
    }

    @Test
    public void testInStream() throws Exception {
        final FileIterator fileIterator = new FileIterator("src/test/resources/sample2.log");

        final Spliterator<String> stringSpliterator = Spliterators.spliteratorUnknownSize(fileIterator, 0);
        final Stream<String> stream = StreamSupport.stream(stringSpliterator, false);

        assertThat(stream.count()).isEqualTo(EXPECTED_SIZE);
    }
}