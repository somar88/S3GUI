package s3.root.gui.Entity;

import java.util.Random;

import s3.root.gui.Loop.MainLoop;

public class Dot {

	public int xpos;
	public int ypos;

	public Dot() {
		xpos = 0;
		ypos = 0;
	}

	public Dot(int x, int y) {
		this.xpos = x;
		this.ypos = y;
	}

	public void update() {

		switch (new Random().nextInt(4)) {
		case 0:
			// move right
			if (xpos == MainLoop.WIDTH - 1)
				break;
			xpos++;
			break;
		case 1:
			// move down
			if (ypos == MainLoop.HEIGHT - 1)
				break;
			ypos++;
			break;
		case 2:
			// move left
			if (xpos == 0)
				break;
			xpos--;
			break;
		case 3:
			// move right
			if (ypos == 0)
				break;
			ypos--;
			break;
		default:
			break;
		}
	}
	
	public void move() {
		
	}

	public void render(MainLoop mainLoop, int[] pixels) {

	}

	public void render(int[] pixels) {
		pixels[xpos + (MainLoop.WIDTH * ypos)] = 0xffffff;
	}

}
