package write;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.ProcessBuilder.Redirect;
import java.util.List;
import java.util.Scanner;

import translation.Program;

/**
 * Runs a <code>Program</code> using GHC. GHC must be in the system's PATH
 * environment variable. All IO from the Haskell/Sprockell program is rerouted
 * to <code>System.[out/err/in]<code>.
 * 
 * @author Ruben Groot Roessink (s1468642) and Dennis de Weerdt (s1420321).
 *
 */
public final class ProgramRunner {

	// Instance variables
	private static final String BASE_PATH = new File("").getAbsolutePath();

	/**
	 * Empty Constructor.
	 */
	private ProgramRunner() {
	}

	/**
	 * run() compiles a haskell file and runs the .exe it created.
	 * 
	 * @param prog
	 *            The program that needs to be compiled.
	 * @param temp
	 *            Boolean whether the created files should be deleted after
	 *            execution.
	 */
	private static void run(Program prog, boolean temp) {
		String name = prog.getName();
		if (name == null)
			name = BASE_PATH + "\\sprockell\\prog" + System.currentTimeMillis();
		OutputDebug.write(name + ".hs", prog.getCoreCount(), prog.print());
		File tmpdir = new File("./tmp");
		tmpdir.mkdir();
		ProcessBuilder compileBuilder = new ProcessBuilder("ghc", "-i" + BASE_PATH + "\\sprockell\\Sprockell",
				"-outputdir ./tmp", name + ".hs");
		compileBuilder.inheritIO();
		Process compile = null;
		try {
			compile = compileBuilder.start();
			compile.waitFor();
		} catch (IOException | InterruptedException e1) {
			e1.printStackTrace();
			return;
		}
		ProcessBuilder runBuilder = new ProcessBuilder(name + ".exe");
		runBuilder.inheritIO();
		Process run = null;
		try {
			run = runBuilder.start();
			run.waitFor();
		} catch (IOException | InterruptedException e1) {
			e1.printStackTrace();
			return;
		}

		if (temp) {
			new File(name + ".hs").delete();
			new File(name + ".exe").delete();
			for (File tmp : tmpdir.listFiles())
				tmp.delete();
		}
	}

	/**
	 * Runs the given <code>Program</code>, giving it a default name.
	 * 
	 * @param prog
	 *            The <code>Program</code> to run.
	 */
	public static void run(Program prog) {
		run(prog, false);
	}

	/**
	 * Runs the given <code>Program</code> and removes the generated files after
	 * execution.
	 * 
	 * @param prog
	 *            The <code>Program</code> to run.
	 */
	public static void runAndRemove(Program prog) {
		run(prog, true);
	}

	/**
	 * Creates a haskell file, compiles it to an .exe file. This file is
	 * executed. The test returns true if the result is 0.
	 * 
	 * @param prog
	 *            The program which needs to be run.
	 * @return Whether the result of the test is 0 or not.
	 */
	public static boolean runTest(Program prog) {
		String name = prog.getName();
		if (name == null)
			name = BASE_PATH + "\\sprockell\\prog" + System.currentTimeMillis();
		Output.write(name + ".hs", prog.getCoreCount(), prog.print());
		File tmpdir = new File("./tmp");
		tmpdir.mkdir();
		ProcessBuilder compileBuilder = new ProcessBuilder("ghc", "-i" + BASE_PATH + "\\sprockell\\Sprockell",
				"-outputdir ./tmp", name + ".hs");
		Process compile = null;
		try {
			System.out.printf("Compiling %s...", name);
			compile = compileBuilder.start();
			compile.waitFor();
			System.out.println("Done");
		} catch (IOException | InterruptedException e1) {
			e1.printStackTrace();
			return false;
		}
		ProcessBuilder runBuilder = new ProcessBuilder(name + ".exe");

		StringBuilder output = new StringBuilder();
		try {
			System.out.printf("Running %s...", name);
			Process run = runBuilder.start();
			new Thread(() -> {
				BufferedReader reader = new BufferedReader(new InputStreamReader(run.getInputStream()));
				try {
					String line;
					while ((line = reader.readLine()) != null)
						output.append(line);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}).start();
			new Thread(() -> {
				BufferedReader reader = new BufferedReader(new InputStreamReader(run.getErrorStream()));
				try {
					String line;
					while ((line = reader.readLine()) != null)
						;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}).start();
			run.waitFor();
			if (output.toString().equals("")) {
				System.out.println("No response.");
				return false;
			}
			int result = Integer.parseInt(output.toString());
			System.out.println("Done: " + result);
			return result == 0;
		} catch (IOException | InterruptedException e1) {
			e1.printStackTrace();
			return false;
		}
	}
}