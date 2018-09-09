package s3.root.gui.Maps;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import s3.root.gui.Layer.Tile;
import s3.root.gui.Loop.MainLoop;

public class LoadableMap extends TileMap {

	private int imgPixels[];

	public LoadableMap(String path) {
		try {
			BufferedImage img = ImageIO.read(Tile.class.getResource(path));
			this.width = img.getWidth();
			this.height = img.getHeight();
			tImgPXs = new int[width * height];
			imgPixels = new int[width * MainLoop.TILESIZE * width * MainLoop.TILESIZE];
			img.getRGB(0, 0, width, height, tImgPXs, 0, width);

		} catch (IOException e) {
			e.printStackTrace();
		}

		this.xpos = 0;
		this.ypos = 0;
//		this.xpos = (MainLoop.WIDTH / 2) - (this.width * MainLoop.TILESIZE / 2);
//		this.ypos = (MainLoop.HEIGHT / 2) - (this.height * MainLoop.TILESIZE / 2);
		init();
	}

	@Override
	protected void init() {
		for (int txpos = 0; txpos < this.width; txpos++) {
			for (int typos = 0; typos < this.height; typos++) {
				loadTileAtPosition(currentTileAtPosission(txpos, typos), txpos, typos);
			}
		}

	}

	@Override
	public void update() {

	}

	@Override
	public void render(int[] pixels) {
		for (int x = 0; x < width * MainLoop.TILESIZE; x++) {
			if (x + xpos >= 0 && x + xpos < MainLoop.WIDTH) for (int y = 0; y < height * MainLoop.TILESIZE; y++) {
				if (y + ypos >= 0 && y + ypos < MainLoop.HEIGHT) pixels[(x + xpos) + (y + ypos) * MainLoop.WIDTH] = imgPixels[x + y * width * MainLoop.TILESIZE];
			}
		}
	}

	private void loadTileAtPosition(Tile tile, int Txpos, int Typos) {
		int tileXpos = Txpos << 4;
		int tileYpos = Typos << 4;

		for (int x = 0; x < MainLoop.TILESIZE; x++) {
//			if (x + tileXpos < width * 16 || x + tileXpos > 0) {
			for (int y = 0; y < MainLoop.TILESIZE; y++) {
//					if (y + tileYpos < height || y + tileYpos > 0) {
				imgPixels[(x + tileXpos) + ((y + (tileYpos)) * (width * MainLoop.TILESIZE))] = tile.imgPXs[x + y * tile.getWidth()];
			}
		}
	}

	private Tile currentTileAtPosission(int x, int y) {
		System.out.println();
		int check = tImgPXs[x + y * width] & 0xffffff;
		switch (check) {
		case 0x000000:
			return Tile.ZERO;
		case 0x7F3300:
			return Tile.BRICKWALL;
		case 0x00FFFF:
			return Tile.WATER;
		case 0x00FF21:
			return Tile.GRASS;
		case 0xFF0000:
			return Tile.MUD;
		case 0xFFD800:
			return Tile.SAND;
		default:
			return Tile.ZERO;
		}
	}
}
