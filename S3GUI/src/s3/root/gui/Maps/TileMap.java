package s3.root.gui.Maps;

import java.awt.Image;

import s3.root.gui.Loop.MainLoop;

public class TileMap {

	private int width;
	private int height;

	public int xpos = 0;
	public int ypos = 0;

	public int tImgPXs[];

	public void init() {
		this.width = 64;
		this.height = 64;
		this.xpos = 0;
		this.ypos = 0;
		tImgPXs = new int[width * height * 256];
		randomizeTiles(width, height);
	}

	public void render(int[] pixels) {
	}

	public void update() {
	}

	private void randomizeTiles(int w, int h) {
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				printAtPos(i, j);
			}
		}
	}
	
	private void printAtPos(int xpos, int ypos) {

		for (int x = 0; x < 16; x++) {
			if (x + xpos < MainLoop.WIDTH || x + xpos > 0) {
				for (int y = 0; y < 16; y++) {
					if (y + ypos < MainLoop.HEIGHT || y + ypos > 0) {
						tImgPXs[(x + xpos) + (y + ypos) * MainLoop.WIDTH] = this.tImgPXs[x + y * width];
					}
				}
			}
		}
	}
}
