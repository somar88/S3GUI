package s3.root.gui.Maps;

import java.util.Random;

import s3.root.gui.Loop.MainLoop;

public class TileMap {

	private int width;
	private int height;

	public int xpos = 0;
	public int ypos = 0;

	public int tImgPXs[];

	public TileMap(int mapWidth, int mapHeight) {
		this.width = mapWidth;
		this.height = mapHeight;
		this.xpos = 0;
		this.ypos = 0;
		init();
	}

	public void init() {
		tImgPXs = new int[width * height * 16 * 16];
		System.out.println(tImgPXs.length);
		randomizeTiles(width, height);
	}

	public void render(int[] pixels) {

		for (int i = 0; i < width * 16; i++) {
			if (i < MainLoop.WIDTH - 1)
				for (int j = 0; j < height * 16; j++) {
					if (j < MainLoop.HEIGHT - 1)
						pixels[(i + xpos) + (j + ypos) * MainLoop.WIDTH] = tImgPXs[i + j * width * 16];
				}
		}

	}

	public void update() {
		this.xpos++;
	}

	private void randomizeTiles(int w, int h) {
		for (int i = 0; i < w; i++) {
			for (int j = 0; j < h; j++) {
				printAtPos(i, j);
			}
		}
	}

	private void printAtPos(int xpos, int ypos) {
		int tileXpos = xpos * 16;
		int tileYpos = ypos * 16;
		int color = new Random().nextInt(0x1_000_000);

		for (int x = 0; x < 16; x++) {
//			if (x + tileXpos < width * 16 || x + tileXpos > 0) {
			for (int y = 0; y < 16; y++) {
//					if (y + tileYpos < height || y + tileYpos > 0) {
				tImgPXs[(x + tileXpos) + ((y + (tileYpos)) * (width * 16))] = color;
			}
		}
	}
}
