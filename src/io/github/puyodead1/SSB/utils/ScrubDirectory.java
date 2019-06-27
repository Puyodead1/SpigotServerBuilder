package io.github.puyodead1.SSB.utils;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import org.apache.commons.io.FileUtils;
import org.eclipse.swt.widgets.Display;

import io.github.puyodead1.SSB.MainGUI;
import io.github.puyodead1.SSB.SSB;

public class ScrubDirectory extends Thread {
	
	public void update(String string) {
		Display.getDefault().asyncExec(new Runnable() {
			public void run() {
				SSB.Log(string);
			}
		});
	}

	public void run() {
		try {
			File btFolder = new File(String.format("%s\\BuildTools", MainGUI.outputPath));
			update("Scrubbing the BuildTools directory... Please wait!!");
			
			FileUtils.deleteDirectory(btFolder);
			
			update("Deleted the BuildTools directory.");
			update(" ");
			update("You may now safely close this program!");
		} catch (IOException e) {
			update("Failed to delete the BuildTools Directory...");
			
			StringWriter writer = new StringWriter();
			e.printStackTrace(new PrintWriter(writer));
			update(writer.toString());
		}	
	}
}
