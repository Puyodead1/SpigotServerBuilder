package io.github.puyodead1.SSB.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.eclipse.swt.widgets.Display;

import io.github.puyodead1.SSB.MainGUI;
import io.github.puyodead1.SSB.SSB;

public class CompileBT extends Thread {

	public static Process proc;
	private String cmd;

	public void update(String string) {
		Display.getDefault().asyncExec(new Runnable() {
			public void run() {
				SSB.Log(string);
			}
		});
	}

	public void run() {
		Utils.GetOS();
		if (Utils.isWindows()) {
			cmd = String.format("cmd /c cd /d %s\\BuildTools && java -jar BuildTools.jar --rev %s ", MainGUI.outputPath,
					MainGUI.version);
		} else if (Utils.isMac() || Utils.isUnix()) {
			String.format("cd %s\\BuildTools && java -jar BuildTools.jar --rev %s ", MainGUI.outputPath,
					MainGUI.version);
		} else {
			update("OS Not supported.");
			return;
		}
		try {
			proc = Runtime.getRuntime()
					.exec(String.format("cmd /c cd /d %s\\BuildTools && java -jar BuildTools.jar --rev %s ",
							MainGUI.outputPath, MainGUI.version));

			InputStream inputStream = proc.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

			String line = null;

			while ((line = bufferedReader.readLine()) != null) {
				update(line);
			}
			
			proc.waitFor();
			
			if(proc.exitValue() == 0) {
				Display.getDefault().asyncExec(new Runnable() {
					public void run() {
						Utils.CopySpigotJar();
						Utils.CreateEULA();
						Utils.LauncherHandlerWindows();
					}
				});
			}

		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
			this.interrupt();
		}
	}
}
