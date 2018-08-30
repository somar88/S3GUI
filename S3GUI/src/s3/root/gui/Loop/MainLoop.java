package s3.root.gui.Loop;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import s3.root.gui.Entity.Dot;
import s3.root.gui.Layer.Map;
import s3.root.gui.Screens.ScreensManager;

@SuppressWarnings("serial")
public class MainLoop extends Canvas implements Runnable {

	// Main State Manager
	public ScreensManager SM;

	// dimensions
	public static final int WIDTH = 340;
	public static final int HEIGHT = WIDTH * 9 / 16;
	public static final int SCALE = 3;
	public Dimension d = new Dimension(WIDTH * SCALE, HEIGHT * SCALE);

	// main thread
	private boolean running = false;
	boolean initialized = false;
	private Thread thread;

	// frames / time control
	public static int frame_limit = 60;
	public static long fps = 1000_000_000L / frame_limit;
	private long pre;
	private long now;

	// FPS display
	private int frames = 0;
	private int ticks = 0;
	private long timer = 0;

	// image
	private BufferedImage image;
	private Graphics g;
	BufferStrategy bs;
	private int[] pixels;

	// objects
	Dot d01 = new Dot(WIDTH / 2, HEIGHT / 2);
	Map m01 = new Map(50, 50);

	// Constructor
	public MainLoop() {

		this.image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		this.pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();

		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = 0xff00ff;
		}
		this.SM = new ScreensManager();

		setPreferredSize(d);
		setFocusable(true);
		requestFocus();
	}

	@Override
	public void run() {

		pre = System.nanoTime();
		timer = System.currentTimeMillis();

		while (running) {
			now = System.nanoTime();
			while (now - pre >= fps) {
				update();
				ticks++;

				pre = now;
			}
			render(pixels);
			frames++;
			drawToScreen(g);
			displayFPS(timer);
		}

	}

	// display FPS and updates
	public void displayFPS(long timer) {
		if (System.currentTimeMillis() - timer >= 1000) {
			this.timer += 1000;
			System.out.println("Frames: " + frames + " - Updates: " + ticks);
			frames = 0;
			ticks = 0;
		}
	}

	private void update() {

//		pixels[new Random().nextInt(pixels.length)] = 0xdfb160;

		d01.update();
		m01.update();
//		for (int i = 0; i < pixels.length; i++) {
//			if (i % 2 == 0) {
//				pixels[i] = 0xff0000;
//			}
//			if (i % 3 == 0) {
//				pixels[i] = 0xdfb160;
//			}
//			if (i % 5 == 0) {
//				pixels[i] = 0x000ff;
//			}
//			if (i % 7 == 0) {
//				pixels[i] = 0xffff00;
//			}
//		}
	}

	private void render(int pixels[]) {

		m01.render(pixels);
		d01.render(pixels);

	}

	private void drawToScreen(Graphics g) {

		bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		g = bs.getDrawGraphics();

		// rendering Area 51 :P
		////////////////////////////////////////////////////////////////////

		g.drawImage(image, 0, 0, WIDTH * SCALE, HEIGHT * SCALE, null);

		////////////////////////////////////////////////////////////////////

		g.dispose();
		bs.show();
		clear(pixels);
	}

	private void clear(int[] pixels) {
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = 0x6E3562;
		}
	}

	public void start() {
		if (running == false) {
			running = true;
			thread = new Thread(this, "Main Loop");
		} else
			return;
		thread.start();
	}

	public void stop() {
		if (running == true) {
			running = false;
			try {
				thread.join();
			} catch (InterruptedException e) {
				System.err.println("Error: Couldn't stop loop thread.");
				e.printStackTrace();
			}
		} else
			return;
	}
}
