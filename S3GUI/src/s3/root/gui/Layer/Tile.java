package s3.root.gui.Layer;

import s3.root.gui.Loop.MainLoop;

public class Tile extends S3UILayer {
	
	public String path;
	
	public Tile(String path) {
		
	}

	public Tile(int w, int h) {

		this.width = w;
		this.height = h;
		this.imgPXs = new int[width * height];
		this.xpos = MainLoop.WIDTH / 2;
		this.ypos = MainLoop.HEIGHT / 2;
		init();
	}

	@Override
	public void render(int[] pixels) {
		for (int x = 0; x < width; x++) {
			if (x + xpos < MainLoop.WIDTH || x + xpos > 0) {
				for (int y = 0; y < height; y++) {
					if (y + ypos < MainLoop.HEIGHT || y + ypos > 0) {
						pixels[(x + xpos) + (y + ypos) * MainLoop.WIDTH] = this.imgPXs[x + y * width];
					}
				}
			}
		}
	}

	@Override
	public void update() {
	}

	@Override
	public void init() {
		for (int i = 0; i < imgPXs.length; i++) {
			if (i % width == i)
				imgPXs[i] = 0xff0000;// upper side
			else if (i % width == 0)
				imgPXs[i] = 0x00ff00;// left side
			else if (i % width == width - 1)
				imgPXs[i] = 0x0000ff;// right side
			else if (i >= imgPXs.length - width)
				imgPXs[i] = 0xffffff;// down side
			else
				imgPXs[i] = 0xff00ff;// rest
		}
	}

}
