package write;

import java.io.File;
import java.io.IOException;

import translation.Program;

public final class ProgramRunner {

	private static final String BASE_PATH = new File("").getAbsolutePath();

	private ProgramRunner() {

	}

	public static void run(Program prog) {
		String file = BASE_PATH + "\\sprockell\\prog"
				+ System.currentTimeMillis();
		OutputDebug.write(file + ".hs", prog.print());
		ProcessBuilder compileBuilder = new ProcessBuilder("ghc", "-i"
				+ BASE_PATH + "\\sprockell\\Sprockell", file + ".hs");
		compileBuilder.inheritIO();
		Process compile = null;
		try {
			compile = compileBuilder.start();
			compile.waitFor();
		} catch (IOException | InterruptedException e1) {
			e1.printStackTrace();
			return;
		}
		ProcessBuilder runBuilder = new ProcessBuilder(file + ".exe");
		runBuilder.inheritIO();
		Process run = null;
		try {
			run = runBuilder.start();
			run.waitFor();
		} catch (IOException | InterruptedException e1) {
			e1.printStackTrace();
			return;
		}
	}

}
