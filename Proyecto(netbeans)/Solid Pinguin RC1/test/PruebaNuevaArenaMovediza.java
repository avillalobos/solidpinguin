


import Logica.ArenaMovediza;
import Logica.Movimiento;
import java.io.FileNotFoundException;
import java.io.IOException;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author andrew
 */
public class PruebaNuevaArenaMovediza {

    public static void Imprimir(char mapa[][], int y, int x){
        for(int i = 0 ; i< y; i++){
            for(int j = 0; j< x; j++){
                System.out.print(mapa[i][j]);
            }
            System.out.println("");
        }
    }

    public static char[][] CrearMatriz(int y, int x){
        char mapa[][] = new char[y][x];
        for(int i = 0; i< y; i++){
            for(int j = 0; j < x; j++){
                mapa[i][j]= '-';
            }
        }
        return mapa;
    }

    public static void main(String args[]) throws FileNotFoundException, IOException, Exception{
        char mapa[][] = CrearMatriz(20,12);
        mapa[19][11] = 'I';
        mapa[5][4] = '6';
        ArenaMovediza arena = new ArenaMovediza(1, 1, 20,12);
        ArenaMovediza arena1 = new ArenaMovediza(5,4,20,12);
        ArenaMovediza arena2 = new ArenaMovediza(7,1,20,12);
        Movimiento m = arena.Infectar(mapa);
        Movimiento m1 = arena1.Infectar(mapa);
        Movimiento m2 = arena2.Infectar(mapa);
        while(!arena.GameOver() && !arena1.GameOver() && !arena2.GameOver()){
            //System.out.println("Dy = " + m.getY() + " Dx = " + m.getX());
            Imprimir(mapa,20,12);
            System.out.println("------------------------------------\n");
            //System.in.read();
            m = arena.Infectar(mapa);
            m1 = arena1.Infectar(mapa);
            m2 = arena2.Infectar(mapa);
        }
    }
}
