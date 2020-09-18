package Shape;

import static org.lwjgl.opengl.GL11.*;

public class Rect {
    float x;
    float y;
    float width;
    float height;

    public Rect(float x, float y, float width, float height){

    }

    public void draw(){
        glBegin(GL_QUADS);
        glVertex2f(x,y);
        glVertex2f(x+ width, y);
        glVertex2f(x+ width, y + height );
        glVertex2f(x, y+height);
        glEnd();

    }
}
