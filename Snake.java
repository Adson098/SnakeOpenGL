import java.util.ArrayList;
import java.util.List;

class Cell {

    private int x;
    private int y;
    private boolean frozen;
    public Cell(int x, int y, boolean frozen){
        this.x = x;
        this.y = y;
        this.frozen = true;

    }

    public void setX(int x){this.x = x;
    }
    public void setY(int y){
        this.y = y;
    }
    public void setFrozen(boolean f){
        this.frozen = f;
    }
    public void set(Cell c){
        this.x = c.getX();
        this.y = c.getY();
    }
    public int getX(){
        return this.x;
    }
    public int getY(){
        return this.y;
    }
    public boolean getFrozen(){
        return frozen;
    }
    public int[] get(){return new int[]{this.x,this.y};}
}