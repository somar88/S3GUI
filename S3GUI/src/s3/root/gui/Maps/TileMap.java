package s3.root.gui.Maps;

public abstract class TileMap {

	protected int width;
	protected int height;

	public int xpos = 0;
	public int ypos = 0;

	public int tImgPXs[];

	protected abstract void init();

	public abstract void update();

	public abstract void render(int[] pixels);
}
