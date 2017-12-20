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

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.UncheckedIOException;
import java.util.function.Consumer;

public class FileReader implements Runnable {
    private long position;
    private final File file;
    private final Consumer<String> lines;
    private boolean tail;

    public FileReader(File file, Consumer<String> lines) {
        position = 0L;
        this.file = file;
        this.lines = lines;
    }

    public FileReader(File file, Consumer<String> lines, boolean tail) {
        this(file, lines);
        this.tail = tail;
    }

    public void read() throws InterruptedException {
        final Thread thread = new Thread(this);
        thread.start();
        thread.join();
    }
    @Override
    public void run() {
        try {
            do {
                final long length = file.length();
                if (position > length) {
                    position = 0L;
                }

                if (length > position) {
                    try (RandomAccessFile raf = new RandomAccessFile(file, "r")) {
                        raf.seek(position);
                        String line;
                        while ((line = raf.readLine()) != null) {
                            lines.accept(line);
                        }
                        position = raf.getFilePointer();
                    }
                }
                try {
                    Thread.sleep(100L);
                } catch (InterruptedException e) {
                    // ignored
                }
            } while (tail);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}
