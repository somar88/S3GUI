package s3.root.gui.Screens;

import java.util.ArrayList;

public class ScreensManager {

	private ArrayList<Screen> states;
	private int currentState;
	
	public static final int MainScreen = 0;

	public ScreensManager() {
		states = new ArrayList<Screen>();
		currentState = MainScreen;
	}

	public void setState(int state) {
		currentState = state;
		states.get(currentState).init();
	}

	public void update() {
		states.get(currentState).update();
	}

	public void draw(java.awt.Graphics2D g) {
		states.get(currentState).draw(g);
	}
}
