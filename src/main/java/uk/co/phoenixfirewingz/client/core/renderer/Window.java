package uk.co.phoenixfirewingz.client.core.renderer;

import org.lwjgl.glfw.GLFW;
import uk.co.phoenixfirewingz.client.core.Main;
import uk.co.phoenixfirewingz.share.util.Tuple;

public class Window {
	private long window_id;

	private final Tuple<Double> mouse_pos = new Tuple<Double>(0D, 0D);

	public Window(int width, int height) {
		if (!GLFW.glfwInit()) {
			Main.getLogger().info("could not start glfw");
			System.exit(-1);
		}
		GLFW.glfwDefaultWindowHints();
		GLFW.glfwWindowHint(GLFW.GLFW_CONTEXT_VERSION_MAJOR, 4);
		GLFW.glfwWindowHint(GLFW.GLFW_CONTEXT_VERSION_MINOR, 6);
		window_id = GLFW.glfwCreateWindow(width, height, "VoxelRoleplay", 0, 0);
		GLFW.glfwSetCursorPosCallback(window_id, (long window, double xpos, double ypos) -> {
			mouse_pos.setOne(xpos);
			mouse_pos.setTwo(ypos);
		});
	}

	public Tuple<Double> getMousePos() {
		return mouse_pos;
	}

	public void update() {
		GLFW.glfwPollEvents();
	}

	public boolean shouldClose() {
		return GLFW.glfwWindowShouldClose(window_id);
	}

	public boolean isMinified() {
		int state = GLFW.glfwGetWindowAttrib(window_id, GLFW.GLFW_ICONIFIED);
		return state == GLFW.GLFW_TRUE;
	}

	public boolean isClicked(int button) {
		return GLFW.glfwGetMouseButton(window_id, button) == GLFW.GLFW_PRESS;
	}

	public boolean isKeyDown(int key) {
		return GLFW.glfwGetKey(window_id, key) == GLFW.GLFW_PRESS;
	}

	public boolean isKeyUp(int key) {
		return GLFW.glfwGetKey(window_id, key) == GLFW.GLFW_RELEASE;
	}

	public double getTime() {
		return GLFW.glfwGetTime();
	}

	public void close() {
		GLFW.glfwSetWindowShouldClose(window_id, true);
	}

	public void cleanup() {
		GLFW.glfwTerminate();
	}

	public long getID() {
		return window_id;
	}
}
