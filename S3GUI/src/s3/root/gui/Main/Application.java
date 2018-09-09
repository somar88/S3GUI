package s3.root.gui.Main;

import java.awt.Color;

import javax.swing.JFrame;
import s3.root.gui.Loop.MainLoop;

public class Application {

	public static void main(String[] args) {

		JFrame window = new JFrame("Start Window!");
		MainLoop MainLoop = new MainLoop();
		window.setResizable(false);
		window.add(MainLoop);
//		window.setUndecorated(true);
		window.setBackground(Color.BLACK);
		window.pack();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setLocationRelativeTo(null);
		window.setVisible(true);
		MainLoop.start();
	}

}