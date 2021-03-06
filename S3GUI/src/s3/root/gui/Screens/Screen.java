package s3.root.gui.Screens;

public abstract class Screen {

	protected ScreensManager sm;

	public abstract void init();

	public abstract void update();

	public abstract void render(java.awt.Graphics2D g);

}
