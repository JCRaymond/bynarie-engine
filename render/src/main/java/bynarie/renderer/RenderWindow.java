package bynarie.renderer;

import bynarie.engine.Engine;
import bynarie.engine.PhysicsObject;
import org.lwjgl.BufferUtils;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;

import java.nio.IntBuffer;
import java.util.Collection;
import java.util.Collections;

import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.NULL;

public class RenderWindow {
    private long window;
    private int width;
    private int height;
    private float viewScale = 10;

    private Collection<Renderable> items;
    private Engine engine;

    public RenderWindow(Engine engine) {
        this(engine, 300, 300);
    }

    public RenderWindow(Engine engine, int width, int height) {
        this.engine = engine;
        this.width = width;
        this.height = height;
    }

    public void start() {
        start(60);
    }

    public void start(int cyclesPerSecond, double cycleRate) {
        try {
            init();
            engine.start(cyclesPerSecond, cycleRate);
            loop();
            engine.stop();

            glfwFreeCallbacks(window);
            glfwDestroyWindow(window);
        } finally {
            glfwTerminate();
            glfwSetErrorCallback(null).free();
        }
    }

    public void start(int cyclesPerSecond){
        this.start(cyclesPerSecond, 1);
    }

    private void init() {
        GLFWErrorCallback.createPrint(System.err).set();

        if (!glfwInit())
            throw new IllegalStateException("Unable to initialize GLFW");

        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);

        window = glfwCreateWindow(width, height, "Bynarie Engine", NULL, NULL);
        if (window == NULL)
            throw new RuntimeException("Failed to create the GLFW window");

        glfwSetKeyCallback(window, (window, key, scan, action, mods) -> {
            if (key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE)
                glfwSetWindowShouldClose(window, true);

            if (key == GLFW_KEY_SPACE && action == GLFW_RELEASE) {
                if (engine.isPaused())
                    engine.play();
                else
                    engine.pause();
            }
        });

        GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());
        glfwSetWindowPos(
                window,
                (vidmode.width() - width) / 2,
                (vidmode.height() - height) / 2
        );

        glfwMakeContextCurrent(window);
        glfwSwapInterval(1);
        glfwShowWindow(window);
    }

    private void loop() {
        GL.createCapabilities();

        glClearColor(.1f, .1f, .1f, 1f);

        while (!glfwWindowShouldClose(window)) {
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

            IntBuffer w = BufferUtils.createIntBuffer(1);
            IntBuffer h = BufferUtils.createIntBuffer(1);
            glfwGetWindowSize(window, w, h);
            int width = w.get(0);
            int height = h.get(0);

            glPushMatrix();
            glOrtho(-viewScale / 2, viewScale / 2, -viewScale / 2 * height / width, viewScale / 2 * height / width, -1, 1);

            for (Object po : this.engine.getObjects().toArray()) {
                if (!Renderable.class.isAssignableFrom(po.getClass())){
                    continue;
                }
                Renderable item = (Renderable) po;
                int[] indices = item.getIndices();
                double[] vertices = item.getVertexData();
                int primitive = item.getPrimitive();
                int stride = item.getStride();
                double[] color = item.getColor();
//                int stride = 3;
                glColor3dv(color);
                glBegin(primitive);
                for (int i : indices) {
                    for (int j = 0; j < stride; j++) {
                        glVertex3d(vertices[i * stride], vertices[i * stride + 1], vertices[i * stride + 2]);
                    }
                }
                glEnd();
            }

            glPopMatrix();

            glfwSwapBuffers(window);
            glfwPollEvents();
        }
    }

    public float getViewScale() {
        return viewScale;
    }

    public void setViewScale(float viewScale) {
        this.viewScale = viewScale;


    }
}
