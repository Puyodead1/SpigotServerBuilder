package me.puyodead1.spigotserverbuilder;

import java.io.PrintWriter;
import java.io.StringWriter;

import me.puyodead1.spigotserverbuilder.utils.Utils;

public class SSB {
	public static String rev;
	public static String buildToolsDownloadURL = "https://hub.spigotmc.org/jenkins/job/BuildTools/lastSuccessfulBuild/artifact/target/BuildTools.jar";

	public static void Log(String log) {
		MainGUI.console.append(log + "\n");
	}

	public static void Init() {
		Utils.GetOS();
		if (Utils.isWindows()) {
			Log("Windows Detected");
			InitWindows();
		}
	}

	private static void InitWindows() {
		Utils.CheckForDirectory(MainGUI.outputPath);
		Utils.CheckForDirectory(MainGUI.outputPath + "\\SpigotServer");
		Utils.CheckForDirectory(MainGUI.outputPath + "\\BuildTools");
		Utils.DownloadBuildTools();
	}

	public static void executeScript(String script) {
		try {
			ProcessBuilder pb = new ProcessBuilder(script);
			Process p = pb.start(); // Start the process.
			p.waitFor(); // Wait for the process to finish.
			System.out.println("Script executed successfully");
		} catch (Exception e) {
			StringWriter writer = new StringWriter();
			e.printStackTrace(new PrintWriter(writer));
			SSB.Log(writer.toString());
		}
	}

	private static void InitLinux() {

	}
}