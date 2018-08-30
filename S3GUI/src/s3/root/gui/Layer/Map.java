package s3.root.gui.Layer;

import s3.root.gui.Loop.MainLoop;

public class Map extends S3UILayer {

	public Map(int w, int h) {

		this.width = w;
		this.height = h;
		this.pixels = new int[width * height];
		this.xpos = MainLoop.WIDTH / 2;
		this.ypos = MainLoop.HEIGHT / 2;
		init();
	}

	public void render(int[] pixels) {
		for (int x = 0; x < width; x++) {
			if (x + xpos < MainLoop.WIDTH || x + xpos > 0) {
				for (int y = 0; y < height; y++) {
					if (y + ypos < MainLoop.HEIGHT || y + ypos > 0) {
						pixels[(x + xpos) + (y + ypos) * MainLoop.WIDTH] = this.pixels[x + y * width];
					}
				}
			}
		}
	}

	public void update() {
		xpos++;
	}

	private void init() {
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = 0xff00ff;
		}
	}

}
