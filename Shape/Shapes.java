package Shape;

import static org.lwjgl.opengl.GL11.*;

public class Shapes {

    public static void Rect(float x, float y, float width, float height){
        glBegin(GL_QUADS);
        glVertex2f(x,y);
        glVertex2f(x+ width, y);
        glVertex2f(x+ width, y + height );
        glVertex2f(x, y+height);
        glEnd();
    }

    public static void Triangles(float x1, float y1, float x2, float y2, float x3, float y3){
        glBegin(GL_TRIANGLES);
        glVertex2f(x1,y1);
        glVertex2f(x2,y2);
        glVertex2f(x3,y3);
        glEnd();
    }

}
