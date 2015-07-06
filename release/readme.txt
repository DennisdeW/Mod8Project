Usage of the compiler:

java -jar g1c.jar <source-file> [-debug] [-stdlib <std-lib-path>]

<source-file>: The absolute or relative path to the source file.
-debug (optional): Compile with debugging enabled.
-stdlib (optional): Use the specified file as standard library. If omitted, the compiler
		    looks for stdlib.txt in the current working directory.

This will generate an executable file. If the debug option is specified, it is recommended that
the standard error stream of the program is rerouted to a file. (E.g: program 2> log.txt)
Also note that saving debug data will dramatically slow down the program.

Check either the report or the test files for example programs.

The following command will invoke the JUnit tests:

	java -cp libs/junit-4.12.jar;libs/antlr-runtime-4.4.jar;libs/hamcrest-core-1.3.jar;classes org.junit.runner.JUnitCore tests.TestRunner

For convenience, this command is also provided as .bat and .sh files.