package s3.root.gui.Maps;

import java.util.Random;

import s3.root.gui.Loop.MainLoop;

public class RandomizedMap extends TileMap {

	public RandomizedMap(int mapWidth, int mapHeight) {

		this.width = mapWidth;
		this.height = mapHeight;
		this.xpos = 0;
		this.ypos = 0;
//		this.xpos = (MainLoop.WIDTH / 2) - (this.width * MainLoop.TILESIZE / 2);
//		this.ypos = (MainLoop.HEIGHT / 2) - (this.height * MainLoop.TILESIZE / 2);
		initRandomMap();
	}

	public void initRandomMap() {
		tImgPXs = new int[(width << MainLoop.TILESIZE_DIVIDER) * (height << MainLoop.TILESIZE_DIVIDER)];
		System.out.println(tImgPXs.length);
		init();
	}

	@Override
	protected void init() {
		for (int txpos = 0; txpos < width; txpos++) {
			for (int typos = 0; typos < height; typos++) {
				clorfulRandomization(txpos, typos);
			}
		}
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
	}

	private void clorfulRandomization(int txpos, int typos) {
		int tileXpos = txpos << MainLoop.TILESIZE_DIVIDER;
		int tileYpos = typos << MainLoop.TILESIZE_DIVIDER;
		int color = new Random().nextInt(0x1_000_000);

		for (int x = 0; x < MainLoop.TILESIZE; x++) {
//			if (x + tileXpos < width * MainLoop.TILESIZE || x + tileXpos > 0) {
			for (int y = 0; y < MainLoop.TILESIZE; y++) {
//					if (y + tileYpos < height || y + tileYpos > 0) {
				tImgPXs[(x + tileXpos) + ((y + (tileYpos)) * (width << MainLoop.TILESIZE_DIVIDER))] = color;
			}
		}
	}

	@Override
	public void render(int[] pixels) {
		for (int x = 0; x < (width << MainLoop.TILESIZE_DIVIDER); x++) {
			if (x + xpos >= 0 && x + xpos < MainLoop.WIDTH) for (int y = 0; y < (height << MainLoop.TILESIZE_DIVIDER); y++) {
				if (y + ypos >= 0 && y + ypos < MainLoop.HEIGHT) pixels[(x + xpos) + (y + ypos) * MainLoop.WIDTH] = tImgPXs[x + y * (width << MainLoop.TILESIZE_DIVIDER)];
			}
		}
	}

}
