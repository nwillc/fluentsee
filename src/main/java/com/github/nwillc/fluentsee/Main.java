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

package com.github.nwillc.fluentsee;


import joptsimple.OptionParser;
import joptsimple.OptionSet;
import org.pmw.tinylog.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

import static com.github.nwillc.fluentsee.CliOptions.CLI;
import static com.github.nwillc.fluentsee.CliOptions.getOptions;

public final class Main {
    public static void main(String[] args) throws IOException {
        final OptionParser parser = getOptions();
        final OptionSet options = parser.parse(args);
        final AtomicReference<Predicate<Entry>> predicate = new AtomicReference<>(e -> true);
        final Function<Entry, String> output;

        if (options.has(CLI.help.name())) {
            parser.printHelpOn(System.out);
            System.exit(0);
        }

        if (options.has(CLI.match.name())) {
            final List<?> matches = options.valuesOf(CLI.match.name());
            for (Object match : matches) {
                final EntryPredicate newPredicate = EntryPredicate.fromDescription(match.toString());
                predicate.set(newPredicate.and(predicate.get()));
            }
        }

        final String log = (String) options.valueOf(CLI.log.name());

        if (options.has(CLI.verbose.name())) {
            output = e -> e.toVerboseString();
        } else {
            output = e -> e.toString();
        }

        try (Stream<String> stream = Files.lines(Paths.get(log))) {

            stream.forEach(line -> {
                final Entry entry = Parser.parseEntry(line);
                if (predicate.get().test(entry)) {
                    System.out.println(output.apply(entry));
                }
            });

        } catch (IOException e) {
            Logger.error("Exception reading file", e);
        }

    }

    private static void accept(String s) {
        Entry entry = Parser.parseEntry(s);
        System.out.println(entry);
    }
}
