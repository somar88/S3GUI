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
		initRandomMap();
	}

	public void initRandomMap() {
		tImgPXs = new int[width * height * 16 * 16];
		System.out.println(tImgPXs.length);
		randomizeTiles(width, height);
	}

	public void render(int[] pixels) {

		for (int x = 0; x < width * 16; x++) {
			if (x + xpos > 0 && x + xpos < MainLoop.WIDTH)
				for (int y = 0; y < height * 16; y++) {
					if (y + ypos > 0 && y + ypos < MainLoop.HEIGHT)
						pixels[(x + xpos) + (y + ypos) * MainLoop.WIDTH] = tImgPXs[x + y * width * 16];
				}
		}

	}

	public void update() {
		this.ypos++;
		this.xpos++;
	}
	
	private void loadTilesFromImage() {
		
	}

	private void randomizeTiles(int w, int h) {
		for (int txpos = 0; txpos < w; txpos++) {
			for (int typos = 0; typos < h; typos++) {
				printAtPos(txpos, typos);
			}
		}
	}

	private void printAtPos(int txpos, int typos) {
		int tileXpos = txpos << 4;
		int tileYpos = typos << 4;
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
