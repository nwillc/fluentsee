package com.github.nwillc.fluentsee;


import joptsimple.OptionParser;
import joptsimple.OptionSet;
import org.pmw.tinylog.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Predicate;
import java.util.stream.Stream;

import static com.github.nwillc.fluentsee.CliOptions.*;

public final class Main {
    public static void main(String[] args) throws IOException {
        final OptionParser parser = getOptions();
        final OptionSet options = parser.parse(args);
        final AtomicReference<Predicate<Entry>> predicate = new AtomicReference<>(e -> true);

        if (options.has(CLI.help.name())) {
            parser.printHelpOn(System.out);
            System.exit(0);
        }

        if (options.has(CLI.match.name())) {
            final List<?> matches = options.valuesOf(CLI.match.name());
            for (Object match : matches) {
                predicate.set(EntryPredicate.fromDescription(match.toString()));
            }
        }

        final String log = (String) options.valueOf(CLI.log.name());

        try (Stream<String> stream = Files.lines(Paths.get(log))) {

            stream.forEach(line -> {
                final Entry entry = Parser.parseEntry(line);
                if (predicate.get().test(entry)) {
                    System.out.println(entry);
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
