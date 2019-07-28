package me.puyodead1.spigotserverbuilder;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import me.puyodead1.spigotserverbuilder.utils.Utils;

public class MainGUI {

	protected Shell shlSpigotServerBuilder;
	public static Text console, text;
	public static Combo combo;
	public static Label downloadProgress;

	public static boolean acceptedEULA = false;
	public static String outputPath, version, SSBVersion = "2.5";
	public static ArrayList<String> versions = new ArrayList<String>();

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// fetch all spigot versions
		URL url;
		try {
			url = new URL("https://hub.spigotmc.org/versions/");

			Scanner sc = new Scanner(url.openStream());
			while (sc.hasNextLine()) {
				String version = Utils.html2text(sc.nextLine()).split(".json")[0];
				if (!version.isEmpty() && !version.contains("Index")) {
					if (version.contains(".")) {
						versions.add(version);
					}
				}
			}

			sc.close();

			// open main window

			MainGUI window = new MainGUI();
			window.open();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents(display);
		shlSpigotServerBuilder.open();
		shlSpigotServerBuilder.layout();
		while (!shlSpigotServerBuilder.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents(Display display) {
		shlSpigotServerBuilder = new Shell(display, SWT.CLOSE | SWT.TITLE | SWT.MIN);
		shlSpigotServerBuilder.setSize(1220, 492);
		shlSpigotServerBuilder.setText("Spigot Server Builder " + SSBVersion + " by Puyodead1");
		shlSpigotServerBuilder.setLayout(null);

		Label lblSelectAVersion = new Label(shlSpigotServerBuilder, SWT.NONE);
		lblSelectAVersion.setBounds(15, 10, 93, 15);
		lblSelectAVersion.setText("Select a version:");

		Label lblChooseOutputPath = new Label(shlSpigotServerBuilder, SWT.NONE);
		lblChooseOutputPath.setBounds(190, 10, 100, 15);
		lblChooseOutputPath.setText("Output Directory:");

		Label lblCopyright = new Label(shlSpigotServerBuilder, SWT.NONE);
		lblCopyright.setBounds(3, 435, 177, 15);
		lblCopyright.setText("Copyright 2018-2019 Puyodead1");

		Button btnGo = new Button(shlSpigotServerBuilder, SWT.NONE);
		btnGo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e1) {
				if (acceptedEULA) {
					if(!outputPath.isEmpty()) {
						version = combo.getText();
						console.setText("");
						SSB.Log("Starting...");
						SSB.Log("Using version: " + version);
						SSB.Init();
						btnGo.setEnabled(false);
					} else {
						SSB.Log("Please choose the output directory!");
					}
				} else {
					SSB.Log("You need to accept the Mojang EULA to continue!");
				}
			}
		});
		btnGo.setBounds(150, 71, 238, 74);
		btnGo.setText("Create");

		Label label = new Label(shlSpigotServerBuilder, SWT.SEPARATOR | SWT.VERTICAL);
		label.setBounds(418, 0, 2, 464);

		Label lblOutput = new Label(shlSpigotServerBuilder, SWT.NONE);
		lblOutput.setAlignment(SWT.CENTER);
		lblOutput.setBounds(571, 10, 296, 15);
		lblOutput.setText("Output:");

		Label label_1 = new Label(shlSpigotServerBuilder, SWT.SEPARATOR | SWT.HORIZONTAL);
		label_1.setBounds(123, 59, 297, 2);

		Label label_2 = new Label(shlSpigotServerBuilder, SWT.SEPARATOR | SWT.VERTICAL);
		label_2.setBounds(123, 0, 2, 163);

		Label label_3 = new Label(shlSpigotServerBuilder, SWT.SEPARATOR | SWT.HORIZONTAL);
		label_3.setBounds(0, 163, 420, 2);

		ScrolledComposite scrolledComposite = new ScrolledComposite(shlSpigotServerBuilder,
				SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		scrolledComposite.setBounds(445, 31, 759, 409);
		scrolledComposite.setExpandHorizontal(true);
		scrolledComposite.setExpandVertical(true);

		console = new Text(scrolledComposite, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL | SWT.CANCEL);
		console.setEditable(false);
		scrolledComposite.setContent(console);
		scrolledComposite.setMinSize(console.computeSize(SWT.DEFAULT, SWT.DEFAULT));

		combo = new Combo(shlSpigotServerBuilder, SWT.NONE);
		combo.setBounds(15, 31, 91, 23);
		combo.add("latest");

		Button btnBrowse = new Button(shlSpigotServerBuilder, SWT.NONE);
		btnBrowse.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				DirectoryDialog dialog = new DirectoryDialog(shlSpigotServerBuilder);
				dialog.setMessage("Select where the server should be created.");
				String dir = dialog.open();
				if (dir != null) {
					text.setText(dir);
					outputPath = dir;
				}

			}
		});
		btnBrowse.setBounds(337, 28, 75, 25);
		btnBrowse.setText("Browse");

		text = new Text(shlSpigotServerBuilder, SWT.BORDER);
		text.setBounds(131, 31, 200, 21);

		Label lblBuildtoolsDownloadProgress = new Label(shlSpigotServerBuilder, SWT.NONE);
		lblBuildtoolsDownloadProgress.setBounds(10, 171, 168, 15);
		lblBuildtoolsDownloadProgress.setText("BuildTools Download Progress:");

		downloadProgress = new Label(shlSpigotServerBuilder, SWT.NONE);
		downloadProgress.setBounds(183, 171, 41, 15);
		downloadProgress.setText("0%");

		Button btnAcceptMojangEula = new Button(shlSpigotServerBuilder, SWT.CHECK);
		btnAcceptMojangEula.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				acceptedEULA = !acceptedEULA;
			}	
		});
		btnAcceptMojangEula.setBounds(4, 138, 104, 16);
		btnAcceptMojangEula.setText("Accept EULA?");

		for (String v : versions) {
			combo.add(v);
		}
	}
}
