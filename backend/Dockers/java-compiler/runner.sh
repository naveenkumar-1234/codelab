#!/bin/sh

# Clean up previous files
rm -f Solution.java Main.java compile_errors.log

# Wait for files to be mounted
sleep 1

javac Solution.java Main.java 2> compile_errors.log

if [ -s compile_errors.log ]; then
    cat compile_errors.log
else
    java Main
fi
