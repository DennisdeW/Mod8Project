package write;

import java.io.File;
import java.io.IOException;

import translation.Program;

/**
 * Runs a <code>Program</code> using GHC. GHC must be in the system's PATH environment variable.
 * All IO from the Haskell/Sprockell program is rerouted to <code>System.[out/err/in]<code>.
 * @author Dennis
 *
 */
public final class ProgramRunner {

	private static final String BASE_PATH = new File("").getAbsolutePath();

	private ProgramRunner() {

	}

	private static void run(Program prog, String name, boolean temp) {
		OutputDebug.write(name + ".hs", prog.print());
		File tmpdir = new File("./tmp");
		tmpdir.mkdir();
		ProcessBuilder compileBuilder = new ProcessBuilder("ghc", "-i"
				+ BASE_PATH + "\\sprockell\\Sprockell", "-outputdir ./tmp", name + ".hs");
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
	 * @param prog The <code>Program</code> to run.
	 */
	public static void run(Program prog) {
		String file = BASE_PATH + "\\sprockell\\prog"
				+ System.currentTimeMillis();
		run(prog, file, false);
	}
	
	/**
	 * Runs the given <code>Program</code>, naming the generated files [name].hs and [name].exe 
	 * @param prog The <code>Program</code> to run.
	 * @param name The name of the <code>Program</code>.
	 */
	public static void run(Program prog, String name) {
		run(prog, name, false);
	}
	
	/**
	 * Runs the given <code>Program</code> and removes the generated files after execution.
	 * @param prog Thr <code>Program</code> to run.
	 */
	public static void runAndRemove(Program prog) {
		String file = BASE_PATH + "\\sprockell\\prog"
				+ System.currentTimeMillis();
		run(prog, file, true);
	}
}
