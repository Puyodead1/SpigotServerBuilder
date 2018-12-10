package io.github.puyodead1.SSB;

import java.util.Arrays;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class MainGUI {

	protected Shell shlSpigotServerBuilder;
	private Text txtOut;
	public static String outputPath;
	public static String version;
	public static Text console;
	
	public static void Rev() {
		switch (version) {
		case "latest":
			SSB.rev = "latest";
			break;
		case "1.12.2":
			SSB.rev = "1.12.2";
			break;
		case "1.12.1":
			SSB.rev = "1.12.1";
			break;
		case "1.12":
			SSB.rev = "1.12";
			break;
		case "1.11":
			SSB.rev = "1.11";
			break;
		case "1.10":
			SSB.rev = "1.10";
			break;
		case "1.9.4":
			SSB.rev = "1.9.4";
			break;
		case "1.9.2":
			SSB.rev = "1.9.2";
			break;
		case "1.9":
			SSB.rev = "1.9";
			break;
		case "1.8.8":
			SSB.rev = "1.8.8";
			break;
		case "1.8.7":
			SSB.rev = "1.8.7";
			break;
		case "1.8.3":
			SSB.rev = "1.8.3";
			break;
		case "1.8":
			SSB.rev = "1.8";
			break;
		default:
			SSB.rev = "latest";
			break;
		}
	}

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			MainGUI window = new MainGUI();
			window.open();
		} catch (Exception e) {
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
		shlSpigotServerBuilder.setSize(1220, 680);
		shlSpigotServerBuilder.setText("Spigot Server Builder V2.1 by Puyodead1");
		shlSpigotServerBuilder.setLayout(null);

		List list = new List(shlSpigotServerBuilder, SWT.BORDER);
		list.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent arg0) {
				String selectedVersion = Arrays.toString(list.getSelection());
				switch (selectedVersion) {
				case "[latest]":
					version = "latest";
					break;
				case "[1.13]":
					version = "1.13";
					break;
				case "[1.12.2]":
					version = "1.12.2";
					break;
				case "[1.12.1]":
					version = "1.12.1";
					break;
				case "[1.12]":
					version = "1.12";
					break;
				case "[1.11]":
					version = "1.11";
					break;
				case "[1.10]":
					version = "1.10";
					break;
				case "[1.9.4]":
					version = "1.9.4";
					break;
				case "[1.9.2]":
					version = "1.9.2";
					break;
				case "[1.9]":
					version = "1.9";
					break;
				case "[1.8.8]":
					version = "1.8.8";
					break;
				case "[1.8.7]":
					version = "1.8.7";
					break;
				case "[1.8.3]":
					version = "1.8.3";
					break;
				case "[1.8]":
					version = "1.8";
					break;
				}
				Rev();
			}
		});
		list.setBounds(34, 31, 63, 207);

		Label lblSelectAVersion = new Label(shlSpigotServerBuilder, SWT.NONE);
		lblSelectAVersion.setBounds(24, 10, 93, 15);
		lblSelectAVersion.setText("Select a version:");

		Label lblChooseOutputPath = new Label(shlSpigotServerBuilder, SWT.NONE);
		lblChooseOutputPath.setBounds(150, 10, 167, 15);
		lblChooseOutputPath.setText("Enter Path to output directory:");
		
		Label lblCopyright = new Label(shlSpigotServerBuilder, SWT.NONE);
		lblCopyright.setBounds(15, 250, 300, 15);
		lblCopyright.setText("Copyright 2018 Puyodead1 and Puyodead1 Development");
		
		Label lblCompiledOn = new Label(shlSpigotServerBuilder, SWT.NONE);
		lblCompiledOn.setBounds(15, 300, 300, 15);
		lblCompiledOn.setText("Compiled on 12/10/2018 at 11:11AM EST");

		txtOut = new Text(shlSpigotServerBuilder, SWT.BORDER);
		txtOut.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if (arg0.keyCode == 13) {
					outputPath = txtOut.getText();
					console.append("Set output path to: " + outputPath + "\n");
				}
			}
		});

		txtOut.setText("Directory output path");
		txtOut.setBounds(160, 32, 126, 21);

		Button btnGo = new Button(shlSpigotServerBuilder, SWT.NONE);
		btnGo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e1) {
				console.setText(" ");
				if (txtOut.getText() != null || txtOut.getText() != "Directory output path") {
					outputPath = txtOut.getText();
					console.append("Set output path to: " + outputPath + "\n");
				}
				console.append("Started!\n");
				SSB.Init();
			}
		});
		btnGo.setBounds(163, 83, 126, 74);
		btnGo.setText("GO!");

		Label label = new Label(shlSpigotServerBuilder, SWT.SEPARATOR | SWT.VERTICAL);
		label.setBounds(332, 0, 2, 464);

		Label lblOutput = new Label(shlSpigotServerBuilder, SWT.NONE);
		lblOutput.setAlignment(SWT.CENTER);
		lblOutput.setBounds(571, 10, 296, 15);
		lblOutput.setText("Output:");

		Label label_1 = new Label(shlSpigotServerBuilder, SWT.SEPARATOR | SWT.HORIZONTAL);
		label_1.setBounds(123, 59, 211, 2);

		Label label_2 = new Label(shlSpigotServerBuilder, SWT.SEPARATOR | SWT.VERTICAL);
		label_2.setBounds(123, 0, 2, 246);

		Label label_3 = new Label(shlSpigotServerBuilder, SWT.SEPARATOR | SWT.HORIZONTAL);
		label_3.setBounds(0, 244, 334, 2);

		ScrolledComposite scrolledComposite = new ScrolledComposite(shlSpigotServerBuilder,
				SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		scrolledComposite.setBounds(340, 51, 854, 412);
		scrolledComposite.setExpandHorizontal(true);
		scrolledComposite.setExpandVertical(true);

		console = new Text(scrolledComposite, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL | SWT.CANCEL);
		console.setEditable(false);
		scrolledComposite.setContent(console);
		scrolledComposite.setMinSize(console.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		String[] versions = { "latest", "1.13", "1.12.2", "1.12.1", "1.12", "1.11", "1.10", "1.9.4", "1.9.2", "1.9",
				"1.8.8", "1.8.7", "1.8.3", "1.8" };
		for (int i = 0; i < versions.length; i++) {
			list.add(versions[i]);
		}
	}
}
