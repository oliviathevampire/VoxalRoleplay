package uk.co.phoenixfirewingz.client.core.Renderer;

public class GameRenderer extends Thread {
    Window window;

    public GameRenderer()
    {
        window = new Window(1280,720);
    }

    @Override
    public void run() {
        while (!window.shouldClose())
        {
            window.update();
        }
    }

    public Window getWindow() {
        return window;
    }
}
