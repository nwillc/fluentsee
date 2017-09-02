# Fluentsee

A simple `fluentd` log parser work in progress.  It's built with `gradle` and Java 1.8,
licensed with the ISC licence, and currently supports filtering and printing.

## Getting Help

Build it and run with the `--help` option:

```bash
$ ./gradlew shadowJar
$ java -jar build/libs/fluentsee-1.0.jar --help
            Option (* = required) Description
            --------------------- -----------
            --help Get command line help.
            * --log  Log file to use.
            --match  Define a match for filtering output. 
                                          May pass in multiple matches.
            --verbose Print verbose format entries.
```

## See Also

- [Collecting Docker Logs with Fluentd](https://nwillc.wordpress.com/2017/08/04/collecting-docker-logs-with-fluentd/)
- [Fluentsee: Fluentd Log Parser](https://nwillc.wordpress.com/2017/09/02/fluentsee-fluentd-log-parser/)


