package uk.co.phoenixfirewingz.client.core.renderer;


import org.lwjgl.glfw.GLFW;
import static org.lwjgl.opengl.GL46C.*;
import org.lwjgl.system.NativeType;
import uk.co.phoenixfirewingz.client.util.Colour;

public class GameGL
{
    private interface GLCallback
    {
        void execute();
    }
    boolean depth_write_enabled = false;
    boolean depth_test_enabled = false;
    boolean blend_enabled = false;
    boolean culling_enabled = false;
    BlendMode Blend;
    int colour_mask = 0;
    Colour clear_colour = new Colour(255);
    float clear_depth = 1.0f;

    // this is used to check current state
    private<T> void statecheck(T context_state_store, T challenger_state,GLCallback func) {
        // if the current state is not av
        if(context_state_store != challenger_state) {
            context_state_store = challenger_state;
            func.execute();
        }
    }

    public GameGL() {}
    public void init(Window window)
    {
        GLFW.glfwMakeContextCurrent(window.getID());
    }

    public long getContext()
    {
        return GLFW.glfwGetCurrentContext();
    }

    public void clear(Colour colour, float depth, int mask_bits)
    {
        statecheck(depth_write_enabled, true,() ->  glDepthMask(true));
        statecheck(colour_mask, 255,() ->  glColorMask(true,true,true,true));
        statecheck(clear_colour, colour,() ->  glClearColor(colour.getR(), colour.getG(), colour.getB(), colour.getA()));
        statecheck(clear_depth, depth,() -> glClearDepth(depth));
        glClear(mask_bits);
    }

    public void swapBuffer(Window window)
    {
        GLFW.glfwSwapBuffers(window.getID());
    }

    public void glToggle(@NativeType("GLenum") int key, boolean value)
    {
        if (value)
            glEnable(key);
        else
            glDisable(key);
    }
}
