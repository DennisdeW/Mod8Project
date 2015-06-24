package write;

import java.io.File;
import java.io.IOException;

import translation.Program;

/**
 * Runs a <code>Program</code> using GHC. GHC must be in the system's PATH
 * environment variable. All IO from the Haskell/Sprockell program is rerouted
 * to <code>System.[out/err/in]<code>.
 * 
 * @author Dennis
 *
 */
public final class ProgramRunner {

	// Instance variables
	private static final String BASE_PATH = new File("").getAbsolutePath();

	/**
	 * Empty Constructor
	 */
	private ProgramRunner() {
	}

	/**
	 * run() compiles a haskell file and runs the .exe it created
	 * 
	 * @param prog
	 *            The program that needs to be compiled
	 * @param temp
	 *            Boolean whether the created files should be deleted after
	 *            execution
	 */
	private static void run(Program prog, boolean temp) {
		String name = prog.getName();
		if (name == null)
			name = BASE_PATH + "\\sprockell\\prog" + System.currentTimeMillis();
		OutputDebug.write(name + ".hs", prog.getCoreCount(), prog.print());
		File tmpdir = new File("./tmp");
		tmpdir.mkdir();
		ProcessBuilder compileBuilder = new ProcessBuilder("ghc", "-i"
				+ BASE_PATH + "\\sprockell\\Sprockell", "-outputdir ./tmp",
				name + ".hs");
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
}