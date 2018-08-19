package s3.root.gui.Screens;

import java.util.ArrayList;

public class ScreensManager {

	private ArrayList<Screen> screens;
	private int currentScreen;

	public static final int MAINSCREEN = 0;

	public ScreensManager() {
		screens = new ArrayList<Screen>();
		currentScreen = MAINSCREEN;
		screens.add(new SandBoxScreen());
	}

	public void setState(int screen) {
		currentScreen = screen;
		screens.get(currentScreen).init();
	}

	public void update() {
		screens.get(currentScreen).update();
	}

	public void render(java.awt.Graphics2D g) {
		screens.get(currentScreen).render(g);
	}
}
