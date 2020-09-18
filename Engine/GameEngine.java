package Engine;

import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWKeyCallback;
import org.lwjgl.opengl.GL;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11C.*;

public class GameEngine implements Engine{

    private GLFWErrorCallback errorCallback;
    private GameStateManager gsm;
    private GLFWKeyCallback keyCallback;
    private Window window;
    private double secPerframe = 1.0d/50.0d;
    private double secPerUpdate = 1.0d/5.0d;
    private double previous = glfwGetTime();
    double steps = 0.0;

    public GameEngine(){
    }

    public void init(int w, int h, String title, GameStateManager gsm) {
        this.gsm = gsm;
        glfwSetErrorCallback(errorCallback = GLFWErrorCallback.createPrint(System.err));
        if(!glfwInit()){
            throw new IllegalStateException();
        }
        window = new Window(w,h,title);
        /*
        glfwSetKeyCallback(window.getWindow(),keyCallback = new GLFWKeyCallback() {
            @Override
            public void invoke(long window, int key, int scancode, int action, int mods) {
                if(key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE){
                    glfwSetWindowShouldClose(window, true);
                }
            }
        });
        */
        gsm.peek().create(window);

    }

    @Override
    public void loop() {
        GL.createCapabilities();

        while(!glfwWindowShouldClose(window.getWindow())){
            double loopStartTime = glfwGetTime();
            double elapsed = loopStartTime- previous;
            previous = loopStartTime;
            steps += elapsed;
            gsm.peek().input();

            while(steps >= secPerUpdate){
                gsm.peek().update();
                steps -= secPerUpdate;
            }

            gsm.peek().render();
            glfwSwapBuffers(window.getWindow());
            glfwPollEvents();
        }

    }

    @Override
    public void update() {

    }
}
