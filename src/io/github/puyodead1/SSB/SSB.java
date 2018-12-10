package io.github.puyodead1.SSB;

import io.github.puyodead1.SSB.utils.Utils;

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
		} else if (Utils.isUnix()) {
			InitLinux();
		} else if(Utils.isMac()) {
			SSB.Log("Sorry, Mac is currently unsupported! 0_0 D:");
			return;
		} else if(Utils.isSolaris()) {
			SSB.Log("Sorry, Solaris is currntly unsupported! 0_0 D:");
			return;
		}else {
			SSB.Log("Hm, nice OS, unfortunatly its unsupported 0_0 D:, please contact me and let me know what OS this is so I can possibly add it :D");
			return;
		}
	}

	private static void InitWindows() {
		Utils.CheckForDirectory(MainGUI.outputPath);
		Utils.CheckForDirectory(MainGUI.outputPath + "\\SpigotServer");
		Utils.CheckForDirectory(MainGUI.outputPath + "\\BuildTools");
		Log("Checking for BuildTools.jar in BuildTools Directory...");
		if (!Utils.CheckForBuildTools(MainGUI.outputPath)) {
			SSB.Log("GREAT ERROR!");
			System.out.println("GREAT ERROR");
			return;
		}
		// BuildTools.jar found and so was an Existing Spigot jar!
		System.out.println("DEBUG 1");
		Utils.CreateEULA();
		Utils.LauncherHandlerWindows();
		return;
	}

	public static void executeScript(String script) {
		try {
			ProcessBuilder pb = new ProcessBuilder(script);
			Process p = pb.start(); // Start the process.
			p.waitFor(); // Wait for the process to finish.
			System.out.println("Script executed successfully");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void InitLinux() {

	}
}