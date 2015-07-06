#!/bin/sh
java -cp libs/junit-4.12.jar;libs/antlr-runtime-4.4.jar;libs/hamcrest-core-1.3.jar;classes org.junit.runner.JUnitCore tests.TestRunner
read -p "Press any key to exit..."