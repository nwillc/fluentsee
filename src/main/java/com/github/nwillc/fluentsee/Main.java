package com.github.nwillc.fluentsee;


import joptsimple.OptionParser;
import joptsimple.OptionSet;

import java.io.IOException;

public final class Main {
    public static void main(String[] args) throws IOException {
        final OptionParser parser = CliOptions.getOptions();
        final OptionSet options = parser.parse(args);

        if (options.has(CliOptions.CLI.help.name())) {
            parser.printHelpOn(System.out);
            System.exit(0);
        }
    }
}
