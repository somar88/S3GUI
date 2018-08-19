package s3.root.gui.Main;

import javax.swing.JFrame;

import s3.root.gui.Loop.MainLoop;

public class Main {

	public static void main(String[] args) {

		JFrame window = new JFrame("Main Window!");
		window.setContentPane(new MainLoop());
		window.setResizable(false);
		window.pack();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);

	}

}
