package io.github.puyodead1.SSB.utils;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.apache.commons.io.FileUtils;

import io.github.puyodead1.SSB.DownloadURL;
import io.github.puyodead1.SSB.MainGUI;
import io.github.puyodead1.SSB.SSB;

public class Utils {
	public static String OS;
	private static File msysDir;

	public static void Complete() {
		SSB.Log(">>>>>>> --------------------------------------------------------------------------- <<<<<<<");
		SSB.Log(">>>>>>> Complete! Please use the Launcher.bat file to start your new server! Enjoy! <<<<<<<");
		SSB.Log(">>>>>>> --------------------------------------------------------------------------- <<<<<<<");
	}

	public static boolean CreateEULA() {
		SSB.Log(">>>>>>> ------------------------------------------------------- <<<<<<<");
		SSB.Log(">>>>>>> PLEASE MAKE SURE YOU READ AND AGREE TO THE Mojang EULA! <<<<<<<");
		SSB.Log(">>>>>>> ------------------------------------------------------- <<<<<<<");
		SSB.Log("Continuing in 5 seconds...");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e1) {
			SSB.Log(e1.getMessage());
		}
		if (!Utils.CheckForFile(MainGUI.outputPath + "\\SpigotServer\\eula.txt")) {
			// EULA file doesn't exist, we create it here
			try {
				PrintWriter pw = new PrintWriter(MainGUI.outputPath + "\\SpigotServer\\eula.txt");
				pw.write("eula=true");
				pw.close();
				SSB.Log("EULA Re-Write complete...");
				// EULA Created successfully!
				return true;
			} catch (IOException e) {
				// Error creating that EULA file 0_0
				SSB.Log("Error creating the EULA.txt file!" + e.getMessage());
				return false;
			}
		} else {
			// EULA file does exist, we can skip to launching the server! YAY!
			SSB.Log("Found EULA, Deleting and Re-Writing.");
			File eula = new File(MainGUI.outputPath + "\\SpigotServer\\eula.txt");
			eula.delete();
			try {
				eula.createNewFile();
				PrintWriter pw = new PrintWriter(MainGUI.outputPath + "\\SpigotServer\\eula.txt");
				pw.write("eula=true");
				pw.close();
				SSB.Log("EULA Re-Write Complete!");
				return true;
			} catch (IOException e) {
				e.printStackTrace();
				return false;
			}
		}
	}

	public static boolean CheckForFile(String path) {
		File in = new File(path);
		if (!in.exists()) {
			// return false If we don't find file
			return false;
		} else {
			// return true If we find file
			return true;
		}
	}

	public static boolean CheckForBuildTools(String path) {
		String btpath = path + "\\BuildTools\\BuildTools.jar";
		File in = new File(btpath);
		if (!in.exists()) {
			SSB.Log("BuildTools.jar not found, Checking for Spigot");
			Utils.CheckForSpigotInBT(MainGUI.outputPath);
			return false;
		} else {
			// true. we found BT
			SSB.Log("BuildTools.jar found, Checking for Spigot");
			Utils.CheckForSpigotInBT(MainGUI.outputPath);
			return true;
		}
	}

	public static boolean CheckForSpigotInBT(String path) {
		String spath = path + "\\BuildTools\\spigot-" + MainGUI.version + ".jar";
		File in = new File(spath);
		if (!in.exists()) {
			SSB.Log("We didn't find Spigot :(");
			DownloadBuildTools();
			runBT();
			return false;
		} else {
			// return true If we find file
			SSB.Log("Found Existing Spigot jar. Using it!");
			// TODO: COPY SPIGOT
			CopyFile(MainGUI.outputPath + "\\BuildTools\\spigot-1.12.2.jar",
					MainGUI.outputPath + "\\SpigotServer\\spigot-1.12.2.jar");
			return true;
		}
	}

	public static boolean LauncherHandlerWindows() {
		if (CheckForFile(MainGUI.outputPath + "\\SpigotServer\\Launcher.bat")) {
			// true if exists
			SSB.Log("Launcher exists.. Skipping...");
			Complete();
			return true;
		} else {
			// false if no
			File launcher = new File(MainGUI.outputPath + "\\SpigotServer\\Launcher.bat");
			try {
				launcher.createNewFile();
				PrintWriter pw = new PrintWriter(MainGUI.outputPath + "\\SpigotServer\\Launcher.bat");
				pw.write("java -jar spigot-" + MainGUI.version + ".jar");
				pw.close();
				SSB.Log("Launcher write complete.");
				Complete();
				return true;
			} catch (IOException e) {
				e.printStackTrace();
				return false;
			}
		}
	}

	public static boolean LauncherHandlerLinux() {
		return false;
	}

	public static boolean CheckForDirectory(String path) {
		File in = new File(path);
		if (!in.exists()) {
			// false not exists
			try {
				CreateDirectory(path);
				SSB.Log("Created Directory at: " + path);
				return true;
			} catch (Exception e) {
				SSB.Log(e.getMessage());
				System.exit(1);
			}
			return false;
		} else {
			// return true If we find file
			SSB.Log("Directory Exists!");
			return true;
		}
	}

	public static boolean CreateDirectory(String path) {
		try {
			Files.createDirectory(Paths.get(path));
			return true;
		} catch (IOException e) {
			if (e.toString().contains("FileAlreadyExists")) { // TODO this might be a bad way of checking...
				SSB.Log("Directory already exists! Skipping...");
				return false;
			} else {
				SSB.Log("Oops, an error occured creating directory...");
				SSB.Log(e.toString());
				return false;
			}
		}
	}

	public static boolean DownloadBuildTools() {
		if (DownloadURL.Download(
				"https://hub.spigotmc.org/jenkins/job/BuildTools/lastSuccessfulBuild/artifact/target/BuildTools.jar",
				MainGUI.outputPath + "\\BuildTools\\BuildTools.jar")) {
			SSB.Log("Downloaded BuildTools.jar!");
			return true; // if Success
		} else {
			SSB.Log("Failed to download BuildTools.jar! Sleeping for 10 seconds then exiting!");
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				SSB.Log(e.getMessage());
			}
			System.exit(1);
			return false;// iF Fail
		}
	}

	public static boolean DownloadBuildToolsLinux() {
		return false;
	}

	public static void runBT() {
		String[] command = { "CMD", "/C",
				"cd /d" + MainGUI.outputPath + "\\BuildTools && java -jar BuildTools.jar" };
		try {
			ProcessBuilder builder = new ProcessBuilder(command);
			// builder.redirectErrorStream(true);
			SSB.Log("Waiting...");
			Process p = builder.start();
			p.waitFor();
			SSB.Log(p.getInputStream().toString());
			System.out.println(p.getInputStream().read());
			try {
				int exitValue = p.waitFor();
				System.out.println("\n\nExit Value is " + exitValue);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			SSB.Log("Done...");
		} catch (Exception e) {
			e.printStackTrace();
			SSB.Log("Error");
			System.exit(1);
		}
	}

	public static boolean isWindows() {

		return (OS.indexOf("win") >= 0);

	}

	public static boolean isMac() {

		return (OS.indexOf("mac") >= 0);

	}

	public static boolean isUnix() {

		return (OS.indexOf("nix") >= 0 || OS.indexOf("nux") >= 0 || OS.indexOf("aix") > 0);

	}

	public static boolean isSolaris() {

		return (OS.indexOf("sunos") >= 0);

	}

	public static void GetOS() {
		OS = System.getProperty("os.name").toLowerCase();
	}

	public static boolean CopyFile(String sourcePath, String destPath) {
		File source = new File(sourcePath);
		File dest = new File(destPath);
		try {
			FileUtils.copyFile(source, dest);
			SSB.Log("File Copied!");
			return true;
		} catch (IOException e) {
			SSB.Log("Failed to copy File!" + e.getMessage());
			return false;
		}
	}
}
