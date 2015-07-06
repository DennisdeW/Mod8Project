package frontend;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Collectors;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.RecognitionException;

import parsing.BaseGrammarLexer;
import parsing.BaseGrammarParser;
import parsing.BaseGrammarParser.ProgramContext;
import parsing.Checker;
import parsing.Checker.CheckResult;
import parsing.Generator;
import translation.Program;
import write.Output;
import write.OutputDebug;

/**
 * FrontEnd makes it possible to run the program on the command line. Compiles a
 * program and runs it.
 * 
 * @author Ruben Groot Roessink (s1468642) and Dennis de Weerdt (s1420321).
 */
public class FrontEnd {

	public static final String	USAGE	= "Usage: java -jar g1c.jar <source-file> [-debug] [-stdlib <std-lib-path>]\n"
												+ "<source-file>: The absolute or relative path to the source file.\n"
												+ "-debug (optional): Compile with debugging enabled.\n"
												+ "-stdlib (optional): Use the specified file as standard library. If omitted, the compiler"
												+ " looks for stdlib.txt in the current working directory.";
	public static final File	STDLIB	= new File("./stdlib.txt");

	/**
	 * Empty private Constructor.
	 */
	private FrontEnd() {
	}

	/**
	 * Main method is the main method of the program. It takes a source file as
	 * input containing a program in our programming language.
	 * 
	 * @param args
	 *            The arguments of the main method.
	 */
	public static void main(String[] args) {
		if (args.length < 1) {
			System.out.println(USAGE);
			System.exit(0);
		}
		String sourceName = args[0];
		File source = new File(sourceName);
		if (!source.exists() || !source.isFile()) {
			System.out
					.println("Error: Input file does not exist or is a directory.");
			System.out.println("Exiting...");
			System.exit(0);
		}
		if (!source.canRead()) {
			System.out.println("Error: Input file cannot be read.");
			System.out.println("Exiting...");
			System.exit(0);
		}
		String stdlibPath = null;
		boolean debug = false;
		for (int i = 1; i < args.length; i++) {
			String arg = args[i];
			if (arg.equals("-debug"))
				debug = true;
			else if (arg.equals("-stdlib"))
				stdlibPath = args[++i];
			else {
				System.err.println("Error: Unrecognized option: " + arg);
				System.exit(1);
			}
		}
		ProgramContext progCtx = null;
		try {
			File stdlib = stdlibPath == null ? STDLIB : new File(stdlibPath);
			progCtx = new BaseGrammarParser(new CommonTokenStream(
					new BaseGrammarLexer(merge(source, stdlib)))).program();
		} catch (RecognitionException e) {
			System.out.flush();
			System.err.println("An exception occured while parsing the file: ");
			e.printStackTrace();
			System.exit(1);
		}
		Checker checker = new Checker();
		CheckResult cres = checker.check(progCtx);
		if (checker.hasErrors()) {
			System.out.flush();
			System.err.println("Parse errors were found: ");
			checker.getErrors().forEach(e -> System.err.println(e));
			System.err.println("Exiting...");
			System.exit(0);
		}
		Generator gen = new Generator();
		System.out.print("Input file is valid, compiling .hs file...");
		Program prog = gen.compile(progCtx, cres);

		String name = prog.getName();
		int cores = prog.getCoreCount();
		if (debug) {
			OutputDebug.write(name + ".hs", cores, prog.print());
		} else {
			Output.write(name + ".hs", cores, prog.print());
		}
		System.out.println("Done");
		System.out.println("Invoking GHC...");
		try {
			ProcessBuilder compileBuilder = new ProcessBuilder("ghc", "-i"
					+ ".\\sprockell\\Sprockell", "-outputdir ./tmp", name
					+ ".hs");
			compileBuilder.inheritIO();
			Process ghc = compileBuilder.start();
			ghc.waitFor();
			File tmpdir = new File("tmp");
			for (File tmp : tmpdir.listFiles())
				tmp.delete();
			tmpdir.delete();
			System.out.println("Done");
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Merge merges the given program with the standard library.
	 * 
	 * @param source
	 *            The given program.
	 * @param stdlib
	 *            The standard library.
	 * @return The ANTLRInputStream.
	 */
	private static ANTLRInputStream merge(File source, File stdlib) {
		if (!stdlib.canRead()) {
			System.err
					.println("Standard library not found or cannot be read, exiting...");
			System.exit(0);
		}
		;
		ANTLRInputStream result = null;
		try (BufferedReader progReader = new BufferedReader(new FileReader(
				source));
				BufferedReader libReader = new BufferedReader(new FileReader(
						stdlib))) {
			String prog = progReader.lines().collect(Collectors.joining("\n"));
			String lib = libReader.lines().collect(Collectors.joining("\n"));
			result = new ANTLRInputStream(prog + "\n\n" + lib);
		} catch (UncheckedIOException | IOException e) {
			System.err.println("An exception occured reading the input "
					+ "file or the standard library: ");
			e.printStackTrace();
		}
		return result;
	}
}