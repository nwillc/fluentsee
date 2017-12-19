/*
 * Copyright (c) 2017, ADP Inc. All Rights Reserved. This property belongs to ADP Inc. Copying in any form is prohibited.
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
