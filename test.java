import java.util.ArrayList;
import java.util.List;

public class test {
    public static void main(String[] args){
        List<int[]> lista = new ArrayList<>();
        lista.add(new int[] {1,2});
        lista.add(lista.get(0));
        lista.set(0,new int[]{5,6});
        for(int[] x: lista){
            System.out.println(x[0] + " "  +x[1]);

        }
    }}
