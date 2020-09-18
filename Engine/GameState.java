package Engine;

public interface GameState {

    void create(Window w);
    void render();
    void destroy();
    void input();
    void update();
}
