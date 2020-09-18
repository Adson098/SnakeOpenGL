import Engine.GameEngine;
import Engine.GameStateManager;

import static org.lwjgl.glfw.GLFW.glfwTerminate;

public class Main {
    public static final int WIDTH = 800;
    public static final int HEIGHT = 580;

    public static void main(String[] args){
        GameStateManager gsm = new GameStateManager();
        gsm.push(new PlayState());
        GameEngine gm = new GameEngine();
        try {
            gm.init(WIDTH,HEIGHT,"Snake", gsm);
            gm.loop();
        }
        finally {
            glfwTerminate();
        }


    }
}
