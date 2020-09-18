import org.lwjgl.opengl.GL11;

import static org.lwjgl.opengl.GL11.*;

public class Shapes {


    public static void Rect(float x, float y, float width, float height) {
        glBegin(GL_QUADS);
        glVertex2f(x, y);
        glVertex2f(x+width, y);
        glVertex2f(x+width, y+height);
        glVertex2f(x,height+y);
        glEnd();
    }
}
