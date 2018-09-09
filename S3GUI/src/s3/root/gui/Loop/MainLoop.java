package s3.root.gui.Loop;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.ArrayList;
import java.util.List;

import s3.root.gui.Entity.Dot;
import s3.root.gui.Layer.Tile;
import s3.root.gui.Maps.LoadableMap;
import s3.root.gui.Screens.ScreensManager;

@SuppressWarnings("serial")
public class MainLoop extends Canvas implements Runnable, MouseListener {

	// Main State Manager
	public ScreensManager SM;

	// dimensions
	public static final int WIDTH = 640;
	public static final int HEIGHT = WIDTH * 9 / 16;
	public static final int SCALE = 2;
	public static final int TILESIZE = 16;

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
	List<Dot> dots = new ArrayList<Dot>();
//	add(new Dot(WIDTH / 2, HEIGHT / 2));
	Tile m01 = Tile.ZERO;
	LoadableMap tm01 = new LoadableMap("/Maps/BigTestMAP.png");

	// Constructor
	public MainLoop() {

		this.image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		this.pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();

		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = 0x000000;
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
//		d01.update();
//		m01.update();
		tm01.update();
		for (Dot i : dots) {
			i.update();
		}
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
		tm01.render(pixels);
		for (Dot i : dots) {
			i.render(pixels);
		}
//		m01.render(pixels);
//		d01.render(pixels);

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
			pixels[i] = 0x000000;
		}
	}

	public void start() {
		if (running == false) {
			running = true;
			thread = new Thread(this, "Main Loop");
			addMouseListener(this);
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

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		System.out.println("X: " + e.getX() + ", Y: " + e.getY());

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		System.out.println("X: " + e.getX() + ", Y: " + e.getY());

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		dots.add(new Dot(e.getX() / MainLoop.SCALE, e.getY() / MainLoop.SCALE));
	}
}
