package me.puyodead1.spigotserverbuilder.utils;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;

import me.puyodead1.spigotserverbuilder.Download;
import me.puyodead1.spigotserverbuilder.MainGUI;
import me.puyodead1.spigotserverbuilder.SSB;

public class Utils extends Thread {
	public static String OS, spigotName;
	public static Download download;

	public static void Complete() {
		SSB.Log(">>>>>>> --------------------------------------------------------------------------- <<<<<<<");
		SSB.Log(">>>>>>> Complete! Please use the Launcher.bat file to start your new server! Enjoy! <<<<<<<");
		SSB.Log(">>>>>>> --------------------------------------------------------------------------- <<<<<<<");
	}

	public static boolean CreateEULA() {
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
				StringWriter writer = new StringWriter();
				e.printStackTrace(new PrintWriter(writer));
				SSB.Log(writer.toString());
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
				StringWriter writer = new StringWriter();
				e.printStackTrace(new PrintWriter(writer));
				SSB.Log(writer.toString());
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

	public static void LauncherHandlerWindows() {
		if (CheckForFile(MainGUI.outputPath + "\\SpigotServer\\Launcher.bat")) {
			// true if exists
			SSB.Log("Launcher exists.. Skipping...");
			Complete();
		} else {
			// false if no
			File launcher = new File(MainGUI.outputPath + "\\SpigotServer\\Launcher.bat");
			try {
				launcher.createNewFile();
				PrintWriter pw = new PrintWriter(MainGUI.outputPath + "\\SpigotServer\\Launcher.bat");
				if(spigotName.isEmpty()) {
					pw.write(String.format("java -jar spigot-%s.jar", MainGUI.version));	
				} else {
					pw.write(String.format("java -jar %s", spigotName));
				}
				pw.close();
				SSB.Log("Launcher write complete.");
				Complete();
			} catch (IOException e) {
				StringWriter writer = new StringWriter();
				e.printStackTrace(new PrintWriter(writer));
				SSB.Log(writer.toString());
			}
		}
	}

	public static boolean LauncherHandlerLinux() {
		return false;
	}

	public static void CheckForDirectory(String path) {
		File in = new File(path);
		if (!in.exists()) {
			// false not exists
			try {
				CreateDirectory(path);
				SSB.Log("Created Directory at: " + path);
			} catch (Exception e) {
				StringWriter writer = new StringWriter();
				e.printStackTrace(new PrintWriter(writer));
				SSB.Log(writer.toString());
			}
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
				StringWriter writer = new StringWriter();
				e.printStackTrace(new PrintWriter(writer));
				SSB.Log(writer.toString());
				return false;
			}
		}
	}

	public static void DownloadBuildTools() {
		URL url = null;
		try {
			url = new URL(
					"https://hub.spigotmc.org/jenkins/job/BuildTools/lastSuccessfulBuild/artifact/target/BuildTools.jar");

			download = new Download(url, MainGUI.outputPath + "\\BuildTools\\BuildTools.jar");

		} catch (MalformedURLException e) {
			SSB.Log("Cought a Malformed URL Exception!");
			StringWriter writer = new StringWriter();
			e.printStackTrace(new PrintWriter(writer));
			SSB.Log(writer.toString());
		}
	}

	public static boolean DownloadBuildToolsLinux() {
		return false;
	}

	public static void runBT() {
		Thread thread = new CompileBT();
		thread.start();
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
	public static void CopySpigotJar() {
		File btFolder = new File(String.format("%s\\BuildTools", MainGUI.outputPath));

		File[] files = btFolder.listFiles();
		for (File file : files) {
			if (file.getName().split("-")[0].equals("spigot")) {
				try {
					spigotName = file.getName();
					File serverFolder = new File(String.format("%s\\SpigotServer\\%s", MainGUI.outputPath, file.getName()));
					FileUtils.copyFile(file, serverFolder);
				} catch (IOException e) {
					SSB.Log("Failed to copy spigot jar!");
					
					StringWriter writer = new StringWriter();
					e.printStackTrace(new PrintWriter(writer));
					SSB.Log(writer.toString());
					return;
				}
			}
		}
		
		Thread scrub = new ScrubDirectory();
		scrub.start();
	}

	public static String html2text(String html) {
		return Jsoup.parse(html).text();
	}
}
