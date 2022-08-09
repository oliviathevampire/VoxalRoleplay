package uk.co.phoenixfirewingz.client.core.renderer;

public class GameRenderer extends Thread {
	Window window;
	GameGL gl = new GameGL();

	public GameRenderer() {
		window = new Window(1280, 720);
		gl.init(window);
	}

	@Override
	public void run() {
		while (!window.shouldClose()) {
			window.update();
		}
	}

	public Window getWindow() {
		return window;
	}
}
