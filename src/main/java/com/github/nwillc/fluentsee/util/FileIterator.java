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


import java.io.*;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class FileIterator implements Iterator<String>, AutoCloseable {
    private static final int WAIT_MILLIS = 200;
    private final FileReader fileReader;
    private final BufferedReader reader;
    private final boolean tail;
    private String nextLine = null;

    public FileIterator(String path) throws FileNotFoundException {
        this(path, false);
    }

    public FileIterator(String path, boolean tail) throws FileNotFoundException {
        fileReader = new FileReader(path);
        reader = new BufferedReader(fileReader);
        this.tail = tail;
    }

    @Override
    public boolean hasNext() {
        if (tail || nextLine != null) {
            return true;
        } else {
            nextLine = readLine();
            return nextLine != null;
        }
    }

    @Override
    public String next() {
        if (nextLine != null || hasNext() || tail) {
            while (nextLine == null) {
                try {
                    Thread.sleep(WAIT_MILLIS);
                } catch (InterruptedException e) {
                    // ignore
                }
                nextLine = readLine();
            }
            String line = nextLine;
            nextLine = null;
            return line;
        } else {
            throw new NoSuchElementException();
        }
    }

    private String readLine() {
        try {
            return reader.readLine();
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    @Override
    public void close() throws Exception {
        fileReader.close();
        reader.close();
    }
}
