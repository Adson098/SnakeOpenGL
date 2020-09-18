import Engine.GameState;
import Engine.Window;
import org.lwjgl.glfw.GLFWKeyCallback;
import org.lwjgl.opengl.GL11;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL11C.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11C.GL_DEPTH_BUFFER_BIT;
import static org.lwjgl.opengl.GL11C.GL_LINE;
import static org.lwjgl.opengl.GL11C.glClear;
import static org.lwjgl.opengl.GL11C.glClearColor;

public class PlayState implements GameState {

    private int mapWidth = 18;
    private int mapHeight = 18;
    private Window window ;
    private GLFWKeyCallback keyCallback;
    private int direction = 2; // 0-> lewo 1-> prawo 2-> góra 3-> dół
    private int[] food_coords ;
    private int score =0;
    private int size = 1;
    private Cell[] snake = new Cell[mapWidth*mapHeight];

    private int[][] map = {{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}};

    @Override
    public void create(Window w) {
        this.window = w;
        snake[0] = new Cell(7,9,false);
        snake[1] = new Cell(8,9,false);
        snake[2] = new Cell(9,9,false);
        snake[3] = new Cell(10,9,false);
        snake[4] = new Cell(11,9,false);
        food_coords = new int[2];
        food_spawn();
        System.out.println("create");
    }

    @Override
    public void render() {
        glClearColor(0.5f,0.0f,0.0f,0.0f);
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
        glColor3f(0.1f, 0f, 0.1f);
        drawGrid(map);

    }

    @Override
    public void destroy() {

    }

    @Override
    public void input() {
        glfwSetKeyCallback(window.getWindow(), keyCallback = new GLFWKeyCallback() {
            @Override
            public void invoke (long window, int key, int scancode, int action, int mods) {
                if ( key == GLFW_KEY_W && direction != 1) {
                    direction =  0;
                } else if ( key == GLFW_KEY_A  && direction != 3) {
                    direction = 2;

                } else if ( key == GLFW_KEY_S && direction != 0) {
                    direction = 1;
                    //food_spawn();

                } else if ( key == GLFW_KEY_D && direction != 2) {
                    direction = 3;
                }
               else if(key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE){
                    glfwSetWindowShouldClose(window, true);
                }
            }
        });
    }

    @Override
    public void update() {
        int[] head_coords = new int[2];

        if (snake[0].getX() == food_coords[0] && snake[0].getY() == food_coords[1]) {
            eat_food();
            food_spawn();
            size++;
            score++;
            System.out.println("SCORE : " + score);
        }

        if (size >= 2) {
            for (int a = size - 1; a > 0; a--) {
                if (snake[a].getFrozen() == false) {
                    snake[a].set(snake[a - 1]);
                }
            }
        }

            if (direction == 0) { //lewo
                if (snake[0].getX() > 0) {
                    snake[0].setX(snake[0].getX()-1);
                } else {
                    gameover();

                }
            } else if (direction == 1) { //prawo
                if (snake[0].getX() < mapWidth - 1) {
                    snake[0].setX(snake[0].getX()+1);
                } else {
                    gameover();

                }
            } else if (direction == 2) { //góra
                if (snake[0].getY() > 0) {
                    snake[0].setY(snake[0].getY()-1);
                } else {
                    gameover();
                }
            } else if (direction == 3) { //dół
                if (snake[0].getY() < mapHeight - 1) {
                    snake[0].setY(snake[0].getY()+1);
                } else {
                    gameover();

                }
            }

            for(int a = 0 ; a < size;a++){
                snake[a].setFrozen(false);
            }
        if(size > 3){
            for(int a = 1;a<+size;a++){
                if(snake[0].getX() == snake[a].getX() && snake[0].getY() == snake[a].getY()){
                    gameover();
                }
            }
        }


        }


        private void drawGrid ( int[][] map){
            clearMap();
            map[food_coords[0]][food_coords[1]] = 2;
            drawSnake();
            for (float i = 0; i < mapWidth; i++) {
                for (float j = 0; j < mapHeight; j++) {

                    if (map[(int) j][(int) i] == 0) {
                        if (i % 2 == 0 && j % 2 == 0) {
                            glColor3f(0.678f, 1.000f, 0.184f); // zielony
                        } else if (i % 2 == 0 && j % 2 != 0) {
                            glColor3f(0.251f, 0.878f, 0.816f); // niebieski
                        } else if (i % 2 != 0 && j % 2 == 0) {
                            glColor3f(0.251f, 0.878f, 0.816f); // niebieski
                        } else if (i % 2 != 0 && j % 2 != 0) {
                            glColor3f(0.678f, 1.000f, 0.184f); // zielony
                        } else if (map[(int) j][(int) i] == 2) {
                            glColor3f(0.1f, 1f, 0.1f);
                        }
                    } else if (map[(int) j][(int) i] == 2) {
                        glColor3f(1f, 0, 0);
                    } else if (map[(int) j][(int) i] == 1) {
                        glColor3f(0.000f, 0.502f, 0.000f);
                    }
                    Shapes.Rect(-0.9f + i / 10, 0.8f - (j / 10), 0.1f, 0.1f);

                }
            }
        }

        private void food_spawn () {
            int x, y;
            Random rand = new Random();
            x = rand.nextInt(mapWidth);
            y = rand.nextInt(mapHeight);
            food_coords[0] = x;
            food_coords[1] = y;
        }

        private void clearMap () {
            for (float i = 0; i < mapWidth; i++) {
                for (float j = 0; j < mapHeight; j++) {
                    map[(int) j][(int) i] = 0;
                }
            }
        }
        private void drawSnake () {
            for (int a = 0; a < size; a++) {
                map[snake[a].getX()][snake[a].getY()] = 1;
            }

        }
        private void eat_food () {
            snake[size] = new Cell(snake[size - 1].getX(), snake[size - 1].getY(), true);

        }
        private void gameover () {
             glfwSetWindowShouldClose(window.getWindow(), true);
        }
    }
