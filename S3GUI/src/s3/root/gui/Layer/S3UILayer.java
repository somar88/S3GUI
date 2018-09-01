package s3.root.gui.Layer;

public abstract class S3UILayer {

	protected int width;
	protected int height;

	protected int[] imgPXs;

	protected int xpos;
	protected int ypos;

	public abstract void init();

	public abstract void render(int[] pixels);

	public abstract void update();
}
