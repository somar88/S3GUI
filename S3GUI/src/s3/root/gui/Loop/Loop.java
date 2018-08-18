package s3.root.gui.Loop;

public class Loop implements Runnable {

	public long pre;
	public long now;
	public static int frame_limit = 60;
	public static long fps = 1000_000_000L / frame_limit;

	int frames = 0;
	int ticks = 0;
	long timer = 0;

	private boolean running = false;
	private Thread t;

	@Override
	public void run() {

		pre = System.nanoTime();
		timer = System.currentTimeMillis();
		
		while (running) {
			now = System.nanoTime();
			while (now - pre >= fps) {
				pre = now;
				render();
				frames++;
			}
			update();
			ticks++;
			displayFPS(timer);
		}

	}

	public void displayFPS(long timer) {
		if (System.currentTimeMillis() - timer>= 1000) {
			this.timer += 1000;
			System.out.println("Frames: " + frames + " - Updates: " + ticks);
			frames = 0;
			ticks = 0;
		}
	}

	private void update() {
		// TODO Auto-generated method stub

	}

	private void render() {
		// TODO Auto-generated method stub

	}

	public void start() {
		if (running == false) {
			running = true;
			t = new Thread(this);
		} else
			return;
		t.start();
	}

	public void stop() {
		if (running == true) {
			running = false;
			try {
				t.join();
			} catch (InterruptedException e) {
				System.err.println("Error: Couldn't stop loop thread.");
				e.printStackTrace();
			}
		} else
			return;
	}
}
