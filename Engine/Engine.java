package Engine;

public interface Engine {

    void init(int w, int h, String title, GameStateManager gsm);
    void loop();
    void update();

}
