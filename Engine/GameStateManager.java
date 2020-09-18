package Engine;

import java.util.Stack;

public class GameStateManager  {

    Stack<GameState> gameState = new Stack<GameState>();

    public GameStateManager(){
    }

    public void push(GameState gm){
        gameState.push(gm);
    }

    public void pop(){
        gameState.pop();
    }

    public GameState peek(){
        return gameState.peek();
    }
}
